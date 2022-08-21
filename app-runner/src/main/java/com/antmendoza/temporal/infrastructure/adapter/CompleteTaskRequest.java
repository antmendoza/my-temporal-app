package com.antmendoza.temporal.infrastructure.adapter;


public record CompleteTaskRequest(String patientId, String treatmentId, String taskId) {

    public String buildWorkflowId() {
        return this.treatmentId + "_" + this.patientId;
    }}
