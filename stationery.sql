create database stationery;
use stationery;

-- 领用信息表 stationery_requisition
DROP TABLE IF EXISTS `stationery_requisition`;
CREATE TABLE `stationery_requisition`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '领用id',
  `ids` int COMMENT '文具id',
  `name` varchar(32)  DEFAULT NULL COMMENT '文具名称',
  `nums` varchar(32) NULL DEFAULT NULL COMMENT '领用数量',
  `department` varchar(32) NULL DEFAULT NULL COMMENT '领用部门',
  `specification` varchar(32) NULL DEFAULT NULL COMMENT '规格/型号',
  `price` double(10, 0) NULL DEFAULT NULL COMMENT '文具价格',
  `upload_time` varchar(32) NULL DEFAULT NULL COMMENT '最近操作时间',
  `status` varchar(1) NULL DEFAULT '0' COMMENT '状态（0：可领用，1:已领用，2：归还中，3：已下架）',
  `borrower` varchar(32) NULL DEFAULT NULL COMMENT '领用人',
  `borrower_id` int NULL DEFAULT NULL COMMENT '领用人id',
  `borrow_time` varchar(32) NULL DEFAULT NULL COMMENT '领用时间',
  `return_time` varchar(32) NULL DEFAULT NULL COMMENT '预计归还时间',
  PRIMARY KEY (`id`) USING BTREE
);

INSERT INTO `stationery_requisition` VALUES (1, 1, 'A4纸', '1', '财务部', '80g', 1, '2024-06-05', '0', '', 1, '', '');
INSERT INTO `stationery_requisition` VALUES (2, 2, '红笔', '1', '财务部', '10g', 1, '2024-06-05', '1', '吴佳', 1, '2024-06-05', '2024-12-08');
INSERT INTO `stationery_requisition` VALUES (3, 3, '文件夹', '1', '财务部', '60g', 1, '2024-06-05', '1', '吴佳', 1, '2024-06-05', '2025-01-05');
INSERT INTO `stationery_requisition` VALUES (4, 4, '黑笔', '1', '行政部', '10g', 2, '2024-06-05', '2', '张三', 2, '2024-06-05', '2025-03-30');
INSERT INTO `stationery_requisition` VALUES (5, 5, '剪刀', '1', '财务部', '120g', 4, '2024-06-05', '1', '吴佳', 1, '2024-06-05', '2024-06-30');
INSERT INTO `stationery_requisition` VALUES (6, 6, '红笔', '1', NULL, '10g', 1, '2024-06-05', '0', NULL, NULL, NULL, NULL);
INSERT INTO `stationery_requisition` VALUES (7, 8, '透明胶', '1', '行政部', '78g', 3, '2024-06-05', '0', '', 2, '', '');



-- 文具表 
DROP TABLE IF EXISTS `stationerys`;
CREATE TABLE `stationerys`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '文具id',
  `name` varchar(32)  DEFAULT NULL COMMENT '文具名称',
  `specification` varchar(32) NULL DEFAULT NULL COMMENT '规格/型号',
  `supplier` varchar(32) NULL DEFAULT NULL COMMENT '文具供应商',
  `purchaser` varchar(32) NULL DEFAULT NULL COMMENT '文具采购人',
  `price` double(10, 0) NULL DEFAULT NULL COMMENT '文具价格',
  `upload_time` varchar(32) NULL DEFAULT NULL COMMENT '文具上架时间',
  PRIMARY KEY (`id`) USING BTREE
);

INSERT INTO `stationerys` VALUES (null, 'A4纸', '80g', '大力公司', '张杰', 1.5, '2023-05-01');
INSERT INTO `stationerys` VALUES (null, '红笔', '10g', '广达公司', '张杰', 1.2, '2023-05-01');
INSERT INTO `stationerys` VALUES (null, '文件夹', '60g', '大力公司', '李明', 1.1, '2023-05-01');
INSERT INTO `stationerys` VALUES (null, '黑笔', '10g', '广达公司', '吴家欣', 1.56, '2023-05-01');
INSERT INTO `stationerys` VALUES (null, '剪刀', '120g', '韵达公司', '吴京', 3.98, '2023-05-01');
INSERT INTO `stationerys` VALUES (null, '红笔', '10g', '广达公司', '张杰', 1.2, '2023-05-01');
INSERT INTO `stationerys` VALUES (null, '橡皮', '120g', '大力公司', '李明', 1.14, '2023-05-01');
INSERT INTO `stationerys` VALUES (null, '透明胶', '78g', '中通公司', '吴家欣', 3.07, '2023-05-01');
INSERT INTO `stationerys` VALUES (null, '文件袋', '110g', '大力公司', '张杰', 1.5, '2023-05-01');
INSERT INTO `stationerys` VALUES (null, '订书机', '10g', '邮政公司', '张子枫', 1.2, '2023-05-01');
INSERT INTO `stationerys` VALUES (null, '笔记本', '60g', '大力公司', '李明', 1.1, '2023-05-01');
INSERT INTO `stationerys` VALUES (null, '笔记本电脑', '2.5kg', '广达公司', '吴家欣', 8650, '2023-05-01');


-- 领用记录表 
DROP TABLE IF EXISTS `stationery_record`;
CREATE TABLE `stationery_record`  (
  `record_id` int NOT NULL AUTO_INCREMENT COMMENT '领用记录id',
  `record_ids`int COMMENT '领用的文具id',
  `record_name` varchar(32) NULL DEFAULT NULL COMMENT '领用的文具名称',
  `record_borrower` varchar(32) NULL DEFAULT NULL COMMENT '领用人',
  `record_borrower_id` varchar(32) NULL DEFAULT NULL COMMENT '领用人id',
  `record_nums` varchar(32) NULL DEFAULT NULL COMMENT '领用数量',
  `record_borrow_time` varchar(32) NULL DEFAULT NULL COMMENT '领用时间',
  `record_return_time` varchar(32) NULL DEFAULT NULL COMMENT '文具归还时间',
  PRIMARY KEY (`record_id`) USING BTREE
);

INSERT INTO `stationery_record` VALUES (1, 8, '透明胶', '张三', '2', '1', '2024-06-05', '2024-06-05');
INSERT INTO `stationery_record` VALUES (2, 1, 'A4纸', '吴佳', '1', '1', '2024-06-05', '2024-06-05');

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(32) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(32) NULL DEFAULT NULL COMMENT '用户名称',
  `user_password` varchar(32) NULL DEFAULT NULL COMMENT '用户密码',
  `user_email` varchar(32) NULL DEFAULT NULL COMMENT '用户邮箱（用户账号）',
  `user_hiredate` varchar(32) NULL DEFAULT NULL COMMENT '用户入职时间',
  `user_role` varchar(32) NULL DEFAULT NULL COMMENT '用户角色',
  `user_departuredate` varchar(32) NULL DEFAULT NULL COMMENT '用户离职时间',
  `user_status` varchar(1) NULL DEFAULT NULL COMMENT '用户状态（0:正常,1:禁用）',
  PRIMARY KEY (`user_id`) USING BTREE
);

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '吴佳', '', 'wujia@163.com', '2024-01-05', 'ADMIN', NULL, '0');
INSERT INTO `user` VALUES (2, '张三', '', 'zhangsan@qq.cn', '2024-01-07', 'USER', NULL, '0');
INSERT INTO `user` VALUES (3, '王五', '', 'wangwu@qq.cn', '2024-01-07', 'USER', '2024-06-05', '1');
