package com.antmendoza.temporal.domain;

import com.antmendoza.temporal.service.CreateTreatmentRequest;

public interface TreatmentRepository {
  String save(CreateTreatmentRequest createTreatmentRequest);
}
