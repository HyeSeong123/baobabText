/*
SQLyog Community v13.1.7 (64 bit)
MySQL - 10.4.19-MariaDB : Database - baobabTextBoard
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
  `hitsCount` int(4) unsigned NOT NULL DEFAULT 0,
  `replyCount` int(4) unsigned NOT NULL DEFAULT 0,
  `like` int(4) unsigned NOT NULL DEFAULT 0,
  PRIMARY KEY (`num`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

/*Data for the table `article` */

insert  into `article`(`num`,`regDate`,`updateDate`,`memberNum`,`boardNum`,`title`,`body`,`hitsCount`,`replyCount`,`like`) values 
(1,'2021-06-22 11:32:47','2021-07-21 18:04:30',1,8,'AUTO_INCREMENT 초기화','<script type=\"text/x-template\">\r\n게시물을 테스트 하다가 전부 삭제하게 되면\r\nAUTO_INCREMENT로 지정된 값은 초기화가 안되고 아래와 같이 마지막 값을 유지하게 된다.\r\n\r\n<div class=\"img-box1\">\r\n    <img src=\"img/게시물/1/1.PNG\" />\r\n</div>\r\n\r\n초기화 방법은\r\nALTER TABLE [테이블 명] AUTO_INCREMENT = [원하는 숫자];\r\n이다.\r\n\r\n사용 예시\r\n```mysql\r\nALTER TABLE article AUTO_INCREMENT = 3;\r\n```\r\n<div class=\"img-box1\">\r\n    <img src=\"img/게시물/1/2.PNG\" />\r\n</div>\r\n\r\n변경 완료!\r\n</script>\r\n\r\n<div class=\"viewer\"></div>',9,0,0),
(2,'2021-06-24 16:25:22','2021-07-21 18:04:30',1,11,'웹 그라디언트/컬러 코드 사이트','<script type=\"text/x-template\">\r\n제가 웹 디자인을 할 때 자주 이용하는 사이트 입니다.\r\n컬러 코드, 그라데이션 색 코드를 받을 수 있습니다.\r\n\r\n1. 컬러 코드 사이트\r\n    - [http://www.colinkeany.com/blend/](http://www.colinkeany.com/blend/)<div class=\"img-box1\"><img src=\"img/게시물/2/1.PNG\" /></div>\r\n    - 이전 색과 변경 색을 두고 차이를 보면서 색을 변경할 수 있다.\r\n    ---\r\n    - [color picker](https://www.google.com/search?q=color+picker&oq=color&aqs=chrome.0.69i59j69i57j69i60l3.1839j0j7&sourceid=chrome&ie=UTF-8) <div class=\"img-box1\"><img src=\"img/게시물/2/2.PNG\" /></div>\r\n        - 사이트 창을 열지 않고 구글창에 color picker만 치면 바로 사용이 가능해 편리하다.\r\n    ---\r\n2. 그라디언트 사이트\r\n    - [https://webgradients.com/](https://webgradients.com/) <div class=\"img-box1\"><img src=\"img/게시물/2/3.PNG\" /></div>\r\n        - 종류도 다양하고 크기도 적당해서 딱 보기 좋은듯하다.\r\n    ---\r\n    - [https://www.eggradients.com/](https://www.eggradients.com/)<div class=\"img-box1\"><img src=\"img/게시물/2/4.PNG\" /></div>\r\n        - 알 모양이 귀엽다.\r\n    ---\r\n    - [https://uigradients.com/#GrapefruitSunset](https://uigradients.com/#GrapefruitSunset)<div class=\"img-box1\"><img src=\"img/게시물/2/5.PNG\" /></div>\r\n        - 크기가 커서 엄청 직관적이다. <br/> 왼쪽 상단의 Show all gradients를 눌러서 편하게 볼 수 있고, <br /> 오른쪽 상단의 버튼 4개를 이용하여 회전,복사 등 기능을 사용할 수 있다.\r\n</script>\r\n<div class=\"viewer\"></div>',7,0,1),
(3,'2021-06-25 10:25:38','2021-07-21 18:04:30',1,9,'운영체제 기본','<script type=\"text/x-template\">\r\n우리가 사용하는 일반적인 운영체제는\r\nWindows, mac OS 등이 있으며 GUI환경이다.\r\nGraphical User Interface의 약자이며 그래픽을 이용해\r\n사용자와 하드웨어 사이에서 중계 역할을 해주는 프로그램이다.\r\n하지만 그래픽을 이용하지 않고 명령어를 입력하여\r\n중계 역할을 해주는 CLI환경의 운영체제들도 있다.\r\n\r\n<div class=\"img-box1\"> <img src=\"img/게시물/3/1.PNG\" /> </div>\r\n\r\n대표적으로 CentOS, Ubuntu, Debian 등이 있고, 굳이 CLI를 쓰는 이유는\r\nGUI가 CLI보다 더 많은 에너지가 소모되고, 동작속도도 느리다.\r\n명령으로 동작들을 실행하다보니 GUI보다 실수할 확률이 적어진다.(오클릭 등)\r\nGUI에서 여러번의 클릭으로 해야할 동작을 CLI에선 <br />\r\n한 두 줄의 명령어로 줄일 수 있다.\r\n<br>\r\n단점은 명령어들을 외워야하고 조금 어렵다는 문제가 있지만\r\n다행히 대부분의 OS는 UNIX 표준을 지켜 명령어가 비슷하다.\r\n\r\nUNIX는 리눅스의 어머니?이고 UNIX에서 파생된 것이 LINUX이다.\r\nUNIX는 유료이고 리눅스는 무료이다.\r\n(레드햇은 리눅스 이지만 유료이다.)\r\n\r\nUnix에는 hp, AIX, macOS 등이 있다.\r\n</script>\r\n<div class=\"viewer\"></div>',3,1,0),
(4,'2021-06-25 14:44:27','2021-07-21 18:04:29',1,11,'이메일 HTML/이미지 보내기','<script type=\"text/x-template\">\r\n업무 중 이메일에 이미지맵이 포함된 HTML 형식을 적용시켜서 보낼 일이 생겼다.\r\n네이버랑 지메일을 이용했는데 지메일은 조금 까다로웠다.\r\n# 네이버\r\n네이버는 우선 이미지를 첨부해주었고,\r\n<div class=\"img-box1\"><img src=\"img/게시물/4/1.PNG\"></div>\r\n\r\n우측하단에서 HTML로 이동한다.\r\n<div class=\"img-box1\"><img src=\"img/게시물/4/2.PNG\"></div>\r\n\r\n그러면 사진과 관련된 코드가 작성되어 있다\r\n<div class=\"img-box1\"><img src=\"img/게시물/4/3.PNG\"></div>\r\n\r\n나는 이미지맵을 사용해야해서 usemap=\"이미지맵 ID\" 코드를 추가해주었다.\r\n\r\n# Gmail\r\n지메일도 마찬가지로 이미지부터 첨부해준다.\r\n<div class=\"img-box1\"><img src=\"img/게시물/4/4.PNG\"></div>\r\n\r\n메일창 바깥을 한 번 클릭하고 Ctrl + shift + c 를 누른 뒤 이미지를 클릭한다.\r\n<div class=\"img-box1\"><img src=\"img/게시물/4/5.PNG\"></div>\r\n\r\n사진과 같은 상태에서 클릭해야 한다.\r\n클릭을 한 뒤 윗줄에는\r\n<div class=\"img-box1\"><img src=\"img/게시물/4/6.PNG\"></div>\r\n\r\n위와 같이 메일 본문이라고 들어간 항목이 있을 것이다.우클릭 뒤 edis as HTML을 클릭하고 HTML 코드를 넣어주고 다른 부분을\r\n클릭해준다 그러면 지메일 작성창은\r\n<div class=\"img-box1\"><img src=\"img/게시물/4/7.PNG\"></div>\r\n\r\n변경되어 있을 것이다.\r\n\r\n코드를 넣어줄 때 삽입 부분은\r\n<div class=\"img-box1\"><img src=\"img/게시물/4/8.PNG\"></div>\r\n\r\n사진의 부분이다.\r\n</script>\r\n\r\n<div class=\"viewer\"></div>',3,0,0),
(5,'2021-06-25 15:37:10','2021-07-21 18:04:29',1,11,'HTML형식 보낼 때 js,style 주의점','<script type=\"text/x-template\">\r\n이메일에 js나 style태그를 넣으면 이메일에서 필터링 하는 것이 확인 되었다.\r\n특정 메일만 그런게 아니고 모든 메일이 그런듯하다.\r\n대신 태그안에 inline-style은 적용이 됐는데 거기서도 필터링 되는 부분이 있다.\r\n우선 일반 메일이다.\r\n<div class=\"img-box1\"> <img src=\"img/게시물/5/1.PNG\"> </div>\r\n\r\n위 처럼 적용해서 메일을 보내면\r\n<div class=\"img-box1\"> <img src=\"img/게시물/5/2.PNG\"> </div>\r\n\r\n스크립트와 스타일이 다 제거 돼 있다.\r\n\r\n하지만 아래와 같이 인라인 스타일을 적용시키면\r\n```css\r\n    <div style=\"color:red;\">일반 메일 </div>\r\n```\r\n\r\n<div class=\"img-box1\"> <img src=\"img/게시물/5/3.PNG\"> </div>\r\n\r\n적용이 잘 돼서 나온다. 하지만 만능은 아니다 여기서 또 걸러지는 부분들이 있다.\r\n```css\r\n    <div style=\"position : absolute; color: red; >1234 </div>\r\n    <div style=\"position : relative; top: 10px; left:10px; color: red; > 1234</div> \r\n    <div style=\"position : fixed; top: 10px; left:10px; color: red; > 1234</div> \r\n    <div style=\"width:100px; height:100px; color: red; > 1234</div> \r\n```\r\n<div class=\"img-box1\"> <img src=\"img/게시물/5/5.PNG\"> </div>\r\n\r\nfixed와 absolute는 제거하는듯 하다.\r\n\r\n참고자료 입니다.\r\n<div class=\"img-box1\"> <img src=\"img/게시물/5/6.PNG\"> </div>\r\n</script>\r\n\r\n<div class=\"viewer\"></div>',3,0,0),
(6,'2021-07-02 00:00:00','2021-07-21 18:04:29',1,6,'GSAP 슬라이드 만들기','<script type=\"text/x-template\">\r\n블로그 디자인을 하던 중 슬라이드를 만들 일이 생겼습니다.\r\nswiper 나 slick slider를 사용해도 되지만\r\nGSAP를 조금 더 연습해보고 싶어졌습니다.\r\n\r\n내용을 담아줄 박스를 만들어 줍니다!\r\n</script>\r\n\r\n<div class=\"viewer\"></div>\r\n\r\n<script type=\"text/x-template\">\r\n```codepen\r\nhttps://codepen.io/hyeseong123/embed/gOWRdQj?default-tab=html%2Cresult&editable=true\r\n```\r\n제 블로그에 쓰인 스타일과 HTML 입니다!\r\n이제 스크립트를 입혀줍시다.\r\n화살표를 누르면 이동해야겠지요\r\n(박스들은 포지션이 absolute로 지정되었습니다.)\r\n(css에 화면 크기별 버튼 노출이 설정되어 있습니다.)\r\n```codepen\r\nhttps://codepen.io/hyeseong123/embed/zYwzmGN?default-tab=html%2Cresult&editable=true\r\n```\r\n이동을 하기는 합니다!\r\n이제 부가적인 옵션들을 넣어주겠습니다.\r\n주석을 보고 참고해주세요\r\n```codepen\r\nhttps://codepen.io/hyeseong123/embed/xxdryxr?default-tab=html%2Cresult&editable=true\r\n```\r\n\r\n이제 버튼으로 이동을 잘 하게 됐습니다.\r\n페이징 버튼을 눌러서도 이동을 해야겠지요?\r\n\r\n```codepen\r\nhttps://codepen.io/hyeseong123/embed/PomjyKv?default-tab=html%2Cresult&editable=true\r\n```\r\n\r\n반응형 전체 스크립트 코드는\r\n[https://codepen.io/hyeseong123/pen/MWmomdE?editors=1010](https://codepen.io/hyeseong123/pen/MWmomdE?editors=1010)\r\n이곳에 있습니다ㅎㅎ\r\n</script>\r\n\r\n\r\n<div class=\"viewer\"></div>\r\n',3,0,0),
(7,'2021-07-20 18:17:54','2021-07-21 18:04:29',1,9,'Linux 경로 이동 명령어','<script type=\"text/x-template\">\r\n리눅스 계열 OS에서는 파일을 찾고 여는거 조차도 명령어로 해야합니다.\r\n우선 현재 경로에 있는 파일들을 조회할 때 명령어 입니다.\r\n```ubuntu\r\nls\r\n```\r\n<div class=\"img-box1\"><img src=\"img/게시물/7/1.PNG\"></div>\r\n</script>\r\n<div class=\"viewer\"></div>\r\n<script type=\"text/x-template\">\r\n위와 같이 출력 됩니다.\r\n만약 더 자세한 내용을 보고 싶다면 뒤에 -l을 붙이시면 됩니다.\r\n```ubuntu\r\nls -l\r\n```\r\n<div class=\"img-box1\"><img src=\"img/게시물/7/2.PNG\"></div>\r\n</script>\r\n<div class=\"viewer\"></div>\r\n<script type=\"text/x-template\">\r\n내용이 너무 지저분해서 지우고 싶으시면 아래 명령어를 입력해주세요!\r\n```ubuntu\r\nclear\r\n```\r\n</script>\r\n<div class=\"viewer\"></div>\r\n<script type=\"text/x-template\">\r\n파일의 크기를 보고 싶다면 위의 -l에다가 h를 붙여줍니다.\r\nKB,MB,GB 등 크기를 보기 쉽게 해줍니다.\r\n```ubuntu\r\nls -lh\r\n```\r\n<div class=\"img-box1\"><img src=\"img/게시물/7/3.PNG\"></div>\r\n</script>\r\n<div class=\"viewer\"></div>\r\n<script type=\"text/x-template\">\r\n파일을 크기순으로 정렬해줍니다.\r\n```ubuntu\r\nls -s\r\nls -lsh\r\n```\r\n<div class=\"img-box1\"><img src=\"img/게시물/7/4.PNG\"></div>\r\n</script>\r\n<div class=\"viewer\"></div>\r\n<script type=\"text/x-template\">\r\n이제 경로 이동을 합시다!\r\n```ubuntu\r\ncd dir\r\n```\r\ndir에는 이동할 경로를 넣어줍시다.\r\n<div class=\"img-box1\"><img src=\"img/게시물/7/5.PNG\"></div>\r\n앞에 /test가 붙으며 경로가 변했습니다.\r\n</script>\r\n<div class=\"viewer\"></div>\r\n\r\n<script type=\"text/x-template\">\r\n파일 위치만 정확히 안다면 cd와 ls를 번갈아 가면서 쓰지않아도\r\n```ubuntu\r\ncd dir/dir/dir/dir\r\n```\r\n이런 식으로 이동 가능합니다.\r\n이전 경로로 가기 위해선 cd 뒤에 -만 붙이면 됩니다.\r\n```ubuntu\r\ncd -\r\n```\r\n최상단 폴더로 가기 위해선\r\n```ubuntu\r\ncd /\r\n```\r\n홈 디렉토리로 가기 위해선\r\n```ubuntu\r\ncd ~\r\n```\r\n부모 디렉토리로 가기 위해선\r\n```ubuntu\r\ncd ..\r\n```\r\n입니다.\r\n절대경로와 상대경로 잘 구분해서 사용하시길 바랍니다.\r\n<div class=\"img-box1\"><img src=\"img/게시물/7/6.PNG\"></div>\r\n</script>\r\n<div class=\"viewer\"></div>',2,0,0);

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

