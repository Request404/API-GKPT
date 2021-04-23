package com.bdap.administration.system.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;

@Configuration
public class DruidConfig {

  @Bean
  //配置druid监控
  public ServletRegistrationBean statViewServlet(){
    ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
    HashMap<String, String> map = new HashMap<>();
    map.put("loginUsername","GuoKeGD");
    map.put("loginPassword","GuoKe@3306");
    map.put("allow","");
    bean.setInitParameters(map);
    return bean;
  }

  //配置过滤器
  @Bean
  public FilterRegistrationBean webStatFilter(){
    FilterRegistrationBean bean = new FilterRegistrationBean();
    bean.setFilter(new WebStatFilter());
    HashMap<String, String> map = new HashMap<>();
    map.put("exclusions","*.js,*.html,*.css,*.druid");
    bean.setInitParameters(map);
    bean.setUrlPatterns(Arrays.asList("/*"));
    return bean;
  }
}
