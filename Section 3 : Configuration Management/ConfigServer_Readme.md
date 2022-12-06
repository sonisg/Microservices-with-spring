# Configuration management inside microservices network using Spring Cloud Config #

**Description** : This repository has three maven projects with the names accounts, loans, cards. A new microservices 'configserver' is created in this section based on Spring Cloud Config which will act as a Config server. These three microservices accounts, loans, cards are updated to read the configurations/properties from the 'configserver' microservices.
Below are the key steps where we focused on set up of Config Server inside our microservices network.

**Steps:**
## To set up the Config Server microservice: ##
1. Add dependency Config Server,Spring Boot Actuator to Config server spring boot project.
2. Add **@EnableConfigServer** to the ConfigserverApplication.java file.
3. Create a config folder under resources to configure all the properties. 
   For example: https://github.com/eazybytes/microservices-with-spring-sectionwise-code/tree/master/section7/configserver/src/main/resources/config
4. Add properties to application.properties. (From the same spring project)
   ```
      spring.application.name=configserver
      spring.profiles.active=native 
      ##(the place from where the config file should be used.)
      spring.cloud.config.server.native.search-locations=classpath:/config 
      ##(the location from where the config file should be used)
      server.port=8071
      
 5. Add properties to application.properties. (From the system location)
    ```
      spring.application.name=configserver
      spring.profiles.active=native
      spring.cloud.config.server.native.search-locations=file:///C://config
      server.port=8071
 6. Add properties to application.properties. (From git)
    ```
    spring.application.name=configserver
    spring.profiles.active=git
    spring.cloud.config.server.git.uri=https://github.com/eazybytes/microservices-config.git
    spring.cloud.config.server.git.clone-on-start=true
    spring.cloud.config.server.git.default-label=main
    server.port=8071
    
  7. Go to your Spring Boot main class ConfigserverApplication.java and right click-> Run As -> Java Application. This will start your Spring Boot application successfully at port 8071 which is the port we configured inside application.properties. 
     Your can confirm the same by looking at the console logs.
  8. Access the URLs like http://localhost:8071/accounts/default, http://localhost:8071/loans/dev, http://localhost:8071/cards/prod inside your browser to randomly validate that properties are being read from configured Github location by Config Server for all the three microservices accounts, loans and cards.
  
  
