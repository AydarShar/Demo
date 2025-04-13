# Demo

## Тестовый проект для сайта reqres.in
- Это АФТ проект для тестирования API сайта reqres.in, используемого как тестовый стенд для REST API.

## Технологический стек:
- Java 17
- RestAssured
- JUnit 5
- Cucumber
- Allure
- Maven

## Функциональности проекта:
- Тестирование REST API endpoints сайта reqres.in
- Поддержка HTTP методов (GET, POST, DELETE)
- Валидация статус кодов и тел ответов
- Генерация отчетов с детализацией выполнения тестов

## Требования:
- Java 17 или выше
- Maven 3.6.0 или выше
- Internet соединение для доступа к reqres.in

## Установка и запуск:
- Клонировать репозиторий:

git clone [repository-url]

- Перейти в директорию проекта:

cd [project-directory]

- Запустить тесты:

mvn clean test

- Для генерации Allure отчета:

mvn allure:report

- После выполнения тестов отчет Allure можно просмотреть с помощью команды:

mvn allure:serve

## Контакты
По вопросам и предложениям обращайтесь: AydarSharafetdin@yandex.ru
