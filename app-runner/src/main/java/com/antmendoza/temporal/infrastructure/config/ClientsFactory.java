package com.antmendoza.temporal.infrastructure.config;

import com.antmendoza.ListPendingTaskServiceImpl;
import com.antmendoza.api.ListPendingTaskService;
import com.antmendoza.api.Task;
import com.antmendoza.temporal.application.service.ListPendingTask;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ClientsFactory {

  private final WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
  private final WorkflowClient client = WorkflowClient.newInstance(service);

  @Bean("workflowClient")
  public WorkflowClient workflowClient() {
    return client;
  }


  @Bean
  public ListPendingTaskService listPendingTaskService() {
    return new ListPendingTaskServiceImpl();
  }

}
