
Spring boot  application with postgresSQL and RabbitMQ
======================

Instructions to run the Application:
===================================

**Prerequisites:**

•	 Docker 

•  maven

**Instruction:**

•	Download the porject files into the local folder

•	open commandPromt and navigate to the project folder run maven command "**mvn clean package -DskipTests**".

•	run docker command " **docker build -t chat-app.jar .**"

•	run docker command "**docker compose up**"

•	once the docker containers up and running  check the url **http://localhost:8080/users **


End Implemented:
======================

•	 POST "/user" -> creating user.

•	 POST "/user/{nickName}"-> creating user with nick name.

•	 GET "/users"-> fetch all users.

•	 GET "/user/{userID}"-> fetch user by ID.

•	 POST "/message"-> send message  to user.

•	 POST "/message/in"-> find all recived messages from perticular user.

•	 POST "/message/out"-> find all sent messages from perticular user.

•	 POST "/message/in/{userId}"-> find all recived messages from perticular user.

•	 POST "/message/in"-> find all recived messages from perticular user.


Ideas for further improvement:
==============================
If I had more time I would have:

•	implemented CI/CD pipeline.

•	implemented more API validation and integration test cases.

•	Application logs.
