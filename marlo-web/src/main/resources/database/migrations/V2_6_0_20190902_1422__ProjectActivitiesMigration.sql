INSERT INTO project_activities(project_id, is_active, active_since, created_by, modified_by, modification_justification, composed_id)
SELECT DISTINCT
ac.project_id,
1,
NOW(),
1082,
1082,
'',
ac.composed_id
FROM
activities AS ac
WHERE
ac.is_active = 1
order by 
ac.project_id;