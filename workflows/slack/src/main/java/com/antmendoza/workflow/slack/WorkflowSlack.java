package com.antmendoza.workflow.slack;


import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface WorkflowSlack {


    @WorkflowMethod
    String execute(final WorkflowInput workflowInput);
}
