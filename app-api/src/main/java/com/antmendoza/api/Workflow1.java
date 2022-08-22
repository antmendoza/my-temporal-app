package com.antmendoza.api;

import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface Workflow1 {



    @WorkflowMethod
    String start(PatientDTO patient);


    @SignalMethod
    void completeTask(String taskId);
}
