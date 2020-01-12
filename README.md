# CENG555 Project

## Prerequisites:
* Install Java [JRE 8](https://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html)
* Install Java [JDK 11](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
* Install [Apache Maven](https://maven.apache.org/install.html)
* Install [Apache ActiveMQ Message Broker](https://activemq.apache.org/components/classic/)

## Step 0) Set email credentials:
In main directory, create a ``mailAuth.json`` file as shown below to set username and password. These credentials are used by ``notification-service`` to send emails.

```json
{
  "username":"******@gmail.com",
  "password":"****************"
}
```

## Step 1) Start Apache ActiveMQ broker for async messaging
```bash
<activemq_dir>/activemq start
```

## Step 2) Generate executable JAR files for each service

> **Note:** You can import, build and run the projects through an IDE with Maven plugin, as it is shown [HERE](https://www.jetbrains.com/help/idea/delegate-build-and-run-actions-to-maven.html#build_through_maven). If you prefer to do that skip the steps given below.

```bash
discovery-server/mvn clean install
application-service/mvn clean install
notification-service/mvn clean install
```

## Step 3) Start each service on different terminals

> **Note:** If you built and ran the projects through an IDE in Step 1, skip the steps given below.

```bash
java -jar discovery-server/target/discovery-server-0.0.1-SNAPSHOT.jar
java -jar application-service/target/application-service-0.0.1-SNAPSHOT.jar
java -jar notification-service/target/notification-service-0.0.1-SNAPSHOT.jar
```

> **Note:** It is recommended to run the ``discovery-server`` first since the others register to it.

## Step 4) Perform service calls and trace the messaging

* To view Eureka discover server `http://localhost:8000`
* To submit an application: ``http://localhost:8001/application``
* To view all applications: ``http://localhost:8001/applications``
* To view an application using application id: ``http://localhost:8001/applications/{applicationId}``
* To send a notification using application id: ``http://localhost:8002/notification/send/{applicationID}``
* To view message queues: ``http://localhost:8161/admin/queues.jsp``

> **Note:** You can login to ActiveMQ Web Console by entering ``admin`` into the username and password.

> **Note:** You can trace the communication details through CLI logs of each service.

## Step 5) Stop the services and the broker
* Stop each service by pressing ``CTRL+C`` on terminal
* Stop broker by running ``<activemq_dir>/activemq stop``