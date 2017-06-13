/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : recommand

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-06-13 15:13:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for history_datas
-- ----------------------------
DROP TABLE IF EXISTS `history_datas`;
CREATE TABLE `history_datas` (
  `id` varchar(16) NOT NULL,
  `history_date` date NOT NULL,
  `open` double NOT NULL,
  `max` double NOT NULL,
  `min` double NOT NULL,
  `close` double NOT NULL,
  `deal_count` bigint(20) NOT NULL,
  `deal_value` double NOT NULL,
  `up_rate` double DEFAULT NULL,
  PRIMARY KEY (`id`,`history_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sock_info
-- ----------------------------
DROP TABLE IF EXISTS `sock_info`;
CREATE TABLE `sock_info` (
  `id` varchar(16) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
