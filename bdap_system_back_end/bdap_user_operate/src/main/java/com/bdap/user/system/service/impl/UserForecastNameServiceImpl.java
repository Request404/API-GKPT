package com.bdap.user.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bdap.user.system.entity.UserForecastName;
import com.bdap.user.system.mapper.UserForecastNameMapper;
import com.bdap.user.system.service.UserForecastNameService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author GuoKeGD
 * @since 2020-12-01
 */
@Service
public class UserForecastNameServiceImpl extends ServiceImpl<UserForecastNameMapper, UserForecastName> implements UserForecastNameService {

  @Override
  public Page<UserForecastName> getUserForecastName(Integer page, Integer size) {
    return baseMapper.selectPage(new Page<>(page,size),null);
  }

  @Override
  public Integer saveUserForecastName(UserForecastName userForecastName) {
    if (this.userForecastNameIsExist(userForecastName.getPhone())){
      return -1;
    } else {
      return baseMapper.insert(userForecastName);
    }
  }

  @Override
  public Integer removeUserForecastName(List<Integer> Ids) {
    int i = 0;
    for (Integer id : Ids) {
      i += baseMapper.deleteById(id);
    }
    return i;
  }

  private Boolean userForecastNameIsExist(String phone) {
    return baseMapper.selectOne(new QueryWrapper<UserForecastName>().eq("phone",phone))!=null;
  }
}
