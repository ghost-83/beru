#!/bin/bash

CONTAINER_NAME="beru"

# Если контейнер существует, остановить и удалить его
if [ "$(docker ps -aq -f name=^${CONTAINER_NAME}$)" ]; then
    echo "Stopping and removing existing container: ${CONTAINER_NAME}..."
    docker stop ${CONTAINER_NAME} 2>/dev/null
    docker rm ${CONTAINER_NAME}
fi

# Собрать образ
docker build -t beru .

# Запустить контейнер с нужным именем
docker run --name ${CONTAINER_NAME} -p 8085:80 beru