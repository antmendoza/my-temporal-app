package com.antmendoza.temporal.service;

import com.antmendoza.temporal.domain.TreatmentRepository;
import com.antmendoza.temporal.infrastructure.adapter.CompleteTaskRequest;
import org.springframework.stereotype.Component;

@Component
public class CompleteTask {

    private final TreatmentRepository treatmentRepository;

    public CompleteTask(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    public void execute(CompleteTaskRequest completeTaskRequest) {
         this.treatmentRepository.completeTask(completeTaskRequest);
    }
}
