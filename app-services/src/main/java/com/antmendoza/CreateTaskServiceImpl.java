package com.antmendoza;


import com.antmendoza.api.CreateTask;
import com.antmendoza.api.CreateTaskService;
import com.antmendoza.api.TaskId;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Map;

public class CreateTaskServiceImpl implements CreateTaskService {


    @Override
    public TaskId execute(CreateTask createTask) {

        try (final MongoConnection connection = new MongoConnection()) {

            final MongoCollection<Document> collection = connection.collection();
            final Document tDocument = new Document(Map.of(
                    "name", createTask.taskName(),
                    "processBusinessKey", createTask.processBusinessKey(),
                    "status", "PENDING"));
            collection.insertOne(tDocument);
            final ObjectId id = (ObjectId) tDocument.get("_id");
            return new TaskId(id.toString());
        }
    }


}
