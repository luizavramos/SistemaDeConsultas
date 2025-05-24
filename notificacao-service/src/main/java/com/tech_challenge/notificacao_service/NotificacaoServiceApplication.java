package com.tech_challenge.notificacao_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
		org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class,
		org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration.class
})
public class NotificacaoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificacaoServiceApplication.class, args);
	}

}
