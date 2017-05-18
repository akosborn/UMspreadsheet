CREATE DATABASE  IF NOT EXISTS `umspreadsheet-dev` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `umspreadsheet-dev`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: umspreadsheet-dev
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
-- Table structure for table `privilege`
--

DROP TABLE IF EXISTS `privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `privilege` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `privilege`
--

LOCK TABLES `privilege` WRITE;
/*!40000 ALTER TABLE `privilege` DISABLE KEYS */;
INSERT INTO `privilege` VALUES (1,'ROLE_ADMIN_PRIVILEGE'),(2,'ROLE_MANAGE_SHOWS_PRIVILEGE'),(3,'ROLE_MANAGE_USERS_PRIVILEGE'),(4,'ROLE_SEND_EMAIL_PRIVILEGE'),(5,'ROLE_POST_TO_WORMBLOG_PRIVILEGE'),(6,'ROLE_MOD_PRIVILEGE'),(7,'ROLE_USER_PRIVILEGE');
/*!40000 ALTER TABLE `privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_MOD'),(3,'ROLE_USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles_privileges`
--

DROP TABLE IF EXISTS `roles_privileges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles_privileges` (
  `role_id` bigint(20) NOT NULL,
  `privilege_id` bigint(20) NOT NULL,
  KEY `FK5yjwxw2gvfyu76j3rgqwo685u` (`privilege_id`),
  KEY `FK9h2vewsqh8luhfq71xokh4who` (`role_id`),
  CONSTRAINT `FK5yjwxw2gvfyu76j3rgqwo685u` FOREIGN KEY (`privilege_id`) REFERENCES `privilege` (`id`),
  CONSTRAINT `FK9h2vewsqh8luhfq71xokh4who` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles_privileges`
--

