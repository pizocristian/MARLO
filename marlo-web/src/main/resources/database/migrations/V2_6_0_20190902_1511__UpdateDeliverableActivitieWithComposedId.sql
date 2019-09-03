update deliverable_activities da 
INNER JOIN activities pa ON pa.id = da.activity_id
SET da.composed_id = pa.composed_id;