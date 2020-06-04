package web.config;

import org.springframework.stereotype.Component;
import web.model.User;

@Component
public class HibernateConfigProperties {
    private org.hibernate.cfg.Configuration configuration;

    public HibernateConfigProperties() {
        this.configuration = setConfiguration();
    }

    public org.hibernate.cfg.Configuration getConfiguration() {
        return configuration;
    }

    public org.hibernate.cfg.Configuration setConfiguration() {
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/db_example?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "root");
        configuration.setProperty("hibernate.show_sql", "true");
        //configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        configuration.setProperty("hibernate.hbm2ddl.auto", "validate");
        return configuration;
    }
}
