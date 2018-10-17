/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : sg

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-09-03 17:39:10
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
