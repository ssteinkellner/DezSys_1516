#!/bin/bash

if [ "$(whoami)" != "root" ]; then
	echo "Dieses Script muss als root ausgefuehrt werden!"
	exit 1
fi

echo "
host all all 192.168.20.1/32 md5" >> /etc/postgresql/9.1/main/pg_hba.conf
