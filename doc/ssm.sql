/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : ssm

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2016-07-15 15:18:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permissionName` varchar(50) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `roleId` (`roleId`),
  CONSTRAINT `t_permission_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('1', 'user:*', '1');
INSERT INTO `t_permission` VALUES ('2', 'student:*', '2');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', 'admin');
INSERT INTO `t_role` VALUES ('2', 'teacher');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `roleId` (`roleId`),
  CONSTRAINT `t_user_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'crossoverJie', '123456', '1');
INSERT INTO `t_user` VALUES ('2', 'aaa', '12345', '2');
INSERT INTO `t_user` VALUES ('3', 'bbb', '12345', null);
INSERT INTO `t_user` VALUES ('4', 'ccc', '12345', null);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `description` text,
  `roleId` int(11) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '杜兰特', 'abc123', '凯文·杜兰特（Kevin Durant），1988年9月29日出生于美国华盛顿哥伦比亚特区（Washington,District of Columbia），美国职业篮球运动员，司职小前锋，效力于NBA金州勇士队。 [1] \r\n杜兰特在2007年选秀首轮第二顺位被西雅图超音速队选中，2008年随球队搬迁到俄克拉何马州。2014年，杜兰特同时荣膺NBA得分王与常规赛MVP。\r\n2016年7月4日，杜兰特加盟金州勇士队。2017年6月13日，勇士总比分4-1击败骑士，夺得2016-17赛季NBA总冠军。杜兰特收获生涯首冠，并荣膺FMVP [2]  。2018年4月，获《时代周刊》2018年全球最具影响力人物荣誉。 [3]  2018年6月9日，凯文·杜兰特随勇士队再次夺得NBA总冠军并荣膺2017-2018赛季总决赛MVP。 [4-5]  2017-18赛季荣获年度社区贡献奖。 [6] ', null);
INSERT INTO `user` VALUES ('2', '考辛斯', 'abc123', '德马库斯·考辛斯（DeMarcus Cousins），1990年8月13日出生于美国阿拉巴马州莫比尔（Mobile, AL），美国职业篮球运动员，司职大前锋/中锋，效力于NBA金州勇士队。 [1] \r\n德马库斯·考辛斯于2010年通过选秀进入NBA，先后效力于国王、鹈鹕以及勇士队 [1]  ，新秀赛季入选最佳新秀第一阵容；2014-15、2015-16赛季入选最佳阵容第二阵容；4次入选全明星阵容。\r\n德马库斯·考辛斯于2014年随美国国家队获得男篮世界杯冠军，2016年随美国队获得里约奥运会男篮冠军。', null);
INSERT INTO `user` VALUES ('3', '德拉蒙德格林', 'abc123', '德雷蒙德·格林（Draymond Green），1990年3月4日出生于美国密歇根州塞基诺（Saginaw, Michigan），美国职业篮球运动员，司职大前锋，效力于NBA金州勇士队。 [1] \r\n德雷蒙德·格林于2012年通过选秀进入NBA，2014-15赛季入选最佳防守阵容第一阵容，并随勇士队获得NBA总冠军；2015-16赛季入选最佳防守阵容第一阵容和最佳阵容第二阵容；2016-17赛季当选最佳防守球员，入选最佳防守阵容第一阵容和最佳阵容第三阵容，并随勇士队获得NBA总冠军；3次入选全明星阵容；2017-18赛季随勇士队再次夺得NBA总冠军。', null);
INSERT INTO `user` VALUES ('4', '克莱汤普森', 'abc123', '克莱·汤普森（Klay Thompson），1990年2月8日出生于美国加利福尼亚州洛杉矶（Los Angeles,California），美国职业篮球运动员，司职得分后卫，效力于NBA金州勇士队。 [1] \r\n克莱·汤普森于2011年通过选秀进入NBA，新秀赛季入选最佳新秀阵容第一阵容；2014-15、2016-17、2017-18赛季三次随勇士队获得NBA总冠军；2次入选最佳阵容第三阵容，4次入选全明星阵容。\r\n2014年，克莱·汤普森随美国队获得西班牙篮球世界杯冠军。\r\n克莱·汤普森（Klay Thompson），1990年2月8日出生于美国加利福尼亚州洛杉矶（Los Angeles,California），美国职业篮球运动员，司职得分后卫，效力于NBA金州勇士队。 [1] \r\n克莱·汤普森于2011年通过选秀进入NBA，新秀赛季入选最佳新秀阵容第一阵容；2014-15、2016-17、2017-18赛季三次随勇士队获得NBA总冠军；2次入选最佳阵容第三阵容，4次入选全明星阵容。\r\n2014年，克莱·汤普森随美国队获得西班牙篮球世界杯冠军。\r\n', null);
INSERT INTO `user` VALUES ('5', '詹姆斯', 'abc123', '勒布朗·詹姆斯（LeBron James），1984年12月30日出生在美国俄亥俄州阿克伦，美国职业篮球运动员，司职小前锋，绰号“小皇帝”，效力于NBA洛杉矶湖人队。\r\n詹姆斯在2003年NBA选秀中于首轮第1顺位被克利夫兰骑士队选中。在2009年与2010年蝉联NBA最有价值球员（MVP）。2010年，詹姆斯转会至迈阿密热火队。\r\n2012年，詹姆斯得到NBA个人生涯的第3座常规赛MVP，第1个总冠军和总决赛MVP。并代表美国男篮获得了伦敦奥运会金牌，追平了迈克尔·乔丹在1992年所创的纪录。2013年，詹姆斯获得第4个常规赛MVP、第2个NBA总冠军和第2个总决赛MVP，实现两连冠。\r\n2014年，詹姆斯回归骑士。2016年，詹姆斯带领骑士逆转战胜卫冕冠军勇士获得队史首个总冠军和个人第3个总决赛MVP。2018年7月10日，詹姆斯正式与湖人签下4年1.53亿美元的合同。 [1] \r\n詹姆斯篮球智商极高、突破犀利，拥有出色的视野和传球技术。被认为是NBA有史以来最为全能的球员之一', null);
INSERT INTO `user` VALUES ('6', '艾弗森', 'abc123', '阿伦·艾弗森1996年6月26日被费城76人队选中，成为NBA状元秀。艾弗森14年职业生涯先后效力过费城76人队、掘金、灰熊和活塞， 场均出战41.1分钟，获得26.7分、6.2次助攻和2.2次抢断。在整个职业生涯中，艾弗森四次荣膺得分王，11次入选全明星，三次荣膺抢断王。2000-2001赛季，艾弗森打出了生涯最好表现，夺得常规赛MVP奖杯，并帮助76队打入NBA总决赛。', null);
INSERT INTO `user` VALUES ('7', '库里', 'abc123', '斯蒂芬·库里2009年通过选秀进入NBA后一直效力于勇士队，新秀赛季入选最佳新秀第一阵容；2014-15赛季随勇士队获得NBA总冠军；两次当选常规赛MVP，两次入选最佳阵容第一阵容，三次入选全明星赛西部首发阵容。斯蒂芬·库里2010年随美国队获土耳其世锦赛冠军，2014年随美国队获西班牙篮球世界杯冠军。', null);
INSERT INTO `user` VALUES ('8', '维斯布鲁克', '1111', '拉塞尔·威斯布鲁克于2008年通过选秀进入NBA，新秀赛季入选最佳新秀阵容第一阵容；2010年随美国队获得土耳其篮球世锦赛冠军，2012年随美国男篮获得伦敦奥运会冠军；5次入选全明星阵容，2015、2016连续两年获得全明星赛MVP；2015-16赛季入选最佳阵容第一阵容，4次入选最佳阵容第二阵容。', null);
INSERT INTO `user` VALUES ('9', '凯里·欧文', '6666', '凯里·欧文2011年以选秀状元身份进入NBA，新秀赛季当选最佳新秀；2014年首次入选全明星正赛先发阵容，并当选最有价值球员；同年代表美国队参加男篮世界杯，获得冠军并当选MVP；2014-15赛季入选最佳阵容第三阵容；2015-16赛季随骑士队获得NBA总冠军。', null);
INSERT INTO `user` VALUES ('10', '拉简·朗多', '34343', '拉简·朗多（Rajon Rondo ），男，1986年2月22日出生于美国肯塔基州路易斯维尔。美国职业篮球运动员，司职控球后卫，效力于NBA芝加哥公牛队。\r\n拉简·朗多在2006年NBA第一轮选秀中以第21顺位被菲尼克斯太阳队选中，随后被交易去了波士顿凯尔特人队，2014年正式成为凯尔特人队第15任队长。2014年12月19日，加入达拉斯小牛队。2015年7月4日，加入萨克拉门托国王队。', null);
INSERT INTO `user` VALUES ('11', '约翰·沃尔', 'wall', '\r\n约翰·沃尔（John Wall），1990年9月6日出生于美国北卡罗来纳州罗利（Raleigh, North Carolina），美国职业篮球运动员，司职控球后卫，效力于NBA华盛顿奇才队。\r\n约翰·沃尔于2010年以选秀状元身份进入NBA，新秀赛季入选最佳新秀阵容第一阵容，2011年全明星新秀挑战赛当选MVP；2014-15赛季入选最佳防守阵容第二阵容；三次入选全明星阵容。\r\n\r\n约翰·沃尔（John Wall），1990年9月6日出生于美国北卡罗来纳州罗利（Raleigh, North Carolina），美国职业篮球运动员，司职控球后卫，效力于NBA华盛顿奇才队。\r\n约翰·沃尔于2010年以选秀状元身份进入NBA，新秀赛季入选最佳新秀阵容第一阵容，2011年全明星新秀挑战赛当选MVP；2014-15赛季入选最佳防守阵容第二阵容；三次入选全明星阵容。\r\n\r\n约翰·沃尔（John Wall），1990年9月6日出生于美国北卡罗来纳州罗利（Raleigh, North Carolina），美国职业篮球运动员，司职控球后卫，效力于NBA华盛顿奇才队。\r\n约翰·沃尔于2010年以选秀状元身份进入NBA，新秀赛季入选最佳新秀阵容第一阵容，2011年全明星新秀挑战赛当选MVP；2014-15赛季入选最佳防守阵容第二阵容；三次入选全明星阵容。', null);
INSERT INTO `user` VALUES ('12', '恩比德', '123', '乔尔·恩比德（Joel Embiid），1994年3月16日出生于喀麦隆雅温德，喀麦隆职业篮球运动员，司职中锋，效力于NBA费城76人队。 [1] \r\n在2014年NBA选秀中，乔尔·恩比德于第一轮第3顺位被76人队选中，2017-18赛季入选NBA全明星阵容。 [2-3] ', null);
INSERT INTO `user` VALUES ('13', '米切尔', '123', '多诺万·米切尔（Donovan Mitchell），1996年9月7日出生于美国康涅狄格州格林尼治（Greenwich, Connecticut），美国职业篮球运动员，司职得分后卫，效力于NBA犹他爵士队。 [1] \r\n多诺万·米切尔于2017年通过选秀进入NBA，2018年夺得全明星扣篮大赛冠军。', null);
