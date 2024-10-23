# Домашняя работа 7 к лекции 8 AT Frameworks и Patterns


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
4. Запустите тесты из модуля `api-tests`:
    ```bash
    ./gradlew :api-tests:test --tests "tests.ApiTests"
5. Сгенерируйте отчет Allure:
   ```bash
    ./gradlew allureReport 
6. Просмотрите отчет Allure в веб-браузере:
   ```bash
   ./gradlew allureServe


 
