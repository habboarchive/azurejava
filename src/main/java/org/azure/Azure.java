package org.azure;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.lexicalscope.jewel.cli.CliFactory;
import com.mycila.guice.ext.closeable.CloseableModule;
import com.mycila.guice.ext.jsr250.Jsr250Module;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.netflix.config.*;
import com.netflix.config.sources.JDBCConfigurationSource;
import com.netflix.governator.annotations.AutoBindSingleton;
import com.netflix.governator.configuration.ArchaiusConfigurationProvider;
import com.netflix.governator.guice.LifecycleInjector;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.azure.network.NetworkBootstrap;
import org.azure.network.sessions.SessionManager;
import org.azure.utils.AzureArgs;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import javax.sql.DataSource;

@AutoBindSingleton
public class Azure {
    private static Logger logger = LogManager.getLogger(Azure.class);
    private static Azure instance;
    private static SessionManager sessionManager;
    private Injector injector;
    private HikariDataSource dataSource;
    @Inject
    private NetworkBootstrap network;


    @Inject
    protected Azure(Injector injector) {
        this.injector = injector;
    }

    public static Azure getInstance() {
        return instance;
    }

    public static void main(String[] args) throws ConfigurationException {
        sessionManager = new SessionManager();

        final AzureArgs arguments = CliFactory.parseArguments(AzureArgs.class, args);

        logger.info("Initializing");

        HikariDataSource hikariDataSource = initializeDataSource(arguments);
        initializeConfiguration(arguments, hikariDataSource);
        Injector injector = initializeInjector();

        instance = injector.getInstance(Azure.class);
        instance.setDataSource(hikariDataSource);
        NetworkBootstrap.setInjector(injector);
        logger.info(instance.getDataSource().toString());
        instance.initializeGameServer();
    }

    private static HikariDataSource initializeDataSource(AzureArgs arguments) {
        logger.info("Initializing data source");
        HikariConfig hikariConfig = new HikariConfig();

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName(arguments.getDatabaseHost());
        dataSource.setPort(arguments.getDatabasePort());
        dataSource.setUser(arguments.getDatabaseUser());
        dataSource.setPassword(arguments.getDatabasePassword());
        dataSource.setDatabaseName(arguments.getDatabase());

        hikariConfig.setDataSource(dataSource);
        hikariConfig.setUsername(arguments.getDatabaseUser());
        hikariConfig.setPassword(arguments.getDatabasePassword());

        return new HikariDataSource(hikariConfig);
    }

    private static SessionFactory buildSessionFactory() {
        try {
            org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
            configuration.configure();
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            return sessionFactory;
        }
        catch (HibernateException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static Injector initializeInjector() {
        logger.info("Creating lifecycle injector");
        try {
            return LifecycleInjector
                    .builder()
                    .usingBasePackages("org.azure")
                    .withBootstrapModule(
                            binder -> binder.bindConfigurationProvider().toInstance(ArchaiusConfigurationProvider.builder().build())
                    )
                    .withModuleClass(CloseableModule.class)
                    .withModuleClass(Jsr250Module.class)
                    .build().createInjector();
        } catch (Exception e) {
            logger.error("fucking DI", e);
            throw e;
        }
    }

    private static void initializeConfiguration(AzureArgs arguments, DataSource dataSource) throws ConfigurationException {
        logger.info("Loading configuration from {}, environment and database", arguments.getConfigurationBootstrap());
        ConcurrentMapConfiguration propertiesConfig = new ConcurrentMapConfiguration((new PropertiesConfiguration(arguments.getConfigurationBootstrap())));
        ConcurrentMapConfiguration systemConfig = new ConcurrentMapConfiguration(new SystemConfiguration());

        PolledConfigurationSource polledConfigurationSource = new JDBCConfigurationSource(
                dataSource,
                "Select * from server_properties",
                "property_key",
                "property_value"
        );

        DynamicConfiguration dynamicConfig = new DynamicConfiguration(polledConfigurationSource,
                new FixedDelayPollingScheduler(100, 1000, true));

        ConcurrentCompositeConfiguration finalConfig = new ConcurrentCompositeConfiguration();

        finalConfig.addConfiguration(propertiesConfig, "propertiesConfig");
        finalConfig.addConfiguration(systemConfig, "systemConfig");
        finalConfig.addConfiguration(dynamicConfig, "dynamicConfig");

        ConfigurationManager.install(finalConfig);
    }

    private void initializeGameServer() {
        network.startServer();
    }

    public static SessionManager getSessionManager() {
        return sessionManager;
    }

    public Injector getInjector() {
        return injector;
    }

    public HikariDataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }
}
