{{ config(materialized='table') }}

SELECT CURRENT_DATE() as date
