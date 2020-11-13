package br.com.kafka.noobs.controller;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;


@Service
public class KafkaController implements Serializable {

	private static final long serialVersionUID = -8899008623795014736L;

	@Autowired
	br.com.kafka.noobs.service.ProducerService producerService;

	@Autowired
	br.com.kafka.noobs.service.ConsumerService consumerService;

	@Bean
	public void testKafka() throws InterruptedException, ExecutionException {

		producerService.producerKafka();

		consumerService.consumerKafka();
	}
}
