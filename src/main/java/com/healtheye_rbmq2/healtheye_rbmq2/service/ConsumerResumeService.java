package com.healtheye_rbmq2.healtheye_rbmq2.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ConsumerResumeService {
   private static final String DIRECTORY_PATH = "messages/"; // Carpeta donde se guardarán los archivos
  private final ObjectMapper objectMapper = new ObjectMapper(); // Para manejar JSON
  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

  @RabbitListener(queues = "summaryQueue")
  public void receiveMessage(String message) {
    System.out.println("Mensaje recibido: " + message);
    saveMessageToFile(message);
  }

  private void saveMessageToFile(String message) {
    try {
      File directory = new File(DIRECTORY_PATH);
      if (!directory.exists()) {
        directory.mkdirs();
      }
      JsonNode jsonNode = objectMapper.readTree(message);
      String timestamp = LocalDateTime.now().format(formatter);
      String fileName = DIRECTORY_PATH + "summary_" + timestamp + ".json";
      objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), jsonNode);
      System.out.println("Mensaje guardado en: " + fileName);
    } catch (IOException e) {
      System.err.println("Error guardando el mensaje en JSON: " + e.getMessage());
    }
  }
}
