/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80022
Source Host           : localhost:3306
Source Database       : bdap_user_traffic

Target Server Type    : MYSQL
Target Server Version : 80022
File Encoding         : 65001

Date: 2020-12-25 16:08:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for certificate_info
-- ----------------------------
DROP TABLE IF EXISTS `certificate_info`;
CREATE TABLE `certificate_info` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id编号',
  `certificate_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '证书名',
  `certificate_grade` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '等级',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '价格',
  `remove_state` int NOT NULL DEFAULT '1' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of certificate_info
-- ----------------------------
INSERT INTO `certificate_info` VALUES ('1', '大数据资产规划师', '初级', '999.99', '1');
INSERT INTO `certificate_info` VALUES ('2', '大数据资产规划师', '中级', '1999.99', '1');
INSERT INTO `certificate_info` VALUES ('3', '大数据资产规划师', '高级', '2999.99', '1');
INSERT INTO `certificate_info` VALUES ('4', '大数据分析师', '初级', '999.99', '1');
INSERT INTO `certificate_info` VALUES ('5', '大数据分析师', '中级', '1999.99', '1');
INSERT INTO `certificate_info` VALUES ('6', '大数据分析师', '高级', '2999.99', '1');

-- ----------------------------
-- Table structure for discription_info
-- ----------------------------
DROP TABLE IF EXISTS `discription_info`;
CREATE TABLE `discription_info` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id编号',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '标题',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '内容',
  `picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of discription_info
-- ----------------------------
INSERT INTO `discription_info` VALUES ('1', '概述', '大数据时代，数据成为全新的生产资料——数据是资源，是资本， 是资产。当多元海量数据作为生产资料的价值被数据资产运营充分调动起来，将激活亿万产值。', null);
INSERT INTO `discription_info` VALUES ('2', '概述', '随着大数据技术多年的发展，业界已逐渐将注意力回归到数据价值的本身，数据资产化已达成了行业共识。数据资产运营带来了对传统产业颠覆性的新变革，跨领域、跨地域加速数据流通融合，立足实际需求，创新“数字服务”模式，加快了传统产业数字化进程，以及数据与实体经济的深度融合。', null);
INSERT INTO `discription_info` VALUES ('3', '概述', '工信部教育与考试中心授权贵阳大数据交易所作为工业和信息化人才培养工程培训基地开展大数据领域相关培训工作。贵阳大数据交易所的“大数据资产规划师、分析师”课程已纳入工业和信息化人才培养工程课程体系。', null);
INSERT INTO `discription_info` VALUES ('4', '培训说明', '1、本次培训采用线上培训和考试方式，报名学员超过 50 人方可开展培训。', null);
INSERT INTO `discription_info` VALUES ('5', '培训说明', '2、为保证培训质量，如报名超员，则按报名时间排序，顺延至下一期培训。', null);
INSERT INTO `discription_info` VALUES ('6', '培训说明', '3、学员需全程参加培训，并且考试合格，方可获得工信部教育与考试中心颁发的“大数据资产规划师、分析师”证书。', null);
INSERT INTO `discription_info` VALUES ('7', '培训时间', '2020 年 12 月 25 日、26 日和 2021 年 1 月 1 日、2 日。', null);
INSERT INTO `discription_info` VALUES ('8', '培训方式', '【理论】 + 【应用】 + 【考试】', null);
INSERT INTO `discription_info` VALUES ('9', '课程安排', null, null);
INSERT INTO `discription_info` VALUES ('10', '大数据分析师课程详情', null, null);
INSERT INTO `discription_info` VALUES ('11', '考核方式', '1、第四天培训完毕立即考试。考试为线上方式。', null);
INSERT INTO `discription_info` VALUES ('12', '考核方式', '2、考试时间 120 分钟。考题为百分制，60 分及以上为合格。', null);

-- ----------------------------
-- Table structure for lecture_info
-- ----------------------------
DROP TABLE IF EXISTS `lecture_info`;
CREATE TABLE `lecture_info` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id编号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓名',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '描述',
  `research_orientation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '研究方向',
  `mark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  `photo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '照片',
  `remove_state` int DEFAULT '1' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of lecture_info
-- ----------------------------
INSERT INTO `lecture_info` VALUES ('1', '司亚清', '北京邮电大学经济管理学院副教授，硕导，数据资产管理研究中心主任，区块链技术与应用研究中心常务副主任。', '研究方向：企业管理的信息化，大数据工程与治理、数据市场治理，智能决策，区块链，科研教学。', null, null, '1');
INSERT INTO `lecture_info` VALUES ('2', '苏静', '北京邮电大学经济管理学院副教授，法学及管理学双硕士。任北京邮电大学经济管理学院《数据资产管理研究中心》副主任。', '研究方向：法学及管理，数据资产管理，法律风险识别、科研教学。', null, null, '1');
INSERT INTO `lecture_info` VALUES ('3', '赵启阳', '北京航空航天大学计算机学院讲师，工学博士，硕士生导师。中国计算机学会NOI 科学委员会副主席。', '研究方向：研究方向：深度学习、计算机视觉、数据产权界定和安全。', null, null, '1');
INSERT INTO `lecture_info` VALUES ('4', 'Test01', '该讲师为测试对象', '该讲师为测试对象', '无', 'http://www.test.com', '0');

-- ----------------------------
-- Table structure for user_traffic
-- ----------------------------
DROP TABLE IF EXISTS `user_traffic`;
CREATE TABLE `user_traffic` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id编号',
  `ip_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'IP地址',
  `region` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '地区',
  `visit_time` datetime NOT NULL COMMENT '访问时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user_traffic
-- ----------------------------
INSERT INTO `user_traffic` VALUES ('4', '12.252.44.11', '湖南长沙', '2020-12-25 14:52:59');
INSERT INTO `user_traffic` VALUES ('5', '48.56.23.45', '上海浦东', '2020-12-25 14:53:30');
INSERT INTO `user_traffic` VALUES ('6', '19.56.23.75', '湖北武汉', '2020-12-25 04:01:04');
