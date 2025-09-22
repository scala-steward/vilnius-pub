COPY checkins TO 'checkins_csv'
(FORMAT csv, PARTITION_BY (checkin_year, checkin_month), OVERWRITE, FILENAME_PATTERN 'checkins_{i}');
