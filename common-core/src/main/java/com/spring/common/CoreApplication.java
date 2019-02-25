package com.spring.common;

import com.spring.common.config.RepositoryConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration(exclude = { AopAutoConfiguration.class })
@ComponentScan("com.spring.common")
@EnableLoadTimeWeaving
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
@Import(RepositoryConfiguration.class)
public class CoreApplication implements CommandLineRunner {

	@Override
	public void run(String... args) {
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(CoreApplication.class, args);
	}
}
