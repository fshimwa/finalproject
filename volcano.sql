-- MySQL dump 10.13  Distrib 5.7.13, for Linux (x86_64)
--
-- Host: localhost    Database: volcano
-- ------------------------------------------------------
-- Server version	5.7.13-0ubuntu0.16.04.2

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
-- Table structure for table `bus`
--

DROP TABLE IF EXISTS `bus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bus` (
  `id_bus` int(11) NOT NULL AUTO_INCREMENT,
  `plaque` varchar(255) NOT NULL,
  `saved_date` datetime DEFAULT NULL,
  `seats` int(11) NOT NULL,
  `voided` bit(1) NOT NULL,
  `save_by_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_bus`),
  UNIQUE KEY `UK_1h6lus9a4opxxh36fnh0d7n85` (`plaque`),
  KEY `FK_n9vw3bucqcpxmgv65lwvqiodq` (`save_by_id`),
  CONSTRAINT `FK_n9vw3bucqcpxmgv65lwvqiodq` FOREIGN KEY (`save_by_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus`
--

LOCK TABLES `bus` WRITE;
/*!40000 ALTER TABLE `bus` DISABLE KEYS */;
INSERT INTO `bus` VALUES (1,'RAC337x','2016-07-28 14:12:00',2,'\0',1),(2,'RAC450P','2016-07-28 19:13:07',32,'\0',2);
/*!40000 ALTER TABLE `bus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `saved_date` datetime DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `voided` bit(1) DEFAULT NULL,
  `save_by_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_crkjmjk1oj8gb6j6t5kt7gcxm` (`name`),
  KEY `FK_cc276frdfkj3xs6a6s8hapcy3` (`save_by_id`),
  CONSTRAINT `FK_cc276frdfkj3xs6a6s8hapcy3` FOREIGN KEY (`save_by_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Uwimana','2016-07-28 14:10:50','0788888888','\0',1),(2,'Nicole','2016-07-28 14:39:01','075666666','\0',2);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `driver`
--

DROP TABLE IF EXISTS `driver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `driver` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `driving_lincence` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `saved_date` datetime DEFAULT NULL,
  `voided` bit(1) DEFAULT NULL,
  `bus_idBus` int(11) DEFAULT NULL,
  `save_by_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_slkqrdps15vgfx7qjjgdr3s49` (`bus_idBus`),
  KEY `FK_ad6gnyfu5y5nc17mxc1omlrqb` (`save_by_id`),
  CONSTRAINT `FK_ad6gnyfu5y5nc17mxc1omlrqb` FOREIGN KEY (`save_by_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK_slkqrdps15vgfx7qjjgdr3s49` FOREIGN KEY (`bus_idBus`) REFERENCES `bus` (`id_bus`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `driver`
--

LOCK TABLES `driver` WRITE;
/*!40000 ALTER TABLE `driver` DISABLE KEYS */;
/*!40000 ALTER TABLE `driver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `journey`
--

DROP TABLE IF EXISTS `journey`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `journey` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `saved_date` datetime DEFAULT NULL,
  `time_departure` varchar(255) DEFAULT NULL,
  `voided` bit(1) DEFAULT NULL,
  `bus_idBus` int(11) DEFAULT NULL,
  `ligne_idLigne` int(11) DEFAULT NULL,
  `save_by_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_c4kr8dhmhisbnf34apj83u6n4` (`bus_idBus`),
  KEY `FK_kx3qabsa84y49i66ljlfc8llm` (`ligne_idLigne`),
  KEY `FK_2jcp2xty3cvdvo4wybry8tdtq` (`save_by_id`),
  CONSTRAINT `FK_2jcp2xty3cvdvo4wybry8tdtq` FOREIGN KEY (`save_by_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK_c4kr8dhmhisbnf34apj83u6n4` FOREIGN KEY (`bus_idBus`) REFERENCES `bus` (`id_bus`),
  CONSTRAINT `FK_kx3qabsa84y49i66ljlfc8llm` FOREIGN KEY (`ligne_idLigne`) REFERENCES `ligne` (`id_ligne`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `journey`
--

LOCK TABLES `journey` WRITE;
/*!40000 ALTER TABLE `journey` DISABLE KEYS */;
INSERT INTO `journey` VALUES (1,'2016-07-28','2016-07-28 14:37:55','23:15','\0',1,1,2),(2,'2016-07-28','2016-07-28 15:05:37','15:15','\0',1,1,2);
/*!40000 ALTER TABLE `journey` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ligne`
--

DROP TABLE IF EXISTS `ligne`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ligne` (
  `id_ligne` int(11) NOT NULL AUTO_INCREMENT,
  `from_destination` varchar(255) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `saved_date` datetime DEFAULT NULL,
  `time_length` int(11) DEFAULT NULL,
  `to_destination` varchar(255) DEFAULT NULL,
  `voided` bit(1) DEFAULT NULL,
  `save_by_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_ligne`),
  KEY `FK_pwkkb4bbxuditsdoo0nf4v0du` (`save_by_id`),
  CONSTRAINT `FK_pwkkb4bbxuditsdoo0nf4v0du` FOREIGN KEY (`save_by_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ligne`
--

LOCK TABLES `ligne` WRITE;
/*!40000 ALTER TABLE `ligne` DISABLE KEYS */;
INSERT INTO `ligne` VALUES (1,'Kigali',1000,'2016-07-28 19:08:16',1,'Muhanga','\0',2),(3,'Kigali',4500,'2016-07-28 19:13:34',3,'Ruhengeri','\0',2);
/*!40000 ALTER TABLE `ligne` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ligne_assigned_bus`
--

DROP TABLE IF EXISTS `ligne_assigned_bus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ligne_assigned_bus` (
  `ligne_idLigne` int(11) NOT NULL,
  `assigned_bus_idBus` int(11) NOT NULL,
  UNIQUE KEY `UK_jukpdi2mr45aj9attvnvdwuql` (`assigned_bus_idBus`),
  KEY `FK_4n89lpxw3g0uxc4jm6d7qem7c` (`ligne_idLigne`),
  CONSTRAINT `FK_4n89lpxw3g0uxc4jm6d7qem7c` FOREIGN KEY (`ligne_idLigne`) REFERENCES `ligne` (`id_ligne`),
  CONSTRAINT `FK_jukpdi2mr45aj9attvnvdwuql` FOREIGN KEY (`assigned_bus_idBus`) REFERENCES `bus` (`id_bus`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ligne_assigned_bus`
--

LOCK TABLES `ligne_assigned_bus` WRITE;
/*!40000 ALTER TABLE `ligne_assigned_bus` DISABLE KEYS */;
INSERT INTO `ligne_assigned_bus` VALUES (1,1),(3,2);
/*!40000 ALTER TABLE `ligne_assigned_bus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date_payment` datetime DEFAULT NULL,
  `saved_date` datetime DEFAULT NULL,
  `time_payment` varchar(255) DEFAULT NULL,
  `voided` bit(1) DEFAULT NULL,
  `reservation_id` int(11) DEFAULT NULL,
  `save_by_id` int(11) DEFAULT NULL,
  `amount` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_3llq7oxcs9j7vlujfdf16jmu` (`reservation_id`),
  KEY `FK_l6104ilx530s9n09th78hbxch` (`save_by_id`),
  CONSTRAINT `FK_3llq7oxcs9j7vlujfdf16jmu` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`id`),
  CONSTRAINT `FK_l6104ilx530s9n09th78hbxch` FOREIGN KEY (`save_by_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,'2016-07-28 00:00:00','2016-07-28 18:02:56','18:02:51','\0',1,2,900),(2,'2016-07-28 00:00:00','2016-07-28 19:08:55','19:08:49','\0',2,2,1000);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `price`
--

DROP TABLE IF EXISTS `price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `price` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `actual_amount` bit(1) NOT NULL,
  `price_amount` int(11) NOT NULL,
  `ligne_idLigne` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_8qbj9jngt8kivpdynggv3xfx9` (`ligne_idLigne`),
  CONSTRAINT `FK_8qbj9jngt8kivpdynggv3xfx9` FOREIGN KEY (`ligne_idLigne`) REFERENCES `ligne` (`id_ligne`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `price`
--

LOCK TABLES `price` WRITE;
/*!40000 ALTER TABLE `price` DISABLE KEYS */;
/*!40000 ALTER TABLE `price` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `price_histors`
--

DROP TABLE IF EXISTS `price_histors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `price_histors` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `actual_amount` tinyblob,
  `end_date` datetime DEFAULT NULL,
  `price_amount` tinyblob,
  `saved_date` datetime DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `voided` bit(1) DEFAULT NULL,
  `ligne_idLigne` int(11) DEFAULT NULL,
  `save_by_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_mb7n4lxk52oh16mp9agswv1f1` (`ligne_idLigne`),
  KEY `FK_7n70i4yhnrmkvptiyerlhepka` (`save_by_id`),
  CONSTRAINT `FK_7n70i4yhnrmkvptiyerlhepka` FOREIGN KEY (`save_by_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK_mb7n4lxk52oh16mp9agswv1f1` FOREIGN KEY (`ligne_idLigne`) REFERENCES `ligne` (`id_ligne`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `price_histors`
--

LOCK TABLES `price_histors` WRITE;
/*!40000 ALTER TABLE `price_histors` DISABLE KEYS */;
/*!40000 ALTER TABLE `price_histors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `paid` bit(1) DEFAULT NULL,
  `saved_date` datetime DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `voided` bit(1) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `ligne_idLigne` int(11) DEFAULT NULL,
  `save_by_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_69tigr4wbkrwhfef2l1mp6vgu` (`customer_id`),
  KEY `FK_bb00gvmrj9da8atokj747nmqj` (`ligne_idLigne`),
  KEY `FK_kgmh9ays5ang5s90oktkp6baa` (`save_by_id`),
  CONSTRAINT `FK_69tigr4wbkrwhfef2l1mp6vgu` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `FK_bb00gvmrj9da8atokj747nmqj` FOREIGN KEY (`ligne_idLigne`) REFERENCES `ligne` (`id_ligne`),
  CONSTRAINT `FK_kgmh9ays5ang5s90oktkp6baa` FOREIGN KEY (`save_by_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (1,'2016-07-28','','2016-07-28 14:38:36','23 : 15 ','\0',1,1,2),(2,'2016-07-28','','2016-07-28 15:36:54','15 : 15 ','\0',2,1,2),(3,'2016-07-28','\0','2016-07-28 15:37:14','15 : 15 ','\0',1,1,2),(4,'2016-07-28','\0','2016-07-28 15:37:31','15 : 15 ','\0',2,1,2);
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `saved_date` datetime DEFAULT NULL,
  `user_role` varchar(255) NOT NULL,
  `voided` bit(1) DEFAULT NULL,
  `save_by_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_10nsvj0l8fts3n4uv25kdhv3n` (`save_by_id`),
  CONSTRAINT `FK_10nsvj0l8fts3n4uv25kdhv3n` FOREIGN KEY (`save_by_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,NULL,'Admin','\0',NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket` (
  `id_ticket` int(11) NOT NULL AUTO_INCREMENT,
  `saved_date` datetime DEFAULT NULL,
  `voided` bit(1) DEFAULT NULL,
  `bus_idBus` int(11) DEFAULT NULL,
  `journey_id` int(11) DEFAULT NULL,
  `payment_id` int(11) DEFAULT NULL,
  `reservation_id` int(11) DEFAULT NULL,
  `save_by_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_ticket`),
  KEY `FK_6m527vv79leo8lssr4th6el7e` (`bus_idBus`),
  KEY `FK_h7xai132en8apysdo3fflenvn` (`journey_id`),
  KEY `FK_1lsimdp5kdxly2nfpxke69vnb` (`payment_id`),
  KEY `FK_lu9d2ii2dul4ael518na9w521` (`reservation_id`),
  KEY `FK_teuy2g8y0u98nqkgxs14f3pan` (`save_by_id`),
  CONSTRAINT `FK_1lsimdp5kdxly2nfpxke69vnb` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`id`),
  CONSTRAINT `FK_6m527vv79leo8lssr4th6el7e` FOREIGN KEY (`bus_idBus`) REFERENCES `bus` (`id_bus`),
  CONSTRAINT `FK_h7xai132en8apysdo3fflenvn` FOREIGN KEY (`journey_id`) REFERENCES `journey` (`id`),
  CONSTRAINT `FK_lu9d2ii2dul4ael518na9w521` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`id`),
  CONSTRAINT `FK_teuy2g8y0u98nqkgxs14f3pan` FOREIGN KEY (`save_by_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enabled` bit(1) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `saved_date` date DEFAULT NULL,
  `telephone` int(11) DEFAULT NULL,
  `username` varchar(100) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  `saved_by_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`),
  KEY `FK_krvotbtiqhudlkamvlpaqus0t` (`role_id`),
  KEY `FK_1t60j1l6r6b025rw1jo8noc8h` (`saved_by_id`),
  CONSTRAINT `FK_1t60j1l6r6b025rw1jo8noc8h` FOREIGN KEY (`saved_by_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK_krvotbtiqhudlkamvlpaqus0t` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'','firstaName','lastName','$2a$10$dw3w/diq/ST4gl4o8LI9dO939UeRZIUnG6nrqjottNgJLgUwtzEF2','2016-07-28',NULL,'admin',NULL,NULL),(2,'',NULL,NULL,'$2a$10$dw3w/diq/ST4gl4o8LI9dO939UeRZIUnG6nrqjottNgJLgUwtzEF2',NULL,NULL,'demo',NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-07-29  7:13:38
