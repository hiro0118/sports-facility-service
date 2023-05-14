FROM gradle:8.1.1-jdk17 AS build
COPY --chown=gradle:gradle /settings.gradle /home/gradle/tennisservice/
COPY --chown=gradle:gradle /build.gradle /home/gradle/tennisservice/
COPY --chown=gradle:gradle /src /home/gradle/tennisservice/src
WORKDIR /home/gradle/tennisservice
RUN gradle build

FROM openjdk:17
COPY --from=build /home/gradle/tennisservice/build/libs/tennis-service-1.0.jar /usr/app/tennis-service-1.0.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/tennis-service-1.0.jar"]