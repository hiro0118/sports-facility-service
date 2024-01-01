FROM gradle:8.1.1-jdk17 AS build
COPY --chown=gradle:gradle /settings.gradle /home/gradle/tennisapi/
COPY --chown=gradle:gradle /build.gradle /home/gradle/tennisapi/
COPY --chown=gradle:gradle /src /home/gradle/tennisapi/src
WORKDIR /home/gradle/tennisapi
RUN gradle build

FROM openjdk:17
COPY --from=build /home/gradle/tennisapi/build/libs/tennis-api-1.0.jar /usr/app/tennis-api-1.0.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/tennis-api-1.0.jar"]
