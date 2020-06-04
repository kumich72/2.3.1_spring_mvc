package web.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateConfig {

    private HibernateConfigProperties hibernateConfigProperties;

    public HibernateConfigProperties hibernateConfig() {
        return new HibernateConfigProperties();
    }
}
