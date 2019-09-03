update activities set startDate = null, endDate = null where startDate < '2000-01-01' and is_active=1;
update activities set activityStatus = null where activityStatus = -1 and is_active=1;