package com.antmendoza.api;


import com.antmendoza.workflow1.Activity1Impl;
import com.antmendoza.workflow1.Workflow1Impl;
import com.antmendoza.workflow1.WorkflowChildImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.client.WorkflowStub;
import io.temporal.testing.TestWorkflowRule;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;

import java.time.Duration;

import static org.junit.Assert.assertEquals;


public class Workflow1Test {


    @Rule
    public TestWorkflowRule testWorkflowRule =
            TestWorkflowRule.newBuilder()
                    .setWorkflowTypes(Workflow1Impl.class, WorkflowChildImpl.class)
                    .setDoNotStart(true)
                    .build();


    @After
    public void cleanTestEnv() {
        testWorkflowRule.getTestEnvironment().shutdown();
    }


    @Test
    public void testActivityImpl() {

        testWorkflowRule.getWorker().registerActivitiesImplementations(new Activity1Impl(new CreateTaskService() {

            @Override
            public TaskId execute(CreateTask createTask) {
                return new TaskId("taskId_2");
            }
        }, new CompleteTaskService() {
            @Override
            public void execute(String taskName) {
            }
        }));

        testWorkflowRule.getTestEnvironment().start();

        final Workflow1 workflow =
                testWorkflowRule
                        .getWorkflowClient()
                        .newWorkflowStub(
                                Workflow1.class,
                                WorkflowOptions.newBuilder()
                                        .setWorkflowRunTimeout(Duration.ofSeconds(20))
                                        .setTaskQueue(testWorkflowRule.getTaskQueue()).build());
        WorkflowClient.execute(workflow::start);
        //workflow.completeTask("taskId_2");
        //assertEquals("completed", WorkflowStub.fromTyped(workflow).getResult(String.class));

    }


}