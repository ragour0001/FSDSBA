FROM java:8-jre
WORKDIR usr/src
ENV MYSQL_DATABASE=UserNewsAppDb
ENV MYSQL_USER=approot
ENV MYSQL_PASSWORD=root123
ENV MYSQL_URL=jdbc:mysql://localhost:3306/UserNewsAppDb
ADD ./target/userservice-1.0.jar /usr/src/userservice-1.0.jar
ENTRYPOINT ["java","-jar","userservice-1.0.jar"]
