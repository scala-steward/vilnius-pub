{{ config(materialized='table') }}

SELECT
    beer_name,
    brewery_name,
    venue_name,
    checkin_date,
    COUNT(*) as daily_checkin_sum
FROM {{ source('main', 'checkins') }}
GROUP BY ALL
