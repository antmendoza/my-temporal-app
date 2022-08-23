package com.antmendoza.api;

import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface WorkflowChild {



    @WorkflowMethod
    String start(String taskName);

    @SignalMethod
    void completeTask(String taskId);
}
