package org.photothinik.finance.expense.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;

@Configuration
public class DatasourceConfig {

    @Bean
    public DataSource dataSource() {

        // Get app config
        ApplicationConfiguration config;
        try {
            File configSrcFile = new File("config/expense-analyzer.config");
            config = new ApplicationConfiguration(configSrcFile);
            config.loadFromSource();
        } catch(IOException e) {
            throw new RuntimeException("Unable to load application configuration: " + e.getMessage(), e);
        }

        return DataSourceBuilder.create()
                .driverClassName(config.getProperty(ApplicationProperty.REPOSITORY_DATASOURCE_DRIVERCLASS))
                .url(config.getProperty(ApplicationProperty.REPOSITORY_DATASOURCE_URL))
                .username(config.getProperty(ApplicationProperty.REPOSITORY_DATASOURCE_USERNAME))
                .password(config.getProperty(ApplicationProperty.REPOSITORY_DATASOURCE_PASSWORD))
                .build();
    }


}
