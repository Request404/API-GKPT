package com.bdap.administration.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bdap.administration.system.common.annotation.SystemLog;
import com.bdap.administration.system.entity.AdminAccount;
import com.bdap.administration.system.mapper.AdminAccountMapper;
import com.bdap.administration.system.service.AdminAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bdap.common.dto.BadpUserDto;
import com.bdap.common.exception.UserExistException;
import com.bdap.common.exception.UserNotFoundException;
import com.bdap.common.utils.Sql;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
public class AdminAccountServiceImpl extends ServiceImpl<AdminAccountMapper, AdminAccount> implements AdminAccountService {

  private final PasswordEncoder passwordEncoder;

  @Override
  @SystemLog("生成验证码")
  @Cacheable(value = "verifyCode", key = "#Number")
  public String generatorVerifyCode(String Number) {
    return Sql.generatorNumber(6);
  }

  @Override
  public Integer administrationPasswordAlter(String user, String password) {
    if (this.AdministrationIsExist(user)) {
      AdminAccount adminAccount = new AdminAccount();
      adminAccount.setPassword(passwordEncoder.encode(password));
      adminAccount.setOperate(user);
      adminAccount.setOperateTime(Sql.Datetime());
      return baseMapper.update(adminAccount, new UpdateWrapper<AdminAccount>().eq("user", user));
    } else {
      throw new UserNotFoundException();
    }
  }

  @Override
  public BadpUserDto getAdministrationByUser(String user) {
    AdminAccount account = baseMapper.selectOne(new QueryWrapper<AdminAccount>().eq("user", user));
    BadpUserDto userDto = new BadpUserDto();
    if (account != null) {
      userDto.setUsername(account.getUser());
      userDto.setPassword(account.getPassword());
      userDto.setAuthority(account.getAuthority());
    } else {
      userDto.setUsername("default_user");
      userDto.setPassword(passwordEncoder.encode("%DDD%CCC%DDD%554%%001"));
      userDto.setAuthority("");
    }
    return userDto;
  }

  @Override
  public Integer addAdministrationAccount(AdminAccount adminAccount) {
    if (this.AdministrationIsExist(adminAccount.getUser())) {
      throw new UserExistException();
    } else {
      return baseMapper.insert(adminAccount);
    }
  }

  @Override
  public Integer updateAdministrationAccount(AdminAccount adminAccount) {
    if (this.AdministrationIsExist(adminAccount.getUser())) {
      return baseMapper.update(adminAccount, new UpdateWrapper<AdminAccount>().eq("user", adminAccount.getUser()));
    } else {
      throw new UserNotFoundException();
    }
  }

  @Override
  public Boolean removeAdministrationAccount(String user) {
    if (this.AdministrationIsExist(user)) {
      return baseMapper.delete(new QueryWrapper<AdminAccount>().eq("user", user)) != 0;
    } else {
      throw new UserNotFoundException();
    }
  }


  private Boolean AdministrationIsExist(String user) {
    return baseMapper.selectOne(new QueryWrapper<AdminAccount>().eq("user", user)) != null;
  }

}
