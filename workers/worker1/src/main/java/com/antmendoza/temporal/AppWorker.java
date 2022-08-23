package com.antmendoza.temporal;

import com.antmendoza.CompleteTaskServiceImpl;
import com.antmendoza.CreateTaskServiceImpl;
import com.antmendoza.workflow1.Activity1Impl;
import com.antmendoza.workflow1.Workflow1Impl;
import com.antmendoza.workflow1.WorkflowChildImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.WorkerFactory;

public class AppWorker {
    private static final WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();

    private static final WorkflowClient client = WorkflowClient.newInstance(service);

    private static final WorkerFactory factory = WorkerFactory.newInstance(client);

    public static void start(String[] args) {

        io.temporal.worker.Worker worker = factory.newWorker("task_queue");
        
        worker.registerWorkflowImplementationTypes(Workflow1Impl.class, WorkflowChildImpl.class);
        worker.registerActivitiesImplementations(new Activity1Impl(new CreateTaskServiceImpl(), new CompleteTaskServiceImpl()));

        factory.start();

    }


}


