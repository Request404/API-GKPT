package com.bdap.user.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bdap.user.system.entity.UserCommit;
import com.bdap.user.system.mapper.UserCommitMapper;
import com.bdap.user.system.service.UserCommitService;
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
public class UserCommitServiceImpl extends ServiceImpl<UserCommitMapper, UserCommit> implements UserCommitService {

  @Override
  public Page<UserCommit> getUserCommit(Integer page,Integer size) {
    return baseMapper.selectPage(new Page<>(page,size),null);
  }

  @Override
  public Integer saveUserCommit(UserCommit userCommit) {
    if (this.commitUserIsExist(userCommit.getPhone())) {
      return -1;
    } else {
      return baseMapper.insert(userCommit);
    }
  }

  @Override
  public Integer removeUserCommit(List<Integer> Ids) {
    int i = 0;
    for (Integer id : Ids) {
      i += baseMapper.deleteById(id);
    }
    return i;
  }

  private Boolean commitUserIsExist(String phone) {
    return baseMapper.selectOne(new QueryWrapper<UserCommit>().eq("phone", phone)) != null;
  }

}
