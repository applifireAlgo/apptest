




echo $PATH
DB_PATH=/tmp/applifire/db/EERXPJTYG0KKIURV6LQYZQ/AE1D8466-F127-46DE-8BE0-0FBF411C1725
MYSQL=/usr/bin
USER=algo
PASSWORD=algo
HOST=localhost


echo 'drop db starts....'
$MYSQL/mysql -h$HOST -u$USER -p$PASSWORD -e "SOURCE $DB_PATH/drop_db.sql";
echo 'drop db ends....'