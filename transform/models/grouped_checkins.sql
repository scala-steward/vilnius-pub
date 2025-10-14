{{ config(materialized='table') }}

{%- set style_categories = [
  ['Non-Alcoholic', 'Non-Alcoholic'],
  ['^IPA', 'IPA'],
  ['Stout', 'Stout'],
  ['Sour', 'Sour'],
  ['Pale Ale', 'Pale Ale'],
  ['Dark Ale', 'Dark Ale'],
  ['Red Ale', 'Red Ale'],
  ['Farmhouse Ale', 'Farmhouse Ale'],
  ['^Lager', 'Lager'],
  ['Kellerbier', 'Lager'],
  ['Pilsner', 'Pilsner'],
  ['Belgian', 'Belgian'],
  ['Wheat Beer', 'Wheat Beer'],
  ['Porter', 'Porter'],
  ['Cider', 'Cider'],
  ['Mead', 'Mead'],
  ['Lambic', 'Lambic'],
  ['Kvass', 'Non-Alcoholic'],
  ['^Kombucha', 'Non-Alcoholic'],
] %}

{%- set style_renames = [
    ['IPA - Imperial / Double New England', 'IPA - Imperial / Double New England / Hazy'],
    ['IPA - Triple New England', 'IPA - Triple New England / Hazy'],
    ['IPA - New England', 'IPA - New England / Hazy'],
    ['IPA - White', 'IPA - White / Wheat'],
    ['IPA - Session / India Session Ale', 'IPA - Session'],
    ['Pilsner - Czech', 'Pilsner - Czech / Bohemian'],
    ['Hefeweizen', 'Wheat Beer - Hefeweizen'],
    ['Witbier', 'Wheat Beer - Witbier / Blanche'],
    ['Wheat Beer - Witbier', 'Wheat Beer - Witbier / Blanche'],
    ['Porter - Baltic Imperial / Double', 'Porter - Imperial / Double Baltic'],
] %}



SELECT
    beer_name,
    case
        {% for style_rename in style_renames %}
        when beer_style = '{{ style_rename[0] }}' then '{{ style_rename[1] }}'
        {% endfor %}
    else beer_style end as renamed_beer_style,
    renamed_beer_style as beer_style,
    case
        {% for category in style_categories %}
        when regexp_matches(renamed_beer_style, '{{ category[0] }}', 'i') then '{{ category[1] }}'
        {% endfor %}
    else 'Other'
    end as style_category,
    brewery_name,
    brewery_country,
    case when venue_name = 'Nisha craft Capital'
         then 'Nisha Craft Capital'
         else venue_name
    end as venue_name,
    checkin_date,
    COUNT(*) as daily_checkin_sum
FROM {{ source('main', 'checkins') }}
GROUP BY ALL
