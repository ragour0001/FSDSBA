# NewsApp - A Case Study

## Problem Statement

Build a system to search for news article, open the article, add article to favourite list and recommend most liked/favourite articles to user.

## Requirements

### The application needs to fetch news article from the following API.
https://newsapi.org/

Example API:
https://newsapi.org/v2/everything?q=bitcoin&from=2019-01-06&sortBy=publishedAt&apiKey=b170738ea8d145159da715566679a48fea

### A frontend where the user can register/login to the application, search for article, open interested article, add article to favourite list and view recommended articles.
### User can add an article to favourite list and should be able to view the favourite list.
### A recommendation service should be able to store all the unique favourite articles from all the users and maintain counter for number of users added a particular article into favourite list. 
### An option to view recommended articles should be available on frontend. 

## Microservices/Modules

- UserService - should be able to manage user accounts.
- UI (User interface) -  should be able to
    1. Search for a news article
    2. View or open an article 
    3. Add an article into favourite list
    4. View favourite articles
    5. View recommended articles
    5. UI should be responsive which can run smoothly on various devices 
- FavouriteService - should be able to store all the favourite articles for a user
- ArticleRecommendationService - should be able to store all the unique favourite         articles from all the users and maintain counter for number of users added a            particular article into favourite list.

## Tech Stack
- Spring Boot
- MySQL, MongoDB
- API Gateway
- Eureka Server
- Message Broker (RabbitMQ)
- Angular
- CI (Gitlab Runner)
- Docker, Docker Compose

## Flow of Modules

### Building frontend:
- Building responsive views:
    1. Register/Login
    2. Search for an article
    3. Article list - populating from external API
    4. Build a view to show favourite articles
    5. Build a view to show recommended articles
- Using Services to populate these data in views
- Stitching these views using Routes and Guards
- Making the UI Responsive
- E2E and unit tests
- Writing CI configuration file
- Dockerize the frontend

### Building the UserService
- Creating a server in Spring Boot to facilitate user registration and login using JWT    token and MySQL
- Writing swagger documentation
- Unit Testing
- Write CI Configuration
- Dockerize the application
- Write docker-compose file to build both frontend and backend application

### Create an API Gateway which can serve UI and API Request from same host

### Building the Favourite Service
- Building a server in Spring Boot to facilitate CRUD operation over favourite articles   stored in MongoDB
- Writing Swagger Documentation
- Build a Producer for RabbitMQ which
  - i. Produces events like what user added to favourite list
- Write Test Cases
- Write CI Configuration
- Dockerize the application
- Update the docker-compose

### Building the Article Recommendation Service
- Building a Consumer for RabbitMQ
  - i. On a new event generated Update the recommendations in the system.  Store the         recommendation list in MongoDB.
  - ii. Maintain list of unique recommended articles based on what user added into            favourite list and keep counter for number of users added a particular article        into favourite list
- Build an API to get Recommendations
- Writing Swagger Documentation
- Write Test Cases
- Write CI Configuration
- Dockerize the application
- Update the docker-compose
- Update the API Gateway

### Create Eureka server and make other services as client

### Demonstrate the entire application
