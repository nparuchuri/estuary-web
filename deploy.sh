docker build -t paruchurink/estuary-web -f Dockerfile 
docker push paruchurink/estuary-web 

kubectl apply -f estuary-web-ignite-namespace.yaml
kubectl apply -f estuary-web-ignite-account-role.yaml
kubectl apply -f estuary-web-ignite-service-account.yaml
kubectl apply -f estuary-web-ignite-role-binding.yaml
kubectl config set-context $(kubectl config current-context) --namespace=ignite
kubectl apply -f estuary-web-ignites-service.yaml
kubectl apply -f estuary-web-deployment.yaml