/*Table structure for table `ga4DataPagePath` */

DROP TABLE IF EXISTS `ga4DataPagePath`;

CREATE TABLE `ga4DataPagePath` (
  `num` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `pagePath` char(100) NOT NULL,
  `hit` int(10) unsigned NOT NULL,
  PRIMARY KEY (`num`),
  UNIQUE KEY `pagePath` (`pagePath`)
) ENGINE=InnoDB AUTO_INCREMENT=545 DEFAULT CHARSET=utf8mb4;

/*Data for the table `ga4DataPagePath` */

insert  into `ga4DataPagePath`(`num`,`regDate`,`updateDate`,`pagePath`,`hit`) values 
(528,'2021-07-21 18:04:33','2021-07-21 18:04:33','/',26),
(529,'2021-07-21 18:04:33','2021-07-21 18:04:33','/article_list_java_1.html',12),
(530,'2021-07-21 18:04:33','2021-07-21 18:04:33','/index.html',12),
(531,'2021-07-21 18:04:33','2021-07-21 18:04:33','/article_list_mysql_1.html',11),
(532,'2021-07-21 18:04:33','2021-07-21 18:04:33','/article_list_centos_1.html',10),
(533,'2021-07-21 18:04:33','2021-07-21 18:04:33','/article_detail_1.html',9),
(534,'2021-07-21 18:04:33','2021-07-21 18:04:33','/article_list_js_1.html',9),
(535,'2021-07-21 18:04:33','2021-07-21 18:04:33','/article_list_daily_1.html',8),
(536,'2021-07-21 18:04:33','2021-07-21 18:04:33','/article_detail_2.html',7),
(537,'2021-07-21 18:04:33','2021-07-21 18:04:33','/article_list_HC_1.html',5),
(538,'2021-07-21 18:04:33','2021-07-21 18:04:33','/article_detail_3.html',3),
(539,'2021-07-21 18:04:33','2021-07-21 18:04:33','/article_detail_4.html',3),
(540,'2021-07-21 18:04:33','2021-07-21 18:04:33','/article_detail_5.html',3),
(541,'2021-07-21 18:04:33','2021-07-21 18:04:33','/article_detail_6.html',3),
(542,'2021-07-21 18:04:33','2021-07-21 18:04:33','/article_detail_23.html',2),
(543,'2021-07-21 18:04:33','2021-07-21 18:04:33','/article_detail_7.html',2),
(544,'2021-07-21 18:04:33','2021-07-21 18:04:33','/article_list_spring_1.html',2);

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
