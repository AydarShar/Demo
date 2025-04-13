# language: en

Feature: Авторизация
  Scenario: Успешная авторизация
    Given создаем тело запроса с email "eve.holt@reqres.in" и password "cityslicka"
    When отправляем POST-запрос на "api/login" для авторизации
    Then проверяем статус код 200
    Then проверяем что токен равен "QpwL5tke4Pnpja7X4"