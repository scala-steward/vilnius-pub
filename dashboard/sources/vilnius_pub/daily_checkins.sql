SELECT
    DATE_TRUNC('day', checkin_date) AS day_start_date,
    SUM(daily_checkin_sum) AS daily_checkin_sum
FROM
   grouped_checkins
GROUP BY
    day_start_date
ORDER BY
    day_start_date DESC
