package com.antmendoza.temporal.infrastructure.adapter;


public record CompleteTaskRequest(String taskId, String processBusinessKey) {
}
