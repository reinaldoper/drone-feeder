# Etapa de build
FROM eclipse-temurin:11-jdk AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -Dmaven.test.skip=true


# Etapa de runtime
FROM eclipse-temurin:11-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8888
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]
