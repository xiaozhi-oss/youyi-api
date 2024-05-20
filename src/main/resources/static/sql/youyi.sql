/*
 Navicat Premium Data Transfer

 Source Server         : local-8
 Source Server Type    : MySQL
 Source Server Version : 80033
 Source Host           : localhost:3306
 Source Schema         : youyi

 Target Server Type    : MySQL
 Target Server Version : 80033
 File Encoding         : 65001

 Date: 20/05/2024 13:46:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int NOT NULL COMMENT '归属用户',
  `default_index` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否为默认',
  `recipient` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '收件人',
  `phoneNumber` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '收件人号码',
  `address` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '地址',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (1, 12, 1, '小舔猫', '123123123', '广东省 广州市 黄浦区 广州商学院');
INSERT INTO `address` VALUES (2, 12, 0, '小八嘎', '123123123', '广东省 广州市 黄浦区 广州商学院');
INSERT INTO `address` VALUES (3, 12, 0, '小豹猫', '123123123', '广东省 广州市 黄浦区 广州商学院');

-- ----------------------------
-- Table structure for appointment_order
-- ----------------------------
DROP TABLE IF EXISTS `appointment_order`;
CREATE TABLE `appointment_order`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '预约订单编号',
  `name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '预约人姓名',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '预约状态：0-未确认 1-已确认 2-已完成 3-已失效',
  `mainicurist_id` int NOT NULL COMMENT '美甲师ID',
  `user_id` int NULL DEFAULT NULL COMMENT '买家ID',
  `date` datetime NOT NULL COMMENT '预约时间',
  `project_id` int NOT NULL COMMENT '项目 id',
  `phone` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '手机号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of appointment_order
-- ----------------------------
INSERT INTO `appointment_order` VALUES (1, 'qw', 3, 1, 12, '2023-12-21 08:00:00', 14, '12');
INSERT INTO `appointment_order` VALUES (2, 'ddd', 2, 2, 12, '2023-12-21 08:00:00', 14, '23');
INSERT INTO `appointment_order` VALUES (3, '小花花', 2, 1, 12, '2023-12-22 08:00:00', 14, '13435647872');
INSERT INTO `appointment_order` VALUES (4, 'sadf', 3, 2, 12, '2023-12-21 08:00:00', 11, '13432252523');
INSERT INTO `appointment_order` VALUES (5, '小明', 2, 1, 12, '2023-12-27 08:00:00', 18, '13432524323');

-- ----------------------------
-- Table structure for authorization
-- ----------------------------
DROP TABLE IF EXISTS `authorization`;
CREATE TABLE `authorization`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int NOT NULL COMMENT '用户ID',
  `IDENTITYTYPE` tinyint NOT NULL COMMENT '登录类别：0-账号密码 1-微信登录',
  `IDENTIFIER` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '身份唯一标识，如：登录账号、邮箱地址、手机号码、QQ号码、微信号、微博号；',
  `CREDENTIAL` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '站内账号是密码、第三方登录是Token；',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of authorization
-- ----------------------------
INSERT INTO `authorization` VALUES (1, 1, 0, 'root', '123123');
INSERT INTO `authorization` VALUES (4, 6, 0, 'test', '234');
INSERT INTO `authorization` VALUES (6, 8, 0, '12312312312', '123456');
INSERT INTO `authorization` VALUES (7, 9, 0, '123123345', '123456');
INSERT INTO `authorization` VALUES (9, 11, 0, '123123456', '123123');
INSERT INTO `authorization` VALUES (10, 12, 0, 'test', '123123');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '买家姓名',
  `content` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '评论内容',
  `avg_url` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户头像链接',
  `time` datetime NOT NULL COMMENT '评论时间',
  `user_id` int NOT NULL COMMENT '用户ID',
  `img_ids` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '评论图片id列表',
  `product_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '订单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, 'test', '好好看的美甲', ' https://api.dicebear.com/7.x/adventurer/svg?seed=Precious', '2023-12-20 01:43:23', 12, 'http://localhost:8888/youyi/upload/readFile/bdefee9f65d244fc563bba136c1b02bc.jpg-', '28');
INSERT INTO `comment` VALUES (2, 'test', 'sdfdsfsddsf', ' https://api.dicebear.com/7.x/adventurer/svg?seed=Precious', '2023-12-20 01:48:24', 12, 'http://localhost:8888/youyi/upload/readFile/bdefee9f65d244fc563bba136c1b02bc.jpg-', '24');
INSERT INTO `comment` VALUES (3, ' 测试用户', '哈哈哈好好看的衣服', ' https://api.dicebear.com/7.x/adventurer/svg?seed=Precious', '2023-12-20 20:17:13', 12, 'http://localhost:8888/youyi/upload/readFile/c2b71f0383c9532a467b73b58406178c.jpg-', '20');
INSERT INTO `comment` VALUES (11, ' 测试用户', 'sdfsdf', ' https://api.dicebear.com/7.x/adventurer/svg?seed=Precious', '2023-12-20 22:22:01', 12, 'http://localhost:8888/youyi/upload/readFile/218320beb2f8ffc861bbe74b5bd55021.jpg-', '42');
INSERT INTO `comment` VALUES (17, ' 测试用户', '快来买，真的好好看哦', ' https://api.dicebear.com/7.x/adventurer/svg?seed=Precious', '2023-12-20 22:30:52', 12, 'http://localhost:8888/youyi/upload/readFile/bdefee9f65d244fc563bba136c1b02bc.jpg-http://localhost:8888/youyi/upload/readFile/c2b71f0383c9532a467b73b58406178c.jpg-http://localhost:8888/youyi/upload/readFile/f5ec7519f215cf79ef37d01c0c0df525.png-http://localhost:8888/youyi/upload/readFile/998154c5966aac7f226f8cc161aa0ac1.jpg-', '44');
INSERT INTO `comment` VALUES (18, ' 测试用户', 'sdfsdf', ' https://api.dicebear.com/7.x/adventurer/svg?seed=Precious', '2023-12-20 22:31:47', 12, '', '42');
INSERT INTO `comment` VALUES (19, ' 测试用户', 'sdfsdfsd', ' https://api.dicebear.com/7.x/adventurer/svg?seed=Precious', '2023-12-20 22:32:50', 12, 'http://localhost:8888/youyi/upload/readFile/dcb166cf000a7c72a1c5f3ba494a5743.jpg-', '42');
INSERT INTO `comment` VALUES (20, ' 测试用户', 'dsfsdfs', ' https://api.dicebear.com/7.x/adventurer/svg?seed=Precious', '2023-12-20 22:35:12', 12, 'http://localhost:8888/youyi/upload/readFile/c2b71f0383c9532a467b73b58406178c.jpg-', '44');

-- ----------------------------
-- Table structure for manicure_project
-- ----------------------------
DROP TABLE IF EXISTS `manicure_project`;
CREATE TABLE `manicure_project`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '项目名',
  `new_price` double(10, 2) NOT NULL COMMENT '新价格',
  `old_price` double(10, 2) NOT NULL COMMENT '旧价格',
  `miaoshu` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '商品描述',
  `url` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '项目图片',
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of manicure_project
-- ----------------------------
INSERT INTO `manicure_project` VALUES (6, '12312', 12312.00, 12312.00, '12312321', 'http://localhost:8888/youyi/upload/readFile/dcb166cf000a7c72a1c5f3ba494a5743.jpg', '2023-12-19 22:08:52');
INSERT INTO `manicure_project` VALUES (7, '22', 22.00, 222.00, '222', 'http://localhost:8888/youyi/upload/readFile/c4e012139bcb578102478263b6ce206a.jpg', '2023-12-19 22:10:26');
INSERT INTO `manicure_project` VALUES (8, '22', 333.00, 555.00, '666', 'http://localhost:8888/youyi/upload/readFile/a175f3bfb8a4e7048439dd60df99d574.jpg', '2023-12-19 22:11:04');
INSERT INTO `manicure_project` VALUES (9, 'wert', 23.00, 2332.00, '2323', 'http://localhost:8888/youyi/upload/readFile/c2b71f0383c9532a467b73b58406178c.jpg', '2023-12-19 22:26:15');
INSERT INTO `manicure_project` VALUES (11, '23', 234.00, 23324.00, '23423', 'http://localhost:8888/youyi/upload/readFile/998154c5966aac7f226f8cc161aa0ac1.jpg', '2023-12-19 22:26:46');
INSERT INTO `manicure_project` VALUES (12, '233', 23.00, 2323.00, '2332', 'http://localhost:8888/youyi/upload/readFile/f62fcb4665575b40c558e185ef906ab8.jpg', '2023-12-19 22:31:04');
INSERT INTO `manicure_project` VALUES (13, 'sd', 23.00, 2332.00, '232', 'http://localhost:8888/youyi/upload/readFile/218320beb2f8ffc861bbe74b5bd55021.jpg', '2023-12-19 22:31:24');
INSERT INTO `manicure_project` VALUES (14, '23', 23.00, 23.00, '23444', 'http://localhost:8888/youyi/upload/readFile/f62fcb4665575b40c558e185ef906ab8.jpg', '2023-12-19 22:32:22');
INSERT INTO `manicure_project` VALUES (15, '23', 23.00, 23.00, '23', 'http://localhost:8888/youyi/upload/readFile/f62fcb4665575b40c558e185ef906ab8.jpg', '2023-12-19 22:31:34');
INSERT INTO `manicure_project` VALUES (16, '23', 23.00, 23.00, '23', 'http://localhost:8888/youyi/upload/readFile/f62fcb4665575b40c558e185ef906ab8.jpg', '2023-12-19 22:31:34');
INSERT INTO `manicure_project` VALUES (18, '美甲项目test', 20.00, 30.00, '这个就是最好的产品', 'http://localhost:8888/youyi/upload/readFile/f5ec7519f215cf79ef37d01c0c0df525.png', '2023-12-21 00:35:43');

-- ----------------------------
-- Table structure for manicurist
-- ----------------------------
DROP TABLE IF EXISTS `manicurist`;
CREATE TABLE `manicurist`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '美甲师ID',
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '美甲师姓名',
  `employment_time` int NOT NULL COMMENT '从业时间',
  `works` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '作品集',
  `reservation_count` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '预约人数',
  `url` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '美甲师头像',
  `good` int(10) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '好评数',
  `kucun` int NOT NULL DEFAULT 20 COMMENT '最大预约数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of manicurist
-- ----------------------------
INSERT INTO `manicurist` VALUES (1, '11', 11, 'http://localhost:8888/youyi/upload/readFile/bdefee9f65d244fc563bba136c1b02bc.jpg-', 11, 'http://localhost:8888/youyi/upload/readFile/bdefee9f65d244fc563bba136c1b02bc.jpg', NULL, 20);
INSERT INTO `manicurist` VALUES (2, '2', 2, 'http://localhost:8888/youyi/upload/readFile/bdefee9f65d244fc563bba136c1b02bc.jpg-', 2, 'http://localhost:8888/youyi/upload/readFile/218320beb2f8ffc861bbe74b5bd55021.jpg', NULL, 20);
INSERT INTO `manicurist` VALUES (3, '11', 11, 'http://localhost:8888/youyi/upload/readFile/bdefee9f65d244fc563bba136c1b02bc.jpg-', 2, 'http://localhost:8888/youyi/upload/readFile/c2b71f0383c9532a467b73b58406178c.jpg', NULL, 11);
INSERT INTO `manicurist` VALUES (4, '22', 33, 'http://localhost:8888/youyi/upload/readFile/bdefee9f65d244fc563bba136c1b02bc.jpg-', 3, 'http://localhost:8888/youyi/upload/readFile/c2b71f0383c9532a467b73b58406178c.jpg', NULL, 44);
INSERT INTO `manicurist` VALUES (5, 'wew', 22, 'http://localhost:8888/youyi/upload/readFile/bdefee9f65d244fc563bba136c1b02bc.jpg-', 4, 'http://localhost:8888/youyi/upload/readFile/bdefee9f65d244fc563bba136c1b02bc.jpg', NULL, 22);

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `from_id` int NOT NULL COMMENT '发送用户ID',
  `to_id` int NOT NULL COMMENT '接收用户ID',
  `conversation_id` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '频道ID',
  `status` tinyint NOT NULL COMMENT '消息状态：0-未读 1-已读',
  `send_time` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '发送时间',
  `content` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '发送内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (40, 12, 1, '1_12', 0, '2023-12-21 22:11:24', 'sdfdsfsd');
INSERT INTO `message` VALUES (41, 1, 12, '1_12', 0, '2023-12-21 22:11:46', '什么问题');
INSERT INTO `message` VALUES (42, 12, 1, '1_12', 0, '2023-12-21 22:12:01', '美甲发错了');
INSERT INTO `message` VALUES (43, 1, 12, '1_12', 0, '2023-12-21 22:12:26', '收到收到，你退回来就好了，我重新给你发');

-- ----------------------------
-- Table structure for order_product_info
-- ----------------------------
DROP TABLE IF EXISTS `order_product_info`;
CREATE TABLE `order_product_info`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '商品名',
  `product_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品价格',
  `product_size` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '商品尺寸',
  `product_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '商品图片',
  `product_miaoshu` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '商品描述',
  `product_number` int NULL DEFAULT NULL COMMENT '下单件数',
  `product_id` int NULL DEFAULT NULL COMMENT '产品ID',
  `review_status` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '评论状态：0-未评论 1-已评论',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_product_info
-- ----------------------------
INSERT INTO `order_product_info` VALUES (29, '手工穿戴甲 长款高级线条感、轻奢 - 19', 45.00, 'S码', 'https://aoao-jiao.oss-cn-guangzhou.aliyuncs.com/iamge/3.png', '低调奢华', 1, 21, 1);
INSERT INTO `order_product_info` VALUES (30, '手工穿戴甲 长款高级线条感、轻奢 - 19', 45.00, 'S码', 'https://aoao-jiao.oss-cn-guangzhou.aliyuncs.com/iamge/3.png', '低调奢华', 1, 20, 1);
INSERT INTO `order_product_info` VALUES (31, '手工穿戴甲 长款高级线条感、轻奢 - 19', 45.00, 'M码', 'https://aoao-jiao.oss-cn-guangzhou.aliyuncs.com/iamge/3.png', '低调奢华', 1, 21, 1);
INSERT INTO `order_product_info` VALUES (32, '112', 111.00, 'M码', 'http://localhost:8888/youyi/upload/readFile/4f8f4493b2658b4e96dc8b4e9c00ce29.jpg', '111', 1, 28, 1);
INSERT INTO `order_product_info` VALUES (33, '11', 11.00, 'M码', 'http://localhost:8888/youyi/upload/readFile/6ae45b758da020d9a332da6c7d4bb12b.jpg', '11', 1, 24, 1);
INSERT INTO `order_product_info` VALUES (34, '手工穿戴甲 长款高级线条感、轻奢 - 19', 45.00, 'S码', 'https://aoao-jiao.oss-cn-guangzhou.aliyuncs.com/iamge/3.png', '低调奢华', 1, 21, 1);
INSERT INTO `order_product_info` VALUES (35, '手工穿戴甲 长款高级线条感、轻奢 - 19', 45.00, 'S码', 'https://aoao-jiao.oss-cn-guangzhou.aliyuncs.com/iamge/3.png', '低调奢华', 4, 20, 1);
INSERT INTO `order_product_info` VALUES (36, '手工穿戴甲 长款高级线条感、轻奢 - 19', 45.00, 'S码', 'https://aoao-jiao.oss-cn-guangzhou.aliyuncs.com/iamge/3.png', '低调奢华', 1, 19, 0);
INSERT INTO `order_product_info` VALUES (37, '新品推荐 手工穿戴甲 ', 45.00, 'S码', 'https://aoao-jiao.oss-cn-guangzhou.aliyuncs.com/iamge/3.png', '低调奢华', 1, 18, 0);
INSERT INTO `order_product_info` VALUES (38, '手工穿戴甲 长款高级线条感、轻奢 - 19', 45.00, 'S码', 'https://aoao-jiao.oss-cn-guangzhou.aliyuncs.com/iamge/3.png', '低调奢华', 1, 20, 1);
INSERT INTO `order_product_info` VALUES (39, '手工穿戴甲 长款高级线条感、轻奢 - 19', 45.00, 'S码', 'https://aoao-jiao.oss-cn-guangzhou.aliyuncs.com/iamge/3.png', '低调奢华', 1, 21, 1);
INSERT INTO `order_product_info` VALUES (40, 'test美甲', 11.00, 'M码', 'http://localhost:8888/youyi/upload/readFile/bdefee9f65d244fc563bba136c1b02bc.jpg', '11', 1, 22, 0);
INSERT INTO `order_product_info` VALUES (41, '11', 11.00, 'M码', 'http://localhost:8888/youyi/upload/readFile/218320beb2f8ffc861bbe74b5bd55021.jpg', '11', 1, 23, 1);
INSERT INTO `order_product_info` VALUES (42, '11', 11.00, 'XL码', 'http://localhost:8888/youyi/upload/readFile/6ae45b758da020d9a332da6c7d4bb12b.jpg', '11', 1, 24, 1);
INSERT INTO `order_product_info` VALUES (43, '112', 111.00, 'M码', 'http://localhost:8888/youyi/upload/readFile/4f8f4493b2658b4e96dc8b4e9c00ce29.jpg', '111', 1, 28, 1);
INSERT INTO `order_product_info` VALUES (44, '11', 11.00, 'M码', 'http://localhost:8888/youyi/upload/readFile/6ae45b758da020d9a332da6c7d4bb12b.jpg', '11', 1, 24, 1);
INSERT INTO `order_product_info` VALUES (45, '新增商品 Test', 26.00, 'M码', 'http://localhost:8888/youyi/upload/readFile/bdefee9f65d244fc563bba136c1b02bc.jpg', '测试', 2, 29, 0);
INSERT INTO `order_product_info` VALUES (46, '112', 111.00, 'M码', 'http://localhost:8888/youyi/upload/readFile/4f8f4493b2658b4e96dc8b4e9c00ce29.jpg', '111', 1, 28, 0);
INSERT INTO `order_product_info` VALUES (47, '新增商品 Test', 26.00, 'M码', 'http://localhost:8888/youyi/upload/readFile/bdefee9f65d244fc563bba136c1b02bc.jpg', '测试', 1, 29, 0);
INSERT INTO `order_product_info` VALUES (48, '新增商品 Test', 26.00, 'M码', 'http://localhost:8888/youyi/upload/readFile/bdefee9f65d244fc563bba136c1b02bc.jpg', '测试', 1, 29, 0);
INSERT INTO `order_product_info` VALUES (49, '112', 111.00, 'M码', 'http://localhost:8888/youyi/upload/readFile/4f8f4493b2658b4e96dc8b4e9c00ce29.jpg', '111', 1, 28, 0);
INSERT INTO `order_product_info` VALUES (50, '新增商品 Test', 26.00, 'M码', 'http://localhost:8888/youyi/upload/readFile/bdefee9f65d244fc563bba136c1b02bc.jpg', '测试', 1, 29, 0);
INSERT INTO `order_product_info` VALUES (51, '112', 111.00, 'M码', 'http://localhost:8888/youyi/upload/readFile/4f8f4493b2658b4e96dc8b4e9c00ce29.jpg', '111', 1, 28, 0);
INSERT INTO `order_product_info` VALUES (52, '新增商品 Test', 26.00, 'M码', 'http://localhost:8888/youyi/upload/readFile/bdefee9f65d244fc563bba136c1b02bc.jpg', '测试', 1, 29, 0);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `order_id` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '订单id',
  `total_price` decimal(8, 2) NOT NULL COMMENT '订单总金额',
  `create_time` datetime NOT NULL COMMENT '订单创建时间',
  `totalNumber` int NOT NULL COMMENT '购买数量',
  `address_id` int NOT NULL COMMENT '地址表 id',
  `order_status` tinyint NOT NULL DEFAULT 0 COMMENT '订单状态：0-未发货 1-已发货 2-确认收货  3-售后 4-售后完成',
  `user_id` int NOT NULL COMMENT '买家 id',
  `product_ids` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '多个订单id',
  `logistics_tracking_number` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '物流单号',
  `logistics_waybill_number` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '物流运单号',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('1737157681892315136', 90.00, '2023-12-20 01:07:21', 2, 1, 2, 12, '29-30-', NULL, 'SF7444475272404');
INSERT INTO `orders` VALUES ('1737158068502286336', 45.00, '2023-12-20 01:08:53', 1, 1, 2, 12, '31-', NULL, 'SF7444475272404');
INSERT INTO `orders` VALUES ('1737158346957934592', 122.00, '2023-12-20 01:09:59', 2, 1, 1, 12, '32-33-', NULL, 'SF7444475272404');
INSERT INTO `orders` VALUES ('1737158442764226560', 180.00, '2023-12-20 01:10:22', 7, 1, 2, 12, '34-35-36-37-', NULL, 'SF7444475272404');
INSERT INTO `orders` VALUES ('1737443590785323008', 112.00, '2023-12-20 20:03:27', 4, 1, 2, 12, '38-39-40-41-', NULL, 'SF7444475272404');
INSERT INTO `orders` VALUES ('1737463585846075392', 11.00, '2023-12-20 21:22:54', 1, 1, 2, 12, '42-', NULL, 'SF7444475272404');
INSERT INTO `orders` VALUES ('1737464062696497152', 122.00, '2023-12-20 21:24:48', 2, 1, 2, 12, '43-44-', NULL, 'SF7444475272404');
INSERT INTO `orders` VALUES ('1737521412245671936', 26.00, '2023-12-21 01:12:41', 1, 1, 1, 12, '52-', NULL, 'SF7444475272404');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `url` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '商品图片',
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '商品名称',
  `miaoshu` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '商品描述',
  `price` decimal(8, 0) NOT NULL COMMENT '商品价格',
  `size` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '商品规格',
  `kucun` int NOT NULL COMMENT '商品库存',
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, 'https://aoao-jiao.oss-cn-guangzhou.aliyuncs.com/iamge/3.png', '手工穿戴甲 长款高级线条感、轻奢', '低调而彰显奢华', 26, 'S码-M码-L码', 1000, '2023-12-19 21:25:58');
INSERT INTO `product` VALUES (3, 'https://aoao-jiao.oss-cn-guangzhou.aliyuncs.com/iamge/3.png', '手工穿戴甲 长款高级线条感、轻奢 - 19', '低调奢华', 45, 'S码-M码-L码', 119, '2023-12-19 21:26:05');
INSERT INTO `product` VALUES (4, 'https://aoao-jiao.oss-cn-guangzhou.aliyuncs.com/iamge/3.png', '手工穿戴甲 长款高级线条感、轻奢 - 19', '低调奢华', 45, 'S码-M码-L码', 119, '2023-12-19 21:26:10');
INSERT INTO `product` VALUES (5, 'https://aoao-jiao.oss-cn-guangzhou.aliyuncs.com/iamge/3.png', '手工穿戴甲 长款高级线条感、轻奢 - 19', '低调奢华', 45, 'S码-M码-L码', 119, '2023-12-19 21:26:12');
INSERT INTO `product` VALUES (6, 'https://aoao-jiao.oss-cn-guangzhou.aliyuncs.com/iamge/3.png', '手工穿戴甲 长款高级线条感、轻奢 - 19', '低调奢华', 45, 'S码-M码-L码', 119, '2023-12-19 21:26:14');
INSERT INTO `product` VALUES (7, 'https://aoao-jiao.oss-cn-guangzhou.aliyuncs.com/iamge/3.png', '手工穿戴甲 长款高级线条感、轻奢 - 19', '低调奢华', 45, 'S码-M码-L码', 119, '2023-12-19 21:26:17');
INSERT INTO `product` VALUES (8, 'https://aoao-jiao.oss-cn-guangzhou.aliyuncs.com/iamge/3.png', '手工穿戴甲 长款高级线条感、轻奢 - 19', '低调奢华', 45, 'S码-M码-L码', 119, '2023-12-19 21:26:18');
INSERT INTO `product` VALUES (9, 'https://aoao-jiao.oss-cn-guangzhou.aliyuncs.com/iamge/3.png', '手工穿戴甲 长款高级线条感、轻奢 - 19', '低调奢华', 45, 'S码-M码-L码', 119, '2023-12-19 21:26:20');
INSERT INTO `product` VALUES (10, 'https://aoao-jiao.oss-cn-guangzhou.aliyuncs.com/iamge/3.png', '手工穿戴甲 长款高级线条感、轻奢 - 19', '低调奢华', 45, 'S码-M码-L码', 119, '2023-12-19 21:26:21');
INSERT INTO `product` VALUES (11, 'https://aoao-jiao.oss-cn-guangzhou.aliyuncs.com/iamge/3.png', '手工穿戴甲 长款高级线条感、轻奢 - 19', '低调奢华', 45, 'S码-M码-L码', 119, '2023-12-19 21:26:23');
INSERT INTO `product` VALUES (12, 'https://aoao-jiao.oss-cn-guangzhou.aliyuncs.com/iamge/3.png', '手工穿戴甲 长款高级线条感、轻奢 - 19', '低调奢华', 45, 'S码-M码-L码', 119, '2023-12-19 21:26:24');
INSERT INTO `product` VALUES (13, 'https://aoao-jiao.oss-cn-guangzhou.aliyuncs.com/iamge/3.png', '手工穿戴甲 长款高级线条感、轻奢 - 19', '低调奢华', 45, 'S码-M码-L码', 119, '2023-12-19 21:26:25');
INSERT INTO `product` VALUES (14, 'https://aoao-jiao.oss-cn-guangzhou.aliyuncs.com/iamge/3.png', '手工穿戴甲 长款高级线条感、轻奢 - 19', '低调奢华', 45, 'S码-M码-L码', 119, '2023-12-19 21:26:26');
INSERT INTO `product` VALUES (15, 'https://aoao-jiao.oss-cn-guangzhou.aliyuncs.com/iamge/3.png', '手工穿戴甲 长款高级线条感、轻奢 - 19', '低调奢华', 45, 'S码-M码-L码', 119, '2023-12-19 21:26:28');
INSERT INTO `product` VALUES (16, 'https://aoao-jiao.oss-cn-guangzhou.aliyuncs.com/iamge/3.png', '手工穿戴甲 长款高级线条感、轻奢 - 19', '低调奢华', 45, 'S码-M码-L码', 119, '2023-12-19 21:26:29');
INSERT INTO `product` VALUES (17, 'https://aoao-jiao.oss-cn-guangzhou.aliyuncs.com/iamge/3.png', '手工穿戴甲 长款高级线条感、轻奢 - 19', '低调奢华', 45, 'S码-M码-L码', 119, '2023-12-19 21:26:30');
INSERT INTO `product` VALUES (18, 'https://aoao-jiao.oss-cn-guangzhou.aliyuncs.com/iamge/3.png', '新品推荐 手工穿戴甲 ', '低调奢华', 45, 'S码-M码-L码', 119, '2023-12-19 21:26:32');
INSERT INTO `product` VALUES (19, 'https://aoao-jiao.oss-cn-guangzhou.aliyuncs.com/iamge/3.png', '手工穿戴甲 长款高级线条感、轻奢 - 19', '低调奢华', 45, 'S码-M码-L码', 119, '2023-12-19 21:26:33');
INSERT INTO `product` VALUES (20, 'https://aoao-jiao.oss-cn-guangzhou.aliyuncs.com/iamge/3.png', '手工穿戴甲 长款高级线条感、轻奢 - 19', '低调奢华', 45, 'S码-M码-L码', 119, '2023-12-19 21:26:37');
INSERT INTO `product` VALUES (21, 'https://aoao-jiao.oss-cn-guangzhou.aliyuncs.com/iamge/3.png', '手工穿戴甲 长款高级线条感、轻奢 - 19', '低调奢华', 45, 'S码-M码-L码', 119, '2023-12-19 21:26:39');
INSERT INTO `product` VALUES (22, 'http://localhost:8888/youyi/upload/readFile/bdefee9f65d244fc563bba136c1b02bc.jpg', 'test美甲', '11', 11, 'M码-S码-L码-', 11, '2023-12-19 21:26:40');
INSERT INTO `product` VALUES (23, 'http://localhost:8888/youyi/upload/readFile/218320beb2f8ffc861bbe74b5bd55021.jpg', '11', '11', 11, 'M码-S码-L码-', 11, '2023-12-19 21:29:47');
INSERT INTO `product` VALUES (24, 'http://localhost:8888/youyi/upload/readFile/6ae45b758da020d9a332da6c7d4bb12b.jpg', '11', '11', 11, 'M码-S码-L码-XL码-', 11, '2023-12-19 21:30:11');
INSERT INTO `product` VALUES (28, 'http://localhost:8888/youyi/upload/readFile/4f8f4493b2658b4e96dc8b4e9c00ce29.jpg', '112', '111', 111, 'M码-S码-L码-', 111, '2023-12-19 21:40:19');
INSERT INTO `product` VALUES (29, 'http://localhost:8888/youyi/upload/readFile/bdefee9f65d244fc563bba136c1b02bc.jpg', '新增商品 Test', '测试', 26, 'M码-S码-L码-', 11, '2023-12-21 00:37:47');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `phone_number` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT ' ' COMMENT '用户手机号',
  `username` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT ' ' COMMENT '用户名',
  `sex` tinyint NOT NULL DEFAULT 0 COMMENT '用户性别：1-男 2-女',
  `url` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT ' ' COMMENT '用户头像',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '0-正常 1-禁用 2-删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (1, '110', ' 超级管理员', 0, ' https://api.dicebear.com/7.x/adventurer/svg?seed=Precious', 0);
INSERT INTO `user_info` VALUES (2, ' ', 'sdfsd', 2, 'https://api.dicebear.com/7.x/adventurer/svg?seed=Tigger', 0);
INSERT INTO `user_info` VALUES (3, ' ', 'aelayy', 2, 'https://api.dicebear.com/7.x/adventurer/svg?seed=Tigger', 0);
INSERT INTO `user_info` VALUES (4, ' ', 'ws791b', 2, 'https://api.dicebear.com/7.x/adventurer/svg?seed=Tigger', 0);
INSERT INTO `user_info` VALUES (5, ' ', '8zc46g', 2, 'https://api.dicebear.com/7.x/adventurer/svg?seed=Tigger', 0);
INSERT INTO `user_info` VALUES (6, ' ', 'm7vjti', 2, 'https://api.dicebear.com/7.x/adventurer/svg?seed=Tigger', 0);
INSERT INTO `user_info` VALUES (7, ' ', 'ldwyel', 2, 'https://api.dicebear.com/7.x/adventurer/svg?seed=Tigger', 0);
INSERT INTO `user_info` VALUES (8, ' ', 'y6vr2f', 2, 'https://api.dicebear.com/7.x/adventurer/svg?seed=Tigger', 0);
INSERT INTO `user_info` VALUES (9, ' ', 'b87ywl', 2, 'https://api.dicebear.com/7.x/adventurer/svg?seed=Tigger', 0);
INSERT INTO `user_info` VALUES (10, ' ', 'f6zkwj', 2, 'https://api.dicebear.com/7.x/adventurer/svg?seed=Tigger', 0);
INSERT INTO `user_info` VALUES (11, ' ', '93nuec', 2, 'https://api.dicebear.com/7.x/adventurer/svg?seed=Tigger', 0);
INSERT INTO `user_info` VALUES (12, ' 119', ' 测试用户', 0, ' https://api.dicebear.com/7.x/adventurer/svg?seed=Precious', 0);

SET FOREIGN_KEY_CHECKS = 1;
