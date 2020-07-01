-- MySQL dump 10.13  Distrib 5.7.18, for Win32 (AMD64)
--
-- Host: localhost    Database: personnel
-- ------------------------------------------------------
-- Server version	5.7.18-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `department_name` varchar(20) NOT NULL,
  `department_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`department_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES ('市场部','005'),('总办','001'),('技术部','003'),('财务部','002'),('运维部','004');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job`
--

DROP TABLE IF EXISTS `job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job` (
  `job_name` varchar(10) NOT NULL,
  `job_id` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`job_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
INSERT INTO `job` VALUES ('技术工程师','005'),('技术总监','001'),('研发工程师','003'),('财务总监','006'),('运维工程师','004'),('运营总监','002');
/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `op`
--

DROP TABLE IF EXISTS `op`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `op` (
  `opID` varchar(20) NOT NULL COMMENT '账号',
  `opPassword` varchar(20) NOT NULL,
  `opName` varchar(20) DEFAULT NULL COMMENT '操作员',
  `opPhone` varchar(12) DEFAULT NULL COMMENT '联系电话',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `permission` varchar(10) NOT NULL COMMENT '权限',
  PRIMARY KEY (`opID`),
  KEY `opName` (`opName`,`opID`),
  KEY `opID` (`opID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `op`
--

LOCK TABLES `op` WRITE;
/*!40000 ALTER TABLE `op` DISABLE KEYS */;
INSERT INTO `op` VALUES ('12345','12345','张三','15675013949','略','1');
/*!40000 ALTER TABLE `op` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operate`
--

DROP TABLE IF EXISTS `operate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operate` (
  `no` int(10) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `op` varchar(20) NOT NULL COMMENT '操作员',
  `addDate` date DEFAULT NULL COMMENT '添加日期',
  `Event` varchar(50) DEFAULT NULL COMMENT '事件内容',
  PRIMARY KEY (`no`),
  KEY `operate` (`op`),
  CONSTRAINT `operate` FOREIGN KEY (`op`) REFERENCES `op` (`opID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operate`
--

LOCK TABLES `operate` WRITE;
/*!40000 ALTER TABLE `operate` DISABLE KEYS */;
/*!40000 ALTER TABLE `operate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personneladjustsalary`
--

DROP TABLE IF EXISTS `personneladjustsalary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personneladjustsalary` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `personnelID` varchar(20) NOT NULL COMMENT '员工编号',
  `AdjustSalaryDate` date DEFAULT NULL COMMENT '调薪日期',
  `beforeAdjustSalary` int(10) DEFAULT NULL COMMENT '调前薪资',
  `afterAdjustSalary` int(10) DEFAULT NULL COMMENT '调后薪资',
  `AdjustSalaryReason` varchar(10) DEFAULT NULL COMMENT '调薪原因',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `op` varchar(20) NOT NULL COMMENT '操作员id',
  PRIMARY KEY (`no`),
  KEY `personneladjustsalary` (`op`),
  CONSTRAINT `personneladjustsalary` FOREIGN KEY (`op`) REFERENCES `op` (`opID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personneladjustsalary`
--

LOCK TABLES `personneladjustsalary` WRITE;
/*!40000 ALTER TABLE `personneladjustsalary` DISABLE KEYS */;
INSERT INTO `personneladjustsalary` VALUES (3,'0003','2020-05-26',9000,10000,'略','略','12345'),(4,'2222','2020-05-29',5000,6000,'略','略','12345');
/*!40000 ALTER TABLE `personneladjustsalary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personnelencourageorpunish`
--

DROP TABLE IF EXISTS `personnelencourageorpunish`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personnelencourageorpunish` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `personnelID` varchar(20) NOT NULL COMMENT '员工编号',
  `encOrpunishDate` date DEFAULT NULL COMMENT '奖罚日期',
  `encOrpunishReason` varchar(10) DEFAULT NULL COMMENT '奖罚原因',
  `encOrpunishPoint` int(10) DEFAULT NULL COMMENT '奖罚分',
  `type` varchar(10) NOT NULL,
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `op` varchar(20) NOT NULL,
  PRIMARY KEY (`no`),
  KEY `personnelencourageorpunish` (`op`),
  CONSTRAINT `personnelencourageorpunish` FOREIGN KEY (`op`) REFERENCES `op` (`opID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personnelencourageorpunish`
--

LOCK TABLES `personnelencourageorpunish` WRITE;
/*!40000 ALTER TABLE `personnelencourageorpunish` DISABLE KEYS */;
INSERT INTO `personnelencourageorpunish` VALUES (1,'0003','2020-05-26','略',1,'001','略','12345'),(2,'0003','2020-05-26','略',2,'001','略','12345'),(3,'0003','2020-05-26','略',1,'001','略','12345'),(4,'2222',NULL,'略',2,'002','略','12345'),(5,'0003','2020-05-04','略',10,'001','略','12345');
/*!40000 ALTER TABLE `personnelencourageorpunish` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personnelinfo`
--

DROP TABLE IF EXISTS `personnelinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personnelinfo` (
  `personnelID` varchar(20) NOT NULL COMMENT '工号',
  `personnelName` varchar(10) NOT NULL COMMENT '员工姓名',
  `personnelSex` varchar(4) DEFAULT NULL COMMENT '员工性别',
  `ID` varchar(20) NOT NULL COMMENT '身份证号',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `E_mail` varchar(20) DEFAULT NULL COMMENT '电子邮箱',
  `phone` varchar(12) DEFAULT NULL COMMENT '联系电话',
  `department` varchar(20) NOT NULL COMMENT '部门',
  `job` varchar(10) NOT NULL COMMENT '职位',
  `basisSalary` int(10) NOT NULL,
  `beginWorkDate` date NOT NULL COMMENT '入职日期',
  `workState` varchar(8) NOT NULL COMMENT '在职状态',
  `notWorkDate` date DEFAULT NULL COMMENT '离职日期',
  `workAge` int(4) DEFAULT NULL COMMENT '工龄',
  PRIMARY KEY (`personnelID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personnelinfo`
--

LOCK TABLES `personnelinfo` WRITE;
/*!40000 ALTER TABLE `personnelinfo` DISABLE KEYS */;
INSERT INTO `personnelinfo` VALUES ('0001','孙倩','女','430682199774151581','2020-05-04','1213456@qq.com','15831222195','技术部','技术总监',5000,'2020-05-12','在职',NULL,1),('0002','孙周','男','430682199774151741','2020-05-04','1213956@qq.com','15831222195','财务部','技术总监',6000,'2020-05-12','在职',NULL,1),('0003','赵六','男','430682199709151111','2020-05-27','123456@qq.com','15831222195','市场部','技术工程师',10000,'2020-05-12','在职',NULL,1),('0004','李四','男','478942199774151741','2020-05-04','1213956@qq.com','15831222195','运维部','运维工程师',9000,'2020-05-12','在职',NULL,1),('0005','王五','男','478942199774151741','2020-05-04','1213956@qq.com','15831222195','市场部','研发工程师',8000,'2020-05-12','在职',NULL,1),('0006','钱七','男','430682199709158218','2020-05-04','222596390@qq.com','15831220192','市场部','研发工程师',8000,'2020-05-12','在职',NULL,1),('0007','李森','男','430682199709158218','2020-05-04','222596390@qq.com','15831220192','技术部','技术工程师',8000,'2020-05-12','在职',NULL,1),('0008','李敖','男','430682199709158748','2020-05-04','2225963903@qq.com','15831220192','技术部','技术总监',9000,'2020-05-12','在职',NULL,1),('0009','陈寿','男','430682198909158748','2020-05-04','2225963903@qq.com','15831220192','运维部','技术总监',8000,'2020-05-12','在职',NULL,1),('0010','廖倩','女','430682198909158748','2020-05-04','2225963903@qq.com','15831220192','运维部','财务总监',7000,'2020-05-12','在职',NULL,1),('0011','李正','男','430682199009218981','2020-05-04','2225963903@qq.com','15831220192','运维部','运维工程师',7000,'2020-05-12','在职',NULL,1),('2222','2222','男','430682199709158218','2020-05-29','2225963903@qq.com','15831220192','技术部','财务总监',6000,'2020-05-29','在职',NULL,0),('3333','3333','男','430682199709158218','2001-05-01','2259665@qq.com','15831220192','总办','技术总监',0,'2020-05-29','离职','2020-05-29',0);
/*!40000 ALTER TABLE `personnelinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personnelremove`
--

DROP TABLE IF EXISTS `personnelremove`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personnelremove` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `personnelID` varchar(20) NOT NULL COMMENT '员工编号',
  `removeDate` date DEFAULT NULL COMMENT '调动日期',
  `afterRemoveDepartment` varchar(20) NOT NULL COMMENT '调后部门',
  `afterRemoveJob` varchar(10) NOT NULL COMMENT '调后职位',
  `removeReason` varchar(10) DEFAULT NULL COMMENT '调动原因',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `op` varchar(20) NOT NULL,
  PRIMARY KEY (`no`),
  KEY `personnelremove` (`op`),
  CONSTRAINT `personnelremove` FOREIGN KEY (`op`) REFERENCES `op` (`opID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personnelremove`
--

LOCK TABLES `personnelremove` WRITE;
/*!40000 ALTER TABLE `personnelremove` DISABLE KEYS */;
INSERT INTO `personnelremove` VALUES (2,'0003','2020-05-26','运维部','技术总监','略','略','12345'),(5,'2222','2020-05-29','技术部','财务总监','略','略','12345');
/*!40000 ALTER TABLE `personnelremove` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salary`
--

DROP TABLE IF EXISTS `salary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salary` (
  `personnelID` varchar(20) NOT NULL COMMENT '工号',
  `personnelName` varchar(10) NOT NULL COMMENT '员工姓名',
  `department` varchar(20) NOT NULL COMMENT '部门',
  `bonus` int(10) DEFAULT NULL COMMENT '奖金',
  `basisSalary` int(10) DEFAULT NULL COMMENT '基本工资',
  `trafficSalary` int(10) DEFAULT NULL COMMENT '交通补助',
  `allSalary` int(10) DEFAULT NULL COMMENT '应发工资',
  PRIMARY KEY (`personnelID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salary`
--

LOCK TABLES `salary` WRITE;
/*!40000 ALTER TABLE `salary` DISABLE KEYS */;
INSERT INTO `salary` VALUES ('0001','孙倩','技术部',0,5000,500,5500),('0002','孙周','财务部',0,6000,500,6500),('0003','赵六','总办',0,10000,500,10500),('0004','李四','运维部',0,9000,500,9500),('0005','王五','市场部',0,8000,500,8500),('0006','钱七','市场部',0,8000,500,8500),('0007','李森','技术部',0,8000,500,8500),('0008','李敖','技术部',0,9000,500,9500),('0009','陈寿','运维部',0,8000,500,8500),('0010','廖倩','运维部',0,7000,500,7500),('0011','李正','运维部',0,7000,500,7500),('2222','2222','总办',0,6000,500,6500),('3333','3333','总办',0,6000,500,6500);
/*!40000 ALTER TABLE `salary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys`
--

DROP TABLE IF EXISTS `sys`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys` (
  `sysNo` varchar(20) NOT NULL,
  `sys_Name` varchar(10) NOT NULL,
  `sys_Password` varchar(20) NOT NULL,
  PRIMARY KEY (`sysNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys`
--

LOCK TABLES `sys` WRITE;
/*!40000 ALTER TABLE `sys` DISABLE KEYS */;
INSERT INTO `sys` VALUES ('123456','李森林','123456');
/*!40000 ALTER TABLE `sys` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type`
--

DROP TABLE IF EXISTS `type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `type` (
  `type_ID` varchar(10) NOT NULL,
  `type_Name` varchar(10) NOT NULL,
  PRIMARY KEY (`type_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type`
--

LOCK TABLES `type` WRITE;
/*!40000 ALTER TABLE `type` DISABLE KEYS */;
INSERT INTO `type` VALUES ('001','系统管理员'),('002','操作员');
/*!40000 ALTER TABLE `type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_ID` varchar(20) NOT NULL,
  `user_Password` varchar(20) NOT NULL,
  `user_name` varchar(10) NOT NULL,
  `user_type` varchar(10) NOT NULL,
  PRIMARY KEY (`user_ID`),
  KEY `user` (`user_type`),
  CONSTRAINT `user` FOREIGN KEY (`user_type`) REFERENCES `type` (`type_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('12345','12345','张三','002'),('123456','123456','李森林','001');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-29 20:12:12
