-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: BookWareHouse
-- ------------------------------------------------------
-- Server version	5.7.15

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
-- Table structure for table `Admin`
--

DROP TABLE IF EXISTS `Admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(75) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UserName_UNIQUE` (`UserName`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Admin`
--

LOCK TABLES `Admin` WRITE;
/*!40000 ALTER TABLE `Admin` DISABLE KEYS */;
INSERT INTO `Admin` VALUES (4,'mojadmin','mojadmin12','jaj');
/*!40000 ALTER TABLE `Admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Book`
--

DROP TABLE IF EXISTS `Book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `author` varchar(45) DEFAULT NULL,
  `yearOfPublication` int(11) DEFAULT NULL,
  `schoolClass` varchar(5) NOT NULL,
  `numberInStock` int(11) NOT NULL,
  `isUsed` tinyint(1) DEFAULT NULL,
  `comment` varchar(200) DEFAULT NULL,
  `numberOfUsed` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Book`
--

LOCK TABLES `Book` WRITE;
/*!40000 ALTER TABLE `Book` DISABLE KEYS */;
INSERT INTO `Book` VALUES (1,'title-1','author',2012,'7',3,1,NULL,35),(2,'Slovensky jazyk','Stur',1897,'8',-12,0,NULL,69),(17,'peknakniha','author',2012,'8',-19,0,NULL,61),(18,'titlenull','author',2012,'7',21,1,'comment',17),(19,'titlenull','author',2012,'7',21,0,NULL,17),(22,'titlenull','author',2012,'7',21,1,'comment',17),(25,'titlenull','author',2012,'7',21,1,'comment',17),(28,'titlenull','author',2012,'7',21,1,'comment',17),(31,'titlenull','author',2012,'7',21,1,'comment',17),(34,'titlenull','author',2012,'7',21,1,'comment',17),(39,'titlenull','author',2012,'7',21,1,'comment',17),(48,'title147','author1',2012,'7',21,1,'comment',17),(62,'titlenull','author',2012,'7',21,1,'comment',17),(63,'title','author',2012,'7',21,1,'comment',17),(64,'title-123','author-321',2012,'7',21,1,'comment',17),(65,'title-123','author-321',2012,'7',21,1,'comment',17),(66,'title-123','author-321',2012,'7',21,1,'comment',17),(67,'title-123','author-321',2012,'7',21,1,'comment',17),(68,'title-123','author-321',2012,'7',21,1,'comment',17),(71,'apostol','petofi',2013,'8',77,1,NULL,-53),(72,'toldi','arany',1998,'7',24,1,NULL,13),(77,'Christmas Carrol','Dickens',2013,'9',18,1,NULL,3),(93,'databazy','krajci',2013,'I. G',52,0,NULL,0),(94,'databazy','krajci',2013,'I. G',52,0,NULL,0),(95,'databazy','krajci',2013,'I. G',52,0,NULL,0),(96,'bezpecnost','sokol',2000,'4',11,1,NULL,6),(97,'leteszem a lantot','arany',1998,'7',63,1,NULL,-9),(98,'leteszem a lantot 2 ','arany',2012,'1',2,1,NULL,17),(99,'leda 2','adi',2007,'6',71,0,NULL,0);
/*!40000 ALTER TABLE `Book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BookEdit`
--

DROP TABLE IF EXISTS `BookEdit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BookEdit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idBook` int(11) NOT NULL,
  `nameOfAdmin` varchar(45) NOT NULL,
  `date` datetime NOT NULL,
  `numberBefore` int(11) NOT NULL,
  `numberAfter` int(11) NOT NULL,
  `comment` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BookEdit`
--

LOCK TABLES `BookEdit` WRITE;
/*!40000 ALTER TABLE `BookEdit` DISABLE KEYS */;
INSERT INTO `BookEdit` VALUES (9,1,'admin1','2007-01-01 00:00:00',21,0,'comment'),(10,1,'admin10','2017-11-21 22:05:57',17,21,'comment'),(13,1,'admin11','2017-11-21 22:08:15',17,21,'comment'),(17,65,'admin-12','2017-11-27 23:24:26',17,21,'comment'),(18,66,'admin-12','2017-11-27 23:27:46',17,21,'comment'),(19,67,'admin-12','2017-11-28 07:21:53',17,21,'comment'),(20,68,'admin-12','2017-11-28 07:25:05',17,21,'comment'),(24,17,'mojadmin','2017-12-02 16:40:58',21,25,''),(25,2,'mojadmin','2017-12-06 16:43:49',4,9,''),(26,2,'mojadmin','2017-12-06 18:24:07',4,30,''),(27,71,'mojadmin','2017-12-10 20:52:28',23,24,''),(28,72,'mojadmin','2017-12-10 20:58:07',3,19,'');
/*!40000 ALTER TABLE `BookEdit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BookInStock`
--

DROP TABLE IF EXISTS `BookInStock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BookInStock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idBook` int(11) NOT NULL,
  `idStock` int(11) NOT NULL,
  `number` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_BookInStock_Stock1_idx` (`idStock`),
  CONSTRAINT `fk_BookInStock_Stock1` FOREIGN KEY (`idStock`) REFERENCES `Stock` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BookInStock`
--

LOCK TABLES `BookInStock` WRITE;
/*!40000 ALTER TABLE `BookInStock` DISABLE KEYS */;
/*!40000 ALTER TABLE `BookInStock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BookLending`
--

DROP TABLE IF EXISTS `BookLending`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BookLending` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idTeacher` int(11) NOT NULL,
  `idBook` int(11) NOT NULL,
  `yearOfReturn` int(11) DEFAULT NULL,
  `lended` int(11) DEFAULT NULL,
  `returned` int(11) DEFAULT NULL,
  `lost` int(11) DEFAULT NULL,
  `comment` varchar(200) DEFAULT NULL,
  `approved` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_BookLending_Book1_idx` (`idBook`),
  KEY `fk_BookLending_Teacher1_idx` (`idTeacher`),
  CONSTRAINT `fk_BookLending_Book1` FOREIGN KEY (`idBook`) REFERENCES `Book` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_BookLending_Teacher1` FOREIGN KEY (`idTeacher`) REFERENCES `Teacher` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BookLending`
--

LOCK TABLES `BookLending` WRITE;
/*!40000 ALTER TABLE `BookLending` DISABLE KEYS */;
INSERT INTO `BookLending` VALUES (26,72,17,2018,4,0,0,'prosim si tuto knihu',1),(27,72,17,2018,20,0,0,'chcem tuto peknu knihu',1),(28,72,2,2018,21,0,0,'csuu',1),(29,72,2,2018,1,0,0,'nic',0),(30,72,2,2018,5,0,0,'funguje',0),(37,72,72,2018,17,0,0,'ziadam tieto knihy',0),(38,72,72,2018,5,0,0,NULL,0),(39,72,72,2018,6,0,0,NULL,0),(40,72,72,2018,13,0,0,NULL,0),(41,72,1,2018,5,0,0,NULL,0),(42,72,1,2018,6,0,0,NULL,0),(43,72,1,2018,3,0,0,NULL,0),(44,72,1,2018,1,0,0,NULL,0),(45,72,1,2018,1,0,0,NULL,0),(46,72,1,2018,1,0,0,NULL,1),(47,72,77,2018,1,1,0,NULL,1),(48,72,77,2018,1,0,0,NULL,1);
/*!40000 ALTER TABLE `BookLending` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LostBook`
--

DROP TABLE IF EXISTS `LostBook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LostBook` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idBook` int(11) NOT NULL,
  `title` varchar(45) NOT NULL,
  `author` varchar(45) DEFAULT NULL,
  `yearOfPublication` int(11) DEFAULT NULL,
  `schoolClass` varchar(5) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `idTeacher` int(11) DEFAULT NULL,
  `nameOfTeacher` varchar(45) DEFAULT NULL,
  `surnameOfTeacher` varchar(45) DEFAULT NULL,
  `idAdmin` int(11) DEFAULT NULL,
  `date` datetime NOT NULL,
  `comment` varchar(200) DEFAULT NULL,
  `usernameOfAdmin` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LostBook`
--

LOCK TABLES `LostBook` WRITE;
/*!40000 ALTER TABLE `LostBook` DISABLE KEYS */;
INSERT INTO `LostBook` VALUES (1,1,'nazov','aut1',2012,'8',4,1,'Andrea','Ferencei',1,'2017-09-19 00:00:00','hlupe deti stratili','admin1'),(4,0,'title','author',2004,'3G',31,1,'nameOfTeacher','surnameOfTeacher',1,'2017-11-22 11:04:38','comment','userNameOfAdmin'),(5,96,'bezpecnost','sokol',2000,'4',0,100,'pista','miko',4,'2017-12-11 00:00:36','strata','mojadmin'),(6,97,'leteszem a lantot','arany',1998,'7',0,100,'pista','miko',4,'2017-12-11 00:08:22','strata','mojadmin'),(7,71,'apostol','petofi',2013,'8',3,100,'pista','miko',4,'2017-12-11 19:18:54','strata','mojadmin'),(8,72,'toldi','arany',1998,'7',2,100,'pista','miko',4,'2017-12-11 19:27:11','strata','mojadmin');
/*!40000 ALTER TABLE `LostBook` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OP`
--

DROP TABLE IF EXISTS `OP`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OP` (
  `idOP` int(11) NOT NULL,
  `cisloOP` int(11) DEFAULT NULL,
  `idOs` int(11) NOT NULL,
  PRIMARY KEY (`idOP`),
  KEY `idOs` (`idOs`),
  CONSTRAINT `op_ibfk_1` FOREIGN KEY (`idOs`) REFERENCES `Osoba` (`idOs`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OP`
--

LOCK TABLES `OP` WRITE;
/*!40000 ALTER TABLE `OP` DISABLE KEYS */;
/*!40000 ALTER TABLE `OP` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Stock`
--

DROP TABLE IF EXISTS `Stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) DEFAULT NULL,
  `comment` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Stock`
--

LOCK TABLES `Stock` WRITE;
/*!40000 ALTER TABLE `Stock` DISABLE KEYS */;
INSERT INTO `Stock` VALUES (1,'205','default stock');
/*!40000 ALTER TABLE `Stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SuperAdmin`
--

DROP TABLE IF EXISTS `SuperAdmin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SuperAdmin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(45) NOT NULL,
  `password` varchar(75) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userName_UNIQUE` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SuperAdmin`
--

LOCK TABLES `SuperAdmin` WRITE;
/*!40000 ALTER TABLE `SuperAdmin` DISABLE KEYS */;
INSERT INTO `SuperAdmin` VALUES (4,'newAdmin677.0','password807.0'),(9,'newAdmin4','password'),(10,'newAdmin9','password');
/*!40000 ALTER TABLE `SuperAdmin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Teacher`
--

DROP TABLE IF EXISTS `Teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Surname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(75) NOT NULL,
  `numberOfStudentsInClass` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Teacher`
--

LOCK TABLES `Teacher` WRITE;
/*!40000 ALTER TABLE `Teacher` DISABLE KEYS */;
INSERT INTO `Teacher` VALUES (2,'name85632.0','surname85632.0','teacher85632.0@gmail.com','0B14D501A594442A01C6859541BCB3E8164D183D32937B851835442F69D5C94E',75),(3,'name2','surname2','email','password',13),(7,'name6','surname6','email6@gmail.com','password',80),(8,'name7','surname7','email7@gmail.com','password',50),(9,'name8','surname8','email8@gmail.com','password',69),(10,'name9','surname9','email9@gmail.com','password',92),(11,'name10','surname10','email10@gmail.com','password',68),(14,'name11','surname11','email11@gmail.com','password',70),(15,'name14','surname14','email14@gmail.com','password',76),(49,'tomi','kekenak','kekenak.t@gmail.com','985E819F2BF85A8C2F0FE5641DDC22AFB4EC14F6D8DBB7CD1973D2186676EFDD',0),(50,'ahs','asd','jsha','688787D8FF144C502C7F5CFFAAFE2CC588D86079F9DE88304C26B0CB99CE91C6',0),(51,'','sha','sja','961B6DD3EDE3CB8ECBAACBD68DE040CD78EB2ED5889130CCEB4C49268EA4D506',0),(52,'ahoj','njdkedjk','shdej','F4BF9F7FCBEDABA0392F108C59D8F4A38B3838EFB64877380171B54475C2ADE8',0),(56,'','ahs','sjjn','EA325D761F98C6B73320E442B67F2A3574D9924716D788DDC0DBBDCACA853FE7',0),(63,'ahoj','sja','ksn','A1FCE4363854FF888CFF4B8E7875D600C2682390412A8CF79B37D0B11148B0FA',0),(64,'ahsi','bsjd','bwb','189F40034BE7A199F1FA9891668EE3AB6049F82D38C68BE70F596EAB2E1857B7',0),(65,'ahojb','heud','hebs','85ACC813CA3F42681DE514657891E97B6EBD7AA4BD2673D2B5F82694B40C6C8F',0),(66,'as','asw','qwe','3AC1ECBD2220C6757EB9F289FEE88C4068C6791C5B0AFFEF7644E77ADFC97B45',0),(67,'swd','ytr','bfc','7817BB812E82168BD48FE1EA6783078D42BE37E8DB9BDAAFDAC5C45804ACA64F',0),(68,'she','smw','fie','043A718774C572BD8A25ADBEB1BFCD5C0256AE11CECF9F9C3F925D0E52BEAF89',0),(69,'ja','dna','dns','8254C329A92850F6D539DD376F4816EE2764517DA5E0235514AF433164480D7A',0),(70,'sas','soa','snds','96166E27AF5A3C431FFA7247AD4E1B2D488008311887CEDC655121565721CBCE',0),(71,'abc1','abc1','lkj','189F40034BE7A199F1FA9891668EE3AB6049F82D38C68BE70F596EAB2E1857B7',0),(72,'ahoj','ahoj','ahoj','java',0),(100,'pista','miko','mikop@g.com','jaj',0);
/*!40000 ALTER TABLE `Teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `maz`
--

DROP TABLE IF EXISTS `maz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `maz` (
  `meno` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `maz`
--

LOCK TABLES `maz` WRITE;
/*!40000 ALTER TABLE `maz` DISABLE KEYS */;
INSERT INTO `maz` VALUES ('ahoj');
/*!40000 ALTER TABLE `maz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `osoba`
--

DROP TABLE IF EXISTS `osoba`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `osoba` (
  `idOs` int(11) NOT NULL,
  `meno` varchar(20) DEFAULT NULL,
  `rokNarodenia` int(11) DEFAULT NULL,
  PRIMARY KEY (`idOs`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `osoba`
--

LOCK TABLES `osoba` WRITE;
/*!40000 ALTER TABLE `osoba` DISABLE KEYS */;
INSERT INTO `osoba` VALUES (1,'F',1993),(2,'P',1987),(3,'K',1976);
/*!40000 ALTER TABLE `osoba` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-11 19:33:51
