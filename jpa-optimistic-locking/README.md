This is a template project for Spring Data JPA using Java 21, Spring Boot 3.3.4 and MySQL 5.7

**Overview:**

In this project, I created 4 entities.

1. User
2. Address
3. Order
4. UserBalance

**Here I demonstrated:**

1. Hikari connection pooling.
2. Auditing
3. projection
4. Concurrent modification (optimistic lock)
5. Global exception handling

**How to run:**

I added a docker compose file in the **docker-compose** folder. Executing **docker compose up** will create a database
with the init script that will create all the necessary tables. During startup, application will create a user with some
predefined balance.

Now we can perform load test the apis using the file added in the **LoadTest** folder. I used JMeter to perform load
test. During the load test, user balance will not get corrupted by concurrent transactions. Also, api is configured in a
way that normal balance check will work smoothly. 


