/*
 Navicat Premium Dump SQL

 Source Server         : mysql_9.0_3309_123456
 Source Server Type    : MySQL
 Source Server Version : 90001 (9.0.1-commercial)
 Source Host           : 127.0.0.1:3309
 Source Schema         : seata

 Target Server Type    : MySQL
 Target Server Version : 90001 (9.0.1-commercial)
 File Encoding         : 65001

 Date: 18/12/2024 14:49:21
*/
-- 创建数据库：
CREATE DATABASE  IF NOT EXISTS  `seata`;
-- -------------------------------- The script used when storeMode is 'db' --------------------------------
-- the table to store GlobalSession data
USE seata;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for branch_table
-- ----------------------------
DROP TABLE IF EXISTS `branch_table`;
CREATE TABLE `branch_table`  (
  `branch_id` bigint NOT NULL,
  `xid` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `transaction_id` bigint NULL DEFAULT NULL,
  `resource_group_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `resource_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `branch_type` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` tinyint NULL DEFAULT NULL,
  `client_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `application_data` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gmt_create` datetime(6) NULL DEFAULT NULL,
  `gmt_modified` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`branch_id`) USING BTREE,
  INDEX `idx_xid`(`xid` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of branch_table
-- ----------------------------

-- ----------------------------
-- Table structure for distributed_lock
-- ----------------------------
DROP TABLE IF EXISTS `distributed_lock`;
CREATE TABLE `distributed_lock`  (
  `lock_key` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `lock_value` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `expire` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`lock_key`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of distributed_lock
-- ----------------------------
INSERT INTO `distributed_lock` VALUES ('AsyncCommitting', ' ', 0);
INSERT INTO `distributed_lock` VALUES ('Committing', ' ', 0);
INSERT INTO `distributed_lock` VALUES ('RetryCommitting', ' ', 0);
INSERT INTO `distributed_lock` VALUES ('RetryRollbacking', ' ', 0);
INSERT INTO `distributed_lock` VALUES ('Rollbacking', ' ', 0);
INSERT INTO `distributed_lock` VALUES ('TxTimeoutCheck', ' ', 0);
INSERT INTO `distributed_lock` VALUES ('UndologDelete', ' ', 0);

-- ----------------------------
-- Table structure for global_table
-- ----------------------------
DROP TABLE IF EXISTS `global_table`;
CREATE TABLE `global_table`  (
  `xid` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `transaction_id` bigint NULL DEFAULT NULL,
  `status` tinyint NOT NULL,
  `application_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `transaction_service_group` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `transaction_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `timeout` int NULL DEFAULT NULL,
  `begin_time` bigint NULL DEFAULT NULL,
  `application_data` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gmt_create` datetime NULL DEFAULT NULL,
  `gmt_modified` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`xid`) USING BTREE,
  INDEX `idx_status_gmt_modified`(`status` ASC, `gmt_modified` ASC) USING BTREE,
  INDEX `idx_transaction_id`(`transaction_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of global_table
-- ----------------------------

-- ----------------------------
-- Table structure for lock_table
-- ----------------------------
DROP TABLE IF EXISTS `lock_table`;
CREATE TABLE `lock_table`  (
  `row_key` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `xid` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `transaction_id` bigint NULL DEFAULT NULL,
  `branch_id` bigint NOT NULL,
  `resource_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `table_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `pk` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '0:locked ,1:rollbacking',
  `gmt_create` datetime NULL DEFAULT NULL,
  `gmt_modified` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`row_key`) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_branch_id`(`branch_id` ASC) USING BTREE,
  INDEX `idx_xid`(`xid` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lock_table
-- ----------------------------

-- ----------------------------
-- Table structure for vgroup_table
-- ----------------------------
DROP TABLE IF EXISTS `vgroup_table`;
CREATE TABLE `vgroup_table`  (
  `vGroup` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `namespace` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `cluster` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  UNIQUE INDEX `idx_vgroup_namespace_cluster`(`vGroup` ASC, `namespace` ASC, `cluster` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vgroup_table
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
/*
 Navicat Premium Dump SQL

 Source Server         : mysql_9.0_3309_123456
 Source Server Type    : MySQL
 Source Server Version : 90001 (9.0.1-commercial)
 Source Host           : 127.0.0.1:3309
 Source Schema         : seata_order

 Target Server Type    : MySQL
 Target Server Version : 90001 (9.0.1-commercial)
 File Encoding         : 65001

 Date: 18/12/2024 14:47:06
*/
-- 创建数据库：
CREATE DATABASE  IF NOT EXISTS  `seata_order`;
-- -------------------------------- The script used when storeMode is 'db' --------------------------------
-- the table to store GlobalSession data
USE seata_order;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `order_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '订单编号',
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户ID',
  `commodity_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '商品编码',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '订单状态',
  `amount` double(10, 2) NULL DEFAULT NULL COMMENT '订单金额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES (8, NULL, '1001', NULL, NULL, 300.00);
INSERT INTO `t_order` VALUES (9, NULL, '1001', NULL, '1', 300.00);
INSERT INTO `t_order` VALUES (10, NULL, '1001', NULL, '1', 300.00);

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `branch_id` bigint NOT NULL,
  `xid` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `context` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  `ext` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ux_undo_log`(`xid` ASC, `branch_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of undo_log
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
/*
 Navicat Premium Dump SQL

 Source Server         : mysql_9.0_3309_123456
 Source Server Type    : MySQL
 Source Server Version : 90001 (9.0.1-commercial)
 Source Host           : 127.0.0.1:3309
 Source Schema         : seata_pay

 Target Server Type    : MySQL
 Target Server Version : 90001 (9.0.1-commercial)
 File Encoding         : 65001

 Date: 18/12/2024 14:47:45
*/
-- 创建数据库：
CREATE DATABASE  IF NOT EXISTS  `seata_pay`;
-- -------------------------------- The script used when storeMode is 'db' --------------------------------
-- the table to store GlobalSession data
USE seata_pay;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户ID',
  `amount` double(10, 2) NULL DEFAULT NULL COMMENT '金额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户账户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (12, '1001', 7100.00);

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `branch_id` bigint NOT NULL,
  `xid` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `context` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  `ext` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ux_undo_log`(`xid` ASC, `branch_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of undo_log
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
