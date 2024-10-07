**Introduction:**

This is a docker compose template to start up a mysql instance. 
Here I am using an initialization file that will create a table 
and insert some data. We can only use this once.
I also used local volume to make the data persistent. Even if I
deleted the containers, data will be locally stored in the mapped volume.

I am loading the DB passwords from a file. I am using another user along with root.
Docker will create that new user and give access to the 
database I mentioned in the compose file.

**How to run:**

1. Go to the folder where compose.yaml file is created
2. Execute 'docker compose up'
3. Docker will download the image (if not already downloaded) and
start an instance of mysql image. I am using MySQL 5.7. 
4. Docker will execute init script and map the local volume.
5. After MySQL instance is up and running we will be able to connect to MySQL client.

**Connecting to MySQL client:**

docker exec -it docker-compose-mysql-db-1 mysql -u root -p