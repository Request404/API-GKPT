package com.bdap.traffic.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bdap.common.exception.CertificateExistException;
import com.bdap.common.exception.CertificateNotFoundException;
import com.bdap.traffic.system.entity.CertificateInfo;
import com.bdap.traffic.system.mapper.CertificateInfoMapper;
import com.bdap.traffic.system.service.CertificateInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class CertificateInfoServiceImpl extends ServiceImpl<CertificateInfoMapper, CertificateInfo> implements CertificateInfoService {

  @Override
  public List<CertificateInfo> getCertificates() {
    return baseMapper.selectList(null);
  }

  @Override
  public Integer createCertificate(CertificateInfo certificateInfo) {
    if (this.certificateIsExist(certificateInfo)) {
      throw new CertificateExistException();
    } else {
      return baseMapper.insert(certificateInfo);
    }
  }

  @Override
  public Integer updateCertificate(CertificateInfo certificateInfo) {
    if (this.certificateIsExist(certificateInfo)) {
      return baseMapper.update(certificateInfo, new UpdateWrapper<CertificateInfo>().eq("id", certificateInfo.getId()));
    } else {
      throw new CertificateNotFoundException();
    }
  }

  @Override
  public Integer removeCertificate(Integer id) {
    return baseMapper.delete(new QueryWrapper<CertificateInfo>().eq("id", id));
  }

  private Boolean certificateIsExist(CertificateInfo certificateInfo) {
    return baseMapper.selectOne(new QueryWrapper<CertificateInfo>().eq("certificate_name", certificateInfo.getCertificateName()).eq("certificate_grade", certificateInfo.getCertificateGrade())) != null;
  }
}
