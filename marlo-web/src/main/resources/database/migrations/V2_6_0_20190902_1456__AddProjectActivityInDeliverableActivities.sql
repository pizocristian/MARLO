ALTER TABLE `deliverable_activities`
ADD COLUMN `project_activity_id`  bigint(20) NULL AFTER `deliverable_id`;

ALTER TABLE `deliverable_activities` ADD FOREIGN KEY (`project_activity_id`) REFERENCES `project_activities` (`id`);