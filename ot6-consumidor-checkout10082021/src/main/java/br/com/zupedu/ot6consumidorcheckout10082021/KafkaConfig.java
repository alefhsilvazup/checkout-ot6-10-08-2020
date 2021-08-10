package br.com.zupedu.ot6consumidorcheckout10082021;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Map;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

public class KafkaConfig {


    private final String kafkaServer;
    private final String consumerId;

    public KafkaConfig(@Value("${config.kafka.server}") String kafkaServer,
                       @Value("${config.kafka.consumerId}")String consumerId) {
        this.kafkaServer = kafkaServer;
        this.consumerId = consumerId;
    }

    @Bean
    public ConsumerFactory<String, Object> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(Map.of(
                BOOTSTRAP_SERVERS_CONFIG, kafkaServer,
                GROUP_ID_CONFIG, consumerId,
                ENABLE_AUTO_COMMIT_CONFIG, true,
                SESSION_TIMEOUT_MS_CONFIG, 10000,
                KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class
        ));
    }

    @Bean
    JsonMessageConverter jsonMessageConverter () {
        return new JsonMessageConverter();
    }

//    @Bean
//    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Object>>
//    kafkaListenerContainerFactory(ConsumerFactory consumerFactory) {
//        ConcurrentKafkaListenerContainerFactory<String, Object> factory =
//                new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory);
//        return factory;
//    }


}
