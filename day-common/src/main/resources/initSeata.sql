# 为实现seata-1.3.0版本，分布式事务所创建的demo-sql
#参考与： http://seata.io/zh-cn/docs/ops/deploy-guide-beginner.html
#seata版本1.4.0：https://github.com/seata/seata/tree/1.4.0/script
#mysql： https://github.com/seata/seata/blob/1.4.0/script/client/at/db/mysql.sql
#在每一个client端(每一个微服务中)的数据库中创建的表SQL：
-- for AT mode you must to init this sql for you business database. the seata server not need it.
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE IF NOT EXISTS `undo_log`
(
    `branch_id`     BIGINT(20)   NOT NULL COMMENT 'branch transaction id',
    `xid`           VARCHAR(100) NOT NULL COMMENT 'global transaction id',
    `context`       VARCHAR(128) NOT NULL COMMENT 'undo_log context,such as serialization',
    `rollback_info` LONGBLOB     NOT NULL COMMENT 'rollback info',
    `log_status`    INT(11)      NOT NULL COMMENT '0:normal status,1:defense status',
    `log_created`   DATETIME(6)  NOT NULL COMMENT 'create datetime',
    `log_modified`  DATETIME(6)  NOT NULL COMMENT 'modify datetime',
    UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='AT transaction mode undo table';
#client端(也就是每一个微服务)所需配置的文件地址：
# https://github.com/seata/seata/tree/1.4.0/script/client/conf
##################部署seata-Server端-需要创建的MySQL数据库中的三张表
# 为seata服务端的sql语句：
-- -------------------------------- The script used when storeMode is 'db' --------------------------------
-- the table to store GlobalSession data
#首先创建 服务端的数据库
CREATE DATABASE `seata`;
#然后创建其服务端的所需要的表
DROP TABLE IF EXISTS `global_table`;
CREATE TABLE IF NOT EXISTS `global_table`
(
    `xid`                       VARCHAR(128) NOT NULL,
    `transaction_id`            BIGINT,
    `status`                    TINYINT      NOT NULL,
    `application_id`            VARCHAR(32),
    `transaction_service_group` VARCHAR(32),
    `transaction_name`          VARCHAR(128),
    `timeout`                   INT,
    `begin_time`                BIGINT,
    `application_data`          VARCHAR(2000),
    `gmt_create`                DATETIME,
    `gmt_modified`              DATETIME,
    PRIMARY KEY (`xid`),
    KEY `idx_gmt_modified_status` (`gmt_modified`, `status`),
    KEY `idx_transaction_id` (`transaction_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
DROP TABLE IF EXISTS `branch_table`;
-- the table to store BranchSession data
CREATE TABLE IF NOT EXISTS `branch_table`
(
    `branch_id`         BIGINT       NOT NULL,
    `xid`               VARCHAR(128) NOT NULL,
    `transaction_id`    BIGINT,
    `resource_group_id` VARCHAR(32),
    `resource_id`       VARCHAR(256),
    `branch_type`       VARCHAR(8),
    `status`            TINYINT,
    `client_id`         VARCHAR(64),
    `application_data`  VARCHAR(2000),
    `gmt_create`        DATETIME(6),
    `gmt_modified`      DATETIME(6),
    PRIMARY KEY (`branch_id`),
    KEY `idx_xid` (`xid`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
DROP TABLE IF EXISTS `lock_table`;
-- the table to store lock data
CREATE TABLE IF NOT EXISTS `lock_table`
(
    `row_key`        VARCHAR(128) NOT NULL,
    `xid`            VARCHAR(96),
    `transaction_id` BIGINT,
    `branch_id`      BIGINT       NOT NULL,
    `resource_id`    VARCHAR(256),
    `table_name`     VARCHAR(32),
    `pk`             VARCHAR(36),
    `gmt_create`     DATETIME,
    `gmt_modified`   DATETIME,
    PRIMARY KEY (`row_key`),
    KEY `idx_branch_id` (`branch_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


# -----------创建订单表---------------------------------------------------------------
# 创建  seata_order 数据库
CREATE DATABASE `seata_order`;
# 创建订单表
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`
(
    `id`             int(11)                                                 NOT NULL AUTO_INCREMENT,
    `order_no`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `user_id`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `commodity_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `status`         int(5)                                                  NULL DEFAULT 0,
    `amount`         double(14, 2)                                           NULL DEFAULT 0.00,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

# --------------创建用户表---------------------------------------------------------
#创建 seata_pay 数据库
CREATE DATABASE `seata_pay`;
#创建用户钱包表
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`
(
    `id`      int(11)                                                 NOT NULL AUTO_INCREMENT,
    `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `amount`  double(14, 2)                                           NULL DEFAULT 0.00,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;
-- ----------------------------
-- Records of account
-- ----------------------------
#初始化数据
INSERT INTO `account`
VALUES (1, '1', 4000.00);
INSERT INTO `account`
VALUES (2, '1001', 50000.00);
SET FOREIGN_KEY_CHECKS = 1;

#================================================================================================================================
#配置nacos，即，从nacos官网中下载稳定版都安装包
# 1、首先创建数据库
CREATE DATABASE `nacos_config`;
# 2、创建表，即执行nacos压缩包中的conf文件夹下的nacos-mysql.sql中的创建表的语句
# 3、将创建好的数据库，配置到nacos中，如以下配置：
### Count of DB:
#   db.num=1
#
# ### Connect URL of DB:
#   db.url.0=jdbc:mysql://127.0.0.1:3306/nacos_config?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useUnicode=true&useSSL=false&serverTimezone=UTC
#   db.user=root
#   db.password=123456

# 在本地启动nacos  命令：startup.cmd -m standalone
# 如果需要在docker中启动nacos，
# docker中安装nacos1.3.1
# 主要参考与：
# 1、 https://www.cnblogs.com/binz/p/12295346.html
# 2、 https://www.jianshu.com/p/e053f016371a
# 1. docker search nacos
# 2. docker pull nacos/nacos-server:1.3.1
# 3. docker run -d -p 8848:8848 -e MODE=standalone -v /usr/nacos/properties/custom.properties:/home/nacos/init.d/custom.properties -v /usr/nacos/logs:/home/nacos/logs --restart always --name nacos nacos/nacos-server:1.3.1
