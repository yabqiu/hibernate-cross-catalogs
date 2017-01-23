package config;

import annotation.Segregated;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.annotation.AnnotationDescription;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import util.Client;
import util.CustomResourceLoader;
import util.SegregatedClassLoader;
import util.Utils;

import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Table;
import javax.sql.DataSource;
import java.util.EnumSet;
import java.util.Properties;
import java.util.Set;

@ComponentScan(basePackages = {"repository", "service"})
//@EnableJpaRepositories(basePackages = {"repository"})
public class AppConfig {

    private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, @Named("hibernateProperties") Properties hibernateProperties) {

        enhanceEntities();

        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setGenerateDdl(false);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setBeanClassLoader(new SegregatedClassLoader());
//        factory.setResourceLoader(new DefaultResourceLoader(new SegregatedClassLoader()));
        factory.setPersistenceUnitPostProcessors(pui ->
            pui.addManagedClassName("entity.Account$$$mart")
        );
        factory.setResourceLoader(new CustomResourceLoader());
        factory.setJpaVendorAdapter(adapter);
        factory.setDataSource(dataSource);
        factory.setJpaProperties(hibernateProperties);
        factory.setPackagesToScan("entity");
        factory.afterPropertiesSet();

        return factory;
    }

    private void enhanceEntities() {
        logger.info("Scan all class annotated by @Segregated");
        Set<Class<?>> segregatedEntities = new Reflections("entity").getTypesAnnotatedWith(Segregated.class, true);
        segregatedEntities.forEach((Class<?> aClass) -> {
            logger.info(aClass.toString());
            String tableName = aClass.getAnnotation(Segregated.class).tableName();
            EnumSet.allOf(Client.class).forEach(client -> {
                String newClassName = Utils.makeClassName(aClass, client);
                String newTableName = client.name().concat(".").concat(tableName);
                SegregatedClassLoader.storeClass(newClassName,
                    new ByteBuddy().subclass(aClass)
                        .name(newClassName)
                        .annotateType(AnnotationDescription.Builder.ofType(Table.class).define("name", newTableName).build())
                        .annotateType(AnnotationDescription.Builder.ofType(Entity.class).build())
                        .make().getBytes());
            });
        });
    }

    @Bean
    public Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        hibernateProperties.put("hibernate.show_sql", true);
//        hibernateProperties.put("hibernate.default_schema", "mart");
        hibernateProperties.put("hibernate.format_sql", true);
        hibernateProperties.put("hibernate.generate_statistics", false);
        hibernateProperties.put("hibernate.use_sql_comments", false);
        hibernateProperties.put("hibernate.generate_statistics", true);

        return hibernateProperties;
    }

    @Bean
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory){
        return entityManagerFactory.createEntityManager();
    }

    @Bean
    public DataSource dataSource() {
//        enhanceEntities();

        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
            .addDefaultScripts().build();
    }
}
