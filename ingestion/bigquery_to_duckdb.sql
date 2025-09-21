INSTALL bigquery FROM community;
LOAD bigquery;

ATTACH 'project=untappd-263504' as utpd (TYPE bigquery, READ_ONLY);

CREATE OR REPLACE TABLE IF NOT EXISTS main.checkins AS
SELECT
    json_extract(data::JSON, '$.checkin_id')::int as checkin_id,
    strptime(json_extract_string(data::JSON, '$.created_at'), '%a, %d %b %Y %H:%M:%S %z')::date as checkin_date,
    date_part('year', checkin_date) as checkin_year,
    date_part('month', checkin_date) as checkin_month,
    json_extract(data::JSON, '$.rating_score')::decimal(2, 1) as rating,
    json_extract_string(data::JSON, '$.checkin_comment') as comment,
    json_extract_string(data::JSON, '$.user.location') as user_location,
    json_extract_string(data::JSON, '$.beer.beer_name') as beer_name,
    json_extract_string(data::JSON, '$.beer.beer_style') as beer_style,
    json_extract(data::JSON, '$.beer.beer_abv')::decimal(3, 1) as beer_abv,
    json_extract_string(data::JSON, '$.brewery.brewery_name') as brewery_name,
    json_extract_string(data::JSON, '$.brewery.country_name') as brewery_country,
    json_extract_string(data::JSON, '$.venue.venue_name') as venue_name
FROM utpd.untappd.checkin_data
WHERE checkin_date >= '2019-01-01';
