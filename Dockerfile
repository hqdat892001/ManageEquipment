# First stage: complete build environment
FROM maven:3.8.5-openjdk-17 AS build

# add pom.xml and source code
COPY . .
RUN  mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim

# copy jar from the first stage
COPY --from=build target/ManageEquipment-0.0.1-SNAPSHOT.jar ManageEquipment.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "ManageEquipment.jar"]