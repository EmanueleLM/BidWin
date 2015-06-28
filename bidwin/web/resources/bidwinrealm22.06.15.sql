-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Giu 22, 2015 alle 22:11
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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=69 ;

--
-- Dump dei dati per la tabella `auction`
--

INSERT INTO `auction` (`Auction_id`, `Object_id`, `StartTime`, `EndTime`, `notify`) VALUES
(58, 39, '2015-06-19 13:11:00', '2015-06-19 13:16:00', b'1'),
(59, 41, '2015-06-19 19:44:51', '2015-06-19 19:49:51', b'1'),
(60, 42, '2015-06-20 06:49:28', '2015-06-20 06:54:28', b'1'),
(61, 43, '2015-06-20 07:01:56', '2015-06-20 07:06:56', b'1'),
(62, 44, '2015-06-20 08:20:31', '2015-06-20 08:25:31', b'1'),
(63, 40, '2015-06-20 10:27:06', '2015-06-20 10:32:06', b'1'),
(64, 45, '2015-06-20 12:34:14', '2015-06-20 12:39:14', b'1'),
(65, 46, '2015-06-20 13:18:20', '2015-06-20 13:23:20', b'1'),
(66, 47, '2015-06-21 08:46:42', '2015-06-21 08:51:42', b'1'),
(67, 48, '2015-06-21 20:23:56', '2015-06-21 20:43:56', b'1'),
(68, 49, '2015-06-22 06:25:29', '2015-06-22 06:50:29', b'1');

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
('pollo', 58, 5),
('user', 58, 300002),
('utente', 58, 5),
('momo', 59, 1),
('pollo', 59, 3),
('user', 59, 300002),
('user', 60, 3),
('user', 60, 300002),
('utente', 60, 6),
('momo', 61, 3),
('momo', 61, 5),
('user', 61, 300002),
('utente', 61, 5),
('momo', 62, 7),
('user', 62, 300002),
('utente', 62, 5),
('pollo', 63, 10),
('user', 63, 300002),
('utente', 63, 4),
('momo', 64, 1),
('user', 64, 300002),
('pollo', 65, 1),
('user', 65, 300002),
('momo', 66, 3),
('pollo', 66, 5),
('pollo', 66, 7),
('user', 66, 300002),
('momo', 67, 8),
('momo', 67, 9),
('user', 67, 300002),
('utente', 67, 5),
('momo', 68, 3),
('momo', 68, 5),
('user', 68, 300002),
('utente', 68, 13),
('utente', 68, 18);

-- --------------------------------------------------------

--
-- Struttura della tabella `groups`
--

CREATE TABLE IF NOT EXISTS `groups` (
  `group_id` int(10) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(20) NOT NULL,
  `group_desc` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=33 ;

--
-- Dump dei dati per la tabella `notifications`
--

INSERT INTO `notifications` (`notificationid`, `username`, `auction_id`, `notificationtype`) VALUES
(3, 'momo', 58, 5),
(4, 'pollo', 58, 3),
(5, 'utente', 58, 3),
(6, 'user', 59, 4),
(7, 'momo', 59, 10),
(8, 'pollo', 59, 2),
(9, 'pollo', 60, 5),
(10, 'utente', 60, 3),
(11, 'pollo', 61, 4),
(12, 'momo', 61, 10),
(13, 'utente', 61, 2),
(14, 'enrico', 62, 4),
(15, 'momo', 62, 2),
(16, 'utente', 62, 1),
(17, 'momo', 63, 4),
(18, 'pollo', 63, 2),
(19, 'utente', 63, 1),
(20, 'pollo', 64, 4),
(21, 'momo', 64, 10),
(22, 'momo', 65, 4),
(23, 'pollo', 65, 10),
(24, 'emanuele', 66, 4),
(25, 'momo', 66, 10),
(26, 'pollo', 66, 2),
(27, 'emanuele', 67, 4),
(28, 'momo', 67, 2),
(29, 'utente', 67, 1),
(30, 'emanuele', 68, 4),
(31, 'momo', 68, 1),
(32, 'utente', 68, 2);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=50 ;

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
(37, 'gatto', 'gatto', 'gatto', 'gatto', 'momo'),
(39, 'comodino', 'comodino', 'comodino', 'comodino', 'momo'),
(40, 'comodino', 'comodino', 'comodino', 'comodino', 'momo'),
(41, 'bbb', 'bbb', 'bbb', 'bbb', 'user'),
(42, 'nuvola', 'nuvola', 'nuvola', 'nuvola', 'pollo'),
(43, 'nuvola', 'nuvola', 'nuvola', 'nuvola', 'pollo'),
(44, 'collare', 'collare', 'collare', 'collare', 'enrico'),
(45, 'eee', 'eee', 'eee', 'eee', 'pollo'),
(46, 's', 's', 's', 's', 'momo'),
(47, 'frullatore', 'cucina', 'frullatore Sony 3500xp', '/src/frullatore.jpg', 'emanuele'),
(48, 'Sony Vaio', 'computer', 'Sony Vaio 17''', '/src/computer.png', 'emanuele'),
(49, 'Topolino #756', 'fumetti', 'Topolino #756 anno 1994', '/src/fumetti.jpg', 'emanuele');

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
('emanuele', 1, NULL),
('enrico', 1, NULL),
('momo', 1, NULL),
('pollo', 1, NULL),
('user', 2, NULL),
('utente', 1, NULL);

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
('emanuele', 'emanuele', 'la malfa', 'emanuele.lamalfa@mail.mail.it', 500, 'via marconi', 'shoflndffdf45fddff', 1, '1993-01-19', 100, '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8'),
('enrico', 'enrico', 'progetto', 'enrico@enrico.it', 0, 'here', 'daftrg45gfdf', 0, '1991-01-01', 100, '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8'),
('momo', 'ertyui', 'fghjk', 'yuio@polimi.iu', 500, 'cfvgyih', 'pljjio', 1, '0066-06-06', 64, '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8'),
('pollo', 'pollo', 'pollo', 'pollo@po.po', 500, 'pollo', 'pollo', 2, '0011-04-04', 52, '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8'),
('user', 'vtgbhj', 'vgyhjk', 'vgbh@bu.po', 200, 'fvgbhjn', 'vghbjnk', 1, '0088-08-08', 471, '04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb'),
('userq', 'cfvgh', 'ycfvghj', 'vgh@oj.ivyi', 0, 'cftvhj', 'rcyvgbhj', 0, '0044-04-04', 100, 'a95bc16631ae2b6fadb455ee018da0adc2703e56d89e3eed074ce56d2f7b1b6a'),
('utente', 'ASDFG', 'ASDFG', 'asdfg@po.oi', 0, 'ASDFG', 'ASDFYguyvjb', 0, '0055-05-05', 81, '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8');

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