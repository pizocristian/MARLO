INSERT INTO project_activity_info (
  project_activity_id,
  title,
  description,
  start_date,
  end_date,
  project_partner_person_id,
  activity_status,
  activity_progress,
  id_phase
) SELECT
  pa.id,
  a.title,
  a.description,
  a.startDate,
  a.endDate,
  a.leader_id,
  a.activityStatus,
  a.activityProgress,
  a.id_phase
FROM
  activities AS a,
  project_activities AS pa
WHERE
  a.composed_id = pa.composed_id
ORDER BY
  pa.id ASC