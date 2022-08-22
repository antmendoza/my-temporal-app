package com.antmendoza.workflow1;

import com.antmendoza.api.CreateTask;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface Activity1 {

    @ActivityMethod
    CreateTaskResponse createTask(CreateTask createTask);

    @ActivityMethod
    void completeTask(String taskId);
}
