package com.bdap.administration.system.service;

import com.bdap.administration.system.entity.AdminAccount;
import org.springframework.security.core.Authentication;

public interface AdminCommonService {

  Integer administrationAdd(AdminAccount adminAccount, Authentication authentication);

  Integer administrationUpdate(AdminAccount adminAccount, Authentication authentication);

  Boolean administrationRemove(String user,Authentication authentication);
}
