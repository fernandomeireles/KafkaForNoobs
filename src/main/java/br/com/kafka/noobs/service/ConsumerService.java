package br.com.kafka.noobs.service;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@Component
public class ConsumerService implements Serializable {

	private static final long serialVersionUID = -3966779656571119674L;

	public void consumerKafka() {

		var consumer = new KafkaConsumer<String, String>(properties());
		consumer.subscribe(Collections.singletonList("ECOMMERCE_NEW_ORDER"));

		while (true) {
			var records = consumer.poll(Duration.ofMillis(100));

			if (!records.isEmpty()) {
				System.out.println("Encontrei registros " + records.count());

				for (var record : records) {
					System.out.println(" ------------------------");
					System.out.println("Processing new order");
					System.out.println(record.key());
					System.out.println(record.value());
					System.out.println(record.partition());
					System.out.println(record.offset());

					System.out.println("Order processed");

				}
			}

		}
	}

	public Properties properties() {
		var properties = new Properties();
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "CONSUMER_SERVICE");
		properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1");

		return properties;

	}

}
