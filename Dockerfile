FROM openjdk:11.0-jdk as build
WORKDIR /app
COPY . .
RUN ./mvnw clean package

FROM openjdk:11.0-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8888
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar"]