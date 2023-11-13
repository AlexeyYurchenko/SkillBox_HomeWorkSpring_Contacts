package org.example.config;

import org.example.DatasourceConfig;
import org.example.DevDatasource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application-dev.properties")
@Profile("dev")
public class DevConfig {

    @Bean
    public DatasourceConfig datasourceConfig(){
        return new DevDatasource();
    }

}
