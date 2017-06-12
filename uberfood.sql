-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.7.17-log


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema uberfood
--

CREATE DATABASE IF NOT EXISTS uberfood;
USE uberfood;

--
-- Definition of table `address`
--

DROP TABLE IF EXISTS `address`;
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

--
-- Dumping data for table `address`
--

/*!40000 ALTER TABLE `address` DISABLE KEYS */;
/*!40000 ALTER TABLE `address` ENABLE KEYS */;


--
-- Definition of table `completion_type`
--

DROP TABLE IF EXISTS `completion_type`;
CREATE TABLE `completion_type` (
  `completion_type_id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`completion_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `completion_type`
--

/*!40000 ALTER TABLE `completion_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `completion_type` ENABLE KEYS */;


--
-- Definition of table `contact`
--

DROP TABLE IF EXISTS `contact`;
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `contact`
--

/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` (`contact_id`,`cell1`,`cell2`,`tel1`,`tel2`,`email`,`email2`,`fax`,`person_id`) VALUES 
 (1,'0787563215',NULL,NULL,NULL,'kapiten.bm@',NULL,NULL,NULL);
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;


--
-- Definition of table `courier`
--

DROP TABLE IF EXISTS `courier`;
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

--
-- Dumping data for table `courier`
--

/*!40000 ALTER TABLE `courier` DISABLE KEYS */;
/*!40000 ALTER TABLE `courier` ENABLE KEYS */;


--
-- Definition of table `courier_type`
--

DROP TABLE IF EXISTS `courier_type`;
CREATE TABLE `courier_type` (
  `courier_type_id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`courier_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `courier_type`
--

/*!40000 ALTER TABLE `courier_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `courier_type` ENABLE KEYS */;


--
-- Definition of table `cuisine`
--

DROP TABLE IF EXISTS `cuisine`;
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

--
-- Dumping data for table `cuisine`
--

/*!40000 ALTER TABLE `cuisine` DISABLE KEYS */;
INSERT INTO `cuisine` (`cuisine_id`,`name`,`description`,`ingredients`,`preparation`,`extras`,`price`,`date_added`,`type`,`profile_pic`) VALUES 
 (1,'Hot Dog','A traditional hot dog meal with prefered toppings.',NULL,NULL,NULL,'R25.00',NULL,NULL,'C:/Users/StrettO/Documents/NetBeansProjects/TheServerComp/web/resources/images/the_fat_dog/hotdog.jpg'),
 (2,'Green Faced Dog','A hotdog accompanied by some juicy salads. ',NULL,NULL,NULL,'R33.00',NULL,NULL,'C:/Users/StrettO/Documents/NetBeansProjects/TheServerComp/web/resources/images/the_fat_dog/green_faced_dog.jpg'),
 (3,'Breakfast - Rotorua','A nice meaty breakfast to get you started for the day.',NULL,NULL,NULL,'R24.00',NULL,NULL,'C:/Users/StrettO/Documents/NetBeansProjects/TheServerComp/web/resources/images/the_fat_dog/breakfast_rotorua.jpg'),
 (4,'French Toast','Whether for breakfast or brunch, it\'s enough to fill you up for the next item in your To-Do list.',NULL,NULL,NULL,'R25.00',NULL,NULL,'C:/Users/StrettO/Documents/NetBeansProjects/TheServerComp/web/resources/images/the_fat_dog/french_toast.jpg'),
 (5,'Pork Belly','A juicy slice of pork on top of some greens and small potatoes on the side.',NULL,NULL,NULL,'R30.00',NULL,NULL,'C:/Users/StrettO/Documents/NetBeansProjects/TheServerComp/web/resources/images/the_fat_dog/pork_belly.jpg'),
 (6,'The Dogs Bollox','The biggest burger you\'ll ever have made from the greatest ingredients.',NULL,NULL,NULL,'R54.00',NULL,NULL,'C:/Users/StrettO/Documents/NetBeansProjects/TheServerComp/web/resources/images/the_fat_dog/the_dogs_bollox!!!.jpg'),
 (7,'Sausage Fun','A spiced up hotdog with prefered toppings.',NULL,NULL,NULL,'R40.00',NULL,NULL,'C:/Users/StrettO/Documents/NetBeansProjects/TheServerComp/web/resources/images/the_fat_dog/sausage_fun.jpg');
/*!40000 ALTER TABLE `cuisine` ENABLE KEYS */;


--
-- Definition of table `cuisine_has_extras`
--

DROP TABLE IF EXISTS `cuisine_has_extras`;
CREATE TABLE `cuisine_has_extras` (
  `cuisine_cuisine_id` int(11) NOT NULL,
  `extras_extras_id` int(11) DEFAULT NULL,
  `extra_type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`cuisine_cuisine_id`) USING BTREE,
  KEY `fk_cuisine_has_extras_extras1_idx` (`extras_extras_id`),
  KEY `fk_cuisine_has_extras_cuisine1_idx` (`cuisine_cuisine_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cuisine_has_extras`
--

/*!40000 ALTER TABLE `cuisine_has_extras` DISABLE KEYS */;
INSERT INTO `cuisine_has_extras` (`cuisine_cuisine_id`,`extras_extras_id`,`extra_type_id`) VALUES 
 (1,NULL,1),
 (2,NULL,1),
 (3,NULL,2),
 (6,NULL,1),
 (7,NULL,1);
/*!40000 ALTER TABLE `cuisine_has_extras` ENABLE KEYS */;


--
-- Definition of table `cuisine_has_order_list`
--

DROP TABLE IF EXISTS `cuisine_has_order_list`;
CREATE TABLE `cuisine_has_order_list` (
  `cuisine_cuisine_id` int(11) NOT NULL,
  `order_list_order_list_id` int(11) NOT NULL,
  PRIMARY KEY (`cuisine_cuisine_id`,`order_list_order_list_id`),
  KEY `fk_cuisine_has_order_list_order_list1_idx` (`order_list_order_list_id`),
  KEY `fk_cuisine_has_order_list_cuisine1_idx` (`cuisine_cuisine_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cuisine_has_order_list`
--

/*!40000 ALTER TABLE `cuisine_has_order_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuisine_has_order_list` ENABLE KEYS */;


--
-- Definition of table `extra_type`
--

DROP TABLE IF EXISTS `extra_type`;
CREATE TABLE `extra_type` (
  `extra_type_id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`extra_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `extra_type`
--

/*!40000 ALTER TABLE `extra_type` DISABLE KEYS */;
INSERT INTO `extra_type` (`extra_type_id`,`name`,`description`) VALUES 
 (1,'Sauces',NULL),
 (2,'Egg Prep.',NULL),
 (3,'Spice Level',NULL);
/*!40000 ALTER TABLE `extra_type` ENABLE KEYS */;


--
-- Definition of table `extras`
--

DROP TABLE IF EXISTS `extras`;
CREATE TABLE `extras` (
  `extras_id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `price` varchar(45) DEFAULT NULL,
  `type_id` varchar(45) DEFAULT NULL,
  `required` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`extras_id`),
  KEY `fk_extras_extra_type1_idx` (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `extras`
--

/*!40000 ALTER TABLE `extras` DISABLE KEYS */;
INSERT INTO `extras` (`extras_id`,`name`,`price`,`type_id`,`required`) VALUES 
 (1,'Tomato Sauce',NULL,'1',NULL),
 (2,'Mustard',NULL,'1',NULL),
 (3,'1000 Island',NULL,'1',NULL),
 (4,'Chilli',NULL,'1',NULL),
 (5,'Sweet Chilli',NULL,'1',NULL),
 (6,'Slow-Cooked',NULL,'2',NULL),
 (7,'Fried',NULL,'2',NULL),
 (8,'Crispy Fried',NULL,'2',NULL),
 (9,'Mild',NULL,'3',NULL),
 (10,'Medium',NULL,'3',NULL),
 (11,'Hot',NULL,'3',NULL);
/*!40000 ALTER TABLE `extras` ENABLE KEYS */;


--
-- Definition of table `order`
--

DROP TABLE IF EXISTS `order`;
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
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `order`
--

/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;


--
-- Definition of table `order_list`
--

DROP TABLE IF EXISTS `order_list`;
CREATE TABLE `order_list` (
  `order_list_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(45) NOT NULL,
  `cuisine_id` varchar(45) DEFAULT NULL,
  `extra` varchar(45) DEFAULT NULL,
  `special_instructions` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`order_list_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `order_list`
--

/*!40000 ALTER TABLE `order_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_list` ENABLE KEYS */;


--
-- Definition of table `order_type`
--

DROP TABLE IF EXISTS `order_type`;
CREATE TABLE `order_type` (
  `order_type_id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`order_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `order_type`
--

/*!40000 ALTER TABLE `order_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_type` ENABLE KEYS */;


--
-- Definition of table `payment`
--

DROP TABLE IF EXISTS `payment`;
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

--
-- Dumping data for table `payment`
--

/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;


--
-- Definition of table `person`
--

DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `person_id` int(11) NOT NULL AUTO_INCREMENT,
  `names` varchar(45) DEFAULT NULL,
  `surname` varchar(45) DEFAULT NULL,
  `national_id` varchar(45) DEFAULT NULL,
  `date_of_birth` varchar(45) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `age` varchar(45) DEFAULT NULL,
  `profile_pic` varchar(45) DEFAULT NULL,
  `token_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`person_id`),
  UNIQUE KEY `person_id_UNIQUE` (`person_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `person`
--

/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` (`person_id`,`names`,`surname`,`national_id`,`date_of_birth`,`gender`,`age`,`profile_pic`,`token_id`) VALUES 
 (1,'tebogo','sibiya',NULL,NULL,NULL,NULL,NULL,'2011156277'),
 (2,'tebogo','sibiya',NULL,NULL,NULL,NULL,NULL,'2011213653'),
 (3,'tebogo','sibiya',NULL,NULL,NULL,NULL,NULL,'2011252508'),
 (4,'tebogo','sibiya',NULL,NULL,NULL,NULL,NULL,'2011442725'),
 (5,'tebogo','sibiya',NULL,NULL,NULL,NULL,NULL,'2011838512');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;


--
-- Definition of table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
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

--
-- Dumping data for table `promotion`
--

/*!40000 ALTER TABLE `promotion` DISABLE KEYS */;
/*!40000 ALTER TABLE `promotion` ENABLE KEYS */;


--
-- Definition of table `restaurant`
--

DROP TABLE IF EXISTS `restaurant`;
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

--
-- Dumping data for table `restaurant`
--

/*!40000 ALTER TABLE `restaurant` DISABLE KEYS */;
INSERT INTO `restaurant` (`restaurant_id`,`name`,`owners`,`description`,`operating_hours`,`type`,`address_id`,`profile_pic`,`user_id`) VALUES 
 (1,'The Braai Room',NULL,'We braai it, you enjoy it.','0[7:00/19:00]','African',NULL,'C:/Users/StrettO/Documents/NetBeansProjects/TheServerComp/web/resources/images/the_braai_room.jpg',NULL),
 (2,'Delish',NULL,'An enjoyable dinner place with delishous cuisine.','0[17:00/21:00]',NULL,NULL,'C:/Users/StrettO/Documents/NetBeansProjects/TheServerComp/web/resources/images/delish.jpg',NULL),
 (3,'The Fat Dog',NULL,'The best hot dogs with the best toppings','0[10:00/15:00]',NULL,NULL,'C:\\Users\\StrettO\\Documents\\NetBeansProjects\\TheServerComp\\web\\resources\\images\\the_fat_dog.jpg',NULL),
 (4,'Soko Food',NULL,'South Korean cuisines','0[9:00/17:00]',NULL,NULL,'C:\\Users\\StrettO\\Documents\\NetBeansProjects\\TheServerComp\\web\\resources\\images\\soko_food.jpg',NULL),
 (5,'Salata',NULL,'Most delicious salads you\'ll ever taste, wait did you know they\'re healthy as well.','0[8:00/14:00]',NULL,NULL,'C:/Users/StrettO/Documents/NetBeansProjects/TheServerComp/web/resources/images/salata.jpg',NULL);
/*!40000 ALTER TABLE `restaurant` ENABLE KEYS */;


--
-- Definition of table `restaurant_has_cuisine`
--

DROP TABLE IF EXISTS `restaurant_has_cuisine`;
CREATE TABLE `restaurant_has_cuisine` (
  `restaurant_id` int(11) NOT NULL,
  `cuisine_id` int(20) NOT NULL,
  PRIMARY KEY (`cuisine_id`,`restaurant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `restaurant_has_cuisine`
--

/*!40000 ALTER TABLE `restaurant_has_cuisine` DISABLE KEYS */;
INSERT INTO `restaurant_has_cuisine` (`restaurant_id`,`cuisine_id`) VALUES 
 (3,1),
 (3,2),
 (3,3),
 (3,4),
 (3,5),
 (3,6),
 (3,7);
/*!40000 ALTER TABLE `restaurant_has_cuisine` ENABLE KEYS */;


--
-- Definition of table `store_address`
--

DROP TABLE IF EXISTS `store_address`;
CREATE TABLE `store_address` (
  `store_address_id` int(11) NOT NULL,
  `unit_no` varchar(45) DEFAULT NULL,
  `streetame` varchar(45) DEFAULT NULL,
  `suburb` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `code` varchar(45) DEFAULT NULL,
  `gps_coord` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `restaurant_restaurant_id` int(11) NOT NULL,
  PRIMARY KEY (`store_address_id`,`restaurant_restaurant_id`),
  KEY `fk_store_address_restaurant1_idx` (`restaurant_restaurant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `store_address`
--

/*!40000 ALTER TABLE `store_address` DISABLE KEYS */;
/*!40000 ALTER TABLE `store_address` ENABLE KEYS */;


--
-- Definition of table `store_contact`
--

DROP TABLE IF EXISTS `store_contact`;
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

--
-- Dumping data for table `store_contact`
--

/*!40000 ALTER TABLE `store_contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `store_contact` ENABLE KEYS */;


--
-- Definition of table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `token_id` varchar(100) DEFAULT NULL,
  `person_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `_UNIQUE` (`username`),
  UNIQUE KEY `password_UNIQUE` (`password`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`user_id`,`username`,`password`,`token_id`,`person_id`) VALUES 
 (1,'root','root1234',NULL,NULL),
 (2,'kapiten.bm@','root',NULL,'5');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


--
-- Definition of table `vehicle`
--

DROP TABLE IF EXISTS `vehicle`;
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

--
-- Dumping data for table `vehicle`
--

/*!40000 ALTER TABLE `vehicle` DISABLE KEYS */;
/*!40000 ALTER TABLE `vehicle` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
