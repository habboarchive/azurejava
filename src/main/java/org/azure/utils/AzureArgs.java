package org.azure.utils;

import com.lexicalscope.jewel.cli.Option;

/**
 * Created by scott on 11/25/2015.
 */
public interface AzureArgs {
    @Option(description = "system configuration bootstrap file", defaultValue = "azure.properties")
    String getConfigurationBootstrap();

    @Option(description = "MySQL server host", defaultValue = "localhost")
    String getDatabaseHost();

    @Option(description = "MySQL server port", defaultValue = "3306")
    int getDatabasePort();

    @Option(description = "MySQL server user", defaultValue = "root")
    String getDatabaseUser();

    @Option(description = "MySQL server password", defaultValue = "\\")
    String getDatabasePassword();

    @Option(description = "MySQL server database name", defaultValue = "azurejava")
    String getDatabase();
}
