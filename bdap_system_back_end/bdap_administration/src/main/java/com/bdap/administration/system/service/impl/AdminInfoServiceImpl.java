package com.bdap.administration.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bdap.administration.system.entity.AdminAccount;
import com.bdap.administration.system.entity.AdminInfo;
import com.bdap.administration.system.mapper.AdminInfoMapper;
import com.bdap.administration.system.service.AdminInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bdap.common.exception.PermissionDeniedException;
import com.bdap.common.exception.UserExistException;
import com.bdap.common.exception.UserNotFoundException;
import com.bdap.common.utils.Sql;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author GuoKeGD
 * @since 2020-12-01
 */
@Service
public class AdminInfoServiceImpl extends ServiceImpl<AdminInfoMapper, AdminInfo> implements AdminInfoService {

  @Override
  public List<AdminInfo> rootListTree() {
    List<AdminInfo> list = baseMapper.selectList(null);
    List<AdminInfo> adminTree = list.stream()
        .filter(adminInfo -> adminInfo.getLevel() == 1)
        .peek((branch) -> {
          branch.setSubordinate(getRegionSubordinates(branch, list));
        })
        .collect(Collectors.toList());
    return adminTree;
  }

  @Override
  public List<AdminInfo> regionListTree(String region, Authentication authentication) {
    AdminInfo info = this.getAdministrationInfo(authentication.getName());
    if (("root".equals(info.getAuthority())) || region.equals(info.getRegion())) {
      List<AdminInfo> list = baseMapper.selectList(new QueryWrapper<AdminInfo>().eq("region", region));
      List<AdminInfo> adminTree = list.stream()
          .filter(adminInfo -> adminInfo.getLevel() == 2)
          .peek((branch) -> {
            branch.setSubordinate(getCompanySubordinates(branch, list));
          }).collect(Collectors.toList());
      return adminTree;
    } else {
      throw new PermissionDeniedException();
    }
  }

  @Override
  public AdminInfo companyListTree(String region, String company, Authentication authentication) {
    AdminInfo info = this.getAdministrationInfo(authentication.getName());
    if (("root".equals(info.getAuthority()))
        || ("region".equals(info.getAuthority()) && region.equals(info.getRegion()))
        || (region.equals(info.getRegion()) && company.equals(info.getCompany()))) {
      AdminInfo companyAdmin = baseMapper.selectOne(new QueryWrapper<AdminInfo>().eq("region", region).eq("company", company).eq("level", 2));
      companyAdmin.setSubordinate(baseMapper.selectList(new QueryWrapper<AdminInfo>().eq("region", region).eq("company", company).eq("level", 3)));
      return companyAdmin;
    } else {
      throw new PermissionDeniedException();
    }
  }

  @Override
  public Integer addAdministrationInfo(AdminInfo adminInfo) {
    if (this.AdministrationIsExist(adminInfo.getUser())) {
      throw new UserExistException();
    } else {
      adminInfo.setAdminCode(this.generatorAdminCode(Sql.generatorNumber(8)));
      return baseMapper.insert(adminInfo);
    }
  }


  @Override
  public Integer updateAdministrationInfo(AdminInfo adminInfo) {
    if (this.AdministrationIsExist(adminInfo.getUser())) {
      return baseMapper.update(adminInfo, new UpdateWrapper<AdminInfo>().eq("user", adminInfo.getUser()));
    } else {
      throw new UserNotFoundException();
    }
  }

  @Override
  public Boolean removeAdministrationInfo(String user) {
    if (this.AdministrationIsExist(user)) {
      return baseMapper.delete(new QueryWrapper<AdminInfo>().eq("user", user)) != 0;
    } else {
      throw new UserNotFoundException();
    }
  }

  @Override
  public AdminInfo getAdministrationInfo(String user) {
    if (this.AdministrationIsExist(user)) {
      return baseMapper.selectOne(new QueryWrapper<AdminInfo>().eq("user", user));
    } else {
      throw new UserNotFoundException();
    }
  }

  @Override
  public AdminInfo getAdministrationInfoByAdminCode(String adminCode) {
    return baseMapper.selectOne(new QueryWrapper<AdminInfo>().eq("admin_code", adminCode));
  }


  private Boolean AdministrationIsExist(String user) {
    return baseMapper.selectOne(new QueryWrapper<AdminInfo>().eq("user", user)) != null;
  }


  //搜集地区的子元素s
  private List<AdminInfo> getRegionSubordinates(AdminInfo parent, List<AdminInfo> adminInfos) {
    List<AdminInfo> collect = adminInfos.stream()
        .filter(adminInfo -> adminInfo.getRegion().equals(parent.getRegion()) && adminInfo.getLevel() == 2)
        .peek((branch) -> branch.setSubordinate(getCompanySubordinates(branch, adminInfos)))
        .collect(Collectors.toList());
    System.out.println(collect);
    return collect;
  }


  //搜集公司、机构的子元素
  private List<AdminInfo> getCompanySubordinates(AdminInfo parent, List<AdminInfo> adminInfos) {
    List<AdminInfo> collect = adminInfos.stream()
        .filter(adminInfo -> adminInfo.getCompany().equals(parent.getCompany()) && adminInfo.getLevel() == 3)
        .collect(Collectors.toList());
    return collect;
  }

  private String generatorAdminCode(String adminCode) {
    if (baseMapper.selectOne(new QueryWrapper<AdminInfo>().eq("admin_code", adminCode)) != null) {
      return this.generatorAdminCode(Sql.generatorNumber(8));
    } else {
      return adminCode;
    }
  }

}
