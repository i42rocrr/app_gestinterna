package com.gestinterna.app.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/*
Esto se explica en los vídeos:
   https://www.google.com/search?q=application.properties+conexi%C3%B3n+a+dos+bases+de+datos&oq=application.properties+conexi%C3%B3n+a+dos+bases+de+datos&gs_lcrp=EgZjaHJvbWUyBggAEEUYOdIBCTEyMDMwajBqMagCALACAA&sourceid=chrome&ie=UTF-8#fpstate=ive&vld=cid:146b8898,vid:iE_o8viSbkc,st:0
   https://www.youtube.com/watch?v=_Qpvn1s_afg
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "MySQL_EntityManagerFactory",
        transactionManagerRef = "MySQL_TransactionManager",
        basePackages = {"com.gestinterna.app.repository.mysql"} //El path donde se encuentra el repositorio
)
public class MySQLDB {
    @Autowired
    private Environment environment;

    @Bean(name = "MySQL_DataSource")
    public DataSource DataSource_Usuario() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(environment.getProperty("mysql.datasource.url"));
        dataSource.setUsername(environment.getProperty("mysql.datasource.username"));
        dataSource.setPassword(environment.getProperty("mysql.datasource.password"));
        dataSource.setDriverClassName(environment.getProperty("mysql.datasource.driver-class-name"));

        return dataSource;
    }

    @Bean(name = "MySQL_EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(DataSource_Usuario());
        em.setPackagesToScan("com.gestinterna.app.model.mysql");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", environment.getProperty("mysql.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.show-sql", environment.getProperty("mysql.jpa.show-sql"));
        properties.put("hibernate.dialect", environment.getProperty("mysql.jpa.database-platform"));

        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean(name = "MySQL_TransactionManager")
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());

        return transactionManager;
    }

}
