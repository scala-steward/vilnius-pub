select venue_name, count(*) as cnt
from vilnius_pub.grouped_checkins
group by all
order by cnt desc
