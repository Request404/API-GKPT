package com.bdap.traffic.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bdap.traffic.system.common.exception.LectureNotFoundException;
import com.bdap.traffic.system.entity.LectureInfo;
import com.bdap.traffic.system.mapper.LectureInfoMapper;
import com.bdap.traffic.system.service.LectureInfoService;
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
public class LectureInfoServiceImpl extends ServiceImpl<LectureInfoMapper, LectureInfo> implements LectureInfoService {

  @Override
  public List<LectureInfo> getLectureInfos() {
    return baseMapper.selectList(null);
  }

  @Override
  public Integer createLectureInfo(LectureInfo lectureInfo) {
    return baseMapper.insert(lectureInfo);
  }

  @Override
  public Integer updateLectureInfo(LectureInfo lectureInfo) {
    if (this.lectureIsExist(lectureInfo.getId())) {
      return baseMapper.update(lectureInfo, new UpdateWrapper<LectureInfo>().eq("id", lectureInfo.getId()));
    } else {
      throw new LectureNotFoundException();
    }
  }

  @Override
  public Integer removeLectureInfo(Integer id) {
    if (this.lectureIsExist(id)) {
      return baseMapper.delete(new QueryWrapper<LectureInfo>().eq("id", id));
    } else {
      throw new LectureNotFoundException();
    }
  }

  private Boolean lectureIsExist(Integer id) {
    return baseMapper.selectOne(new QueryWrapper<LectureInfo>().eq("id", id)) != null;
  }
}
