package com.antmendoza;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.Closeable;
import java.io.IOException;

public class MongoConnection implements Closeable {

    private final MongoClient mongoClient;
    private final MongoDatabase database;

    public MongoConnection(){
        this.mongoClient = MongoClients.create("mongodb://localhost:27017");
        this.database = mongoClient.getDatabase("temporal");

    }

    public MongoCollection<Document> collection() {
        final MongoCollection<Document> collection = database.getCollection("tasks");

        return collection;

    }


    @Override
    public void close() {
        mongoClient.close();
    }
}
