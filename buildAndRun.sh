#!/bin/sh
mvn clean package && docker build -t de.papenhagen/testttt .
docker rm -f testttt || true && docker run -d -p 8080:8080 -p 4848:4848 --name testttt de.papenhagen/testttt