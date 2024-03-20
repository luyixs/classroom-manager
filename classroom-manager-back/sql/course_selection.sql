/*
 Navicat Premium Data Transfer

 Source Server         : .
 Source Server Type    : MySQL
 Source Server Version : 80031 (8.0.31)
 Source Host           : localhost:3306
 Source Schema         : course_selection

 Target Server Type    : MySQL
 Target Server Version : 80031 (8.0.31)
 File Encoding         : 65001

 Date: 21/04/2023 15:22:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_course
-- ----------------------------
DROP TABLE IF EXISTS `t_course`;
CREATE TABLE `t_course`  (
  `cid` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `c_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `teacher_id` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `credit` tinyint NULL DEFAULT NULL,
  `limit` tinyint NULL DEFAULT NULL,
  `classroom` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `time_range` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `type` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `academy` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `c_intro` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_course
-- ----------------------------
INSERT INTO `t_course` VALUES ('thPyx9', 'Java', '20190101', 4, 100, 'A01-0101', '周一_第一大节', '必修', '计算机学院', 'Java是一门面向对象的编程语言');
INSERT INTO `t_course` VALUES ('Ha734J', 'Spring Boot', '20190101', 4, 100, 'A01-0102', '周一_第二大节', '必修', '计算机学院', 'Spring Boot 是Java框架');
INSERT INTO `t_course` VALUES ('WD_HLe', 'JavaScript', '20190102', 3, 70, 'A01-0202', '周一_第三大节', '必修', '计算机学院', 'JavaScript是一门前端语言');
INSERT INTO `t_course` VALUES ('knMxC9', '软件项目管理', '20190103', 2, 120, 'A01-0201', '周一_第四大节', '必修', '计算机学院', '软件项目管理');
INSERT INTO `t_course` VALUES ('DKcJI5', 'Web前端技术', '20180101', 2, 100, 'A01-0103', '周一_第一大节', '必修', '计算机学院', '该课程讲解Web前端技术');
INSERT INTO `t_course` VALUES ('Hqe-9F', '数据库原理与应用', '2', 3, 100, 'A01-0104', '周一_第一大节', '必修', '计算机学院', '本课程讲解数据库的原理和应用');

-- ----------------------------
-- Table structure for t_note
-- ----------------------------
DROP TABLE IF EXISTS `t_note`;
CREATE TABLE `t_note`  (
  `nid` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `uid` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `content` varchar(2000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `tag` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `edit_time` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `public` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`nid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_note
-- ----------------------------
INSERT INTO `t_note` VALUES ('004318', '1', '你好 Vue', 'Vue小试牛刀\n- 双向数据绑定\n- v-if\n- v-for\n- 监听\n- 事件通信\n- Vuex', '[\"Vue\"]', '2023/4/13 15:30:24', 'true');
INSERT INTO `t_note` VALUES ('060488', '2', 'MySQL SELECT', 'MySQL查询语句\n```mysql\nselect * from table\n```', '[\"MySQL\"]', '2023/4/11 22:29:57', 'true');
INSERT INTO `t_note` VALUES ('259713', '1', 'MovenEditor', '@[toc](目录)\n\nMarkdown 语法简介\n=============\n> [语法详解](http://commonmark.org/help/)\n\n## **粗体**\n```\n**粗体**\n__粗体__\n```\n## *斜体*\n```\n*斜体*\n_斜体_\n```\n## 标题\n```\n# 一级标题 #\n一级标题\n====\n## 二级标题 ##\n二级标题\n----\n### 三级标题 ###\n#### 四级标题 ####\n##### 五级标题 #####\n###### 六级标题 ######\n```\n## 分割线\n```\n***\n---\n```\n****\n## ^上^角~下~标\n```\n上角标 x^2^\n下角标 H~2~0\n```\n## ++下划线++ ~~中划线~~\n```\n++下划线++\n~~中划线~~\n```\n## ==标记==\n```\n==标记==\n```\n## 段落引用\n```\n> 一级\n>> 二级\n>>> 三级\n...\n```\n\n## 列表\n```\n有序列表\n1.\n2.\n3.\n...\n无序列表\n-\n-\n...\n```\n\n## 任务列表\n\n- [x] 已完成任务\n- [ ] 未完成任务\n\n```\n- [x] 已完成任务\n- [ ] 未完成任务\n```\n\n## 链接\n```\n[链接](www.baidu.com)\n![图片描述](http://www.image.com)\n```\n## 代码段落\n\\``` type\n\n代码段落\n\n\\```\n\n\\` 代码块 \\`\n\n```c++\nint main()\n{\n    printf(\"hello world!\");\n}\n```\n`code`\n## 表格(table)\n```\n| 标题1 | 标题2 | 标题3 |\n| :--  | :--: | ----: |\n| 左对齐 | 居中 | 右对齐 |\n| ---------------------- | ------------- | ----------------- |\n```\n| 标题1 | 标题2 | 标题3 |\n| :--  | :--: | ----: |\n| 左对齐 | 居中 | 右对齐 |\n| ---------------------- | ------------- | ----------------- |\n## 脚注(footnote)\n```\nhello[^hello]\n```\n\n见底部脚注[^hello]\n\n[^hello]: 一个注脚\n\n## 表情(emoji)\n[参考网站: https://www.webpagefx.com/tools/emoji-cheat-sheet/](https://www.webpagefx.com/tools/emoji-cheat-sheet/)\n```\n:laughing:\n:blush:\n:smiley:\n:)\n...\n```\n:laughing::blush::smiley::)\n\n## $\\KaTeX$公式\n\n我们可以渲染公式例如：$x_i + y_i = z_i$和$\\sum_{i=1}^n a_i=0$\n我们也可以单行渲染\n$$\\sum_{i=1}^n a_i=0$$\n具体可参照[katex文档](http://www.intmath.com/cg5/katex-mathjax-comparison.php)和[katex支持的函数](https://github.com/Khan/KaTeX/wiki/Function-Support-in-KaTeX)以及[latex文档](https://math.meta.stackexchange.com/questions/5020/mathjax-basic-tutorial-and-quick-reference)\n\n## 布局\n\n::: hljs-left\n`::: hljs-left`\n`居左`\n`:::`\n:::\n\n::: hljs-center\n`::: hljs-center`\n`居中`\n`:::`\n:::\n\n::: hljs-right\n`::: hljs-right`\n`居右`\n`:::`\n:::\n\n## 定义\n\n术语一\n\n:   定义一\n\n包含有*行内标记*的术语二\n\n:   定义二\n\n        {一些定义二的文字或代码}\n\n    定义二的第三段\n\n```\n术语一\n\n:   定义一\n\n包含有*行内标记*的术语二\n\n:   定义二\n\n        {一些定义二的文字或代码}\n\n    定义二的第三段\n\n```\n\n\n', '[\"MarkDown\"]', '2023/4/13 21:12:07', 'false');
INSERT INTO `t_note` VALUES ('440457', '1', 'Java入门', '## 字符串操作\n## 数组\n## 循环\n## 面向对象\n## 多线程\n## 网络编程\n', '[\"Java\"]', '2023/4/10 22:59:19', 'true');
INSERT INTO `t_note` VALUES ('459502', '1', '1', '11111222', '[\"test\"]', '2023/4/13 21:56:38', 'true');

-- ----------------------------
-- Table structure for t_sc
-- ----------------------------
DROP TABLE IF EXISTS `t_sc`;
CREATE TABLE `t_sc`  (
  `sc_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `uid` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `cid` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `score` tinyint(3) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  PRIMARY KEY (`sc_id`) USING BTREE,
  INDEX `uid`(`uid` ASC) USING BTREE,
  INDEX `cid`(`cid` ASC) USING BTREE,
  CONSTRAINT `uid` FOREIGN KEY (`uid`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sc
-- ----------------------------
INSERT INTO `t_sc` VALUES ('0jGDas', '2022010101', 'WD_HLe', 000);
INSERT INTO `t_sc` VALUES ('9-e4aB', '1', 'WD_HLe', 000);
INSERT INTO `t_sc` VALUES ('IzKi2K', '1', 'Hqe-9F', 000);
INSERT INTO `t_sc` VALUES ('nYowSy', '2022010102', 'WD_HLe', 000);
INSERT INTO `t_sc` VALUES ('oobn7L', '1', 'DKcJI5', 000);
INSERT INTO `t_sc` VALUES ('YAJQPZ', '11', 'Hqe-9F', 000);
INSERT INTO `t_sc` VALUES ('z-xV16', '2022010101', 'DKcJI5', 000);
INSERT INTO `t_sc` VALUES ('zI2cfu', '1', 'DKcJI5', 000);

-- ----------------------------
-- Table structure for t_signin
-- ----------------------------
DROP TABLE IF EXISTS `t_signin`;
CREATE TABLE `t_signin`  (
  `sicode` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `cid` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `uid` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `create_time` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `expire` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_signin
-- ----------------------------
INSERT INTO `t_signin` VALUES ('11', 'Hqe-9F', '2', '2023/3/27 23:21:33', '4');
INSERT INTO `t_signin` VALUES ('12', 'Hqe-9F', '2', '2023/3/27 23:59:59', '1');
INSERT INTO `t_signin` VALUES ('g5vHAW', 'Hqe-9F', '2', '2023/4/1 16:42:33', '10');
INSERT INTO `t_signin` VALUES ('g5vHAW', 'Hqe-9F', '2', '2023/4/1 16:43:14', '10');
INSERT INTO `t_signin` VALUES ('H04UFJ', 'Hqe-9F', '2', '2023/4/16 20:09:01', '5');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `pwd` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `class` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `academy` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `major` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `tel` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `role` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '1', '1', '2022级软件工程1班', '计算机学院', '软件工程', '1', '1', 'student');
INSERT INTO `t_user` VALUES ('11', '11', '11', '2022级软件工程1班', '计算机学院', '软件工程', '11', '11', 'student');
INSERT INTO `t_user` VALUES ('2', '2', '2', '2022级软件工程1班', '计算机学院', '软件工程', '2', '2@null.com', 'teacher');
INSERT INTO `t_user` VALUES ('20180101', '王武', 'ww', '2022级计算机科学与技术2班', '计算机学院', '计算机科学与技术', '150789451233', 'qwe@null.com', 'teacher');
INSERT INTO `t_user` VALUES ('20190101', '张山峰', 'zsf', '', '计算机学院', '', '11111111111', 'zsf@null.com', 'teacher');
INSERT INTO `t_user` VALUES ('20190102', '弗里斯比', 'flsb', '2022级计算机科学与技术1班', '计算机学院', '计算机科学与技术', '', '666612345', 'teacher');
INSERT INTO `t_user` VALUES ('20190103', '李英龙', 'lyr', '2022级软件工程3班', '计算机学院', '软件工程', '156456545555', 'as1d3@null.com', 'teacher');
INSERT INTO `t_user` VALUES ('2022010101', '张三', 'zs123', '2022级软件工程1班', '计算机学院', '软件工程', '11111111111', 'zs@null.com', 'student');
INSERT INTO `t_user` VALUES ('2022010102', '里斯', 'ls', '2022级软件工程1班', '计算机学院', '软件工程', '15687982467', 'lisi@null.com', 'student');

SET FOREIGN_KEY_CHECKS = 1;
