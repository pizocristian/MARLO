SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for project_activity_deliverables
-- ----------------------------
DROP TABLE IF EXISTS `project_activity_deliverables`;
CREATE TABLE `project_activity_deliverables` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `project_activity_id` bigint(20) DEFAULT NULL,
  `deliverable_id` bigint(20) DEFAULT NULL,
  `id_phase` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `project_activity_id` (`project_activity_id`),
  KEY `deliverable_id` (`deliverable_id`),
  KEY `id_phase` (`id_phase`),
  CONSTRAINT `project_activity_deliverables_ibfk_1` FOREIGN KEY (`project_activity_id`) REFERENCES `project_activities` (`id`),
  CONSTRAINT `project_activity_deliverables_ibfk_2` FOREIGN KEY (`deliverable_id`) REFERENCES `deliverables` (`id`),
  CONSTRAINT `project_activity_deliverables_ibfk_3` FOREIGN KEY (`id_phase`) REFERENCES `phases` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project_activity_deliverables
-- ----------------------------
SET FOREIGN_KEY_CHECKS=1;
