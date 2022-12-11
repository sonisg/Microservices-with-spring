## Handling Routing & Cross cutting concerns inside microservices network using Spring Cloud Gateway ##
A new microservices 'gatewayserver' is created based on Spring Cloud Gateway which will help in handling routing & any other cross cutting concerns inside microservices network.
Steps:
1. Fill all the details required to generate a gatewayserver Spring Boot project and add dependencies Spring Cloud Routing,Spring Boot Actuator, Eureka Discovery Client, Config Client, Spring Boot DevTools.
2. Open the SpringBoot main class GatewayserverApplication.java add annotation '@EnableEurekaClient'. Add a routing configurations by creating a @Bean RouteLocator.
3. Add the application.properties inside gatewayserver microservices.
   ``` pring.application.name=gatewayserver

        spring.config.import=optional:configserver:http://localhost:8071

        management.endpoints.web.exposure.include=*

        ## Configuring info endpoint
        info.app.name=Gateway Server Microservice
        info.app.description=Eazy Bank Gateway Server Application
        info.app.version=1.0.0
        management.info.env.enabled = true

        spring.cloud.gateway.discovery.locator.enabled=true
        spring.cloud.gateway.discovery.locator.lowerCaseServiceId=true

        logging.level.com.eaztbytes.gatewayserver: DEBUG
        
 4. Add config to gatewayserver.properties.
      ``` server.port=8072
          eureka.instance.preferIpAddress = true 
          eureka.client.registerWithEureka = true
          eureka.client.fetchRegistry = true
          eureka.client.serviceUrl.defaultZone = http://localhost:8070/eureka/
 5. Run GatewayserverApplication.java and this will start your Spring Boot application successfully at port 8072 which is the port we configured inside gatewayserver.properties. 
 6. Access the URL http://localhost:8072/eazybank/accounts/myCusomerDetails through Postman by passing the below request in JSON format. You should get the response from the accounts microservices which has all the details related to account, loans and cards.
 7. In order to implement cross cutting concerns inside your microservices, please create the classes TraceFilter.java, ResponseTraceFilter.java, FilterUtility.java.
 8. Update all the important classes like AccountsController.java, LoansController.java, CardsController.java to accept the @RequestHeader("eazybank-correlation-id") String correlationid as input inside the method parameters.
 9. Restart your gatewayserver microservice and invoke the REST API http://localhost:8072/eazybank/accounts/myCusomerDetails. You should get the response from the accounts microservices which has all the details related to account, loans and cards. Also validate if you received custom header eazybank-correlation-id that we created in the response.
 10. Validate the logger statements of gatewayserver microservice to check if the eazybank-correlation-id value is logged properly or not.
