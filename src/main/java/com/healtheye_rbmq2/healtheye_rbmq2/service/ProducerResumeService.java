package com.healtheye_rbmq2.healtheye_rbmq2.service;

import java.util.List;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProducerResumeService {
  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Autowired
  private ObjectMapper objectMapper;

  public void sendMessage(List<Map<String, Object>> data) {
    try {
      String jsonMessage = objectMapper.writeValueAsString(data);
      System.out.println("Sending alert: " + jsonMessage);
      rabbitTemplate.convertAndSend("summaryQueue", jsonMessage);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }
}
