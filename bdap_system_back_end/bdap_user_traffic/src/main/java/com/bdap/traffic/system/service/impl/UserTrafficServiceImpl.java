package com.bdap.traffic.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bdap.traffic.system.entity.UserTraffic;
import com.bdap.traffic.system.mapper.UserTrafficMapper;
import com.bdap.traffic.system.service.UserTrafficService;
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
public class UserTrafficServiceImpl extends ServiceImpl<UserTrafficMapper, UserTraffic> implements UserTrafficService {

  @Override
  public Integer createUserTraffic(UserTraffic userTraffic) {
    return baseMapper.insert(userTraffic);
  }

  @Override
  public Integer removeUserTraffic(List<Integer> Ids) {
    int delete = 0;
    for (Integer id : Ids) {
      int i = baseMapper.delete(new QueryWrapper<UserTraffic>().eq("id", id));
      delete += i;
    }
    return delete;
  }

  @Override
  public Page<UserTraffic> getUserTraffics(Integer page, Integer size, String like) {
    if ("".equals(like.trim())) {
      return baseMapper.selectPage(new Page<>(page, size), null);
    } else {
      return baseMapper.selectPage(new Page<>(page, size), new QueryWrapper<UserTraffic>().like("ip_address", like).or().like("region", like).or().like("visit_time", like));
    }
  }
}
