package com.chatbot.deltour.sevice.Impl;

import com.chatbot.deltour.dto.FulfillmentTextDto;
import com.chatbot.deltour.domain.detectIntent.Intent;
import com.chatbot.deltour.repository.IntentRepository;
import com.chatbot.deltour.sevice.DialogflowService;
import com.chatbot.deltour.util.ResponseMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.Http;
import com.google.cloud.dialogflow.v2.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ooeunz
 *
 */

@Service
public class DialogflowServiceImpl implements DialogflowService {

    @Autowired
    private IntentRepository intentRepository;

    private String projectId = "deltour-mark-2";
    private String languageCode = "ko";

    private Map<String, Object> convertGoogleParameter(Map<String, com.google.protobuf.Value> parameters) {

        Map<String, Object> googleParameter = new HashMap<String, Object>();

        for (String key : parameters.keySet()) {

            String[] string_value = parameters.get(key).toString().split(" ");
            String str = string_value[string_value.length-1].replaceAll("\"", "").replaceAll("\n", "");

            googleParameter.put(key, str);
        }

        return googleParameter;
    }

    private Map<String, Object> findEqualParameter(Map<String, Object> googleParameter, List<Map<String, Object>> responseList) {

        for (Map<String, Object> exResponse : responseList) {
            if (googleParameter.equals(exResponse.get("parameter"))) {
                return exResponse;
            }
        }
        return null;
    }

    @Override
    public ResponseMessage detectIntentTexts(String queryTxt, String sessionId) throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        FulfillmentTextDto fulfillmentTextDTO = new FulfillmentTextDto();

        ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK);

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

            String detectIntent = queryResult.getIntent().getDisplayName();

            System.out.format("Query Text: '%s'\n", queryText);
            System.out.format("Detected Intent: %s (confidence: %f)\n",
                    queryResult.getIntent().getDisplayName(), queryResult.getIntentDetectionConfidence());
            System.out.println("googleParameter: " + convertGoogleParameter(queryResult.getParameters().getFieldsMap()));

            if (queryResult.getAllRequiredParamsPresent()) {

                // response output
                Intent intent = this.intentRepository.findByIntent(detectIntent);
                if (intent == null) {
                    responseMessage.add("msg", fulfillmentTextDTO.multiTurn("무슨 말인지 모르겠어ㅜㅜ"));
                    return responseMessage;
                }

                List<Map<String, Object>> responseList = intent.getResponse();

                Map<String, Object> googleParameter = convertGoogleParameter(queryResult.getParameters().getFieldsMap());

                System.out.println("====================");
                System.out.format("Query Text: '%s'\n", queryText);
                System.out.format("Detected Intent: %s (confidence: %f)\n",
                        queryResult.getIntent().getDisplayName(), queryResult.getIntentDetectionConfidence());
                System.out.println("googleParameter: " + googleParameter);

                if (queryResult.getParameters() != null) {
                    Map<String, Object> result = findEqualParameter(googleParameter, responseList);

                    responseMessage.add("msg", fulfillmentTextDTO.convertResponseDTO(result));

                    return responseMessage;
                }
            }
            // Multi turn
            responseMessage.add("msg", fulfillmentTextDTO.multiTurn(fulfillmentText));

            return responseMessage;
        }
    }
}
