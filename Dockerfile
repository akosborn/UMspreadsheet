FROM adoptopenjdk/openjdk11:latest as build-stage
WORKDIR /usr/app/
COPY build.gradle settings.gradle gradlew /usr/app/
COPY gradle /usr/app/gradle
COPY . .
RUN ./gradlew bootJar

FROM adoptopenjdk/openjdk11:latest
ARG JAR_FILE=/usr/app/build/libs/*.jar
COPY --from=build-stage ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
