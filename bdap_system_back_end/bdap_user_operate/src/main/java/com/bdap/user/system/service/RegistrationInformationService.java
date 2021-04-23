package com.bdap.user.system.service;

import com.bdap.user.system.entity.RegistrationInformation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author GuoKeGD
 * @since 2020-12-01
 */
public interface RegistrationInformationService extends IService<RegistrationInformation> {

  Integer webSiteUserRegistration(RegistrationInformation registrationInformation);

  Integer openRegistration(RegistrationInformation registrationInformation);

  Integer updateRegistration(RegistrationInformation registrationInformation);

  Integer updateOpenRegionRegistration(RegistrationInformation registrationInformation);

  List<RegistrationInformation> getUserRegistration(String phone, String userCode);

  List<RegistrationInformation> getRootRegistration(String like);

  List<RegistrationInformation> getRegionRegistration(String region, String like);

  List<RegistrationInformation> getCompanyRegistration(String region, String company, String like);

  List<RegistrationInformation> getBaseRegistration(String reference, String like);

  RegistrationInformation getReferenceRegistration(Integer id, String reference);

  Integer removeRegistration(Integer id);

  Integer removeOpenRegistration(Integer id, String reference);

}
