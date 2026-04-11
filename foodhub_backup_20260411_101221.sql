-- MySQL dump 10.13  Distrib 8.0.45, for Linux (x86_64)
--
-- Host: localhost    Database: foodhub
-- ------------------------------------------------------
-- Server version	8.0.45-0ubuntu0.24.04.1

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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK46ccwnsi9409t36lurvtyljak` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,NULL,'Starters'),(2,NULL,'Chinese'),(3,NULL,'Rice'),(4,NULL,'Seafood'),(5,NULL,'Main Course'),(6,NULL,'Desserts'),(7,NULL,'Drinks');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `comment` varchar(2000) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `customer_name` varchar(255) DEFAULT NULL,
  `rating` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (9,'Good','2026-04-10 22:38:02.550921','Sandra',4),(10,'OK','2026-04-11 01:32:39.814925','Agnel',2);
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food_item`
--

DROP TABLE IF EXISTS `food_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `food_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `category` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `is_available` bit(1) NOT NULL,
  `is_veg` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=152 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food_item`
--

LOCK TABLES `food_item` WRITE;
/*!40000 ALTER TABLE `food_item` DISABLE KEYS */;
INSERT INTO `food_item` VALUES (1,'Starters','https://c.ndtvimg.com/2023-09/2fugrm2_paneer-tikka_625x300_18_September_23.jpg','Paneer Tikka Skewers',270,_binary '',_binary ''),(2,'Starters','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTza89XuqcvI6HAxx4cgkRt2Q__7pusfLjiCQ&s','Crispy Spring Rolls',220,_binary '',_binary ''),(3,'Starters','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRWJhbQLhckD-sehrQ8P0mY4bVjGhJRbvNIUA&s','Gobi Manchurian Dry',240,_binary '',_binary ''),(4,'Starters','https://www.foodandwine.com/thmb/ErNZTmhSUt3HiOwy4JujFiQM9co=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/Chicken-Tikka-Kebabs-FT-RECIPE0622-3c77b6c2efa04e1c946b320c6db30a91.jpg','Chicken Tikka Skewers',350,_binary '',_binary ''),(5,'Starters','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSQEB8PNddOkmNLCr1CwPyeS_ivhGFb2f7c3g&s','Lamb Kebabs',450,_binary '',_binary ''),(6,'Starters','https://loremflickr.com/500/400/meat,chops?lock=6','Mutton Chops',480,_binary '',_binary '\0'),(7,'Chinese','https://static.toiimg.com/thumb/75356205.cms?width=1200&height=900','Veg Hakka Noodles',220,_binary '',_binary ''),(8,'Chinese','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSxO3S1B_k4rUZaSyZPjWRaAqEyAy--LrzTwQ&s','Chilli Paneer Gravy',280,_binary '',_binary ''),(9,'Chinese','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTuDttBX4uGzZ1Y5POXeUaPMkVCYtayAsijVw&s','Veg Fried Rice',210,_binary '',_binary ''),(10,'Chinese','https://i.ytimg.com/vi/J2v6Pwgy4Uk/hq720.jpg?sqp=-oaymwEhCK4FEIIDSFryq4qpAxMIARUAAAAAGAElAADIQj0AgKJD&rs=AOn4CLAs4D2DaKvDxhUBOwdHxv2PEUSTuw','Chicken Hakka Noodles',290,_binary '',_binary ''),(11,'Chinese','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTaRXJoX1SPrsWOmLCWhA_xbfyT6u7hL-HtuA&s','Chilli Chicken Dry',320,_binary '',_binary ''),(12,'Chinese','https://loremflickr.com/500/400/egg,friedrice?lock=12','Egg Fried Rice',250,_binary '',_binary '\0'),(13,'Rice','https://loremflickr.com/500/400/biryani,veg?lock=13','Hyderabadi Veg Biryani',290,_binary '',_binary ''),(14,'Rice','https://loremflickr.com/500/400/rice,jeera?lock=14','Jeera Rice',180,_binary '',_binary ''),(15,'Rice','https://loremflickr.com/500/400/biryani,rice?lock=15','Soya Chaap Biryani',310,_binary '',_binary ''),(16,'Rice','https://i.ytimg.com/vi/AqDdTR6XD3o/hq720.jpg?sqp=-oaymwEhCK4FEIIDSFryq4qpAxMIARUAAAAAGAElAADIQj0AgKJD&rs=AOn4CLBiP2SaqLBfAQxs863CZhWQQIYY8A','Chicken Dum Biryani',380,_binary '',_binary ''),(17,'Rice','https://loremflickr.com/500/400/mutton,biryani?lock=17','Mutton Biryani',450,_binary '',_binary '\0'),(18,'Rice','https://loremflickr.com/500/400/biryani,egg?lock=18','Egg Biryani',280,_binary '',_binary '\0'),(19,'Seafood','https://www.northseafarmers.org/news/2025/news/07-july/crispyfish.jpg','Crispy Plant-based Fish',320,_binary '',_binary ''),(20,'Seafood','https://www.thewasabicompany.co.uk/cdn/shop/articles/wakame-seaweed-salad-recipe.webp','Seaweed Salad',280,_binary '',_binary ''),(21,'Seafood','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQiU2dlfo_JQuEl2e-qQuSI-cUsONNJ5Pu62A&s','Vegan Sushi Roll',400,_binary '',_binary ''),(22,'Seafood','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQsw-eav9dsAQYA_wH_lbQ4M4nXf9j_WRXyrg&s','Goan Fish Curry',480,_binary '',_binary ''),(23,'Seafood','https://loremflickr.com/500/400/prawns,garlic?lock=23','Butter Garlic Prawns',550,_binary '',_binary '\0'),(24,'Seafood','https://loremflickr.com/500/400/grilled,salmon?lock=24','Grilled Salmon',850,_binary '',_binary '\0'),(25,'Main Course','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT0aV-Brpr-4Wo3oS5o3cUp9jVORki5hwSYYw&s','Palak Paneer',290,_binary '',_binary ''),(26,'Main Course','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcThaSoxpvVNzEh_jLBofQVC-0IfFz34BBuFeQ&s','Dal Makhani',250,_binary '',_binary ''),(27,'Main Course','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSmxB-hyyTEU6CDdZpWJcVQT18aD5aIW7lAdQ&s','Malai Kofta',320,_binary '',_binary ''),(28,'Main Course','https://loremflickr.com/500/400/chicken,tikka?lock=28','Chicken Tikka Masala',390,_binary '',_binary '\0'),(29,'Main Course','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRyydlpFTfyMMtWwwUR96d7B0c-nDeSLirdRg&s','Mutton Rogan Josh',480,_binary '',_binary ''),(30,'Main Course','https://www.whiskaffair.com/wp-content/uploads/2020/04/Kerala-Style-Egg-Curry-2-3.jpg','Egg Curry',260,_binary '',_binary ''),(31,'Desserts','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQcQ5ajWr8uH8ucnNcpqa9irsm0aTGEDZN4hw&s','Gulab Jamun',150,_binary '',_binary ''),(32,'Desserts','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSeJBOCqSaAYEsuxwkxy5fkFaAx3202YfcHUg&s','Chocolate Brownie',220,_binary '',_binary ''),(33,'Desserts','https://img.taste.com.au/jJzBRg7p/taste/2016/11/how-to-choose-mangoes-in-the-supermarket-90398-2.jpeg','Mango Cheesecake',280,_binary '',_binary ''),(34,'Desserts','https://loremflickr.com/500/400/tiramisu,dessert?lock=34','Classic Tiramisu',320,_binary '',_binary '\0'),(35,'Desserts','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSEezdhLnOkqv9Ho9JN3cuwU3cSsdJ84o7iTw&s','Macarons',300,_binary '',_binary ''),(36,'Desserts','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSwPC3qZs8od5d7z_kOPOQhgZF_3V4IyOSsTA&s','Crème Brûlée',350,_binary '',_binary ''),(37,'Drinks','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRkeQxlQC87d8GWfHn0BcF50bVPibZPR0NLfg&s','Fresh Lime Soda',120,_binary '',_binary ''),(38,'Drinks','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRV5Q6U-3vLaV4HDL3ZzOGrr74_1wznjiNc-w&s','Mango Lassi',150,_binary '',_binary ''),(39,'Drinks','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTSVYAb9l2oOQ4iRwqARLLwHoTuyZgY9RKT0Q&s','Cold Coffee',180,_binary '',_binary ''),(40,'Drinks','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTua7gve7vbsSFttQijWYm8GVRDTY5r9NsKhw&s','Berry Smoothie',210,_binary '',_binary ''),(41,'Drinks','https://static.toiimg.com/thumb/84339280.cms?imgsize=327880&width=800&height=800','Iced Lemon Tea',150,_binary '',_binary ''),(42,'Drinks','https://loremflickr.com/500/400/mojito,mint?lock=42','Mint Mojito',200,_binary '',_binary ''),(145,'Main',NULL,'Margherita Pizza',12.99,_binary '',_binary ''),(146,'Main',NULL,'Margherita Pizza',12.99,_binary '',_binary ''),(147,'Main',NULL,'Margherita Pizza',12.99,_binary '',_binary ''),(148,'Main',NULL,'Margherita Pizza',12.99,_binary '',_binary ''),(149,'Main',NULL,'Margherita Pizza',12.99,_binary '',_binary ''),(150,'Main',NULL,'Margherita Pizza',12.99,_binary '',_binary ''),(151,'Main',NULL,'Margherita Pizza',12.99,_binary '',_binary '');
/*!40000 ALTER TABLE `food_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_items` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `price` double NOT NULL,
  `quantity` int DEFAULT NULL,
  `food_item_id` bigint DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  `special_instructions` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn6l4ybgcwkl53vy98hdm2c5mt` (`food_item_id`),
  KEY `FKbioxgbv59vetrxe0ejfubep1w` (`order_id`),
  CONSTRAINT `FKbioxgbv59vetrxe0ejfubep1w` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `FKn6l4ybgcwkl53vy98hdm2c5mt` FOREIGN KEY (`food_item_id`) REFERENCES `food_item` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
