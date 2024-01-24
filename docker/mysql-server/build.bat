docker container prune
docker image prune

docker build . -t mysql
docker run -it -p 33060:3306 -d --name mysql1 mysql

docker exec -it mysql1 mysql -uroot -p