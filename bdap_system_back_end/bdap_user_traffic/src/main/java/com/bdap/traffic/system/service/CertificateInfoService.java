package com.bdap.traffic.system.service;

import com.bdap.traffic.system.entity.CertificateInfo;
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
public interface CertificateInfoService extends IService<CertificateInfo> {

  List<CertificateInfo> getCertificates();

  Integer createCertificate(CertificateInfo certificateInfo);

  Integer updateCertificate(CertificateInfo certificateInfo);

  Integer removeCertificate(Integer id);

}
