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
        entityManagerFactoryRef = "PostgreSQL_EntityManagerFactory",
        transactionManagerRef = "PostgreSQL_TransactionManager",
        basePackages = {"com.gestinterna.app.repository.postgresql"} //El path donde se encuentra el repositorio
)
public class PostgreSQLDB {
    @Autowired
    private Environment environment;

    @Bean(name = "PostgreSQL_DataSource")
    public DataSource DataSource_Usuario() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(environment.getProperty("postgres.datasource.url"));
        dataSource.setUsername(environment.getProperty("postgres.datasource.username"));
        dataSource.setPassword(environment.getProperty("postgres.datasource.password"));
        dataSource.setDriverClassName(environment.getProperty("postgres.datasource.driver-class-name"));

        return dataSource;
    }

    @Bean(name = "PostgreSQL_EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(DataSource_Usuario());
        em.setPackagesToScan("com.gestinterna.app.model.postgresql");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", environment.getProperty("postgres.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.show-sql", environment.getProperty("postgres.jpa.show-sql"));
        properties.put("hibernate.dialect", environment.getProperty("postgres.jpa.database-platform"));

        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean(name = "PostgreSQL_TransactionManager")
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());

        return transactionManager;
    }
}
