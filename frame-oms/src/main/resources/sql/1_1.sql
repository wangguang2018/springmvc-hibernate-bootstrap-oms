/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : frame_oms

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-06-15 14:53:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dm_activity
-- ----------------------------
DROP TABLE IF EXISTS `dm_activity`;
CREATE TABLE `dm_activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `agent_id` int(11) DEFAULT NULL COMMENT '代理商ID',
  `join_count` int(11) NOT NULL COMMENT '参与人数',
  `title` varchar(100) DEFAULT NULL COMMENT '主标题',
  `sub_title` varchar(100) DEFAULT NULL COMMENT '副标题',
  `content` varchar(2000) DEFAULT NULL COMMENT '活动内容',
  `img` varchar(2000) DEFAULT NULL COMMENT '活动封面图',
  `bonus` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '奖金',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '活动类型1:集五福活动（预留字段）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='活动';

-- ----------------------------
-- Table structure for dm_activity_apply_record
-- ----------------------------
DROP TABLE IF EXISTS `dm_activity_apply_record`;
CREATE TABLE `dm_activity_apply_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_id` int(11) NOT NULL COMMENT '活动ID',
  `member_id` int(11) NOT NULL COMMENT '用户ID',
  `alipay_account` varchar(100) DEFAULT NULL COMMENT '支付宝账号',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '申请状态:0默认 1完成 -1失败',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8mb4 COMMENT='活动记录申请表';

-- ----------------------------
-- Table structure for dm_agent
-- ----------------------------
DROP TABLE IF EXISTS `dm_agent`;
CREATE TABLE `dm_agent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '代理商名称',
  `phone` varchar(100) DEFAULT NULL COMMENT '联系电话',
  `contact` varchar(100) DEFAULT NULL COMMENT '联系人',
  `mail` varchar(128) DEFAULT '' COMMENT '邮箱地址',
  `admin_id` int(11) NOT NULL,
  `secret_key` varchar(200) DEFAULT NULL COMMENT '代理商唯一密钥',
  `jpush_key` varchar(100) DEFAULT NULL COMMENT '极光key',
  `jpush_secret` varchar(100) DEFAULT NULL COMMENT '极光secret',
  `alipay_app_id` varchar(100) DEFAULT NULL,
  `alipay_private_key` varchar(3000) DEFAULT NULL,
  `alipay_public_key` varchar(3000) DEFAULT NULL COMMENT '支付宝公钥',
  `alipay_app_public_key` varchar(3000) DEFAULT NULL COMMENT '支付宝应用公钥',
  `wx_api_cert_name` varchar(50) DEFAULT NULL COMMENT '微信证书文件名',
  `wx_app_id` varchar(50) DEFAULT NULL,
  `wx_mch_id` varchar(100) DEFAULT NULL COMMENT '微信商户号',
  `wx_pay_key` varchar(500) DEFAULT NULL COMMENT '微信支付密钥',
  `wx_app_secret` varchar(100) DEFAULT NULL COMMENT '微信app端secret',
  `wx_gzh_app_id` varchar(100) DEFAULT NULL COMMENT '微信公众号APPID',
  `wx_gzh_app_secret` varchar(100) DEFAULT NULL COMMENT '微信公众号secret',
  `sms_account` varchar(20) DEFAULT NULL COMMENT '短信账号',
  `sms_pwd` varchar(30) DEFAULT NULL COMMENT '短信密码',
  `sms_url` varchar(200) DEFAULT NULL COMMENT '短信URL',
  `sms_signature` varchar(10) DEFAULT NULL COMMENT '短信签名',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1正常 0禁用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  `type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1代理商 2渠道商',
  `pid` int(11) NOT NULL DEFAULT '0' COMMENT '父级代理商id',
  `alipay_notify_url` varchar(60) DEFAULT NULL COMMENT '支付宝回调url',
  `wechat_notify_url` varchar(60) DEFAULT NULL COMMENT '微信回调url',
  `zego_id` varchar(50) DEFAULT NULL COMMENT '即构的id',
  `zego_key` varchar(255) DEFAULT NULL COMMENT '即构的key',
  `zego_server` varchar(50) DEFAULT NULL COMMENT '即构的server',
  `huanxin_org_name` varchar(50) DEFAULT NULL COMMENT '环信的orgName',
  `huanxin_app_name` varchar(50) DEFAULT NULL COMMENT '环信的appName',
  `huanxin_client_id` varchar(50) DEFAULT NULL COMMENT '环信的clientid',
  `huanxin_client_secret` varchar(50) DEFAULT NULL COMMENT '环信的clientSecret',
  `mail_smtp` varchar(128) DEFAULT '' COMMENT '邮箱主机地址',
  `mail_from` varchar(128) DEFAULT '' COMMENT '发件邮箱',
  `mail_password` varchar(128) DEFAULT '' COMMENT '邮箱密码',
  `share_url` varchar(300) DEFAULT NULL COMMENT '代理商分享用URL',
  `endpoint` varchar(300) DEFAULT NULL COMMENT 'OSS服务器的endpoint',
  `access_key_id` varchar(300) DEFAULT NULL COMMENT 'OSS服务器的access_key_id',
  `access_key_secret` varchar(300) DEFAULT NULL COMMENT 'OSS服务器的access_key_secret',
  `bucket_name` varchar(300) DEFAULT NULL COMMENT 'OSS服务器的bucket_name',
  `bucket_url` varchar(300) DEFAULT NULL COMMENT 'OSS服务器的bucket_url',
  `xcx_app_id` varchar(300) DEFAULT NULL COMMENT '微信小程序appId',
  `xcx_app_secret` varchar(300) DEFAULT NULL COMMENT '微信小程序appSecret',
  `wx_gzh_mch_id` varchar(100) DEFAULT NULL COMMENT '微信公众号商户号',
  `wx_gzh_pay_key` varchar(500) DEFAULT NULL COMMENT '微信公众号支付密钥',
  `wx_gzh_cert_name` varchar(50) DEFAULT NULL COMMENT '微信公众号证书文件名',
  `wx_gzh_notify_url` varchar(60) DEFAULT NULL COMMENT '微信公众号支付回调',
  `xcx_mch_id` varchar(32) DEFAULT NULL COMMENT '微信小程序商户号',
  `xcx_pay_key` varchar(32) DEFAULT NULL COMMENT '微信小程序支付秘钥',
  `xcx_cert_name` varchar(128) DEFAULT NULL COMMENT '微信小程序证书路径',
  `xcx_notify_url` varchar(256) DEFAULT NULL COMMENT '微信小程序通知地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COMMENT='代理商';

-- ----------------------------
-- Table structure for dm_agent_config
-- ----------------------------
DROP TABLE IF EXISTS `dm_agent_config`;
CREATE TABLE `dm_agent_config` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `agent_id` int(11) unsigned NOT NULL COMMENT '代理商编号',
  `key` varchar(128) NOT NULL COMMENT '键',
  `value` varchar(128) NOT NULL COMMENT '值',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `flag` tinyint(4) DEFAULT '1' COMMENT '删除标志位：0未删除 1已删除',
  PRIMARY KEY (`id`),
  KEY `idx_flag` (`flag`) USING BTREE,
  KEY `key_agent_id_key` (`agent_id`,`key`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代理商配置表';

-- ----------------------------
-- Table structure for dm_agent_feature_config
-- ----------------------------
DROP TABLE IF EXISTS `dm_agent_feature_config`;
CREATE TABLE `dm_agent_feature_config` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `agent_id` int(11) unsigned NOT NULL COMMENT '代理商编号',
  `feature_key` varchar(128) NOT NULL COMMENT '功能键值',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `flag` tinyint(4) DEFAULT '1' COMMENT '删除标志位：0未删除 1已删除',
  `switch_value` tinyint(3) unsigned DEFAULT '0' COMMENT '开关：0关 1开',
  PRIMARY KEY (`id`),
  KEY `idx_flag` (`flag`) USING BTREE,
  KEY `key_agent_id_feature_key` (`agent_id`,`feature_key`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=253 DEFAULT CHARSET=utf8 COMMENT='功能配置表';

-- ----------------------------
-- Table structure for dm_appeal
-- ----------------------------
DROP TABLE IF EXISTS `dm_appeal`;
CREATE TABLE `dm_appeal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reason` varchar(200) NOT NULL COMMENT '申诉理由',
  `member_id` int(11) NOT NULL COMMENT '申诉人',
  `machine_id` int(11) NOT NULL COMMENT '机器编号',
  `result` varchar(200) DEFAULT NULL COMMENT '处理结果',
  `status` tinyint(4) DEFAULT NULL COMMENT '0申诉中 1 申诉成功 2申诉失败',
  `play_time` datetime NOT NULL COMMENT '抓取时间',
  `agent_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  `type` tinyint(4) DEFAULT '1' COMMENT '申诉类型 1退钻石 2：娃娃 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=275 DEFAULT CHARSET=utf8mb4 COMMENT='申诉';

