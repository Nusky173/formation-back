FROM mysql/mysql-server:8.0

#script to init server and database
ADD 1-script-init.sql /docker-entrypoint-initdb.d

#script to add data on database
ADD 2-script-data-init.sql /docker-entrypoint-initdb.d

EXPOSE 3306

ENV MYSQL_ROOT_PASSWORD=simon.lecordierpswd
ENV MYSQL_USER=simon.lecordier
ENV MYSQL_DATABASE=formation
ENV MYSQL_PASSWORD=simon.lecordierpswd
