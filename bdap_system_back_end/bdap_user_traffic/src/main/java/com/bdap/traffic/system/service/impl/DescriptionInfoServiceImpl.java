package com.bdap.traffic.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bdap.traffic.system.entity.DescriptionInfo;
import com.bdap.traffic.system.mapper.DescriptionInfoMapper;
import com.bdap.traffic.system.service.DescriptionInfoService;
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
public class DescriptionInfoServiceImpl extends ServiceImpl<DescriptionInfoMapper, DescriptionInfo> implements DescriptionInfoService {

  @Override
  public Boolean descriptionInExist(Integer descriptionId) {
    return baseMapper.selectOne(new QueryWrapper<DescriptionInfo>().eq("id", descriptionId)) != null;
  }

  @Override
  public List<DescriptionInfo> getDescriptions() {
    return baseMapper.selectList(null);
  }

  @Override
  public Integer createDescription(DescriptionInfo descriptionInfo) {
    return baseMapper.insert(descriptionInfo);
  }

  @Override
  public Integer updateDescription(DescriptionInfo descriptionInfo) {
    return baseMapper.update(descriptionInfo, new QueryWrapper<DescriptionInfo>().eq("id", descriptionInfo.getId()));
  }

  @Override
  public Boolean removeDescription(Integer id) {
    return baseMapper.deleteById(id) != 0;
  }
}
