package com.tech_challenge.notificacao_service.config;

import com.tech_challenge.notificacao_service.model.Consulta;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, Consulta> consultaConsumerFactory() {
        JsonDeserializer<Consulta> deserializer = new JsonDeserializer<>(Consulta.class);
        deserializer.addTrustedPackages("*");

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "notificacao-service");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
    }

    @Bean(name = "consultaFactory")
    public ConcurrentKafkaListenerContainerFactory<String, Consulta> consultaKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Consulta> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consultaConsumerFactory());
        return factory;
    }
}
