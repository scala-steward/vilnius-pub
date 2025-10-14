---
title: Trends over time
---

The following graph shows how the popularity of a specific style category changes over time.

```sql beer_styles
select distinct style_category
from vilnius_pub.beer_styles
```

<Dropdown
    data={beer_styles}
    name=beer_style_selection
    value=style_category
    multiple=true
    selectAllByDefault=true
/>

```sql beer_styles_over_time
select style_category, month_start_date, sum(cnt) as cnt
from vilnius_pub.beer_styles_over_time
where style_category in ${inputs.beer_style_selection.value}
group by all
```

<AreaChart
    data={beer_styles_over_time}
    x=month_start_date
    y=cnt
    series=style_category
    type=stacked100
    chartAreaHeight=300
/>

### Break down of specific style category

<Dropdown
    data={beer_styles}
    name=beer_style_to_break_down
    value=style_category
    defaultValue=IPA
/>

```sql category_styles_over_time
select beer_style, month_start_date, sum(cnt) as cnt
from vilnius_pub.beer_styles_over_time
where style_category = '${inputs.beer_style_to_break_down.value}'
group by all
```

<AreaChart
    data={category_styles_over_time}
    x=month_start_date
    y=cnt
    series=beer_style
    type=stacked100
    chartAreaHeight=300
/>