-- ----------------------------
-- Table structure for dm_banner
-- ----------------------------
DROP TABLE IF EXISTS `dm_banner`;
CREATE TABLE `dm_banner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `img_url` varchar(200) DEFAULT NULL COMMENT '图片地址',
  `url` varchar(2000) DEFAULT NULL COMMENT 'banner url',
  `machine_sn` varchar(20) DEFAULT NULL COMMENT '机器',
  `content` longtext COMMENT 'banner富文本',
  `type` tinyint(4) DEFAULT '1' COMMENT '1机器 2充值 3url',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `agent_id` int(11) DEFAULT NULL COMMENT '代理商ID',
  `flag` tinyint(4) DEFAULT '1',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COMMENT='banner广告';

-- ----------------------------
-- Table structure for dm_channel
-- ----------------------------
DROP TABLE IF EXISTS `dm_channel`;
CREATE TABLE `dm_channel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `agent_id` int(11) DEFAULT NULL COMMENT '代理商ID',
  `channel_key` varchar(100) NOT NULL COMMENT '渠道商key',
  `channel_name` varchar(100) NOT NULL COMMENT '渠道商名称',
  `share_url` varchar(200) NOT NULL COMMENT '分享链接',
  `pid` int(11) DEFAULT NULL COMMENT '父级渠道商ID',
  `profit_rate` int(4) DEFAULT '0' COMMENT '分成收益%',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `idx_channle_no` (`channel_key`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8mb4 COMMENT='渠道商';

-- ----------------------------
-- Table structure for dm_channel_charge_log
-- ----------------------------
DROP TABLE IF EXISTS `dm_channel_charge_log`;
CREATE TABLE `dm_channel_charge_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `channel_id` int(11) DEFAULT NULL COMMENT '渠道商ID',
  `charge_log_id` int(11) DEFAULT NULL COMMENT '充值记录ID',
  `member_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `date` datetime DEFAULT NULL COMMENT '日期',
  `profit_rate` int(4) DEFAULT '0' COMMENT '分成收益%',
  `profit_money` decimal(10,2) DEFAULT '0.00' COMMENT '分成金额',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8mb4 COMMENT='渠道商下的用户充值记录';

-- ----------------------------
-- Table structure for dm_channel_daily_summary
-- ----------------------------
DROP TABLE IF EXISTS `dm_channel_daily_summary`;
CREATE TABLE `dm_channel_daily_summary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `channel_id` int(11) DEFAULT NULL COMMENT '渠道商ID',
  `daily_total_money` decimal(10,2) NOT NULL COMMENT '每天充值总金额',
  `daily_profit_money` decimal(10,2) NOT NULL COMMENT '每天充值总收益',
  `daily_increase_num` int(11) NOT NULL COMMENT '每天新增人数',
  `date` datetime NOT NULL COMMENT '日期',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=506 DEFAULT CHARSET=utf8mb4 COMMENT='渠道商按天汇总收益';

-- ----------------------------
-- Table structure for dm_channel_monthly_summary
-- ----------------------------
DROP TABLE IF EXISTS `dm_channel_monthly_summary`;
CREATE TABLE `dm_channel_monthly_summary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `channel_id` int(11) DEFAULT NULL COMMENT '渠道商ID',
  `month` int(11) DEFAULT NULL COMMENT '月份',
  `year` int(11) DEFAULT NULL COMMENT '年份',
  `monthly_total_money` decimal(10,2) NOT NULL COMMENT '每月充值总金额',
  `monthly_profit_money` decimal(10,2) NOT NULL COMMENT '每月充值总收益',
  `monthly_increase_num` int(11) NOT NULL COMMENT '每月新增人数',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否结算：1未结算 2结算',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8mb4 COMMENT='渠道商按月汇总收益';

-- ----------------------------
-- Table structure for dm_channel_user
-- ----------------------------
DROP TABLE IF EXISTS `dm_channel_user`;
CREATE TABLE `dm_channel_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `channel_id` int(11) DEFAULT NULL COMMENT '渠道商ID',
  `account` varchar(100) NOT NULL COMMENT '渠道商登录账号',
  `password` varchar(100) NOT NULL COMMENT '渠道商登录密码',
  `salt` varchar(55) NOT NULL COMMENT '密码盐',
  `nickname` varchar(100) NOT NULL COMMENT '用户昵称',
  `mobile` varchar(55) NOT NULL COMMENT '手机号',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8mb4 COMMENT='渠道商登录用户';

-- ----------------------------
-- Table structure for dm_charge_log
-- ----------------------------
DROP TABLE IF EXISTS `dm_charge_log`;
CREATE TABLE `dm_charge_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_sn` varchar(100) DEFAULT NULL COMMENT '充值订单号',
  `trade_sn` varchar(100) DEFAULT NULL COMMENT '外部订单号',
  `price` decimal(10,2) NOT NULL COMMENT '充值金额',
  `money` decimal(10,2) NOT NULL COMMENT '游戏币',
  `member_id` int(11) NOT NULL COMMENT '用户ID',
  `type` tinyint(4) DEFAULT NULL COMMENT '1支付宝 2微信 3:玩吧安卓 4：玩吧IOS',
  `status` tinyint(4) DEFAULT NULL COMMENT '0未支付 1已完成',
  `agent_id` int(11) DEFAULT NULL COMMENT '代理商ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  `buy_type` tinyint(4) DEFAULT '3' COMMENT '充值类型： 1:周卡 2：月卡  3.普通钻石',
  `option_id` int(11) DEFAULT NULL COMMENT '充值对应的选项ID',
  `channel_id` int(11) DEFAULT NULL COMMENT '渠道推广商Id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1555 DEFAULT CHARSET=utf8mb4 COMMENT='充值记录';

-- ----------------------------
-- Table structure for dm_charge_option
-- ----------------------------
DROP TABLE IF EXISTS `dm_charge_option`;
CREATE TABLE `dm_charge_option` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `price` decimal(10,2) NOT NULL COMMENT '充值金额',
  `money` decimal(10,2) NOT NULL COMMENT '游戏币',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `sort` int(11) DEFAULT '0',
  `agent_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  `mark` tinyint(4) DEFAULT '1' COMMENT '标记 1普通 2热销 3推荐 4限购 5限时',
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `agentId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for dm_child_task
-- ----------------------------
DROP TABLE IF EXISTS `dm_child_task`;
CREATE TABLE `dm_child_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task_id` int(11) NOT NULL COMMENT '父级任务Id',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `goal` int(4) NOT NULL DEFAULT '0' COMMENT '完成子任务的目标',
  `reward` int(4) NOT NULL DEFAULT '0' COMMENT '完成子任务的奖励',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `display` int(11) NOT NULL COMMENT '1：显示  0：隐藏',
  `task_key` varchar(55) DEFAULT NULL COMMENT '任务关键字',
  `agent_id` int(11) NOT NULL COMMENT '代理商Id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8 COMMENT='子任务系统';

-- ----------------------------
-- Table structure for dm_daily_share_task_record
-- ----------------------------
DROP TABLE IF EXISTS `dm_daily_share_task_record`;
CREATE TABLE `dm_daily_share_task_record` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `agent_id` int(11) unsigned NOT NULL COMMENT '代理商编号',
  `member_id` int(11) unsigned NOT NULL COMMENT '会员编号',
  `date` datetime NOT NULL COMMENT '日期',
  `share_friend_count` int(10) unsigned DEFAULT '0' COMMENT '分享好友次数',
  `share_moment_count` int(10) unsigned DEFAULT '0' COMMENT '分享朋友圈次数',
  `share_friend_received_count` int(10) unsigned DEFAULT '0' COMMENT '分享好友领取次数',
  `share_moment_received_count` int(10) unsigned DEFAULT '0' COMMENT '分享朋友圈领取次数',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `flag` tinyint(4) DEFAULT '1' COMMENT '删除标志位：0未删除 1已删除',
  PRIMARY KEY (`id`),
  KEY `idx_flag` (`flag`) USING BTREE,
  KEY `idx_agent_id` (`agent_id`) USING BTREE,
  KEY `idx_member_id` (`member_id`) USING BTREE,
  KEY `idx_date` (`date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf8 COMMENT='每日分享任务记录';

-- ----------------------------
-- Table structure for dm_daily_task_record
-- ----------------------------
DROP TABLE IF EXISTS `dm_daily_task_record`;
CREATE TABLE `dm_daily_task_record` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `agent_id` int(11) unsigned NOT NULL COMMENT '代理商编号',
  `member_id` int(11) unsigned NOT NULL COMMENT '会员编号',
  `date` datetime NOT NULL COMMENT '日期',
  `shared_count` int(10) unsigned DEFAULT '0' COMMENT '分享次数',
  `invited_count` int(10) unsigned DEFAULT '0' COMMENT '邀请次数',
  `grabbed_count` int(10) unsigned DEFAULT '0' COMMENT '抓取次数',
  `received` int(10) unsigned DEFAULT '0' COMMENT '是否领取：0未领取 1领取',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `flag` tinyint(4) DEFAULT '1' COMMENT '删除标志位：0未删除 1已删除',
  PRIMARY KEY (`id`),
  KEY `idx_flag` (`flag`) USING BTREE,
  KEY `idx_agent_id` (`agent_id`) USING BTREE,
  KEY `idx_member_id` (`member_id`) USING BTREE,
  KEY `idx_date` (`date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=296 DEFAULT CHARSET=utf8 COMMENT='每日任务记录表';

-- ----------------------------
-- Table structure for dm_doll_log
-- ----------------------------
DROP TABLE IF EXISTS `dm_doll_log`;
CREATE TABLE `dm_doll_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(1000) DEFAULT NULL COMMENT '抓取视频地址',
  `member_id` int(11) NOT NULL COMMENT '用户ID',
  `machine_id` int(11) DEFAULT NULL COMMENT '机器ID',
  `product_id` int(11) NOT NULL COMMENT '娃娃ID',
  `product_name` varchar(100) NOT NULL COMMENT '娃娃名称',
  `product_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '娃娃价格',
  `product_imgs` varchar(2000) DEFAULT NULL,
  `exchange_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '兑换状态 0未兑换 1已兑换',
  `status` tinyint(4) NOT NULL COMMENT '0 成功 1 失败 2游戏中',
  `appeal_id` varchar(255) DEFAULT NULL COMMENT '申诉ID',
  `agent_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5048 DEFAULT CHARSET=utf8mb4 COMMENT='抓取娃娃记录';

-- ----------------------------
-- Table structure for dm_exchange
-- ----------------------------
DROP TABLE IF EXISTS `dm_exchange`;
CREATE TABLE `dm_exchange` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `agent_id` int(11) unsigned NOT NULL COMMENT '代理商编号',
  `member_id` int(11) unsigned NOT NULL COMMENT '会员编号',
  `goods_id` int(11) unsigned NOT NULL COMMENT '商品编号',
  `goods_name` varchar(64) NOT NULL COMMENT '商品名称',
  `goods_thumb` varchar(256) NOT NULL COMMENT '商品缩略图',
  `goods_points` int(11) DEFAULT NULL COMMENT '商品价格（积分）',
  `total_points` int(11) DEFAULT NULL COMMENT '商品总价（积分）',
  `quantity` int(10) unsigned NOT NULL COMMENT '商品数量',
  `product_id` int(10) unsigned DEFAULT NULL COMMENT '产品编号',
  `diamond_num` int(11) unsigned DEFAULT NULL COMMENT '钻石数量',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `flag` tinyint(4) DEFAULT '1' COMMENT '删除标志位：0未删除 1已删除',
  `exchange_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '兑换类型1积分 2碎片',
  `fragment_id` int(11) DEFAULT NULL COMMENT '碎片ID',
  `fragment_name` varchar(30) DEFAULT NULL COMMENT '碎片名称',
  `fragment_img` varchar(100) DEFAULT NULL COMMENT '碎片图片',
  `fragment_num` int(11) DEFAULT NULL COMMENT '碎片数量',
  `doll_num` int(6) DEFAULT '0' COMMENT '兑换需要的娃娃数量',
  PRIMARY KEY (`id`),
  KEY `idx_flag` (`flag`) USING BTREE,
  KEY `idx_agent_id` (`agent_id`) USING BTREE,
  KEY `idx_member_id` (`member_id`) USING BTREE,
  KEY `idx_goods_id` (`goods_id`) USING BTREE,
  KEY `idx_product_id` (`product_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=312 DEFAULT CHARSET=utf8 COMMENT='积分商品兑换记录';

-- ----------------------------
-- Table structure for dm_exchange_detail
-- ----------------------------
DROP TABLE IF EXISTS `dm_exchange_detail`;
CREATE TABLE `dm_exchange_detail` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `exchange_id` int(11) unsigned NOT NULL COMMENT '兑换编号',
  `product_id` int(11) unsigned NOT NULL COMMENT '兑换商品编号',
  `product_name` varchar(64) NOT NULL COMMENT '商品名称',
  `product_thumb` varchar(256) NOT NULL COMMENT '商品缩略图',
  `quantity` int(6) unsigned NOT NULL COMMENT '商品数量',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `flag` tinyint(4) DEFAULT '1' COMMENT '删除标志位：0未删除 1已删除',
  PRIMARY KEY (`id`),
  KEY `idx_flag` (`flag`) USING BTREE,
  KEY `idx_exchange_id` (`exchange_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='兑换详情记录';

-- ----------------------------
-- Table structure for dm_invite_record
-- ----------------------------
DROP TABLE IF EXISTS `dm_invite_record`;
CREATE TABLE `dm_invite_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) DEFAULT NULL COMMENT '邀请人',
  `invite_member_id` int(11) DEFAULT NULL COMMENT '被邀请人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COMMENT='邀请记录';

-- ----------------------------
-- Table structure for dm_live_room
-- ----------------------------
DROP TABLE IF EXISTS `dm_live_room`;
CREATE TABLE `dm_live_room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `live_code` varchar(20) DEFAULT NULL COMMENT '直播码',
  `room_num` varchar(20) DEFAULT NULL COMMENT '房间号',
  `rtmp_url` varchar(500) DEFAULT NULL COMMENT 'rtmp播放地址',
  `flv_url` varchar(500) DEFAULT NULL COMMENT 'flv播放地址',
  `hls_url` varchar(500) DEFAULT NULL COMMENT 'hls播放地址',
  `push_url` varchar(500) DEFAULT NULL COMMENT '推流地址',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `live_code` (`live_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for dm_log_member_money
-- ----------------------------
DROP TABLE IF EXISTS `dm_log_member_money`;
CREATE TABLE `dm_log_member_money` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `flag` tinyint(4) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `member_id` int(11) DEFAULT NULL,
  `money` decimal(10,2) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `source` tinyint(4) NOT NULL,
  `type` tinyint(4) NOT NULL,
  `amount` decimal(10,2) unsigned DEFAULT NULL COMMENT '剩余积分',
  `agent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6306 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for dm_machine
-- ----------------------------
DROP TABLE IF EXISTS `dm_machine`;
CREATE TABLE `dm_machine` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sn` varchar(200) DEFAULT NULL COMMENT '机器设备号',
  `client_ip` varchar(55) DEFAULT '' COMMENT '设备端IP',
  `version_code` varchar(55) DEFAULT '' COMMENT '控制端版本号',
  `product_id` int(11) DEFAULT NULL COMMENT '机器所对应的娃娃ID',
  `live_room1` int(11) DEFAULT NULL COMMENT '直播1号流',
  `live_room2` int(11) DEFAULT NULL COMMENT '直播2号流',
  `live_room_code` varchar(55) DEFAULT '' COMMENT '推流编码(即构)',
  `chat_room` varchar(100) DEFAULT NULL COMMENT '聊天室ID',
  `member_count` int(11) NOT NULL DEFAULT '0' COMMENT '房间数量',
  `is_top` int(2) DEFAULT '0' COMMENT '设为首页',
  `online` int(2) DEFAULT '1' COMMENT '1：在线 0：离线',
  `status` tinyint(4) DEFAULT NULL COMMENT '0空闲 1游戏中 2游戏结束',
  `agent_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  `sort` int(11) DEFAULT '0',
  `fix_status` tinyint(4) DEFAULT '0' COMMENT '维护状态 0：非维护 1:系统设为维护 2：人工设置维护',
  `mark` varchar(100) DEFAULT NULL COMMENT '机器编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=223 DEFAULT CHARSET=utf8mb4 COMMENT='娃娃机';

-- ----------------------------
-- Table structure for dm_machine_set
-- ----------------------------
DROP TABLE IF EXISTS `dm_machine_set`;
CREATE TABLE `dm_machine_set` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `machine_id` int(11) DEFAULT NULL COMMENT '机器ID',
  `game_mode` int(2) DEFAULT '1' COMMENT '游戏模式',
  `strong_voltage` varchar(10) DEFAULT NULL COMMENT '强力电压',
  `small_voltage` varchar(10) DEFAULT NULL COMMENT '弱力电压',
  `change_time` varchar(10) DEFAULT NULL COMMENT '强转弱时间',
  `probability` int(4) NOT NULL DEFAULT '0' COMMENT '中奖概率',
  `status` int(4) NOT NULL DEFAULT '0' COMMENT '状态 1：等待处理，2：设置成功 3：设置失败',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  `master_exposure` int(6) DEFAULT NULL COMMENT '主摄像头曝光',
  `slave_exposure` int(6) DEFAULT NULL COMMENT '副摄像头曝光',
  `encode_level` int(4) DEFAULT NULL COMMENT '分辨率',
  `exposure_mode` tinyint(4) DEFAULT '1' COMMENT '曝光模式 1自定义曝光 3自动曝光',
  `master_brightness` int(6) DEFAULT NULL COMMENT '主摄像头亮度',
  `slave_brightness` int(6) DEFAULT NULL COMMENT '从摄像头亮度',
  `master_saturability` int(6) DEFAULT NULL COMMENT '主摄像头饱和度',
  `slave_saturability` int(6) DEFAULT NULL COMMENT '从摄像头饱和度',
  `width` int(6) DEFAULT NULL COMMENT '自定义分辨率宽度',
  `height` int(6) DEFAULT NULL COMMENT '自定义分辨率高度',
  `open_exposure_setting` tinyint(4) DEFAULT '0' COMMENT '曝光值设置 0关 1开',
  `test_stream_switch` tinyint(4) DEFAULT '0' COMMENT '是否使用测试流 0:否  1:是',
  `rotation_angle` tinyint(4) DEFAULT '0' COMMENT '旋转角度 0:不旋转  1:旋转90度  2:旋转180度 3:旋转270度',
  `change_weak` int(2) DEFAULT '0' COMMENT '到顶转弱抓力: 0关闭，1开启',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COMMENT='娃娃机设置';

-- ----------------------------
-- Table structure for dm_machine_update_record
-- ----------------------------
DROP TABLE IF EXISTS `dm_machine_update_record`;
CREATE TABLE `dm_machine_update_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `machine_id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL COMMENT '升级标题',
  `version_code` varchar(55) NOT NULL COMMENT '版本号',
  `content` varchar(255) NOT NULL COMMENT '内容',
  `update_url` varchar(255) NOT NULL COMMENT '升级链接',
  `flag` tinyint(4) DEFAULT '1' COMMENT '1正常 -1删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='机器软件包升级记录';

-- ----------------------------
-- Table structure for dm_member
-- ----------------------------
DROP TABLE IF EXISTS `dm_member`;
CREATE TABLE `dm_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `alias_id` varchar(100) DEFAULT NULL COMMENT '别名ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `avatar` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `mobile` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `openid` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `points` int(11) NOT NULL DEFAULT '0' COMMENT '积分',
  `money` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '游戏币',
  `register_time` datetime DEFAULT NULL,
  `salt` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `status` tinyint(4) NOT NULL,
  `remark` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `hx_id` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '环信帐号ID',
  `hx_pwd` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '环信帐号密码',
  `invite_code` varchar(8) CHARACTER SET utf8 DEFAULT NULL,
  `agent_id` int(11) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT '',
  `platform` tinyint(4) DEFAULT '1' COMMENT '登录平台',
  `oauth_id` varchar(200) DEFAULT NULL,
  `week_expire_date` datetime DEFAULT NULL COMMENT '周卡到期时间',
  `mouth_expire_date` datetime DEFAULT NULL COMMENT '月卡到期时间',
  `channel_id` int(11) DEFAULT NULL COMMENT '渠道商ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=291 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for dm_member_address
-- ----------------------------
DROP TABLE IF EXISTS `dm_member_address`;
CREATE TABLE `dm_member_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) NOT NULL DEFAULT '1',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `city_name` varchar(255) DEFAULT NULL,
  `consignee` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `district_name` varchar(255) DEFAULT NULL,
  `is_default` tinyint(4) DEFAULT NULL,
  `member_id` int(11) DEFAULT NULL,
  `address` varchar(200) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '地址',
  `mobile` varchar(255) DEFAULT NULL,
  `province_name` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `city_id` int(11) DEFAULT NULL,
  `district_id` int(11) DEFAULT NULL,
  `province_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=211 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for dm_member_card
-- ----------------------------
DROP TABLE IF EXISTS `dm_member_card`;
CREATE TABLE `dm_member_card` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `agent_id` int(11) DEFAULT NULL,
  `title` varchar(55) DEFAULT NULL COMMENT '标题',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型：1周卡 2：月卡',
  `price` decimal(10,2) NOT NULL COMMENT '充值金额',
  `money` int(6) NOT NULL COMMENT '立即到账游戏币',
  `day_money` int(6) NOT NULL COMMENT '每天额外赠送的游戏币',
  `short_desc` varchar(100) DEFAULT NULL COMMENT '简介',
  `description` varchar(255) DEFAULT NULL COMMENT '详细说明',
  `mark` tinyint(4) DEFAULT '1' COMMENT '标记 1:普通 2：热销 3：推荐',
  `sort` int(11) DEFAULT '0',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='会员卡';

-- ----------------------------
-- Table structure for dm_member_card_record
-- ----------------------------
DROP TABLE IF EXISTS `dm_member_card_record`;
CREATE TABLE `dm_member_card_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) NOT NULL COMMENT '会员ID',
  `agent_id` int(11) NOT NULL COMMENT '代理商ID',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型：1周卡 2：月卡',
  `day_money` int(6) NOT NULL COMMENT '每天额外赠送的游戏币',
  `expire_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '到期时间',
  `total_money` int(6) NOT NULL COMMENT '已累计领取的游戏币',
  `receive_record` text COMMENT '领取记录',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8 COMMENT='购买会员卡记录';

-- ----------------------------
-- Table structure for dm_member_fragment
-- ----------------------------
DROP TABLE IF EXISTS `dm_member_fragment`;
CREATE TABLE `dm_member_fragment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) NOT NULL COMMENT '用户ID',
  `fragment_id` int(11) NOT NULL COMMENT '碎片ID',
  `fragment_num` int(11) NOT NULL COMMENT '碎片数量',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8mb4 COMMENT='用户拥有的碎片';

-- ----------------------------
-- Table structure for dm_member_fragment_log
-- ----------------------------
DROP TABLE IF EXISTS `dm_member_fragment_log`;
CREATE TABLE `dm_member_fragment_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) NOT NULL COMMENT '用户ID',
  `fragment_id` int(11) NOT NULL COMMENT '碎片ID',
  `fragment_name` varchar(30) NOT NULL COMMENT '碎片名称',
  `fragment_num` int(11) NOT NULL COMMENT '碎片数量',
  `type` tinyint(4) NOT NULL COMMENT '1获得 2消耗',
  `remark` varchar(200) DEFAULT NULL COMMENT '说明',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=386 DEFAULT CHARSET=utf8mb4 COMMENT='用户获取碎片记录';

-- ----------------------------
-- Table structure for dm_member_login_log
-- ----------------------------
DROP TABLE IF EXISTS `dm_member_login_log`;
CREATE TABLE `dm_member_login_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) NOT NULL COMMENT '会员ID',
  `agent_id` int(11) NOT NULL COMMENT '代理商ID',
  `register_date` datetime DEFAULT NULL COMMENT '注册日期',
  `login_date` datetime DEFAULT NULL COMMENT '登录日期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `member_date_uq` (`member_id`,`login_date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for dm_member_oauth
