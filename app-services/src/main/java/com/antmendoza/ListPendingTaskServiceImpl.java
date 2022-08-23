package com.antmendoza;

import com.antmendoza.api.ListPendingTaskService;
import com.antmendoza.api.UserTask;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class ListPendingTaskServiceImpl implements ListPendingTaskService {


    @Override
    public List<UserTask> execute() {

        try (final MongoConnection connection = new MongoConnection()) {

            List<UserTask> userTaskResult = new ArrayList();

            final MongoCollection<Document> collection = connection.collection();

            FindIterable<Document> result = collection.find(Filters.eq("status", "PENDING"));
            MongoCursor<Document> itResult = result.iterator();
            while (itResult.hasNext()) {
                Document t = itResult.next();
                userTaskResult.add(new UserTask(t.get("_id").toString(),
                                (String) t.get("status"),
                                (String) t.get("name"),
                                (String) t.get("processBusinessKey")
                        )
                );
            }

            return userTaskResult;

        }

    }
}
