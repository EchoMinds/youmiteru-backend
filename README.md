# Backend for the Youmiteru Website
[![codecov](https://codecov.io/gh/DJ-Java-Team/youmiteru-backend/graph/badge.svg?token=OI80Q5XKJD)](https://codecov.io/gh/DJ-Java-Team/youmiteru-backend)

Этот репозиторий содержит исходный код backend'а для веб-сайта Youmiteru. Данный backend разработан на базе Spring с использованием Java 17 и PostgreSQL.
____
## Установка и настройка

### Требования

Перед началом установки убедитесь, что у вас установлены следующие инструменты:

- Docker

### Установка

1. Клонируйте репозиторий на свой локальный компьютер:

```commandline
git clone https://github.com/DJ-Java-Team/youmiteru-backend.git
```

2. Откройте командную строку и перейдите в каталог проекта:
```commandline
cd youmiteru-backend
```

3. Соберите Docker образ.
```commandline
docker-compose up --build
```

Приложение будет доступно по адресу `http://localhost:8080`.
Документация на Swagger будет доступна по адресу `http://localhost:8080/swagger-ui/index.html`
___
## Структура проекта

- `src/main/java`: Исходные файлы Java.
- `src/main/resources`: Ресурсы, такие как файлы конфигурации и миграции базы данных.

## Дополнительная информация

### Контакты

В случае возникновения вопросов или проблем, пожалуйста, 
свяжитесь с нами в [Discord](https://discord.gg/b22nWq3QFP).

### Лицензия

Этот проект лицензирован под [MIT License](LICENSE).
