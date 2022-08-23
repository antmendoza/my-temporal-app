package com.antmendoza.workflow1;

import com.antmendoza.api.CreateTask;
import com.antmendoza.api.WorkflowChild;
import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;

import java.util.ArrayList;
import java.util.List;

import static java.time.Duration.ofSeconds;

public class WorkflowChildImpl implements WorkflowChild {


    private final Activity1 activity = Workflow.newActivityStub(Activity1.class,
            ActivityOptions.newBuilder()
                    .setStartToCloseTimeout(ofSeconds(2)).build());
    private final List<String> taskCompleted = new ArrayList<>();


    @Override
    public String start(String taskName) {


        final CreateTaskResponse createTaskResponse = activity.createTask(
                new CreateTask(taskName,
                        Workflow.getInfo().getWorkflowId())

        );
        Workflow.await(() -> this.taskCompleted.contains(createTaskResponse.taskId()));

        return "completed";
    }


    @Override
    public void completeTask(String taskId) {
        activity.completeTask(taskId);
        this.taskCompleted.add(taskId);
    }
}
