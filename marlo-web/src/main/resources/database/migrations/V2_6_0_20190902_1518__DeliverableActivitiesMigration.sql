update deliverable_activities da 
INNER JOIN project_activities pa ON pa.composed_id = da.composed_id
SET da.project_activity_id = pa.id;