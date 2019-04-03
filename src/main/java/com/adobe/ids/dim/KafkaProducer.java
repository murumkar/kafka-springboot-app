package com.adobe.ids.dim;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.beans.factory.annotation.Value;

@Configuration

public class KafkaProducer {


    @Value(value = "${kafka.bootstrap-servers}")
    private String bootstrapServers;
    @Value(value = "${kafka.topic-name}")
    private String topicName;
    @Value(value = "${kafka.producer.acks}")
    private short acks;
    @Value(value = "${kafka.producer.batch-size}")
    private short batchSize;
    @Value(value = "${kafka.producer.retries}")
    private short retries;
    @Value(value = "${kafka.producer.linger-ms}")
    private short lingerms;
    @Value(value = "${kafka.producer.buffer-memory}")
    private short bufferMemory;
    @Value(value = "${kafka.key-serializer}")
    private short keySerializer;
    @Value(value = "${kafka.value-serializer}")
    private short valueSerializer;

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.ACKS_CONFIG, acks);
        configProps.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
        configProps.put(ProducerConfig.RETRIES_CONFIG, retries);
        configProps.put(ProducerConfig.LINGER_MS_CONFIG, lingerms);
        configProps.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
        return new DefaultKafkaProducerFactory<>(configProps);
    }
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String msg) {
        kafkaTemplate.send(topicName, msg);
    }
}