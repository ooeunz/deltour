package com.chatbot.deltour.common.error;

import com.chatbot.deltour.common.enums.EnumErrorType;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * wedulpos
 *
 * @author wedul
 * @since 2018-12-23
 **/
@Data
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends RuntimeException {

  public InternalServerException(String msg) {
    super(StringUtils.isBlank(msg) ? EnumErrorType.ERROR_500.getDefaultMsg() : msg);
  }

}
