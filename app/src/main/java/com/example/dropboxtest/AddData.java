/*package com.example.dropboxtest.Mongodb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.dropboxtest.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.lang.NonNull;
import com.mongodb.stitch.android.core.Stitch;
import com.mongodb.stitch.android.core.StitchAppClient;
import com.mongodb.stitch.android.services.mongodb.local.LocalMongoDbService;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoCollection;
import com.mongodb.stitch.core.services.mongodb.remote.RemoteInsertManyResult;

import org.bson.BsonString;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AddData extends AppCompatActivity {
    private StitchAppClient stitchClient;
    private RemoteMongoClient mongoClient;
    private RemoteMongoCollection itemsCollection;
    private RemoteMongoCollection testcollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);


        Document doc1 = new Document()
                .append("name", "basketball")
                .append("category", "sports")
                .append("quantity", 20)
                .append("reviews", Arrays.asList());


        // Create the default Stitch Client
        final StitchAppClient client = Stitch.initializeDefaultAppClient("pspn_1-ykutk");

// Create a Client for MongoDB Mobile (initializing MongoDB Mobile)
        final MongoClient mobileClient = client.getServiceClient(LocalMongoDbService.clientFactory);


        // Point to the target collection and insert a document
        MongoCollection<Document> localCollection = mobileClient.getDatabase("Data").getCollection("Coll");

        localCollection.insertOne(doc1);

    // Find the first document
        Document doc = localCollection.find().first();

    //Find all documents that match the find criteria

      Document query = new Document();
        query.put("name", new BsonString("veirs"));

        FindIterable<Document> cursor = localCollection.find(query);
        ArrayList<Document> results = (ArrayList<Document>) cursor.into(new ArrayList<Document>());









//        stitchClient = Stitch.getDefaultAppClient();
//        mongoClient = stitchClient.getServiceClient(RemoteMongoClient.factory, "mongodb-atlas");
//        itemsCollection = mongoClient.getDatabase("Data").getCollection("Coll");
//        testcollection = mongoClient.getDatabase("Test").getCollection("my_collection");
//
//        Document doc1 = new Document()
//                .append("name", "basketball")
//                .append("category", "sports")
//                .append("quantity", 20)
//                .append("reviews", Arrays.asList());
//
//        Document doc2 = new Document()
//                .append("name", "football")
//                .append("category", "sports")
//                .append("quantity", 30)
//                .append("reviews", Arrays.asList());
//
//        List<Document> docs = Arrays.asList(doc1, doc2);
//
//        final Task<RemoteInsertManyResult> insertTask = itemsCollection.insertMany(docs);
//        insertTask.addOnCompleteListener(new OnCompleteListener<RemoteInsertManyResult>() {
//            @Override
//            public void onComplete(@NonNull Task <RemoteInsertManyResult> task) {
//                if (task.isSuccessful()) {
//                    Log.d("app",
//                            String.format("successfully inserted %d items with ids: %s",
//                                    task.getResult().getInsertedIds().size(),
//                                    task.getResult().getInsertedIds().toString()));
//                } else {
//                    Log.e("app", "failed to inserts document with: ", task.getException());
//                }
//            }
//        });



    }
}
*/