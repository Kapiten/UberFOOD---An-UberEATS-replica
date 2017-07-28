-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: uberfood
-- ------------------------------------------------------
-- Server version	5.7.18

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
CREATE DATABASE IF NOT EXISTS uberfood;
USE uberfood;user
--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `address_id` int(11) NOT NULL AUTO_INCREMENT,
  `unit_no` varchar(45) DEFAULT NULL,
  `streetame` varchar(45) DEFAULT NULL,
  `suburb` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `code` varchar(45) DEFAULT NULL,
  `gps_coord` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `person_id` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `completion_type`
--

DROP TABLE IF EXISTS `completion_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `completion_type` (
  `completion_type_id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`completion_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `completion_type`
--

LOCK TABLES `completion_type` WRITE;
/*!40000 ALTER TABLE `completion_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `completion_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact` (
  `contact_id` int(11) NOT NULL AUTO_INCREMENT,
  `cell1` varchar(45) DEFAULT NULL,
  `cell2` varchar(45) DEFAULT NULL,
  `tel1` varchar(45) DEFAULT NULL,
  `tel2` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `email2` varchar(45) DEFAULT NULL,
  `fax` varchar(45) DEFAULT NULL,
  `person_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`contact_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` VALUES (1,'0787563215',NULL,NULL,NULL,'kapiten.bm@',NULL,NULL,NULL),(2,'0832563256',NULL,NULL,NULL,'rich1.bren@yahoo.com',NULL,NULL,'10'),(3,'0736521456',NULL,NULL,NULL,'brad.coupe@gmail.com',NULL,NULL,'11'),(4,'0736521456',NULL,NULL,NULL,'brad1.coupe@gmail.com',NULL,NULL,'12'),(5,'0736521456',NULL,NULL,NULL,'brad12.coupe@gmail.com',NULL,NULL,'13'),(6,'0736521456',NULL,NULL,NULL,'brad123.coupe@gmail.com',NULL,NULL,'14');
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courier`
--

DROP TABLE IF EXISTS `courier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `courier` (
  `courier_id` int(11) NOT NULL,
  `person_id` varchar(45) DEFAULT NULL,
  `courier_type_id` int(11) DEFAULT NULL,
  `rating` varchar(45) DEFAULT NULL,
  `couriercol` varchar(45) DEFAULT NULL,
  `vehicle_vehicle_id` int(11) NOT NULL,
  PRIMARY KEY (`courier_id`,`vehicle_vehicle_id`),
  KEY `fk_courier_vehicle1_idx` (`vehicle_vehicle_id`),
  KEY `fk_courier_courier_type1_idx` (`courier_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courier`
--

LOCK TABLES `courier` WRITE;
/*!40000 ALTER TABLE `courier` DISABLE KEYS */;
/*!40000 ALTER TABLE `courier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courier_type`
--

DROP TABLE IF EXISTS `courier_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `courier_type` (
  `courier_type_id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`courier_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courier_type`
--

LOCK TABLES `courier_type` WRITE;
/*!40000 ALTER TABLE `courier_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `courier_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuisine`
--

DROP TABLE IF EXISTS `cuisine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuisine` (
  `cuisine_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `ingredients` varchar(300) DEFAULT NULL,
  `preparation` varchar(300) DEFAULT NULL,
  `extras` varchar(100) DEFAULT NULL,
  `price` varchar(45) DEFAULT NULL,
  `date_added` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `profile_pic` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`cuisine_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuisine`
--

LOCK TABLES `cuisine` WRITE;
/*!40000 ALTER TABLE `cuisine` DISABLE KEYS */;
INSERT INTO `cuisine` VALUES (1,'Hot Dog','A traditional hot dog meal with prefered toppings.',NULL,NULL,NULL,'R25.00',NULL,NULL,'/Uber/TheServerComp/web/resources/images/the_fat_dog/hotdog.jpg'),(2,'Green Faced Dog','A hotdog accompanied by some juicy salads. ',NULL,NULL,NULL,'R33.00',NULL,'2','/Uber/TheServerComp/web/resources/images/the_fat_dog/green_faced_dog.jpg'),(3,'Breakfast - Rotorua','A nice meaty breakfast to get you started for the day.',NULL,NULL,NULL,'R24.00',NULL,'2','/Uber/TheServerComp/web/resources/images/the_fat_dog/breakfast_rotorua.jpg'),(4,'French Toast','Whether for breakfast or brunch, it\'s enough to fill you up for the next item in your To-Do list.',NULL,NULL,NULL,'R25.00',NULL,NULL,'/Uber/TheServerComp/web/resources/images/the_fat_dog/french_toast.jpg'),(5,'Pork Belly','A juicy slice of pork on top of some greens and small potatoes on the side.',NULL,NULL,NULL,'R30.00',NULL,NULL,'/Uber/TheServerComp/web/resources/images/the_fat_dog/pork_belly.jpg'),(6,'The Dogs Bollox','The biggest burger you\'ll ever have made from the greatest ingredients.',NULL,NULL,NULL,'R54.00',NULL,'2','/Uber/TheServerComp/web/resources/images/the_fat_dog/the_dogs_bollox!!!.jpg'),(7,'Sausage Fun','A spiced up hotdog with prefered toppings.',NULL,NULL,NULL,'R40.00',NULL,NULL,'/Uber/TheServerComp/web/resources/images/the_fat_dog/sausage_fun.jpg');
/*!40000 ALTER TABLE `cuisine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuisine_extras`
--

DROP TABLE IF EXISTS `cuisine_extras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuisine_extras` (
  `cuisine_extras_id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `price` varchar(45) DEFAULT NULL,
  `type_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cuisine_extras_id`),
  KEY `fk_extras_extra_type1_idx` (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuisine_extras`
--

LOCK TABLES `cuisine_extras` WRITE;
/*!40000 ALTER TABLE `cuisine_extras` DISABLE KEYS */;
INSERT INTO `cuisine_extras` VALUES (1,'Tomato Sauce',NULL,'1'),(2,'Mustard',NULL,'1'),(3,'1000 Island',NULL,'1'),(4,'Chilli',NULL,'1'),(5,'Sweet Chilli',NULL,'1'),(6,'Slow-Cooked',NULL,'2'),(7,'Fried',NULL,'2'),(8,'Crispy Fried',NULL,'2'),(9,'Mild',NULL,'3'),(10,'Medium',NULL,'3'),(11,'Hot',NULL,'3');
/*!40000 ALTER TABLE `cuisine_extras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuisine_has_extras`
--

DROP TABLE IF EXISTS `cuisine_has_extras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuisine_has_extras` (
  `cuisine_cuisine_id` int(11) NOT NULL,
  `extras_extras_id` int(11) DEFAULT NULL,
  `extra_type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`cuisine_cuisine_id`) USING BTREE,
  KEY `fk_cuisine_has_extras_extras1_idx` (`extras_extras_id`),
  KEY `fk_cuisine_has_extras_cuisine1_idx` (`cuisine_cuisine_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuisine_has_extras`
--

LOCK TABLES `cuisine_has_extras` WRITE;
/*!40000 ALTER TABLE `cuisine_has_extras` DISABLE KEYS */;
INSERT INTO `cuisine_has_extras` VALUES (1,NULL,1),(2,NULL,1),(3,NULL,2),(6,NULL,1),(7,NULL,1);
/*!40000 ALTER TABLE `cuisine_has_extras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuisine_has_order_list`
--

DROP TABLE IF EXISTS `cuisine_has_order_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuisine_has_order_list` (
  `cuisine_cuisine_id` int(11) NOT NULL,
  `order_list_order_list_id` int(11) NOT NULL,
  PRIMARY KEY (`cuisine_cuisine_id`,`order_list_order_list_id`),
  KEY `fk_cuisine_has_order_list_order_list1_idx` (`order_list_order_list_id`),
  KEY `fk_cuisine_has_order_list_cuisine1_idx` (`cuisine_cuisine_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuisine_has_order_list`
--

LOCK TABLES `cuisine_has_order_list` WRITE;
/*!40000 ALTER TABLE `cuisine_has_order_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuisine_has_order_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuisine_list`
--

DROP TABLE IF EXISTS `cuisine_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuisine_list` (
  `cuisine_list_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(45) NOT NULL,
  `cuisine_id` varchar(45) DEFAULT NULL,
  `extra` varchar(45) DEFAULT NULL,
  `special_instructions` varchar(200) DEFAULT NULL,
  `quantity` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`cuisine_list_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuisine_list`
--

LOCK TABLES `cuisine_list` WRITE;
/*!40000 ALTER TABLE `cuisine_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuisine_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `extra_type`
--

DROP TABLE IF EXISTS `extra_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `extra_type` (
  `extra_type_id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`extra_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `extra_type`
--

LOCK TABLES `extra_type` WRITE;
/*!40000 ALTER TABLE `extra_type` DISABLE KEYS */;
INSERT INTO `extra_type` VALUES (1,'Sauces',NULL),(2,'Egg Prep.',NULL),(3,'Spice Level',NULL);
/*!40000 ALTER TABLE `extra_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `order_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `order_no` varchar(45) NOT NULL,
  `date` varchar(45) NOT NULL,
  `order_type` varchar(45) DEFAULT NULL,
  `extras_note` varchar(500) DEFAULT NULL,
  `delivery_note` varchar(500) DEFAULT NULL,
  `recipient` varchar(45) DEFAULT NULL,
  `intended_recipient` varchar(45) DEFAULT NULL,
  `completion_type_id` varchar(45) DEFAULT NULL,
  `restaurant_id` varchar(45) NOT NULL,
  `courier_id` varchar(45) DEFAULT NULL,
  `subtotal` varchar(45) DEFAULT NULL,
  `tax` varchar(45) DEFAULT NULL,
  `delivery_fee` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,'FD00-00001','17/06/2017 05:53:59:89',NULL,NULL,'Ring door bell',NULL,NULL,NULL,'3',NULL,NULL,NULL,NULL,NULL),(2,'TS99-12345','04 07 2017 08:21:06.381','','Extra hot sauce','Leave with reception','','','','3','','R57.00','R7.98','R15.00','1'),(3,'TS99-12345','04 07 2017 08:58:43.330','','','','','','','3','','R74.00','R10.36','R15.00','1'),(4,'TS99-12345','04 07 2017 10:10:51.243','','','','','','','3','','R25.00','R3.50','R15.00','1'),(5,'TS99-12345','04 07 2017 10:11:23.712','','','','','','','3','','R24.00','R3.36','R15.00','1'),(6,'TS99-12345','04 07 2017 11:51:42.226','','','','','','','3','','R33.00','R4.62','R15.00','1'),(7,'TS99-12345','04 07 2017 11:54:43.709','','','','','','','3','','R33.00','R4.62','R15.00','1'),(8,'TS99-12345','04 07 2017 11:56:46.063','','','','','','','3','','R25.00','R3.50','R15.00','1'),(9,'TS99-12345','04 07 2017 12:03:09.280','','','','','','','3','','R33.00','R4.62','R15.00','1'),(10,'TS99-12345','04 07 2017 12:04:42.111','','','','','','','3','','R24.00','R3.36','R15.00','1'),(11,'TS99-12345','04 07 2017 12:07:13.379','','','','','','','3','','R24.00','R3.36','R15.00','1'),(12,'TS99-12345','04 07 2017 12:07:48.390','','','','','','','3','','R24.00','R3.36','R15.00','1'),(13,'TS99-12345','04 07 2017 12:07:48.390','','','','','','','3','','R24.00','R3.36','R15.00','1');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_type`
--

DROP TABLE IF EXISTS `order_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_type` (
  `order_type_id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`order_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_type`
--

LOCK TABLES `order_type` WRITE;
/*!40000 ALTER TABLE `order_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment` (
  `payment_id` int(11) NOT NULL,
  `card_no` varchar(45) DEFAULT NULL,
  `card_type` varchar(45) DEFAULT NULL,
  `cvv` varchar(3) DEFAULT NULL,
  `expire_date` varchar(5) DEFAULT NULL,
  `paypal_id` varchar(45) DEFAULT NULL,
  `person_person_id` int(11) NOT NULL,
  PRIMARY KEY (`payment_id`,`person_person_id`),
  KEY `fk_payment_person1_idx` (`person_person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `person_id` int(11) NOT NULL AUTO_INCREMENT,
  `names` varchar(45) DEFAULT NULL,
  `surname` varchar(45) DEFAULT NULL,
  `national_id` varchar(45) DEFAULT NULL,
  `date_of_birth` varchar(45) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `age` varchar(45) DEFAULT NULL,
  `token_id` varchar(45) DEFAULT NULL,
  `user_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`person_id`),
  UNIQUE KEY `person_id_UNIQUE` (`person_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'tebogo','sibiya',NULL,NULL,NULL,NULL,'2011156277',NULL),(2,'tebogo','sibiya',NULL,NULL,NULL,NULL,'2011213653',NULL),(3,'tebogo','sibiya',NULL,NULL,NULL,NULL,'2011252508',NULL),(4,'tebogo','sibiya',NULL,NULL,NULL,NULL,'2011442725',NULL),(5,'tebogo','sibiya',NULL,NULL,NULL,NULL,'2011838512',NULL),(6,'Bradley','Foster',NULL,NULL,NULL,NULL,'1498333394741-495414080',NULL),(7,'Bradley','Foster',NULL,NULL,NULL,NULL,'1498333666318-1531083000',NULL),(8,'Richard','Brenson',NULL,NULL,NULL,NULL,'1498337017166-1556913178',NULL),(9,'Richard','Brenson',NULL,NULL,NULL,NULL,'1498337070315--2034171151',NULL),(10,'Richard','Brenson',NULL,NULL,NULL,NULL,'1498341916225--653853746',NULL),(11,'Bradley','Cooper',NULL,NULL,NULL,NULL,'1498343755075-293210076',NULL),(12,'Bradley','Cooper',NULL,NULL,NULL,NULL,'1498343785583-2056603549',NULL),(13,'Bradley','Cooper',NULL,NULL,NULL,NULL,'1498344622087-1005105063','13'),(14,'Bradley','Cooper',NULL,NULL,NULL,NULL,'1498345366636-1102926376','15');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promotion` (
  `promotion_id` int(11) NOT NULL,
  `title` varchar(45) DEFAULT NULL,
  `description` varchar(300) DEFAULT NULL,
  `promo_code` varchar(45) DEFAULT NULL,
  `date_from` varchar(45) DEFAULT NULL,
  `date_to` varchar(45) DEFAULT NULL,
  `promo_type` varchar(45) DEFAULT NULL,
  `requirements` varchar(45) DEFAULT NULL,
  `restaurant_restaurant_id` int(11) NOT NULL,
  PRIMARY KEY (`promotion_id`,`restaurant_restaurant_id`),
  KEY `fk_promotion_restaurant1_idx` (`restaurant_restaurant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotion`
--

LOCK TABLES `promotion` WRITE;
/*!40000 ALTER TABLE `promotion` DISABLE KEYS */;
/*!40000 ALTER TABLE `promotion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant`
--

DROP TABLE IF EXISTS `restaurant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restaurant` (
  `restaurant_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `owners` varchar(45) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `operating_hours` varchar(100) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `address_id` varchar(45) DEFAULT NULL,
  `profile_pic` varchar(200) DEFAULT NULL,
  `user_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`restaurant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant`
--

LOCK TABLES `restaurant` WRITE;
/*!40000 ALTER TABLE `restaurant` DISABLE KEYS */;
INSERT INTO `restaurant` VALUES (1,'The Braai Room',NULL,'We braai it, you enjoy it.','0[7:00/19:00]','African',NULL,'/Uber/TheServerComp/web/resources/images/the_braai_room.jpg',NULL),(2,'Delish',NULL,'An enjoyable dinner place with delishous cuisine.','0[17:00/21:00]',NULL,NULL,'/Uber/TheServerComp/web/resources/images/delish.jpg',NULL),(3,'The Fat Dog',NULL,'The best hot dogs with the best toppings','1[10:00/15:00]',NULL,NULL,'/Uber/TheServerComp/web/resources/images/the_fat_dog.jpg',NULL),(4,'Soko Food',NULL,'South Korean cuisines','0[9:00/17:00]',NULL,NULL,'/Uber/TheServerComp/web/resources/images/soko_food.jpg',NULL),(5,'Salata',NULL,'Most delicious salads you\'ll ever taste, wait did you know they\'re healthy as well.','0[8:00/14:00]',NULL,NULL,'/Uber/TheServerComp/web/resources/images/salata.jpg',NULL);
/*!40000 ALTER TABLE `restaurant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant_has_cuisine`
--

DROP TABLE IF EXISTS `restaurant_has_cuisine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restaurant_has_cuisine` (
  `restaurant_id` int(11) NOT NULL,
  `cuisine_id` int(20) NOT NULL,
  PRIMARY KEY (`cuisine_id`,`restaurant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant_has_cuisine`
--

LOCK TABLES `restaurant_has_cuisine` WRITE;
/*!40000 ALTER TABLE `restaurant_has_cuisine` DISABLE KEYS */;
INSERT INTO `restaurant_has_cuisine` VALUES (3,1),(3,2),(3,3),(3,4),(3,5),(3,6),(3,7);
/*!40000 ALTER TABLE `restaurant_has_cuisine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sample`
--

DROP TABLE IF EXISTS `sample`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sample` (
  `id` time DEFAULT NULL,
  `name` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sample`
--

LOCK TABLES `sample` WRITE;
/*!40000 ALTER TABLE `sample` DISABLE KEYS */;
/*!40000 ALTER TABLE `sample` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store_address`
--

DROP TABLE IF EXISTS `store_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `store_address` (
  `store_address_id` int(11) NOT NULL,
  `unit_no` varchar(45) DEFAULT NULL,
  `streetname` varchar(45) DEFAULT NULL,
  `suburb` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `code` varchar(45) DEFAULT NULL,
  `gps_coord` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `restaurant_restaurant_id` int(11) NOT NULL,
  PRIMARY KEY (`store_address_id`,`restaurant_restaurant_id`),
  KEY `fk_store_address_restaurant1_idx` (`restaurant_restaurant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store_address`
--

LOCK TABLES `store_address` WRITE;
/*!40000 ALTER TABLE `store_address` DISABLE KEYS */;
/*!40000 ALTER TABLE `store_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store_contact`
--

DROP TABLE IF EXISTS `store_contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `store_contact` (
  `store_contact_id` int(11) NOT NULL,
  `tel1` varchar(45) DEFAULT NULL,
  `tel2` varchar(45) DEFAULT NULL,
  `cell1` varchar(45) DEFAULT NULL,
  `cell2` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `email2` varchar(45) DEFAULT NULL,
  `fax` varchar(45) DEFAULT NULL,
  `restaurant_restaurant_id` int(11) NOT NULL,
  PRIMARY KEY (`store_contact_id`,`restaurant_restaurant_id`),
  KEY `fk_store_contact_restaurant1_idx` (`restaurant_restaurant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store_contact`
--

LOCK TABLES `store_contact` WRITE;
/*!40000 ALTER TABLE `store_contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `store_contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transactions` (
  `transactions_id` int(11) NOT NULL,
  `date` varchar(45) DEFAULT NULL,
  `amount` decimal(10,0) DEFAULT NULL,
  `sender_type` varchar(45) DEFAULT NULL,
  `sender` varchar(45) DEFAULT NULL,
  `transactionscol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`transactions_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `token_id` varchar(100) DEFAULT NULL,
  `session_token` varchar(45) DEFAULT NULL,
  `profile_pic` varchar(45) DEFAULT NULL,
  `logged_in` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'root@gmail.com','root1234',NULL,'434960768866987',NULL,NULL),(2,'kapiten.bm@','root',NULL,'5985423467698543',NULL,NULL),(3,'brad.com@yahoo.com','ropt1234',NULL,'-6239426493752681308',NULL,NULL),(5,'rich.bren@yahoo.com','root123',NULL,'1877799479163411799',NULL,NULL),(8,'rich1.bren@yahoo.com','root12',NULL,'3465931104792537755',NULL,NULL),(10,'brad.coupe@gmail.com','root',NULL,'-795901586640793365',NULL,NULL),(11,'brad1.coupe@gmail.com','root',NULL,'-7997797482714295044',NULL,NULL),(13,'brad12.coupe@gmail.com','root','1498344622087-1005105063',NULL,NULL,NULL),(15,'brad123.coupe@gmail.com','root','1498345366636-1102926376',NULL,NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle`
--

DROP TABLE IF EXISTS `vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vehicle` (
  `vehicle_id` int(11) NOT NULL,
  `vehicle_type_id` varchar(45) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `brand` varchar(45) DEFAULT NULL,
  `model` varchar(45) DEFAULT NULL,
  `color` varchar(45) DEFAULT NULL,
  `owner` varchar(45) DEFAULT NULL,
  `registration_no` varchar(45) DEFAULT NULL,
  `description` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`vehicle_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle`
--

LOCK TABLES `vehicle` WRITE;
/*!40000 ALTER TABLE `vehicle` DISABLE KEYS */;
/*!40000 ALTER TABLE `vehicle` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-12 10:07:16
