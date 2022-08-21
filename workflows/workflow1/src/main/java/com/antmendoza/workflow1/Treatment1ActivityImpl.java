package com.antmendoza.workflow1;


import com.antmendoza.api.CreateTaskService;

public class Treatment1ActivityImpl implements Treatment1Activity {

    private final CreateTaskService createTaskService;

    public Treatment1ActivityImpl(CreateTaskService service) {
        this.createTaskService = service;
    }

    @Override
    public CreateTaskResponse createTask(String taskName) {
        return new CreateTaskResponse(createTaskService.execute(taskName).taskId());
    }

}
