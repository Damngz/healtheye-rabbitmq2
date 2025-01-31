package com.healtheye_rbmq2.healtheye_rbmq2.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
  @Bean
  public Queue summaryQueue() {
    return new Queue("summaryQueue", true);
  }
}
