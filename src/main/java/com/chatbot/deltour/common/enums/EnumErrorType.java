package com.chatbot.deltour.common.enums;

import com.chatbot.deltour.common.util.MessageBundleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.EnumSet;

/**
 * wedulpos
 * 에러 기본 메시지 Enum Type
 *
 * @author wedul
 * @since 2018-12-20
 **/
public enum EnumErrorType implements ErrorMsgI {
  ERROR_400(400) {
    @Override
    public String getDefaultMsg() {
      return messageBundleUtil.getMessage("error.message.400");
    }
  },
  ERROR_500(500) {
    @Override
    public String getDefaultMsg() {
      return messageBundleUtil.getMessage("error.message.500");
    }
  },
  ERROR_404(404) {
    @Override
    public String getDefaultMsg() {
      return messageBundleUtil.getMessage("error.message.404");
    }
  },
  ERROR_403(403) {
    @Override
    public String getDefaultMsg() {
      return messageBundleUtil.getMessage("error.message.403");
    }
  },
  ERROR_409(409) {
    @Override
    public String getDefaultMsg() {
      return messageBundleUtil.getMessage("error.message.409");
    }
  };

  @Component
  public static class MessageInjector {
    @Autowired
    private MessageBundleUtil messageBundleUtil;

    @PostConstruct
    public void postConstruct() {
      for (EnumErrorType rt : EnumSet.allOf(EnumErrorType.class))
        rt.setMessageBundleUtil(messageBundleUtil);
    }
  }

  MessageBundleUtil messageBundleUtil;

  public void setMessageBundleUtil(MessageBundleUtil messageBundleUtil) {
    this.messageBundleUtil = messageBundleUtil;
  }

  private int errorCode;

  EnumErrorType(int errorCode) {
    this.errorCode = errorCode;
  }

  public int getErrorCode() {
    return this.errorCode;
  }

  public static String getErrorMsg(int code) {
    for(EnumErrorType type: EnumErrorType.values()) {
      if(type.getErrorCode() == code) {
        return type.getDefaultMsg();
      }
    }

    return "Break error";
  }

}
