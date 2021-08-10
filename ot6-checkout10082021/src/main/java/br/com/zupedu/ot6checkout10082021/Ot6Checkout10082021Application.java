package br.com.zupedu.ot6checkout10082021;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Ot6Checkout10082021Application {

	public static void main(String[] args) {
		SpringApplication.run(Ot6Checkout10082021Application.class, args);
	}


//	@Bean
//	public CommandLineRunner commandLineRunner() {
//		return args -> {
//
//			//codigo necessario para enviar a mensagem pro kafka
//			return;
//		};
//
//	}
//
//	@Bean
//	public ApplicationRunner commandLineRunner() {
//		return args -> {
//
//			//codigo necessario para enviar a mensagem pro kafka
//			return;
//		};
//
//	}
}
