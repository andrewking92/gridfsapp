package com.msresearch.app;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import org.bson.Document;

import java.io.File;
import java.io.FileInputStream;

public class GridFSWrite {
    private static final String MONGODB_URI = System.getProperty("mongodb.uri");
    private static final String MONGODB_DB = System.getProperty("mongodb.database");
    private static final String LOCAL_FILEPATH = System.getProperty("local.filepath");

    public static void main(String[] args) throws Exception {
        MongoClient mongoClient = MongoClients.create(MONGODB_URI);
        MongoDatabase database = mongoClient.getDatabase(MONGODB_DB);

        GridFSBucket gridFSBucket = GridFSBuckets.create(database);

        File folder = new File(LOCAL_FILEPATH);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile() && isSupportedFileType(file.getName())) {
                    try (FileInputStream streamToUploadFrom = new FileInputStream(file)) {
                        GridFSUploadOptions options = new GridFSUploadOptions()
                                .metadata(new Document("type", getFileExtension(file.getName())));

                        gridFSBucket.uploadFromStream(file.getName(), streamToUploadFrom, options);
                        System.out.println("Uploaded: " + file.getName());
                    }
                }
            }
        }

        mongoClient.close();
    }

    private static boolean isSupportedFileType(String fileName) {
        String fileNameLower = fileName.toLowerCase();
        return fileNameLower.endsWith(".pdf") || fileNameLower.endsWith(".ppt") || fileNameLower.endsWith(".doc")
                || fileNameLower.endsWith(".txt") || fileNameLower.endsWith(".zip") || fileNameLower.endsWith(".rar")
                || fileNameLower.endsWith(".eml");
    }

    private static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    }
}
