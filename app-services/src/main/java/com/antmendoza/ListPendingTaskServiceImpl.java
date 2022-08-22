package com.antmendoza;

import com.antmendoza.api.ListPendingTaskService;
import com.antmendoza.api.Task;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class ListPendingTaskServiceImpl implements ListPendingTaskService {


    @Override
    public List<Task> execute() {

        try (final MongoConnection connection = new MongoConnection()) {

            List<Task> taskResult = new ArrayList();

            final MongoCollection<Document> collection = connection.collection();

            FindIterable<Document> result = collection.find(Filters.eq("status", "PENDING"));
            MongoCursor<Document> itResult = result.iterator();
            while (itResult.hasNext()) {
                Document t = itResult.next();
                taskResult.add(new Task(t.get("_id").toString(), (String) t.get("status"), (String) t.get("name")));
            }

            return taskResult;

        }

    }
}
