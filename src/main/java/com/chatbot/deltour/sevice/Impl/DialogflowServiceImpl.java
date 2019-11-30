package com.chatbot.deltour.sevice.Impl;

import com.chatbot.deltour.model.detectIntent.Intent;
import com.chatbot.deltour.model.detectIntent.Response;
import com.chatbot.deltour.repository.IntentRepository;
import com.chatbot.deltour.sevice.DialogflowService;
import com.google.cloud.dialogflow.v2.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class DialogflowServiceImpl implements DialogflowService {
    private IntentRepository intentRepository;

    private String projectId = "deltour-mark-2";
    private String languageCode = "ko";


    @Autowired
    public DialogflowServiceImpl(IntentRepository intentRepository) {
        this.intentRepository = intentRepository;
    }

    @Override
    public Intent detectIntentTexts(String queryTxt, String sessionId) throws Exception {

        // Instantiates a client
        try (SessionsClient sessionsClient = SessionsClient.create()) {
            // create session path
            SessionName session = SessionName.of(projectId, sessionId);
            System.out.println("Session Path: " + session.toString());

            TextInput.Builder textInput = TextInput.newBuilder().setText(queryTxt).setLanguageCode(languageCode);
            QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();

            DetectIntentResponse response = sessionsClient.detectIntent(session, queryInput);
            QueryResult queryResult = response.getQueryResult();

            // console output
            System.out.println("====================");
            System.out.format("Query Text: '%s'\n", queryResult.getQueryText());
            System.out.format("Detected Intent: %s (confidence: %f)\n",
                    queryResult.getIntent().getDisplayName(), queryResult.getIntentDetectionConfidence());
            System.out.format("Fulfillment Text: '%s'\n", queryResult.getFulfillmentText());

            // response output
            String detectIntent = queryResult.getIntent().getDisplayName();
            Intent intent = this.intentRepository.findByIntent(detectIntent);
            return intent;
//            List<Response> responseMessage = intent.getResponse();
//            String fulfillment = responseMessage.get(0).getFulfillmentText();
//            return fulfillment;



        }
    }

}
