package com.bdap.user.system.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bdap.user.system.entity.UserCommit;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author GuoKeGD
 * @since 2020-12-01
 */
public interface UserCommitService extends IService<UserCommit> {

  Page<UserCommit> getUserCommit(Integer page, Integer size);

  Integer saveUserCommit(UserCommit userCommit);

  Integer removeUserCommit(List<Integer> Ids);

}
