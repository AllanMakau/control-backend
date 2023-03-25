package br.com.mksoftware.control;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class ControlApplication  {
	

	public static void main(String[] args) {
		SpringApplication.run(ControlApplication.class, args);

	}



}
