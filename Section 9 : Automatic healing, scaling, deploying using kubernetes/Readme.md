## Automatic self-healing, scaling, deployments inside microservices network using Kubernetes ##

### Kubernetes Command and Connection ###
**Using GCP:**
1. Create a GCP account and create a cluster according to your requirements.
2. Enable Cloud Logging API from API and services.
3. Enable stack driver monitoring api for
4. Can connect through active cloud shell/GCP terminal (UI) or system terminal (gcloud container cluster...)
5. To see nodes: **kubectl get nodes**
6. To check pods: **kubectl get pods**
7. To check deployment: **kubectl get deployment**
8. To get all: **kubectl all**

### Creating environment variables using ConfigMap and deploying ###
1. Create the 5 yaml files to get started with deployment of microservices into kubernetes cluster.
2. Run the command **kubectl apply -f 1_configmaps.yml** to create ConfigMaps inside Kubernetes cluster. The same can be validated by running the command kubectl get configmap.
3. Run the commands **kubectl apply -f 2_zipkin.yml, kubectl apply -f 3_configserver.yml, kubectl apply -f 4_eurekaserver.yml, kubectl apply -f 5_accounts.yml** to deploy the applicable microservices into kubernetes cluster. The same can be validated by running the commands **kubectl get pods, kubectl get deployments, kubectl get services, kubectl get replicaset**.
4. Validate if the deployment of microservices is successful or not by using the endpoint IPs provided by kubernetes.

### Automatic Self healing ###
1. Create more replica set for any service by changing replica config to desire. Create 2 pods of account.
2. Delete one pod of account.
3. K8 automatically creates another pod for the deleted one. This way current and desired pod remains same and the service can't be done.

### Automatic rollout and rollback ###
1. Make some change in account service.
2. Two docker containers, one Latest with old change and one K8 with new change.
3. Increase account replica to 3. (**kubectl scale deployment accounts-deployment --replica=3**)
4. To get details of any account: **kubectl describe accounts...** can check for docker and other details.
5. To set the new docker image use command **kubectl set image deployment accounts-deployment accounts=eazybytes accounts/K8s**.
6. It will start the new pod and will terminate the old pod without affecting the server thats means microservice won't come down. (Roll out features one by one).
7. To check what happened command **kubectl get events --sort-by=.metadata.creationTimeStamp**.
8. To check rollout history **kubectl rollout history deployment accounts-deployment**.
9. To rollback to previous version: **kubectl rollout undo deployment accounts-deployment --to-revision=1**.

### Logging and monitoring ###
1. To get logs: **kubectl logs pod-name**.

### Autoscaling using HPA (Horizontal Power Autoscale) ###
1. **kubectl get hpa**
2. **kubectl autoscale deployment accounts-deployment --min=3 --max=10 --cpu-percent=70** autoscale when cpu percent is more than 70. min 3 pods and max 10 pods always available. Scale 3 pods to 10 pods when neccessary.



