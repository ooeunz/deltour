package com.chatbot.deltour.sevice.Impl;

import com.chatbot.deltour.model.Message;

import java.util.List;

public interface DialogflowImpl {
    Message detectIntentTexts(String projectId, List<String> texts, String sessionId,
                              String languageCode);
}
