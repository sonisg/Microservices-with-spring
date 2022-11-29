# Updating Accounts microservices to make them Docker compatible using Docker command process #
### Key Steps ### 
- Go to https://www.docker.com/ and create an account in it.
- To verify docker install **docker --version**
- Create **Dockerfile** inside the microservice folder and write the commands to execute once docker is started.
- To create image run **docker build . -t tagname**
- To check docker images **docker images**
- To create container **docker run -p 8080(spring boot running port):8080(docker port) image_name**
- To check running container **docker ps**
- To stop running container **docker stop [container-id]**
- To deploy docker image **docker push docker.io/[image-name]** 

# Updating Loans microservices to make them Docker compatible using Buildpacks #
### Key Steps ###
- Update the respective pom.xml files with the below build plugin details. This will allow us to create docker images with out the need of Dockerfile.
  ```<build>
	<plugins>
		<plugin>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-maven-plugin</artifactId>
			<configuration>
				<image>
					<name>eazybytes/${project.artifactId}</name>
				</image>
			</configuration>
		</plugin>
	</plugins>
</build> 
- To generate docker image **mvn spring-boot:build-image**

# To start all the container at once #
- Install docker compose.
- Validate docker compose running **docker-compose --version**
- Write a **docker-compose.yml** file with below content.
- To start all microservice container at once **docker-compose up**
- To stop all microservice container at once **docker-compose down**
``` version: "3.8"
services:

  accounts:
    image: eazybytes/accounts:latest
    mem_limit: 700m
    ports:
      - "8080:8080"
    networks:
      - eazybank-network
    
  loans:
    image: eazybytes/loans:latest
    mem_limit: 700m
    ports:
      - "8090:8090"
    networks:
      - eazybank-network
    
  cards:
    image: eazybytes/cards:latest
    mem_limit: 700m
    ports:
      - "9000:9000"
    networks:
      - eazybank-network
    
networks:
  eazybank-network:  


