package com.antmendoza.temporal.infrastructure.adapter;

import com.antmendoza.temporal.application.service.CompleteTask;
import com.antmendoza.temporal.application.service.ListPendingTask;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskRestController {


    private final ListPendingTask listPendingTask;
    private final CompleteTask completeTask;

    public TaskRestController(ListPendingTask listPendingTask, CompleteTask completeTask) {
        this.listPendingTask = listPendingTask;
        this.completeTask = completeTask;
    }


    @GetMapping("/tasks")
    public ListTaskResponse list() {
        return new ListTaskResponse(this.listPendingTask.execute());
    }


    @PostMapping("/tasks/{taskId}")
    public void startTreatment(
            @RequestBody CompleteTaskRequest completeTaskRequest) {

        this.completeTask.execute(completeTaskRequest);

    }

}
