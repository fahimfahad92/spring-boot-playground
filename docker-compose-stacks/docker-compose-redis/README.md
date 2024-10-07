**Introduction:**

This is a docker compose template to start up a redis instance. 

**How to run:**

1. Go to the folder where compose.yaml file is created
2. Execute 'docker compose up'
3. Docker will download the image (if not already downloaded) and
   start an instance of redis image. I am using Redis 7.4.
4. I mapped a port 6379:6379, so I will be able to access my redis instance from my client using 6379.