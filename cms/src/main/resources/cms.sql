/*
Navicat MySQL Data Transfer

Source Server         : testcloud
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : cms

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2017-04-08 16:17:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `goods`
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL COMMENT '商品名称',
  `originalPrice` decimal(20,2) DEFAULT NULL COMMENT '商品原价',
  `coupon` decimal(20,2) DEFAULT NULL COMMENT '优惠券',
  `price` decimal(20,2) DEFAULT NULL COMMENT '商品价格',
  `description` varchar(2000) DEFAULT NULL COMMENT '商品描述',
  `picture` varchar(200) DEFAULT NULL COMMENT '商品图片',
  `discountStartTime` varchar(20) DEFAULT NULL COMMENT '商品优惠开始时间',
  `discountEndTime` varchar(20) DEFAULT NULL COMMENT '商品优惠结束时间',
  `type` varchar(20) DEFAULT NULL COMMENT '商品分类',
  `saleNum` decimal(20,0) DEFAULT '0' COMMENT '销量',
  `getNum` decimal(20,0) DEFAULT '0' COMMENT '领券量',
  `flag` varchar(2) DEFAULT NULL COMMENT '商品状态',
  `sjgxsj` varchar(20) DEFAULT NULL COMMENT '数据更新时间',
  `sjgxr` int(11) DEFAULT NULL COMMENT '数据更新人',
  `sjcjsj` varchar(20) DEFAULT NULL COMMENT '数据创建时间',
  `sjcjr` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index` (`name`,`originalPrice`,`coupon`,`price`,`description`(255),`type`,`saleNum`,`getNum`,`flag`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('33', '', null, null, null, '原价：25.9\r\n优惠券：10\r\n\r\n妈妈装短袖中年人大码T恤\r\n券后【15.9元】包邮\r\n简约温婉，舒适百搭，优雅印花，彰显典雅气质，大码版型，不挑身材，胖瘦皆宜，环保面料，细腻纤维，亲肤舒适，清爽透气。\r\n\r\n复制这条信息，￥vyxLMo2obQ￥，打开【手机淘宝】即可领券下单', '/cms/upload/1491553318632.jpg', null, null, 'WOMEN', '123', '123', '01', '2017-04-07 16:21:59', '0', '2017-03-30 10:21:56', '0');
INSERT INTO `goods` VALUES ('34', '', null, null, null, '原价：11\r\n优惠券：3\r\n\r\n维达纸抽3包+10cm窗帘滑道样品\r\n券后【5.1元】包邮\r\n拍最后一个选项下单5.1元。实发维达纸抽3包。\r\n请不要咨询客服，不要晒纸抽图，直接好评。谢谢合作。\r\n\r\n复制这条信息，￥2c0IMocYNH￥，打开【手机淘宝】即可领券下单', '/cms/upload/1491553327985.jpg', null, null, 'HOME', '333', '222', '01', '2017-04-07 16:22:08', '0', '2017-03-30 10:22:46', '0');
INSERT INTO `goods` VALUES ('35', '', null, null, null, '原价：11\r\n优惠券：3\r\n\r\n维达纸抽3包+10cm窗帘滑道样品\r\n券后【5.1元】包邮\r\n拍最后一个选项下单5.1元。实发维达纸抽3包。\r\n请不要咨询客服，不要晒纸抽图，直接好评。谢谢合作。\r\n\r\n复制这条信息，￥2c0IMocYNH￥，打开【手机淘宝】即可领券下单', '/cms/upload/1491553338280.jpg', null, null, 'HOME', '456', '12', '01', '2017-04-07 16:22:19', '0', '2017-03-30 10:24:05', '0');
INSERT INTO `goods` VALUES ('36', '', null, null, null, '原价：11\r\n优惠券：3\r\n\r\n维达纸抽3包+10cm窗帘滑道样品\r\n券后【5.1元】包邮\r\n拍最后一个选项下单5.1元。实发维达纸抽3包。\r\n请不要咨询客服，不要晒纸抽图，直接好评。谢谢合作。\r\n\r\n复制这条信息，￥2c0IMocYNH￥，打开【手机淘宝】即可领券下单', '/cms/upload/1491553348928.jpg', null, null, 'HOME', '44', '33', '01', '2017-04-07 16:22:29', '0', '2017-03-30 10:24:10', '0');
INSERT INTO `goods` VALUES ('37', '', null, null, null, '原价：11\r\n优惠券：3\r\n\r\n维达纸抽3包+10cm窗帘滑道样品\r\n券后【5.1元】包邮\r\n拍最后一个选项下单5.1元。实发维达纸抽3包。\r\n请不要咨询客服，不要晒纸抽图，直接好评。谢谢合作。\r\n\r\n复制这条信息，￥2c0IMocYNH￥，打开【手机淘宝】即可领券下单', '/cms/upload/1491553308748.jpg', null, null, 'HOME', '55', '66', '01', '2017-04-07 16:21:49', '0', '2017-03-30 10:24:14', '0');
INSERT INTO `goods` VALUES ('52', '', null, null, null, '原价：19.9\r\n优惠券：10\r\n\r\n【才者】古法手工熬制玫瑰黑糖260g\r\n原价19.9元【券后9.9元】包邮\r\n古法熬制，健康零添加~可以让经血通畅，改善痛经~最高的性价比，一款你不容错过的玫瑰黑糖！\r\n\r\n复制这条信息，￥oFaqLN1lS4￥，打开【手机淘宝】即可领券下单', '/cms/upload/1491461490326.jpg', null, null, 'MAKEUP', '111', '11111', '01', '2017-04-07 09:18:43', '0', '2017-04-06 14:04:20', '0');
INSERT INTO `goods` VALUES ('57', '', null, null, null, '原价：168\r\n优惠券：100\r\n\r\n2017新款中长款春秋女装\r\n原价168元【券后68元】包邮\r\n100%纯棉 休闲带帽外套 潮\r\n\r\n复制这条信息，￥RxqkLN4450￥，打开【手机淘宝】即可领券下单', '/cms/upload/1491458658880.jpg', null, null, '', '1222', '233', '01', '2017-04-07 09:20:54', '0', '2017-04-06 14:47:54', '0');
INSERT INTO `goods` VALUES ('58', '', null, null, null, '原价：168\r\n优惠券：100\r\n\r\n2017新款中长款春秋女装\r\n原价168元【券后68元】包邮\r\n100%纯棉 休闲带帽外套 潮\r\n\r\n复制这条信息，￥RxqkLN4450￥，打开【手机淘宝】即可领券下单', '/cms/upload/1491458658880.jpg', null, null, '', '10098', '233232', '01', '2017-04-07 09:07:34', '0', '2017-04-06 14:51:03', '0');
INSERT INTO `goods` VALUES ('59', '', null, null, null, '原价：9.9\r\n优惠券：3\r\n\r\n透明网格拉链防水A4文件袋\r\n原价9.9元【券后6.9元】包邮\r\n优质材质，柔韧防水，手感舒适，方便拿取，大小适中，超值5个装，先到先得！【赠运险费】\r\n\r\n复制这条信息，￥ABXOL3UwRm￥，打开【手机淘宝】即可领券下单', '/cms/upload/1491528147528.jpg', null, null, 'SPORTS_AND_CAR', '2322', '22', '01', '2017-04-07 16:21:40', '0', '2017-04-07 09:22:44', '0');
INSERT INTO `goods` VALUES ('60', '', null, null, null, '原价：19.9\r\n优惠券：10\r\n\r\n祛痘芦荟胶60g×【3支装】\r\n券后9.9元已验货限领2券\r\n【推荐】【PS】清痘淡印 收敛毛孔 舒缓滋养 密集补水\r\n\r\n复制这条信息，￥tNKsL31uta￥，打开【手机淘宝】即可领券下单', '/cms/upload/1491528204887.jpg', null, null, 'MAKEUP', '12', '11', '01', '2017-04-07 09:23:34', '0', '2017-04-07 09:23:34', '0');
INSERT INTO `goods` VALUES ('65', '', null, null, null, '原价：32.4\r\n优惠券：10\r\n\r\n发箍+纸巾3包\r\n5.8元包邮 （拍第一个选项）\r\n下单：\r\n不要咨询客服！收货五星无字好评即可!不要晒图!不要换号多拍，换号多拍不发货，每个ID限购一件，商家亏本不易 谢谢合作\r\n\r\n复制这条信息，￥5KumLqv113￥，打开【手机淘宝】即可领券下单', '/cms/upload/1491555275863.jpg', null, null, 'MEN', '32', '13', '01', '2017-04-07 16:56:34', '0', '2017-04-07 16:26:14', '0');
INSERT INTO `goods` VALUES ('66', '', null, null, null, '原价：32.4\r\n优惠券：10\r\n\r\n发箍+纸巾3包\r\n5.8元包邮 （拍第一个选项）\r\n下单：\r\n不要咨询客服！收货五星无字好评即可!不要晒图!不要换号多拍，换号多拍不发货，每个ID限购一件，商家亏本不易 谢谢合作\r\n\r\n复制这条信息，￥5KumLqv113￥，打开【手机淘宝】即可领券下单', '/cms/upload/1491554325372.jpg', null, null, 'FOOD', '34', '23', '01', '2017-04-07 16:56:07', '0', '2017-04-07 16:38:48', '0');
INSERT INTO `goods` VALUES ('67', '', null, null, null, '原价：32.4\r\n优惠券：10\r\n\r\n发箍+纸巾3包\r\n5.8元包邮 （拍第一个选项）\r\n下单：\r\n不要咨询客服！收货五星无字好评即可!不要晒图!不要换号多拍，换号多拍不发货，每个ID限购一件，商家亏本不易 谢谢合作\r\n\r\n复制这条信息，￥5KumLqv113￥，打开【手机淘宝】即可领券下单', '/cms/upload/1491555245546.jpg', null, null, 'UNDERWEAR', '32', '0', '01', '2017-04-07 16:55:48', '0', '2017-04-07 16:40:25', '0');
INSERT INTO `goods` VALUES ('68', '', null, null, null, '原价：32.4\r\n优惠券：10\r\n\r\n发箍+纸巾3包\r\n5.8元包邮 （拍第一个选项）\r\n下单：\r\n不要咨询客服！收货五星无字好评即可!不要晒图!不要换号多拍，换号多拍不发货，每个ID限购一件，商家亏本不易 谢谢合作\r\n\r\n复制这条信息，￥5KumLqv113￥，打开【手机淘宝】即可领券下单', '/cms/upload/1491555338376.jpg', null, null, '', '0', '0', '01', '2017-04-07 16:55:38', '0', '2017-04-07 16:40:40', '0');
INSERT INTO `goods` VALUES ('69', '', null, null, null, '原价：32.4\r\n优惠券：10\r\n\r\n发箍+纸巾3包\r\n5.8元包邮 （拍第一个选项）\r\n下单：\r\n不要咨询客服！收货五星无字好评即可!不要晒图!不要换号多拍，换号多拍不发货，每个ID限购一件，商家亏本不易 谢谢合作\r\n\r\n复制这条信息，￥5KumLqv113￥，打开【手机淘宝】即可领券下单', '/cms/upload/1491555285495.jpg', null, null, 'MUMANDBABY', '342', '23', '01', '2017-04-07 16:55:56', '0', '2017-04-07 16:40:48', '0');
INSERT INTO `goods` VALUES ('70', '', null, null, null, '原价：36\r\n优惠券：20\r\n\r\n火烈鸟晶透璀璨唇彩化妆品专柜\r\n原价36元【劵后16元】包邮\r\n颜色多样，补水保湿，防水不易掉色，水润不易黏腻，亮泽持久，尽显气质，女神必备。\r\n\r\n复制这条信息，￥0MwjLqD1cA￥，打开【手机淘宝】即可领券下单', '/cms/upload/1491555267007.jpg', null, null, 'FOOD', '23', '23', '01', '2017-04-07 16:55:23', '0', '2017-04-07 16:40:54', '0');
INSERT INTO `goods` VALUES ('71', '', null, null, null, '原价：36\r\n优惠券：20\r\n\r\n火烈鸟晶透璀璨唇彩化妆品专柜\r\n原价36元【劵后16元】包邮\r\n颜色多样，补水保湿，防水不易掉色，水润不易黏腻，亮泽持久，尽显气质，女神必备。\r\n\r\n复制这条信息，￥0MwjLqD1cA￥，打开【手机淘宝】即可领券下单', '/cms/upload/1491555257316.jpg', null, null, 'FOOD', '232', '2323', '01', '2017-04-07 16:55:15', '0', '2017-04-07 16:41:16', '0');
INSERT INTO `goods` VALUES ('72', '', null, null, null, '原价：36\r\n优惠券：20\r\n\r\n火烈鸟晶透璀璨唇彩化妆品专柜\r\n原价36元【劵后16元】包邮\r\n颜色多样，补水保湿，防水不易掉色，水润不易黏腻，亮泽持久，尽显气质，女神必备。\r\n\r\n复制这条信息，￥0MwjLqD1cA￥，打开【手机淘宝】即可领券下单', '/cms/upload/1491555251798.jpg', null, null, 'SHOSE_AND_BAG', '0', '0', '01', '2017-04-07 16:55:06', '0', '2017-04-07 16:41:28', '0');
INSERT INTO `goods` VALUES ('73', '', null, null, null, '原价：39.9\r\n优惠券：20\r\n\r\ns925银天使之吻钻戒\r\n原价39元【卷后19.9】包邮\r\n专柜爆款，国标925银，铂金质感，仿真钻戒，女神必备。可调节大小~  精美包装礼盒！\r\n\r\n复制这条信息，￥bEQDLqCn6t￥，打开【手机淘宝】即可领券下单', '/cms/upload/1491555220184.jpg', null, null, 'SHOSE_AND_BAG', '3', '2', '01', '2017-04-07 16:53:57', '0', '2017-04-07 16:41:38', '0');
INSERT INTO `goods` VALUES ('74', '', null, null, null, '原价：52.9\r\n优惠券：40\r\n\r\n高品质拉架棉女T恤加长款钱包\r\n券后：12.9元\r\n买高品质女T恤送长款钱包多种花色任选 质量非常好，请拍套餐链接、全国包邮、每人限购一件\r\n\r\n复制这条信息，￥jw0ILqzNyV￥，打开【手机淘宝】即可领券下单', '/cms/upload/1491555192974.jpg', null, null, 'WOMEN', '22', '121', '01', '2017-04-07 16:53:21', '0', '2017-04-07 16:43:19', '0');
INSERT INTO `goods` VALUES ('75', '', null, null, null, '原价：89\r\n优惠券：30\r\n\r\n女装条纹衬衣两件套\r\n原价89元【券后59元】包邮\r\n经典圆领 灯笼袖  优质面料  时尚雅致  穿着舒适！【送运费险】\r\n\r\n复制这条信息，￥3FrcLJZRzA￥，打开【手机淘宝】即可领券下单', '/cms/upload/1491555166978.jpg', null, null, 'WOMEN', '22', '22', '01', '2017-04-07 16:52:53', '0', '2017-04-07 16:43:26', '0');
INSERT INTO `goods` VALUES ('76', '', null, null, null, '原价：24.9\r\n优惠券：5\r\n\r\n【山金牌碗碗香】陕北黄小米500g*3袋\r\n原价24.9元【券后19.9元】包邮\r\n纯绿色有机小米,口感醇厚,尤其适合孕妇婴儿老人食用,放心米,良心米。【赠运费险】\r\n\r\n复制这条信息，￥4LU9LJYX0L￥，打开【手机淘宝】即可领券下单', '/cms/upload/1491555136215.jpg', null, null, 'FOOD', '22', '22', '01', '2017-04-07 16:52:25', '0', '2017-04-07 16:45:01', '0');
INSERT INTO `goods` VALUES ('77', '', null, null, null, '原价：39.9\r\n优惠券：5\r\n\r\n秋冬九分裤新款阔腿裤女\r\n聚划算39.9元，券后【34.9元】包邮\r\n【聚划算同步】春夏新品，时髦女生的选择，时尚潮流不再是虚幻，腰部向上提升7cm，显瘦显高~女神必备~\r\n\r\n复制这条信息，￥RkpJLJcjYC￥，打开【手机淘宝】即可领券下单', '/cms/upload/1491555098513.jpg', null, null, 'UNDERWEAR', '0', '0', '01', '2017-04-07 16:51:55', '0', '2017-04-07 16:45:09', '0');
INSERT INTO `goods` VALUES ('78', '', null, null, null, '原价：39.9\r\n优惠券：15\r\n\r\n洁丽雅袜子男士女士棉袜袜子春夏季棉袜船袜\r\n原价39.9元 券后【24.9元】包邮\r\n洁丽雅大品牌精致呈现 新疆阿克苏1级长绒棉精制而成 纯净温和\r\n\r\n复制这条信息，￥sCcdLJfukv￥，打开【手机淘宝】即可领券下单', '/cms/upload/1491555052465.jpg', null, null, 'UNDERWEAR', '122', '222', '01', '2017-04-07 16:51:31', '0', '2017-04-07 16:46:37', '0');
INSERT INTO `goods` VALUES ('79', '', null, null, null, '原价：129.9\r\n优惠券：120\r\n\r\n【微商爆款】999纯银手镯\r\n券后【9.9元】包邮限购3个好评支付宝反2元\r\n【推荐】银手镯多款任选+手镯买二送一+配证书送礼盒+收藏宝贝就送精美钥匙扣一个史上最低价 只需7.9元 速度抢购\r\n\r\n复制这条信息，￥38FhLJMvhW￥，打开【手机淘宝】即可领券下单', '/cms/upload/1491554800740.jpg', null, null, 'FOOD', '1', '1', '01', '2017-04-07 16:48:58', '0', '2017-04-07 16:46:44', '0');
INSERT INTO `goods` VALUES ('80', '', null, null, null, '原价：74.9\r\n优惠券：50\r\n\r\n衣3衣4新款褶皱百搭雪纺衫 \r\n原价74.9元【券后24.9元】包邮\r\n飘逸雪纺，性感透视，面料柔软，健康舒适，清淡雅洁，轻薄透气\r\n\r\n复制这条信息，￥gfnfLJmfJg￥，打开【手机淘宝】即可领券下单', '/cms/upload/1491554869553.jpg', null, null, 'MUMANDBABY', '11', '22', '01', '2017-04-07 16:49:16', '0', '2017-04-07 16:47:50', '0');
INSERT INTO `goods` VALUES ('81', '', null, null, null, '原价：15.2\r\n优惠券：10\r\n\r\n拍下发：正品家用银鸽纸巾一提3包\r\n券后【5.2元】包邮\r\n限拍一件 多拍不发 禁止咨询 默认好评 汇通快递，新疆地区勿拍，谢谢合作！\r\n\r\n复制这条信息，￥mIKmLJPdZS￥，打开【手机淘宝】即可领券下单', '/cms/upload/1491554983392.jpg', null, null, 'HOME', '1', '1', '01', '2017-04-07 16:49:50', '0', '2017-04-07 16:47:59', '0');

-- ----------------------------
-- Table structure for `setting`
-- ----------------------------
DROP TABLE IF EXISTS `setting`;
CREATE TABLE `setting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `picture` varchar(200) DEFAULT NULL COMMENT '图片',
  `description` varchar(1000) DEFAULT NULL COMMENT '说明',
  `flag` varchar(2) NOT NULL COMMENT '设置类型（01：网站设置；02：）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `Flag` (`flag`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of setting
-- ----------------------------
INSERT INTO `setting` VALUES ('1', '商城2', '/cms/img/logo.png', '商城', '01');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Name` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'nimda');
