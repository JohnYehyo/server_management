/*
 Navicat Premium Data Transfer

 Source Server         : docker
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : 192.168.100.233:3306
 Source Schema         : server_management

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 11/05/2021 22:17:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for server_info
-- ----------------------------
DROP TABLE IF EXISTS `server_info`;
CREATE TABLE `server_info`  (
  `id` int(12) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `server_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '服务名',
  `server_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '服务地址',
  `tomcat_dir` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'tomcat路径',
  `state` tinyint(1) NOT NULL COMMENT '状态 0 正常 1 未启动',
  `bucket_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '存储桶',
  `object_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '对象名',
  `is_valid` tinyint(1) NOT NULL DEFAULT 0 COMMENT '伪删除 0 正常 1 删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of server_info
-- ----------------------------
INSERT INTO `server_info` VALUES (1, '核查系统-测试服', 'http://localhost:8083', 'D:/tool/apache-tomcat-8.5.55-test/', 0, 'test', 'file/rjcloud-sysweb.war', 0);
INSERT INTO `server_info` VALUES (2, '核查系统-测试服', 'http://32.0.7.11:8083', 'D:/tool/apache-tomcat-8.5.55-test/', 1, 'test', 'file/rjcloud-sysweb.war', 0);

SET FOREIGN_KEY_CHECKS = 1;
