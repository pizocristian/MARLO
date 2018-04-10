SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bi_permissions
-- ----------------------------
DROP TABLE IF EXISTS `bi_permissions`;
CREATE TABLE `bi_permissions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` tinyint(1),
  `user` bigint(20),
  `urlbi` text,
  `is_active` tinyint(1) NOT NULL,  
  PRIMARY KEY (`id`),
  CONSTRAINT `bi_permissions_fk` FOREIGN KEY (`user`) REFERENCES `users` (`id`)  
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
