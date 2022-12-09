package com.antmendoza.workflow.slack;

import io.temporal.api.enums.v1.ParentClosePolicy;
import io.temporal.workflow.Async;
import io.temporal.workflow.ChildWorkflowOptions;
import io.temporal.workflow.Promise;
import io.temporal.workflow.Workflow;

import java.util.ArrayList;
import java.util.List;

public class WorkflowSlackImpl implements WorkflowSlack {




    @Override
    public String execute(final WorkflowInput workflowInput) {

        WorkflowChannel workflowChannel =  Workflow.newChildWorkflowStub(WorkflowChannel.class,
                ChildWorkflowOptions.newBuilder()
                        .setParentClosePolicy(ParentClosePolicy.PARENT_CLOSE_POLICY_ABANDON)
                        .build());


        List result = new ArrayList<>();

        for (SlackChannel slackChannel :workflowInput.slackChannels().channel() ) {

            result.add(Async.procedure(workflowChannel::execute, slackChannel, workflowInput.period()));

        }



        Promise.allOf(result).get();



        return "completed";
    }



}
