package com.chatbot.deltour.common.error;

import com.chatbot.deltour.common.enums.EnumErrorType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * wedulpos
 *
 * @author wedul
 * @since 2018-12-22
 **/
@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends Exception {
  public ConflictException(String msg) {
    super(StringUtils.isBlank(msg) ? EnumErrorType.ERROR_409.getDefaultMsg() : msg);
  }
}
