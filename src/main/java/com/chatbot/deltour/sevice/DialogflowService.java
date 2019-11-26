package com.chatbot.deltour.sevice;

import com.chatbot.deltour.model.Message;
import com.chatbot.deltour.sevice.Impl.DialogflowImpl;
import com.google.api.gax.paging.Page;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.dialogflow.v2.*;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;

@Service
public class DialogflowService implements DialogflowImpl {
    private Message message ;
    private String projectId = "deltour-mark-2";
    private String languageCode = "ko";


    @Autowired
    public DialogflowService(Message message) {
        this.message = message;
    }

    @Override
    public void detectIntentTexts(String queryTxt, String sessionId) throws Exception {

        Storage storage = StorageOptions.getDefaultInstance().getService();

        // Instantiates a client
        try (SessionsClient sessionsClient = SessionsClient.create()) {
            // create session path
            SessionName session = SessionName.of(projectId, sessionId);
            System.out.println("Session Path: " + session.toString());

            TextInput.Builder textInput = TextInput.newBuilder().setText(queryTxt).setLanguageCode(languageCode);
            QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();

            DetectIntentResponse response = sessionsClient.detectIntent(session, queryInput);
            QueryResult queryResult = response.getQueryResult();

            // output
            System.out.println("====================");
            System.out.format("Query Text: '%s'\n", queryResult.getQueryText());
            System.out.format("Detected Intent: %s (confidence: %f)\n",
                    queryResult.getIntent().getDisplayName(), queryResult.getIntentDetectionConfidence());
            System.out.format("Fulfillment Text: '%s'\n", queryResult.getFulfillmentText());

        }
    }

}
