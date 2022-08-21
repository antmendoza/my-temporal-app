package com.antmendoza.api;


import com.antmendoza.workflow1.Treatment1ActivityImpl;
import com.antmendoza.workflow1.Treatment1Impl;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.client.WorkflowStub;
import io.temporal.testing.TestWorkflowRule;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;

import java.time.Duration;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

public class Treatment1Test {



    @Rule
    public TestWorkflowRule testWorkflowRule =
            TestWorkflowRule.newBuilder()
                    .setWorkflowTypes(Treatment1Impl.class)
                    .setDoNotStart(true)
                    .build();




    @After
    public void cleanTestEnv(){
        testWorkflowRule.getTestEnvironment().shutdown();
    }


    @Test
    public void testActivityImpl() throws ExecutionException, InterruptedException {

        testWorkflowRule.getWorker().registerActivitiesImplementations(new Treatment1ActivityImpl(new CreateTaskService() {
            @Override
            public String execute() {
                return null;
            }
        }));
        testWorkflowRule.getTestEnvironment().start();

        final Treatment1 workflow =
                testWorkflowRule
                        .getWorkflowClient()
                        .newWorkflowStub(
                                Treatment1.class,
                                WorkflowOptions.newBuilder()
                                        .setWorkflowRunTimeout(Duration.ofSeconds(2))
                                        .setTaskQueue(testWorkflowRule.getTaskQueue()).build());


        WorkflowClient.execute(workflow::start, new PatientDTO());
        workflow.patientContacted();

        assertEquals("Hello World!", WorkflowStub.fromTyped(workflow).getResult(String.class));

    }



}