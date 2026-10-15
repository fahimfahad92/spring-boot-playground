**Introduction:**

This is a docker compose template to start up a redis instance and a mysql instance.

**MySQL:**

Here I am using an initialization file that will create a table and insert some data. 
We can only use this once. I also used local volume to make the data persistent. Even if I
deleted the containers, data will be locally stored in the mapped volume.

I am loading the DB passwords from a file. I am using another user along with root.
Docker will create that new user and give access to the database I mentioned in the compose file.

**Redis:**

Redis will simply start at port 6379.

**How to run:**

1. Go to the folder where compose.yaml file is created
2. Execute 'docker compose up'
3. Docker will download the image (if not already downloaded) and
   start instances of redis and mysql image. I am using Redis 7.4 and MySQL 5.7.
4. For Redis, I mapped a port 6379:6379, so I will be able to access my redis instance from my client using 6379.
5. For MySQL, Docker will execute init script and map the local volume.
6. After MySQL instance is up and running we will be able to connect to MySQL client.


**Connecting to MySQL client:**

docker exec -it docker-compose-mysql-redis-db-1 mysql -u root -p