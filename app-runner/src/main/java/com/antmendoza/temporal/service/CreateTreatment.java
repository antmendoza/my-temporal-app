package com.antmendoza.temporal.service;

import com.antmendoza.temporal.domain.TreatmentRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateTreatment {

  private final TreatmentRepository treatmentRepository;

  public CreateTreatment(TreatmentRepository treatmentRepository) {
    this.treatmentRepository = treatmentRepository;
  }

  public String execute(CreateTreatmentRequest createTreatmentRequest) {
    return this.treatmentRepository.save(createTreatmentRequest);
  }
}
