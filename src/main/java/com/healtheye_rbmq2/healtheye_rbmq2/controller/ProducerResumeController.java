package com.healtheye_rbmq2.healtheye_rbmq2.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.healtheye_rbmq2.healtheye_rbmq2.service.ProducerResumeService;

@RestController
@CrossOrigin
public class ProducerResumeController {
  @Autowired
  private ProducerResumeService producer;

  @PostMapping("/send")
  public Map<String, Object> sendMessage(@RequestBody List<Map<String, Object>> message) {
    System.out.println(message);
    producer.sendMessage(message);
    Map<String, Object> response = new HashMap<>();
    response.put("status", "success");
    response.put("message", "Message sent successfully");
    return response;
  }
}
