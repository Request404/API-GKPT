package com.bdap.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Sql {

  //生成标准日期
  public static String Datetime() {
    return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
  }

  //生成随机字符串数字,i表示生成的长度
  public static String generatorNumber(Integer i) {
    String hashCode = String.valueOf(Math.abs(UUID.randomUUID().toString().hashCode()));
    return hashCode.substring(hashCode.length() - i);
  }
}
