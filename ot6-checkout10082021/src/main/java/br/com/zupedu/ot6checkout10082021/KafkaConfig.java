package br.com.zupedu.ot6checkout10082021;


import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Map;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

@Configuration
public class KafkaConfig {

    private final String kafkaServer, topicCartoes;
    private final Integer retry;
    private final Integer bufferMemory;

    public KafkaConfig(@Value("${config.kafka.server}") String kafkaServer,
                       @Value("${config.kafka.topic.cartoes}") String topicCartoes,
                       @Value("${config.kafka.retry}") Integer retry,
                       @Value("${config.kafka.bufferMemory}") Integer bufferMemory) {
        this.kafkaServer = kafkaServer;
        this.topicCartoes = topicCartoes;
        this.retry = retry;
        this.bufferMemory = bufferMemory;
    }


    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        return new DefaultKafkaProducerFactory(Map.of(
                KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
                VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class,
                BOOTSTRAP_SERVERS_CONFIG, kafkaServer,
                RETRIES_CONFIG, retry,
                BUFFER_MEMORY_CONFIG, bufferMemory //20mb
        ));
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate(ProducerFactory<String, Object> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public KafkaAdmin admin() {
        return new KafkaAdmin(Map.of(
                AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer
        ));
    }

    @Bean
    public NewTopic cartoes() {
        return TopicBuilder.name(topicCartoes)
                .partitions(5)
                .compact()
                .build();
    }
}
