package br.com.kafka.noobs;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.kafka.noobs.controller.KafkaController;

@SpringBootApplication
public class EcommerceApplication {

	@Autowired
	private static KafkaController KafkaController;

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		SpringApplication.run(EcommerceApplication.class, args);

		KafkaController.testKafka();

	}

}
