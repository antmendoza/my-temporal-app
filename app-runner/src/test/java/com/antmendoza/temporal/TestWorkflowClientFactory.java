package com.antmendoza.temporal;

import static org.mockito.ArgumentMatchers.any;

import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowStub;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class TestWorkflowClientFactory {

  @Primary
  @Bean("workflowClient")
  public WorkflowClient workflowClient() {

    final WorkflowStub workflowStub = Mockito.mock(WorkflowStub.class);
    Mockito.when(workflowStub.start(any()))
        .thenReturn(WorkflowExecution.newBuilder().setRunId("not-null").build());

    final WorkflowClient client = Mockito.mock(WorkflowClient.class);
    Mockito.when(client.newUntypedWorkflowStub((String) any(), any())).thenReturn(workflowStub);

    return client;
  }
}
