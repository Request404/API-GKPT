package com.bdap.traffic.system.service;

import com.bdap.traffic.system.entity.DescriptionInfo;
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
public interface DescriptionInfoService extends IService<DescriptionInfo> {

    //查询描述Id是否存在
    Boolean descriptionInExist(Integer descriptionId);

    List<DescriptionInfo> getDescriptions();

    Integer createDescription(DescriptionInfo descriptionInfo);

    Integer updateDescription(DescriptionInfo descriptionInfo);

    Boolean removeDescription(Integer id);

}
