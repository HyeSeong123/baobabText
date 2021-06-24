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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `article` */

insert  into `article`(`num`,`regDate`,`updateDate`,`memberNum`,`boardNum`,`title`,`body`,`hitsCount`,`replyCount`) values 
(1,'2021-06-22 11:32:47','2021-06-22 11:32:47',1,8,'AUTO_INCREMENT 초기화','게시물을 테스트 하다가 전부 삭제하게 되면\r\nAUTO_INCREMENT로 지정된 값은 초기화가 안되고 아래와 같이 마지막 값을 유지하게 된다.\r\n\r\n<div class=\"img-box1\">\r\n    <img src=\"img/게시물/1/1.PNG\" />\r\n</div>\r\n\r\n초기화 방법은\r\nALTER TABLE [테이블 명] AUTO_INCREMENT = [원하는 숫자 - 1];\r\n이다.\r\n\r\n사용 예시\r\n```\r\nALTER TABLE article AUTO_INCREMENT = 2;\r\n```\r\n<div class=\"img-box1\">\r\n    <img src=\"img/게시물/1/2.PNG\" />\r\n</div>\r\n\r\n변경 완료!',0,0),
(2,'2021-06-24 16:25:22','2021-06-24 16:25:23',1,11,'웹 그라디언트/컬러 코드 사이트','제가 웹 디자인을 할 때 자주 이용하는 사이트 입니다.\r\n컬러 코드, 그라데이션 색 코드를 받을 수 있습니다.\r\n\r\n1. 컬러 코드 사이트\r\n    - [http://www.colinkeany.com/blend/](http://www.colinkeany.com/blend/) <div class=\"img-box1\"><img src=\"img/게시물/2/1.PNG\" /></div>\r\n        - 이전 색과 변경 색을 두고 차이를 보면서 색을 변경할 수 있다.\r\n    ---\r\n    - [color picker](https://www.google.com/search?q=color+picker&oq=color&aqs=chrome.0.69i59j69i57j69i60l3.1839j0j7&sourceid=chrome&ie=UTF-8) <div class=\"img-box1\"><img src=\"img/게시물/2/2.PNG\" /></div>\r\n        - 사이트 창을 열지 않고 구글창에 color picker만 치면 바로 사용이 가능해 편리하다.\r\n    ---\r\n2. 그라디언트 사이트\r\n    - [https://webgradients.com/](https://webgradients.com/) <div class=\"img-box1\"><img src=\"img/게시물/2/3.PNG\" /></div>\r\n        - 종류도 다양하고 크기도 적당해서 딱 보기 좋은듯하다.\r\n    ---\r\n    - [https://www.eggradients.com/](https://www.eggradients.com/)<div class=\"img-box1\"><img src=\"img/게시물/2/4.PNG\" /></div>\r\n        - 알 모양이 귀엽다.\r\n    ---\r\n    - [https://uigradients.com/#GrapefruitSunset](https://uigradients.com/#GrapefruitSunset)<div class=\"img-box1\"><img src=\"img/게시물/2/5.PNG\" /></div>\r\n        - 크기가 커서 엄청 직관적이다. <br/> 왼쪽 상단의 Show all gradients를 눌러서 편하게 볼 수 있고, <br /> 오른쪽 상단의 버튼 4개를 이용하여 회전,복사 등 기능을 사용할 수 있다.',0,0);

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

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
(10,'2021-06-04 15:35:13','2021-06-04 15:35:13','Daily','daily',2,'free','article_list_daily_1.html'),
(11,'2021-06-24 16:24:00','2021-06-24 16:24:00','HTML/CSS','HC',2,'web','article_list_HC_1.html');

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
