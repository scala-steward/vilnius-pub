select beer_style, style_category, checkin_date, count(*) as cnt
from vilnius_pub.grouped_checkins
group by all
order by cnt desc
