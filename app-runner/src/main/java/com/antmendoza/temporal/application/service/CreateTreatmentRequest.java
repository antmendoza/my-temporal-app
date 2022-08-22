package com.antmendoza.temporal.application.service;

public record CreateTreatmentRequest(String patientId, String treatmentId) {
    public String buildWorkflowId() {
        return this.treatmentId + "_" + this.patientId;
    }
}
