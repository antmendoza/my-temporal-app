package com.antmendoza.temporal.application.service;

import com.antmendoza.api.ListPendingTaskService;
import com.antmendoza.api.Task;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListPendingTask {

    private final ListPendingTaskService listPendingTaskService;

    public ListPendingTask(ListPendingTaskService listPendingTaskService) {
        this.listPendingTaskService = listPendingTaskService;
    }


    public List<Task> execute() {
        return listPendingTaskService.execute();
    }
}
