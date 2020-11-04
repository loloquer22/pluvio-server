#!/bin/bash
# Script de sauvegarde de la bdd chaque mois

cd /home/pi/mariadb_backup
# on sauvegarde les base de egroupware,jcb et spip
for i in relevepluie; do

## Sauvegarde des bases de données en fichiers .sql
mysqldump -ujojo -pjojo $i > ${i}_`date +%D | sed 's;/;-;g'`.sql

## Compression des exports en tar.bz2 (le meilleur taux de compression)
tar jcf ${i}_`date +%D | sed 's;/;-;g'`.sql.tar.bz2 ${i}_`date +%D | sed 's;/;-;g'`.sql

## Suppression des exports non compressés
rm ${i}_`date +%D | sed 's;/;-;g'`.sql

done

