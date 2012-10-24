CREATE TABLE `high_scores` (
  `id` int(11) NOT NULL,
  `player_initials` varchar(3) DEFAULT NULL,
  `player_picture` longblob,
  `player_score` double DEFAULT NULL,
  `score_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$

