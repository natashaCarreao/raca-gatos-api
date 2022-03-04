package br.com.raca.gatos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@SpringBootApplication
@ComponentScan
@EnableScheduling
public class RacaGatosApplication {

	public static void main(String[] args) {

		SpringApplication.run(RacaGatosApplication.class, args);

	}

}
