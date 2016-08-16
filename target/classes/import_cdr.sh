#!/bin/bash
##########################################################
# IMPORTAÇÃO DE CDRS DO FREESWITCH PARA O BANCO DE DADOS #
# Renato Muller Rosa					 #
# Data: 07/10/2014					 #
# IFSC Campus São José					 #
##########################################################

DB_USER=root
DB_PASS=root
DB_IP=localhost
DB_NAME=fsbilling

mkdir /tmp/cdr_backup/ &> /dev/null
/usr/local/freeswitch/bin/fs_cli -x 'cdr_csv rotate'
mysql -u$DB_USER -p$DB_PASS -h$DB_IP $DB_NAME < /usr/local/freeswitch/log/cdr-csv/Master.csv.*
mv /usr/local/freeswitch/log/cdr-csv/Master.csv.* /tmp/cdr_backup/

