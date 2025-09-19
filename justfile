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
    cd ingestion; duckdb untappd_checkins.db < bigquery_to_duckdb.sql

duckdb-to-csv:
    cd ingestion; duckdb untappd_checkins.db < duckdb_to_csv.sql

build:
    dbt build

run target:
    dbt run --target {{target}}
