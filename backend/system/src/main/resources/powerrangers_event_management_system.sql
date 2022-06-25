/*
 Navicat Premium Data Transfer

 Source Server         : haoyuwang
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : powerrangers_event_management_system

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 26/06/2022 02:47:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for coupon_customer
-- ----------------------------
DROP TABLE IF EXISTS `coupon_customer`;
CREATE TABLE `coupon_customer` (
  `coupon_id` int NOT NULL,
  `customer_id` int NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  KEY `coupon_id_index` (`coupon_id`) USING BTREE,
  KEY `customer_id_index` (`customer_id`) USING BTREE,
  KEY `time_index` (`create_time`,`update_time`) USING BTREE,
  CONSTRAINT `coupon_customer_coupon_id_key` FOREIGN KEY (`coupon_id`) REFERENCES `sys_coupon` (`coupon_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `coupon_customer_customer_id_key` FOREIGN KEY (`customer_id`) REFERENCES `sys_user_customer` (`customer_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of coupon_customer
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for coupon_rule
-- ----------------------------
DROP TABLE IF EXISTS `coupon_rule`;
CREATE TABLE `coupon_rule` (
  `rule_id` int NOT NULL AUTO_INCREMENT,
  `coupon_id` int NOT NULL,
  `type_id` int NOT NULL,
  `threshold` decimal(10,2) DEFAULT '0.00',
  `amount` decimal(10,2) NOT NULL DEFAULT '0.00',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`rule_id`),
  UNIQUE KEY `rule_id_index` (`rule_id`) USING BTREE,
  KEY `coupon_id_index` (`coupon_id`) USING BTREE,
  KEY `type_id_index` (`type_id`) USING BTREE,
  KEY `amount_index` (`amount`) USING BTREE,
  CONSTRAINT `rule_coupon_id_key` FOREIGN KEY (`coupon_id`) REFERENCES `sys_coupon` (`coupon_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rule_coupon_type_id_key` FOREIGN KEY (`type_id`) REFERENCES `coupon_type` (`type_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of coupon_rule
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for coupon_type
-- ----------------------------
DROP TABLE IF EXISTS `coupon_type`;
CREATE TABLE `coupon_type` (
  `type_id` int NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `type_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`type_id`),
  UNIQUE KEY `type_id_index` (`type_id`) USING BTREE,
  KEY `time_index` (`create_time`,`update_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of coupon_type
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for event_authcode
-- ----------------------------
DROP TABLE IF EXISTS `event_authcode`;
CREATE TABLE `event_authcode` (
  `code_id` int NOT NULL AUTO_INCREMENT,
  `event_id` int NOT NULL,
  `code_file` blob COMMENT 'binary large object for authcode',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`code_id`),
  UNIQUE KEY `code_id_index` (`code_id`) USING BTREE,
  KEY `event_id_index` (`event_id`) USING BTREE,
  CONSTRAINT `authcode_event_id_key` FOREIGN KEY (`event_id`) REFERENCES `sys_event` (`event_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of event_authcode
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for event_comments
-- ----------------------------
DROP TABLE IF EXISTS `event_comments`;
CREATE TABLE `event_comments` (
  `event_id` int NOT NULL,
  `customer_id` int NOT NULL,
  `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `star_level` float DEFAULT NULL,
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  KEY `event_id_index` (`event_id`) USING BTREE,
  KEY `customer_id_index` (`customer_id`) USING BTREE,
  KEY `time_index` (`create_time`,`update_time`) USING BTREE,
  CONSTRAINT `comments_customer_id_key` FOREIGN KEY (`customer_id`) REFERENCES `sys_user_customer` (`customer_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comments_event_id_key` FOREIGN KEY (`event_id`) REFERENCES `sys_event` (`event_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of event_comments
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for event_customer
-- ----------------------------
DROP TABLE IF EXISTS `event_customer`;
CREATE TABLE `event_customer` (
  `event_id` int NOT NULL,
  `customer_id` int NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  KEY `event_id_index` (`event_id`) USING BTREE,
  KEY `customer_id_index` (`customer_id`) USING BTREE,
  CONSTRAINT `customer_customer_id_key` FOREIGN KEY (`customer_id`) REFERENCES `sys_user_customer` (`customer_id`),
  CONSTRAINT `customer_event_id_key` FOREIGN KEY (`event_id`) REFERENCES `sys_event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of event_customer
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for event_file
-- ----------------------------
DROP TABLE IF EXISTS `event_file`;
CREATE TABLE `event_file` (
  `file_id` int NOT NULL AUTO_INCREMENT,
  `event_id` int NOT NULL,
  `filename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `file` blob NOT NULL COMMENT 'file binary large object',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`file_id`),
  UNIQUE KEY `file_id` (`file_id`) USING BTREE,
  KEY `event_id` (`event_id`) USING BTREE,
  CONSTRAINT `file_event_id_key` FOREIGN KEY (`event_id`) REFERENCES `sys_event` (`event_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of event_file
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for event_ticket
-- ----------------------------
DROP TABLE IF EXISTS `event_ticket`;
CREATE TABLE `event_ticket` (
  `ticket_id` int NOT NULL AUTO_INCREMENT,
  `event_id` int NOT NULL,
  `ticket_type` int NOT NULL COMMENT 'ticket type id',
  `ticket_amount` int NOT NULL DEFAULT '0',
  `ticket_price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ticket_id`),
  UNIQUE KEY `ticket_id_inedex` (`ticket_id`) USING BTREE,
  KEY `event_id_index` (`event_id`) USING BTREE,
  KEY `ticket_type_index` (`ticket_type`) USING BTREE,
  CONSTRAINT `ticket_event_id_key` FOREIGN KEY (`event_id`) REFERENCES `sys_event` (`event_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ticket_ticket_type_key` FOREIGN KEY (`ticket_type`) REFERENCES `event_ticket_type` (`type_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of event_ticket
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for event_ticket_type
-- ----------------------------
DROP TABLE IF EXISTS `event_ticket_type`;
CREATE TABLE `event_ticket_type` (
  `type_id` int NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`type_id`),
  UNIQUE KEY `type_id_index` (`type_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of event_ticket_type
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for event_type
-- ----------------------------
DROP TABLE IF EXISTS `event_type`;
CREATE TABLE `event_type` (
  `type_id` int NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT 'event type name',
  `create_by` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'log of manipulation',
  `update_by` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`type_id`),
  UNIQUE KEY `type_id_index` (`type_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of event_type
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for order_coupon
-- ----------------------------
DROP TABLE IF EXISTS `order_coupon`;
CREATE TABLE `order_coupon` (
  `order_id` int NOT NULL,
  `coupon_id` int NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  KEY `order_id_index` (`order_id`) USING BTREE,
  KEY `coupon_id_index` (`coupon_id`) USING BTREE,
  CONSTRAINT `order_coupon_coupon_id_key` FOREIGN KEY (`coupon_id`) REFERENCES `sys_coupon` (`coupon_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `order_coupon_order_id_key` FOREIGN KEY (`order_id`) REFERENCES `sys_order` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of order_coupon
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for order_payment_type
-- ----------------------------
DROP TABLE IF EXISTS `order_payment_type`;
CREATE TABLE `order_payment_type` (
  `type_id` int NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`type_id`),
  UNIQUE KEY `type_id_index` (`type_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of order_payment_type
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for order_second_auth
-- ----------------------------
DROP TABLE IF EXISTS `order_second_auth`;
CREATE TABLE `order_second_auth` (
  `order_id` int NOT NULL,
  `customer_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `customer_identity` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `is_auth` tinyint NOT NULL DEFAULT '0',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  KEY `order_id_index` (`order_id`) USING BTREE,
  CONSTRAINT `auth_order_id_key` FOREIGN KEY (`order_id`) REFERENCES `sys_order` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of order_second_auth
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_admin
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin`;
CREATE TABLE `sys_admin` (
  `admin_id` int NOT NULL DEFAULT '1' COMMENT 'admin id',
  `role_id` int NOT NULL DEFAULT '1' COMMENT 'role id',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT 'username',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 's1mple' COMMENT 'nickname',
  `avatar` blob COMMENT 'avatar of profile',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT 'password',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT 'user description',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT 'user email address ',
  `phone_number` bigint NOT NULL COMMENT 'phone number',
  `balance` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT 'balance for futher event creating',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT 'log of manipulation',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`admin_id`),
  UNIQUE KEY `admin_id_index` (`admin_id`) USING BTREE,
  KEY `role_id_index` (`role_id`) USING BTREE,
  KEY `time_index` (`create_time`,`update_time`) USING BTREE,
  CONSTRAINT `admin_role_id_key` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of sys_admin
-- ----------------------------
BEGIN;
INSERT INTO `sys_admin` (`admin_id`, `role_id`, `username`, `nickname`, `avatar`, `password`, `description`, `email`, `phone_number`, `balance`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (1, 3, 'hades', 's1mple', NULL, 'hd123', 'I am the god', 'hades@hades.com', 13881111111, 9999999.00, '', '', '2022-06-22 13:19:07', '2022-06-22 13:19:09');
COMMIT;

-- ----------------------------
-- Table structure for sys_coupon
-- ----------------------------
DROP TABLE IF EXISTS `sys_coupon`;
CREATE TABLE `sys_coupon` (
  `coupon_id` int NOT NULL AUTO_INCREMENT,
  `coupon_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `coupon_type` int NOT NULL,
  `event_id` int NOT NULL,
  `is_specified` tinyint NOT NULL DEFAULT '0' COMMENT 'coupon is deliveried by host or admin',
  `assign_amount` int NOT NULL DEFAULT '0',
  `used_amount` int NOT NULL DEFAULT '0',
  `reveive_start_time` datetime NOT NULL COMMENT 'when can acquire',
  `receive_end_time` datetime NOT NULL,
  `valid_start_time` datetime NOT NULL COMMENT 'when can use',
  `valid_end_time` datetime NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`coupon_id`),
  UNIQUE KEY `coupon_id_index` (`coupon_id`) USING BTREE,
  KEY `coupon_type_index` (`coupon_type`) USING BTREE,
  KEY `event_id_index` (`event_id`) USING BTREE,
  KEY `receive_time_index` (`reveive_start_time`,`receive_end_time`) USING BTREE,
  KEY `valid_time_index` (`valid_start_time`,`valid_end_time`) USING BTREE,
  CONSTRAINT `coupon_coupon_type_key` FOREIGN KEY (`coupon_type`) REFERENCES `coupon_type` (`type_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `coupon_event_id_key` FOREIGN KEY (`event_id`) REFERENCES `sys_event` (`event_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of sys_coupon
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_event
-- ----------------------------
DROP TABLE IF EXISTS `sys_event`;
CREATE TABLE `sys_event` (
  `event_id` int NOT NULL AUTO_INCREMENT,
  `event_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `event_type` int NOT NULL DEFAULT '1' COMMENT 'event_type id',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT 'event description',
  `site_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT 'address of event',
  `site_description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'address additional info',
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `is_cancelled` tinyint NOT NULL DEFAULT '0' COMMENT 'whether event is cancelled or not',
  `star_level` decimal(10,0) NOT NULL COMMENT 'star level of events',
  `event_tag` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'tag of event for recommendation',
  `create_by` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'log of manipulation',
  `update_by` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`event_id`),
  UNIQUE KEY `event_id_index` (`event_id`) USING BTREE,
  KEY `event_type_index` (`event_type`) USING BTREE,
  KEY `start_level_index` (`star_level`) USING BTREE,
  KEY `event_tag_index` (`event_tag`) USING BTREE,
  KEY `time_index` (`create_time`,`update_time`) USING BTREE,
  CONSTRAINT `event_event_type_key` FOREIGN KEY (`event_type`) REFERENCES `event_type` (`type_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of sys_event
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_order
-- ----------------------------
DROP TABLE IF EXISTS `sys_order`;
CREATE TABLE `sys_order` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `event_id` int NOT NULL,
  `customer_id` int NOT NULL,
  `host_id` int NOT NULL,
  `payment_type` int NOT NULL DEFAULT '1' COMMENT 'payment type id',
  `payment_amount` decimal(10,2) NOT NULL DEFAULT '0.00',
  `is_paid` tinyint NOT NULL DEFAULT '0',
  `is_refund` tinyint NOT NULL DEFAULT '0',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_id`),
  UNIQUE KEY `order_id_index` (`order_id`) USING BTREE,
  KEY `event_id_index` (`event_id`) USING BTREE,
  KEY `customer_id_index` (`customer_id`) USING BTREE,
  KEY `host_id_index` (`host_id`) USING BTREE,
  KEY `payment_type_index` (`payment_type`) USING BTREE,
  KEY `time_index` (`create_time`,`update_time`) USING BTREE,
  CONSTRAINT `order_customer_id_key` FOREIGN KEY (`customer_id`) REFERENCES `sys_user_customer` (`customer_id`),
  CONSTRAINT `order_event_id_key` FOREIGN KEY (`event_id`) REFERENCES `sys_event` (`event_id`),
  CONSTRAINT `order_host_id_key` FOREIGN KEY (`host_id`) REFERENCES `sys_user_host` (`host_id`),
  CONSTRAINT `order_payment_type_key` FOREIGN KEY (`payment_type`) REFERENCES `order_payment_type` (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of sys_order
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` int NOT NULL AUTO_INCREMENT COMMENT 'role id',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT 'role name',
  `create_by` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'log of manipulation',
  `update_by` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_id_index` (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` (`role_id`, `role_name`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (1, 'customer', '', NULL, '2022-06-22 13:00:02', '2022-06-22 13:00:02');
INSERT INTO `sys_role` (`role_id`, `role_name`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (2, 'host', NULL, NULL, '2022-06-22 13:00:31', '2022-06-22 13:00:34');
INSERT INTO `sys_role` (`role_id`, `role_name`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (3, 'admin', NULL, NULL, '2022-06-22 13:00:45', '2022-06-22 13:00:47');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_customer
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_customer`;
CREATE TABLE `sys_user_customer` (
  `customer_id` int NOT NULL AUTO_INCREMENT COMMENT 'customer id',
  `role_id` int NOT NULL DEFAULT '1' COMMENT 'role id',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT 'username',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 's1mple' COMMENT 'nickname on profile',
  `avatar` blob COMMENT ' avatar binary object stream',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT 'password',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'a nerd' COMMENT 'user description',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT 'user email address',
  `phone_number` bigint NOT NULL COMMENT 'user phone number',
  `pref_tag` json DEFAULT NULL COMMENT 'tag for further recommendation',
  `balance` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT 'balance to book events',
  `is_verified` tinyint NOT NULL DEFAULT '0' COMMENT 'whether user is verified by email or not',
  `is_received` tinyint NOT NULL DEFAULT '0' COMMENT 'whether to receive promotion info',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT 'log for manipulation',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`customer_id`) USING BTREE,
  UNIQUE KEY `customer_id_index` (`customer_id`) USING BTREE,
  KEY `role_id_index` (`role_id`) USING BTREE,
  KEY `time_index` (`create_time`,`update_time`) USING BTREE,
  CONSTRAINT `customer_role_id_key` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of sys_user_customer
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_customer` (`customer_id`, `role_id`, `username`, `nickname`, `avatar`, `password`, `description`, `email`, `phone_number`, `pref_tag`, `balance`, `is_verified`, `is_received`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (2, 1, 'doom slayer', 's1mple', NULL, 'ds123', 'a nerd', 'dsds@gmail.com', 13883699123, NULL, 100.00, 1, 0, '', '', '2022-06-22 13:15:42', '2022-06-22 13:15:48');
INSERT INTO `sys_user_customer` (`customer_id`, `role_id`, `username`, `nickname`, `avatar`, `password`, `description`, `email`, `phone_number`, `pref_tag`, `balance`, `is_verified`, `is_received`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (3, 1, 'niko', 's1mple', NULL, 'nk123', 'a nerd', 'nikonikoni@gmai.com', 13883688123, NULL, 0.00, 1, 0, '', '', '2022-06-22 13:16:41', '2022-06-22 13:16:43');
INSERT INTO `sys_user_customer` (`customer_id`, `role_id`, `username`, `nickname`, `avatar`, `password`, `description`, `email`, `phone_number`, `pref_tag`, `balance`, `is_verified`, `is_received`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (4, 1, 'harden', 's1mple', NULL, 'hd123', 'a nerd', 'james@gmail.com', 13883677123, NULL, 20.00, 1, 0, '', '', '2022-06-22 13:17:22', '2022-06-22 13:17:24');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_host
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_host`;
CREATE TABLE `sys_user_host` (
  `host_id` int NOT NULL AUTO_INCREMENT COMMENT 'auto increment key',
  `role_id` int NOT NULL DEFAULT '1' COMMENT 'role id',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT 'username',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT 'password',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 's1mple' COMMENT 'nickname shows on profile',
  `avatar` blob COMMENT 'avatar binary object stream',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'a nerd' COMMENT 'user description',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT 'user email address',
  `phone_number` bigint NOT NULL COMMENT 'user phone number',
  `balance` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT 'balance to book events',
  `qualification` blob COMMENT 'host qualification for futher event creating',
  `is_auth` tinyint NOT NULL DEFAULT '0' COMMENT 'whether qualification is authorized or not',
  `is_verified` tinyint NOT NULL DEFAULT '0' COMMENT 'whether user is verified by email or not',
  `is_received` tinyint NOT NULL DEFAULT '0' COMMENT 'whether to receive promotion info',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT 'log for manipulation',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`host_id`),
  UNIQUE KEY `host_id_index` (`host_id`) USING BTREE,
  KEY `role_id_index` (`role_id`) USING BTREE,
  KEY `time_index` (`create_time`,`update_time`) USING BTREE,
  CONSTRAINT `host_role_id_key` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of sys_user_host
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_host` (`host_id`, `role_id`, `username`, `password`, `nickname`, `avatar`, `description`, `email`, `phone_number`, `balance`, `qualification`, `is_auth`, `is_verified`, `is_received`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (1, 2, 'music top', 'mt123', 's1mple', NULL, 'a nerd', 'musictop@mt.com', 13883633123, 10000.00, NULL, 1, 1, 0, '', '', '2022-06-22 13:02:55', '2022-06-22 13:02:57');
INSERT INTO `sys_user_host` (`host_id`, `role_id`, `username`, `password`, `nickname`, `avatar`, `description`, `email`, `phone_number`, `balance`, `qualification`, `is_auth`, `is_verified`, `is_received`, `create_by`, `update_by`, `create_time`, `update_time`) VALUES (2, 2, 'mystery campper', 'cp123', 's1mple', NULL, 'a nerd', 'mysterycampper@cp.com', 13883622123, 5000.00, NULL, 1, 1, 0, '', '', '2022-06-22 13:05:12', '2022-06-22 13:05:15');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
