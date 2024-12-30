
```bash
$ export CPLUS_INCLUDE_PATH=/Library/Developer/CommandLineTools/SDKs/MacOSX15.2.sdk/usr/include/c++/v1
$ clang++ -std=c++11 Josephus.cpp 
$ ./a.out
```

Docker:
```bash
$ mvn clean package
$ jar -tvf target/josephus-api-1.0-SNAPSHOT.jar
$ inikube docker-env
$ eval $(minikube -p minikube docker-env)
$ docker build -t josephus-api:1.0 .
```

Reference: https://kubernetes.io/docs/tutorials/hello-minikube/
```bash 
$ sudo install minikube-darwin-arm64 /usr/local/bin/minikube

$ minikube start

$ minikube dashboard

$ kubectl apply -f deployment.yaml

$ minikube ip
192.168.49.2

$ kubectl expose  deployment josephus-deployment --type=LoadBalancer --port=8080

$ kubectl get pods
NAME                                  READY   STATUS    RESTARTS   AGE
josephus-deployment-cd5bdc57f-hfsbn   1/1     Running   0          22h

$ kubectl logs josephus-deployment-cd5bdc57f-hfsbn

$ minikube service josephus-deployment
|-----------|---------------------|-------------|---------------------------|
| NAMESPACE |        NAME         | TARGET PORT |            URL            |
|-----------|---------------------|-------------|---------------------------|
| default   | josephus-deployment |        8080 | http://192.168.49.2:30172 |
|-----------|---------------------|-------------|---------------------------|
🏃  Starting tunnel for service josephus-deployment.
|-----------|---------------------|-------------|------------------------|
| NAMESPACE |        NAME         | TARGET PORT |          URL           |
|-----------|---------------------|-------------|------------------------|
| default   | josephus-deployment |             | http://127.0.0.1:51423 |
|-----------|---------------------|-------------|------------------------|
🎉  Opening service default/josephus-deployment in default browser...
❗  Because you are using a Docker driver on darwin, the terminal needs to be open to run it.

$ curl -X POST http://127.0.0.1:51423/josephus/lastPerson -H "Content-Type: application/json" -d '{"people":["Alice", "Bob", "Charlie", "David", "Eve"], "stepCount":3}'
```