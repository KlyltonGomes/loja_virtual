package com.BirdSoftware.Loja_Virtual;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAsync
@EntityScan(basePackages = "com.BirdSoftware.Loja_Virtual.model")
@ComponentScan(basePackages = "com.BirdSoftware.Loja_Virtual")
@EnableJpaRepositories(basePackages = {"com.BirdSoftware.Loja_Virtual.repository"})
@EnableTransactionManagement
public class LojaVirtualApplication {

	public static void main(String[] args) {
		SpringApplication.run(LojaVirtualApplication.class, args);
		System.out.println("Lets-Go");
	}

}
