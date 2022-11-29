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




