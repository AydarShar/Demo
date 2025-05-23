# language: en

Feature: Пользователь
  Scenario: Создание пользователя
    Given cоздаем тело запроса для пользователя с именем "morpheus" и должностью "leader"
    When отправляем POST-запрос на "/api/users" для создания пользователя
    Then проверяем статус ответа 201
    Then сравниваем имя в ответе c именем "morpheus"

  Scenario: Удаление пользователя
    Given cоздаем тело запроса для пользователя с именем "morpheus" и должностью "leader"
    And отправляем POST-запрос на "/api/users" для создания пользователя
    When отправляем DELETE-запрос на "/api/users/" для удаление пользователя
    Then проверяем статус ответа 204

  Scenario: Получить несуществующего пользователя
    When отправляем GET-запрос на "/api/users/" с id 23 для получения несуществующего пользователя
    Then проверяем статус ответа 404