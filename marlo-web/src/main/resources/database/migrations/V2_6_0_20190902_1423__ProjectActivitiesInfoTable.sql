SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for project_activity_info
-- ----------------------------
DROP TABLE IF EXISTS `project_activity_info`;
CREATE TABLE `project_activity_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `project_activity_id` bigint(20) NOT NULL,
  `title` text,
  `description` text,
  `start_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `end_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `project_partner_person_id` bigint(20) DEFAULT NULL,
  `activity_status` bigint(20) DEFAULT NULL,
  `activity_progress` text,
  `id_phase` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `project_activity_info_ibfk_1` (`project_partner_person_id`),
  KEY `project_activity_info_ibfk_2` (`activity_status`),
  KEY `project_activity_info_ibfk_3` (`id_phase`),
  KEY `project_activity_info_ibfk_4` (`project_activity_id`),
  CONSTRAINT `project_activity_info_ibfk_1` FOREIGN KEY (`project_partner_person_id`) REFERENCES `project_partner_persons` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `project_activity_info_ibfk_2` FOREIGN KEY (`activity_status`) REFERENCES `general_statuses` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `project_activity_info_ibfk_3` FOREIGN KEY (`id_phase`) REFERENCES `phases` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `project_activity_info_ibfk_4` FOREIGN KEY (`project_activity_id`) REFERENCES `project_activities` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project_activity_info
-- ----------------------------
SET FOREIGN_KEY_CHECKS=1;
