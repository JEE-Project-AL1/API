#!/bin/bash
cd API
git checkout master
git pull 
cd apijee
mvn clean install 
cd target
kill $(ps aux | grep "jar API-JEE-0.0.1-" | grep -v "grep" | head -1 | awk '{print $2}')
nohup java -Dspring.profiles.active=dev -jar API-JEE-0.0.1-SNAPSHOT.jar &
