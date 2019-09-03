SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for project_activities
-- ----------------------------
DROP TABLE IF EXISTS `project_activities`;
CREATE TABLE `project_activities` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `project_id` bigint(20) NOT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '1',
  `active_since` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modification_justification` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `composed_id` text,
  PRIMARY KEY (`id`),
  KEY `project_activities_ibfk_1` (`project_id`),
  KEY `project_activities_ibfk_2` (`created_by`),
  KEY `project_activities_ibfk_3` (`modified_by`),
  CONSTRAINT `project_activities_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `projects` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `project_activities_ibfk_2` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `project_activities_ibfk_3` FOREIGN KEY (`modified_by`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project_activities
-- ----------------------------
SET FOREIGN_KEY_CHECKS=1;
