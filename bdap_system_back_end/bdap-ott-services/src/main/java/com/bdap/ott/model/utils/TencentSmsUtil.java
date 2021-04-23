package com.bdap.ott.model.utils;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TencentSmsUtil {

  // 个人密钥ID
  @Value("${tencent.cloud.sms.secretId}")
  private String secretId;

  // 个人密钥
  @Value("${tencent.cloud.sms.secretKey}")
  private String secretKey;

  // 短信签名内容,
  @Value("${tencent.cloud.sms.sign}")
  private String sign;

  // 中国大陆手机号码头
  @Value("${tencent.cloud.sms.baseNumber}")
  private String baseNumber;

  // 腾讯短信API
  @Value("${tencent.cloud.sms.endPoint}")
  private String endPoint;

  // 腾讯短信API应用ID
  @Value("${tencent.cloud.sms.smsSdkAppid}")
  private String smsSdkAppid;

  // 腾讯短信发送验证码模板ID
  @Value("${tencent.cloud.sms.verifyPhoneTemplateID}")
  private String verifyPhoneTemplateID;

  // 腾讯短信重置密码模板ID
  @Value("${tencent.cloud.sms.updatePassTemplateID}")
  private String updatePassTemplateID;

  // 腾讯短信重置密码模板ID2
  @Value("${tencent.cloud.sms.updatePasswordTemplateID}")
  public String updatePasswordTemplateID;

  /**
   * 验证手机方法
   * 腾讯云发送6位数字验证码到指定手机
   *
   * @param phoneNumber 手机号码，用户用来接收短信验证码的手机号码，必填
   * @return resp 腾讯云官方返回的json
   */

  public String verifyPhone(String phoneNumber, String verifyCode) {

    System.out.println(secretId);
    System.out.println(secretKey);
    System.out.println(sign);
    System.out.println(baseNumber);
    System.out.println(endPoint);
    System.out.println(smsSdkAppid);
    System.out.println(verifyPhoneTemplateID);
    System.out.println(updatePasswordTemplateID);

    try {
      Credential cred = new Credential(secretId, secretKey);

      HttpProfile httpProfile = new HttpProfile();
      httpProfile.setEndpoint(endPoint);

      ClientProfile clientProfile = new ClientProfile();
      clientProfile.setHttpProfile(httpProfile);

      SmsClient client = new SmsClient(cred, "ap-guangzhou", clientProfile);

      SendSmsRequest req = new SendSmsRequest();
      String[] phoneNumberSet1 = {baseNumber + phoneNumber};
      req.setPhoneNumberSet(phoneNumberSet1);

      String[] templateParamSet1 = {verifyCode, "2"};
      req.setTemplateParamSet(templateParamSet1);

      req.setTemplateID(verifyPhoneTemplateID);
      req.setSessionContext(verifyCode);
      req.setSmsSdkAppid(smsSdkAppid);
      req.setSign(sign);

      SendSmsResponse resp = client.SendSms(req);
      return SendSmsResponse.toJsonString(resp);
    } catch (TencentCloudSDKException e) {
      return e.toString();
    }
  }
}
