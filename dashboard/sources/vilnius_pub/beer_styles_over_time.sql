select
    beer_style,
    style_category,
    DATE_TRUNC('month', checkin_date) AS month_start_date,
    count(*) as cnt
from vilnius_pub.grouped_checkins
group by all
order by month_start_date asc
