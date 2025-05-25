@echo off
set CONTAINER_NAME=beru

:: Если контейнер существует, остановить и удалить его
FOR /f "tokens=* USEBACKQ" %%i IN (`docker ps -aq -f name=^%CONTAINER_NAME%$`) DO (
    echo Stopping and removing existing container: %CONTAINER_NAME% ...
    docker stop %CONTAINER_NAME% >nul 2>&1
    docker rm %CONTAINER_NAME%
)

:: Собрать образ
docker build -t beru .

:: Запустить контейнер с нужным именем
docker run --name %CONTAINER_NAME% -p 8085:80 beru