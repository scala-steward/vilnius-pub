SELECT
    beer_name,
    beer_style,
    brewery_name || ' (' || brewery_country || ')' as brewery_name,
    MIN(checkin_date) AS first_checked_in
FROM
    grouped_checkins
GROUP BY ALL
ORDER BY
    first_checked_in DESC;
