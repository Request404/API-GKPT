package com.bdap.administration.system.service.impl;

import com.bdap.administration.system.entity.AdminAccount;
import com.bdap.administration.system.entity.AdminInfo;
import com.bdap.administration.system.service.AdminAccountService;
import com.bdap.administration.system.service.AdminCommonService;
import com.bdap.administration.system.service.AdminInfoService;
import com.bdap.common.exception.PermissionDeniedException;
import com.bdap.common.utils.Sql;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdminCommonServiceImpl implements AdminCommonService {

  private final AdminInfoService adminInfoService;

  private final AdminAccountService adminAccountService;

  @Override
  public Integer administrationAdd(AdminAccount adminAccount, Authentication authentication) {
    String datetime = Sql.Datetime();
    if (authentication.getAuthorities().toString().contains("root")) {
      if (adminAccount.getAdminInfo().getAuthority().contains("root")
          || adminAccount.getAdminInfo().getLevel() <= 0) {
        throw new PermissionDeniedException();
      } else {
        adminAccount.setOperateTime(datetime);
        adminAccount.getAdminInfo().setOperateTime(datetime);
        adminAccount.setOperate(authentication.getName());
        adminAccount.getAdminInfo().setOperate(authentication.getName());
        return this.adminAdd(adminAccount);
      }
    } else if (authentication.getAuthorities().toString().contains("region")) {
      AdminInfo administrationInfo = adminInfoService.getAdministrationInfo(authentication.getName());
      if (!administrationInfo.getRegion().equals(adminAccount.getAdminInfo().getRegion())
          || adminAccount.getAdminInfo().getAuthority().contains("root")
          || adminAccount.getAdminInfo().getAuthority().contains("region")
          || adminAccount.getAdminInfo().getLevel() <= 1) {
        throw new PermissionDeniedException();
      } else {
        adminAccount.setOperateTime(datetime);
        adminAccount.getAdminInfo().setOperateTime(datetime);
        adminAccount.setOperate(authentication.getName());
        adminAccount.getAdminInfo().setOperate(authentication.getName());
        return this.adminAdd(adminAccount);
      }
    } else if (authentication.getAuthorities().toString().contains("company")) {
      AdminInfo administrationInfo = adminInfoService.getAdministrationInfo(authentication.getName());
      if (!administrationInfo.getRegion().equals(adminAccount.getAdminInfo().getRegion())
          || !administrationInfo.getCompany().equals(adminAccount.getAdminInfo().getCompany())
          || adminAccount.getAdminInfo().getAuthority().contains("root")
          || adminAccount.getAdminInfo().getAuthority().contains("region")
          || adminAccount.getAdminInfo().getAuthority().contains("company")
          || adminAccount.getAdminInfo().getLevel() <= 2) {
        throw new PermissionDeniedException();
      } else {
        adminAccount.setOperateTime(datetime);
        adminAccount.getAdminInfo().setOperateTime(datetime);
        adminAccount.setOperate(authentication.getName());
        adminAccount.getAdminInfo().setOperate(authentication.getName());
        return this.adminAdd(adminAccount);
      }
    } else {
      throw new PermissionDeniedException();
    }
  }

  @Override
  public Integer administrationUpdate(AdminAccount adminAccount, Authentication authentication) {
    String datetime = Sql.Datetime();
    adminAccount.setOperateTime(datetime);
    adminAccount.getAdminInfo().setOperateTime(datetime);
    adminAccount.setOperate(authentication.getName());
    adminAccount.getAdminInfo().setOperate(authentication.getName());
    AdminInfo info = adminInfoService.getAdministrationInfo(authentication.getName());
    AdminInfo changeInfo = adminInfoService.getAdministrationInfo(adminAccount.getUser());
    if (authentication.getName().equals(adminAccount.getUser())) {
      if (info.getAuthority().equals(adminAccount.getAuthority())
          && info.getRegion().equals(adminAccount.getAdminInfo().getRegion())
          && info.getCompany().equals(adminAccount.getAdminInfo().getCompany())) {
        return this.adminUpdate(adminAccount);
      } else {
        throw new PermissionDeniedException();
      }
    } else {
      if ("root".equals(info.getAuthority())) {
        if (adminAccount.getAdminInfo().getAuthority().contains("root")
            || adminAccount.getAdminInfo().getLevel() <= 0) {
          throw new PermissionDeniedException();
        } else {
          return this.adminUpdate(adminAccount);
        }
      } else if ("region".equals(info.getAuthority())) {
        if (!info.getRegion().equals(changeInfo.getRegion())
            || !info.getRegion().equals(adminAccount.getAdminInfo().getRegion())
            || adminAccount.getAdminInfo().getAuthority().contains("root")
            || adminAccount.getAdminInfo().getAuthority().contains("region")
            || adminAccount.getAdminInfo().getLevel() <= 1) {
          throw new PermissionDeniedException();
        } else {
          return this.adminUpdate(adminAccount);
        }
      } else if ("company".equals(info.getAuthority())) {
        if (!info.getRegion().equals(changeInfo.getRegion())
            || !info.getCompany().equals(changeInfo.getCompany())
            || !info.getRegion().equals(adminAccount.getAdminInfo().getRegion())
            || !info.getCompany().equals(adminAccount.getAdminInfo().getCompany())
            || adminAccount.getAdminInfo().getAuthority().contains("root")
            || adminAccount.getAdminInfo().getAuthority().contains("region")
            || adminAccount.getAdminInfo().getAuthority().contains("company")
            || adminAccount.getAdminInfo().getLevel() <= 2) {
          throw new PermissionDeniedException();
        } else {
          return this.adminUpdate(adminAccount);
        }
      } else {
        throw new PermissionDeniedException();
      }
    }
  }

  @Override
  public Boolean administrationRemove(String user, Authentication authentication) {
    AdminInfo removeInfo = adminInfoService.getAdministrationInfo(user);
    if (authentication.getAuthorities().toString().contains("root")) {
      if (removeInfo.getLevel() <= 0) {
        throw new PermissionDeniedException();
      } else {
        return this.adminRemove(user);
      }
    } else if (authentication.getAuthorities().toString().contains("region")) {
      AdminInfo OperateInfo = adminInfoService.getAdministrationInfo(authentication.getName());
      if (removeInfo.getLevel() <= 1 || !removeInfo.getRegion().equals(OperateInfo.getRegion())) {
        throw new PermissionDeniedException();
      } else {
        return this.adminRemove(user);
      }
    } else if (authentication.getAuthorities().toString().contains("company")) {
      AdminInfo OperateInfo = adminInfoService.getAdministrationInfo(authentication.getName());
      if (removeInfo.getLevel() <= 2
          || !removeInfo.getRegion().equals(OperateInfo.getRegion())
          || !removeInfo.getCompany().equals(OperateInfo.getCompany())) {
        throw new PermissionDeniedException();
      } else {
        return this.adminRemove(user);
      }
    } else {
      throw new PermissionDeniedException();
    }
  }

  @Transactional
  public Integer adminAdd(AdminAccount adminAccount) {
    Integer account = adminAccountService.addAdministrationAccount(adminAccount);
    Integer info = adminInfoService.addAdministrationInfo(adminAccount.getAdminInfo());
    return (0 != account && 0 != info) ? 1 : 0;
  }


  public Integer adminUpdate(AdminAccount adminAccount) {
    Integer account = adminAccountService.updateAdministrationAccount(adminAccount);
    Integer info = adminInfoService.updateAdministrationInfo(adminAccount.getAdminInfo());
    return (0 != account && 0 != info) ? 1 : 0;
  }

  @Transactional
  public Boolean adminRemove(String user) {
    boolean account = adminAccountService.removeAdministrationAccount(user);
    boolean info = adminInfoService.removeAdministrationInfo(user);
    return account && info;
  }
}

