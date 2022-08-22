package com.antmendoza;


import com.antmendoza.api.CompleteTaskService;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.types.ObjectId;

public class CompleteTaskServiceImpl implements CompleteTaskService {

    @Override
    public void execute(String id) {
        try (final MongoConnection connection = new MongoConnection()) {
            final MongoCollection<Document> collection = connection.collection();
            collection.updateOne(Filters.eq("_id", new ObjectId(id)),
                    Updates.combine(Updates.set("status", "COMPLETED")));
        }
    }


}
