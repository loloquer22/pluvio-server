#!/bin/bash
#
## Supprime les sauvegardes vieilles de plus de 5 jours
#
#find /home/pi/mariadb_backup/ -type f -mtime +4 | xargs -r rm
find /home/pi/mariadb_backup/ -type f -mmonth +2 | xargs -r rm