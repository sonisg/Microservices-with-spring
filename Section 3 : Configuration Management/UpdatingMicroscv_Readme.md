# Integrate microservice to Config Server #

Description: Connecting the config sever to other microservices(accounts, loans, cards) to read the config.

Steps:
1. Update the pom.xml files inside these microservices with <spring-cloud.version> details, spring-cloud-starter-config dependency, spring-cloud-dependencies under dependencyManagement.
2. Update accounts properties to use the config server.
   ```  
    spring.application.name=accounts
    # the name of the application same as mentioned in config folder.
    spring.profiles.active=prod
    # environment to connect the microservice to.
    spring.config.import=optional:configserver:http://localhost:8071
    # config server connected to.
    management.endpoints.web.exposure.include=*
    # to activate the refresh api
    
  3. Check if your Config Server is running at port 8071, if not go to your Spring Boot main class ConfigserverApplication.java and right click-> Run As -> Java Application. This will start your Spring Boot application successfully at port 8071 which is the port we configured inside application.properties. 
     You can confirm the same by looking at the console logs.
  4. Start the microservices at 8100, 8090, 9000.
  5. To validate if individual microservices like accounts, loans & cards are able to fetch properties from configserver based on the cofigured spring.profiles.active value, invoke the REST APIs http://localhost:8100/account/properties, http://localhost:8090/loans/properties, http://localhost:9000/cards/properties through browser. 
     You should get the properties related to a microservice based on the active profile configured.
