package com.chatbot.deltour;

import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DeltourApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeltourApplication.class, args);
    }

}
