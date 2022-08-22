package com.antmendoza.temporal.application.service;

import com.antmendoza.temporal.domain.WorkflowRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateTreatment {

  private final WorkflowRepository treatmentRepository;

  public CreateTreatment(WorkflowRepository treatmentRepository) {
    this.treatmentRepository = treatmentRepository;
  }

  public String execute(CreateTreatmentRequest createTreatmentRequest) {
    return this.treatmentRepository.save(createTreatmentRequest);
  }
}