INSERT INTO `order_items` VALUES (3,250,1,12,2,NULL),(4,240,1,3,3,NULL),(5,220,1,2,4,NULL),(6,220,1,2,5,NULL),(7,270,1,1,6,NULL),(9,210,1,40,8,NULL),(10,350,1,4,9,NULL),(11,550,2,23,9,NULL);
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `table_number` int DEFAULT NULL,
  `total_amount` double NOT NULL,
  `customer_username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (2,'2026-04-09 02:52:03.258036','SERVED',8,250,'guest'),(3,'2026-04-09 02:54:36.920187','SERVED',4,240,'guest'),(4,'2026-04-09 02:56:38.735290','SERVED',10,220,'guest'),(5,'2026-04-09 03:19:56.265175','SERVED',7,220,'guest'),(6,'2026-04-09 03:29:30.116636','SERVED',6,270,'guest'),(8,'2026-04-11 00:21:00.841831','SERVED',7,210,'guest'),(9,'2026-04-11 01:31:47.438143','SERVED',6,1450,'guest');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','ADMIN','admin'),(2,'guest','CUSTOMER','guest'),(6,'kitchen','KITCHEN','kitchen'),(7,'waiter','WAITER','waiter');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `waiter_calls`
--

DROP TABLE IF EXISTS `waiter_calls`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `waiter_calls` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `resolved_at` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `table_number` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `waiter_calls`
--

LOCK TABLES `waiter_calls` WRITE;
/*!40000 ALTER TABLE `waiter_calls` DISABLE KEYS */;
INSERT INTO `waiter_calls` VALUES (1,'2026-04-09 02:49:21.130058','2026-04-09 03:01:36.356289','RESOLVED',4),(2,'2026-04-09 02:52:10.232501','2026-04-09 03:02:23.448369','RESOLVED',8),(3,'2026-04-09 02:56:40.829674','2026-04-09 03:02:24.799518','RESOLVED',10),(4,'2026-04-09 02:56:41.744644','2026-04-09 03:02:25.851408','RESOLVED',10),(5,'2026-04-09 02:56:44.189964','2026-04-09 03:02:27.077075','RESOLVED',10),(6,'2026-04-09 03:02:54.174989','2026-04-09 03:06:52.853604','RESOLVED',10),(7,'2026-04-09 03:05:04.562501','2026-04-09 03:06:54.575773','RESOLVED',10),(8,'2026-04-09 03:06:30.259531','2026-04-09 03:06:56.529230','RESOLVED',4),(9,'2026-04-09 03:07:17.581397','2026-04-09 03:07:35.267939','RESOLVED',4),(10,'2026-04-09 03:07:56.483868','2026-04-09 03:08:11.743079','RESOLVED',2),(11,'2026-04-09 03:08:28.641369','2026-04-09 03:08:52.482534','RESOLVED',3),(12,'2026-04-09 03:10:22.272658','2026-04-09 03:14:08.415046','RESOLVED',3),(13,'2026-04-09 03:13:32.710938','2026-04-09 03:14:06.450285','RESOLVED',2),(14,'2026-04-09 03:20:00.855809','2026-04-09 03:20:16.374876','RESOLVED',7),(15,'2026-04-09 03:22:41.674566','2026-04-09 03:22:57.115371','RESOLVED',10),(16,'2026-04-09 03:23:25.719524','2026-04-09 03:23:38.747032','RESOLVED',10),(17,'2026-04-09 03:30:28.501461','2026-04-09 03:31:55.840453','RESOLVED',6),(18,'2026-04-11 01:32:18.477279','2026-04-11 01:33:22.176202','RESOLVED',6);
/*!40000 ALTER TABLE `waiter_calls` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-11 10:12:25
