CREATE OR REPLACE TABLE main.checkins AS
SELECT * FROM read_csv('ingestion/checkins_csv/*/*/*.csv', hive_partitioning = true);
