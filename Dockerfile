FROM maven:3.8.2-openjdk-17-slim as build

WORKDIR /workspace/app
COPY . .
RUN mvn package -DskipTests

FROM openjdk:17-slim
COPY --from=build /workspace/app/target/cars-api-0.0.1-SNAPSHOT.jar cars-api.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","-XX:MinRAMPercentage=70","-XX:MaxRAMPercentage=90","-server","-XX:+OptimizeStringConcat","-XX:+UseStringDeduplication","-Doracle.jdbc.timezoneAsRegion=false","/cars-api.jar"]
