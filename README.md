# [vilnius.pub](https://vilnius.pub)

A data analytics project tracking beer check-ins in Vilnius, Lithuania using data from [Untappd][]. The project features automated data ingestion from Google Cloud, [dbt][] transformations, and a  dashboard built with [Evidence.dev][].

[Untappd]: https://untappd.com/
[dbt]: https://www.getdbt.com/
[Evidence.dev]: https://www.evidence.dev/

## Prerequisites

- [just](https://github.com/casey/just) - Command runner
- [DuckDB](https://duckdb.org/) - Database engine
- [uv](https://docs.astral.sh/uv/) - Python package manager (for dbt)
- [Bun](https://bun.sh/) - JavaScript runtime (for dashboard)

## Local development

1. Load data into DuckDB:

    ```sh
    just csv-to-duckdb
    ```

1. Clean up and categories data with dbt:

    ```sh
    just transform
    ```

1. Generate parquet files for the dashboard:

    ```sh
    just dashboard-sources
    ```

1. Run the development version of the dashboard:

    ```sh
    just dashboard-dev
    ```
