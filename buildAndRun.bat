@echo off
call mvn clean package
call docker build -t de.papenhagen/testttt .
call docker rm -f testttt
call docker run -d -p 8080:8080 -p 4848:4848 --name testttt de.papenhagen/testttt