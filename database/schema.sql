-- MySQL dump 10.13  Distrib 5.5.44, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: dinky
-- ------------------------------------------------------
-- Server version	5.5.44-0ubuntu0.14.04.1

--
-- Table structure for table `corpus`
--

DROP TABLE IF EXISTS `corpus`;

CREATE TABLE `corpus` (
  `id` int(11) NOT NULL,
  `passphrase` varchar(45) NOT NULL,
  `creationDate` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Table structure for table `story`
--

DROP TABLE IF EXISTS `story`;

CREATE TABLE `story` (
  `corpus` int(11) NOT NULL,
  `element1` int(11) NOT NULL,
  `element2` int(11) DEFAULT NULL,
  `element3` int(11) DEFAULT NULL,
  `element4` int(11) DEFAULT NULL,
  `element5` int(11) DEFAULT NULL,
  `element6` int(11) DEFAULT NULL,
  `element7` int(11) DEFAULT NULL,
  `element8` int(11) DEFAULT NULL,
  `element9` int(11) DEFAULT NULL,
  `element10` int(11) DEFAULT NULL,
  `element11` int(11) DEFAULT NULL,
  `element12` int(11) DEFAULT NULL,
  `element13` int(11) DEFAULT NULL,
  `element14` int(11) DEFAULT NULL,
  `element15` int(11) DEFAULT NULL,
  `element16` int(11) DEFAULT NULL,
  KEY `fk_story_1_idx` (`corpus`),
  CONSTRAINT `fk_story_1` FOREIGN KEY (`corpus`) REFERENCES `corpus` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

