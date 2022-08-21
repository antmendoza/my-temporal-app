#!/bin/sh

startDateTime=`date +%s`

HOME=$(pwd)


cd $HOME
cd app-api
mvn clean install

cd $HOME
cd app-services
mvn clean install


cd $HOME
cd app-runner
mvn clean install


endDateTime=`date +%s`
spentSeconds=`expr $endDateTime - $startDateTime`
echo
echo "Total execution time: ${spentSeconds}s"