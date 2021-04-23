package com.bdap.traffic.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bdap.traffic.system.entity.UserTraffic;
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
public interface UserTrafficService extends IService<UserTraffic> {

  Integer createUserTraffic(UserTraffic certificateInfo);

  Integer removeUserTraffic(List<Integer> Ids);

  Page<UserTraffic> getUserTraffics(Integer page, Integer size, String like);

}
