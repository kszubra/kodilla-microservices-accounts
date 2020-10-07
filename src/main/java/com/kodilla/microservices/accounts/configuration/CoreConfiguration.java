package com.kodilla.microservices.accounts.configuration;

import com.kodilla.microservices.accounts.service.SimpleAccountNumberGenerator;
import com.kodilla.microservices.accounts.service.interfaces.AccountNumberGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.kodilla.microservices.accounts.domain.repository")
@EntityScan(basePackages = {"com.kodilla.microservices.accounts.domain.model"})
@ComponentScan(basePackages = {"com.kodilla.microservices.accounts.service.*"})
public class CoreConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public AccountNumberGenerator accountNumberGenerator() {
        return new SimpleAccountNumberGenerator();
    }
}
