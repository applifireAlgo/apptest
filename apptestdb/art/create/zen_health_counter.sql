DROP TABLE IF EXISTS `zen_health_counter`;

CREATE TABLE `zen_health_counter` (  `counterId` varchar(64) NOT NULL ,  `serverInstance_Id` int(11) NOT NULL,  `httpStatus` varchar(256) NOT NULL,  `serverIpAddress` varchar(256) NOT NULL,  `serviceName` varchar(32) NOT NULL,  `scheduledDateTime` datetime NOT NULL,  `statusCount` int(2) NOT NULL,  `methodName` varchar(100) NOT NULL,  `counterType` int(4) NOT NULL,  PRIMARY KEY (`counterId`)) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

