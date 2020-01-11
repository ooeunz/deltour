package com.chatbot.deltour.sevice.Impl;

import com.chatbot.deltour.dto.response.ResponseContentDTO;
import com.chatbot.deltour.model.detectIntent.Intent;
import com.chatbot.deltour.model.detectIntent.Response;
import com.chatbot.deltour.repository.IntentRepository;
import com.chatbot.deltour.sevice.DialogflowService;
import com.google.cloud.dialogflow.v2.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DialogflowServiceImpl implements DialogflowService {

    @Autowired
    private IntentRepository intentRepository;

    private String projectId = "deltour-mark-2";
    private String languageCode = "ko";

    @Override
    public ResponseContentDTO detectIntentTexts(String queryTxt, String sessionId) throws Exception {

        ResponseContentDTO responseContentDTO = new ResponseContentDTO();

        // Instantiates a client
        try (SessionsClient sessionsClient = SessionsClient.create()) {

            // create session path
            SessionName session = SessionName.of(projectId, sessionId);
            System.out.println("Session Path: " + session.toString());

            TextInput.Builder textInput = TextInput.newBuilder().setText(queryTxt).setLanguageCode(languageCode);
            QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();

            DetectIntentResponse response = sessionsClient.detectIntent(session, queryInput);
            QueryResult queryResult = response.getQueryResult();

            String queryText = queryResult.getQueryText();
            String fulfillmentText = queryResult.getFulfillmentText();

            if (queryResult.getAllRequiredParamsPresent()) {

                // response output
                String detectIntent = queryResult.getIntent().getDisplayName();
                Intent intent = this.intentRepository.findByIntent(detectIntent);

                // debug
                System.out.println("Intent List: " + intent);
                List<Response> responseList = intent.getResponse();

                // console output
                System.out.println("====================");
                System.out.format("Query Text: '%s'\n", queryText);
                System.out.format("Detected Intent: %s (confidence: %f)\n",
                        queryResult.getIntent().getDisplayName(), queryResult.getIntentDetectionConfidence());
                System.out.format("Fulfillment Text: '%s'\n", fulfillmentText);

                // find equal parameter
                if (queryResult.getParameters() != null) {
                    for (Response res : responseList) {
                        if (queryResult.getParameters().equals(res.getParameter())) {
                            return responseContentDTO.convertResponseDTO(res);
                        }
                    }
                }
            }

            // debug
            System.out.println("Multi-Turn Text: " + fulfillmentText);

            // Not null exception Error 발생!!!
            return responseContentDTO.multiTurn(fulfillmentText);
        }
    }
}
