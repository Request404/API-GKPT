/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80022
Source Host           : localhost:3306
Source Database       : bdap_user_data

Target Server Type    : MYSQL
Target Server Version : 80022
File Encoding         : 65001

Date: 2020-12-25 16:07:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for registration_information
-- ----------------------------
DROP TABLE IF EXISTS `registration_information`;
CREATE TABLE `registration_information` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id编号',
  `name` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓名',
  `gender` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '性别',
  `identity_number` varchar(255) NOT NULL COMMENT '身份证号',
  `phone` varchar(255) NOT NULL COMMENT '手机号',
  `certificate_name` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '报考证书名',
  `certificate_grade` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '报考证书等级',
  `photo` varchar(255) NOT NULL COMMENT '照片',
  `region` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '地区',
  `company` varchar(255) DEFAULT NULL COMMENT '公司/机构名称',
  `base` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '推荐人',
  `reference` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '推荐码',
  `user_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户码',
  `user_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户名',
  `operate_time` datetime NOT NULL COMMENT '操作时间',
  `operate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作人',
  `remove_state` int NOT NULL DEFAULT '1' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of registration_information
-- ----------------------------
INSERT INTO `registration_information` VALUES ('1', 'test01', '男', '440555200009080475', '13544774474', '大数据资产规划师', '中级', 'http://www.xxxxx.com', 'region01', 'cc01', 'test', '47187103', null, null, '2020-12-25 11:56:01', 'root', '1');
INSERT INTO `registration_information` VALUES ('2', 'cxk', '男', '130425198708092057', '13655885574', '大数据资产规划师', '中级', 'http://www.test.com', null, null, null, null, 'de172b11-8367-426d-bfff-b6cf53b623df', '13300425877', '2020-12-25 03:56:51', '网站报名', '1');

-- ----------------------------
-- Table structure for user_commit
-- ----------------------------
DROP TABLE IF EXISTS `user_commit`;
CREATE TABLE `user_commit` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id编号',
  `name` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '姓名',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '电话号码',
  `graduate` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学历',
  `age` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '年龄',
  `goal` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '目的',
  `remove_state` int NOT NULL DEFAULT '1' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user_commit
-- ----------------------------
INSERT INTO `user_commit` VALUES ('1', 'a', 'aaa', 'aa', 'aa', 'aa', '1');
INSERT INTO `user_commit` VALUES ('2', 'b', 'bbb', 'bb', 'bb', 'bb', '1');
INSERT INTO `user_commit` VALUES ('3', 'c', 'ccc', 'ccc', 'cc', 'ccc', '1');
INSERT INTO `user_commit` VALUES ('4', 'd', 'dd', 'ddd', 'ddd', 'ddd', '1');
INSERT INTO `user_commit` VALUES ('5', 'xc', '1324423', 'dde', 'eee', 'rrr', '1');
INSERT INTO `user_commit` VALUES ('6', 'ddd', '1233444', '5d', 'dd', 'd', '1');
INSERT INTO `user_commit` VALUES ('7', 'bb', '14444', 'eee', '333', '4455', '1');
INSERT INTO `user_commit` VALUES ('8', 'bb', '213114', '13', '33', '232', '1');
INSERT INTO `user_commit` VALUES ('9', 'gd', '2332321', '133123', '13123', '1231231', '1');
INSERT INTO `user_commit` VALUES ('10', 'e21231', '13131', '312313', '13131', '3131', '1');
INSERT INTO `user_commit` VALUES ('11', '12323', '31233', '2321313', '131313', '1313', '1');
INSERT INTO `user_commit` VALUES ('12', '321323', '232323', '3232', '233', '232323', '1');
INSERT INTO `user_commit` VALUES ('13', '32', '232', '23232', '23232322', '32323', '1');
INSERT INTO `user_commit` VALUES ('14', '2323', '3232', '32', 'ddada', 'dadada', '1');
INSERT INTO `user_commit` VALUES ('15', 'ew', 'ewew', 'ewe', 'ew', 'wewewe', '1');
INSERT INTO `user_commit` VALUES ('16', 'ew', '323', '32', '2323', '2321', '1');
INSERT INTO `user_commit` VALUES ('17', '323', '331', '3', '32323', '22', '1');
INSERT INTO `user_commit` VALUES ('18', '3232', '3232', 'e', '32323', '2323', '1');
INSERT INTO `user_commit` VALUES ('19', '323', '232', '3232', '3232', '23232', '1');
INSERT INTO `user_commit` VALUES ('20', '2323', '23232', '2323', '232', '2323', '1');
INSERT INTO `user_commit` VALUES ('21', '2323', '2323', '2232', '3232', '32', '1');
INSERT INTO `user_commit` VALUES ('22', '32', '322', '32323', '2232', '3232', '1');
INSERT INTO `user_commit` VALUES ('23', '232', '23232', '232', '2323', '23232', '1');
INSERT INTO `user_commit` VALUES ('24', 'cxk', '13300425877', '本科', '30-40', '提升学历', '1');

-- ----------------------------
-- Table structure for user_data
-- ----------------------------
DROP TABLE IF EXISTS `user_data`;
CREATE TABLE `user_data` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id编号',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户手机号码',
  `password` varchar(255) NOT NULL COMMENT '用户密码',
  `user_code` varchar(255) NOT NULL COMMENT '用户认证码',
  `state` int NOT NULL DEFAULT '1' COMMENT '状态码',
  `operate_time` datetime NOT NULL COMMENT '操作时间',
  `operate` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '操作人',
  `remove_state` int NOT NULL DEFAULT '1' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user_data
-- ----------------------------
INSERT INTO `user_data` VALUES ('1', '13587414174', '$2a$10$tflA.PryftEMpv6ZS24UYudnJxg4sYC7VS8SAYiLgsF4IVEqS2uuG', '5db896e8-4c9a-4ae3-8cf8-0b4606400f1b', '1', '2020-12-16 10:41:11', 'user', '1');
INSERT INTO `user_data` VALUES ('2', '13300425877', '$2a$10$tflA.PryftEMpv6ZS24UYudnJxg4sYC7VS8SAYiLgsF4IVEqS2uuG', 'de172b11-8367-426d-bfff-b6cf53b623df', '1', '2020-12-25 03:32:20', '用户', '1');

-- ----------------------------
-- Table structure for user_forecast_name
-- ----------------------------
DROP TABLE IF EXISTS `user_forecast_name`;
CREATE TABLE `user_forecast_name` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id编号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '姓名',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '联系方式',
  `remove_state` int NOT NULL DEFAULT '1' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user_forecast_name
-- ----------------------------
INSERT INTO `user_forecast_name` VALUES ('1', 'wew', '2323', '0');
INSERT INTO `user_forecast_name` VALUES ('2', 'zx', '141224141', '0');
INSERT INTO `user_forecast_name` VALUES ('3', '3d', '3131', '0');
INSERT INTO `user_forecast_name` VALUES ('4', 'xxx', '232412323', '1');
INSERT INTO `user_forecast_name` VALUES ('5', '2323', '312313', '1');
INSERT INTO `user_forecast_name` VALUES ('6', '232', 'dadadas', '1');
INSERT INTO `user_forecast_name` VALUES ('7', 'wewe', 'wqeeqwe', '1');
INSERT INTO `user_forecast_name` VALUES ('8', 'eweqw', 'eweewe', '1');
INSERT INTO `user_forecast_name` VALUES ('9', 'ewew', '2323123131', '1');
INSERT INTO `user_forecast_name` VALUES ('10', '232', '323224', '1');
INSERT INTO `user_forecast_name` VALUES ('11', '32323', '23232424', '1');
INSERT INTO `user_forecast_name` VALUES ('12', '32323', '424223232', '1');
INSERT INTO `user_forecast_name` VALUES ('13', '32', '2323242', '1');
INSERT INTO `user_forecast_name` VALUES ('14', '232', '2312341', '1');
INSERT INTO `user_forecast_name` VALUES ('15', '323', '24dfasdaf', '1');
INSERT INTO `user_forecast_name` VALUES ('16', 's', 'ffafa', '1');
INSERT INTO `user_forecast_name` VALUES ('17', 'dsds', 'fsfaf', '1');
INSERT INTO `user_forecast_name` VALUES ('18', 'dsd', 'ffsfgs', '1');
INSERT INTO `user_forecast_name` VALUES ('19', 'dd', 'adadad', '1');
INSERT INTO `user_forecast_name` VALUES ('20', 'dsd', 'dsdsddsfs', '1');
INSERT INTO `user_forecast_name` VALUES ('21', 'dd', 'ffsfsddsds', '1');
INSERT INTO `user_forecast_name` VALUES ('22', 'dsds', 'dffa', '1');
INSERT INTO `user_forecast_name` VALUES ('23', 'cbcbcbc', 'bcvc', '1');
INSERT INTO `user_forecast_name` VALUES ('24', 'ktkt', 'tttytyyt', '1');
INSERT INTO `user_forecast_name` VALUES ('25', 'tyttjtt', 'tttytyt', '1');
INSERT INTO `user_forecast_name` VALUES ('26', 'ytytt', 'jtttjtjjjjjjjjjjj', '1');
INSERT INTO `user_forecast_name` VALUES ('28', 'cxk', '13300425877', '1');
