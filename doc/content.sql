/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : ssm

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2016-08-31 13:15:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for content
-- ----------------------------
DROP TABLE IF EXISTS `content`;
CREATE TABLE `content` (
  `contentId` int(50) NOT NULL AUTO_INCREMENT,
  `contentName` varchar(50) DEFAULT NULL COMMENT '发送者',
  `content` varchar(1000) DEFAULT NULL COMMENT '聊天内容',
  `createDate` varchar(100) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`contentId`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of content
-- ----------------------------
INSERT INTO `content` VALUES ('2', null, '<p>9090<br/></p>', '2016-08-28 21:03:28');
INSERT INTO `content` VALUES ('7', '你好', 'hah', '都会死');
INSERT INTO `content` VALUES ('9', null, '<p>说的是都是<br/></p>', '2016-08-28 21:29:28');
INSERT INTO `content` VALUES ('10', null, '哈哈你说的是呢<br/>', '2016-08-28 21:30:28');
INSERT INTO `content` VALUES ('11', null, '<p>你说什么呢<br/></p>', '2016-08-28 23:53:28');
INSERT INTO `content` VALUES ('12', null, '<p>你好啊<img src=\"http://img.baidu.com/hi/jx2/j_0026.gif\"/><br/></p>', '2016-08-29 00:28:29');
INSERT INTO `content` VALUES ('13', null, '<p>nihao啊</p>', '2016-08-29 00:31:29');
INSERT INTO `content` VALUES ('14', null, '说的', '2016-08-29 00:33:29');
INSERT INTO `content` VALUES ('15', null, '哈哈哈哈<br/>', '2016-08-29 00:33:29');
INSERT INTO `content` VALUES ('16', null, '你在干甚呢<br/>', '2016-08-29 00:33:29');
INSERT INTO `content` VALUES ('17', null, '<p>搞好久</p>', '2016-08-29 01:00:29');
INSERT INTO `content` VALUES ('18', null, '<p>8989<br/></p>', '2016-08-29 01:00:29');
INSERT INTO `content` VALUES ('19', null, '9899', '2016-08-29 01:01:29');
INSERT INTO `content` VALUES ('20', null, '家哈哈哈', '2016-08-29 01:01:29');
INSERT INTO `content` VALUES ('21', null, '<p>说的是<br/></p>', '2016-08-29 01:02:29');
INSERT INTO `content` VALUES ('22', null, '<p>哈哈</p>', '2016-08-29 01:02:29');
INSERT INTO `content` VALUES ('23', null, '<p>你说什么<br/></p>', '2016-08-29 01:02:29');
INSERT INTO `content` VALUES ('24', null, '909', '2016-08-29 01:03:29');
INSERT INTO `content` VALUES ('25', null, '<p>你好<br/></p>', '2016-08-29 01:06:29');
INSERT INTO `content` VALUES ('26', null, '你在干嘛呢<br/>', '2016-08-29 01:06:29');
INSERT INTO `content` VALUES ('27', null, '<p>积极</p>', '2016-08-29 01:06:29');
INSERT INTO `content` VALUES ('28', null, '7878', '2016-08-29 01:06:29');


