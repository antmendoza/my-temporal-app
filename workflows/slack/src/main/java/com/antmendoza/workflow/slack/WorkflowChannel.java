package com.antmendoza.workflow.slack;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface WorkflowChannel {
    @WorkflowMethod
    void execute(SlackChannel slackChannel, Period period);
}
