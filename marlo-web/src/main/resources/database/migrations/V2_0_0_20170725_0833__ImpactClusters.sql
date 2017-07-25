SET FOREIGN_KEY_CHECKS = 0;

ALTER TABLE `crp_cluster_key_outputs`
ADD COLUMN `composed_id`  varchar(20)  NULL AFTER `modification_justification`;



update  crp_cluster_key_outputs ml INNER JOIN crp_cluster_of_activities po 
on po.id=ml.cluster_activity_id
set ml.composed_id=CONCAT(po.identifier,'-',ml.id) ;

CREATE TEMPORARY TABLE
IF NOT EXISTS tableclusters AS (SELECT * FROM crp_cluster_of_activities);



CREATE TEMPORARY TABLE
IF NOT EXISTS table_temp_crp_cluster_key_outputs AS (
SELECT
  pp.*,ppp.identifier,ppp.crp_program_id
FROM
  crp_cluster_key_outputs pp
INNER JOIN crp_cluster_of_activities ppp ON pp.cluster_activity_id = ppp.id)
;

CREATE TEMPORARY TABLE
IF NOT EXISTS table_temp_crp_cluster_leaders AS (

SELECT
 pp.*,ppp.identifier,ppp.crp_program_id
FROM
  crp_cluster_activity_leaders pp
INNER JOIN crp_cluster_of_activities ppp ON pp.cluster_activity_id=ppp.id
)
;

CREATE TEMPORARY TABLE
IF NOT EXISTS table_temp_crp_cluster_key_outputs_outcome_cl AS (
SELECT
  pp.*,ppp.composed_id
FROM
  crp_cluster_key_outputs_outcome pp
INNER JOIN crp_cluster_key_outputs ppp ON pp.key_output_id = ppp.id)
;

TRUNCATE TABLE crp_cluster_of_activities;
TRUNCATE TABLE crp_cluster_activity_leaders ;
TRUNCATE TABLE crp_cluster_key_outputs ;
TRUNCATE TABLE crp_cluster_key_outputs_outcome ;


ALTER TABLE `crp_cluster_of_activities`
ADD COLUMN `id_phase`  bigint(20) NULL AFTER `modification_justification`;

ALTER TABLE `crp_cluster_of_activities` ADD FOREIGN KEY (`id_phase`) REFERENCES `phases` (`id`);

INSERT INTO crp_cluster_of_activities (

identifier,
description,
crp_program_id,
is_active,
created_by,
active_since,
modified_by,
modification_justification,

id_phase
) SELECT 
t2.identifier,
t2.description,
t2.crp_program_id,
t2.is_active,
t2.created_by,
t2.active_since,
t2.modified_by,
t2.modification_justification,
  ph.id
FROM
  tableclusters t2
  inner join crp_programs pr on pr.id=t2.crp_program_id
inner JOIN phases ph ON ph.crp_id=pr.crp_id
;


insert into crp_cluster_activity_leaders (
user_id,
cluster_activity_id,
is_active,
created_by,
active_since,
modified_by,
modification_justification


)
select distinct 
temp.user_id,
pp.id,
temp.is_active,
temp.created_by,
temp.active_since,
temp.modified_by,
temp.modification_justification

from table_temp_crp_cluster_leaders temp 
INNER JOIN crp_cluster_of_activities pp on pp.crp_program_id=temp.crp_program_id
and pp.identifier =temp.identifier
;


insert into crp_cluster_key_outputs (
contribution,
key_output,
cluster_activity_id,
is_active,
created_by,
active_since,
modified_by,
modification_justification,
composed_id


)
select distinct 
temp.contribution,
temp.key_output,
pp.id,
temp.is_active,
temp.created_by,
temp.active_since,
temp.modified_by,
temp.modification_justification,
temp.composed_id

from table_temp_crp_cluster_key_outputs temp 
INNER JOIN crp_cluster_of_activities pp on pp.crp_program_id=temp.crp_program_id
and pp.identifier =temp.identifier
;



insert into crp_cluster_key_outputs_outcome (contribution,
key_output_id,
outcome_id,
is_active,
created_by,
active_since,
modified_by,
modification_justification


)
select distinct 
temp.contribution,
pp.id,
temp.outcome_id,
temp.is_active,
temp.created_by,
temp.active_since,
temp.modified_by,
temp.modification_justification

from table_temp_crp_cluster_key_outputs_outcome_cl temp 
INNER JOIN crp_cluster_key_outputs pp on 
 pp.composed_id =temp.composed_id
;

