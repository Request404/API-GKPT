package com.bdap.administration.system.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bdap.administration.system.entity.AdminInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author GuoKeGD
 * @since 2020-12-01
 */
@Service
public interface AdminInfoService extends IService<AdminInfo> {

  List<AdminInfo> rootListTree();

  List<AdminInfo> regionListTree(String region, Authentication authentication);

  AdminInfo companyListTree(String region,String company, Authentication authentication);

  Integer addAdministrationInfo(AdminInfo adminInfo);

  Integer updateAdministrationInfo(AdminInfo adminInfo);

  Boolean removeAdministrationInfo(String user);

  AdminInfo getAdministrationInfo(String user);

  AdminInfo getAdministrationInfoByAdminCode(String adminCode);
  ;

}
