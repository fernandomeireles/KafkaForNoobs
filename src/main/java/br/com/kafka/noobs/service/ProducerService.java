package br.com.kafka.noobs.service;

import java.io.Serializable;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;


@Component
public class ProducerService implements Serializable {

	private static final long serialVersionUID = 880578245436306222L;

	
	public void producerKafka() throws InterruptedException, ExecutionException {
		var producer = new KafkaProducer<String, String>(properties());
		var value = "1232131,0808,B123123";
		var record = new ProducerRecord<>("ECOMMERCE_NEW_ORDER", value, value);
		producer.send(record, (data, ex) -> {
			if (ex != null) {
				ex.printStackTrace();
				return;
			}
			System.out.println(
					" sucess " + data.topic() + ":::partion " + data.partition() + ":::offset " + data.offset());
		}).get();

	}

	private Properties properties() {
		var properties = new Properties();
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

		return properties;

	}

}
