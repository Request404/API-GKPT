package com.bdap.user.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bdap.user.system.entity.UserForecastName;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author GuoKeGD
 * @since 2020-12-01
 */
public interface UserForecastNameService extends IService<UserForecastName> {

  Page<UserForecastName> getUserForecastName(Integer page,Integer size);

  Integer saveUserForecastName(UserForecastName userForecastName);

  Integer removeUserForecastName(List<Integer> Ids);

}
