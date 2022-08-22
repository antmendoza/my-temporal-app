package com.antmendoza.workflow1;


import com.antmendoza.api.CompleteTaskService;
import com.antmendoza.api.CreateTaskService;

public class Activity1Impl implements Activity1 {

    private final CreateTaskService createTaskService;
    private final CompleteTaskService completeTaskService;

    public Activity1Impl(CreateTaskService service,
                         CompleteTaskService completeTaskService) {
        this.createTaskService = service;
        this.completeTaskService = completeTaskService;
    }

    @Override
    public CreateTaskResponse createTask(String taskName) {
        return new CreateTaskResponse(createTaskService.execute(taskName).taskId());
    }

    @Override
    public void completeTask(String taskId) {
        completeTaskService.execute(taskId);
    }

}
