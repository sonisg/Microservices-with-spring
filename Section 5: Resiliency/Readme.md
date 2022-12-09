## Making Microservices Resilient using Resilience4j patterns ##
**Steps**
1. Open the pom.xml of accounts microservice and add the below Resilience4j related dependencies to it,
    ```
        <dependency>
	      <groupId>io.github.resilience4j</groupId>
	      <artifactId>resilience4j-spring-boot2</artifactId>
         </dependency>
        <dependency>
	      <groupId>io.github.resilience4j</groupId>
	      <artifactId>resilience4j-circuitbreaker</artifactId>
        </dependency>
        <dependency>
       	<groupId>io.github.resilience4j</groupId>
	      <artifactId>resilience4j-timelimiter</artifactId>
        </dependency>
        <dependency>
        	<groupId>org.springframework.boot</groupId>
        	<artifactId>spring-boot-starter-aop</artifactId>
        </dependency>
 2. In order to implement the Circuit Breaker pattern for "/myCustomerDetails" API inside accounts microservice, open the AccountsController.java class and add @CircuitBreaker annotation on top of the "/myCustomerDetails" API method along with a fallback method details. Post that add a new fallbackMethod method in the same class with the same parameters like myCustomerDetails() method along with a Throwable t parameter.
 3. To implement the Retry pattern for "/myCustomerDetails" API inside accounts microservice, open the AccountsController.java class and add @Retry annotation on top of the "/myCustomerDetails" API method along with the fallback method details that we build in the previous method.
 4. To implement the RateLimiter pattern for "/myCustomerDetails" API inside accounts microservice, open the AccountsController.java class and add @RateLimiter annotation on top of the "/sayHello" API method along with a fallback method details. Post that add a new fallbackMethod method in the same class with the same parameters like sayHello() method along with a Throwable t parameter.
 5. Open the application.properties inside accounts microservices and make the following entries inside it. These entries will help in configuring the parameters for CircuitBreaker, Retry and RateLimiter patterns.
      ``` 
        # Circuit Breaker
        resilience4j.circuitbreaker.configs.default.registerHealthIndicator= true
        resilience4j.circuitbreaker.instances.detailsForCustomerSupportApp.minimumNumberOfCalls= 5
        resilience4j.circuitbreaker.instances.detailsForCustomerSupportApp.failureRateThreshold= 50
        resilience4j.circuitbreaker.instances.detailsForCustomerSupportApp.waitDurationInOpenState= 30000
        resilience4j.circuitbreaker.instances.detailsForCustomerSupportApp.permittedNumberOfCallsInHalfOpenState=2
        
        # Retry
        resilience4j.retry.configs.default.registerHealthIndicator= true
        resilience4j.retry.instances.retryForCustomerDetails.maxRetryAttempts=3
        resilience4j.retry.instances.retryForCustomerDetails.waitDuration=2000
        
        # Rate Limiter
        resilience4j.ratelimiter.configs.default.registerHealthIndicator= true
        resilience4j.ratelimiter.instances.sayHello.timeoutDuration=5000
        resilience4j.ratelimiter.instances.sayHello.limitRefreshPeriod=5000
        resilience4j.ratelimiter.instances.sayHello.limitForPeriod=1
 6. In order to test the CircuitBreaker, Retry pattern changes, invoke the endpoint http://localhost:8100/myCustomerDetails through Postman by passing the below request in JSON format. Since cards microservice is down, you should be able to see the response from fall back mechanisms configured as part of CircuitBreaker, Retry patterns.
 7. In order to test the RateLimiter pattern changes, invoke the endpoint http://localhost:8100/sayHello multiple times at a time through browser. You should be able to see the response from fall back mechanisms configured for RateLimiter pattern.
  
