FROM java:8-jre
WORKDIR usr/src
ENV MONGO_DATABASENAME=newsdb
ENV MONGO_URL=mongodb://localhost:27017/newsdb
ADD ./target/articleRecommendationService-1.0.jar /usr/src/articleRecommendationService-1.0.jar
ENTRYPOINT ["java","-jar","articleRecommendationService-1.0.jar"]
