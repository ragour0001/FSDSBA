
# NOTE : In my git labs mysql server start automatically. My humble request to user first stop the mysql server then do docker-compose up


# NewsApp - 

![NewsApp Logo](./NewsappUI/src/assets/image/newsApp.jpeg)




## About the News App

In this app user can search news article,  open the article, add article to favourite list and recommend most liked/favourite articles to user and user can update the comments about the articles.


## Features of the NewsApp

- A frontend where the user can register/login to the application, after register/login user will get the artiles from third api, search for article, open interested article, add article to favourite list and view recommended articles.

- User can add an article to favourite list and able to view the favourite list and recommendation list.

- A recommendation service will store all the unique favourite articles from all the users and maintain counter for number of users added a particular article into favourite list. 



## Services and modules in the NewsApp

- NewsappUI : This is frontend where the user can register/login to the application, search for article, open interested article, add article to favourite list and view 					recommended articles.
				1. Search for a news article
				2. View or open an article 
				3. Add an article into favourite list
				4. View favourite articles
				5. View recommended articles
				5. UI is responsive which can run smoothly on various devices 

- userservice : This service will be manage user accounts.
				1. Created a server in Spring Boot to facilitate user registration and login using JWT    token and MySQL
				2.  swagger documentation (http://localhost:8071/swagger-ui.html)
				3. It will use port 8071


- favouriteservice : This service will be store all the favourite articles for a user
					1. Created a server in Spring Boot to facilitate CRUD operation over favourite articles   stored in MongoDB
					2. swagger documentation (http://localhost:8072/swagger-ui.html)
					3. It will produce the article for RabbitMQ
					4. It will use port 8072


- articleRecommendationService : This service will be store all the unique favourite articles from all the users and maintain counter for number of users added a 									 particular article into favourite list.
						
								1. Created a server in Spring Boot to facilitate CRUD operation over favourite articles   stored in MongoDB
								2. swagger documentation (http://localhost:8072/swagger-ui.html)
								3. It will consume the article for RabbitMQ and Store the recommendation list in MongoDB.
								4. Maintains list of unique recommended articles based on what user added into favourite list and keep counter for number of users added a 									   particular article into favourite list
								5. swagger documentation (http://localhost:8074/swagger-ui.html)
								6. It will use port 8074



- eurekaserverdemo : This service will be act as server where userservice, favouriteservice and articleRecommendationService will become clients. It will use port 9004


- zuulservice : This service is an API gateway, which will serve UI and API Request. It will use port 8073



## List of service and ports

	- userservice : 8071

	- favouriteservice : 8072

	- articleRecommendationService : 8074

	- eurekaserverdemo : 9004

	- zuulservice : 8073
	



## Tech Stack used in the NewsApp

- Spring Boot
- MySQL, MongoDB
- API Gateway
- Eureka Server
- Message Broker (RabbitMQ)
- Angular
- CI (Gitlab Runner)
- Docker, Docker Compose


##steps for installation and deployment of news app

### NOTE : In my git labs mysql server start automatically. My humble request to user first stop the mysql server then proceed below steps

	1. Open terminal where docker-compose.yml file located
	
	2. Location : Desktop/SBADev/ragour19-newsapp-java-boilerplate (Here you will find the docker-compose.yml file)

	3. Do docker-compose up

	4. In new tab of terminal you can check the status of all services: (docker ps -a)

	5. User can check the client service status on eureka server (http://localhost:9004)

	6. User can check rabbitMQ server using (http://localhost:15672) username/password is guest/guest

	7. open browser and type http://localhost:8080 (Here you can perform the actions) 

	8. Once User Register, user need to wait till it consume by userservice and after consumig user can login to News App and perform the crus operation.


## Author

	Name: RAKESH GOUR

	E-Mail: ragour19@in.ibm.com






