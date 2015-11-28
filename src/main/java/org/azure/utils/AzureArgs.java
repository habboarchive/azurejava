package org.azure.utils;

import com.lexicalscope.jewel.cli.Option;

/**
 * AzureJava,
 * Edited: 11/25/2015 (Scott Stamp).
 */
public interface AzureArgs {
    @Option(description = "system configuration bootstrap file", defaultValue = "azure.properties")
    String getConfigurationBootstrap();

    @Option(description = "PostgreSQL server host", defaultValue = "localhost")
    String getDatabaseHost();

    @Option(description = "PostgreSQL server port", defaultValue = "3306")
    int getDatabasePort();

    @Option(description = "PostgreSQL server user", defaultValue = "azure")
    String getDatabaseUser();

    @Option(description = "PostgreSQL server password", defaultValue = "passw0rd")
    String getDatabasePassword();

    @Option(description = "PostgreSQL server database name", defaultValue = "azure")
    String getDatabase();
}
