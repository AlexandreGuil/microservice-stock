package com.inti.formation.stock.api.configuration;

import com.inti.formation.stock.api.model.Stock;
import com.inti.formation.stock.api.seriallizer.JsonPojoSeriallizer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class configurationProducer {

    @Value("${kafka.bootstrap-server}")
    private String kafkaBrokerUrl;

    @Value("${kafka.acks}")
    private String acks;

    @Value("${kafka.retries}")
    private String retries;

    @Value("${kafka.buffer-memory}")
    private String bufferMemory;

    @Value("${kafka.batch-size}")
    private String batchSize;

    @Value("${kafka.client-id}")
    private String clientId;

    @Value("${kafka.compression-type}")
    private String compressionType;

    @Value("${kafka.user}")
    private String user;

    @Value("${kafka.password}")
    private String password;

    private static final String SECURITY_PROTOCOL = "security.protocol";
    private static final String SASL_MECHANISM = "sasl.mechanism";
    private static final String SASL_JAAS_CONFIG = "sasl.jaas.config";

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> conf = new HashMap<>();
        conf.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBrokerUrl);
        conf.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        conf.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonPojoSeriallizer.class.getName());
        conf.put(ProducerConfig.RETRIES_CONFIG, retries);
        conf.put(ProducerConfig.ACKS_CONFIG, acks);
        conf.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
        conf.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);
        conf.put(ProducerConfig.CLIENT_ID_CONFIG, clientId);
        conf.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, compressionType);
        conf.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, 1);
        conf.put(StreamsConfig.EXACTLY_ONCE, true);
        return conf;
    }

    @Bean
    public ProducerFactory<String, Stock> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, Stock> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
