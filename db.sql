/*
SQLyog Community v13.1.7 (64 bit)
MySQL - 10.4.17-MariaDB : Database - baobabTextBoard
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`baobabTextBoard` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `baobabTextBoard`;

/*Table structure for table `article` */

DROP TABLE IF EXISTS `article`;

CREATE TABLE `article` (
  `num` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `memberNum` int(10) unsigned NOT NULL,
  `boardNum` int(10) unsigned NOT NULL,
  `title` char(200) NOT NULL,
  `body` longtext NOT NULL,
  `hitsCount` int(10) unsigned NOT NULL DEFAULT 0,
  `replyCount` int(10) unsigned NOT NULL DEFAULT 0,
  PRIMARY KEY (`num`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4;

/*Data for the table `article` */

insert  into `article`(`num`,`regDate`,`updateDate`,`memberNum`,`boardNum`,`title`,`body`,`hitsCount`,`replyCount`) values 
(23,'2021-06-22 11:32:47','2021-06-22 11:32:47',1,5,'aa','# aa12323213\r\n- asdsad\r\nasd\r\ncccasas',0,0);

/*Table structure for table `board` */

DROP TABLE IF EXISTS `board`;

CREATE TABLE `board` (
  `num` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `name` char(13) NOT NULL,
  `code` char(10) NOT NULL,
  `menu_depth` int(2) NOT NULL,
  `parent_code` char(15) DEFAULT NULL,
  `menu_url` varchar(200) NOT NULL,
  PRIMARY KEY (`num`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

/*Data for the table `board` */

insert  into `board`(`num`,`regDate`,`updateDate`,`name`,`code`,`menu_depth`,`parent_code`,`menu_url`) values 
(1,'2021-06-04 15:10:24','2021-06-04 15:10:26','Web Programs','web',1,NULL,'article_list_java_1.html'),
(2,'2021-06-04 15:12:13','2021-06-04 15:12:15','DataBase','db',1,NULL,'article_list_mysql_1.html'),
(3,'2021-06-04 15:12:32','2021-06-04 15:12:34','Server','server',1,NULL,'article_list_centos_1.html'),
(4,'2021-06-04 15:12:49','2021-06-04 15:12:52','Free','free',1,NULL,'article_list_daily_1.html'),
(5,'2021-06-04 15:34:46','2021-06-04 15:34:48','Java','java',2,'web','article_list_java_1.html'),
(6,'2021-06-04 15:35:11','2021-06-04 15:35:13','JavaScript','js',2,'web','article_list_js_1.html'),
(7,'2021-06-04 15:36:27','2021-06-04 15:35:13','Spring','spring',2,'web','article_list_spring_1.html'),
(8,'2021-06-04 15:36:27','2021-06-04 15:35:13','Mysql','mysql',2,'db','article_list_mysql_1.html'),
(9,'2021-06-04 15:35:13','2021-06-04 15:35:13','CentOs','centos',2,'server','article_list_centos_1.html'),
(10,'2021-06-04 15:35:13','2021-06-04 15:35:13','Daily','daily',2,'free','article_list_daily_1.html');

/*Table structure for table `member` */

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
  `num` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `name` char(10) NOT NULL,
  `loginId` char(20) NOT NULL,
  `loginPw` char(200) NOT NULL,
  PRIMARY KEY (`num`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `member` */

insert  into `member`(`num`,`regDate`,`updateDate`,`name`,`loginId`,`loginPw`) values 
(1,'2021-05-25 22:39:53','2021-05-25 22:39:53','방혜성','test2','test2'),
(2,'2021-05-26 00:17:17','2021-05-26 00:17:17','aaa','aaa','aaa');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
