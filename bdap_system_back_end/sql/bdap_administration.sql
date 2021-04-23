/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80022
Source Host           : localhost:3306
Source Database       : bdap_administration

Target Server Type    : MYSQL
Target Server Version : 80022
File Encoding         : 65001

Date: 2020-12-25 16:07:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin_account
-- ----------------------------
DROP TABLE IF EXISTS `admin_account`;
CREATE TABLE `admin_account` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id编号',
  `user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '手机号码',
  `mail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '邮箱',
  `authority` varchar(255) NOT NULL,
  `operate_time` datetime NOT NULL COMMENT '操作时间',
  `operate` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '操作人',
  `remove_state` int NOT NULL DEFAULT '1' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of admin_account
-- ----------------------------
INSERT INTO `admin_account` VALUES ('1', 'root', '$2a$10$2Ohh4qavtWxFyPvYAhGkEeoK5j6KsArACjFFgdjMpJbuNUDkwksXy', '15626116118', '123456@abc.com', 'root', '2020-12-24 12:43:32', 'root', '1');
INSERT INTO `admin_account` VALUES ('2', 'region01', '$2a$10$JzMBGDr9G23ertan5tFiAuWDQ/JJ2fp1LNgNgiD9o/H96iWc2HhPC', 'x', 'x', 'region', '2020-12-16 10:45:01', 'root', '1');
INSERT INTO `admin_account` VALUES ('3', '1-02', '$2a$10$JzMBGDr9G23ertan5tFiAuWDQ/JJ2fp1LNgNgiD9o/H96iWc2HhPC', 'x', 'x', 'company', '2020-12-16 10:45:03', 'root', '1');
INSERT INTO `admin_account` VALUES ('4', '1-03', '$2a$10$JzMBGDr9G23ertan5tFiAuWDQ/JJ2fp1LNgNgiD9o/H96iWc2HhPC', 'x', 'x', 'company', '2020-12-16 10:45:06', 'root', '1');
INSERT INTO `admin_account` VALUES ('5', '1-04', '$2a$10$j/NG.qbejj7lRdB7SrgGX.St/VDyV2jAFGsPKBmc36pb9AKXGqkV.', '13584778514', '1235@123.com', 'company', '2020-12-21 03:46:29', '1-06', '1');
INSERT INTO `admin_account` VALUES ('6', '1-05', '$2a$10$JzMBGDr9G23ertan5tFiAuWDQ/JJ2fp1LNgNgiD9o/H96iWc2HhPC', 'x', 'x', 'base', '2020-12-16 10:45:11', 'root', '1');
INSERT INTO `admin_account` VALUES ('7', '1-06', '$2a$10$JzMBGDr9G23ertan5tFiAuWDQ/JJ2fp1LNgNgiD9o/H96iWc2HhPC', 'x', 'x', 'region', '2020-12-16 10:45:13', 'root', '1');
INSERT INTO `admin_account` VALUES ('8', '1-07', '$2a$10$ykUv1s28u.4ylwZNlU9I3u8qYa6BSjA4pj6HAJ9IFF01OyoZSBiiK', '13584778514', '1235@123.com', 'company', '2020-12-21 03:55:57', '1-06', '1');
INSERT INTO `admin_account` VALUES ('9', '1-08', '$2a$10$JzMBGDr9G23ertan5tFiAuWDQ/JJ2fp1LNgNgiD9o/H96iWc2HhPC', 'x', 'x', 'company', '2020-12-16 10:45:19', 'root', '1');
INSERT INTO `admin_account` VALUES ('10', '1-09', '$2a$10$JzMBGDr9G23ertan5tFiAuWDQ/JJ2fp1LNgNgiD9o/H96iWc2HhPC', 'x', 'x', 'base', '2020-12-16 10:45:21', 'root', '1');
INSERT INTO `admin_account` VALUES ('11', '1-10', '$2a$10$JzMBGDr9G23ertan5tFiAuWDQ/JJ2fp1LNgNgiD9o/H96iWc2HhPC', 'x', 'x', 'base', '2020-12-16 10:45:25', 'root', '1');
INSERT INTO `admin_account` VALUES ('13', '12201', '$2a$10$g9I4JU9vk0drnpYVYMw5hecJAnwGGIpMMbYu3XpUvdaldp7zKI6b6', '13584778514', '1235@123.com', 'base', '2020-12-16 10:45:27', 'root', '0');
INSERT INTO `admin_account` VALUES ('14', '122201', '$2a$10$NIFPMC5k9cEDhsN7tHWNa.jTXOZOE1hXbqNYaTle91VqZWLCrO3qq', '13584778514', '1235@123.com', 'base', '2020-12-16 10:45:31', 'root', '0');
INSERT INTO `admin_account` VALUES ('15', '122201', '$2a$10$qipTyeE0Hy1vNtNIOOOwIOe8hnM6IYToH1Vh/MoznWUqQwSMHQseO', '13584778514', '1235@123.com', 'region', '2020-12-21 03:30:48', '122201', '1');
INSERT INTO `admin_account` VALUES ('16', 'test', '$2a$10$sR8jf0GRRWGTy1FGcK.uD.OgKTpVIAO69sTOxkknGDwJwqYCQ5hUu', '13425255255', '136@163.com', 'region', '2020-12-24 02:22:28', 'root', '0');

