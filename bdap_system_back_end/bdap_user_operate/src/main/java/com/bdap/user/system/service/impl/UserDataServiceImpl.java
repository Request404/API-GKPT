package com.bdap.user.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bdap.common.exception.UserExistException;
import com.bdap.common.exception.UserLoginException;
import com.bdap.common.exception.UserNotFoundException;
import com.bdap.common.utils.Sql;
import com.bdap.user.system.common.annotation.SystemLog;
import com.bdap.user.system.entity.UserData;
import com.bdap.user.system.entity.UserReturn;
import com.bdap.user.system.mapper.UserDataMapper;
import com.bdap.user.system.service.UserDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author GuoKeGD
 * @since 2020-12-01
 */
@Service
@CacheConfig(cacheManager = "temporarilyCacheManager")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDataServiceImpl extends ServiceImpl<UserDataMapper, UserData> implements UserDataService {

  private final PasswordEncoder passwordEncoder;

  @Override
  @SystemLog("生成验证码")
  @Cacheable(value = "verifyCode", key = "#Number")
  public String generatorVerifyCode(String Number) {
    return Sql.generatorNumber(6);
  }

  @Override
  public Integer userRegistration(UserData userData) {
    if (this.UserIsExist(userData.getPhone())) {
      throw new UserExistException();
    } else {
      String USER_CODE = UUID.randomUUID().toString();
      userData.setUserCode(this.userCodeIsRepeat(USER_CODE));
      userData.setPassword(passwordEncoder.encode(userData.getPassword()));
      userData.setOperateTime(Sql.Datetime());
      userData.setOperate("user");
      return baseMapper.insert(userData);
    }
  }

  @Override
  public Integer userUpdate(UserData userData) {
    if (!this.UserIsExist(userData.getPhone())) {
      throw new UserNotFoundException();
    } else {
      userData.setOperate("用户");
      userData.setOperateTime(Sql.Datetime());
      return baseMapper.update(userData, new UpdateWrapper<UserData>().eq("phone", userData.getPhone()));
    }
  }

  @Override
  public List<UserData> getAllUser() {
    return baseMapper.selectList(null);
  }

  @Override
  public UserReturn userLogin(UserData userData) {
    if (this.queryUserState(userData.getPhone())) {
      UserData user = baseMapper.selectOne(new QueryWrapper<UserData>().eq("phone", userData.getPhone()));
      if (passwordEncoder.matches(userData.getPassword(), user.getPassword())) {
        return new UserReturn(user.getPhone(), user.getUserCode(), 1);
      } else {
        throw new UserLoginException();
      }
    } else {
      throw new UserNotFoundException();
    }
  }

  @Override
  public Boolean userMatch(String phone, String userCode) {
    if (this.queryUserState(phone)) {
      return baseMapper.selectOne(new QueryWrapper<UserData>().eq("phone", phone).eq("user_code", userCode)) != null;
    } else {
      throw new UserNotFoundException();
    }
  }

  private Boolean UserIsExist(String phone) {
    return baseMapper.selectOne(new QueryWrapper<UserData>().eq("phone", phone)) != null;
  }

  private String userCodeIsRepeat(String userCode) {
    if (baseMapper.selectOne(new QueryWrapper<UserData>().eq("user_code", userCode)) != null) {
      String uuid = UUID.randomUUID().toString();
      return this.userCodeIsRepeat(uuid);
    } else {
      return userCode;
    }
  }

  private Boolean queryUserState(String phone) {
    UserData user = baseMapper.selectOne(new QueryWrapper<UserData>().eq("phone", phone));
    if (user != null) {
      return 1 == user.getState();
    } else {
      throw new UserNotFoundException();
    }
  }

}
