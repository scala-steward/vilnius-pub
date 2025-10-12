---
title: Seen for the first time
---

New beers are checked in every day in Vilnius. Some are local, some are from abroad, and some are rare finds. This page tracks beers and venues that have been checked in for the first time in Vilnius.

```sql dates
select first_checked_in as new_beer_date
from vilnius_pub.new_beers
```

<DateRange
    name=date_filter
    data={dates}
    dates=new_beer_date
    title='Date Range'
/>

```sql filtered_new_beers
select *
from vilnius_pub.new_beers
where first_checked_in between '${inputs.date_filter.start}' and '${inputs.date_filter.end}'
```

<DataTable data={filtered_new_beers}/>

### Style breakdown

```sql new_beer_styles
select beer_style as name, count(*) as value
from vilnius_pub.new_beers
where first_checked_in between '${inputs.date_filter.start}' and '${inputs.date_filter.end}'
group by all
order by value desc
limit 10
```

<ECharts config={
    {
        tooltip: {
            formatter: '{b}: {c} ({d}%)'
        },
        series: [
            {
            type: 'pie',
            radius: ['40%', '70%'],
            data: [...new_beer_styles],
            }
        ]
    }
}/>

### First seen venues

```sql new_venue_names
select *
from vilnius_pub.new_venues
where first_checked_in between '${inputs.date_filter.start}' and '${inputs.date_filter.end}'
group by all
```

<DataTable data={new_venue_names}/>
