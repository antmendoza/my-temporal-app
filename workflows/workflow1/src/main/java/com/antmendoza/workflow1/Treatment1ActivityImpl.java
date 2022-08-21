package com.antmendoza.workflow1;


import com.antmendoza.api.CreateTaskService;

public class Treatment1ActivityImpl implements Treatment1Activity {

    private final CreateTaskService createTaskService;

    public Treatment1ActivityImpl(CreateTaskService service) {
        this.createTaskService = service;
    }

    @Override
    public String contactPatient() {
        return createTaskService.execute();
    }

}
