package com.inti.formation.stock.topic.consumer.configuration;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.inti.formation.stock.topic.consumer.repository")
public class ElasticsearchConfig {

    @Value("${elasticsearch.host}")
    private String host;

    @Value("${elasticsearch.port}")
    private Integer port;

    @Value("${elasticsearch.cluster-name}")
    private String clustername;

    @Bean
    public Client client() throws Exception {
        Settings settings = Settings.builder()
                .put("cluster.name", clustername)
                .build();
        return new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName(host)
                        ,9200));
    }

    @Bean
    public ElasticsearchOperations elasticsearchThemplate() throws Exception {
        return new ElasticsearchTemplate(client());
    }

}
