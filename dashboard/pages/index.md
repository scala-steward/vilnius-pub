---
title: What people are drinking in Vilnius?
---

```sql last_week_checkins
select SUM(daily_checkin_sum) as checkin_count
from vilnius_pub.daily_checkins
where day_start_date > CURRENT_DATE() - interval 8 day
```

```sql last_month_checkins
select SUM(daily_checkin_sum) as checkin_count
from vilnius_pub.daily_checkins
where day_start_date > CURRENT_DATE() - interval 31 day
```

```sql total_checkins
select SUM(daily_checkin_sum) as checkin_count
from vilnius_pub.daily_checkins
```

```sql last_refresh
select date from vilnius_pub.last_refresh
```

<BigValue
    title='Last week checkins'
    data={last_week_checkins}
    value='checkin_count'
    fmt='#,##0'
/>

<BigValue
    title='Last month checkins'
    data={last_month_checkins}
    value='checkin_count'
    fmt='#,##0'
/>

<BigValue
    title='Total checkins'
    data={total_checkins}
    value='checkin_count'
    fmt='#,##0'
/>

<BigValue
  title='Data last updated on'
  data={last_refresh}
  value=date
/>


## Checkins over time

```sql all_but_last_day_checkins
select daily_checkin_sum, day_start_date
from vilnius_pub.daily_checkins
where day_start_date < CURRENT_DATE()
```

<LineChart
    data={all_but_last_day_checkins}
    y=daily_checkin_sum
    x=day_start_date>
    <ReferenceArea xMin='2020-02-22' xMax='2020-02-23' label="Žmogšala 2020" color=red/>
    <ReferenceArea xMin='2025-09-13' xMax='2025-09-14' label="Putoja 2025" color=red/>
</LineChart>

## Top 20 beer styles

```sql beer_styles
select beer_style as name, cnt as value
from vilnius_pub.beer_styles
limit 20
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
            data: [...beer_styles],
            }
        ]
    }
}/>

## Top 20 breweries

```sql breweries
select brewery_name as name, cnt as value
from vilnius_pub.breweries
limit 20
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
            data: [...breweries],
            }
        ]
    }
}/>

## Top 20 venues

```sql venues
select venue_name as name, cnt as value
from vilnius_pub.venues
limit 20
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
            data: [...venues],
            }
        ]
    }
}/>
