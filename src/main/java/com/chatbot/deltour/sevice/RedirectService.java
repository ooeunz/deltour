package com.chatbot.deltour.sevice;

import com.chatbot.deltour.dto.FulfillmentTextDto;
import com.chatbot.deltour.dto.ResponseJSON.ResponseJsonDto;
import com.chatbot.deltour.util.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author ooeunz
 */

@Service
public class RedirectService {

    public RedirectService() {
    }

    public ResponseMessage RedirectQueryTxt(String queryTxt, String author) {

        ResponseJsonDto responseJsonDto = new ResponseJsonDto();

        ResponseMessage message = new ResponseMessage(HttpStatus.OK);

        FulfillmentTextDto fulfillmentTextDto = new FulfillmentTextDto(queryTxt, author);
        message.add("msg", fulfillmentTextDto);

        return message;
    }

}
