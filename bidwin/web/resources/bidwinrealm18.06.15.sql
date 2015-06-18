-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Giu 17, 2015 alle 17:56
-- Versione del server: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

drop database bidwinrealm;
drop user 'user'@'localhost';
create database bidwinrealm;

create user 'user'@'localhost' identified by 'user';
grant INSERT,DELETE,UPDATE,SELECT on bidwinrealm.* to 'user'@'localhost';
grant create,drop on bidwinrealm1.* to 'user'@'localhost';
flush privileges;

use bidwinrealm;

drop table if exists auction;
drop table if exists bid;
drop table if exists objects;
drop table if exists users;
drop table if exists groups;
drop table if exists user_groups;
drop view if exists v_user_role;
drop table if exists notifications;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `bidwinrealm`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `auction`
--

CREATE TABLE IF NOT EXISTS `auction` (
  `Auction_id` int(9) NOT NULL AUTO_INCREMENT,
  `Object_id` int(11) NOT NULL,
  `StartTime` timestamp NULL DEFAULT NULL,
  `EndTime` timestamp NULL DEFAULT NULL,
  `notify` bit(1) DEFAULT b'0',
  PRIMARY KEY (`Auction_id`),
  UNIQUE KEY `Objects-id` (`Object_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=57 ;

--
-- Dump dei dati per la tabella `auction`
--

INSERT INTO `auction` (`Auction_id`, `Object_id`, `StartTime`, `EndTime`, `notify`) VALUES
(3, 9, '2015-06-14 10:04:55', '2015-06-14 10:34:55', b'1'),
(43, 8, '2015-06-16 06:19:47', '2015-06-16 06:49:47', b'1'),
(44, 23, '2015-06-16 15:35:35', '2015-06-16 16:05:35', b'1'),
(45, 24, '2015-06-16 16:35:31', '2015-06-16 17:05:31', b'1'),
(46, 25, '2015-06-16 17:12:02', '2015-06-16 17:42:02', b'1'),
(47, 26, '2015-06-16 17:54:27', '2015-06-16 18:24:27', b'1'),
(48, 27, '2015-06-16 18:36:20', '2015-06-16 19:06:20', b'1'),
(49, 28, '2015-06-16 19:03:32', '2015-06-16 19:33:32', b'1'),
(50, 29, '2015-06-16 19:03:41', '2015-06-16 19:33:41', b'1'),
(51, 30, '2015-06-16 19:57:28', '2015-06-16 20:27:28', b'1'),
(52, 32, '2015-06-17 06:36:53', '2015-06-17 07:06:53', b'1'),
(53, 33, '2015-06-17 07:44:34', '2015-06-17 08:14:34', b'1'),
(54, 35, '2015-06-17 08:33:56', '2015-06-17 09:03:56', b'1'),
(55, 36, '2015-06-17 09:15:56', '2015-06-18 09:45:56', b'1'),
(56, 37, '2015-06-17 13:44:32', '2015-06-17 14:14:32', b'1');

-- --------------------------------------------------------

--
-- Struttura della tabella `bid`
--

CREATE TABLE IF NOT EXISTS `bid` (
  `Username` varchar(60) NOT NULL,
  `Auction_id` int(11) NOT NULL,
  `Value` int(11) NOT NULL,
  PRIMARY KEY (`Username`,`Auction_id`,`Value`),
  KEY `bid` (`Auction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `bid`
--

INSERT INTO `bid` (`Username`, `Auction_id`, `Value`) VALUES
('momo', 43, 0),
('user', 43, 6),
('user', 43, 7),
('utente', 43, 18),
('pollo', 45, 4),
('user', 48, 10),
('user', 51, 1),
('user', 51, 4),
('pollo', 52, 1),
('pollo', 52, 5),
('pollo', 52, 13),
('pollo', 52, 21),
('pollo', 52, 46),
('pollo', 52, 95),
('pollo', 52, 500),
('pollo', 54, 0),
('pollo', 54, 7),
('pollo', 54, 24),
('momo', 55, 9),
('momo', 55, 100),
('user', 55, 4),
('utente', 55, 8),
('utente', 56, 7),
('utente', 56, 8);

-- --------------------------------------------------------

--
-- Struttura della tabella `groups`
--

CREATE TABLE IF NOT EXISTS `groups` (
  `group_id` int(10) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(20) NOT NULL,
  `group_desc` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `groups`
--

INSERT INTO `groups` (`group_id`, `group_name`, `group_desc`) VALUES
(1, 'USER', 'Regular users'),
(2, 'ADMIN', 'Administration users');

-- --------------------------------------------------------

--
-- Struttura della tabella `notifications`
--

CREATE TABLE IF NOT EXISTS `notifications` (
  `notificationid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(60) NOT NULL,
  `auction_id` int(11) NOT NULL,
  `notificationtype` int(20) DEFAULT NULL,
  PRIMARY KEY (`notificationid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `objects`
--

CREATE TABLE IF NOT EXISTS `objects` (
  `Object_id` int(11) NOT NULL AUTO_INCREMENT,
  `ObjectName` varchar(60) NOT NULL,
  `ObjectType` varchar(60) NOT NULL,
  `Description` varchar(255) NOT NULL,
  `ImageLink` varchar(255) NOT NULL,
  `Username` varchar(60) NOT NULL,
  PRIMARY KEY (`Object_id`),
  KEY `belongs to` (`Username`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=38 ;

--
-- Dump dei dati per la tabella `objects`
--

INSERT INTO `objects` (`Object_id`, `ObjectName`, `ObjectType`, `Description`, `ImageLink`, `Username`) VALUES
(8, 'ancora', 'anocra', 'ancora', '/src', 'pollo'),
(9, 'frullatore', 'frullaotre', 'frulla', '/src', 'user'),
(23, 'arcolaio', 'ladra', 'di', 'polli', 'user'),
(24, 'panino', 'fritto', 'fritto', 'con pollo', 'user'),
(25, 'qw', 'qwe', 'erew', 'ewr', 'utente'),
(26, 'palla', 'palla', 'palla', 'palla', 'pollo'),
(27, 'arco', 'arco', 'arco', '/src.png', 'pollo'),
(28, 'molla', 'molla', 'molla', 'molla', 'user'),
(29, 'sasso', 'sasso', 'sasso', 'sasso', 'user'),
(30, 'a', 'a', 'a', 'a', 'pollo'),
(31, 'b', 'b', 'b', 'b', 'pollo'),
(32, 'campana', 'ca', 'ca', 'ca', 'user'),
(33, 'pollo', 'pollo', 'pollo', 'pollo', 'user'),
(34, 'vestito', 'vestito', 'vestito', 'vestito', 'user'),
(35, 'vestito', 'vestito', 'vestito', 'vestito', 'user'),
(36, 'scopa', 'scopa', 'scopa', 'scoap', 'pollo'),
(37, 'gatto', 'gatto', 'gatto', 'gatto', 'momo');

-- --------------------------------------------------------

--
-- Struttura della tabella `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `Username` varchar(60) NOT NULL,
  `Name` varchar(60) NOT NULL,
  `Surname` varchar(60) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Ranking` int(1) NOT NULL,
  `Address` varchar(255) NOT NULL,
  `PaymentInfo` varchar(255) NOT NULL,
  `AuctionCounter` int(6) NOT NULL,
  `Birthdate` date NOT NULL,
  `Credits` int(9) NOT NULL,
  `Password` varchar(100) NOT NULL,
  PRIMARY KEY (`Username`),
  UNIQUE KEY `Username` (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `users`
--

INSERT INTO `users` (`Username`, `Name`, `Surname`, `Email`, `Ranking`, `Address`, `PaymentInfo`, `AuctionCounter`, `Birthdate`, `Credits`, `Password`) VALUES
('momo', 'ertyui', 'fghjk', 'yuio@polimi.iu', 0, 'cfvgyih', 'pljjio', 0, '0066-06-06', 86, '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8'),
('pollo', 'pollo', 'pollo', 'pollo@po.po', 0, 'pollo', 'pollo', 0, '0011-04-04', 64, '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8'),
('user', 'vtgbhj', 'vgyhjk', 'vgbh@bu.po', 0, 'fvgbhjn', 'vghbjnk', 0, '0088-08-08', 473, '04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb'),
('userq', 'cfvgh', 'ycfvghj', 'vgh@oj.ivyi', 0, 'cftvhj', 'rcyvgbhj', 0, '0044-04-04', 100, 'a95bc16631ae2b6fadb455ee018da0adc2703e56d89e3eed074ce56d2f7b1b6a'),
('utente', 'ASDFG', 'ASDFG', 'asdfg@po.oi', 0, 'ASDFG', 'ASDFYguyvjb', 0, '0055-05-05', 82, '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8');

-- --------------------------------------------------------

--
-- Struttura della tabella `user_groups`
--

CREATE TABLE IF NOT EXISTS `user_groups` (
  `Username` varchar(60) NOT NULL,
  `group_id` int(10) NOT NULL,
  `uriId` int(10) DEFAULT NULL,
  PRIMARY KEY (`Username`,`group_id`),
  KEY `fk_groups` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `user_groups`
--

INSERT INTO `user_groups` (`Username`, `group_id`, `uriId`) VALUES
('momo', 1, NULL),
('pollo', 1, NULL),
('user', 2, NULL),
('utente', 1, NULL);

-- --------------------------------------------------------

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `auction`
--
ALTER TABLE `auction`
  ADD CONSTRAINT `is related` FOREIGN KEY (`Object_id`) REFERENCES `objects` (`Object_id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Limiti per la tabella `bid`
--
ALTER TABLE `bid`
  ADD CONSTRAINT `bid` FOREIGN KEY (`Auction_id`) REFERENCES `auction` (`Auction_id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `bidder` FOREIGN KEY (`Username`) REFERENCES `users` (`Username`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Limiti per la tabella `objects`
--
ALTER TABLE `objects`
  ADD CONSTRAINT `belongs to` FOREIGN KEY (`Username`) REFERENCES `users` (`Username`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Limiti per la tabella `user_groups`
--
ALTER TABLE `user_groups`
  ADD CONSTRAINT `fk_groups` FOREIGN KEY (`group_id`) REFERENCES `groups` (`group_id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_users` FOREIGN KEY (`Username`) REFERENCES `users` (`Username`) ON DELETE NO ACTION ON UPDATE CASCADE;
	
	
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
