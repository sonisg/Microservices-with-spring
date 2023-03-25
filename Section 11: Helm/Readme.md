## HELM for Kubernetes ##

The goal of Helm was to provide users
with a better way to manage all the Kubernetes YAML files we create on Kubernetes projects.

Install helm.

### Create helm chart ##
1. Check helm version by **helm version**
2. Create helm chart **helm create chart-name**

### Install helm chart into k8 cluster ###
1. Go the K8 cluster, check for connect command and run the command on helm terminal to connect it.
2. To install helm chart into K8 cluster **helm install sample-deployment chart-name**.
3. To upgrade **helm upgrade sample-deployment chart-name**.
4. To build **helm dependency build**