-- ----------------------------
DROP TABLE IF EXISTS `dm_member_oauth`;
CREATE TABLE `dm_member_oauth` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  `type` tinyint(4) DEFAULT NULL,
  `member_id` int(10) DEFAULT NULL,
  `oauth_id` varchar(60) DEFAULT NULL,
  `access_token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for dm_member_point_records
-- ----------------------------
DROP TABLE IF EXISTS `dm_member_point_records`;
CREATE TABLE `dm_member_point_records` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `agent_id` int(11) unsigned NOT NULL COMMENT '代理商编号',
  `member_id` int(11) unsigned NOT NULL COMMENT '会员编号',
  `type` tinyint(3) unsigned NOT NULL COMMENT '类型：1增加 2减少',
  `remark` varchar(128) NOT NULL COMMENT '备注说明',
  `points` int(11) NOT NULL COMMENT '积分数量',
  `remains` int(11) unsigned NOT NULL COMMENT '剩余积分',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `flag` tinyint(4) DEFAULT '1' COMMENT '删除标志位：0未删除 1已删除',
  PRIMARY KEY (`id`),
  KEY `idx_flag` (`flag`) USING BTREE,
  KEY `idx_agent_id` (`agent_id`) USING BTREE,
  KEY `idx_member_id` (`member_id`) USING BTREE,
  KEY `idx_type` (`type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1792 DEFAULT CHARSET=utf8 COMMENT='用户积分记录';

