package com.chatbot.deltour.sevice.Impl;

import com.chatbot.deltour.dto.response.ResponseContentDTO;
import com.chatbot.deltour.domain.detectIntent.Intent;
import com.chatbot.deltour.repository.IntentRepository;
import com.chatbot.deltour.sevice.DialogflowService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.dialogflow.v2.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class DialogflowServiceImpl implements DialogflowService {

    @Autowired
    private IntentRepository intentRepository;

    private String projectId = "deltour-mark-2";
    private String languageCode = "ko";

    public Map<String, Object> convertGoogleParameter(Map<String, com.google.protobuf.Value> parameters) {
        Map<String, Object> googleParameter = new HashMap<String, Object>();
        for (String key : parameters.keySet()) {

            String[] string_value = parameters.get(key).toString().split(" ");
            String str = string_value[string_value.length-1].replaceAll("\"", "").replaceAll("\n", "");
            googleParameter.put(key, str);
        }
        return googleParameter;
    }

    public Map<String, Object> findEqualParameter(Map<String, Object> googleParameter, List<Map<String, Object>> responseList) {

        for (Map<String, Object> exResponse : responseList) {
            if (googleParameter.equals(exResponse.get("parameter"))) {
                return exResponse;
            }
        }
        return null;
    }

    @Override
    public ResponseContentDTO detectIntentTexts(String queryTxt, String sessionId) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
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

            // cashing
            String queryText = queryResult.getQueryText();
            String fulfillmentText = queryResult.getFulfillmentText();
            String detectIntent = queryResult.getIntent().getDisplayName();

            System.out.println("Detect Intent: " + detectIntent);

            if (queryResult.getAllRequiredParamsPresent()) {

                // debug
                System.out.println(("AllRequiredParamsPresent: " + queryResult.getAllRequiredParamsPresent()));

                // response output
                Intent intent = this.intentRepository.findByIntent(detectIntent);
                List<Map<String, Object>> responseList = intent.getResponse();

                Map<String, Object> googleParameter = convertGoogleParameter(queryResult.getParameters().getFieldsMap());
                // console output
                System.out.println("====================");
                System.out.format("Query Text: '%s'\n", queryText);
                System.out.format("Detected Intent: %s (confidence: %f)\n",
                        queryResult.getIntent().getDisplayName(), queryResult.getIntentDetectionConfidence());
                System.out.println("googleParameter: " + googleParameter);

                // find equal parameter
                if (queryResult.getParameters() != null) {
                    Map<String, Object> result = findEqualParameter(googleParameter, responseList);
                    return responseContentDTO.convertResponseDTO(result);
                }
            }
            // Multi turn
            return responseContentDTO.multiTurn(fulfillmentText);
        }
    }
}
