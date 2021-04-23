package com.bdap.user.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bdap.common.exception.UserLoginException;
import com.bdap.common.exception.UserNotRegistrationException;
import com.bdap.common.exception.UserRegistrationException;
import com.bdap.common.utils.Sql;
import com.bdap.user.system.entity.RegistrationInformation;
import com.bdap.user.system.mapper.RegistrationInformationMapper;
import com.bdap.user.system.service.RegistrationInformationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bdap.user.system.service.UserDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author GuoKeGD
 * @since 2020-12-01
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RegistrationInformationServiceImpl extends ServiceImpl<RegistrationInformationMapper, RegistrationInformation> implements RegistrationInformationService {

  private final UserDataService userDataService;

  @Override
  public Integer webSiteUserRegistration(RegistrationInformation registrationInformation) {
    if (userDataService.userMatch(registrationInformation.getUserPhone(), registrationInformation.getUserCode())) {
      if (this.userIsRegistration(registrationInformation.getIdentityNumber(), registrationInformation.getCertificateName(), registrationInformation.getCertificateGrade())) {
        throw new UserRegistrationException();
      } else {
        registrationInformation.setOperateTime(Sql.Datetime());
        registrationInformation.setOperate("网站报名");
        return baseMapper.insert(registrationInformation);
      }
    } else {
      throw new UserLoginException();
    }
  }

  @Override
  public Integer openRegistration(RegistrationInformation registrationInformation) {
    if (this.userIsRegistration(registrationInformation.getIdentityNumber(), registrationInformation.getCertificateName(), registrationInformation.getCertificateGrade())) {
      throw new UserRegistrationException();
    } else {
      registrationInformation.setOperateTime(Sql.Datetime());
      return baseMapper.insert(registrationInformation);
    }
  }

  @Override
  public Integer updateRegistration(RegistrationInformation registrationInformation) {
    if (baseMapper.selectById(registrationInformation.getId()) != null) {
      registrationInformation.setOperateTime(Sql.Datetime());
      return baseMapper.updateById(registrationInformation);
    } else {
      throw new UserNotRegistrationException();
    }
  }

  @Override
  public Integer updateOpenRegionRegistration(RegistrationInformation registrationInformation) {
    if (this.getReferenceRegistration(registrationInformation.getId(), registrationInformation.getReference()) != null) {
      registrationInformation.setOperateTime(Sql.Datetime());
      return baseMapper.update(registrationInformation, new UpdateWrapper<RegistrationInformation>().eq("id", registrationInformation.getId()).eq("reference", registrationInformation.getReference()));
    } else {
      throw new UserNotRegistrationException();
    }
  }

  @Override
  public List<RegistrationInformation> getUserRegistration(String phone, String userCode) {
    return baseMapper.selectList(new QueryWrapper<RegistrationInformation>().eq("user_phone", phone).eq("user_code", userCode));
  }

  @Override
  public List<RegistrationInformation> getRootRegistration(String like) {
    if ("".equals(like.trim())) {
      return baseMapper.selectList(null);
    } else {
      return baseMapper.selectList(new QueryWrapper<RegistrationInformation>()
          .like("name", like).or()
          .like("identity_number", like).or()
          .like("certificate_name", like).or()
          .like("region", like).or()
          .like("company", like).or()
          .like("reference", like).or()
          .like("user_phone", like));
    }
  }

  @Override
  public List<RegistrationInformation> getRegionRegistration(String region, String like) {

    if ("".equals(like.trim())) {
      return baseMapper.selectList(new QueryWrapper<RegistrationInformation>().eq("region", region));
    } else {
      return baseMapper.selectList(new QueryWrapper<RegistrationInformation>()
          .eq("region", region)
          .like("name", like).or()
          .like("identity_number", like).or()
          .like("certificate_name", like).or()
          .like("company", like).or()
          .like("reference", like).or()
          .like("user_phone", like));
    }
  }

  @Override
  public List<RegistrationInformation> getCompanyRegistration(String region, String company, String like) {

    if ("".equals(like.trim())) {
      return baseMapper.selectList(new QueryWrapper<RegistrationInformation>().eq("region", region).eq("company", company));
    } else {
      return baseMapper.selectList(new QueryWrapper<RegistrationInformation>()
          .eq("region", region)
          .eq("company", company)
          .like("name", like).or()
          .like("identity_number", like).or()
          .like("certificate_name", like).or()
          .like("reference", like).or()
          .like("user_phone", like));
    }
  }

  @Override
  public List<RegistrationInformation> getBaseRegistration(String reference, String like) {

    if ("".equals(like.trim())) {
      return baseMapper.selectList(new QueryWrapper<RegistrationInformation>().eq("reference", reference));
    } else {
      return baseMapper.selectList(new QueryWrapper<RegistrationInformation>()
          .eq("reference", reference)
          .eq("region", like)
          .eq("company", like)
          .eq("base", like)
          .like("name", like).or()
          .like("identity_number", like).or()
          .like("certificate_name", like).or()
          .like("user_phone", like));
    }
  }

  @Override
  public RegistrationInformation getReferenceRegistration(Integer id, String reference) {
    return baseMapper.selectOne(new QueryWrapper<RegistrationInformation>().eq("id", id).eq("reference", reference));
  }

  @Override
  public Integer removeRegistration(Integer id) {
    if (baseMapper.selectById(id) != null) {
      return baseMapper.deleteById(id);
    } else {
      throw new UserNotRegistrationException();
    }
  }

  @Override
  public Integer removeOpenRegistration(Integer id, String reference) {
    if (this.getReferenceRegistration(id, reference) != null) {
      return baseMapper.deleteById(id);
    } else {
      throw new UserNotRegistrationException();
    }
  }

  private Boolean userIsRegistration(String IdNumber, String certificateName, String certificateGrade) {
    return baseMapper.selectOne(new QueryWrapper<RegistrationInformation>().eq("identity_number", IdNumber).eq("certificate_name", certificateName).eq("certificate_grade", certificateGrade)) != null;
  }
}
