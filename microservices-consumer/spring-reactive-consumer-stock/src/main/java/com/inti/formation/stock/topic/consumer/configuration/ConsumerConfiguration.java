package com.inti.formation.stock.topic.consumer.configuration;

import com.inti.formation.stock.topic.consumer.data.Stock;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class ConsumerConfiguration {

    @Value("${kafka.boot-server}")
    private String bootServer;

    @Value("${kafka.consumer-group-id}")
    private String consumerGroupId;

    @Value("${kafka.client-id}")
    private String clientId;

    @Value("${kafka.auto-ofset-rest}")
    private String autoOfsetRest;

    @Value("${kafka.enable-auto-commit}")
    private String enableAutoCommit;

    @Value("${kafka.auto-commit-interval-ms}")
    private String autoCommitIntervalMs;

    @Value("${kafka.heartbeat-interval-ms}")
    private String heartbeatIntervalMs;

    @Bean
    public ConsumerFactory<String, Stock> consumerConfig() {
        Map<String, Object> configuration = new HashMap<>();
        configuration.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootServer);
        configuration.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroupId);
        configuration.put(ConsumerConfig.CLIENT_ID_CONFIG, clientId);
        configuration.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOfsetRest);
        configuration.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enableAutoCommit);
        configuration.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, autoCommitIntervalMs);
        configuration.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, heartbeatIntervalMs);
        configuration.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configuration.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(configuration, null, new JsonDeserializer<>(Stock.class));
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Stock>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Stock> listener = new ConcurrentKafkaListenerContainerFactory<>();
        listener.setConsumerFactory(consumerConfig());
        return listener;
    }

}
