DROP TABLE IF EXISTS `zen_health_gauge`;

CREATE TABLE `zen_health_gauge` (  `gaugeId` varchar(64) NOT NULL ,  `scheduledDateTime` datetime NOT NULL,  `methodHitCount` double NOT NULL,  `serverInstanceId` int(11) NOT NULL,  `methodHitTime` double NOT NULL,  `serverIpAddress` varchar(256) NOT NULL,  `serviceName` varchar(256) NOT NULL,  `methodName` varchar(32) NOT NULL,  PRIMARY KEY (`gaugeId`));

