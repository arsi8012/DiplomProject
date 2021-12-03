### Инструкция для настройки и запуска автоматического тестирования веб-сервиса для покупки тура для путешествия

**Документация к курсовой работе:**

- [Планирование автоматизации тестирования](documents/Plan.md)
- Отчётные документы по итогам автоматизированного тестирования
- Отчётные документы по итогам автоматизации

**Инструкция по запуску:**

1. Запустить контейнер с базой данных MySQL через терминал в программе Intellij IDEA, выполнив команду: docker-compose up -d
2. В новой вкладке терминала запустить тестируемое приложение, выполнив команду: java -jar "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" ./artifacts/aqa-shop.jar
3. Запустить тесты командой: ./gradlew clean test
4. По окончанию тестирования отключить приложение сочетанием клавиш:Ctrl + C и остановить БД docker-compose down