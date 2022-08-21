package com.antmendoza.temporal.infrastructure.adapter;

import com.antmendoza.temporal.service.CompleteTask;
import com.antmendoza.temporal.service.CreateTreatment;
import com.antmendoza.temporal.service.CreateTreatmentRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TreatmentRestController {

    private final CreateTreatment createTreatment;

    private final CompleteTask completeTask;

    public TreatmentRestController(CreateTreatment createTreatment, CompleteTask completeTask) {
        this.createTreatment = createTreatment;
        this.completeTask = completeTask;
    }

    @PostMapping("/patients/{patientId}/treatments")
    public String startTreatment(
            @PathVariable(name = "patientId") String patientId,
            @RequestBody StartTreatmentRequest startTreatmentRequest) {

        return this.createTreatment.execute(new CreateTreatmentRequest(patientId, startTreatmentRequest.treatmentId()));
    }

    @PostMapping("/complete-task")
    public void startTreatment(
            @RequestBody CompleteTaskRequest completeTaskRequest) {

        this.completeTask.execute(completeTaskRequest);

    }

}
