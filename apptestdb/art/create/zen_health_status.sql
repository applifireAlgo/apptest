DROP TABLE IF EXISTS `zen_health_status`;

CREATE TABLE `zen_health_status` (  `healthId` varchar(64) NOT NULL ,  `scheduledDateTime` datetime NOT NULL,  `freeSpace` double NOT NULL,  `status` varchar(256) NOT NULL,  `serverInstanceId` int(3) NOT NULL,  `usedSpace` double NOT NULL,  `serverIpAddress` varchar(30) NOT NULL,  `totalSpace` double NOT NULL,  PRIMARY KEY (`healthId`)) ;

