-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: smart-sso
-- ------------------------------------------------------
-- Server version	9.2.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `sys_app`
--

DROP TABLE IF EXISTS `sys_app`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_app` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(50) NOT NULL COMMENT '编码',
  `name` varchar(128) NOT NULL COMMENT '名称',
  `sort` int NOT NULL COMMENT '排序',
  `is_enable` int NOT NULL COMMENT '是否启用',
  `client_id` varchar(20) NOT NULL COMMENT '客户端ID',
  `client_secret` varchar(128) NOT NULL COMMENT '客户端密钥',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `unique_code` (`code`),
  UNIQUE KEY `unique_client_id` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8mb3 COMMENT='应用表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_app`
--

LOCK TABLES `sys_app` WRITE;
/*!40000 ALTER TABLE `sys_app` DISABLE KEYS */;
INSERT INTO `sys_app` VALUES (1,'smart-sso-server','SSO-Server',20,1,'1000','rokY9BdKh5bHiX/zL26qOg==','2015-06-02 11:31:44','2015-06-02 11:31:44'),(81,'smart-sso-demo','Demo-App',15,1,'1001','kpA1y7k1uyrcoGhrKvA1Ag==','2015-11-08 17:16:39','2024-07-16 11:29:10'),(82,'smart-sso-demo-h5','Demo-App-H5',10,1,'1002','3vjPTgn+9XwV+Q6PRUA5oQ==','2015-11-08 17:16:39','2015-11-08 17:16:39');
/*!40000 ALTER TABLE `sys_app` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_office`
--

DROP TABLE IF EXISTS `sys_office`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_office` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `parent_id` bigint DEFAULT NULL COMMENT '父ID',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `sort` int NOT NULL COMMENT '排序',
  `is_enable` int NOT NULL COMMENT '是否启用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3 COMMENT='机构';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_office`
--

LOCK TABLES `sys_office` WRITE;
/*!40000 ALTER TABLE `sys_office` DISABLE KEYS */;
INSERT INTO `sys_office` VALUES (8,NULL,'AT Energy',1,1,'2025-10-20 15:29:19','2025-10-20 15:29:19'),(9,8,'NCPT',1,1,'2025-10-20 15:29:32','2025-10-20 15:29:32');
/*!40000 ALTER TABLE `sys_office` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_permission`
--

DROP TABLE IF EXISTS `sys_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_permission` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `app_id` bigint NOT NULL COMMENT '应用ID',
  `parent_id` bigint DEFAULT NULL COMMENT '父ID',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `url` varchar(255) NOT NULL COMMENT '权限URL',
  `sort` int NOT NULL COMMENT '排序',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `is_menu` int NOT NULL COMMENT '是否菜单',
  `is_enable` int NOT NULL COMMENT '是否启用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb3 COMMENT='权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_permission`
--

LOCK TABLES `sys_permission` WRITE;
/*!40000 ALTER TABLE `sys_permission` DISABLE KEYS */;
INSERT INTO `sys_permission` VALUES (2,1,NULL,'Application Management','/admin/app',59,'fa fa-th-large',1,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(3,1,NULL,'User Management','/admin/user',79,'fa-user',1,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(4,1,NULL,'Role Management','/admin/role',69,'fa-briefcase',1,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(5,1,NULL,'Permission Management','/admin/permission',29,'fa-key',1,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(6,1,2,'Application Add','/admin/app/edit',4,'fa-plus-circle blue',0,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(7,1,2,'Application Enable/Disable','/admin/app/enable',3,'fa-lock orange',0,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(9,1,2,'Application Delete','/admin/app/delete',1,'fa-trash-o red',0,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(10,1,3,'User Add','/admin/user/edit',6,'fa-plus-circle blue',0,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(11,1,3,'User Enable/Disable','/admin/user/enable',5,'fa-lock orange',0,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(13,1,3,'User Delete','/admin/user/delete',3,'fa-trash-o red',0,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(14,1,3,'Reset Password','/admin/user/resetPassword',2,'fa-key grey',0,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(16,1,4,'Role Add','/admin/role/edit',5,'fa-plus-circle blue',0,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(17,1,4,'Role Enable/Disable','/admin/role/enable',4,'fa-lock orange',0,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(19,1,4,'Role Delete','/admin/role/delete',2,'fa-trash-o red',0,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(20,1,4,'Role Authorization','/admin/role/allocate',1,'fa-cog grey',0,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(22,1,2,'Application List','/admin/app/list',5,'',0,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(23,1,3,'User List','/admin/user/list',7,'',0,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(24,1,4,'Role List','/admin/role/list',6,'',0,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(25,1,5,'Permission Tree List','/admin/permission/nodes',1,'',0,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(26,1,2,'Application Save','/admin/app/save',1,'',0,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(27,1,3,'User Save','/admin/user/save',1,'',0,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(28,1,4,'Role Save','/admin/role/save',1,'',0,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(29,1,5,'Permission Save','/admin/permission/save',1,'',0,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(30,1,5,'Permission Delete','/admin/permission/delete',1,'',0,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(33,81,NULL,'Menu 1','/admin/menu1',100,'fa-user',1,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(35,81,33,'Menu 1 Add','/admin/menu1/edit',1,'',0,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(36,81,33,'Menu 1 Delete','/admin/menu1/delete',1,'',0,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(39,1,NULL,'Navigation Bar','/admin/admin/menu',99,'',0,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(41,1,NULL,'Personal Center','/admin/profile',89,'fa fa-desktop',1,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(42,1,41,'Change Password','/admin/profile/savePassword',1,'',0,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(44,82,NULL,'Channel Management','/admin/channel',100,'fa fa-th-large',1,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(47,82,NULL,'Footer Menu Management','/admin/menu',110,'fa fa-list-alt',1,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(48,82,NULL,'Article Management','/admin/article',90,'fa fa-file-text',1,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(49,82,NULL,'Product Management','/admin/product',70,'fa fa-file-powerpoint-o',1,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(50,82,NULL,'Product Specifications','/admin/spec',75,'fa fa-cubes',1,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(55,82,NULL,'Homepage Slideshow Management','/admin/slide',120,'fa fa-sliders',1,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(56,82,NULL,'Footer Menu Configuration','/admin/channelMenu/edit',105,'fa fa-cog',1,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(59,81,NULL,'Menu 2','/admin/menu2',90,'',1,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(60,1,NULL,'Organization Management','/admin/office',80,'fa-cogs',1,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(61,1,60,'Organization List','/admin/office/list',5,'',0,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(62,1,60,'Organization Add','/admin/app/edit',4,'fa-plus-circle blue',0,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(63,1,60,'Organization Enable/Disable','/admin/office/enable',3,'fa-lock orange',0,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(65,1,60,'Organization Delete','/admin/office/delete',1,'fa-trash-o red',0,1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(66,1,NULL,'Login User Management','/admin/login-user',1,'fa-users',1,1,'2025-03-28 15:05:31','2025-03-28 15:05:31'),(67,1,66,'Login User List','/admin/login-user/list',5,'',0,1,'2025-03-31 15:52:25','2025-03-31 15:52:25'),(68,1,66,'Login User Logout','/admin/login-user/logout',2,'',0,1,'2025-03-31 15:52:55','2025-03-31 15:53:33');
/*!40000 ALTER TABLE `sys_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '名称',
  `sort` int NOT NULL COMMENT '排序',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `is_enable` int NOT NULL COMMENT '是否启用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'admin',999,'admin',1,'2015-06-02 11:31:44','2015-06-02 11:31:44'),(2,'User',1,'',1,'2025-10-15 16:00:04','2025-10-15 16:00:04');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_permission`
--

DROP TABLE IF EXISTS `sys_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_permission` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `permission_id` bigint NOT NULL COMMENT '权限ID',
  `app_id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=454 DEFAULT CHARSET=utf8mb3 COMMENT='角色权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_permission`
--

LOCK TABLES `sys_role_permission` WRITE;
/*!40000 ALTER TABLE `sys_role_permission` DISABLE KEYS */;
INSERT INTO `sys_role_permission` VALUES (345,1,33,81),(346,1,35,81),(347,1,36,81),(348,1,59,81),(349,1,55,82),(350,1,47,82),(351,1,56,82),(352,1,44,82),(353,1,48,82),(354,1,50,82),(355,1,49,82),(419,1,39,1),(420,1,41,1),(421,1,42,1),(422,1,60,1),(423,1,61,1),(424,1,62,1),(425,1,63,1),(426,1,65,1),(427,1,3,1),(428,1,23,1),(429,1,10,1),(430,1,11,1),(431,1,13,1),(432,1,14,1),(433,1,27,1),(434,1,4,1),(435,1,24,1),(436,1,16,1),(437,1,17,1),(438,1,19,1),(439,1,20,1),(440,1,28,1),(441,1,2,1),(442,1,22,1),(443,1,6,1),(444,1,7,1),(445,1,9,1),(446,1,26,1),(447,1,5,1),(448,1,25,1),(449,1,29,1),(450,1,30,1),(451,1,66,1),(452,1,67,1),(453,1,68,1);
/*!40000 ALTER TABLE `sys_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `office_id` bigint NOT NULL COMMENT '机构ID',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `account` varchar(50) NOT NULL COMMENT '登录名',
  `password` varchar(100) NOT NULL COMMENT '密码(加密)',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `login_count` int NOT NULL COMMENT '登录总次数',
  `is_enable` int NOT NULL COMMENT '是否启用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (2,3,'Joe','admin','26524bdf4ea266f131566a89e8f4972c','2025-10-21 17:56:54',135,1,'2015-06-02 11:31:56','2025-10-21 17:56:54'),(3,9,'nguyễn xuân huy','huynx','26524bdf4ea266f131566a89e8f4972c','2025-10-16 12:59:30',0,1,'2025-10-15 15:58:49','2025-10-20 15:30:03');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL COMMENT '用户ID ',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb3 COMMENT='用户角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES (33,2,1),(34,3,1);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'smart-sso'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-10-22  8:54:59
