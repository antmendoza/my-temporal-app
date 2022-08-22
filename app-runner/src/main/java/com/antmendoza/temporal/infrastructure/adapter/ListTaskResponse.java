package com.antmendoza.temporal.infrastructure.adapter;

import com.antmendoza.api.Task;

import java.util.List;

public record ListTaskResponse(List<Task> tasks) {
}
