package com.chuistov.mvc.config;

import com.chuistov.mvc.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScan(value = "com.chuistov.mvc")
public class AppConfig {

   @Autowired
   private Environment env;

   @Bean
   public DataSource getDataSource() {
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("db.driver")));
      dataSource.setUrl(env.getProperty("db.url"));
      dataSource.setUsername(env.getProperty("db.username"));
      dataSource.setPassword(env.getProperty("db.password"));
      return dataSource;
   }

   @Bean
   public LocalContainerEntityManagerFactoryBean getEntityManagerFactory() {
      LocalContainerEntityManagerFactoryBean entityManagerFactory =
              new LocalContainerEntityManagerFactoryBean();
      entityManagerFactory.setDataSource(getDataSource());
      entityManagerFactory.setPackagesToScan("com.chuistov.mvc.entities");

      Properties props = new Properties();
      props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
      props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
      props.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
      entityManagerFactory.setJpaProperties(props);

      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
      entityManagerFactory.setJpaVendorAdapter(vendorAdapter);

      return entityManagerFactory;
   }

   @Bean
   public JpaTransactionManager getJpaTransactionManager() {
      JpaTransactionManager transactionManager = new JpaTransactionManager();
      transactionManager.setEntityManagerFactory(
              getEntityManagerFactory().getObject());
      return transactionManager;
   }

   @Bean
   public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
      return new PersistenceExceptionTranslationPostProcessor();
   }
}
