package br.com.zupedu.ot6consumidorcheckout10082021;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumidorDeMensagem {

    private final Logger logger;

    public ConsumidorDeMensagem() {
        this.logger = LoggerFactory.getLogger(ConsumidorDeMensagem.class);
    }

    @KafkaListener(topics = "${config.kafka.topic.cartoes}", groupId = "nosso-primeiro-consumidor")
    public void processaMensagem(String cartao) {
        logger.info("consumindo mensagem {}", cartao);
    }

}