-- ----------------------------
-- Table structure for admin_info
-- ----------------------------
DROP TABLE IF EXISTS `admin_info`;
CREATE TABLE `admin_info` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id编号',
  `user` varchar(255) NOT NULL COMMENT '用户名',
  `name` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓名',
  `gender` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '性别',
  `phone` varchar(255) NOT NULL COMMENT '电话',
  `mail` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `mark` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '备注',
  `region` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '地区',
  `company` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公司',
  `authority` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限',
  `level` int NOT NULL COMMENT '级别',
  `admin_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '管理员身份码（用于推荐用户报名填写）',
  `operate_time` datetime NOT NULL COMMENT '操作时间',
  `operate` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '操作人',
  `remove_state` int NOT NULL DEFAULT '1' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of admin_info
-- ----------------------------
INSERT INTO `admin_info` VALUES ('1', 'root', 'zairui', '男', '15626116118', '123456@abc.com', 'Super User', '华南', '再芮', 'root', '0', '10000001', '2020-12-16 10:43:55', 'root', '1');
INSERT INTO `admin_info` VALUES ('2', 'region01', 'x', 'x', 'x', 'x', 'x', 'region01', 'company1', 'region', '1', '10000002', '2020-12-16 10:43:59', 'root', '1');
INSERT INTO `admin_info` VALUES ('3', '1-02', 'x', 'x', 'x', 'x', 'x', 'region01', 'company2', 'company', '2', '10000003', '2020-12-16 10:44:01', 'root', '1');
INSERT INTO `admin_info` VALUES ('4', '1-03', 'x', 'x', 'x', 'x', 'x', 'region01', 'company3', 'company', '2', '10000004', '2020-12-16 10:44:05', 'root', '1');
INSERT INTO `admin_info` VALUES ('5', '1-04', '13', '女', '13584778514', '1235@123.com', 'x', 'region01', 'cc01', 'company', '2', '10000005', '2020-12-21 03:46:29', '1-06', '1');
INSERT INTO `admin_info` VALUES ('6', '1-05', 'x', 'x', 'x', 'x', 'x', 'region01', 'company3', 'base', '3', '10000006', '2020-12-16 10:44:10', 'root', '1');
INSERT INTO `admin_info` VALUES ('7', '1-06', 'x', 'x', 'x', 'x', 'x', 'region02', 'company1', 'region', '1', '10000007', '2020-12-16 10:44:12', 'root', '1');
INSERT INTO `admin_info` VALUES ('8', '1-07', '13', '女', '13584778514', '1235@123.com', 'x', 'region02', 'cc02', 'company', '2', '10000008', '2020-12-21 03:55:57', '1-06', '1');
INSERT INTO `admin_info` VALUES ('9', '1-08', 'x', 'x', 'x', 'x', 'x', 'region02', 'company2', 'company', '2', '10000009', '2020-12-16 10:44:18', 'root', '1');
INSERT INTO `admin_info` VALUES ('10', '1-09', 'x', 'x', 'x', 'x', 'x', 'region02', 'company2', 'base', '3', '10000010', '2020-12-16 10:44:23', 'root', '1');
INSERT INTO `admin_info` VALUES ('11', '1-10', 'x', 'x', 'x', 'x', 'x', 'region02', 'company2', 'base', '3', '10000011', '2020-12-16 10:44:25', 'root', '1');
INSERT INTO `admin_info` VALUES ('12', '12201', '12', '女', '13584778514', '1235@123.com', null, 'region01', 'cc01', 'base', '3', '10000012', '2020-12-16 10:44:27', 'root', '0');
INSERT INTO `admin_info` VALUES ('13', '122201', '12', '女', '13584778514', '1235@123.com', null, 'region01', 'cc01', 'base', '3', '10000013', '2020-12-16 10:44:30', 'root', '0');
INSERT INTO `admin_info` VALUES ('14', '122201', '13', '女', '13584778514', '1235@123.com', null, 'region01', 'cc01', 'region', '1', '47187103', '2020-12-21 03:30:48', '122201', '1');
INSERT INTO `admin_info` VALUES ('15', 'test', 'test', '男', '13425255255', '136@163.com', '无', 'test', 'test', 'region', '1', '49137089', '2020-12-24 02:22:28', 'root', '0');
