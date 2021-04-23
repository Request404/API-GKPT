package com.bdap.ott.model.service.impl;

import com.bdap.ott.model.service.OssService;
import com.bdap.ott.model.utils.TencentOssUtil;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OssServiceImpl implements OssService {

  private final TencentOssUtil tencentOssUtil;

  @Override
  public String getOssToken() {
    return tencentOssUtil.TencentOssAuthentication();
  }
}
