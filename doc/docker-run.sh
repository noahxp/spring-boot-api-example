#!/bin/bash

# get mongo docker image
docker pull mongo:3.2.20-jessie
# 預設執行，不把資料檔掛出來，以利重啟 docker 時資料還原
docker run -p 27017:27017 -d mongo:3.2.20-jessie

# maria db : https://hub.docker.com/_/mariadb/
docker pull mariadb:10.3.8
docker run -p 3306:3306 -e MYSQL_ROOT_PASSWORD=1qaz2wsx -d mariadb:10.3.8