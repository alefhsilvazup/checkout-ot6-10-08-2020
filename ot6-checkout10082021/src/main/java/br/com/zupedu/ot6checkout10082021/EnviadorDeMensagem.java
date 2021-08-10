package br.com.zupedu.ot6checkout10082021;


import com.github.javafaker.CreditCardType;
import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;
import java.util.stream.Stream;

@Component
public class EnviadorDeMensagem {

    private final Logger logger;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final String topicCartoes;
    public EnviadorDeMensagem(KafkaTemplate<String, Object> kafkaTemplate,
                              @Value("${config.kafka.topic.cartoes}") String topicCartoes) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicCartoes = topicCartoes;
        this.logger = LoggerFactory.getLogger(EnviadorDeMensagem.class);
    }


    @EventListener(ApplicationStartedEvent.class)
    public void enviaMensagem() {
        //enviar mensagem pro kafka
        Faker faker = new Faker();


        Flux<Long> interval = Flux.interval(Duration.ofMillis(5_000));
        Flux<Cartao> cartoes = Flux.fromStream(Stream.generate(() -> {
            return new Cartao(faker.finance().creditCard(CreditCardType.MASTERCARD),
                    faker.name().fullName(),
                    CreditCardType.MASTERCARD.name(),
                    new Random().nextInt(999));
        }));

        Flux.zip(interval, cartoes)
                .map(tupla -> {
                    logger.info("chave {}  valor {}", tupla.getT1(), tupla.getT2());
                    return kafkaTemplate.send(topicCartoes, tupla.getT2().getBandeira(), tupla.getT2());
                }).blockLast();
    }
}
