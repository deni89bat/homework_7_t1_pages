# Домашняя работа 7 к лекции 8 AT Frameworks и Patterns

## Описание задания

Все практические задания выполняются на открытом
стенде [the-internet.herokuapp.com](https://the-internet.herokuapp.com/).

### Задания:

## Запуск тестов

Для запуска тестов и генерации отчетов выполните следующие команды:

1. Запустите тесты из модуля `ui-tests`:
    ```bash
    ./gradlew :ui-tests:test --tests "tests.HomeWorkPageObjTests" 
2. Сгенерируйте отчет Allure:
   ```bash
    ./gradlew allureReport 
3. Просмотрите отчет Allure в веб-браузере:
   ```bash
   ./gradlew allureServe 
