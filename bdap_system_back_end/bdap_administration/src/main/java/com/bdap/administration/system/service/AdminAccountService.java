package com.bdap.administration.system.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bdap.administration.system.entity.AdminAccount;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bdap.administration.system.entity.AdminInfo;
import com.bdap.common.dto.BadpUserDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author GuoKeGD
 * @since 2020-12-01
 */
@Service
public interface AdminAccountService extends IService<AdminAccount> {

  String generatorVerifyCode(String Number);

  Integer administrationPasswordAlter(String user, String password);

  BadpUserDto getAdministrationByUser(String user);

  Integer addAdministrationAccount(AdminAccount adminAccount);

  Integer updateAdministrationAccount(AdminAccount adminAccount);

  Boolean removeAdministrationAccount(String user);

//  AdminAccount getAdministrationAccount(String user);


}
