package ar.com.jf.antilavado.repository.config;

import ar.com.jf.antilavado.repository.contant.Databases;
import com.google.common.base.Strings;
import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;
/**
 * DatabaseConfig.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 03/07/2015.
 */
@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(DatabaseConfig.class);

    @Value("${db.selected}")
    private String DB_SELECTED;

    @Value("${db.oracle.driver}")
    private String DB_ORACLE_DRIVER;
    @Value("${db.sqlserver.driver}")
    private String DB_SQLSERVER_DRIVER;
    @Value("${db.mysql.driver}")
    private String DB_MYSQL_DRIVER;

    @Value("${db.oracle.password}")
    private String DB_ORACLE_PASSWORD;
    @Value("${db.sqlserver.password}")
    private String DB_SQLSERVER_PASSWORD;
    @Value("${db.mysql.password}")
    private String DB_MYSQL_PASSWORD;

    @Value("${db.oracle.url}")
    private String DB_ORACLE_URL;
    @Value("${db.sqlserver.url}")
    private String DB_SQLSERVER_URL;
    @Value("${db.mysql.url}")
    private String DB_MYSQL_URL;

    @Value("${db.oracle.username}")
    private String DB_ORACLE_USERNAME;
    @Value("${db.sqlserver.username}")
    private String DB_SQLSERVER_USERNAME;
    @Value("${db.mysql.username}")
    private String DB_MYSQL_USERNAME;

    @Value("${db.initialSize}")
    private int DB_INITIAL_SIZE;

    @Value("${db.maxActive}")
    private int DB_MAX_ACTIVE;

    @Value("${hibernate.oracle.dialect}")
    private String HIBERNATE_ORACLE_DIALECT;
    @Value("${hibernate.sqlserver.dialect}")
    private String HIBERNATE_SQLSERVER_DIALECT;
    @Value("${hibernate.mysql.dialect}")
    private String HIBERNATE_MYSQL_DIALECT;

    @Value("${hibernate.show_sql}")
    private String HIBERNATE_SHOW_SQL;

    @Value("${hibernate.hbm2ddl.auto}")
    private String HIBERNATE_HBM2DDL_AUTO;

    @Value("${entitymanager.packagesToScan}")
    private String ENTITYMANAGER_PACKAGES_TO_SCAN;

    @Bean
    public DataSource dataSource() throws Exception {
        BasicDataSource dataSource = getDatasource();
        dataSource.setMaxActive(DB_MAX_ACTIVE);
        dataSource.setMaxIdle(5);
        dataSource.setMinIdle(0);
        dataSource.setMaxWait(5000);
        dataSource.setInitialSize(DB_INITIAL_SIZE);
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxOpenPreparedStatements(100);
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(false);
        dataSource.setTestWhileIdle(true);
        dataSource.setValidationQuery("SELECT 1+1");
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() throws Exception {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN.split(","));
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", getDialecSelected());
        hibernateProperties.put("hibernate.show_sql", HIBERNATE_SHOW_SQL);
        hibernateProperties.put("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);
        hibernateProperties.put("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
        sessionFactoryBean.setHibernateProperties(hibernateProperties);
        return sessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager transactionManager() throws Exception {
        HibernateTransactionManager transactionManager =
                new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    private BasicDataSource getDatasource() throws Exception {
        if (Strings.isNullOrEmpty(DB_SELECTED)) {
            LOGGER.error("No se completo la base a utilizar. Revisar: \"db.properties\" y volver a intentar.");
            throw new Exception("No se completo la base a utilizar. Revisar: \"db.properties\" y volver a intentar.");
        }

        BasicDataSource dataSource = new BasicDataSource();

        if (DB_SELECTED.equals(Databases.mysql.name())) {
            dataSource.setDriverClassName(DB_MYSQL_DRIVER);
            dataSource.setUrl(DB_MYSQL_URL);
            dataSource.setUsername(DB_MYSQL_USERNAME);
            dataSource.setPassword(DB_MYSQL_PASSWORD);
        } else if (DB_SELECTED.equals(Databases.oracle.name())) {
            dataSource.setDriverClassName(DB_ORACLE_DRIVER);
            dataSource.setUrl(DB_ORACLE_URL);
            dataSource.setUsername(DB_ORACLE_USERNAME);
            dataSource.setPassword(DB_ORACLE_PASSWORD);
        } else if (DB_SELECTED.equals(Databases.sqlserver.name())) {
            dataSource.setDriverClassName(DB_SQLSERVER_DRIVER);
            dataSource.setUrl(DB_SQLSERVER_URL);
            dataSource.setUsername(DB_SQLSERVER_USERNAME);
            dataSource.setPassword(DB_SQLSERVER_PASSWORD);
        }

        return dataSource;
    }

    private String getDialecSelected() throws Exception {
        if (Strings.isNullOrEmpty(DB_SELECTED)) {
            LOGGER.error("No se completo la base a utilizar. Revisar: \"db.properties\" y volver a intentar.");
            throw new Exception("No se completo la base a utilizar. Revisar: \"db.properties\" y volver a intentar.");
        }

        String dialec = "";

        if (DB_SELECTED.equals(Databases.mysql.name())) {
            dialec = HIBERNATE_MYSQL_DIALECT;
        } else if (DB_SELECTED.equals(Databases.oracle.name())) {
            dialec = HIBERNATE_ORACLE_DIALECT;
        } else if (DB_SELECTED.equals(Databases.sqlserver.name())) {
            dialec = HIBERNATE_SQLSERVER_DIALECT;
        }

        if (Strings.isNullOrEmpty(dialec)) {
            LOGGER.error("Error al obtener el dialecto de la BD a utilizar. Revisar: \"db.properties\" y volver a intentar.");
            throw new Exception("Error al obtener el dialecto de la BD a utilizar. Revisar: \"db.properties\" y volver a intentar.");
        }
        return dialec;
    }


}
