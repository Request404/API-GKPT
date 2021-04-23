package com.bdap.user.system.service;

import com.bdap.user.system.entity.UserData;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bdap.user.system.entity.UserReturn;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author GuoKeGD
 * @since 2020-12-01
 */
public interface UserDataService extends IService<UserData> {

  String generatorVerifyCode(String number);

  Integer userRegistration(UserData userData);

  Integer userUpdate(UserData userData);

  List<UserData> getAllUser();

  UserReturn userLogin(UserData userData);

  Boolean userMatch(String phone, String userCode);
}
