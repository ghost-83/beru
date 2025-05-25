# 1. Сборка Java-приложения
FROM maven:3.9.6-eclipse-temurin-17 AS build-java
WORKDIR /app/beru
COPY /pom.xml .
COPY /src ./src
RUN mvn package -DskipTests

# 2. Собранные фронтенд-файлы
FROM nginx:1.25 AS build-frontend
WORKDIR /usr/share/nginx/html
COPY dist/ .

# 3. Финальный контейнер — Ubuntu с Java и nginx
FROM ubuntu:22.04

# Установка Java 17, nginx и supervisor
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk nginx supervisor && \
    apt-get clean

COPY --from=build-java /app/beru/target/*.jar /usr/src/beru/beru.jar

COPY --from=build-frontend /usr/share/nginx/html /var/www/html
COPY templates/nginx.conf /etc/nginx/nginx.conf

COPY supervisord.conf /etc/supervisor/conf.d/supervisord.conf

# Открываем нужные порты
EXPOSE 80

CMD ["/usr/bin/supervisord"]