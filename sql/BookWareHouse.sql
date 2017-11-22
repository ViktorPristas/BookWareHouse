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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Admin`
--

LOCK TABLES `Admin` WRITE;
/*!40000 ALTER TABLE `Admin` DISABLE KEYS */;
INSERT INTO `Admin` VALUES (3,'newAdmin87255.0','email','password9253.0');
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
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Book`
--

LOCK TABLES `Book` WRITE;
/*!40000 ALTER TABLE `Book` DISABLE KEYS */;
INSERT INTO `Book` VALUES (1,'title-1','author',2012,'7',21,0,'comment',17),(2,'Slovensky jazyk','Stur',1897,'8',26,0,'nic',0),(17,'titlenull','author',2012,'7',21,1,'comment',17),(18,'titlenull','author',2012,'7',21,1,'comment',17),(19,'titlenull','author',2012,'7',21,1,'comment',17),(22,'titlenull','author',2012,'7',21,1,'comment',17),(25,'titlenull','author',2012,'7',21,1,'comment',17),(28,'titlenull','author',2012,'7',21,1,'comment',17),(31,'titlenull','author',2012,'7',21,1,'comment',17),(34,'titlenull','author',2012,'7',21,1,'comment',17);
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BookEdit`
--

LOCK TABLES `BookEdit` WRITE;
/*!40000 ALTER TABLE `BookEdit` DISABLE KEYS */;
INSERT INTO `BookEdit` VALUES (9,1,'admin1','2007-01-01 00:00:00',21,0,'comment'),(10,1,'admin10','2017-11-21 22:05:57',17,21,'comment'),(13,1,'admin11','2017-11-21 22:08:15',17,21,'comment');
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
  UNIQUE KEY `idTeacher_UNIQUE` (`idTeacher`),
  UNIQUE KEY `idBook_UNIQUE` (`idBook`),
  KEY `fk_BookLending_Teacher1_idx` (`idTeacher`),
  CONSTRAINT `fk_BookLending_Book1` FOREIGN KEY (`idBook`) REFERENCES `Book` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_BookLending_Teacher1` FOREIGN KEY (`idTeacher`) REFERENCES `Teacher` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BookLending`
--

LOCK TABLES `BookLending` WRITE;
/*!40000 ALTER TABLE `BookLending` DISABLE KEYS */;
INSERT INTO `BookLending` VALUES (3,2,1,2018,21,0,0,'comment',0);
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LostBook`
--

LOCK TABLES `LostBook` WRITE;
/*!40000 ALTER TABLE `LostBook` DISABLE KEYS */;
INSERT INTO `LostBook` VALUES (1,1,'nazov','aut1',2012,'8',4,1,'Andrea','Ferencei',1,'2017-09-19 00:00:00','hlupe deti stratili','admin1'),(4,0,'title','author',2004,'3G',31,1,'nameOfTeacher','surnameOfTeacher',1,'2017-11-22 11:04:38','comment','userNameOfAdmin');
/*!40000 ALTER TABLE `LostBook` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Teacher`
--

LOCK TABLES `Teacher` WRITE;
/*!40000 ALTER TABLE `Teacher` DISABLE KEYS */;
INSERT INTO `Teacher` VALUES (2,'name84282.0','surname84282.0','teacher84282.0@gmail.com','password',94),(3,'name2','surname2','email','password',13),(5,'name3','surname3','email3@gmail.com','password',59),(6,'name5','surname5','email5@gmail.com','password',63),(7,'name6','surname6','email6@gmail.com','password',80),(8,'name7','surname7','email7@gmail.com','password',50),(9,'name8','surname8','email8@gmail.com','password',69),(10,'name9','surname9','email9@gmail.com','password',92),(11,'name10','surname10','email10@gmail.com','password',68),(14,'name11','surname11','email11@gmail.com','password',70),(15,'name14','surname14','email14@gmail.com','password',76),(18,'name61044.0','surname61044.0','teacher61044.0@gmail.com','password',7);
/*!40000 ALTER TABLE `Teacher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-22 20:33:45
