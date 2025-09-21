{{ config(materialized='table') }}

SELECT
    beer_name,
    beer_style,
    brewery_name,
    case when venue_name = 'Nisha craft Capital'
         then 'Nisha Craft Capital'
         else venue_name
    end as venue_name,
    checkin_date,
    COUNT(*) as daily_checkin_sum
FROM {{ source('main', 'checkins') }}
GROUP BY ALL
