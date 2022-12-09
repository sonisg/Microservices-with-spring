## Service Discovery & Registration inside microservices network using Spring Cloud Netflix Eureka ##

The Eureka naming server comes into existence when we want to make maintenance easier. All the instances of all microservices will be register with the Eureka naming server. Whenever a new instance of a microservice comes up, it would register itself with the Eureka naming server. The registration of microservice with the naming server is called Service Registration.

Whenever a service wants to talk with another service, suppose CurrencyCalculationService wants to talk to the CurrencyExchangeService. The CurrencyCalculationService first talk with the Eureka naming server. The naming server provides the instances of CurrencyExchangeService that are currently running. The process of providing instances to other services is called Service Discovery.

Service registration and service discovery are the two important features of the naming server.

### Connecting all the microservices to a central server ##

Desciption: This repository has four maven projects with the names accounts, loans, cards, configserver. 
A new microservices 'eurekaserver' is created based on Spring Cloud Netflix Eureka which will act as a Service Discovery & Registration server. All the existing microservices accounts, loans, cards are updated to register themself with the eurekaserver during the startup and send heartbeat signals.

**Steps to start eureka server**
1. Fill all the details required to generate a eurekaserver Spring Boot project and add dependencies Eureka Server,Spring Boot Actuator, Config Client.
2. Add annotation **@EnableEurekaServer** to EurekaServer project. This annotation will make your microservice to act as a Spring Cloud Netflix Eureka Server.
3. In **application.properties** add the following,
    ``` 
     spring.application.name=eurekaserver
     #(The name of the application)
     spring.config.import=optional:configserver:http://localhost:8071
     #(To get the config from configserver & help in connecting to the Config Server)
4. Add config to eureka.properties in the config server with following properties.
    ``` 
      server.port=8070
      eureka.instance.hostname=localhost
      eureka.client.registerWithEureka=false
      eureka.client.fetchRegistry=false
      eureka.client.serviceUrl.defaultZone=http://${eureka.instance.hostname}:${server.port}/eureka/     
 5. Start the config server first & then the eureka server.
 6. Access the URL http://localhost:8070 inside your browser and make sure that you are able to access the Eureka Dashboard home page.
 
 **Steps to connect accounts microservice to eureka server**
 1. Add dependencies in pom.xml.
    ``` 
        <dependency>
	        <groupId>org.springframework.cloud</groupId>
	            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>       
  2. Open the application.properties inside accounts microservices and add the below entries inside it which will help in integrating with eurekaserver.
     ```
        eureka.instance.preferIpAddress = true 
        eureka.client.registerWithEureka = true
        eureka.client.fetchRegistry = true
        eureka.client.serviceUrl.defaultZone = http://localhost:8070/eureka/

        ## Configuring info endpoint
        info.app.name=Accounts Microservice
        info.app.description=Eazy Bank Accounts Application
        info.app.version=1.0.0
        management.info.env.enabled = true

        endpoints.shutdown.enabled=true
        management.endpoint.shutdown.enabled=true
   3. Start the accounts microservice. Access the Eureka Server Dashboard URL http://localhost:8070 inside your browser and make sure that you are able to see that accounts microservice details on the Eureka Dashboard home page.

## Interconnecting microservice using Feign Client ##
Feign, as a client, is an important tool for microservice developers to communicate with other microservices via Rest API. Feign is used as a Load Balancer using Eureka.
Accounts microservice is also updated to connect with loans and cards microservices using Netflix Feign client.

**Steps to connect accounts ms to loans & cards ms**
1. Add Feign dependency in accounts pom.xml.
    ``` 
      <dependency>
	      <groupId>org.springframework.cloud</groupId>
	          <artifactId>spring-cloud-starter-openfeign</artifactId>
      </dependency>
2. In order to set up Client side load balancing using Feign client, add **@EnableFeignClients** annotation on top of AccountsApplication.java class which is present inside accounts microservice.
3. Create two interfaces with the name LoansFeignClient.java,CardsFeignClient.java inside accounts microservice project. These two interfaces and the methods inside them will help to communicate with loans and cards microservices using Feign client from accounts microservice.
4. **@FeignClient("loans")** helps to connect accounts to loans.
5. In order to fetch the cards and loans details using Feign client from accounts microservice, update the AccountsController.java to expose a new REST API **/myCustomerDetails**.
6. Restart the accounts microservice and test the feign client changes done by invoking the endpoint http://localhost:8100/myCustomerDetails through Postman by passing the below request in JSON format. You should get the response from the accounts microservices which has all the details related to account, loans and cards.


  
 
