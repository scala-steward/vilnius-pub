set dotenv-load

login account:
    gcloud config set account {{account}}
    gcloud auth application-default login
    gcloud auth application-default set-quota-project untappd-263504

export:
    gcloud firestore export --collection-ids=checkins gs://untappd-backup --database='(default)'

import-bq:
    #!/usr/bin/env bash
    set -euxo pipefail

    LAST_BACKUP=$(gcloud storage ls gs://untappd-backup | tail -n 1)
    bq --location=EU load \
        --source_format=DATASTORE_BACKUP \
        --replace \
        untappd.checkin_data \
        ${LAST_BACKUP}all_namespaces/kind_checkins/all_namespaces_kind_checkins.export_metadata

bq-to-duckdb:
    cd ingestion; duckdb untappd_checkins.duckdb < bigquery_to_duckdb.sql

duckdb-to-csv:
    cd ingestion; duckdb untappd_checkins.duckdb < duckdb_to_csv.sql

transform:
    cd transform; uv run dbt build

duckdb-ui:
    duckdb -ui transform/vilnius_pub.duckdb

dashboard-install:
    cd dashboard; bun install

dashboard-sources:
    cd dashboard; bun run sources

dashboard-dev:
    cd dashboard; bun run dev

dashboard-build:
    cd dashboard; bun run build
