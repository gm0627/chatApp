FROM openjdk:8
EXPOSE 8080
ADD target/chat-app.jar chat-app.jar 
ENTRYPOINT ["java","-jar","/chat-app.jar"]