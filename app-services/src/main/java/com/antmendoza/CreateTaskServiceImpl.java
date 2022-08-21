package com.antmendoza;


import com.antmendoza.api.CreateTaskService;
import com.antmendoza.api.TaskId;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Map;

public class CreateTaskServiceImpl implements CreateTaskService {


    @Override
    public TaskId execute(String taskName) {

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("temporal");
            MongoCollection<Document> collection = database.getCollection("tasks");
            Document tDocument = new Document(Map.of("taskName", taskName));
            collection.insertOne(tDocument);
            ObjectId id = (ObjectId)tDocument.get( "_id" );
            return new TaskId(id.toString());
        }
    }
}
