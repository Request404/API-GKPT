package com.bdap.ott.model.service;

public interface SmsService {
  String sendVerifyCode(String number, String code);
}
