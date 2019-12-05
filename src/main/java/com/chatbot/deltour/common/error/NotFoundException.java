package com.chatbot.deltour.common.error;

import com.chatbot.deltour.common.enums.EnumErrorType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 404 에러 메시지
 *
 * @author wedul
 * @since 2018-12-22
 **/
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends Exception {

  public NotFoundException(String msg) {
    super(StringUtils.isBlank(msg) ? EnumErrorType.ERROR_404.getDefaultMsg() : msg);
  }

}
