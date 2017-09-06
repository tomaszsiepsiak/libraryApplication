package com.klb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource(value = {"classpath:hibernate.properties"})
@EnableJpaRepositories(basePackages = "com.klb.dao")
public class HibernateConfig {

    @Autowired
    private Environment environment;  //obiekt zwiazany z plikiem hibernate.properties

    // 1. Utworzenie DataSource

    //obiekt konfiguracji dostepu do bazy danych
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driver.class.name"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.user.name"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));

        return dataSource;
    }


    //obiekt fabryki beanow encyjnych
    @Bean
    public EntityManagerFactory entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getProperty("hibernate.format_sql"));
        properties.put("hibernate.generate_statistics", environment.getProperty("hibernate.generate_statistics"));

        //glowny obiekt fabryki beanow
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPackagesToScan("com.klb.entity");
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.setJpaProperties(properties);
        factoryBean.setDataSource(dataSource());  //konfiguracja dost do bazy
        factoryBean.afterPropertiesSet();  //produkcja beanow encyjnych bedzie mozliwa
        //dopiero gdy wszystkie ustawienia zostana wykonane

        return factoryBean.getObject();  //wzorzec - budowniczy ?
    }

    //obiekt do obslugi transakcji
    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory());
        return transactionManager;
    }


}
