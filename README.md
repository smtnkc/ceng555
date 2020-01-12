# CENG555 Project

## Prerequisites:
* Install Java [JRE 8](https://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html)
* Install Java [JDK 11](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
* Install [Apache Maven](https://maven.apache.org/install.html)
* Install [Apache ActiveMQ Message Broker](https://activemq.apache.org/components/classic/)

## Step 1) Generate executable JAR files for each service
```bash
discovery-server/mvn clean install
application-service/mvn clean install
notification-service/mvn clean install
```

## Step 2) Start Apache ActiveMQ broker for async messaging
```bash
<activemq_dir>/activemq start
```

## Step 3) Start each service on different terminals

```bash
java -jar discovery-server/target/discovery-server-0.0.1-SNAPSHOT.jar
java -jar application-service/target/application-service-0.0.1-SNAPSHOT.jar
java -jar notification-service/target/notification-service-0.0.1-SNAPSHOT.jar
```

## Step 4) Perform service calls and trace the messaging

* To view Eureka discover server `http://localhost:/8000`
* To submit an application: ``http://localhost:8001/application``
* To view all applications: ``http://localhost:8001/applications``
* To view application with an ID: ``http://localhost:8001/applications/{applicationId}``
* To view message queues: ``http://localhost:8161/admin/queues.jsp``

> **Note 1:** You can login to ActiveMQ Web Console by entering ``admin`` into the username and password.

> **Note 2:** You can trace the messaging details throung the relevant terminal or CLI.

## Step 5) Stop the services and the broker
* Stop each service by pressing ``CTRL+C`` on terminal
* Stop broker by running ``<activemq_dir>/activemq stop``