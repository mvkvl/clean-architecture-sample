#!/usr/bin/env bash

DIR="$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )"

cd ${DIR}

CFG=docker-compose.yml

export CA_USER=$(id -u ${USER})
export CA_GROUP=$(id -g ${USER})

docker-compose -f $CFG up &

sleep 3
docker exec -t ca_postgres bash -c "PGPASSWORD=test psql -U test -c 'create schema if not exists spring_jpa'"
docker exec -it ca_postgres bash -c "PGPASSWORD=test psql -U test -c 'create schema if not exists spring_jdbc'"
docker exec -it ca_postgres bash -c "PGPASSWORD=test psql -U test -c 'create schema if not exists quarkus'"
