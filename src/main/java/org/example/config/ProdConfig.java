package org.example.config;

import org.example.DatasourceConfig;
import org.example.ProdDatasource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application-prod.properties")
@Profile("prod")
public class ProdConfig {

    @Bean
    public DatasourceConfig datasourceConfig(){
        return new ProdDatasource();
    }
}
