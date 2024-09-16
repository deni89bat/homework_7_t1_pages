Создать мультимодульные проекты (на maven и на gradle) из двух модулей: api-tests и ui-tests

Главный проект должен иметь зависимости (здесь и далее все версии – последние релизные, указать их через свойства):

- org.junit.jupiter:junit-jupiter

- io.qameta.allure:allure-junit5

Модуль «api-tests» должен иметь зависимости:

- всё от родительского проекта

- io.rest-assured:rest-assured

- io.qameta.allure:allure-rest-assured

- com.fasterxml.jackson.core:jackson-databind

Модуль «ui-tests» должен иметь зависимости:

- всё от родительского проекта

- от проекта «api-tests»

- org.testcontainers:selenium

- com.codeborne:selenide

- io.qameta.allure:allure-selenide

Настроить варианты запуска:

- все тесты

- только тесты из модуля «api-tests»

- только тесты из модуля «ui-tests»

- только Smoke-тесты обоих модулей (по тэгу «smoke»)

Если требуется подключение и настройка плагинов – подключаем и настраиваем
