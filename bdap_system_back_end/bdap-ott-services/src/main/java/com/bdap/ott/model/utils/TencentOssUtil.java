package com.bdap.ott.model.utils;


import com.tencent.cloud.CosStsClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.TreeMap;

@Configuration
public class TencentOssUtil {

  // 个人密钥ID
  @Value("${tencent.cloud.oss.secretId}")
  private String secretId;
  // 个人密钥
  @Value("${tencent.cloud.oss.secretKey}")
  private String secretKey;

  // 令牌有效时间
  @Value("${tencent.cloud.oss.durationSeconds}")
  private Integer durationSeconds;

  // 对象存储桶
  @Value("${tencent.cloud.oss.bucket}")
  private String bucket;

  // 腾讯云区域
  @Value("${tencent.cloud.oss.region}")
  private String region;

  // 允许的前缀
  @Value("${tencent.cloud.oss.allowPrefix}")
  private String allowPrefix;

  public String TencentOssAuthentication() {
    TreeMap<String, Object> config = new TreeMap<String, Object>();

    try {
      config.put("SecretId", secretId);
      config.put("SecretKey", secretKey);
      // 临时密钥有效时长，单位是秒，默认1800秒，目前主账号最长2小时（即7200秒），子账号最长36小时（即129600秒）
      config.put("durationSeconds", durationSeconds);
      config.put("bucket", bucket);
      config.put("region", region);
      // 这里改成允许的路径前缀，可以根据自己网站的用户登录态判断允许上传的具体路径，例子：a.jpg 或者 a/* 或者 * 。
      // 如果填写了“*”，将允许用户访问所有资源；除非业务需要，否则请按照最小权限原则授予用户相应的访问权限范围。
      config.put("allowPrefix", allowPrefix);
      // 密钥的权限列表。简单上传、表单上传和分片上传需要以下的权限，其他权限列表请看 https://cloud.tencent.com/document/product/436/31923
      String[] allowActions = new String[]{
          // 简单上传
          "name/cos:PutObject",
          // 表单上传、小程序上传
          "name/cos:PostObject",
          // 分片上传
          /*"name/cos:InitiateMultipartUpload",
          "name/cos:ListMultipartUploads",
          "name/cos:ListParts",
          "name/cos:UploadPart",
          "name/cos:CompleteMultipartUpload"*/
      };
      config.put("allowActions", allowActions);
      return CosStsClient.getCredential(config).toString();
      //成功返回临时密钥信息，如下打印密钥信息
    } catch (Exception e) {
      //失败抛出异常
      throw new IllegalArgumentException("no valid secret !");
    }
  }

}

