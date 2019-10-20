# FeedMe Tech Test

This repository contains the implementation for the FeedMe application.
It uses Spring Boot to startup the application and consumes the Feed data which is then saved to MongDB.
Spring Boot is used as the dependencies can be injected dynamically and it has a simpler abstraction for inserting data into MongoDb.

To ensure that the application starts up correctly please run the following steps in the root directory
 1. mvn package to generate the jar file for the feedme application
 2. docker-compose up will copy the feedme application jar and start the corresponding containers.
 3. It will continue to insert values in DB until there are no more messages
 4. The application just considers Create event and does not consider Update events
