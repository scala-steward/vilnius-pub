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

<DateRange
    name=date_filter
    data={all_but_last_day_checkins}
    dates=day_start_date
    title='Date Range'
/>

```sql filtered_checkins
select
    *
from ${all_but_last_day_checkins}
where day_start_date between '${inputs.date_filter.start}' and '${inputs.date_filter.end}'
```

<LineChart
    data={filtered_checkins}
    y=daily_checkin_sum
    x=day_start_date>
    <ReferenceArea xMin='2020-03-16' xMax='2020-06-17' label="Lockdown #1" color=warning/>
    <ReferenceArea xMin='2020-11-04' xMax='2021-07-01' label="Lockdown #2" color=warning/>
    <ReferencePoint x="2020-02-22" y=1150 label="Žmogšala 2020" labelPosition=top/>
    <ReferencePoint x="2022-02-19" y=1500 label="Žmogšala 2022" labelPosition=left/>
    <ReferencePoint x="2023-02-11" y=1900 label="Žmogšala 2023" labelPosition=left/>
    <ReferencePoint x="2023-09-22" y=1800 label="Putoja 2023" labelPosition=top/>
    <ReferencePoint x="2023-11-24" y=1700 label="VAF 2023" labelPosition=right/>
    <ReferencePoint x="2024-02-10" y=1450 label="Žmogšala 2020" labelPosition=right/>
    <ReferencePoint x="2024-09-20" y=1800 label="Putoja 2024" labelPosition=top/>
    <ReferencePoint x="2024-11-29" y=1800 label="VAF 2024" labelPosition=right/>
    <ReferencePoint x="2025-09-13" y=2100 label="Putoja 2025" labelPosition=top/>
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
