package com.antmendoza.temporal.domain;

import com.antmendoza.temporal.infrastructure.adapter.CompleteTaskRequest;
import com.antmendoza.temporal.application.service.CreateTreatmentRequest;

public interface WorkflowRepository {
  String save(CreateTreatmentRequest createTreatmentRequest);

  void completeTask(CompleteTaskRequest completeTaskRequest);
}