-- ----------------------------
-- Table structure for dm_member_profile
-- ----------------------------
DROP TABLE IF EXISTS `dm_member_profile`;
CREATE TABLE `dm_member_profile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) NOT NULL DEFAULT '1',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `app_version` varchar(255) DEFAULT NULL COMMENT '应用版本号',
  `gender` tinyint(4) NOT NULL COMMENT '性别：0未知 1男 2女 ',
  `member_id` int(11) DEFAULT NULL COMMENT '会员编号',
  `nickname` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '昵称',
  `platform` tinyint(4) NOT NULL COMMENT '平台',
  `realname` varchar(255) DEFAULT NULL COMMENT '真实姓名',
  `province_id` int(11) unsigned DEFAULT NULL COMMENT '省编号',
  `city_id` int(11) unsigned DEFAULT NULL COMMENT '城市编号',
  `district_id` int(11) unsigned DEFAULT NULL COMMENT '区编号',
  PRIMARY KEY (`id`),
  KEY `idx_member_id` (`member_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=281 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for dm_member_summary
-- ----------------------------
DROP TABLE IF EXISTS `dm_member_summary`;
CREATE TABLE `dm_member_summary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) NOT NULL COMMENT '会员ID',
  `charge_counts` int(6) DEFAULT '0' COMMENT '充值次数',
  `charge_status` tinyint(4) DEFAULT '-1' COMMENT '充值状态 -1：未充值 0：已充值未领取奖励   1：充值已领取奖励',
  `grab_status` tinyint(4) DEFAULT '-1' COMMENT '抓取奖励状态 -1：未达成目标  0：已达到未领取 1：已领取',
  `grab_counts` int(11) NOT NULL DEFAULT '0' COMMENT '抓取次数',
  `grab_success_counts` int(11) NOT NULL DEFAULT '0' COMMENT '抓中次数',
  `charge_moneys` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '充值总金额',
  `consume_diamonds` int(11) NOT NULL DEFAULT '0' COMMENT '消费总钻石数',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  `charge_reward` varchar(255) DEFAULT '' COMMENT '充值奖励领取情况',
  `grab_success_reward` varchar(255) DEFAULT '' COMMENT '抓取成功奖励领取情况',
  `grab_reward` varchar(255) DEFAULT '' COMMENT '抓取奖励领取情况',
  `fragment_counts` int(11) DEFAULT '0' COMMENT '总碎片数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=146 DEFAULT CHARSET=utf8 COMMENT='会员统计相关数据';

-- ----------------------------
-- Table structure for dm_member_token
-- ----------------------------
DROP TABLE IF EXISTS `dm_member_token`;
CREATE TABLE `dm_member_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) NOT NULL DEFAULT '1',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `access_token` varchar(255) DEFAULT NULL,
  `expire_time` datetime DEFAULT NULL,
  `member_id` int(11) DEFAULT NULL,
  `refresh_token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_member_id` (`member_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=275 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for dm_member_wanba_task
-- ----------------------------
DROP TABLE IF EXISTS `dm_member_wanba_task`;
CREATE TABLE `dm_member_wanba_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) unsigned NOT NULL COMMENT '会员编号',
  `collection_date` varchar(55) DEFAULT '' COMMENT '收藏奖励的领取时间',
  `novice_gift_date` varchar(55) DEFAULT '' COMMENT '新手礼包的领取时间',
  `exclusive_gift_date` varchar(55) DEFAULT '' COMMENT '空间专属礼包的领取时间',
  `day_gift_date` varchar(55) DEFAULT '' COMMENT '每日礼包的领取时间',
  `day_share_count` varchar(55) DEFAULT '' COMMENT '每日分享的领取时间以及次数',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `idx_member_id` (`member_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='会员玩吧任务进度';

-- ----------------------------
-- Table structure for dm_message
-- ----------------------------
DROP TABLE IF EXISTS `dm_message`;
CREATE TABLE `dm_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(300) DEFAULT NULL,
  `message` varchar(500) DEFAULT NULL,
  `agent_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  `type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '消息类型1：系统消息 2订单消息 3兑换消息',
  `order_sn` varchar(20) DEFAULT NULL COMMENT '订单号',
  `exchange_id` int(11) DEFAULT NULL COMMENT '兑换ID',
  `member_id` int(11) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=236 DEFAULT CHARSET=utf8mb4 COMMENT='系统消息';

-- ----------------------------
-- Table structure for dm_message_member
-- ----------------------------
DROP TABLE IF EXISTS `dm_message_member`;
CREATE TABLE `dm_message_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) DEFAULT NULL,
  `message_id` int(11) DEFAULT NULL,
  `is_read` tinyint(4) DEFAULT NULL COMMENT '0未读 1已读',
  `is_delete` tinyint(4) DEFAULT NULL COMMENT '0正常 1删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1366 DEFAULT CHARSET=utf8mb4 COMMENT='用户已读消息';

-- ----------------------------
-- Table structure for dm_order
-- ----------------------------
DROP TABLE IF EXISTS `dm_order`;
CREATE TABLE `dm_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_sn` varchar(30) NOT NULL COMMENT '订单号',
  `member_id` int(11) NOT NULL COMMENT '用户ID',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0已发货 1未发货',
  `mobile` varchar(11) DEFAULT NULL COMMENT '收货人手机号',
  `consignee` varchar(20) DEFAULT NULL COMMENT '收货人',
  `address` varchar(255) DEFAULT NULL COMMENT '发货地址',
  `express_time` datetime DEFAULT NULL COMMENT '发货时间',
  `express_no` varchar(20) DEFAULT NULL COMMENT '快递号',
  `type` tinyint(4) DEFAULT NULL COMMENT '快递类型 1顺丰 2申通 3韵达 4中通 5天天 6ems ',
  `agent_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `express_name` varchar(50) DEFAULT NULL COMMENT '快递名称',
  `take_me` int(4) DEFAULT '0' COMMENT '是否自取：1自取  0：不自取 ',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_order_sn` (`order_sn`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=145 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for dm_order_product
-- ----------------------------
DROP TABLE IF EXISTS `dm_order_product`;
CREATE TABLE `dm_order_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL COMMENT '订单ID',
  `product_id` int(11) NOT NULL COMMENT '娃娃ID',
  `num` int(11) NOT NULL DEFAULT '1' COMMENT '娃娃数量',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=173 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for dm_points_mall_goods
-- ----------------------------
DROP TABLE IF EXISTS `dm_points_mall_goods`;
CREATE TABLE `dm_points_mall_goods` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `agent_id` int(11) unsigned NOT NULL COMMENT '代理商编号',
  `status` tinyint(3) unsigned NOT NULL COMMENT '状态：0下架 1上架',
  `type` tinyint(3) unsigned NOT NULL COMMENT '类型：1娃娃 2钻石',
  `name` varchar(64) NOT NULL COMMENT '商品名称',
  `thumb` varchar(256) NOT NULL COMMENT '缩略图',
  `points` int(10) unsigned NOT NULL COMMENT '价格（积分）',
  `description` text NOT NULL COMMENT '商品描述',
  `product_id` int(11) unsigned DEFAULT NULL COMMENT '产品编号',
  `diamond_num` int(11) unsigned DEFAULT NULL COMMENT '钻石数量',
  `sort` int(11) unsigned DEFAULT '0' COMMENT '排序值',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `flag` tinyint(4) DEFAULT '1' COMMENT '删除标志位：0未删除 1已删除',
  `exchange_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '兑换类型1积分 2碎片',
  `stock` int(6) DEFAULT '-1' COMMENT '库存数量  -1:不限制 ',
  `doll_num` int(6) DEFAULT NULL COMMENT '兑换比例：需要多少小娃娃才能兑换',
  PRIMARY KEY (`id`),
  KEY `idx_flag_status` (`flag`,`status`) USING BTREE,
  KEY `idx_agent_id` (`agent_id`) USING BTREE,
  KEY `idx_type` (`type`) USING BTREE,
  KEY `idx_name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8 COMMENT='积分商城商品';

-- ----------------------------
-- Table structure for dm_points_mall_goods_pic
-- ----------------------------
DROP TABLE IF EXISTS `dm_points_mall_goods_pic`;
CREATE TABLE `dm_points_mall_goods_pic` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `goods_id` int(11) unsigned NOT NULL COMMENT '积分商城商品编号',
  `pic` varchar(256) NOT NULL COMMENT '图片地址',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `flag` tinyint(4) DEFAULT '1' COMMENT '删除标志位：0未删除 1已删除',
  PRIMARY KEY (`id`),
  KEY `idx_flag_goods_id` (`flag`,`goods_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=170 DEFAULT CHARSET=utf8 COMMENT='积分商城商品图片';

-- ----------------------------
-- Table structure for dm_prize_agent_option
-- ----------------------------
DROP TABLE IF EXISTS `dm_prize_agent_option`;
CREATE TABLE `dm_prize_agent_option` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `agent_id` int(11) NOT NULL COMMENT '代理商ID',
  `prize_option_id` int(11) NOT NULL COMMENT '奖品ID',
  `flag` tinyint(4) DEFAULT '1',
  `weight` int(11) DEFAULT '1' COMMENT '权重',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COMMENT='代理商拥有的奖品';

-- ----------------------------
-- Table structure for dm_prize_money_option
-- ----------------------------
DROP TABLE IF EXISTS `dm_prize_money_option`;
CREATE TABLE `dm_prize_money_option` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sort` int(11) NOT NULL COMMENT '排序',
  `weight` int(11) NOT NULL COMMENT '权重',
  `agent_id` int(11) NOT NULL COMMENT '代理商ID',
  `money` int(11) NOT NULL COMMENT '赠送钻石数量',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COMMENT='钻石奖品配置';

-- ----------------------------
-- Table structure for dm_prize_option
-- ----------------------------
DROP TABLE IF EXISTS `dm_prize_option`;
CREATE TABLE `dm_prize_option` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '奖品名称',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '奖品全局开关：1开启 2关闭',
  `flag` tinyint(4) DEFAULT '1',
  `weight` int(11) NOT NULL DEFAULT '1' COMMENT '权重',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='未抓中奖品设置';

-- ----------------------------
-- Table structure for dm_prize_points_option
-- ----------------------------
DROP TABLE IF EXISTS `dm_prize_points_option`;
CREATE TABLE `dm_prize_points_option` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sort` int(11) NOT NULL COMMENT '排序',
  `weight` int(11) NOT NULL COMMENT '权重',
  `agent_id` int(11) NOT NULL COMMENT '代理商ID',
  `points` int(11) NOT NULL COMMENT '赠送积分数量',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COMMENT='积分奖品配置';

-- ----------------------------
-- Table structure for dm_product
-- ----------------------------
DROP TABLE IF EXISTS `dm_product`;
CREATE TABLE `dm_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '价格',
  `imgs` varchar(5000) NOT NULL DEFAULT '' COMMENT '娃娃图片 ;隔开',
  `win_img` varchar(200) DEFAULT NULL COMMENT '中奖图片',
  `agent_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT NULL,
  `diamond_rate` int(6) DEFAULT NULL COMMENT '一个娃娃可兑换多少个钻石',
  `detail_img` varchar(2000) DEFAULT NULL COMMENT '详情大图片',
  `type` tinyint(4) DEFAULT '1' COMMENT '1:娃娃 2:兑换商品 3:兑换大娃娃',
  `stock` int(6) DEFAULT '0' COMMENT '库存数量  0:不限制 ',
  `exchange_rate` int(6) DEFAULT NULL COMMENT '兑换比例：需要多少小娃娃才能兑换',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COMMENT='娃娃商品';

-- ----------------------------
-- Table structure for dm_product_fragment
-- ----------------------------
DROP TABLE IF EXISTS `dm_product_fragment`;
CREATE TABLE `dm_product_fragment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL COMMENT '碎片名称',
  `img` varchar(100) DEFAULT NULL COMMENT '碎片图标',
  `agent_id` int(11) NOT NULL COMMENT '代理商ID',
  `weight` int(11) NOT NULL COMMENT '权重',
  `setting` varchar(300) NOT NULL COMMENT '碎片随机数量json配置[{num:,weight:}]',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  `type` int(11) NOT NULL DEFAULT '1' COMMENT '碎片类型1:兑换产品碎片 2其他碎片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8mb4 COMMENT='产品碎片';

-- ----------------------------
-- Table structure for dm_product_fragment_relation
-- ----------------------------
DROP TABLE IF EXISTS `dm_product_fragment_relation`;
CREATE TABLE `dm_product_fragment_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `agent_id` int(11) NOT NULL COMMENT '代理商ID',
  `points_mall_goods_id` int(11) DEFAULT NULL COMMENT '积分商城产品ID',
  `fragment_id` int(11) NOT NULL COMMENT '碎片ID',
  `exchange_num` int(11) NOT NULL DEFAULT '1' COMMENT '兑换所需碎片数量',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  `activity_id` int(11) DEFAULT '0' COMMENT '活动ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COMMENT='产品与碎片的关联';

-- ----------------------------
-- Table structure for dm_push_log
-- ----------------------------
DROP TABLE IF EXISTS `dm_push_log`;
CREATE TABLE `dm_push_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL COMMENT '1首页 2娃娃机',
  `msg` varchar(200) DEFAULT NULL COMMENT '消息',
  `agent_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COMMENT='极光推送记录';

-- ----------------------------
-- Table structure for dm_room_notification
-- ----------------------------
DROP TABLE IF EXISTS `dm_room_notification`;
CREATE TABLE `dm_room_notification` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `agent_id` int(10) unsigned NOT NULL COMMENT '代理商编号',
  `status` tinyint(3) unsigned DEFAULT '0' COMMENT '状态：0停用 1启用',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `content` varchar(100) NOT NULL COMMENT '消息内容',
  `sort` int(10) unsigned NOT NULL COMMENT '排序',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `idx_agent_id` (`agent_id`) USING BTREE,
  KEY `idx_status` (`status`) USING BTREE,
  KEY `idx_start_time_end_time` (`start_time`,`end_time`) USING BTREE,
  KEY `idx_flag` (`flag`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='房间通知';

-- ----------------------------
-- Table structure for dm_share_records
-- ----------------------------
DROP TABLE IF EXISTS `dm_share_records`;
CREATE TABLE `dm_share_records` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `agent_id` int(11) unsigned NOT NULL COMMENT '代理商编号',
  `member_id` int(11) unsigned NOT NULL COMMENT '会员编号',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `flag` tinyint(4) DEFAULT '1' COMMENT '删除标志位：0未删除 1已删除',
  PRIMARY KEY (`id`),
  KEY `idx_flag` (`flag`) USING BTREE,
  KEY `idx_agent_id` (`agent_id`) USING BTREE,
  KEY `idx_member_id` (`member_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8 COMMENT='分享记录表';

-- ----------------------------
-- Table structure for dm_sign_option
-- ----------------------------
DROP TABLE IF EXISTS `dm_sign_option`;
CREATE TABLE `dm_sign_option` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `day_id` int(11) DEFAULT NULL,
  `description` varchar(10) DEFAULT NULL,
  `points` int(11) DEFAULT NULL,
  `agent_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  `type` int(11) DEFAULT '1' COMMENT '类型 1: 钻石  2：积分',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=206 DEFAULT CHARSET=utf8 COMMENT='签到积分配置';

-- ----------------------------
-- Table structure for dm_sign_record
-- ----------------------------
DROP TABLE IF EXISTS `dm_sign_record`;
CREATE TABLE `dm_sign_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) NOT NULL,
  `points` int(11) DEFAULT NULL COMMENT '获取的积分',
  `day_id` int(11) DEFAULT NULL COMMENT '连续签到第几天',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0有效 1无效',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=337 DEFAULT CHARSET=utf8 COMMENT='签到记录';

-- ----------------------------
-- Table structure for dm_success_log
-- ----------------------------
DROP TABLE IF EXISTS `dm_success_log`;
CREATE TABLE `dm_success_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `source` tinyint(3) unsigned DEFAULT '1' COMMENT '来源：1抓取 2兑换',
  `url` varchar(1000) DEFAULT NULL COMMENT '抓取视频地址',
  `member_id` int(11) NOT NULL COMMENT '用户ID',
  `machine_id` int(11) DEFAULT NULL COMMENT '机器ID',
  `exchange_id` int(11) unsigned DEFAULT NULL COMMENT '积分兑换编号',
  `product_id` int(11) NOT NULL COMMENT '娃娃ID',
  `product_name` varchar(100) NOT NULL COMMENT '娃娃名称',
  `product_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '娃娃价格',
  `product_imgs` varchar(2000) DEFAULT NULL,
  `exchange_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '兑换状态 0未兑换 1已兑换',
  `appeal_id` varchar(255) DEFAULT NULL COMMENT '申诉ID',
  `agent_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  `log_pid` int(11) NOT NULL COMMENT '抓取记录主表dm_doll_log的id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4111 DEFAULT CHARSET=utf8mb4 COMMENT='娃娃抓取成功的记录';

-- ----------------------------
-- Table structure for dm_tag
-- ----------------------------
DROP TABLE IF EXISTS `dm_tag`;
CREATE TABLE `dm_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(55) DEFAULT NULL COMMENT '标签名称',
  `status` tinyint(4) DEFAULT NULL COMMENT '1：正常 2：禁用',
  `agent_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  `tag_category_id` int(11)  DEFAULT NULL COMMENT '标签类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COMMENT='标签';

-- ----------------------------
-- Table structure for dm_tag_category
-- ----------------------------
DROP TABLE IF EXISTS `dm_tag_category`;
CREATE TABLE `dm_tag_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(55) DEFAULT NULL COMMENT '标签名称',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='标签类型';

-- ----------------------------
-- Table structure for dm_tag_machine
-- ----------------------------
DROP TABLE IF EXISTS `dm_tag_machine`;
CREATE TABLE `dm_tag_machine` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_id` int(11) NOT NULL,
  `machine_id` tinyint(4) DEFAULT NULL COMMENT '1：正常 2：禁用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COMMENT='机器标签关系表';

-- ----------------------------
-- Table structure for dm_task
-- ----------------------------
DROP TABLE IF EXISTS `dm_task`;
CREATE TABLE `dm_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `agent_id` int(11) NOT NULL COMMENT '代理商Id',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `has_child` tinyint(4) DEFAULT '1' COMMENT '是否有子任务  1:有 0：没有',
  `total_reward` int(6) NOT NULL DEFAULT '0' COMMENT '完成所有子任务的总奖励',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  `display` int(11) NOT NULL COMMENT '1：显示  0：隐藏',
  `task_key` varchar(55) DEFAULT NULL COMMENT '任务关键字',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8 COMMENT='任务系统';

-- ----------------------------
-- Table structure for dm_third_charge_option
-- ----------------------------
DROP TABLE IF EXISTS `dm_third_charge_option`;
CREATE TABLE `dm_third_charge_option` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `price` decimal(10,2) NOT NULL COMMENT '价格',
  `money` decimal(10,2) NOT NULL COMMENT '充值游戏币',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `sort` int(11) DEFAULT '0',
  `item_id` varchar(55) DEFAULT NULL COMMENT '玩吧充值道具ID',
  `platform` tinyint(4) DEFAULT '0' COMMENT '平台 1：安卓  2: IOS   0:类型不限',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  `agent_id` int(11) DEFAULT NULL,
  `mark` tinyint(4) DEFAULT '1' COMMENT '标记 1普通 2热销 3推荐 4限购 5限时',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for dm_transfer_snapshot
-- ----------------------------
DROP TABLE IF EXISTS `dm_transfer_snapshot`;
CREATE TABLE `dm_transfer_snapshot` (
  `old_member_id` int(11) unsigned NOT NULL COMMENT '老系统用户编号',
  `new_member_id` int(11) unsigned NOT NULL COMMENT '新系统用户编号',
  PRIMARY KEY (`old_member_id`,`new_member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户编号迁移快照';

-- ----------------------------
-- Table structure for dm_update_detail
-- ----------------------------
DROP TABLE IF EXISTS `dm_update_detail`;
CREATE TABLE `dm_update_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `r_id` int(11) NOT NULL COMMENT '对应的父级表记录的id',
  `t_id` int(11) NOT NULL COMMENT '机器id',
  `name` varchar(255) NOT NULL COMMENT '机器编号',
  `flag` tinyint(4) DEFAULT '1' COMMENT '1正常 -1删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=150 DEFAULT CHARSET=utf8 COMMENT='机器软件包升级记录的子表';

-- ----------------------------
-- Table structure for dm_update_record
-- ----------------------------
DROP TABLE IF EXISTS `dm_update_record`;
CREATE TABLE `dm_update_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` tinyint(4) DEFAULT '1' COMMENT '1全部 2部分',
  `agent_id` int(11) NOT NULL COMMENT '代理商id',
  `title` varchar(255) NOT NULL COMMENT '升级标题',
  `version_code` varchar(55) NOT NULL COMMENT '版本号',
  `content` varchar(255) NOT NULL COMMENT '内容',
  `update_url` varchar(255) NOT NULL COMMENT '升级链接',
  `flag` tinyint(4) DEFAULT '1' COMMENT '1正常 -1删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8 COMMENT='机器软件包升级记录的父级表';

-- ----------------------------
-- Table structure for dm_wanba_charge_option
-- ----------------------------
DROP TABLE IF EXISTS `dm_wanba_charge_option`;
CREATE TABLE `dm_wanba_charge_option` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `price` decimal(10,2) NOT NULL COMMENT '价格',
  `money` int(6) NOT NULL COMMENT '充值游戏币',
  `title` varchar(100) DEFAULT '' COMMENT '标题',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `sort` int(11) DEFAULT '0',
  `item_id` varchar(55) DEFAULT NULL COMMENT '玩吧充值道具ID',
  `type` tinyint(4) DEFAULT '0' COMMENT '1：周卡 2：月卡 3：普通充值',
  `platform` tinyint(4) DEFAULT '0' COMMENT '平台 1：安卓  2: IOS',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  `mark` tinyint(4) DEFAULT '1' COMMENT '标记 1普通 2热销 3推荐 4限购 5限时 6首冲',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='玩吧充值选项';

-- ----------------------------
-- Table structure for dm_wanba_task
-- ----------------------------
DROP TABLE IF EXISTS `dm_wanba_task`;
CREATE TABLE `dm_wanba_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `option_id` varchar(55) DEFAULT NULL COMMENT '玩吧对应的配置ID',
  `task_key` varchar(55) DEFAULT NULL COMMENT '任务关键字',
  `goal_money` int(6) DEFAULT '0' COMMENT '完成赠送的钻石',
  `goal_point` int(6) DEFAULT '0' COMMENT '完成赠送的积分',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `flag` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='玩吧任务系统';

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for sys_area
-- ----------------------------
DROP TABLE IF EXISTS `sys_area`;
CREATE TABLE `sys_area` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `pingyin` varchar(50) NOT NULL,
  `pid` int(11) unsigned NOT NULL,
  `level` tinyint(4) unsigned NOT NULL COMMENT '1:省份，2:市，3:区域',
  `code` int(11) unsigned NOT NULL,
  `hot` smallint(6) unsigned NOT NULL DEFAULT '0',
  `short` char(2) NOT NULL,
  `en_name` varchar(10) DEFAULT '',
  `intro` varchar(128) DEFAULT '',
  `lat` double DEFAULT '0',
  `lng` double DEFAULT '0',
  `status` int(10) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3885 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单Id',
  `name` varchar(45) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(100) DEFAULT NULL COMMENT '菜单访问地址',
  `icon` varchar(20) DEFAULT NULL,
  `is_leaf` tinyint(4) DEFAULT '0' COMMENT '是否叶子节点',
  `pid` int(11) DEFAULT '0',
  `sort` int(11) DEFAULT '0' COMMENT '排序码',
  `remark` varchar(500) DEFAULT NULL COMMENT '菜单功能描述',
  `create_time` datetime DEFAULT NULL,
  `create_uid` int(11) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '1' COMMENT '菜单状态  1:正常  0：注销',
  PRIMARY KEY (`id`),
  KEY `FKilsmbm8k8qqnrc9wombfa73po` (`pid`),
  CONSTRAINT `FKilsmbm8k8qqnrc9wombfa73po` FOREIGN KEY (`pid`) REFERENCES `sys_menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_menu_copy
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_copy`;
CREATE TABLE `sys_menu_copy` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单Id',
  `name` varchar(45) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(100) DEFAULT NULL COMMENT '菜单访问地址',
  `icon` varchar(20) DEFAULT NULL,
  `is_leaf` tinyint(4) DEFAULT '0' COMMENT '是否叶子节点',
  `pid` int(11) DEFAULT '0',
  `sort` int(11) DEFAULT '0' COMMENT '排序码',
  `remark` varchar(500) DEFAULT NULL COMMENT '菜单功能描述',
  `create_time` datetime DEFAULT NULL,
  `create_uid` int(11) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '1' COMMENT '菜单状态  1:正常  0：注销',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` text NOT NULL COMMENT '??',
  `content` text,
  `create_time` datetime DEFAULT NULL,
  `publish_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1,没有读，2已读，0无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统公告';

-- ----------------------------
-- Table structure for sys_param
-- ----------------------------
DROP TABLE IF EXISTS `sys_param`;
CREATE TABLE `sys_param` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `key` varchar(64) DEFAULT NULL,
  `agent_id` int(11) DEFAULT NULL COMMENT '代理商',
  `value` varchar(2048) DEFAULT '1',
  `param_desc` varchar(128) DEFAULT NULL COMMENT '说明',
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(30) NOT NULL DEFAULT '' COMMENT '权限点名称',
  `permission` varchar(30) NOT NULL DEFAULT '' COMMENT '权限点',
  `menu_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '菜单编号',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `menu_id` (`menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='权限点';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_uid` int(11) NOT NULL DEFAULT '0',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`menu_id`),
  UNIQUE KEY `idx_role_menu` (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `role_id` int(10) unsigned NOT NULL COMMENT '角色编号',
  `permission_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`),
  KEY `role_id` (`role_id`) USING BTREE,
  KEY `role_permission` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色－权限点关联表';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(45) NOT NULL COMMENT '用户登录账号',
  `password` varchar(50) NOT NULL,
  `salt` varchar(20) NOT NULL,
  `nickname` varchar(50) DEFAULT NULL COMMENT '用户名称',
  `fullname` varchar(45) DEFAULT NULL COMMENT '真实姓名',
  `gender` tinyint(4) DEFAULT '1' COMMENT '1: 男 2: 女',
  `phone` varchar(15) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(45) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0: 未激活 1：正常  2：未补全信息 ',
  `deleted` char(1) NOT NULL DEFAULT 'N',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `unq_account` (`account`,`deleted`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Table structure for sys_user_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_info`;
CREATE TABLE `sys_user_info` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `avatar_url` varchar(100) DEFAULT NULL COMMENT '头像URL',
  `sw_coin` int(11) DEFAULT '0' COMMENT '洗洗币',
  `last_login_time` datetime NOT NULL,
  `last_login_ip` varchar(45) DEFAULT NULL,
  `login_ip` varchar(45) DEFAULT NULL,
  `login_times` int(11) NOT NULL DEFAULT '0',
  `login_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_user_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_login_log`;
CREATE TABLE `sys_user_login_log` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `flag` tinyint(45) NOT NULL DEFAULT '1',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `user_id` int(10) unsigned NOT NULL COMMENT '会员编号',
  `ip` varchar(15) DEFAULT '' COMMENT 'IP地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1852 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `uid` int(11) unsigned NOT NULL DEFAULT '0',
  `role_id` int(11) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`uid`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_wx_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_wx_user`;
CREATE TABLE `sys_wx_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `flag` tinyint(4) DEFAULT NULL COMMENT '标识位',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `city` varchar(25) DEFAULT NULL COMMENT '城市',
  `country` varchar(35) DEFAULT NULL COMMENT '国家',
  `language` varchar(15) DEFAULT NULL COMMENT '语言',
  `nickname` varchar(35) DEFAULT NULL COMMENT '昵称',
  `openid` varchar(255) DEFAULT NULL COMMENT '用户标识',
  `province` varchar(35) DEFAULT NULL COMMENT '省',
  `sex` tinyint(4) DEFAULT '0' COMMENT '性别',
  `subscribe` tinyint(4) DEFAULT '0' COMMENT '关注状态',
  `subscribe_time` datetime DEFAULT NULL COMMENT '关注时间',
  `unionid` varchar(255) DEFAULT NULL COMMENT '唯一标志',
  PRIMARY KEY (`id`),
  UNIQUE KEY `openid` (`openid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `age` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for wx_msg_response
-- ----------------------------
DROP TABLE IF EXISTS `wx_msg_response`;
CREATE TABLE `wx_msg_response` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` longtext,
  `keyword` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `msg_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wx_msg_subscribe
-- ----------------------------
DROP TABLE IF EXISTS `wx_msg_subscribe`;
CREATE TABLE `wx_msg_subscribe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `msg_type` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
