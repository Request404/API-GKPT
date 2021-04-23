package com.bdap.ott.model.common.exception;

import com.bdap.common.exception.*;
import com.bdap.common.exception.state.StateCode;
import com.bdap.common.utils.Q;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages = "com.bdap.ott.model.controller")
public class BdapExceptionHandler {

  @ExceptionHandler(value = FeignInvokeException.class)
  public Q handlerFeignInvokeException() {
    return Q.error(StateCode.FEIGN_INVOKE_EXCEPTION.getCode(), StateCode.FEIGN_INVOKE_EXCEPTION.getMsg());
  }

  @ExceptionHandler(value = Throwable.class)
  public Q handleException(Throwable throwable) {
    return Q.error();
  }
}
