/* Add AR2018 flagship progress policies */

DROP TABLE IF EXISTS `report_synthesis_flagship_progress_policies`;
CREATE TABLE `report_synthesis_flagship_progress_policies` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`report_synthesis_flagship_progress_id`  bigint(20) NOT NULL ,
`project_policy_id`  bigint(20) NOT NULL ,
`is_active`  tinyint(1) NOT NULL ,
`active_since`  timestamp NULL DEFAULT NULL ,
`created_by`  bigint(20) NULL DEFAULT NULL ,
`modified_by`  bigint(20) NULL DEFAULT NULL ,
`modification_justification`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
PRIMARY KEY (`id`),
CONSTRAINT `report_synthesis_flagship_progress_policies_ibfk_1` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
CONSTRAINT `report_synthesis_flagship_progress_policies_ibfk_2` FOREIGN KEY (`report_synthesis_flagship_progress_id`) REFERENCES `report_synthesis_flagship_progress` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
CONSTRAINT `report_synthesis_flagship_progress_policies_ibfk_3` FOREIGN KEY (`modified_by`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
CONSTRAINT `report_synthesis_flagship_progress_policies_ibfk_4` FOREIGN KEY (`project_policy_id`) REFERENCES `project_policies` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `idx_report_synthesis_flagship_progress_id` (`report_synthesis_flagship_progress_id`) USING BTREE ,
INDEX `idx_created_by` (`created_by`) USING BTREE ,
INDEX `idx_modified_by` (`modified_by`) USING BTREE ,
INDEX `idx_project_policy_id` (`project_policy_id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
ROW_FORMAT=COMPACT;

