package com.antmendoza.temporal.application.service;

import com.antmendoza.MongoConnection;
import com.antmendoza.temporal.domain.WorkflowRepository;
import com.antmendoza.temporal.infrastructure.adapter.CompleteTaskRequest;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class CompleteTask {

    private final WorkflowRepository treatmentRepository;

    public CompleteTask(WorkflowRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    public void execute(String taskId) {

        try (final MongoConnection connection = new MongoConnection()) {

            final MongoCollection<Document> collection = connection.collection();

            FindIterable<Document> result = collection.find(Filters.eq("_id", new ObjectId(taskId)));

            String processBusinessKey = result.iterator().next().get("processBusinessKey",String.class);

            this.treatmentRepository.completeTask(new CompleteTaskRequest(taskId, processBusinessKey));
        }


    }
}
