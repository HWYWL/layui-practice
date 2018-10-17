/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : sg

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-09-03 17:39:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for gateway_address
-- ----------------------------
DROP TABLE IF EXISTS `gateway_address`;
CREATE TABLE `gateway_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `internal_network_ip` varchar(255) NOT NULL COMMENT '网关内网ip地址',
  `outside_network_ip` varchar(255) NOT NULL COMMENT '网关外网ip地址',
  `port_list` varchar(255) NOT NULL DEFAULT '' COMMENT '随机端口列表',
  `gateway_address_name` varchar(255) DEFAULT NULL COMMENT '网关服务器名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0：正常使用、-1：已被删除',
  `crtTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='网关服务器地址';

-- ----------------------------
-- Records of gateway_address
-- ----------------------------
INSERT INTO `gateway_address` VALUES ('13', '192.168.2.17', '120.79.117.112', '[7100,8391,8735,6215]', '防御网关测试服务器', '防御网关测试服务器!!!', '0', '2018-07-16 19:05:21');
INSERT INTO `gateway_address` VALUES ('14', '192.168.2.18', '120.168.2.188', '', '防御网关测试服务器2', '防御网关测试服务器2', '0', '2018-07-30 09:47:24');
INSERT INTO `gateway_address` VALUES ('15', '192.168.2.19', '120.168.2.19', '[8345,6179,8360,7361]', '防御网关测试服务器3', '防御网关测试服务器3', '0', '2018-07-30 09:47:49');

-- ----------------------------
-- Table structure for rule_group
-- ----------------------------
DROP TABLE IF EXISTS `rule_group`;
CREATE TABLE `rule_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `rule_group_name` varchar(255) DEFAULT NULL COMMENT '规则组名',
  `gateway_address_ids` varchar(255) DEFAULT NULL COMMENT '所属该组的网关id',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `enable` tinyint(4) NOT NULL DEFAULT '-1' COMMENT '随机端口 0：启用、-1：关闭',
  `del` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0：正常使用、-1：已被删除',
  `crtTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='规则组';

-- ----------------------------
-- Records of rule_group
-- ----------------------------
INSERT INTO `rule_group` VALUES ('8', '测试规则组', '[\"15\",\"13\"]', '测试规则组!!!', '0', '0', '2018-07-16 19:05:40');

-- ----------------------------
-- Table structure for rules
-- ----------------------------
DROP TABLE IF EXISTS `rules`;
CREATE TABLE `rules` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `rule_group_id` bigint(20) NOT NULL COMMENT '规则组ID',
  `from_port` bigint(20) DEFAULT NULL COMMENT '来源端口',
  `to_port` bigint(20) DEFAULT NULL COMMENT '转发端口',
  `to_ip` varchar(255) DEFAULT NULL COMMENT '转发地址',
  `rule_name` varchar(255) DEFAULT NULL COMMENT '规则名称',
  `agreement` varchar(100) DEFAULT NULL COMMENT '协议',
  `max_concurrent_conn` varchar(100) DEFAULT '0' COMMENT '代理最大并发连接数',
  `max_concurrent_conn_per_ip` varchar(100) DEFAULT '0' COMMENT '每个ip最大并发连接数',
  `max_new_conn_per_min_per_ip` varchar(100) DEFAULT '0' COMMENT '每个ip每分钟最大新建连接数',
  `rev_first_pkg_timeout_mills` varchar(100) DEFAULT '0' COMMENT '首包超时毫秒数',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0：正常使用、-1：已被删除',
  `crtTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='规则表';

-- ----------------------------
-- Records of rules
-- ----------------------------
INSERT INTO `rules` VALUES ('9', '8', '8888', '8666', '120.78.252.181', '测试规则', 'http', '100', '10', '10', '1000', '测试规则！！！', '0', '2018-07-16 19:17:09');
INSERT INTO `rules` VALUES ('10', '8', '9000', '9000', '120.78.252.181', '测试', 'tcp_game', '200', '10', '10', '2000', '测试！！！', '0', '2018-07-16 19:19:38');
INSERT INTO `rules` VALUES ('11', '8', '9001', '9001', '192.168.100.1', '测试规则3', 'tcp_game', '0', '0', '0', '0', '测试规则3！！！', '0', '2018-08-02 11:31:40');
INSERT INTO `rules` VALUES ('12', '8', '9002', '9002', '192.168.1.105', '测试规则4', 'http', '0', '0', '0', '0', '测试规则4！！', '0', '2018-08-02 11:51:29');

-- ----------------------------
-- Table structure for user_group
-- ----------------------------
DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_group_name` varchar(255) DEFAULT NULL COMMENT '用户组名',
  `rule_group_ids` varchar(255) DEFAULT NULL COMMENT '所属规则组的id',
  `game_id` bigint(20) DEFAULT NULL COMMENT '游戏id',
  `encrypt_key` varchar(255) DEFAULT NULL COMMENT '加密秘钥key',
  `gateway_address_ids` varchar(255) DEFAULT NULL COMMENT '所属该组的网关id',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0：正常使用、-1：已被删除',
  `crtTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='用户组';

-- ----------------------------
-- Records of user_group
-- ----------------------------
INSERT INTO `user_group` VALUES ('4', '测试用户组', '8', null, 'id6plnnt2d9a9kzh', '[\"13\",\"15\"]', '测试用户组！！！', '0', '2018-07-16 19:15:12');
INSERT INTO `user_group` VALUES ('5', '测试用户组2', '8', null, 'id6plnnt2d9a9kzh', '[\"13\",\"15\"]', '测试用户组2', '0', '2018-07-30 10:28:53');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `role` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0：管理员、1：普通用户',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0：正常使用、-1：已被删除',
  `crtTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'user', 'e10adc3949ba59abbe56e057f20f883e', '0', '测试管理员', '0', '2018-07-20 09:26:55');
