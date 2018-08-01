package com.github.wsmt.reporter.config;

import com.github.wsmt.reporter.config.properties.HBaseProperties;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(HBaseProperties.class)
public class HBaseConfig {
    @Bean
    public org.apache.hadoop.conf.Configuration hBaseConfiguration(HBaseProperties hBaseProperties) {
        org.apache.hadoop.conf.Configuration configuration = HBaseConfiguration.create();
        hBaseProperties.getHBase().ifPresent(hBase -> hBase.forEach(configuration::set));
        return configuration;
    }
}
