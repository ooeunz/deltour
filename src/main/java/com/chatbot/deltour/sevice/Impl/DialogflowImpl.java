package com.chatbot.deltour.sevice.Impl;

import com.chatbot.deltour.model.Message;

import java.util.List;

public interface DialogflowImpl {
    void detectIntentTexts(String queryTxt, String sessionI) throws Exception;
}
