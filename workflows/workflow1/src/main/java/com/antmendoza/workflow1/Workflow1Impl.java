package com.antmendoza.workflow1;

import com.antmendoza.api.Workflow1;
import com.antmendoza.api.WorkflowChild;
import io.temporal.workflow.Workflow;

public class Workflow1Impl implements Workflow1 {


    WorkflowChild child = Workflow.newChildWorkflowStub(WorkflowChild.class);

    @Override
    public String start() {

        child.start("Create task");

        child.start("Create task1");

        child.start("Create task2");

        child.start("Create task3");

        return "completed";
    }



}
