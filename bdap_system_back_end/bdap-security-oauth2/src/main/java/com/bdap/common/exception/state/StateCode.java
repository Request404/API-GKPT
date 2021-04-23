package com.bdap.common.exception.state;

public enum StateCode {
  UN_KNOW_EXCEPTION(10000, "系统未知异常"),
  FEIGN_INVOKE_EXCEPTION(10001, "该方法仅限内部调用"),

  ADMINISTRATION_VALID_EXCEPTION(11001, "管理员模块数据校验异常"),
  ADMINISTRATION_NULL_POINTER_EXCEPTION(11002, "管理员模块数据封装数据空指针异常"),
  ADMINISTRATION_USER_EXIST_EXCEPTION(11003, "管理员信息异常：该管理员用户名已存在"),
  ADMINISTRATION_USER_NOT_FOUND_EXCEPTION(11004, "管理员信息异常：该管理员用户没有找到"),
  ADMINISTRATION_PERMISSION_DENIED_EXCEPTION(11005, "请求被拒绝，权限不足，内容不允许访问"),
  ADMINISTRATION_CODE_INVALID_EXCEPTION(11006, "数据异常：该推荐码无效"),
  ADMINISTRATION_CERTIFICATE_NOT_FOUND_EXCEPTION(11010, "用户报名异常：该证书不存在"),

  USER_OPERATE_VALID_EXCEPTION(12001, "用户模块数据校验异常"),
  USER_OPERATE_NULL_POINTER_EXCEPTION(12002, "用户模块数据封装数据空指针异常"),
  USER_EXIST_EXCEPTION(12003, "用户信息异常：该用户已存在"),
  USER_NOT_FOUND_EXCEPTION(12004, "用户信息异常：该用户没有找到"),
  USER_LOGIN_EXCEPTION(12005, "用户登陆失败：用户名、密码不匹配"),
  USER_REGISTRATION_EXCEPTION(12006, "用户报名异常：该用户已报名该课程，不可重复报名"),
  USER_NOT_REGISTRATION_EXCEPTION(12007, "用户报名异常：找不到该用户报名信息"),
  USER_CERTIFICATE_NOT_FOUND_EXCEPTION(12008, "用户报名异常：该证书不存在"),

  TRAFFIC_VALID_EXCEPTION(13001, "流量及其他模块数据校验异常"),
  TRAFFIC_NULL_POINTER_EXCEPTION(13002, "流量及其他模块数据封装数据空指针异常"),
  TRAFFIC_CERTIFICATE_EXIST_EXCEPTION(13011, "该证书信息存在"),
  TRAFFIC_CERTIFICATE_NOT_FOUND_EXCEPTION(13012, "该证书信息不存在"),
  TRAFFIC_LECTURE_NOT_FOUND_EXCEPTION(13013, "讲师不存在");

  private final int code;
  private final String msg;

  StateCode(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public int getCode() {
    return code;
  }

  public String getMsg() {
    return msg;
  }
}
