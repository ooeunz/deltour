package com.chatbot.deltour.sevice;

import com.chatbot.deltour.model.Message;
import com.chatbot.deltour.repository.MessageRepository;
import com.chatbot.deltour.sevice.Impl.DialogflowImpl;
import com.google.cloud.dialogflow.v2.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;


@Service
public class DialogflowService implements DialogflowImpl {
    private Message message ;
    private MessageRepository messageRepository;
    private String projectId = "deltour-mark-2";
    private String languageCode = "ko";


    @Autowired
    public DialogflowService(Message message, MessageRepository messageRepository) {
        this.message = message;
        this.messageRepository = messageRepository;
    }

    public JSONArray parseJson(String intent) throws JSONException {
        JSONObject jsonObject = messageRepository.findByIntent(intent);
        JSONArray jsonResponseArray = jsonObject.getJSONArray("response");
        return jsonResponseArray;
    }

    @Override
    public void detectIntentTexts(String queryTxt, String sessionId) throws Exception {

        // Instantiates a client
        try (SessionsClient sessionsClient = SessionsClient.create()) {
            // create session path
            SessionName session = SessionName.of(projectId, sessionId);
            System.out.println("Session Path: " + session.toString());

            TextInput.Builder textInput = TextInput.newBuilder().setText(queryTxt).setLanguageCode(languageCode);
            QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();

            DetectIntentResponse response = sessionsClient.detectIntent(session, queryInput);
            QueryResult queryResult = response.getQueryResult();

            // find data
            String detectIntent = queryResult.getIntent().getDisplayName();
            JSONArray jsonResponseArray = parseJson(detectIntent);
            System.out.println(jsonResponseArray.get(0));

            // output
//            System.out.println("====================");
//            System.out.format("Query Text: '%s'\n", queryResult.getQueryText());
//            System.out.format("Detected Intent: %s (confidence: %f)\n",
//                    queryResult.getIntent().getDisplayName(), queryResult.getIntentDetectionConfidence());
//            System.out.format("Fulfillment Text: '%s'\n", queryResult.getFulfillmentText());

        }
    }

}
