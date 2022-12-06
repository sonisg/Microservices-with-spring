# Starting all the microservice using docker compose #

Steps:
1. Generate the docker images and push them into Docker hub.
2. Now write docker-compose.yml files inside accounts/docker-compose folder for each profile with the following content.
   ``` 
       version: "3.8"
       services:
       configserver:
          image: eazybytes/configserver:latest
          mem_limit: 700m
          ports:
           - "8071:8071"
          networks:
           - eazybank
      
       accounts:
          image: eazybytes/accounts:latest
          mem_limit: 700m
          ports:
            - "8100:8100"
          networks:
            - eazybank
          depends_on:
            - configserver
          deploy:
            restart_policy:
            condition: on-failure
            delay: 5s
            max_attempts: 3
            window: 120s
          environment:
             SPRING_PROFILES_ACTIVE: default
             SPRING_CONFIG_IMPORT: configserver:http://configserver:8071/
  
      loans:
        image: eazybytes/loans:latest
        mem_limit: 700m
        ports:
           - "8090:8090"
        networks:
           - eazybank
        depends_on:
           - configserver
        deploy:
            restart_policy:
            condition: on-failure
            delay: 5s
            max_attempts: 3
            window: 120s
        environment:
            SPRING_PROFILES_ACTIVE: default
             SPRING_CONFIG_IMPORT: configserver:http://configserver:8071/
    
      cards:
        image: eazybytes/cards:latest
        mem_limit: 700m
        ports:
          - "9000:9000"
       networks:
         - eazybank
        depends_on:
         - configserver
        deploy:
          restart_policy:
          condition: on-failure
          delay: 5s
          max_attempts: 3
          window: 120s
       environment:
         SPRING_PROFILES_ACTIVE: default
          SPRING_CONFIG_IMPORT: configserver:http://configserver:8071/
      
      networks:
         eazybank:
         
  3. Based on the active profile that you want start the microservices, open the command line tool where the docker-compose.yml is present and run the docker compose command "docker-compose up" to start all the microservices containers with a single command. All the running containers can be validated by running a docker command "docker ps".
  4. To validate if individual microservices like accounts, loans & cards are able to fetch properties from configserver based on the started active profile value, invoke the REST APIs http://localhost:8100/account/properties, http://localhost:8090/loans/properties, http://localhost:9000/cards/properties through browser. You should get the properties related to a microservice based on the active profile started.
  5. Stop the three running containers by executing the docker compose command "docker-compose down" from the location where docker-compose.yml is present.
