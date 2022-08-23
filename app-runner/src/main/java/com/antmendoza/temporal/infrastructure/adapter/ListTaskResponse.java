package com.antmendoza.temporal.infrastructure.adapter;

import com.antmendoza.api.UserTask;

import java.util.List;

public record ListTaskResponse(List<UserTask> userTasks) {
}
