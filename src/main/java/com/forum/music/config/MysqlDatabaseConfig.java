package com.forum.music.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories( basePackages = "com.forum.music.repository",
                        entityManagerFactoryRef = "mysqlEntityManager",
                        transactionManagerRef = "mysqlTrasactionManager"
)
@EnableJpaAuditing
@Slf4j
//@Profile("dsv, hml, prod, default")
public class MysqlDatabaseConfig {

    @Autowired
    private ForumMusicProperties properties;

    @Primary
    @Bean
    public DataSource mysqlDataSource() {
        ForumMusicProperties.DataBaseInfo mysql = properties.getDatabase().getMysql();
        return getHikariConfig(mysql.getJdbcDriver(),
                               mysql.getDatasourceUrl(),
                               mysql.getUser(),
                                mysql.getPassword(),
                                mysql.getTestSql(),
                                "mysqlDataSourcePool");
    }
    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean mysqlEntityManager (@Qualifier(value = "mysqlDataSource") DataSource msysqlDataSource){
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(msysqlDataSource);
        em.setPackagesToScan("com.forum.music.entity");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> jpaProperties = new HashMap<>();
        jpaProperties.put("hibernate.dialect", properties.getDatabase().getMysql().getDialect());
        em.setJpaPropertyMap(jpaProperties);
        em.setPersistenceUnitName("mysqlPersistenceUnit");
        return em;
    }
    @Primary
    @Bean
    public PlatformTransactionManager mysqlTrasactionManager(@Qualifier(value = "mysqlEntityManager") LocalContainerEntityManagerFactoryBean mysqlEntityManager){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(mysqlEntityManager.getObject());
        return  transactionManager;
    }


    private DataSource getHikariConfig(String driverClassName, String url, String username, String password, String testQuery, String pullName){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(driverClassName);
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setConnectionTestQuery(testQuery);
        hikariConfig.setPoolName(pullName);
        hikariConfig.setConnectionTimeout(20000);
        hikariConfig.setIdleTimeout(120000);
        hikariConfig.setMaxLifetime(60000);
        hikariConfig.setMaximumPoolSize(3);
        return new HikariDataSource(hikariConfig);
    }
}
