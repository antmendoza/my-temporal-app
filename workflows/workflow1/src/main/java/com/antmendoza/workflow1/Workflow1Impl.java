package com.antmendoza.workflow1;

import com.antmendoza.api.PatientDTO;
import com.antmendoza.api.Workflow1;
import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;

import java.util.ArrayList;
import java.util.List;

import static java.time.Duration.ofSeconds;

public class Workflow1Impl implements Workflow1 {


    private final Activity1 activity = Workflow.newActivityStub(Activity1.class,
            ActivityOptions.newBuilder()
                    .setStartToCloseTimeout(ofSeconds(2)).build());
    private final List<String> taskCompleted = new ArrayList<>();


    @Override
    public String start(PatientDTO patient) {

        final CreateTaskResponse createTaskResponse = activity.createTask("Contact patient");
        Workflow.await(() -> this.taskCompleted.contains(createTaskResponse.taskId()));

        return "Hello World!";
    }


    @Override
    public void completeTask(String taskId) {
        activity.completeTask(taskId);
        this.taskCompleted.add(taskId);
    }
}
