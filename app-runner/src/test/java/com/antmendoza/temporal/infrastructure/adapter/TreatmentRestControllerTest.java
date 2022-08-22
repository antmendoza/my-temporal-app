package com.antmendoza.temporal.infrastructure.adapter;

import com.antmendoza.temporal.TestWorkflowClientFactory;
import io.temporal.client.WorkflowClient;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = TestWorkflowClientFactory.class)
class TreatmentRestControllerTest {

  @Autowired WorkflowClient workflowClient;

  @Autowired TreatmentRestController treatmentRestController;

  @Test
  public void test() {
    final String response = treatmentRestController.startTreatment("patientId",
            new StartTreatmentRequest("Workflow1"));
    Assert.assertNotNull(response);
  }
}
