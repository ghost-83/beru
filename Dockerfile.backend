FROM openjdk:17.0.1-jdk

WORKDIR /usr/src/ghost_main

COPY ./target/beru.jar /usr/src/ghost_main

EXPOSE 8080

ENTRYPOINT ["java","-jar","beru.jar"]