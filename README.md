# Messages REST API

## Stack

1. Maven 4 
1. Java 8
1. Spring Boot (part of maven build, no install necessary)
1. Lombok

## Configuration

1. The file /resources/application.properties holds the default message returned.


## Build steps

1. Build and test `mvn clean install`
1. Run server `java -jar target/messages-1.0.jar`