package com.antmendoza.api;


import com.antmendoza.workflow.slack.*;
import io.temporal.api.workflowservice.v1.ListClosedWorkflowExecutionsRequest;
import io.temporal.api.workflowservice.v1.ListClosedWorkflowExecutionsResponse;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.client.WorkflowStub;
import io.temporal.testing.TestWorkflowRule;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;
import org.mockito.verification.VerificationMode;

import java.time.Duration;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.withSettings;


public class WorkflowSlackTest {

    @Rule
    public TestWorkflowRule testWorkflowRule =
            TestWorkflowRule.newBuilder()
                    .setWorkflowTypes(WorkflowSlackImpl.class, WorkflowChannelImpl.class)
                    .setDoNotStart(true)
                    .build();


    @After
    public void cleanTestEnv() {
        testWorkflowRule.getTestEnvironment().shutdown();
    }




    @Test
    public void testExecuteChildWf() {


        final ActivityChannel activityChannel = Mockito.mock(ActivityChannel.class, withSettings().withoutAnnotations());


        testWorkflowRule.getWorker().registerActivitiesImplementations(activityChannel);
        testWorkflowRule.getTestEnvironment().start();

        final WorkflowSlack workflow = createWorkflow();

        SlackChannels channels = new SlackChannels(new SlackChannel("support"), new SlackChannel("support-cloud"));
        Period period = new Period(new Date(), new Date());
        CompletableFuture<String> result = WorkflowClient.execute(workflow::execute, new WorkflowInput(channels, period));

        assertEquals("completed", WorkflowStub.fromTyped(workflow).getResult(String.class));

        final ListClosedWorkflowExecutionsResponse closedWfExecutions =
                testWorkflowRule.getTestEnvironment().getWorkflowClient().getWorkflowServiceStubs().blockingStub().listClosedWorkflowExecutions(ListClosedWorkflowExecutionsRequest.newBuilder().build());
        assertEquals(3, closedWfExecutions.getExecutionsCount());

    }


    @Test
    public void testPullFromChannelsWf() {


        final ActivityChannel activityChannel = Mockito.mock(ActivityChannel.class, withSettings().withoutAnnotations());


        testWorkflowRule.getWorker().registerActivitiesImplementations(activityChannel);
        testWorkflowRule.getTestEnvironment().start();


        final WorkflowSlack workflow = createWorkflow();

        SlackChannels channels = new SlackChannels(new SlackChannel("support"), new SlackChannel("support-cloud"));
        Period period = new Period(new Date(), new Date());
        WorkflowClient.execute(workflow::execute, new WorkflowInput(channels, period));

        assertEquals("completed", WorkflowStub.fromTyped(workflow).getResult(String.class));

        final ListClosedWorkflowExecutionsResponse closedWfExecutions =
                testWorkflowRule.getTestEnvironment().getWorkflowClient().getWorkflowServiceStubs().blockingStub().listClosedWorkflowExecutions(ListClosedWorkflowExecutionsRequest.newBuilder().build());
        assertEquals(3, closedWfExecutions.getExecutionsCount());

        verify(activityChannel, new Times(2)).pullMessages(any(), any());

    }


    private WorkflowSlack createWorkflow() {
        final WorkflowSlack workflow =
                testWorkflowRule
                        .getWorkflowClient()
                        .newWorkflowStub(
                                WorkflowSlack.class,
                                WorkflowOptions.newBuilder()
                                        .setWorkflowRunTimeout(Duration.ofSeconds(20))
                                        .setTaskQueue(testWorkflowRule.getTaskQueue()).build());
        return workflow;
    }

}