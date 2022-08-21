package com.antmendoza.workflow1;

import com.antmendoza.api.PatientDTO;
import com.antmendoza.api.Treatment1;
import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;

import java.util.ArrayList;
import java.util.List;

import static java.time.Duration.ofSeconds;

public class Treatment1Impl implements Treatment1 {


    private final Treatment1Activity activity = Workflow.newActivityStub(Treatment1Activity.class,
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
        this.taskCompleted.add(taskId);
    }
}
