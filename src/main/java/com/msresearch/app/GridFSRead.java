package com.msresearch.app;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.model.Filters;

import java.io.FileOutputStream;
import org.bson.types.ObjectId;

public class GridFSRead {
    private static final String MONGODB_URI = System.getProperty("mongodb.uri");
    private static final String MONGODB_DB = System.getProperty("mongodb.database");
    // private static final String FILENAME = System.getProperty("filename");
    private static final String OBJECT_ID = System.getProperty("objectid");
    private static final String LOCAL_FILEPATH = System.getProperty("local.filepath");

    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create(MONGODB_URI);
        MongoDatabase database = mongoClient.getDatabase(MONGODB_DB);
        GridFSBucket gridFSBucket = GridFSBuckets.create(database);

        // Query for the file
        // GridFSFile gridFSFile = gridFSBucket.find(Filters.eq("filename", FILENAME)).first();

        ObjectId fileId = new ObjectId(OBJECT_ID);
        GridFSFile gridFSFile = gridFSBucket.find(Filters.eq("_id", fileId)).first();

        if (gridFSFile != null) {
            // File found, now download it
            try (FileOutputStream streamToDownloadTo = new FileOutputStream(LOCAL_FILEPATH)) {
                gridFSBucket.downloadToStream(gridFSFile.getObjectId(), streamToDownloadTo);
                System.out.println("File downloaded successfully");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File not found");
        }

        mongoClient.close();
    }
}
