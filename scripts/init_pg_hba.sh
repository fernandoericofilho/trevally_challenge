#!/bin/bash

cat << EOF > /etc/postgresql/pg_hba.conf
# Entradas existentes...
host    trevally_challenge  postgres    192.168.1.108/32       md5
host    trevally_challenge  postgres    0.0.0.0/32       md5
EOF