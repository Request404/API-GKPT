package com.bdap.ott.model.service.impl;

import com.bdap.ott.model.service.SmsService;
import com.bdap.ott.model.utils.TencentSmsUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SmsServiceImpl implements SmsService {

  private final TencentSmsUtil tencentSmsUtil;

  @Override
  public String sendVerifyCode(String number, String code) {
    return tencentSmsUtil.verifyPhone(number, code);
  }
}
