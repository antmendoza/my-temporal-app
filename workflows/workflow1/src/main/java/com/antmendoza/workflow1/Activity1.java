package com.antmendoza.workflow1;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface Activity1 {
    @ActivityMethod
    CreateTaskResponse createTask(String taskName);

    @ActivityMethod
    void completeTask(String taskId);
}
