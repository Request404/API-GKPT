package com.bdap.administration.system.common.exception;

import com.bdap.common.exception.*;
import com.bdap.common.exception.state.StateCode;
import com.bdap.common.utils.Q;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@Slf4j
@RestControllerAdvice(basePackages = "com.bdap.administration.system.controller")
public class BdapExceptionHandler {

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public Q handleValidException(MethodArgumentNotValidException e) {

    BindingResult result = e.getBindingResult();

    HashMap<String, String> errorMap = new HashMap<>();

    result.getFieldErrors().forEach(fieldError -> {
      errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
    });

    return Q.error(StateCode.ADMINISTRATION_VALID_EXCEPTION.getCode(), StateCode.ADMINISTRATION_VALID_EXCEPTION.getMsg()).put("data", errorMap);
  }

  @ExceptionHandler(value = ArgumentNotValidException.class)
  public Q handleValidException(ArgumentNotValidException e) {
    return Q.error(StateCode.ADMINISTRATION_VALID_EXCEPTION.getCode(), StateCode.ADMINISTRATION_VALID_EXCEPTION.getMsg()).put("data", "子对象参数校验异常");
  }

  @ExceptionHandler(value = NullPointerException.class)
  public Q handlerNullPointerException(NullPointerException e) {
    return Q.error(StateCode.ADMINISTRATION_NULL_POINTER_EXCEPTION.getCode(), StateCode.ADMINISTRATION_NULL_POINTER_EXCEPTION.getMsg());
  }

  @ExceptionHandler(value = UserExistException.class)
  public Q handlerUserExistException() {
    return Q.error(StateCode.ADMINISTRATION_USER_EXIST_EXCEPTION.getCode(), StateCode.ADMINISTRATION_USER_EXIST_EXCEPTION.getMsg());
  }

  @ExceptionHandler(value = UserNotFoundException.class)
  public Q handlerUserNotFoundException() {
    return Q.error(StateCode.ADMINISTRATION_USER_NOT_FOUND_EXCEPTION.getCode(), StateCode.ADMINISTRATION_USER_NOT_FOUND_EXCEPTION.getMsg());
  }

  @ExceptionHandler(value = PermissionDeniedException.class)
  public Q handlerPermissionDeniedException() {
    return Q.error(StateCode.ADMINISTRATION_PERMISSION_DENIED_EXCEPTION.getCode(), StateCode.ADMINISTRATION_PERMISSION_DENIED_EXCEPTION.getMsg());
  }

  @ExceptionHandler(value = CertificateNotFoundException.class)
  public Q handlerCertificateNotFoundException() {
    return Q.error(StateCode.ADMINISTRATION_CERTIFICATE_NOT_FOUND_EXCEPTION.getCode(), StateCode.ADMINISTRATION_CERTIFICATE_NOT_FOUND_EXCEPTION.getMsg());
  }

  @ExceptionHandler(value = FeignInvokeException.class)
  public Q handlerFeignInvokeException() {
    return Q.error(StateCode.FEIGN_INVOKE_EXCEPTION.getCode(), StateCode.FEIGN_INVOKE_EXCEPTION.getMsg());
  }


//  @ExceptionHandler(value = Throwable.class)
//  public Q handleException(Throwable throwable){
//    return Q.error();
//  }
}
