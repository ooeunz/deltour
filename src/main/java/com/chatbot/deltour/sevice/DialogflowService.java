package com.chatbot.deltour.sevice;

import com.chatbot.deltour.model.Message;
import com.chatbot.deltour.sevice.Impl.DialogflowImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DialogflowService implements DialogflowImpl {
    private Message message ;
    private String projectId = "deltour";
    private String languageCode = "ko";


    @Autowired
    public DialogflowService(Message message) {
        this.message = message;
    }

    @Override
    public Message detectIntentTexts(List<String> texts, String sessionId)  {

        return message
    }

}
