package com.kafka.provider.kafkaProvider.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {


    @Bean
    public NewTopic generateTopic(){

        Map<String,String> configurations=new HashMap<>();
        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG,TopicConfig.CLEANUP_POLICY_DELETE);// DELETE ES PARA ELIMINAR TODOS LOS MENSAJES - COMPACT TAMBIEN ELIMINA PERO MANTIENE EL ULTIMO
        configurations.put(TopicConfig.RETENTION_MS_CONFIG,"86400000");// 86400000 vale un día - por defecto es -1 (osea no hay limite)
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG,"1073741824");// 1073741824 vale 1gb- esto es el tamaño del segmento
        configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG,"1000012");// 1000012 puse un numero cualquiera- por defecto es 1mb

        return TopicBuilder.name("henry-topic")
                .partitions(2)//ten cuidado con esto
                .replicas(2)//ten cuidado con esto  - se tiene que revisar porque no ejecuta
                .configs(configurations)
                .build();
    }
}
