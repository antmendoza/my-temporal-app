package com.antmendoza.temporal;

import com.antmendoza.CreateTaskServiceImpl;
import com.antmendoza.workflow1.Treatment1ActivityImpl;
import com.antmendoza.workflow1.Treatment1Impl;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.WorkerFactory;

public class AppWorker {
    private static final WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();

    private static final WorkflowClient client = WorkflowClient.newInstance(service);

    private static final WorkerFactory factory = WorkerFactory.newInstance(client);

    public static void start(String[] args) {

        io.temporal.worker.Worker worker = factory.newWorker("task_queue");


        worker.registerWorkflowImplementationTypes(Treatment1Impl.class);
        worker.registerActivitiesImplementations(new Treatment1ActivityImpl(new CreateTaskServiceImpl()));

        factory.start();


    }


}


