package com.spring.common.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@Profile({"dev", "stage", "production"})
@EnableJpaRepositories(basePackages = "com.spring.common.repository")
@EnableJpaAuditing
public class RepositoryConfiguration {
	@Value("${dataSource.spring.driverClassName}")
	private String driverClassName;

	@Value("${dataSource.spring.url}")
	private String url;

	@Value("${dataSource.spring.username}")
	private String username;

	@Value("${dataSource.spring.password}")
	private String password;

	@Value("${dataSource.spring.initialSize}")
	private Integer initialSize;

	@Value("${dataSource.spring.minIdle}")
	private Integer minIdle;

	@Value("${dataSource.spring.maxActive}")
	private Integer maxActive;

	@Bean
	public DataSource dataSource() {
//		HikariDataSource dataSource = new HikariDataSource();
//		dataSource.setDriverClassName(driverClassName);
//		dataSource.setJdbcUrl(url);
//		dataSource.setUsername(username);
//		dataSource.setPassword(password);
//		dataSource.setMinimumIdle(minIdle);
//		dataSource.setMaximumPoolSize(maxActive);
//		return dataSource;

		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder
				.setType(EmbeddedDatabaseType.H2)
				.build();
		return db;
	}

	private Properties hibernateProperties() {
		return new Properties() {
			{
				setProperty("hibernate.hbm2ddl.auto", "update");
				setProperty("hibernate.show_sql", "true");
				setProperty("hibernate.format_sql", "true");
				setProperty("hibernate.use_sql_comments", "true");
				setProperty("hibernate.id.new_generator_mapping", "true");
				setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
				setProperty("hibernate.physical_naming_strategy", "com.spring.common.config.DefaultPhysicalNamingStrategy");

			}
		};
	}

	@Bean
	public EntityManagerFactory entityManagerFactory() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.spring.common.domain");
		factory.setDataSource(dataSource());
		factory.setJpaProperties(hibernateProperties());
		factory.afterPropertiesSet();
		return factory.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory());
		return txManager;
	}
}
