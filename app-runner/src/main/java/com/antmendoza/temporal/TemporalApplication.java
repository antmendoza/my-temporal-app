package com.antmendoza.temporal;

import io.temporal.api.workflowservice.v1.GetClusterInfoRequest;
import io.temporal.api.workflowservice.v1.WorkflowServiceGrpc;
import io.temporal.client.WorkflowClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TemporalApplication {

  public static void main(String[] args) {
    SpringApplication.run(TemporalApplication.class, args);
  }
}
