INSERT INTO project_activity_deliverables (
  deliverable_id,
  project_activity_id,
  id_phase
) SELECT DISTINCT
  deliverable_activities.deliverable_id,
  deliverable_activities.project_activity_id,
  deliverable_activities.id_phase
FROM
  deliverable_activities;