set dotenv-load

login account:
    gcloud config set account {{account}}
    gcloud auth application-default login
    gcloud auth application-default set-quota-project untappd-263504

export:
    just export

import-bq:
    #!/usr/bin/env bash
    set -euxo pipefail

    LAST_BACKUP=$(gcloud storage ls gs://untappd-backup | tail -n 1)

    echo "Importing backup from $LAST_BACKUP to BigQuery"
    bq --location=EU load \
        --source_format=DATASTORE_BACKUP \
        --replace \
        untappd.checkin_data \
        ${LAST_BACKUP}all_namespaces/kind_checkins/all_namespaces_kind_checkins.export_metadata

build:
    dbt build

run target:
    dbt run --target {{target}}
