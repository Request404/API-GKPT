package com.bdap.traffic.system.service;

import com.bdap.traffic.system.entity.LectureInfo;
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
public interface LectureInfoService extends IService<LectureInfo> {

    //查询讲师名是否存在
//    Boolean lectureInfoInExist(String lectureInfoName);

    List<LectureInfo> getLectureInfos();

    Integer createLectureInfo(LectureInfo lectureInfo);

    Integer updateLectureInfo(LectureInfo lectureInfo);

    Integer removeLectureInfo(Integer id);
}
