/*
 Navicat Premium Data Transfer

 Source Server         : Drive
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : house_db

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 16/03/2022 11:12:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `admin_name` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `admin_password` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  PRIMARY KEY (`admin_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('sd', 'swewe2');

-- ----------------------------
-- Table structure for custom
-- ----------------------------
DROP TABLE IF EXISTS `custom`;
CREATE TABLE `custom`  (
  `custom_id` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户编号',
  `custom_name` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户姓名',
  `custom_sex` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户性别',
  `custom_phone` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户电话',
  `custom_remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户备注',
  PRIMARY KEY (`custom_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of custom
-- ----------------------------
INSERT INTO `custom` VALUES ('100001', '王宇', '女', '1678902233', '现居贵州、买房意向客户');
INSERT INTO `custom` VALUES ('13123', '王松', '男', '113213221', '爱仕达多');
INSERT INTO `custom` VALUES ('131233', '离松', '男', '113213221', '爱仕达多');
INSERT INTO `custom` VALUES ('1312334', '许松', '女', '113213221', '爱仕达多');
INSERT INTO `custom` VALUES ('3213131213221', '王企鹅群', '女', '2313', '132213');
INSERT INTO `custom` VALUES ('442423111', '王宇', '女', '1678902233', '认真');
INSERT INTO `custom` VALUES ('442423111121', '王而然', '女', '1678902233', '毕节人');
INSERT INTO `custom` VALUES ('522410021565422', '达达', '女', '1678902236', '有意租房');

-- ----------------------------
-- Table structure for house
-- ----------------------------
DROP TABLE IF EXISTS `house`;
CREATE TABLE `house`  (
  `house_id` varchar(23) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
  `houser_name` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '房主',
  `house_title` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题(房屋出租/房屋出售)',
  `house_area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '地址',
  `house_pay` double(15, 0) NOT NULL COMMENT '租金/成交价（价格）',
  `house_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '详情描述',
  `house_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '图片',
  `house_state` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '房源状态（已租/未租,已售/未售）',
  `house_contact` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系人（员工填写个人账号）',
  `house_phone` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话',
  `startTime` date NOT NULL COMMENT '发布时间',
  PRIMARY KEY (`house_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of house
-- ----------------------------
INSERT INTO `house` VALUES ('001', '阿萨德', '房屋出租', '阿萨', 1233, '三室一厅', '../../../../static/upload/image/001.jpg', '已租', '38381718103', '15117578949', '2022-03-04');
INSERT INTO `house` VALUES ('002', '阿萨德', '房屋出售', '阿萨', 20000, '三室一厅一厨一卫', '../../../../static/upload/image/002.jpg', '已售', '38381718103', '15117578949', '2022-03-03');
INSERT INTO `house` VALUES ('003', '阿萨德', '房屋出售', '阿萨', 20000, '三室一厅一厨一卫', '../../../../static/upload/image/003.jpg', '出售', '38381718103', '15117578949', '2022-03-05');

-- ----------------------------
-- Table structure for houser
-- ----------------------------
DROP TABLE IF EXISTS `houser`;
CREATE TABLE `houser`  (
  `houser_id` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '房东编号',
  `houser_name` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `houser_sex` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别',
  `houser_phone` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系方式',
  `houser_wages` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '地址',
  `houser_remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '备注',
  PRIMARY KEY (`houser_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of houser
-- ----------------------------

-- ----------------------------
-- Table structure for housetype
-- ----------------------------
DROP TABLE IF EXISTS `housetype`;
CREATE TABLE `housetype`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `type` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '房屋类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of housetype
-- ----------------------------
INSERT INTO `housetype` VALUES (1, '三室两厅');
INSERT INTO `housetype` VALUES (2, '三室一厅');
INSERT INTO `housetype` VALUES (3, '三室一厅一厨一卫');
INSERT INTO `housetype` VALUES (4, '三室一厅两卫');
INSERT INTO `housetype` VALUES (5, '三室一厅一厨');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `Message_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '留言号',
  `Message_name` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '留言姓名',
  `Message_phone` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系方式',
  `Message_remarks` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '留言详情',
  PRIMARY KEY (`Message_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (1, '龚红艳艳', '112313131', '编号为2333434可以租吗');
INSERT INTO `message` VALUES (2, '王企鹅群', '112321', '是撒多大奥');

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record`  (
  `record_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '记录编号',
  `house_id` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '房源编号',
  `record_state` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '交易状态',
  `record_pay` double(15, 0) NOT NULL COMMENT '交易金额',
  `record_start` date NOT NULL COMMENT '开始时间',
  `record_end` date NOT NULL COMMENT '结束时间',
  `record_remarks` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '备注',
  `record_contact` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '交易人',
  `record_phone` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系电话·',
  `deposit` double(12, 0) NOT NULL COMMENT '押金',
  `agency_fee` double(12, 0) NOT NULL COMMENT '中介费',
  PRIMARY KEY (`record_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of record
-- ----------------------------

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff`  (
  `staff_id` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '员工编号/账号',
  `staff_name` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '员工姓名',
  `staff_sex` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别',
  `staff_phone` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话号码',
  `staff_wages` double(25, 0) NOT NULL COMMENT '工资',
  `staff_remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '备注',
  `staff_state` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '在职状态（离职,在职）',
  `staff_roles` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '员工角色(0经理,1普通员工)',
  `staff_time` date NOT NULL COMMENT '员工入职时间',
  `password` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录密码',
  PRIMARY KEY (`staff_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of staff
-- ----------------------------
INSERT INTO `staff` VALUES ('38381718101', '发于', '女', '1678902233', 4000, '态度认真', '离职', '0', '2022-02-23', '1234567');
INSERT INTO `staff` VALUES ('38381718102', '发于于', '男', '1678902233', 4000, '态度认真', '在职', '0', '2022-02-27', '123456');
INSERT INTO `staff` VALUES ('38381718103', '宫洪彦', '男', '15117578949', 8000, '现住毕节', '在职', '1', '2022-01-20', 'ghy199711');
INSERT INTO `staff` VALUES ('38381718104', '王淼秒', '女', '1567892345', 5000, '喜欢旅游，性格开朗乐观，热情友好，能吃苦耐劳，学习能力强。三年的校园学习生活经历使我积累了较强的组织、协调沟通能力和团队合作精神，具有较强的责任心。面对校外的实习机会我会努力认真的工作，积累更多的相关工作经验，能够在实习期间在处理问题时能够取得重大的提升，使自己更成熟。对事物有敏锐的洞察力，多次的社会实践经历及在学生会工作期间锻炼了我与人沟通合作的能力以及独立能力，做事认真负责。', '在职', '0', '2022-02-27', '123456');
INSERT INTO `staff` VALUES ('38381718105', '于苏苏', '女', '15117579934', 7000, '情分', '在职', '0', '2022-02-28', '123456');
INSERT INTO `staff` VALUES ('38381718129', '胡于', '男', '1678902233', 4000, '态度认真', '在职', '0', '2022-03-01', '123456');
INSERT INTO `staff` VALUES ('38381718130', '胡包包', '女', '1678902233', 4000, '态度认真', '在职', '0', '2022-03-01', '123456');
INSERT INTO `staff` VALUES ('38381718145', '徐媛媛', '女', '123121312321', 4000, '娱乐', '在职', '0', '2022-03-06', '123456');
INSERT INTO `staff` VALUES ('38381718193', '西语', '女', '151517453456', 4000, '现居六盘水', '在职', '0', '2022-02-24', '123456');

SET FOREIGN_KEY_CHECKS = 1;
