package com.chatbot.deltour.sevice.Impl;

import com.chatbot.deltour.dto.response.ResponseContentDTO;
import com.chatbot.deltour.domain.detectIntent.Intent;
import com.chatbot.deltour.domain.detectIntent.Response;
import com.chatbot.deltour.repository.IntentRepository;
import com.chatbot.deltour.sevice.DialogflowService;
import com.google.api.SystemParameterProto;
import com.google.cloud.dialogflow.v2.*;
import com.google.protobuf.Value;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.protobuf.ProtobufDecoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class DialogflowServiceImpl implements DialogflowService {

    @Autowired
    private IntentRepository intentRepository;

    private String projectId = "deltour-mark-2";
    private String languageCode = "ko";

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
//            QueryResult queryResult = response.getQueryResult();

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

                    int count = 1;
                    Map<String, com.google.protobuf.Value> parameters = queryResult.getParameters().getFieldsMap();

                    System.out.println("queryParameter: " + parameters);

                    for (Response res : responseList) {
                        String resParameter = mapper.writeValueAsString(res.getParameter());

                        System.out.println("=============" + count++);
                        System.out.println("res: " + resParameter);

                        if (queryResult.getParameters().equals(res.getParameter())) {
                            return responseContentDTO.convertResponseDTO(res);
                        }
                    }
                }
            }

            // debug
            System.out.println("Multi-Turn Text: " + fulfillmentText);
            return responseContentDTO.multiTurn(fulfillmentText);
        }
    }
}
