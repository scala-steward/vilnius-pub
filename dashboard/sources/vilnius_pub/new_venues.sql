SELECT
    venue_name,
    MIN(checkin_date) AS first_checked_in
FROM
    grouped_checkins
GROUP BY ALL
ORDER BY
    first_checked_in DESC;
