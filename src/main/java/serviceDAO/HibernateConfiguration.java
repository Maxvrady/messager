package serviceDAO;

import models.Message;
import models.Profile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "serviceDAO")
public class HibernateConfiguration {

    @Bean
    public LocalSessionFactoryBean localSessionFactoryBean() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
//      Set property
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
        properties.put("hibernate.hbm2ddl.auto", "update");
//      Set DataSource
        factoryBean.setDataSource(dataSource());
        factoryBean.setHibernateProperties(properties);
//      Set models
        factoryBean.setAnnotatedClasses(Profile.class, Message.class);
        return factoryBean;
    }

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/maxouni_db");
        dataSource.setUsername("maxouni");
        dataSource.setPassword("009009salli");
        return dataSource;
    }
}
