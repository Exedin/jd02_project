package it.academy;


import com.mysql.cj.jdbc.Driver;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import it.academy.model.Department;
import it.academy.model.Employee;
import it.academy.model.EmployeeFullName;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@PropertySource(value = "classpath:datasource.properties")
@Configuration
@ComponentScan(basePackages = "it.academy")
@EnableTransactionManagement
public class DaoTestConfiguration {

    @Autowired
    Environment env;

    @Bean(name = "testDataSource")
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(env.getProperty("datasource.url"));
        hikariConfig.setDriverClassName(Driver.class.getName());
        hikariConfig.setUsername(env.getProperty("datasource.username"));
        hikariConfig.setPassword(env.getProperty("datasource.password"));
        hikariConfig.setMaximumPoolSize(100);
        return new HikariDataSource(hikariConfig);
    }

    @Bean(name = "testSessionFactory")
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactoryBean
                = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setAnnotatedClasses(
                Department.class, Employee.class, EmployeeFullName.class
        );
        Properties properties = new Properties();
        properties.setProperty("hibernate.show_sql", "false");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");

        sessionFactoryBean.setHibernateProperties(properties);
        return sessionFactoryBean;
    }
    @Bean
    public PlatformTransactionManager transactionManager(
            SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
}
