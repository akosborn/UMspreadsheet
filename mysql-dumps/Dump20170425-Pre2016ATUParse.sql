CREATE DATABASE  IF NOT EXISTS `spring-social` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `spring-social`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: spring-social
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sets`
--

DROP TABLE IF EXISTS `sets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sets` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `average_rating` double DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `show_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9j9m8ncl1a3f35jtmafwvi7gn` (`show_id`),
  CONSTRAINT `FK9j9m8ncl1a3f35jtmafwvi7gn` FOREIGN KEY (`show_id`) REFERENCES `shows` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sets`
--

LOCK TABLES `sets` WRITE;
/*!40000 ALTER TABLE `sets` DISABLE KEYS */;
INSERT INTO `sets` VALUES (1,NULL,'Set 1',1),(4,NULL,'Set 1',2),(10,NULL,'Set 1',3),(12,NULL,'Set 2',1),(13,NULL,'Encore',1),(14,NULL,'Set 2',3),(15,NULL,'Encore',3),(16,NULL,'Set 2',2),(17,NULL,'Encore',2),(21,NULL,'Set 1',6),(22,NULL,'Set 2',6),(23,NULL,'Encore',6),(24,NULL,'Set 1',7),(25,NULL,'Set 2',7),(26,NULL,'Encore',7),(27,NULL,'Set 1',8),(28,NULL,'Set 2',8),(29,NULL,'Encore',8),(30,NULL,'Set 1',9),(31,NULL,'Set 2',9),(32,NULL,'Encore',9),(33,NULL,'Set 1',10),(34,NULL,'Set 2',10),(35,NULL,'Encore',10),(36,NULL,'Set 1',11),(37,NULL,'Set 2',11),(38,NULL,'Encore',11),(39,NULL,'Set 1',12),(40,NULL,'Set 2',12),(41,NULL,'Encore',12),(42,NULL,'Set 1',13),(43,NULL,'Set 2',13),(44,NULL,'Encore',13),(45,NULL,'Set 1',14),(46,NULL,'Set 2',14),(47,NULL,'Encore',14),(48,NULL,'Set 1',15),(49,NULL,'Set 2',15),(50,NULL,'Encore',15),(51,NULL,'Set 1',16),(52,NULL,'Set 2',16),(53,NULL,'Encore',16),(54,NULL,'Set 1',17),(55,NULL,'Set 2',17),(56,NULL,'Encore',17);
/*!40000 ALTER TABLE `sets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `show_review`
--

DROP TABLE IF EXISTS `show_review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `show_review` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment` text,
  `reviewed_on` datetime DEFAULT NULL,
  `show_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkmvh7trt2i6pqvaqmm6ke8jnw` (`show_id`),
  KEY `FK3so4bkhur6gs0acr59ugkb2qs` (`user_id`),
  CONSTRAINT `FK3so4bkhur6gs0acr59ugkb2qs` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKkmvh7trt2i6pqvaqmm6ke8jnw` FOREIGN KEY (`show_id`) REFERENCES `shows` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `show_review`
--

LOCK TABLES `show_review` WRITE;
/*!40000 ALTER TABLE `show_review` DISABLE KEYS */;
/*!40000 ALTER TABLE `show_review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shows`
--

DROP TABLE IF EXISTS `shows`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shows` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `average_rating` double DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `notes` text,
  `state` varchar(255) DEFAULT NULL,
  `venue` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shows`
--

LOCK TABLES `shows` WRITE;
/*!40000 ALTER TABLE `shows` DISABLE KEYS */;
INSERT INTO `shows` VALUES (1,7.593137254882352,'Detroit','2017-02-04',NULL,'MI','The Fillmore'),(2,8.875,'Detroit','2017-02-03',NULL,'MI','The Fillmore'),(3,4.875,'Grand Rapids','2017-02-02',NULL,'MI','20 Monroe Live'),(6,7.426470588235294,'Tulsa','2017-04-19',NULL,'OK','Cain\'s Ballroom'),(7,NULL,'Austin','2017-04-20',NULL,'TX','Stubb\'s BBQ'),(8,8.583333333333334,'New Orleans','2017-04-21',NULL,'LA','Orpheum Theater'),(9,NULL,'Milwaukee','2017-04-01',NULL,'WI','Riverside Theater'),(10,NULL,'Dallas','2017-04-22',NULL,'TX','House of Blues'),(11,NULL,'Atlanta','2017-01-13',NULL,'GA','The Tabernacle'),(12,NULL,'Atlanta','2017-01-14',NULL,'GA','The Tabernacle'),(13,NULL,'Atlanta','2017-01-15',NULL,'GA','The Tabernacle'),(14,NULL,'Richmond','2017-01-20',NULL,'VA','The National'),(15,NULL,'Richmond','2017-01-21',NULL,'VA','The National'),(16,NULL,'Jim Thorpe','2017-01-22',NULL,'PA','Penn\'s Peak'),(17,NULL,'New Haven','2017-01-26',NULL,'CT','College Street Music Hall');
/*!40000 ALTER TABLE `shows` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `track`
--

DROP TABLE IF EXISTS `track`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `track` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `average_rating` double DEFAULT NULL,
  `fluid_segue` tinyint(1) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `segue` tinyint(1) DEFAULT NULL,
  `show_track_number` int(11) DEFAULT NULL,
  `song` varchar(255) DEFAULT NULL,
  `set_id` bigint(20) DEFAULT NULL,
  `show_id` bigint(20) DEFAULT NULL,
  `jam` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `length` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg9ek9fc44goou7n7xh1nse2pg` (`set_id`),
  KEY `FK6jukbvbkma0s9rbfotfgnpkr0` (`show_id`),
  CONSTRAINT `FK6jukbvbkma0s9rbfotfgnpkr0` FOREIGN KEY (`show_id`) REFERENCES `shows` (`id`),
  CONSTRAINT `FKg9ek9fc44goou7n7xh1nse2pg` FOREIGN KEY (`set_id`) REFERENCES `sets` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=292 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `track`
--

LOCK TABLES `track` WRITE;
/*!40000 ALTER TABLE `track` DISABLE KEYS */;
INSERT INTO `track` VALUES (1,9.583333333,0,NULL,1,1,'Gents',1,1,NULL,NULL,263),(2,10,1,NULL,0,2,'Plunger',1,1,NULL,NULL,775),(54,8.25,0,NULL,1,1,'There\'s No Crying In Mexico',4,2,NULL,NULL,245),(55,4.875,0,NULL,1,1,'Flamethrower',10,3,NULL,NULL,217),(61,NULL,0,NULL,0,2,'The Floor',10,3,NULL,NULL,391),(63,NULL,0,NULL,0,3,'Mulche\'s Odyssey',10,3,'','',767),(64,NULL,0,NULL,1,4,'Night Nurse',10,3,'','',789),(65,NULL,0,NULL,0,5,'Bright Lights, Big City',10,3,'','',782),(70,NULL,0,NULL,1,6,'Visions',10,3,'','',600),(71,NULL,0,NULL,1,7,'Ain\'t Too Proud to Beg',10,3,'','C',247),(72,NULL,0,NULL,0,8,'Red Tape',10,3,'','',571),(73,NULL,0,NULL,1,1,'Soul Food I',14,3,'','',411),(74,NULL,0,NULL,1,2,'Soul Food II',14,3,'','',652),(75,NULL,0,NULL,0,3,'Soul Food III',14,3,'','',606),(76,NULL,0,NULL,0,4,'Conduit',14,3,'','',753),(77,NULL,0,NULL,0,5,'Comfortably Numb',14,3,'','C',453),(78,NULL,0,NULL,0,6,'Partyin\' Peeps',14,3,'','',456),(79,NULL,0,NULL,0,7,'JaJunk',14,3,'','',943),(80,NULL,0,NULL,0,1,'Kimble',15,3,'','',290),(81,NULL,0,NULL,0,2,'Gulf Stream',15,3,'','',355),(82,9.5,0,'',0,2,'Attachments',4,2,'','',446),(83,NULL,0,'',1,3,'All In Time',4,2,'','',721),(84,NULL,0,NULL,0,4,'Day Nurse',4,2,'','',552),(85,NULL,0,NULL,0,5,'Educated Guess',4,2,'','',386),(86,NULL,0,NULL,1,6,'Alex\'s House',4,2,'','',490),(87,NULL,0,NULL,1,7,'Bridgeless',4,2,'','',780),(88,NULL,0,NULL,1,8,'No Comment',4,2,'','',335),(89,NULL,0,NULL,0,9,'Believe the Lie',4,2,'','',826),(90,NULL,0,NULL,0,1,'Miss Tinkle\'s Overture',16,2,'','',810),(91,NULL,0,NULL,0,2,'Remind Me',16,2,'','',718),(92,NULL,0,NULL,1,3,'Cemetery Walk',16,2,'','',457),(93,NULL,0,NULL,0,4,'Cemetery Walk II',16,2,'','',719),(94,NULL,0,NULL,0,5,'Out Of Order',16,2,'','',521),(95,NULL,0,NULL,1,6,'Wappy Sprayberry',16,2,'','',653),(96,NULL,0,'',0,7,'All In Time',16,2,'','',623),(99,5.25,0,NULL,0,3,'Dump City',1,1,'','',842),(100,7.25,0,NULL,1,4,'Miami Virtue',1,1,'','',467),(101,5.75,0,NULL,0,5,'Nemo',1,1,'','',297),(102,9,1,NULL,0,6,'Utopian Fir',1,1,'','',484),(103,NULL,0,NULL,1,7,'Speak Up',1,1,'','',584),(104,5.5,0,NULL,1,8,'Utopian Fir',1,1,'','',383),(105,7.25,0,NULL,0,9,'Let\'s Dance',1,1,'','C',333),(106,8.25,0,NULL,1,1,'Hurt Bird Bath',12,1,'','',434),(108,6.75,0,'original version',1,2,'Cut the Cable',12,1,'','',579),(109,8,0,'',0,3,'Hurt Bird Bath',12,1,'','',205),(110,NULL,0,'',0,4,'August',12,1,'','',839),(111,8,0,'',0,5,'Red',12,1,'','C',424),(112,6.75,0,'with Rhiannon (Fleetwood Mac) tease',1,6,'Comma Later',12,1,'','',659),(113,6.25,0,'',0,7,'Den',12,1,'','',534),(114,7.25,0,'\"lounge\" version',0,8,'Nopener',12,1,'','',485),(115,8.75,0,'',1,1,'Much Obliged',13,1,'','',442),(116,9.5,0,'',1,2,'Kula',13,1,'','',359),(117,8.75,0,'',0,1,'Room to Breathe',21,6,'','',485),(118,5.75,0,'',0,2,'In the Black',21,6,'','',323),(119,9.25,0,'',1,3,'Intentions Clear',21,6,'Funk','',760),(120,7.75,0,'',0,4,'Get in the Van',21,6,'','',421),(121,8.25,0,'',0,5,'Example 1',21,6,'','',536),(122,8,0,'',0,6,'The Fussy Dutchman',21,6,'','',552),(123,7.25,0,'',1,7,'Sociable Jimmy',21,6,'','',644),(124,5.5,0,'',0,8,'Make It Right',21,6,'','',416),(125,9.25,0,'',0,1,'Bright Lights, Big City',22,6,'Industrial','',776),(126,8,0,'',1,2,'The Linear',22,6,'Prog','',744),(127,6.5,0,'',1,3,'Booth Love',22,6,'Funk','',769),(128,8.25,0,'',1,4,'Nothing Too Fancy',22,6,'','',599),(129,NULL,0,'',1,1,'Glory',17,2,'','',278),(130,NULL,0,'',0,2,'Bridgeless',17,2,'','',329),(131,7.25,0,'',0,5,'She Came In Through The Bathroom Window',22,6,'','C',213),(132,6,0,'',1,6,'Little Gift',22,6,'','',201),(133,8,0,'',0,7,'Mulche\'s Odyssey',22,6,'','',757),(134,4.25,0,'',1,1,'Kimble',23,6,'','',327),(135,8.25,0,'',0,2,'Nothing Too Fancy',23,6,'','',430),(136,NULL,0,'',1,1,'Goonville',33,10,'','',131),(137,NULL,0,'',0,2,'Educated Guess',33,10,'','',361),(138,NULL,0,'',1,3,'Cemetery Walk',33,10,'','',1001),(139,NULL,0,'',0,4,'Cemetery Walk II',33,10,'','',779),(140,NULL,0,'',1,5,'Last Man Swerving',33,10,'','',594),(141,NULL,0,'',0,6,'Slacker',33,10,'','',777),(142,NULL,0,'',0,7,'Transdermal Celebration',33,10,'','C',245),(143,NULL,0,'with Pipeline (The Chantays) and Roundabout (Yes) teases',0,1,'The Floor',34,10,'','',832),(144,NULL,0,'',0,2,'Hindsight',34,10,'','',207),(145,NULL,0,'',0,3,'End of the Road',34,10,'','',269),(146,NULL,0,'',1,4,'Eat',34,10,'','',94),(147,NULL,0,'',1,5,'Time',34,10,'','C',388),(148,NULL,0,'',0,6,'Eat',34,10,'','',214),(149,NULL,0,'',1,7,'Wappy Sprayberry',34,10,'Industrial','',714),(150,NULL,0,'',0,8,'Partyin\' Peeps',34,10,'','',423),(151,NULL,0,'',1,9,'Upward',34,10,'','',357),(152,NULL,0,'',0,10,'In The Kitchen',34,10,'','',1051),(153,NULL,0,'',0,1,'Remind Me',35,10,'','',644),(154,NULL,0,'',1,1,'North Route',27,8,'','',264),(155,NULL,0,'',0,2,'All In Time',27,8,'Metal','',1129),(156,NULL,0,'',0,3,'Out Of Order',27,8,'','',577),(157,NULL,0,'',1,4,'Speak Up',27,8,'Funk','',993),(158,NULL,0,'',0,5,'Believe the Lie',27,8,'','',575),(159,8.25,0,'',0,6,'Great American',27,8,'','',690),(160,8.5,0,'',0,1,'Miss Tinkle\'s Overture',28,8,'Prog','',693),(161,NULL,0,'',0,2,'Red Tape',28,8,'','',835),(162,9,0,'with Sad But True (Metallica) and Rock With You (Michael Jackson) jams',0,3,'Utopian Fir',28,8,'Funk','',1164),(163,NULL,0,'with All In Time tease',0,4,'Wife Soup',28,8,'','',743),(164,NULL,0,'',1,5,'When the World Is Running Down You Make the Best of What\'s Still Around',28,8,'','',492),(165,NULL,0,'',0,6,'40\'s Theme',28,8,'','',802),(166,NULL,0,'',1,1,'Much Obliged',29,8,'','',480),(167,NULL,0,'',0,2,'Hajimemashite',29,8,'','',358),(168,NULL,0,'',1,1,'Bathing Digits',36,11,'','',111),(169,NULL,0,'',0,2,'Bad Friday',36,11,'','',494),(170,NULL,0,'',0,3,'Morning Song',36,11,'','',507),(171,NULL,0,'',1,4,'FF',36,11,'','',486),(172,NULL,0,'',1,5,'Last Man Swerving',36,11,'','',844),(173,NULL,0,'',0,6,'2x2',36,11,'','',1061),(174,NULL,0,'',1,7,'Wizard Burial Ground',36,11,'','',549),(175,NULL,0,'',0,8,'Sludge & Death',36,11,'','',412),(176,NULL,0,'',1,1,'Der Bluten Kat',37,11,'','',1089),(177,NULL,0,'',1,2,'Sociable Jimmy',37,11,'','',500),(178,NULL,1,'',0,3,'We\'re Going to War',37,11,'','C',346),(179,NULL,0,'',1,4,'Out Of Order',37,11,'','',452),(180,NULL,0,'with I Keep Forgettin\' (Michael McDonald) teases',0,5,'40\'s Theme',37,11,'','',627),(181,NULL,0,'with Stairway to Heaven (Led Zeppelin) tease',1,6,'Miami Virtue',37,11,'','',641),(182,NULL,0,'',0,7,'Power of Soul',37,11,'','C',601),(183,NULL,0,'with Third Stone from the Sun (Jimi Hendrix) teases',0,1,'Divisions',38,11,'','',1091),(184,NULL,0,'',1,1,'North Route',39,12,'','',314),(185,NULL,0,'',0,2,'Similar Skin',39,12,'','',1012),(186,NULL,0,'',1,3,'Wappy Sprayberry',39,12,'','',607),(187,NULL,0,'',0,4,'Hajimemashite',39,12,'','',391),(188,NULL,0,'',0,5,'Attachments',39,12,'','',507),(189,NULL,0,'with Jake on keys',0,6,'Deeper',39,12,'','',617),(190,NULL,0,'',0,7,'Miss Tinkle\'s Overture',39,12,'','',671),(191,NULL,0,'',1,1,'Plunger',40,12,'','',290),(192,NULL,0,'',1,2,'Glory',40,12,'','',236),(193,NULL,0,'',0,3,'Plunger',40,12,'','',177),(194,NULL,0,'with 25 or 6 to 4 (Chicago) tease',1,4,'The Triple Wide',40,12,'','',752),(195,NULL,0,'',1,5,'The Linear',40,12,'','',508),(196,NULL,0,'',1,6,'Push the Pig',40,12,'','',498),(197,NULL,0,'',1,7,'Hurt Bird Bath',40,12,'','',469),(198,NULL,0,'',1,8,'Full Frontal',40,12,'','',425),(199,NULL,0,'',0,9,'Hurt Bird Bath',40,12,'','',224),(200,NULL,0,'',0,10,'Piranhas',40,12,'','',385),(201,NULL,0,'',0,11,'Forty-Six & 2',40,12,'','C',553),(202,NULL,0,'',0,1,'Puppet String',41,12,'','',823),(203,NULL,0,'',0,1,'Rocker Part 2',42,13,'','',645),(204,NULL,0,'',0,2,'No DIablo',42,13,'','',355),(205,NULL,0,'',1,3,'Kabump',42,13,'','',714),(206,NULL,0,'',0,4,'2nd Self',42,13,'','',494),(207,NULL,0,'',0,5,'#5',42,13,'','',790),(208,NULL,0,'',0,6,'Cut Off',42,13,'','',457),(209,NULL,0,'with The Spirit of the Radio (Rush) tease',0,7,'Utopian Fir',42,13,'','',848),(210,NULL,0,'',0,1,'Bright Lights, Big City',43,13,'','',668),(211,NULL,0,'',1,2,'Cemetery Walk',43,13,'','',455),(212,NULL,0,'',0,3,'Cemetery Walk II',43,13,'','',771),(213,NULL,0,'',0,4,'The Song Remains the Same',43,13,'','C',379),(214,NULL,0,'',1,5,'1348',43,13,'','',569),(215,NULL,0,'',1,6,'In The Kitchen',43,13,'','',539),(216,NULL,0,'',1,7,'Higgins',43,13,'','',782),(217,NULL,0,'',0,8,'1348',43,13,'','',350),(218,NULL,0,'',0,1,'Pay the Snucka',44,13,'','',544),(219,NULL,0,'',0,1,'Andy\'s Last Beer',45,14,'','',494),(220,NULL,0,'',1,2,'Preamble',45,14,'','',13),(221,NULL,0,'',1,3,'Mantis Ghetts',45,14,'','',180),(222,NULL,0,'',1,4,'Mantis',45,14,'','',666),(223,NULL,0,'',0,5,'Thin Air',45,14,'','',633),(224,NULL,0,'',0,6,'Draconian',45,14,'','',1148),(225,NULL,0,'',0,7,'13 Days',45,14,'','',267),(226,NULL,0,'',0,8,'Slacker',45,14,'','',673),(227,NULL,0,'',1,1,'In the Flesh',46,14,'','C',185),(228,NULL,0,'',1,2,'Another Brick in the Wall',46,14,'','C',905),(229,NULL,0,'',0,3,'Ringo',46,14,'','',1199),(230,NULL,0,'',1,4,'Tribute to the Spinal Shaft',46,14,'','',760),(231,NULL,0,'',1,5,'The Fuzz',46,14,'','',448),(232,NULL,0,'',1,6,'The Triple Wide',46,14,'','',517),(233,NULL,0,'',1,7,'Final Word',46,14,'','',331),(234,NULL,0,'',0,8,'Mantis',46,14,'','',551),(235,NULL,0,'',1,1,'Glory',47,14,'','',262),(236,NULL,0,'',0,2,'Kid Charlemagne',47,14,'','C',433),(237,NULL,0,'',1,1,'Conduit',48,15,'','',727),(238,NULL,0,'',0,2,'Walletsworth',48,15,'','',433),(239,NULL,0,'',0,3,'Nemo',48,15,'','',1012),(240,NULL,0,'with Crazy Train (Ozzy Osbourne) tease',1,4,'Prowler',48,15,'','',346),(241,NULL,0,'',0,5,'Wife Soup',48,15,'','',666),(242,NULL,0,'',1,6,'Speak Up',48,15,'','',599),(243,NULL,0,'',0,7,'Live and Let Die',48,15,'','C',322),(244,NULL,1,'',0,1,'The Crooked One',49,15,'','',666),(245,NULL,0,'',0,2,'Make It Right',49,15,'','',435),(246,NULL,1,'',0,3,'The Linear',49,15,'','',670),(247,NULL,0,'',0,4,'Educated Guess',49,15,'','',448),(248,NULL,0,'with Entrance of the Gladiators (Julius Fučík) tease',1,5,'JaJunk',49,15,'','',484),(249,NULL,0,'',0,6,'Mail Package',49,15,'','',542),(250,NULL,0,'',0,7,'The Fussy Dutchman',49,15,'','',560),(251,NULL,0,'',1,8,'Women Wine and Song',49,15,'','',412),(252,NULL,0,'',0,9,'JaJunk',49,15,'','',477),(253,NULL,0,'',0,1,'Phil\'s Farm',50,15,'','',776),(254,NULL,0,'with Brendan and Jake on acoustics',0,1,'Gone for Good',51,16,'','',334),(255,NULL,0,'',0,2,'Where Is My Mind?',51,16,'','C',259),(256,NULL,0,'with Brendan and Jake on acoustics',1,3,'Great American',51,16,'','',380),(257,NULL,0,'with Brendan and Jake on acoustics',1,4,'The Pequod',51,16,'','',154),(258,NULL,0,'with Brendan and Jake on acoustics',0,5,'Great American',51,16,'','',153),(259,NULL,0,'',1,6,'Wappy Sprayberry',51,16,'','',725),(260,NULL,0,'',0,7,'Believe the Lie',51,16,'','',688),(261,NULL,0,'',1,8,'Nothing Too Fancy',51,16,'','',639),(262,NULL,0,'',1,9,'Syncopated Strangers',51,16,'','',186),(263,NULL,0,'',0,10,'Dump City',51,16,'','',629),(264,NULL,0,'',1,1,'2x2',52,16,'','',458),(265,NULL,0,'',1,2,'Syncopated Strangers',52,16,'','',239),(266,NULL,0,'',0,3,'2x2',52,16,'','',319),(267,NULL,0,'',0,4,'August',52,16,'','',787),(268,NULL,0,'',1,5,'Robot World',52,16,'','',759),(269,NULL,0,'with Norwegian Wood (The Beatles) jam',1,6,'Resolution',52,16,'','',715),(270,NULL,0,'',0,7,'Bad Poker',52,16,'','',440),(271,NULL,0,'with Divisions intro\r\n',0,8,'All In Time',52,16,'','',1273),(272,NULL,0,'with Crazy Train (Ozzy Osbourne) jam',1,1,'Miss Gradenko',53,16,'','',470),(273,NULL,0,'',0,2,'Nothing Too Fancy',53,16,'','',70),(274,NULL,0,'',1,1,'Nipple Trix',54,17,'','',178),(275,NULL,0,'',0,2,'Slacker',54,17,'','',649),(276,NULL,0,'',1,3,'Wellwishers',54,17,'','',707),(277,NULL,0,'',1,4,'Sociable Jimmy',54,17,'','',570),(278,NULL,0,'',1,5,'Sweetness',54,17,'','',228),(279,NULL,0,'',0,6,'Mad Love',54,17,'','',482),(280,NULL,0,'with Joshua Redman on saxophone',1,7,'Speak Up',54,17,'','',709),(281,NULL,0,'with Joshua Redman on saxophone',0,8,'Booth Love',54,17,'','',627),(282,NULL,0,'with Joshua Redman on saxophone',1,1,'Der Bluten Kat',55,17,'','',919),(283,NULL,0,'with Joshua Redman on saxophone',1,2,'Final Word',55,17,'','',322),(284,NULL,0,'with Joshua Redman on saxophone',0,3,'Der Bluten Kat',55,17,'','',263),(285,NULL,0,'with Joshua Redman on saxophone; with Working Day and Night (Michael Jackson) teases',1,4,'Day Nurse',55,17,'','',794),(286,NULL,0,'with Joshua Redman on saxophone',0,5,'Wife Soup',55,17,'','',785),(287,NULL,0,'with Joshua Redman on saxophone',0,6,'Dear Lord',55,17,'','',459),(288,NULL,0,'\"dub\" version',0,7,'Breathe',55,17,'','C',835),(289,NULL,0,'just Joel on piano',0,1,'Jessica',56,17,'','C',410),(290,NULL,0,'',1,2,'Uncommon',56,17,'','',186),(291,NULL,0,'',0,3,'Down Under',56,17,'','C',228);
/*!40000 ALTER TABLE `track` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER after_track_update
AFTER UPDATE ON track
FOR EACH ROW
BEGIN
	UPDATE shows s
	SET s.average_rating = (
		SELECT AVG(track.average_rating)
        FROM track
        WHERE track.show_id = s.id
        GROUP BY track.show_id)
    WHERE s.id = NEW.show_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `track_review`
--

DROP TABLE IF EXISTS `track_review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `track_review` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment` text,
  `reviewed_on` datetime NOT NULL,
  `score` decimal(4,2) DEFAULT NULL,
  `track_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7dqviea2x4ak2kfu61w0ej81r` (`track_id`),
  KEY `FKo11d6i1p3edfjjqo9pe9ly3ql` (`user_id`),
  CONSTRAINT `FK7dqviea2x4ak2kfu61w0ej81r` FOREIGN KEY (`track_id`) REFERENCES `track` (`id`),
  CONSTRAINT `FKo11d6i1p3edfjjqo9pe9ly3ql` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `track_review`
--

LOCK TABLES `track_review` WRITE;
/*!40000 ALTER TABLE `track_review` DISABLE KEYS */;
INSERT INTO `track_review` VALUES (10,NULL,'2017-04-19 12:32:10',9.00,1,32),(11,NULL,'2017-02-18 23:14:45',8.25,54,32),(12,NULL,'2017-02-18 23:14:21',9.75,55,32),(13,NULL,'2017-04-18 19:58:12',10.00,1,31),(14,NULL,'2017-04-18 19:58:16',10.00,2,31),(15,NULL,'2017-04-18 19:35:48',9.75,1,33),(16,NULL,'2017-04-20 23:14:12',5.75,101,32),(17,NULL,'2017-04-20 23:14:16',7.25,100,32),(18,NULL,'2017-04-23 23:41:28',5.25,99,32),(20,NULL,'2017-04-20 23:14:24',9.00,102,32),(21,NULL,'2017-04-20 23:14:27',5.50,104,32),(22,NULL,'2017-04-20 23:14:30',7.25,105,32),(23,NULL,'2017-04-20 23:14:35',7.25,114,32),(25,NULL,'2017-04-20 23:14:38',6.75,108,32),(26,NULL,'2017-04-20 23:14:40',8.25,106,32),(27,NULL,'2017-04-20 23:14:41',8.00,109,32),(28,NULL,'2017-04-20 23:14:44',6.75,112,32),(29,NULL,'2017-04-20 23:14:46',8.00,111,32),(30,NULL,'2017-04-20 23:14:50',6.25,113,32),(31,NULL,'2017-04-20 23:14:53',8.75,115,32),(32,NULL,'2017-04-20 23:14:57',9.50,116,32),(33,NULL,'2017-04-20 23:57:51',9.50,82,32),(34,NULL,'2017-04-24 00:54:38',8.00,122,32),(35,NULL,'2017-04-24 00:54:40',6.50,127,32),(36,NULL,'2017-04-24 01:57:13',7.25,131,32),(37,NULL,'2017-04-24 00:54:45',9.25,119,32),(38,NULL,'2017-04-24 00:54:48',5.75,118,32),(39,NULL,'2017-04-24 00:54:51',8.25,135,32),(40,NULL,'2017-04-24 01:57:25',7.75,120,32),(41,NULL,'2017-04-24 00:54:53',8.25,121,32),(42,NULL,'2017-04-24 01:57:20',8.75,117,32),(43,NULL,'2017-04-24 00:54:57',6.00,132,32),(44,NULL,'2017-04-24 00:54:59',7.25,123,32),(45,NULL,'2017-04-24 00:55:00',5.50,124,32),(46,NULL,'2017-04-24 00:55:13',9.25,125,32),(47,NULL,'2017-04-24 00:55:03',8.00,126,32),(48,NULL,'2017-04-24 00:55:06',8.25,128,32),(49,NULL,'2017-04-24 00:55:08',8.00,133,32),(50,NULL,'2017-04-24 00:55:10',4.25,134,32),(51,NULL,'2017-04-24 19:40:48',0.00,55,34),(52,NULL,'2017-04-24 19:54:47',9.00,162,31),(53,NULL,'2017-04-24 19:53:37',8.25,159,31),(54,NULL,'2017-04-24 19:54:07',8.50,160,31);
/*!40000 ALTER TABLE `track_review` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER after_track_review_insert
AFTER INSERT ON track_review
FOR EACH ROW
BEGIN
	UPDATE track t
	SET t.average_rating = (
		SELECT AVG(track_review.score)
        FROM track_review
        WHERE track_review.track_id = t.id
        GROUP BY track_review.track_id)
    WHERE t.id = NEW.track_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER after_track_review_update
AFTER UPDATE ON track_review
FOR EACH ROW
BEGIN
	UPDATE track t
	SET t.average_rating = (
		SELECT AVG(track_review.score)
        FROM track_review
        WHERE track_review.track_id = t.id
        GROUP BY track_review.track_id)
    WHERE t.id = NEW.track_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `track_review_AFTER_DELETE` 
AFTER DELETE ON `track_review` 
FOR EACH ROW
BEGIN
	UPDATE track t
    SET t.average_rating = (
		SELECT AVG(track_review.score)
        FROM track_review
        WHERE track_review.track_id = t.id
        GROUP BY track_review.track_id)
	WHERE t.id = OLD.track_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `userconnection`
--

DROP TABLE IF EXISTS `userconnection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userconnection` (
  `userId` varchar(255) NOT NULL,
  `providerId` varchar(255) NOT NULL,
  `providerUserId` varchar(255) NOT NULL,
  `rank` int(11) NOT NULL,
  `displayName` varchar(255) DEFAULT NULL,
  `profileUrl` varchar(512) DEFAULT NULL,
  `imageUrl` varchar(512) DEFAULT NULL,
  `accessToken` varchar(512) NOT NULL,
  `secret` varchar(512) DEFAULT NULL,
  `refreshToken` varchar(512) DEFAULT NULL,
  `expireTime` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`userId`,`providerId`,`providerUserId`),
  UNIQUE KEY `UserConnectionRank` (`userId`,`providerId`,`rank`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userconnection`
--

LOCK TABLES `userconnection` WRITE;
/*!40000 ALTER TABLE `userconnection` DISABLE KEYS */;
INSERT INTO `userconnection` VALUES ('100519502399947188017','google','100519502399947188017',1,'Andrew Osborn','https://plus.google.com/100519502399947188017','https://lh6.googleusercontent.com/-wpQGICEUKeA/AAAAAAAAAAI/AAAAAAAAG5E/p6Lq6WAtNqI/photo.jpg?sz=50','ya29.GlwABCmnPXS_54tQGP9OYIBRWyYDkAZ30c7vDPzslY8R09MejtN4Z7dg8wELEVAxDg4z_bC3bYtys1_Q1IQJT6_Hx_TBw8cST_xNYF9FPPyOVHGQ-wm4Fa921-3y1g',NULL,NULL,1488348172903);
/*!40000 ALTER TABLE `userconnection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `is_not_banned` tinyint(1) NOT NULL,
  `is_not_suspended` tinyint(1) NOT NULL,
  `joined_on` datetime DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`),
  UNIQUE KEY `UK_6efs5vmce86ymf5q7lmvn2uuf` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (31,'aaronlebo8@gmail.com',1,1,'2017-02-18 19:15:46','$2a$10$ck8tn4kf8ejFhqqsJsJX0eeKAQ/OjpAy6E/OhE5/7ewucaq58ir/W',NULL,'EEslacker'),(32,'andrewosborn93@gmail.com',1,1,'2017-02-18 23:05:02','$2a$10$0rFin8SAR5t69CONiFeBTeA4aqLg4zJkkXU0398nnSKVhSrpEDBdm','100519502399947188017','andrew4bama'),(33,'Jeffpechenik@gmail.com',1,1,'2017-04-18 19:35:07','$2a$10$Ed0V6.4gDLhOCL/uMT5xQuCNfZhkHNGi3m8H8eB.8aQCIGspzLeFG',NULL,'oliverbirdseye'),(34,'lukebelke25@gmail.com',1,1,'2017-04-24 19:39:07','$2a$10$TufrG82/1gZs8hn.PZ1AQ.WPcJcmuMKoKWsRROFySEJWXe8bc5Bli',NULL,'MooseMuses');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKj6m8fwv7oqv74fcehir1a9ffy` (`role_id`),
  CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKj6m8fwv7oqv74fcehir1a9ffy` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (31,1),(32,1),(33,1),(34,1);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wormblog_posts`
--

DROP TABLE IF EXISTS `wormblog_posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wormblog_posts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `body` text,
  `posted_on` datetime DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `author_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK25l96q0gw6wcvyv75kw8nycjo` (`author_id`),
  CONSTRAINT `FK25l96q0gw6wcvyv75kw8nycjo` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wormblog_posts`
--

LOCK TABLES `wormblog_posts` WRITE;
/*!40000 ALTER TABLE `wormblog_posts` DISABLE KEYS */;
INSERT INTO `wormblog_posts` VALUES (5,'<p><img src=\"https://i.imgur.com/2ppOi3x.gif\" alt=\"test\" width=\"288\" height=\"239\" /></p>','2017-04-23 17:03:29','imgur-test','Imgur Test',32),(6,'<p><iframe src=\"//www.youtube.com/embed/TOPXbgSte58\" width=\"560\" height=\"314\" allowfullscreen=\"allowfullscreen\"></iframe></p>','2017-04-23 17:24:35','youtube-test','YouTube Test',32);
/*!40000 ALTER TABLE `wormblog_posts` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-25  1:33:46