LOCK TABLES `roles_privileges` WRITE;
/*!40000 ALTER TABLE `roles_privileges` DISABLE KEYS */;
INSERT INTO `roles_privileges` VALUES (1,1),(1,6),(1,2),(1,3),(1,4),(1,5),(2,6),(2,2),(2,5),(3,7);
/*!40000 ALTER TABLE `roles_privileges` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=586 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sets`
--

LOCK TABLES `sets` WRITE;
/*!40000 ALTER TABLE `sets` DISABLE KEYS */;
INSERT INTO `sets` VALUES (469,NULL,'Set 1',157),(470,NULL,'Set 2',157),(471,NULL,'Encore',157),(472,NULL,'Set 1',158),(473,NULL,'Set 2',158),(474,NULL,'Encore',158),(475,NULL,'Set 1',159),(476,NULL,'Set 2',159),(477,NULL,'Encore',159),(478,NULL,'Set 1',160),(479,NULL,'Set 2',160),(480,NULL,'Encore',160),(481,NULL,'Set 1',161),(482,NULL,'Set 2',161),(483,NULL,'Encore',161),(484,NULL,'Set 1',162),(485,NULL,'Set 2',162),(486,NULL,'Encore',162),(487,NULL,'Set 1',163),(488,NULL,'Set 2',163),(489,NULL,'Encore',163),(490,NULL,'Set 1',164),(491,NULL,'Set 2',164),(492,NULL,'Encore',164),(493,NULL,'Set 1',165),(494,NULL,'Set 2',165),(495,NULL,'Encore',165),(496,NULL,'Set 1',166),(497,NULL,'Set 2',166),(498,NULL,'Encore',166),(499,NULL,'Set 1',167),(500,NULL,'Set 2',167),(501,NULL,'Encore',167),(502,NULL,'Set 1',168),(503,NULL,'Set 2',168),(504,NULL,'Encore',168),(505,NULL,'Set 1',169),(506,NULL,'Set 2',169),(507,NULL,'Encore',169),(508,NULL,'Set 1',170),(509,NULL,'Set 2',170),(510,NULL,'Encore',170),(511,NULL,'Set 1',171),(512,NULL,'Set 2',171),(513,NULL,'Encore',171),(514,NULL,'Set 1',172),(515,NULL,'Set 2',172),(516,NULL,'Encore',172),(517,NULL,'Set 1',173),(518,NULL,'Set 2',173),(519,NULL,'Encore',173),(520,NULL,'Set 1',174),(521,NULL,'Set 2',174),(522,NULL,'Encore',174),(523,NULL,'Set 1',175),(524,NULL,'Set 2',175),(525,NULL,'Encore',175),(526,NULL,'Set 1',176),(527,NULL,'Set 2',176),(528,NULL,'Encore',176),(529,NULL,'Set 1',177),(530,NULL,'Set 2',177),(531,NULL,'Encore',177),(532,NULL,'Set 1',178),(533,NULL,'Set 2',178),(534,NULL,'Encore',178),(535,NULL,'Set 1',179),(536,NULL,'Set 2',179),(537,NULL,'Encore',179),(538,NULL,'Set 1',180),(539,NULL,'Set 2',180),(540,NULL,'Encore',180),(541,NULL,'Set 1',181),(542,NULL,'Set 2',181),(543,NULL,'Encore',181),(544,NULL,'Set 1',182),(545,NULL,'Set 2',182),(546,NULL,'Encore',182),(547,NULL,'Set 1',183),(548,NULL,'Set 2',183),(549,NULL,'Encore',183),(550,NULL,'Set 1',184),(551,NULL,'Set 2',184),(552,NULL,'Encore',184),(553,NULL,'Set 1',185),(554,NULL,'Set 2',185),(555,NULL,'Encore',185),(556,NULL,'Set 1',186),(557,NULL,'Set 2',186),(558,NULL,'Encore',186),(559,NULL,'Set 1',187),(560,NULL,'Set 2',187),(561,NULL,'Encore',187),(562,NULL,'Set 1',188),(563,NULL,'Set 2',188),(564,NULL,'Encore',188),(565,NULL,'Set 1',189),(566,NULL,'Set 2',189),(567,NULL,'Encore',189),(568,NULL,'Set 1',190),(569,NULL,'Set 2',190),(570,NULL,'Encore',190),(571,NULL,'Set 1',191),(572,NULL,'Set 2',191),(573,NULL,'Encore',191),(574,NULL,'Set 1',192),(575,NULL,'Set 2',192),(576,NULL,'Encore',192),(577,NULL,'Set 1',193),(578,NULL,'Set 2',193),(579,NULL,'Encore',193),(580,NULL,'Set 1',194),(581,NULL,'Set 2',194),(582,NULL,'Encore',194),(583,NULL,'Set 1',195),(584,NULL,'Set 2',195),(585,NULL,'Encore',195);
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
  KEY `FK710jkeieblxjugpvc9gxe0d9t` (`user_id`),
  CONSTRAINT `FK710jkeieblxjugpvc9gxe0d9t` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
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
) ENGINE=InnoDB AUTO_INCREMENT=196 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shows`
--

LOCK TABLES `shows` WRITE;
/*!40000 ALTER TABLE `shows` DISABLE KEYS */;
INSERT INTO `shows` VALUES (157,NULL,'Atlanta','2017-01-13',NULL,'GA','The Tabernacle'),(158,NULL,'Atlanta','2017-01-14',NULL,'GA','The Tabernacle'),(159,NULL,'Atlanta','2017-01-15',NULL,'GA','The Tabernacle'),(160,NULL,'Richmond','2017-01-20',NULL,'VA','The National'),(161,NULL,'Richmond','2017-01-21',NULL,'VA','The National'),(162,NULL,'Jim Thorpe','2017-01-22',NULL,'PA','Penn\'s Peak'),(163,NULL,'New Haven','2017-01-26',NULL,'CT','College Street Music Hall'),(164,NULL,'Portland','2017-01-27',NULL,'ME','State Theatre'),(165,NULL,'Albany','2017-01-28',NULL,'NY','Palace Theatre'),(166,NULL,'Rochester','2017-01-29',NULL,'NY','Anthology'),(167,NULL,'Grand Rapids','2017-02-02',NULL,'MI','20 Monroe Live'),(168,NULL,'Detroit','2017-02-03',NULL,'MI','The Fillmore'),(169,NULL,'Detroit','2017-02-04',NULL,'MI','The Fillmore'),(170,NULL,'Asheville','2017-02-17',NULL,'NC','Exploreasheville.com Arena'),(171,NULL,'Asheville','2017-02-18',NULL,'NC','Exploreasheville.com Arena'),(172,NULL,'Asheville','2017-02-19',NULL,'NC','The Orange Peel'),(173,NULL,'Sandpoint','2017-03-01',NULL,'ID','The Hive'),(174,NULL,'Sandpoint','2017-03-02',NULL,'ID','The Hive'),(175,NULL,'Missoula','2017-03-03',NULL,'MT','The Wilma Theatre'),(176,NULL,'Missoula','2017-03-04',NULL,'MT','The Wilma Theatre'),(177,NULL,'Salt Lake City','2017-03-09',NULL,'UT','The Depot'),(178,NULL,'Stateline','2017-03-10',NULL,'NV','MontBleu Resort'),(179,NULL,'Oakland','2017-03-11',NULL,'CA','The Fox Theater'),(180,NULL,'San Rafael','2017-03-12',NULL,'CA','Terrapin Crossroads'),(181,NULL,'Tempe','2017-03-16',NULL,'AZ','The Marquee Theatre'),(182,NULL,'Las Vegas','2017-03-17',NULL,'NV','Brooklyn Bowl Las Vegas'),(183,NULL,'Los Angeles','2017-03-18',NULL,'CA','The Wiltern'),(184,NULL,'San Diego','2017-03-19',NULL,'CA','The Observatory North Park'),(185,NULL,'Milwaukee','2017-03-30',NULL,'WI','Riverside Theater'),(186,NULL,'Milwaukee','2017-03-31',NULL,'WI','Riverside Theater'),(187,NULL,'Milwaukee','2017-04-01',NULL,'WI','Riverside Theater'),(188,NULL,'Tulsa','2017-04-19',NULL,'OK','Cain\'s Ballroom'),(189,NULL,'Austin','2017-04-20',NULL,'TX','Stubb\'s BBQ'),(190,NULL,'New Orleans','2017-04-21',NULL,'LA','Orpheum Theater'),(191,NULL,'Dallas','2017-04-22',NULL,'TX','House of Blues'),(192,NULL,'St. Petersburg','2017-04-26',NULL,'FL','Jannus Live'),(193,NULL,'Orlando','2017-04-27',NULL,'FL','House of Blues'),(194,NULL,'Miami Beach','2017-04-28',NULL,'FL','The Fillmore Miami Beach'),(195,NULL,'St. Augustine','2017-04-29',NULL,'FL','St. Augustine Amphitheatre');
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
  `jam` varchar(255) DEFAULT NULL,
  `length` bigint(20) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `segue` tinyint(1) DEFAULT NULL,
  `show_track_number` int(11) DEFAULT NULL,
  `song` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `set_id` bigint(20) DEFAULT NULL,
  `show_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg9ek9fc44goou7n7xh1nse2pg` (`set_id`),
  KEY `FK6jukbvbkma0s9rbfotfgnpkr0` (`show_id`),
  CONSTRAINT `FK6jukbvbkma0s9rbfotfgnpkr0` FOREIGN KEY (`show_id`) REFERENCES `shows` (`id`),
  CONSTRAINT `FKg9ek9fc44goou7n7xh1nse2pg` FOREIGN KEY (`set_id`) REFERENCES `sets` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3401 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `track`
--

LOCK TABLES `track` WRITE;
/*!40000 ALTER TABLE `track` DISABLE KEYS */;
INSERT INTO `track` VALUES (2721,NULL,0,NULL,NULL,NULL,1,NULL,'Bathing Digits',NULL,469,157),(2722,NULL,0,NULL,NULL,NULL,0,NULL,'Bad Friday',NULL,469,157),(2723,NULL,0,NULL,NULL,NULL,0,NULL,'Morning Song',NULL,469,157),(2724,NULL,0,NULL,NULL,NULL,1,NULL,'FF',NULL,469,157),(2725,NULL,0,NULL,NULL,NULL,1,NULL,'Last Man Swerving',NULL,469,157),(2726,NULL,0,NULL,NULL,NULL,0,NULL,'2x2',NULL,469,157),(2727,NULL,0,NULL,NULL,NULL,1,NULL,'Wizard Burial Ground',NULL,469,157),(2728,NULL,0,NULL,NULL,NULL,0,NULL,'Sludge & Death',NULL,469,157),(2729,NULL,0,NULL,NULL,NULL,1,NULL,'Der Bluten Kat',NULL,470,157),(2730,NULL,0,NULL,NULL,NULL,1,NULL,'Sociable Jimmy',NULL,470,157),(2731,NULL,0,NULL,NULL,NULL,0,NULL,'Der Bluten Kat',NULL,470,157),(2732,NULL,0,NULL,NULL,NULL,1,NULL,'We\'re Going to War',NULL,470,157),(2733,NULL,0,NULL,NULL,NULL,1,NULL,'Out Of Order',NULL,470,157),(2734,NULL,0,NULL,NULL,'with I Keep Forgettin\' (Michael McDonald) teases',0,NULL,'40\'s Theme',NULL,470,157),(2735,NULL,0,NULL,NULL,'with Stairway to Heaven (Led Zeppelin) tease',0,NULL,'Miami Virtue',NULL,470,157),(2736,NULL,0,NULL,NULL,NULL,0,NULL,'Power of Soul',NULL,470,157),(2737,NULL,0,NULL,NULL,'with Third Stone from the Sun (Jimi Hendrix) teases',0,NULL,'Divisions',NULL,471,157),(2738,NULL,0,NULL,NULL,NULL,1,NULL,'North Route',NULL,472,158),(2739,NULL,0,NULL,NULL,NULL,0,NULL,'Similar Skin',NULL,472,158),(2740,NULL,0,NULL,NULL,NULL,1,NULL,'Wappy Sprayberry',NULL,472,158),(2741,NULL,0,NULL,NULL,NULL,0,NULL,'Hajimemashite',NULL,472,158),(2742,NULL,0,NULL,NULL,NULL,0,NULL,'Attachments',NULL,472,158),(2743,NULL,0,NULL,NULL,'with Jake on keys',0,NULL,'Deeper',NULL,472,158),(2744,NULL,0,NULL,NULL,NULL,0,NULL,'Miss Tinkle\'s Overture',NULL,472,158),(2745,NULL,0,NULL,NULL,NULL,1,NULL,'Plunger',NULL,473,158),(2746,NULL,0,NULL,NULL,NULL,1,NULL,'Glory',NULL,473,158),(2747,NULL,0,NULL,NULL,NULL,0,NULL,'Plunger',NULL,473,158),(2748,NULL,0,NULL,NULL,'with 25 or 6 to 4 (Chicago) tease',0,NULL,'The Triple Wide',NULL,473,158),(2749,NULL,0,NULL,NULL,NULL,1,NULL,'The Linear',NULL,473,158),(2750,NULL,0,NULL,NULL,NULL,1,NULL,'Push the Pig',NULL,473,158),(2751,NULL,0,NULL,NULL,NULL,1,NULL,'Hurt Bird Bath',NULL,473,158),(2752,NULL,0,NULL,NULL,NULL,1,NULL,'Full Frontal',NULL,473,158),(2753,NULL,0,NULL,NULL,NULL,0,NULL,'Hurt Bird Bath',NULL,473,158),(2754,NULL,0,NULL,NULL,NULL,0,NULL,'Piranhas',NULL,473,158),(2755,NULL,0,NULL,NULL,NULL,0,NULL,'Forty-Six & 2',NULL,473,158),(2756,NULL,0,NULL,NULL,'with Soul Food II tease',0,NULL,'Puppet String',NULL,474,158),(2757,NULL,0,NULL,NULL,NULL,0,NULL,'Rocker Part 2',NULL,475,159),(2758,NULL,0,NULL,NULL,NULL,0,NULL,'No Diablo',NULL,475,159),(2759,NULL,0,NULL,NULL,NULL,1,NULL,'Kabump',NULL,475,159),(2760,NULL,0,NULL,NULL,NULL,0,NULL,'2nd Self',NULL,475,159),(2761,NULL,0,NULL,NULL,NULL,0,NULL,'#5',NULL,475,159),(2762,NULL,0,NULL,NULL,NULL,0,NULL,'Cut Off',NULL,475,159),(2763,NULL,0,NULL,NULL,'with The Spirit of Radio (Rush) tease',0,NULL,'Utopian Fir',NULL,475,159),(2764,NULL,0,NULL,NULL,NULL,0,NULL,'Bright Lights, Big City',NULL,476,159),(2765,NULL,0,NULL,NULL,NULL,1,NULL,'Cemetery Walk',NULL,476,159),(2766,NULL,0,NULL,NULL,NULL,0,NULL,'Cemetery Walk II',NULL,476,159),(2767,NULL,0,NULL,NULL,NULL,0,NULL,'The Song Remains the Same',NULL,476,159),(2768,NULL,0,NULL,NULL,NULL,1,NULL,'1348',NULL,476,159),(2769,NULL,0,NULL,NULL,NULL,1,NULL,'In The Kitchen',NULL,476,159),(2770,NULL,0,NULL,NULL,NULL,1,NULL,'Higgins',NULL,476,159),(2771,NULL,0,NULL,NULL,NULL,0,NULL,'1348',NULL,476,159),(2772,NULL,0,NULL,NULL,NULL,0,NULL,'Pay the Snucka',NULL,477,159),(2773,NULL,0,NULL,NULL,NULL,0,NULL,'Andy\'s Last Beer',NULL,478,160),(2774,NULL,0,NULL,NULL,NULL,1,NULL,'Preamble',NULL,478,160),(2775,NULL,0,NULL,NULL,NULL,1,NULL,'Mantis Ghetts',NULL,478,160),(2776,NULL,0,NULL,NULL,NULL,1,NULL,'Mantis',NULL,478,160),(2777,NULL,0,NULL,NULL,NULL,0,NULL,'Thin Air',NULL,478,160),(2778,NULL,0,NULL,NULL,NULL,0,NULL,'Draconian',NULL,478,160),(2779,NULL,0,NULL,NULL,NULL,0,NULL,'13 Days',NULL,478,160),(2780,NULL,0,NULL,NULL,NULL,0,NULL,'Slacker',NULL,478,160),(2781,NULL,0,NULL,NULL,NULL,1,NULL,'In the Flesh',NULL,479,160),(2782,NULL,0,NULL,NULL,NULL,1,NULL,'Another Brick in the Wall',NULL,479,160),(2783,NULL,0,NULL,NULL,NULL,0,NULL,'Ringo',NULL,479,160),(2784,NULL,0,NULL,NULL,NULL,1,NULL,'Tribute to the Spinal Shaft',NULL,479,160),(2785,NULL,0,NULL,NULL,NULL,1,NULL,'The Fuzz',NULL,479,160),(2786,NULL,0,NULL,NULL,NULL,1,NULL,'The Triple Wide',NULL,479,160),(2787,NULL,0,NULL,NULL,NULL,1,NULL,'Final Word',NULL,479,160),(2788,NULL,0,NULL,NULL,NULL,0,NULL,'Mantis',NULL,479,160),(2789,NULL,0,NULL,NULL,NULL,1,NULL,'Glory',NULL,480,160),(2790,NULL,0,NULL,NULL,NULL,0,NULL,'Kid Charlemagne',NULL,480,160),(2791,NULL,0,NULL,NULL,NULL,1,NULL,'Conduit',NULL,481,161),(2792,NULL,0,NULL,NULL,NULL,0,NULL,'Walletsworth',NULL,481,161),(2793,NULL,0,NULL,NULL,NULL,0,NULL,'Nemo',NULL,481,161),(2794,NULL,0,NULL,NULL,'with Crazy Train (Ozzy Osbourne) tease',0,NULL,'Prowler',NULL,481,161),(2795,NULL,0,NULL,NULL,NULL,0,NULL,'Wife Soup',NULL,481,161),(2796,NULL,0,NULL,NULL,NULL,1,NULL,'Speak Up',NULL,481,161),(2797,NULL,0,NULL,NULL,NULL,0,NULL,'Live and Let Die',NULL,481,161),(2798,NULL,0,NULL,NULL,NULL,1,NULL,'The Crooked One',NULL,482,161),(2799,NULL,0,NULL,NULL,NULL,0,NULL,'Make It Right',NULL,482,161),(2800,NULL,0,NULL,NULL,NULL,1,NULL,'The Linear',NULL,482,161),(2801,NULL,0,NULL,NULL,NULL,0,NULL,'Educated Guess',NULL,482,161),(2802,NULL,0,NULL,NULL,'with Entrance of the Gladiators (Julius Fučík) tease',0,NULL,'JaJunk',NULL,482,161),(2803,NULL,0,NULL,NULL,NULL,0,NULL,'Mail Package',NULL,482,161),(2804,NULL,0,NULL,NULL,NULL,0,NULL,'The Fussy Dutchman',NULL,482,161),(2805,NULL,0,NULL,NULL,NULL,1,NULL,'Women Wine and Song',NULL,482,161),(2806,NULL,0,NULL,NULL,NULL,0,NULL,'JaJunk',NULL,482,161),(2807,NULL,0,NULL,NULL,NULL,0,NULL,'Phil\'s Farm',NULL,483,161),(2808,NULL,0,NULL,NULL,'with Brendan and Jake on acoustics',0,NULL,'Gone for Good',NULL,484,162),(2809,NULL,0,NULL,NULL,'with Brendan and Jake on acoustics',0,NULL,'Where Is My Mind?',NULL,484,162),(2810,NULL,0,NULL,NULL,'with Brendan and Jake on acoustics',0,NULL,'Great American',NULL,484,162),(2811,NULL,0,NULL,NULL,'with Brendan and Jake on acoustics',0,NULL,'The Pequod',NULL,484,162),(2812,NULL,0,NULL,NULL,'with Brendan and Jake on acoustics',0,NULL,'Great American',NULL,484,162),(2813,NULL,0,NULL,NULL,NULL,1,NULL,'Wappy Sprayberry',NULL,484,162),(2814,NULL,0,NULL,NULL,NULL,0,NULL,'Believe the Lie',NULL,484,162),(2815,NULL,0,NULL,NULL,NULL,1,NULL,'Nothing Too Fancy',NULL,484,162),(2816,NULL,0,NULL,NULL,NULL,1,NULL,'Syncopated Strangers',NULL,484,162),(2817,NULL,0,NULL,NULL,NULL,0,NULL,'Dump City',NULL,484,162),(2818,NULL,0,NULL,NULL,NULL,1,NULL,'2x2',NULL,485,162),(2819,NULL,0,NULL,NULL,NULL,1,NULL,'Syncopated Strangers',NULL,485,162),(2820,NULL,0,NULL,NULL,NULL,0,NULL,'2x2',NULL,485,162),(2821,NULL,0,NULL,NULL,NULL,0,NULL,'August',NULL,485,162),(2822,NULL,0,NULL,NULL,NULL,1,NULL,'Robot World',NULL,485,162),(2823,NULL,0,NULL,NULL,'with Norwegian Wood (The Beatles) jam',0,NULL,'Resolution',NULL,485,162),(2824,NULL,0,NULL,NULL,NULL,0,NULL,'Bad Poker',NULL,485,162),(2825,NULL,0,NULL,NULL,'with Divisions intro',0,NULL,'All In Time',NULL,485,162),(2826,NULL,0,NULL,NULL,'with Crazy Train (Ozzy Osbourne) jam',0,NULL,'Miss Gradenko',NULL,486,162),(2827,NULL,0,NULL,NULL,NULL,0,NULL,'Nothing Too Fancy',NULL,486,162),(2828,NULL,0,NULL,NULL,NULL,1,NULL,'Nipple Trix',NULL,487,163),(2829,NULL,0,NULL,NULL,NULL,0,NULL,'Slacker',NULL,487,163),(2830,NULL,0,NULL,NULL,NULL,1,NULL,'Wellwishers',NULL,487,163),(2831,NULL,0,NULL,NULL,NULL,1,NULL,'Sociable Jimmy',NULL,487,163),(2832,NULL,0,NULL,NULL,NULL,1,NULL,'Sweetness',NULL,487,163),(2833,NULL,0,NULL,NULL,NULL,0,NULL,'Mad Love',NULL,487,163),(2834,NULL,0,NULL,NULL,'with Joshua Redman on saxophone',0,NULL,'Speak Up',NULL,487,163),(2835,NULL,0,NULL,NULL,'with Joshua Redman on saxophone',0,NULL,'Booth Love',NULL,487,163),(2836,NULL,0,NULL,NULL,'with Joshua Redman on saxophone',0,NULL,'Der Bluten Kat',NULL,488,163),(2837,NULL,0,NULL,NULL,'with Joshua Redman on saxophone',0,NULL,'Final Word',NULL,488,163),(2838,NULL,0,NULL,NULL,'with Joshua Redman on saxophone',0,NULL,'Der Bluten Kat',NULL,488,163),(2839,NULL,0,NULL,NULL,'with Joshua Redman on saxophone; with Working Day and Night (Michael Jackson) teases',0,NULL,'Day Nurse',NULL,488,163),(2840,NULL,0,NULL,NULL,'with Joshua Redman on saxophone',0,NULL,'Wife Soup',NULL,488,163),(2841,NULL,0,NULL,NULL,'with Joshua Redman on saxophone',0,NULL,'Dear Lord',NULL,488,163),(2842,NULL,0,NULL,NULL,'\"dub\" version',0,NULL,'Breathe',NULL,488,163),(2843,NULL,0,NULL,NULL,'just Joel on piano',0,NULL,'Jessica',NULL,489,163),(2844,NULL,0,NULL,NULL,NULL,1,NULL,'Uncommon',NULL,489,163),(2845,NULL,0,NULL,NULL,NULL,0,NULL,'Down Under',NULL,489,163),(2846,NULL,0,NULL,NULL,'with Feels So Good (Chuck Mangione) teases',0,NULL,'2x2',NULL,490,164),(2847,NULL,0,NULL,NULL,'with Joshua Redman on saxophone',0,NULL,'Bad Friday',NULL,490,164),(2848,NULL,0,NULL,NULL,'with Joshua Redman on saxophone',0,NULL,'Anchor Drops',NULL,490,164),(2849,NULL,0,NULL,NULL,'with Joshua Redman on saxophone',0,NULL,'When the World Is Running Down You Make the Best of What\'s Still Around',NULL,490,164),(2850,NULL,0,NULL,NULL,'with Joshua Redman on saxophone',0,NULL,'Professor Wormbog',NULL,490,164),(2851,NULL,0,NULL,NULL,'with Joshua Redman on saxophone',0,NULL,'Hajimemashite',NULL,490,164),(2852,NULL,0,NULL,NULL,'with Joshua Redman on saxophone and Joel on vocals',0,NULL,'Mail Package',NULL,490,164),(2853,NULL,0,NULL,NULL,'with Joshua Redman on saxophone',0,NULL,'Chapter 11',NULL,491,164),(2854,NULL,0,NULL,NULL,'with Joshua Redman on saxophone',0,NULL,'Chapter 12',NULL,491,164),(2855,NULL,0,NULL,NULL,'with Joshua Redman on saxophone',0,NULL,'Chapter 13',NULL,491,164),(2856,NULL,0,NULL,NULL,'with Joshua Redman on saxophone',0,NULL,'Chapter 14',NULL,491,164),(2857,NULL,0,NULL,NULL,'with Joshua Redman on saxophone',0,NULL,'Chapter 15',NULL,491,164),(2858,NULL,0,NULL,NULL,'with Joshua Redman on saxophone',0,NULL,'Chapter 16',NULL,491,164),(2859,NULL,0,NULL,NULL,'with Joshua Redman on saxophone',0,NULL,'Chapter 17',NULL,491,164),(2860,NULL,0,NULL,NULL,'with Joshua Redman on saxophone',0,NULL,'Chapter 18',NULL,491,164),(2861,NULL,0,NULL,NULL,'with Joshua Redman on saxophone',0,NULL,'Chapter 19',NULL,491,164),(2862,NULL,0,NULL,NULL,'\"Jimmy Stewart\" with lyrics',0,NULL,'Chapter 20',NULL,491,164),(2863,NULL,0,NULL,NULL,NULL,0,NULL,'Chapter 21',NULL,491,164),(2864,NULL,0,NULL,NULL,NULL,1,NULL,'Much Obliged',NULL,492,164),(2865,NULL,0,NULL,NULL,NULL,0,NULL,'Ophelia',NULL,492,164),(2866,NULL,0,NULL,NULL,NULL,0,NULL,'Divisions',NULL,493,165),(2867,NULL,0,NULL,NULL,NULL,1,NULL,'Prowler',NULL,493,165),(2868,NULL,0,NULL,NULL,NULL,0,NULL,'2nd Self',NULL,493,165),(2869,NULL,0,NULL,NULL,NULL,0,NULL,'The Weight Around',NULL,493,165),(2870,NULL,0,NULL,NULL,'with Joshua Redman on saxophone',0,NULL,'Gone for Good',NULL,493,165),(2871,NULL,0,NULL,NULL,'with Joshua Redman on saxophone',0,NULL,'Higgins',NULL,493,165),(2872,NULL,0,NULL,NULL,'with Joshua Redman on saxophone',0,NULL,'Ocean Billy',NULL,493,165),(2873,NULL,0,NULL,NULL,'with Joshua Redman on saxophone',0,NULL,'1348',NULL,494,165),(2874,NULL,0,NULL,NULL,'with Joshua Redman on saxophone',0,NULL,'Intentions Clear',NULL,494,165),(2875,NULL,0,NULL,NULL,'with Joshua Redman on saxophone',0,NULL,'1348',NULL,494,165),(2876,NULL,0,NULL,NULL,'with Joshua Redman on saxophone',0,NULL,'Walletsworth',NULL,494,165),(2877,NULL,0,NULL,NULL,'with Brendan on vocals',0,NULL,'40\'s Theme',NULL,494,165),(2878,NULL,0,NULL,NULL,NULL,1,NULL,'In The Kitchen',NULL,494,165),(2879,NULL,0,NULL,NULL,NULL,0,NULL,'Drums',NULL,494,165),(2880,NULL,0,NULL,NULL,NULL,0,NULL,'Don\'t You (Forget About Me)',NULL,494,165),(2881,NULL,0,NULL,NULL,NULL,1,NULL,'Resolution',NULL,495,165),(2882,NULL,0,NULL,NULL,NULL,0,NULL,'In The Kitchen',NULL,495,165),(2883,NULL,0,NULL,NULL,NULL,0,NULL,'Domino Theory',NULL,496,166),(2884,NULL,0,NULL,NULL,NULL,1,NULL,'Loose Ends',NULL,496,166),(2885,NULL,0,NULL,NULL,NULL,1,NULL,'Turn & Dub',NULL,496,166),(2886,NULL,0,NULL,NULL,NULL,1,NULL,'Yoga Pants',NULL,496,166),(2887,NULL,0,NULL,NULL,'with Joshua Redman on saxophone',0,NULL,'The Linear',NULL,496,166),(2888,NULL,0,NULL,NULL,'with Joshua Redman on saxophone',0,NULL,'Example 1',NULL,496,166),(2889,NULL,0,NULL,NULL,'with Joshua Redman on saxophone',0,NULL,'Make It Right',NULL,496,166),(2890,NULL,0,NULL,NULL,'with Joshua Redman on saxophone; with Cocaine Blues (Escort) teases',0,NULL,'The Triple Wide',NULL,497,166),(2891,NULL,0,NULL,NULL,'with Joshua Redman on saxophone',0,NULL,'Upward',NULL,497,166),(2892,NULL,0,NULL,NULL,'with Joshua Redman on saxophone',0,NULL,'1000 Places to See Before You Die',NULL,497,166),(2893,NULL,0,NULL,NULL,'with Joshua Redman on saxophone',0,NULL,'Similar Skin',NULL,497,166),(2894,NULL,0,NULL,NULL,'with Joshua Redman on saxophone',0,NULL,'Lively Up Yourself',NULL,497,166),(2895,NULL,0,NULL,NULL,NULL,1,NULL,'Robot World',NULL,497,166),(2896,NULL,0,NULL,NULL,NULL,1,NULL,'Front Porch',NULL,497,166),(2897,NULL,0,NULL,NULL,NULL,1,NULL,'Muff II: The Revenge',NULL,497,166),(2898,NULL,0,NULL,NULL,'with Will You Be There (Michael Jackson) tease',0,NULL,'Front Porch',NULL,497,166),(2899,NULL,0,NULL,NULL,'just Joel on piano',0,NULL,'Orfeo',NULL,498,166),(2900,NULL,0,NULL,NULL,NULL,0,NULL,'Waiting Room',NULL,498,166),(2901,NULL,0,NULL,NULL,NULL,1,NULL,'Flamethrower',NULL,499,167),(2902,NULL,0,NULL,NULL,NULL,0,NULL,'The Floor',NULL,499,167),(2903,NULL,0,NULL,NULL,NULL,0,NULL,'Mulche\'s Odyssey',NULL,499,167),(2904,NULL,0,NULL,NULL,NULL,1,NULL,'Night Nurse',NULL,499,167),(2905,NULL,0,NULL,NULL,NULL,0,NULL,'Bright Lights, Big City',NULL,499,167),(2906,NULL,0,NULL,NULL,NULL,1,NULL,'Visions',NULL,499,167),(2907,NULL,0,NULL,NULL,NULL,1,NULL,'Ain\'t Too Proud to Beg',NULL,499,167),(2908,NULL,0,NULL,NULL,NULL,0,NULL,'Red Tape',NULL,499,167),(2909,NULL,0,NULL,NULL,NULL,1,NULL,'Soul Food I',NULL,500,167),(2910,NULL,0,NULL,NULL,'with Holly Bowling on keys; with Owner Of A Lonely Heart (Yes) teases',0,NULL,'Soul Food II',NULL,500,167),(2911,NULL,0,NULL,NULL,NULL,0,NULL,'Soul Food III',NULL,500,167),(2912,NULL,0,NULL,NULL,NULL,0,NULL,'Conduit',NULL,500,167),(2913,NULL,0,NULL,NULL,NULL,0,NULL,'Comfortably Numb',NULL,500,167),(2914,NULL,0,NULL,NULL,NULL,0,NULL,'Partyin\' Peeps',NULL,500,167),(2915,NULL,0,NULL,NULL,NULL,0,NULL,'JaJunk',NULL,500,167),(2916,NULL,0,NULL,NULL,NULL,0,NULL,'Kimble',NULL,501,167),(2917,NULL,0,NULL,NULL,NULL,0,NULL,'Gulf Stream',NULL,501,167),(2918,NULL,0,NULL,NULL,NULL,1,NULL,'There\'s No Crying In Mexico',NULL,502,168),(2919,NULL,0,NULL,NULL,NULL,0,NULL,'Attachments',NULL,502,168),(2920,NULL,0,NULL,NULL,NULL,1,NULL,'All In Time',NULL,502,168),(2921,NULL,0,NULL,NULL,NULL,0,NULL,'Day Nurse',NULL,502,168),(2922,NULL,0,NULL,NULL,NULL,0,NULL,'Educated Guess',NULL,502,168),(2923,NULL,0,NULL,NULL,NULL,1,NULL,'Alex\'s House',NULL,502,168),(2924,NULL,0,NULL,NULL,NULL,1,NULL,'Bridgeless',NULL,502,168),(2925,NULL,0,NULL,NULL,NULL,1,NULL,'No Comment',NULL,502,168),(2926,NULL,0,NULL,NULL,NULL,0,NULL,'Believe the Lie',NULL,502,168),(2927,NULL,0,NULL,NULL,NULL,0,NULL,'Miss Tinkle\'s Overture',NULL,503,168),(2928,NULL,0,NULL,NULL,NULL,0,NULL,'Remind Me',NULL,503,168),(2929,NULL,0,NULL,NULL,NULL,1,NULL,'Cemetery Walk',NULL,503,168),(2930,NULL,0,NULL,NULL,NULL,0,NULL,'Cemetery Walk II',NULL,503,168),(2931,NULL,0,NULL,NULL,NULL,0,NULL,'Out Of Order',NULL,503,168),(2932,NULL,0,NULL,NULL,NULL,1,NULL,'Wappy Sprayberry',NULL,503,168),(2933,NULL,0,NULL,NULL,NULL,0,NULL,'All In Time',NULL,503,168),(2934,NULL,0,NULL,NULL,NULL,1,NULL,'Glory',NULL,504,168),(2935,NULL,0,NULL,NULL,NULL,0,NULL,'Bridgeless',NULL,504,168),(2936,NULL,0,NULL,NULL,NULL,1,NULL,'Gents',NULL,505,169),(2937,NULL,0,NULL,NULL,NULL,1,NULL,'Plunger',NULL,505,169),(2938,NULL,0,NULL,NULL,NULL,0,NULL,'Dump City',NULL,505,169),(2939,NULL,0,NULL,NULL,NULL,1,NULL,'Miami Virtue',NULL,505,169),(2940,NULL,0,NULL,NULL,NULL,0,NULL,'Nemo',NULL,505,169),(2941,NULL,0,NULL,NULL,'with March of the S.O.D. (S.O.D.) tease',0,NULL,'Utopian Fir',NULL,505,169),(2942,NULL,0,NULL,NULL,NULL,1,NULL,'Speak Up',NULL,505,169),(2943,NULL,0,NULL,NULL,NULL,1,NULL,'Utopian Fir',NULL,505,169),(2944,NULL,0,NULL,NULL,NULL,0,NULL,'Let\'s Dance',NULL,505,169),(2945,NULL,0,NULL,NULL,NULL,1,NULL,'Hurt Bird Bath',NULL,506,169),(2946,NULL,0,NULL,NULL,'original version',0,NULL,'Cut the Cable',NULL,506,169),(2947,NULL,0,NULL,NULL,NULL,0,NULL,'Hurt Bird Bath',NULL,506,169),(2948,NULL,0,NULL,NULL,NULL,0,NULL,'August',NULL,506,169),(2949,NULL,0,NULL,NULL,NULL,0,NULL,'Red',NULL,506,169),(2950,NULL,0,NULL,NULL,'with Rhiannon (Fleetwood Mac) tease',0,NULL,'Comma Later',NULL,506,169),(2951,NULL,0,NULL,NULL,NULL,0,NULL,'Den',NULL,506,169),(2952,NULL,0,NULL,NULL,'\"lounge\" version',0,NULL,'Nopener',NULL,506,169),(2953,NULL,0,NULL,NULL,NULL,1,NULL,'Much Obliged',NULL,507,169),(2954,NULL,0,NULL,NULL,NULL,0,NULL,'Kula',NULL,507,169),(2955,NULL,0,NULL,NULL,NULL,1,NULL,'Bathing Digits',NULL,508,170),(2956,NULL,0,NULL,NULL,NULL,0,NULL,'Rocker Part 2',NULL,508,170),(2957,NULL,0,NULL,NULL,'with Stranglehold (Ted Nugent) teases',0,NULL,'Nothing Too Fancy',NULL,508,170),(2958,NULL,0,NULL,NULL,NULL,1,NULL,'Spires',NULL,508,170),(2959,NULL,0,NULL,NULL,NULL,0,NULL,'Upward',NULL,508,170),(2960,NULL,0,NULL,NULL,NULL,1,NULL,'Atmosfarag',NULL,508,170),(2961,NULL,0,NULL,NULL,NULL,0,NULL,'Der Bluten Kat',NULL,508,170),(2962,NULL,0,NULL,NULL,NULL,0,NULL,'40\'s Theme',NULL,509,170),(2963,NULL,0,NULL,NULL,NULL,1,NULL,'Resolution',NULL,509,170),(2964,NULL,0,NULL,NULL,NULL,0,NULL,'Phil\'s Farm',NULL,509,170),(2965,NULL,0,NULL,NULL,NULL,1,NULL,'Black Sabbath',NULL,509,170),(2966,NULL,0,NULL,NULL,'with Anders Beck on dobro and Paul Hoffman on vocals',0,NULL,'War Pigs',NULL,509,170),(2967,NULL,0,NULL,NULL,NULL,1,NULL,'2x2',NULL,509,170),(2968,NULL,0,NULL,NULL,NULL,0,NULL,'Nothing Too Fancy',NULL,509,170),(2969,NULL,0,NULL,NULL,'with Wanna Be Startin\' Somethin\' (Michael Jackson) jam',0,NULL,'Bad Friday',NULL,510,170),(2970,NULL,0,NULL,NULL,NULL,1,NULL,'Gurgle',NULL,511,171),(2971,NULL,0,NULL,NULL,NULL,0,NULL,'Higgins',NULL,511,171),(2972,NULL,0,NULL,NULL,NULL,0,NULL,'Make It Right',NULL,511,171),(2973,NULL,0,NULL,NULL,NULL,1,NULL,'In The Kitchen',NULL,511,171),(2974,NULL,0,NULL,NULL,NULL,0,NULL,'White Man\'s Moccasins',NULL,511,171),(2975,NULL,0,NULL,NULL,'with Jake on keys',0,NULL,'Puppet String',NULL,511,171),(2976,NULL,0,NULL,NULL,'with Anders Beck on dobro and Paul Hoffman on vocals and mandolin',0,NULL,'Can\'t You See',NULL,511,171),(2977,NULL,0,NULL,NULL,NULL,0,NULL,'Mulche\'s Odyssey',NULL,512,171),(2978,NULL,0,NULL,NULL,NULL,1,NULL,'Hajimemashite',NULL,512,171),(2979,NULL,0,NULL,NULL,NULL,1,NULL,'JaJunk',NULL,512,171),(2980,NULL,0,NULL,NULL,'with (Don\'t Fear) The Reaper (Blue Oyster Cult) teases',0,NULL,'Miami Virtue',NULL,512,171),(2981,NULL,0,NULL,NULL,NULL,0,NULL,'Remind Me',NULL,512,171),(2982,NULL,0,NULL,NULL,NULL,0,NULL,'Professor Wormbog',NULL,512,171),(2983,NULL,0,NULL,NULL,NULL,1,NULL,'Much Obliged',NULL,512,171),(2984,NULL,0,NULL,NULL,NULL,1,NULL,'In The Kitchen',NULL,512,171),(2985,NULL,0,NULL,NULL,'with Top Gun Anthem (Harold Faltermeyer) tease',0,NULL,'JaJunk',NULL,512,171),(2986,NULL,0,NULL,NULL,NULL,0,NULL,'Tom Sawyer',NULL,513,171),(2987,NULL,0,NULL,NULL,NULL,1,NULL,'North Route',NULL,514,172),(2988,NULL,0,NULL,NULL,NULL,1,NULL,'The Linear',NULL,514,172),(2989,NULL,0,NULL,NULL,NULL,0,NULL,'Go to Hell',NULL,514,172),(2990,NULL,0,NULL,NULL,NULL,1,NULL,'Cut Off',NULL,514,172),(2991,NULL,0,NULL,NULL,NULL,0,NULL,'Example 1',NULL,514,172),(2992,NULL,0,NULL,NULL,NULL,0,NULL,'Ride On Pony',NULL,514,172),(2993,NULL,0,NULL,NULL,NULL,0,NULL,'Night Nurse',NULL,514,172),(2994,NULL,0,NULL,NULL,NULL,1,NULL,'Preamble',NULL,514,172),(2995,NULL,0,NULL,NULL,NULL,1,NULL,'Mantis',NULL,514,172),(2996,NULL,0,NULL,NULL,NULL,0,NULL,'Dancing Days',NULL,514,172),(2997,NULL,0,NULL,NULL,NULL,0,NULL,'Attachments',NULL,515,172),(2998,NULL,0,NULL,NULL,NULL,1,NULL,'Utopian Fir',NULL,515,172),(2999,NULL,0,NULL,NULL,NULL,1,NULL,'Ain\'t No Fun (If the Homies Can\'t Have None)',NULL,515,172),(3000,NULL,0,NULL,NULL,'with Tom Sawyer (Rush) tease',0,NULL,'Utopian Fir',NULL,515,172),(3001,NULL,0,NULL,NULL,NULL,0,NULL,'No Diablo',NULL,515,172),(3002,NULL,0,NULL,NULL,NULL,1,NULL,'The Triple Wide',NULL,515,172),(3003,NULL,0,NULL,NULL,NULL,0,NULL,'Mantis',NULL,515,172),(3004,NULL,0,NULL,NULL,NULL,0,NULL,'Breaker',NULL,515,172),(3005,NULL,0,NULL,NULL,NULL,0,NULL,'Booth Love',NULL,515,172),(3006,NULL,0,NULL,NULL,NULL,0,NULL,'Pay the Snucka',NULL,516,172),(3007,NULL,0,NULL,NULL,NULL,1,NULL,'Similar Skin',NULL,517,173),(3008,NULL,0,NULL,NULL,NULL,0,NULL,'Educated Guess',NULL,517,173),(3009,NULL,0,NULL,NULL,NULL,1,NULL,'Sociable Jimmy',NULL,517,173),(3010,NULL,0,NULL,NULL,NULL,0,NULL,'Mad Love',NULL,517,173),(3011,NULL,0,NULL,NULL,NULL,1,NULL,'Alex\'s House',NULL,517,173),(3012,NULL,0,NULL,NULL,NULL,1,NULL,'Der Bluten Kat',NULL,517,173),(3013,NULL,0,NULL,NULL,NULL,1,NULL,'Final Word',NULL,517,173),(3014,NULL,0,NULL,NULL,NULL,0,NULL,'Der Bluten Kat',NULL,517,173),(3015,NULL,0,NULL,NULL,NULL,0,NULL,'Miss Tinkle\'s Overture',NULL,518,173),(3016,NULL,0,NULL,NULL,NULL,1,NULL,'Hourglass',NULL,518,173),(3017,NULL,0,NULL,NULL,NULL,0,NULL,'Domino Theory',NULL,518,173),(3018,NULL,0,NULL,NULL,'with Kashmir (Led Zeppelin) jam',0,NULL,'Syncopated Strangers',NULL,518,173),(3019,NULL,0,NULL,NULL,NULL,1,NULL,'Girlfriend is Better',NULL,518,173),(3020,NULL,0,NULL,NULL,NULL,0,NULL,'The Fussy Dutchman',NULL,518,173),(3021,NULL,0,NULL,NULL,NULL,0,NULL,'The Floor',NULL,518,173),(3022,NULL,0,NULL,NULL,NULL,0,NULL,'Hangover',NULL,519,173),(3023,NULL,0,NULL,NULL,NULL,1,NULL,'Conduit',NULL,520,174),(3024,NULL,0,NULL,NULL,NULL,0,NULL,'Loose Ends',NULL,520,174),(3025,NULL,0,NULL,NULL,NULL,0,NULL,'Hurt Bird Bath',NULL,520,174),(3026,NULL,0,NULL,NULL,NULL,0,NULL,'Wife Soup',NULL,520,174),(3027,NULL,0,NULL,NULL,'with It\'s About That Time (Miles Davis) teases',0,NULL,'Dump City',NULL,520,174),(3028,NULL,0,NULL,NULL,NULL,1,NULL,'The Triple Wide',NULL,521,174),(3029,NULL,0,NULL,NULL,NULL,1,NULL,'Cut Off',NULL,521,174),(3030,NULL,0,NULL,NULL,NULL,0,NULL,'Higgins',NULL,521,174),(3031,NULL,0,NULL,NULL,NULL,0,NULL,'Search 4',NULL,521,174),(3032,NULL,0,NULL,NULL,NULL,1,NULL,'In The Kitchen',NULL,521,174),(3033,NULL,0,NULL,NULL,NULL,1,NULL,'No Comment',NULL,521,174),(3034,NULL,0,NULL,NULL,'with Jeff Grady on percussion',0,NULL,'Rock the Casbah',NULL,521,174),(3035,NULL,0,NULL,NULL,NULL,1,NULL,'Plunger',NULL,522,174),(3036,NULL,0,NULL,NULL,NULL,1,NULL,'In The Kitchen',NULL,522,174),(3037,NULL,0,NULL,NULL,NULL,0,NULL,'Plunger',NULL,522,174),(3038,NULL,0,NULL,NULL,NULL,1,NULL,'You Got the Wrong Guy',NULL,523,175),(3039,NULL,0,NULL,NULL,NULL,0,NULL,'Speak Up',NULL,523,175),(3040,NULL,0,NULL,NULL,NULL,1,NULL,'Mantis',NULL,523,175),(3041,NULL,0,NULL,NULL,NULL,0,NULL,'Room to Breathe',NULL,523,175),(3042,NULL,0,NULL,NULL,NULL,0,NULL,'The Bottom Half',NULL,523,175),(3043,NULL,0,NULL,NULL,NULL,0,NULL,'Make It Right',NULL,523,175),(3044,NULL,0,NULL,NULL,NULL,1,NULL,'Yoga Pants',NULL,523,175),(3045,NULL,0,NULL,NULL,NULL,0,NULL,'Mantis',NULL,523,175),(3046,NULL,0,NULL,NULL,NULL,1,NULL,'1348',NULL,524,175),(3047,NULL,0,NULL,NULL,NULL,0,NULL,'Day Nurse',NULL,524,175),(3048,NULL,0,NULL,NULL,NULL,1,NULL,'I Am the Walrus',NULL,524,175),(3049,NULL,0,NULL,NULL,NULL,1,NULL,'Tribute to the Spinal Shaft',NULL,524,175),(3050,NULL,0,NULL,NULL,NULL,1,NULL,'Much Obliged',NULL,524,175),(3051,NULL,0,NULL,NULL,NULL,0,NULL,'Hajimemashite',NULL,524,175),(3052,NULL,0,NULL,NULL,NULL,1,NULL,'Intentions Clear',NULL,524,175),(3053,NULL,0,NULL,NULL,NULL,0,NULL,'1348',NULL,524,175),(3054,NULL,0,NULL,NULL,NULL,0,NULL,'40\'s Theme',NULL,525,175),(3055,NULL,0,NULL,NULL,NULL,1,NULL,'All In Time',NULL,526,176),(3056,NULL,0,NULL,NULL,NULL,1,NULL,'Anchor Drops',NULL,526,176),(3057,NULL,0,NULL,NULL,NULL,0,NULL,'2x2',NULL,526,176),(3058,NULL,0,NULL,NULL,'with Brendan and Jake on acoutics',0,NULL,'Uncle Wally',NULL,526,176),(3059,NULL,0,NULL,NULL,'with Brendan on acoustic',0,NULL,'Kielbasa',NULL,526,176),(3060,NULL,0,NULL,NULL,NULL,1,NULL,'Draconian',NULL,526,176),(3061,NULL,0,NULL,NULL,NULL,0,NULL,'Partyin\' Peeps',NULL,526,176),(3062,NULL,0,NULL,NULL,NULL,1,NULL,'Wappy Sprayberry',NULL,527,176),(3063,NULL,0,NULL,NULL,NULL,0,NULL,'Ringo',NULL,527,176),(3064,NULL,0,NULL,NULL,NULL,1,NULL,'Out Of Order',NULL,527,176),(3065,NULL,0,NULL,NULL,NULL,1,NULL,'Bridgeless',NULL,527,176),(3066,NULL,0,NULL,NULL,NULL,0,NULL,'Bad Friday',NULL,527,176),(3067,NULL,0,NULL,NULL,NULL,1,NULL,'Layla',NULL,527,176),(3068,NULL,0,NULL,NULL,NULL,0,NULL,'Bridgeless',NULL,527,176),(3069,NULL,0,NULL,NULL,'with Den teases',0,NULL,'Booth Love',NULL,528,176),(3070,NULL,0,NULL,NULL,NULL,0,NULL,'All In Time',NULL,528,176),(3071,NULL,0,NULL,NULL,'unfinished',0,NULL,'Nothing Too Fancy',NULL,529,177),(3072,NULL,0,NULL,NULL,NULL,0,NULL,'Cemetery Walk',NULL,529,177),(3073,NULL,0,NULL,NULL,NULL,0,NULL,'Slacker',NULL,529,177),(3074,NULL,0,NULL,NULL,NULL,0,NULL,'Walletsworth',NULL,529,177),(3075,NULL,0,NULL,NULL,NULL,1,NULL,'Resolution',NULL,529,177),(3076,NULL,0,NULL,NULL,NULL,0,NULL,'Glory',NULL,529,177),(3077,NULL,0,NULL,NULL,NULL,1,NULL,'JaJunk',NULL,530,177),(3078,NULL,0,NULL,NULL,NULL,0,NULL,'Upward',NULL,530,177),(3079,NULL,0,NULL,NULL,'with Jake on keys',0,NULL,'Push the Pig',NULL,530,177),(3080,NULL,0,NULL,NULL,'with Bulls On Parade (Rage Against the Machine) jam and quotes',0,NULL,'Turn & Dub',NULL,530,177),(3081,NULL,0,NULL,NULL,NULL,0,NULL,'Puppet String',NULL,530,177),(3082,NULL,0,NULL,NULL,NULL,0,NULL,'Driven to Tears',NULL,530,177),(3083,NULL,0,NULL,NULL,'unfinished',0,NULL,'Mulche\'s Odyssey',NULL,531,177),(3084,NULL,0,NULL,NULL,NULL,0,NULL,'JaJunk',NULL,531,177),(3085,NULL,0,NULL,NULL,NULL,1,NULL,'Tango Mike',NULL,532,178),(3086,NULL,0,NULL,NULL,NULL,0,NULL,'Phil\'s Farm',NULL,532,178),(3087,NULL,0,NULL,NULL,NULL,1,NULL,'Piranhas',NULL,532,178),(3088,NULL,0,NULL,NULL,'with Jake on keys',0,NULL,'Blue Echo',NULL,532,178),(3089,NULL,0,NULL,NULL,NULL,0,NULL,'Speak Up',NULL,532,178),(3090,NULL,0,NULL,NULL,NULL,1,NULL,'Dear Lord',NULL,532,178),(3091,NULL,0,NULL,NULL,NULL,0,NULL,'The Crooked One',NULL,532,178),(3092,NULL,0,NULL,NULL,NULL,0,NULL,'Hot for Teacher',NULL,532,178),(3093,NULL,0,NULL,NULL,NULL,0,NULL,'Wizard Burial Ground',NULL,532,178),(3094,NULL,0,NULL,NULL,NULL,1,NULL,'Plunger',NULL,533,178),(3095,NULL,0,NULL,NULL,NULL,0,NULL,'Uncommon',NULL,533,178),(3096,NULL,0,NULL,NULL,NULL,1,NULL,'Miami Virtue',NULL,533,178),(3097,NULL,0,NULL,NULL,NULL,0,NULL,'August',NULL,533,178),(3098,NULL,0,NULL,NULL,NULL,1,NULL,'Golden Years',NULL,533,178),(3099,NULL,0,NULL,NULL,NULL,1,NULL,'FF',NULL,533,178),(3100,NULL,0,NULL,NULL,NULL,0,NULL,'Sociable Jimmy',NULL,533,178),(3101,NULL,0,NULL,NULL,NULL,0,NULL,'Remind Me',NULL,534,178),(3102,NULL,0,NULL,NULL,NULL,1,NULL,'There\'s No Crying In Mexico',NULL,535,179),(3103,NULL,0,NULL,NULL,NULL,0,NULL,'Attachments',NULL,535,179),(3104,NULL,0,NULL,NULL,NULL,1,NULL,'Bridgeless',NULL,535,179),(3105,NULL,0,NULL,NULL,NULL,0,NULL,'2nd Self',NULL,535,179),(3106,NULL,0,NULL,NULL,NULL,1,NULL,'Wappy Sprayberry',NULL,535,179),(3107,NULL,0,NULL,NULL,NULL,0,NULL,'Rocker Part 2',NULL,535,179),(3108,NULL,0,NULL,NULL,NULL,1,NULL,'Kid Charlemagne',NULL,535,179),(3109,NULL,0,NULL,NULL,NULL,0,NULL,'Bridgeless',NULL,535,179),(3110,NULL,0,NULL,NULL,NULL,1,NULL,'Hurt Bird Bath',NULL,536,179),(3111,NULL,0,NULL,NULL,NULL,0,NULL,'2x2',NULL,536,179),(3112,NULL,0,NULL,NULL,NULL,1,NULL,'1348',NULL,536,179),(3113,NULL,0,NULL,NULL,NULL,0,NULL,'Hajimemashite',NULL,536,179),(3114,NULL,0,NULL,NULL,NULL,1,NULL,'Ringo',NULL,536,179),(3115,NULL,0,NULL,NULL,NULL,0,NULL,'1348',NULL,536,179),(3116,NULL,0,NULL,NULL,NULL,0,NULL,'...And Justice for All',NULL,536,179),(3117,NULL,0,NULL,NULL,NULL,1,NULL,'Day Nurse',NULL,537,179),(3118,NULL,0,NULL,NULL,NULL,0,NULL,'The Floor',NULL,537,179),(3119,NULL,0,NULL,NULL,NULL,1,NULL,'October Rain',NULL,538,180),(3120,NULL,0,NULL,NULL,NULL,0,NULL,'Higgins',NULL,538,180),(3121,NULL,0,NULL,NULL,NULL,1,NULL,'Rosanna',NULL,538,180),(3122,NULL,0,NULL,NULL,NULL,0,NULL,'Prowler',NULL,538,180),(3123,NULL,0,NULL,NULL,NULL,1,NULL,'Similar Skin',NULL,538,180),(3124,NULL,0,NULL,NULL,NULL,1,NULL,'The Triple Wide',NULL,538,180),(3125,NULL,0,NULL,NULL,NULL,0,NULL,'Similar Skin',NULL,538,180),(3126,NULL,0,NULL,NULL,'with Brendan on acoustic',0,NULL,'No Diablo',NULL,538,180),(3127,NULL,0,NULL,NULL,'with Brendan on acoustic',0,NULL,'The Weight Around',NULL,538,180),(3128,NULL,0,NULL,NULL,NULL,0,NULL,'40\'s Theme',NULL,538,180),(3129,NULL,0,NULL,NULL,NULL,1,NULL,'In The Kitchen',NULL,539,180),(3130,NULL,0,NULL,NULL,NULL,1,NULL,'Utopian Fir',NULL,539,180),(3131,NULL,0,NULL,NULL,'incomplete',0,NULL,'Immigrant Song',NULL,539,180),(3132,NULL,0,NULL,NULL,'with 24 or 6 to 4 (Chicago) jam',0,NULL,'Utopian Fir',NULL,539,180),(3133,NULL,0,NULL,NULL,NULL,0,NULL,'Making Flippy Floppy',NULL,539,180),(3134,NULL,0,NULL,NULL,NULL,1,NULL,'Tribute to the Spinal Shaft',NULL,539,180),(3135,NULL,0,NULL,NULL,NULL,1,NULL,'Conduit',NULL,539,180),(3136,NULL,0,NULL,NULL,NULL,0,NULL,'Make It Right',NULL,539,180),(3137,NULL,0,NULL,NULL,NULL,0,NULL,'Divisions',NULL,540,180),(3138,NULL,0,NULL,NULL,NULL,1,NULL,'Goonville',NULL,541,181),(3139,NULL,0,NULL,NULL,NULL,0,NULL,'Get In The Van',NULL,541,181),(3140,NULL,0,NULL,NULL,NULL,1,NULL,'Morning Song',NULL,541,181),(3141,NULL,0,NULL,NULL,NULL,0,NULL,'Booth Love',NULL,541,181),(3142,NULL,0,NULL,NULL,NULL,0,NULL,'Red Tape',NULL,541,181),(3143,NULL,0,NULL,NULL,'with La Grange (ZZ Top) teases',0,NULL,'The Fuzz',NULL,541,181),(3144,NULL,0,NULL,NULL,NULL,0,NULL,'Women Wine and Song',NULL,541,181),(3145,NULL,0,NULL,NULL,NULL,0,NULL,'Bridgeless',NULL,541,181),(3146,NULL,0,NULL,NULL,NULL,0,NULL,'Life During Exodus',NULL,542,181),(3147,NULL,0,NULL,NULL,NULL,1,NULL,'All In Time',NULL,542,181),(3148,NULL,0,NULL,NULL,NULL,1,NULL,'The Linear',NULL,542,181),(3149,NULL,0,NULL,NULL,NULL,0,NULL,'Partyin\' Peeps',NULL,542,181),(3150,NULL,0,NULL,NULL,NULL,0,NULL,'Professor Wormbog',NULL,542,181),(3151,NULL,0,NULL,NULL,NULL,0,NULL,'Wife Soup',NULL,542,181),(3152,NULL,0,NULL,NULL,'with Xxplosive (Dr. Dre) teases',0,NULL,'Plunger',NULL,542,181),(3153,NULL,0,NULL,NULL,NULL,1,NULL,'Kimble',NULL,543,181),(3154,NULL,0,NULL,NULL,NULL,0,NULL,'All In Time',NULL,543,181),(3155,NULL,0,NULL,NULL,NULL,1,NULL,'Flamethrower',NULL,544,182),(3156,NULL,0,NULL,NULL,NULL,1,NULL,'Puppet String',NULL,544,182),(3157,NULL,0,NULL,NULL,NULL,0,NULL,'Roctopus',NULL,544,182),(3158,NULL,0,NULL,NULL,NULL,0,NULL,'Attachments',NULL,544,182),(3159,NULL,0,NULL,NULL,NULL,1,NULL,'Out Of Order',NULL,544,182),(3160,NULL,0,NULL,NULL,NULL,0,NULL,'Puppet String',NULL,544,182),(3161,NULL,0,NULL,NULL,NULL,1,NULL,'Got Your Milk (Right Here)',NULL,544,182),(3162,NULL,0,NULL,NULL,'\"Jimmy Stewart\" with lyrics',0,NULL,'Der Bluten Kat',NULL,544,182),(3163,NULL,0,NULL,NULL,'with Speak Up tease',0,NULL,'Gurgle',NULL,545,182),(3164,NULL,0,NULL,NULL,'unfinished',0,NULL,'Speak Up',NULL,545,182),(3165,NULL,0,NULL,NULL,NULL,1,NULL,'The Floor',NULL,545,182),(3166,NULL,0,NULL,NULL,NULL,1,NULL,'I\'m On Fire',NULL,545,182),(3167,NULL,0,NULL,NULL,NULL,0,NULL,'The Floor',NULL,545,182),(3168,NULL,0,NULL,NULL,NULL,1,NULL,'Kabump',NULL,545,182),(3169,NULL,0,NULL,NULL,'with Front Porch tease',0,NULL,'Comma Later',NULL,545,182),(3170,NULL,0,NULL,NULL,NULL,1,NULL,'In a Silent Way',NULL,545,182),(3171,NULL,0,NULL,NULL,NULL,0,NULL,'It\'s About That Time',NULL,545,182),(3172,NULL,0,NULL,NULL,NULL,1,NULL,'Glory',NULL,546,182),(3173,NULL,0,NULL,NULL,NULL,0,NULL,'The Good Times Are Killing Me',NULL,546,182),(3174,NULL,0,NULL,NULL,NULL,0,NULL,'Mad Love',NULL,546,182),(3175,NULL,0,NULL,NULL,NULL,1,NULL,'You Got the Wrong Guy',NULL,547,183),(3176,NULL,0,NULL,NULL,NULL,0,NULL,'Remind Me',NULL,547,183),(3177,NULL,0,NULL,NULL,NULL,0,NULL,'Cemetery Walk',NULL,547,183),(3178,NULL,0,NULL,NULL,NULL,0,NULL,'Nemo',NULL,547,183),(3179,NULL,0,NULL,NULL,NULL,0,NULL,'Room to Breathe',NULL,547,183),(3180,NULL,0,NULL,NULL,NULL,1,NULL,'Made to Measure',NULL,547,183),(3181,NULL,0,NULL,NULL,NULL,0,NULL,'Ocean Billy',NULL,547,183),(3182,NULL,0,NULL,NULL,NULL,1,NULL,'The Triple Wide',NULL,548,183),(3183,NULL,0,NULL,NULL,NULL,0,NULL,'Mulche\'s Odyssey',NULL,548,183),(3184,NULL,0,NULL,NULL,NULL,1,NULL,'August',NULL,548,183),(3185,NULL,0,NULL,NULL,NULL,0,NULL,'Rocker Part 2',NULL,548,183),(3186,NULL,0,NULL,NULL,NULL,0,NULL,'Higgins',NULL,548,183),(3187,NULL,0,NULL,NULL,NULL,0,NULL,'Let\'s Dance',NULL,548,183),(3188,NULL,0,NULL,NULL,NULL,0,NULL,'In The Kitchen',NULL,549,183),(3189,NULL,0,NULL,NULL,NULL,0,NULL,'Bad Friday',NULL,550,184),(3190,NULL,0,NULL,NULL,NULL,0,NULL,'Walletsworth',NULL,550,184),(3191,NULL,0,NULL,NULL,NULL,1,NULL,'Preamble',NULL,550,184),(3192,NULL,0,NULL,NULL,NULL,1,NULL,'Mantis Ghetts',NULL,550,184),(3193,NULL,0,NULL,NULL,NULL,0,NULL,'Mantis',NULL,550,184),(3194,NULL,0,NULL,NULL,'with Andrew “Red” Johnson on keyboards',0,NULL,'Example 1',NULL,550,184),(3195,NULL,0,NULL,NULL,NULL,0,NULL,'Mantis',NULL,550,184),(3196,NULL,0,NULL,NULL,NULL,0,NULL,'Draconian',NULL,550,184),(3197,NULL,0,NULL,NULL,NULL,0,NULL,'Reelin\' in the Years',NULL,550,184),(3198,NULL,0,NULL,NULL,NULL,1,NULL,'JaJunk',NULL,551,184),(3199,NULL,0,NULL,NULL,'original version',0,NULL,'Cut the Cable',NULL,551,184),(3200,NULL,0,NULL,NULL,NULL,0,NULL,'Dump City',NULL,551,184),(3201,NULL,0,NULL,NULL,NULL,0,NULL,'Call to Arms',NULL,551,184),(3202,NULL,0,NULL,NULL,NULL,0,NULL,'Slacker',NULL,551,184),(3203,NULL,0,NULL,NULL,NULL,1,NULL,'Miami Virtue',NULL,551,184),(3204,NULL,0,NULL,NULL,NULL,0,NULL,'Eat',NULL,551,184),(3205,NULL,0,NULL,NULL,NULL,0,NULL,'1348',NULL,551,184),(3206,NULL,0,NULL,NULL,NULL,1,NULL,'Hajimemashite',NULL,552,184),(3207,NULL,0,NULL,NULL,NULL,0,NULL,'JaJunk',NULL,552,184),(3208,NULL,0,NULL,NULL,NULL,1,NULL,'October Rain',NULL,553,185),(3209,NULL,0,NULL,NULL,NULL,0,NULL,'Phil\'s Farm',NULL,553,185),(3210,NULL,0,NULL,NULL,NULL,0,NULL,'No Diablo',NULL,553,185),(3211,NULL,0,NULL,NULL,NULL,1,NULL,'Glory',NULL,553,185),(3212,NULL,0,NULL,NULL,NULL,0,NULL,'2x2',NULL,553,185),(3213,NULL,0,NULL,NULL,NULL,1,NULL,'North Route',NULL,553,185),(3214,NULL,0,NULL,NULL,'unfinished',0,NULL,'Blue Echo',NULL,553,185),(3215,NULL,0,NULL,NULL,NULL,0,NULL,'Booth Love',NULL,553,185),(3216,NULL,0,NULL,NULL,NULL,1,NULL,'Der Bluten Kat',NULL,554,185),(3217,NULL,0,NULL,NULL,NULL,0,NULL,'In The Black',NULL,554,185),(3218,NULL,0,NULL,NULL,NULL,1,NULL,'Hurt Bird Bath',NULL,554,185),(3219,NULL,0,NULL,NULL,NULL,1,NULL,'Upward',NULL,554,185),(3220,NULL,0,NULL,NULL,NULL,0,NULL,'Hurt Bird Bath',NULL,554,185),(3221,NULL,0,NULL,NULL,NULL,1,NULL,'FF',NULL,554,185),(3222,NULL,0,NULL,NULL,'with Jailbreak (Thin Lizzy) quote and Can\'t You Hear Me Knockin\' (Rolling Stones) teases',0,NULL,'Utopian Fir',NULL,554,185),(3223,NULL,0,NULL,NULL,NULL,0,NULL,'Der Bluten Kat',NULL,554,185),(3224,NULL,0,NULL,NULL,NULL,0,NULL,'Attachments',NULL,555,185),(3225,NULL,0,NULL,NULL,NULL,1,NULL,'Lord Bail Ship',NULL,556,186),(3226,NULL,0,NULL,NULL,NULL,0,NULL,'Padgett\'s Profile',NULL,556,186),(3227,NULL,0,NULL,NULL,NULL,1,NULL,'Resolution',NULL,556,186),(3228,NULL,0,NULL,NULL,NULL,0,NULL,'Anchor Drops',NULL,556,186),(3229,NULL,0,NULL,NULL,NULL,0,NULL,'Stinko\'s Ascension',NULL,556,186),(3230,NULL,0,NULL,NULL,NULL,1,NULL,'The Haunt',NULL,556,186),(3231,NULL,0,NULL,NULL,NULL,0,NULL,'Cut the Cable',NULL,556,186),(3232,NULL,0,NULL,NULL,NULL,0,NULL,'Bad Friday',NULL,556,186),(3233,NULL,0,NULL,NULL,NULL,0,NULL,'40\'s Theme',NULL,557,186),(3234,NULL,0,NULL,NULL,NULL,1,NULL,'Puppet String',NULL,557,186),(3235,NULL,0,NULL,NULL,NULL,1,NULL,'Bridgeless',NULL,557,186),(3236,NULL,0,NULL,NULL,NULL,0,NULL,'Puppet String',NULL,557,186),(3237,NULL,0,NULL,NULL,'unfinished',0,NULL,'Ringo',NULL,557,186),(3238,NULL,0,NULL,NULL,'with Simple Gifts (Joseph Brackett Jr.) teases',0,NULL,'Divisions',NULL,557,186),(3239,NULL,0,NULL,NULL,NULL,0,NULL,'Bridgeless',NULL,557,186),(3240,NULL,0,NULL,NULL,'with Space Funk Booty tease',0,NULL,'Mail Package',NULL,558,186),(3241,NULL,0,NULL,NULL,NULL,1,NULL,'Depth Charge',NULL,559,187),(3242,NULL,0,NULL,NULL,NULL,1,NULL,'Wappy Sprayberry',NULL,559,187),(3243,NULL,0,NULL,NULL,NULL,0,NULL,'Higgins',NULL,559,187),(3244,NULL,0,NULL,NULL,NULL,0,NULL,'2nd Self',NULL,559,187),(3245,NULL,0,NULL,NULL,'with Stranglehold (Ted Nugent) jam',0,NULL,'#5',NULL,559,187),(3246,NULL,0,NULL,NULL,NULL,0,NULL,'Syncopated Strangers',NULL,559,187),(3247,NULL,0,NULL,NULL,NULL,0,NULL,'Crucial Taunt',NULL,559,187),(3248,NULL,0,NULL,NULL,NULL,0,NULL,'Weird Fishes/Arpeggi',NULL,559,187),(3249,NULL,0,NULL,NULL,NULL,0,NULL,'The Floor',NULL,559,187),(3250,NULL,0,NULL,NULL,NULL,0,NULL,'1348',NULL,560,187),(3251,NULL,0,NULL,NULL,NULL,0,NULL,'Speak Up',NULL,560,187),(3252,NULL,0,NULL,NULL,NULL,1,NULL,'Plunger',NULL,560,187),(3253,NULL,0,NULL,NULL,NULL,1,NULL,'Kula',NULL,560,187),(3254,NULL,0,NULL,NULL,NULL,1,NULL,'Plunger',NULL,560,187),(3255,NULL,0,NULL,NULL,NULL,1,NULL,'Voices Inside My Head',NULL,560,187),(3256,NULL,0,NULL,NULL,NULL,0,NULL,'Conduit',NULL,560,187),(3257,NULL,0,NULL,NULL,NULL,1,NULL,'Mulche\'s Odyssey',NULL,560,187),(3258,NULL,0,NULL,NULL,'incomplete',0,NULL,'Burnin\' and Lootin\'',NULL,560,187),(3259,NULL,0,NULL,NULL,NULL,0,NULL,'Mulche\'s Odyssey',NULL,560,187),(3260,NULL,0,NULL,NULL,NULL,0,NULL,'Bittersweet Symphony',NULL,560,187),(3261,NULL,0,NULL,NULL,NULL,0,NULL,'The Triple Wide',NULL,561,187),(3262,NULL,0,NULL,NULL,NULL,0,NULL,'Room to Breathe',NULL,562,188),(3263,NULL,0,NULL,NULL,NULL,0,NULL,'In The Black',NULL,562,188),(3264,NULL,0,NULL,NULL,NULL,1,NULL,'Intentions Clear',NULL,562,188),(3265,NULL,0,NULL,NULL,NULL,0,NULL,'Get In The Van',NULL,562,188),(3266,NULL,0,NULL,NULL,NULL,0,NULL,'Example 1',NULL,562,188),(3267,NULL,0,NULL,NULL,NULL,0,NULL,'The Fussy Dutchman',NULL,562,188),(3268,NULL,0,NULL,NULL,NULL,1,NULL,'Sociable Jimmy',NULL,562,188),(3269,NULL,0,NULL,NULL,NULL,0,NULL,'Make It Right',NULL,562,188),(3270,NULL,0,NULL,NULL,NULL,0,NULL,'Bright Lights, Big City',NULL,563,188),(3271,NULL,0,NULL,NULL,NULL,1,NULL,'The Linear',NULL,563,188),(3272,NULL,0,NULL,NULL,NULL,1,NULL,'Booth Love',NULL,563,188),(3273,NULL,0,NULL,NULL,NULL,1,NULL,'Nothing Too Fancy',NULL,563,188),(3274,NULL,0,NULL,NULL,NULL,0,NULL,'She Came In Through The Bathroom Window',NULL,563,188),(3275,NULL,0,NULL,NULL,NULL,1,NULL,'Little Gift',NULL,563,188),(3276,NULL,0,NULL,NULL,NULL,0,NULL,'Mulche\'s Odyssey',NULL,563,188),(3277,NULL,0,NULL,NULL,NULL,1,NULL,'Kimble',NULL,564,188),(3278,NULL,0,NULL,NULL,NULL,0,NULL,'Nothing Too Fancy',NULL,564,188),(3279,NULL,0,NULL,NULL,NULL,1,NULL,'There\'s No Crying In Mexico',NULL,565,189),(3280,NULL,0,NULL,NULL,NULL,1,NULL,'JaJunk',NULL,565,189),(3281,NULL,0,NULL,NULL,NULL,0,NULL,'Plunger',NULL,565,189),(3282,NULL,0,NULL,NULL,NULL,1,NULL,'Conduit',NULL,565,189),(3283,NULL,0,NULL,NULL,NULL,0,NULL,'Day Nurse',NULL,565,189),(3284,NULL,0,NULL,NULL,NULL,0,NULL,'Hang Up Your Hang Ups',NULL,565,189),(3285,NULL,0,NULL,NULL,NULL,1,NULL,'Higgins',NULL,565,189),(3286,NULL,0,NULL,NULL,NULL,0,NULL,'JaJunk',NULL,565,189),(3287,NULL,0,NULL,NULL,NULL,0,NULL,'Bad Friday',NULL,566,189),(3288,NULL,0,NULL,NULL,NULL,0,NULL,'Crucial Taunt',NULL,566,189),(3289,NULL,0,NULL,NULL,NULL,0,NULL,'Puppet String',NULL,566,189),(3290,NULL,0,NULL,NULL,'with On the Run (Pink Floyd) tease',0,NULL,'Miami Virtue',NULL,566,189),(3291,NULL,0,NULL,NULL,NULL,0,NULL,'Push the Pig',NULL,566,189),(3292,NULL,0,NULL,NULL,NULL,0,NULL,'Attachments',NULL,566,189),(3293,NULL,0,NULL,NULL,'with Voodoo Child (Slight Return) (Jimi Hendrix) jam',0,NULL,'Ringo',NULL,566,189),(3294,NULL,0,NULL,NULL,NULL,1,NULL,'Glory',NULL,567,189),(3295,NULL,0,NULL,NULL,NULL,0,NULL,'Hollywood Nights',NULL,567,189),(3296,NULL,0,NULL,NULL,NULL,1,NULL,'North Route',NULL,568,190),(3297,NULL,0,NULL,NULL,NULL,0,NULL,'All In Time',NULL,568,190),(3298,NULL,0,NULL,NULL,NULL,0,NULL,'Out Of Order',NULL,568,190),(3299,NULL,0,NULL,NULL,NULL,1,NULL,'Speak Up',NULL,568,190),(3300,NULL,0,NULL,NULL,NULL,0,NULL,'Believe the Lie',NULL,568,190),(3301,NULL,0,NULL,NULL,NULL,0,NULL,'Great American',NULL,568,190),(3302,NULL,0,NULL,NULL,NULL,0,NULL,'Miss Tinkle\'s Overture',NULL,569,190),(3303,NULL,0,NULL,NULL,NULL,0,NULL,'Red Tape',NULL,569,190),(3304,NULL,0,NULL,NULL,'with Sad But True (Metallica) and Rock With You (Michael Jackson) jams',0,NULL,'Utopian Fir',NULL,569,190),(3305,NULL,0,NULL,NULL,'with All In Time tease',0,NULL,'Wife Soup',NULL,569,190),(3306,NULL,0,NULL,NULL,NULL,1,NULL,'When the World Is Running Down You Make the Best of What\'s Still Around',NULL,569,190),(3307,NULL,0,NULL,NULL,NULL,0,NULL,'40\'s Theme',NULL,569,190),(3308,NULL,0,NULL,NULL,NULL,1,NULL,'Much Obliged',NULL,570,190),(3309,NULL,0,NULL,NULL,NULL,0,NULL,'Hajimemashite',NULL,570,190),(3310,NULL,0,NULL,NULL,NULL,1,NULL,'Goonville',NULL,571,191),(3311,NULL,0,NULL,NULL,NULL,0,NULL,'Educated Guess',NULL,571,191),(3312,NULL,0,NULL,NULL,NULL,1,NULL,'Cemetery Walk',NULL,571,191),(3313,NULL,0,NULL,NULL,NULL,0,NULL,'Cemetery Walk II',NULL,571,191),(3314,NULL,0,NULL,NULL,NULL,0,NULL,'Phil\'s Farm',NULL,571,191),(3315,NULL,0,NULL,NULL,NULL,1,NULL,'Last Man Swerving',NULL,571,191),(3316,NULL,0,NULL,NULL,NULL,0,NULL,'Slacker',NULL,571,191),(3317,NULL,0,NULL,NULL,NULL,0,NULL,'Transdermal Celebration',NULL,571,191),(3318,NULL,0,NULL,NULL,'with Pipeline (The Chantays) and Roundabout (Yes) teases',0,NULL,'The Floor',NULL,572,191),(3319,NULL,0,NULL,NULL,NULL,0,NULL,'Hindsight',NULL,572,191),(3320,NULL,0,NULL,NULL,NULL,0,NULL,'End of the Road',NULL,572,191),(3321,NULL,0,NULL,NULL,NULL,1,NULL,'Eat',NULL,572,191),(3322,NULL,0,NULL,NULL,NULL,1,NULL,'Time',NULL,572,191),(3323,NULL,0,NULL,NULL,NULL,0,NULL,'Eat',NULL,572,191),(3324,NULL,0,NULL,NULL,NULL,1,NULL,'Wappy Sprayberry',NULL,572,191),(3325,NULL,0,NULL,NULL,NULL,0,NULL,'Partyin\' Peeps',NULL,572,191),(3326,NULL,0,NULL,NULL,NULL,1,NULL,'Upward',NULL,572,191),(3327,NULL,0,NULL,NULL,NULL,0,NULL,'In The Kitchen',NULL,572,191),(3328,NULL,0,NULL,NULL,NULL,0,NULL,'Remind Me',NULL,573,191),(3329,NULL,0,NULL,NULL,NULL,1,NULL,'You Got the Wrong Guy',NULL,574,192),(3330,NULL,0,NULL,NULL,NULL,0,NULL,'Crucial Taunt',NULL,574,192),(3331,NULL,0,NULL,NULL,NULL,0,NULL,'Higgins',NULL,574,192),(3332,NULL,0,NULL,NULL,NULL,0,NULL,'Morning Song',NULL,574,192),(3333,NULL,0,NULL,NULL,NULL,1,NULL,'Resolution',NULL,574,192),(3334,NULL,0,NULL,NULL,NULL,0,NULL,'Space Funk Booty',NULL,574,192),(3335,NULL,0,NULL,NULL,NULL,1,NULL,'Spires',NULL,574,192),(3336,NULL,0,NULL,NULL,NULL,0,NULL,'13 Days',NULL,574,192),(3337,NULL,0,NULL,NULL,NULL,0,NULL,'Attachments',NULL,574,192),(3338,NULL,0,NULL,NULL,NULL,0,NULL,'Kula',NULL,575,192),(3339,NULL,0,NULL,NULL,'with For Whom the Bell Tolls (Metallica) and Thunder Kiss \'65 (White Zombie) teases',0,NULL,'Draconian',NULL,575,192),(3340,NULL,0,NULL,NULL,NULL,0,NULL,'I Ran',NULL,575,192),(3341,NULL,0,NULL,NULL,NULL,1,NULL,'Night Nurse',NULL,575,192),(3342,NULL,0,NULL,NULL,NULL,0,NULL,'40\'s Theme',NULL,575,192),(3343,NULL,0,NULL,NULL,'original version',0,NULL,'Cut the Cable',NULL,575,192),(3344,NULL,0,NULL,NULL,NULL,0,NULL,'Baba O\'Riley',NULL,575,192),(3345,NULL,0,NULL,NULL,NULL,0,NULL,'The Triple Wide',NULL,576,192),(3346,NULL,0,NULL,NULL,NULL,0,NULL,'Similar Skin',NULL,577,193),(3347,NULL,0,NULL,NULL,NULL,1,NULL,'Bridgeless',NULL,577,193),(3348,NULL,0,NULL,NULL,NULL,0,NULL,'Mad Love',NULL,577,193),(3349,NULL,0,NULL,NULL,NULL,0,NULL,'FF',NULL,577,193),(3350,NULL,0,NULL,NULL,NULL,0,NULL,'Go to Hell',NULL,577,193),(3351,NULL,0,NULL,NULL,NULL,0,NULL,'Dig A Pony',NULL,577,193),(3352,NULL,0,NULL,NULL,NULL,1,NULL,'Alex\'s House',NULL,577,193),(3353,NULL,0,NULL,NULL,NULL,0,NULL,'Bridgeless',NULL,577,193),(3354,NULL,0,NULL,NULL,NULL,1,NULL,'Mantis',NULL,578,193),(3355,NULL,0,NULL,NULL,NULL,0,NULL,'Mullet (Over)',NULL,578,193),(3356,NULL,0,NULL,NULL,NULL,1,NULL,'Deeper',NULL,578,193),(3357,NULL,0,NULL,NULL,NULL,1,NULL,'The Linear',NULL,578,193),(3358,NULL,0,NULL,NULL,NULL,0,NULL,'Mantis',NULL,578,193),(3359,NULL,0,NULL,NULL,NULL,1,NULL,'Tribute to the Spinal Shaft',NULL,578,193),(3360,NULL,0,NULL,NULL,NULL,1,NULL,'A Go Go',NULL,578,193),(3361,NULL,0,NULL,NULL,NULL,0,NULL,'Speak Up',NULL,578,193),(3362,NULL,0,NULL,NULL,NULL,0,NULL,'Bad Friday',NULL,578,193),(3363,NULL,0,NULL,NULL,NULL,1,NULL,'Prowler',NULL,579,193),(3364,NULL,0,NULL,NULL,NULL,0,NULL,'Runnin\' With The Devil',NULL,579,193),(3365,NULL,0,NULL,NULL,NULL,1,NULL,'Bathing Digits',NULL,580,194),(3366,NULL,0,NULL,NULL,NULL,0,NULL,'Mulche\'s Odyssey',NULL,580,194),(3367,NULL,0,NULL,NULL,NULL,0,NULL,'Wife Soup',NULL,580,194),(3368,NULL,0,NULL,NULL,NULL,1,NULL,'Intentions Clear',NULL,580,194),(3369,NULL,0,NULL,NULL,NULL,0,NULL,'Miss Tinkle\'s Overture',NULL,580,194),(3370,NULL,0,NULL,NULL,NULL,0,NULL,'Comfortably Numb',NULL,580,194),(3371,NULL,0,NULL,NULL,NULL,1,NULL,'Comma Later',NULL,580,194),(3372,NULL,0,NULL,NULL,NULL,0,NULL,'Pay the Snucka',NULL,580,194),(3373,NULL,0,NULL,NULL,NULL,0,NULL,'In The Black',NULL,581,194),(3374,NULL,0,NULL,NULL,NULL,1,NULL,'Wappy Sprayberry',NULL,581,194),(3375,NULL,0,NULL,NULL,NULL,0,NULL,'The Bottom Half',NULL,581,194),(3376,NULL,0,NULL,NULL,NULL,1,NULL,'No Diablo',NULL,581,194),(3377,NULL,0,NULL,NULL,NULL,0,NULL,'1348',NULL,581,194),(3378,NULL,0,NULL,NULL,NULL,1,NULL,'Two Tickets to Paradise',NULL,581,194),(3379,NULL,0,NULL,NULL,NULL,0,NULL,'Dump City',NULL,581,194),(3380,NULL,0,NULL,NULL,NULL,0,NULL,'In The Kitchen',NULL,581,194),(3381,NULL,0,NULL,NULL,NULL,1,NULL,'2x2',NULL,582,194),(3382,NULL,0,NULL,NULL,NULL,1,NULL,'Regulate',NULL,582,194),(3383,NULL,0,NULL,NULL,NULL,0,NULL,'2x2',NULL,582,194),(3384,NULL,0,NULL,NULL,NULL,1,NULL,'Catshot',NULL,583,195),(3385,NULL,0,NULL,NULL,NULL,0,NULL,'Make It Right',NULL,583,195),(3386,NULL,0,NULL,NULL,NULL,1,NULL,'Loose Ends',NULL,583,195),(3387,NULL,0,NULL,NULL,NULL,1,NULL,'Turn & Dub',NULL,583,195),(3388,NULL,0,NULL,NULL,NULL,1,NULL,'Driven to Tears',NULL,583,195),(3389,NULL,0,NULL,NULL,NULL,0,NULL,'White Man\'s Moccasins',NULL,583,195),(3390,NULL,0,NULL,NULL,NULL,0,NULL,'Professor Wormbog',NULL,583,195),(3391,NULL,0,NULL,NULL,NULL,0,NULL,'2nd Self',NULL,583,195),(3392,NULL,0,NULL,NULL,NULL,0,NULL,'Rocker Part 2',NULL,583,195),(3393,NULL,0,NULL,NULL,NULL,0,NULL,'Ocean Billy',NULL,584,195),(3394,NULL,0,NULL,NULL,NULL,1,NULL,'The Fussy Dutchman',NULL,584,195),(3395,NULL,0,NULL,NULL,NULL,0,NULL,'Booth Love',NULL,584,195),(3396,NULL,0,NULL,NULL,NULL,0,NULL,'Hurt Bird Bath',NULL,584,195),(3397,NULL,0,NULL,NULL,NULL,0,NULL,'Thin Air',NULL,584,195),(3398,NULL,0,NULL,NULL,NULL,0,NULL,'Kashmir',NULL,584,195),(3399,NULL,0,NULL,NULL,NULL,0,NULL,'Den',NULL,584,195),(3400,NULL,0,NULL,NULL,NULL,0,NULL,'JaJunk',NULL,585,195);
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
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `umspreadsheet-dev`.`track_AFTER_UPDATE` AFTER UPDATE ON `track` FOR EACH ROW
BEGIN
	IF ((SELECT COUNT(*) FROM track_review reviews JOIN track tracks ON reviews.track_id = tracks.id WHERE show_id = NEW.show_id) > 2) THEN
		UPDATE shows s
		SET s.average_rating = (
			SELECT AVG(track.average_rating)
			FROM track
			WHERE track.show_id = s.id
			GROUP BY track.show_id)
		WHERE s.id = NEW.show_id;
	ELSE
		UPDATE shows s
			SET s.average_rating = null;
	END IF;
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
  KEY `FKpu91vftyilv80w4a0txyff5w4` (`user_id`),
  CONSTRAINT `FK7dqviea2x4ak2kfu61w0ej81r` FOREIGN KEY (`track_id`) REFERENCES `track` (`id`),
  CONSTRAINT `FKpu91vftyilv80w4a0txyff5w4` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `track_review`
--

LOCK TABLES `track_review` WRITE;
/*!40000 ALTER TABLE `track_review` DISABLE KEYS */;
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
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `umspreadsheet-dev`.`track_review_AFTER_INSERT` AFTER INSERT ON `track_review` FOR EACH ROW
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
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `umspreadsheet-dev`.`track_review_AFTER_UPDATE` AFTER UPDATE ON `track_review` FOR EACH ROW
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
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `umspreadsheet-dev`.`track_review_AFTER_DELETE` AFTER DELETE ON `track_review` FOR EACH ROW
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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `is_enabled` bit(1) NOT NULL,
  `is_not_banned` tinyint(1) NOT NULL,
  `is_not_suspended` tinyint(1) NOT NULL,
  `joined_on` datetime DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`),
  UNIQUE KEY `UK_a3imlf41l37utmxiquukk8ajc` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin@umspreadsheet.com','',1,1,'2017-05-17 18:18:39','$2a$10$jaTbaB2Q/sM.LIaDxvg.meulnOe/.znr7pSbmB4f9HsbJR9A/MP5.',NULL,'admin'),(2,'mod@umspreadsheet.com','',1,1,'2017-05-17 18:18:39','$2a$10$8CMaghJAhndK1TDN1SRLVekFOQokHyZiciiWjKB6tVkBWGWSsctUq',NULL,'mod'),(3,'user@umspreadsheet.com','',1,1,'2017-05-17 18:18:39','$2a$10$ASqnyHbWIX9gCd5exP7v6evQTzTBSCvA48NbtRRxRQDR5qpQYXlX6',NULL,'user');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
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
  KEY `FKt4v0rrweyk393bdgt107vdx0x` (`role_id`),
  KEY `FKgd3iendaoyh04b95ykqise6qh` (`user_id`),
  CONSTRAINT `FKgd3iendaoyh04b95ykqise6qh` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKt4v0rrweyk393bdgt107vdx0x` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (1,1),(2,2),(3,3);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `verification_token`
--

DROP TABLE IF EXISTS `verification_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `verification_token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `expiration_date` datetime DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrdn0mss276m9jdobfhhn2qogw` (`user_id`),
  CONSTRAINT `FKrdn0mss276m9jdobfhhn2qogw` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `verification_token`
--

LOCK TABLES `verification_token` WRITE;
/*!40000 ALTER TABLE `verification_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `verification_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wormblog_posts`
--

DROP TABLE IF EXISTS `wormblog_posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wormblog_posts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `body` text NOT NULL,
  `posted_on` datetime DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL,
  `title` varchar(200) NOT NULL,
  `author_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2d9i5mcrs8qiaspubtjdgtj6m` (`author_id`),
  CONSTRAINT `FK2d9i5mcrs8qiaspubtjdgtj6m` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wormblog_posts`
--

LOCK TABLES `wormblog_posts` WRITE;
/*!40000 ALTER TABLE `wormblog_posts` DISABLE KEYS */;
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

-- Dump completed on 2017-05-17 21:23:29
