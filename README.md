[//]: # (##  Тестовое задание для VitaSoft &#40;Java-developer&#41; / *vita-soft-test* #)

[//]: # ()

[//]: # ()

[//]: # (**Выполненные задачи:**)

[//]: # ()

[//]: # (1&#41; Стек: Java 11, SQL, Spring Boot &#40;MVC, AOP, Data, Security, Eureka, Gateway&#41;, JUnit, Mockito, Docker, Hibernate, PostgreSQL, Swagger, Maven, Maven Checkstyle, Lombok, Mapstruct;)

[//]: # ()

[//]: # (2&#41; Выполнение всех пунктов высланного проверочного задания;)

[//]: # ()

[//]: # (3&#41; Авторизация выполнена на Spring Security &#40;Jwt token&#41; c выделенным сервером авторизации;)

[//]: # ()

[//]: # (4&#41; Реализована одна точка входа в приложение через gateway&#40;port:8080&#41; с перенаправлением запросов.)

[//]: # ()

[//]: # (5&#41; Написана часть тестов для сущности User. &#40;JUnit, Mockito&#41;)

[//]: # ()

[//]: # (6&#41; Для проверки и тестирования API написаны и приложены 44 Postman теста &#40;авторизация происходит перед выполнением запросов приложения, токен записывается в глобальную переменную&#41;.)

[//]: # ()

[//]: # (7&#41; Написана документация в Swagger.)

[//]: # ()

[//]: # (![Диаграмма приложения для vita-soft-test]&#40;docs/diagram.png&#41;)

[//]: # ()

[//]: # (Пользовтели &#40;import.sql&#41;:)

[//]: # ()

[//]: # (   - 1 USER &#40;root1&#41;, ROLE_ADMIN)

[//]: # ()

[//]: # (   - 2 USER &#40;root2&#41;, ROLE_OPERATOR)

[//]: # ()

[//]: # (   - 3 USER &#40;root3&#41;, ROLE_USER)

[//]: # ()

[//]: # (   - 4 USER &#40;root4&#41;, ROLE_USER, после обновления + ROLE_OPERATOR)

[//]: # ()

[//]: # (   - password для всех пользоватей: root)

[//]: # ()

[//]: # (**Roadmap:**)

[//]: # ()

[//]: # (1&#41; Разделение БД сервиса и аутентификации. Передача пользователя в сервис в заголовке запроса.  )

[//]: # ()

[//]: # (2&#41; Написание индивидуальных ошибок для возникающих в сервисах исключений.)

[//]: # ()

[//]: # (3&#41; Дополнение GlobalExceptionController на основе индивидульных ошибок и формирование определенных кодов ответов. )

[//]: # ()

[//]: # (4&#41; Дополнение JUnit + Mockito тестирования с порытием классов и методов min 90%.)

[//]: # ()

[//]: # (Перед началом работы необходитмо убедиться в том, что все сервисы поднялись и зарегистрировались в service discovery. Только после этого тесты Postman отработают верно.)

[//]: # ()

[//]: # (#### [Ссылка на service discovery  &#40;vita-eureka&#41;]&#40;http://localhost:8761/&#41;)

[//]: # ()

[//]: # ()

[//]: # (Веб-сервис для авторизации и выдачи токена. &#40;vita-auth&#41;. Эта ссылка будет работать при запущенном приложении.)

[//]: # ()

[//]: # (#### [Ссылка API swagger-ui документации и доступных эндпоинтов &#40;vita-auth&#41;]&#40;http://localhost:8085/swagger-ui/index.html&#41;)

[//]: # ()

[//]: # (Веб-сервис для обработки потсупающей информации. &#40;vita-service&#41;. Эта ссылка будет работать при запущенном приложении.)

[//]: # ()

[//]: # (#### [Ссылка API swagger-ui документации и доступных эндпоинтов &#40;vita-service&#41;]&#40;http://localhost:8090/swagger-ui/index.html&#41;)

[//]: # ()

[//]: # (![Схема БД для vita-soft-test]&#40;docs/claims.png&#41;)

[//]: # ()

[//]: # ()

[//]: # (## Быстрый старт)

[//]: # ()

[//]: # (### Требования)

[//]: # ()

[//]: # (- Java Platform &#40;JDK&#41; 11)

[//]: # ()

[//]: # (- Apache Maven 4.x)

[//]: # ()

[//]: # ()

[//]: # (Находясь в каталоге в командной строке, введите:)

[//]: # ()

[//]: # ()

[//]: # (`./mvn package`)

[//]: # ()

[//]: # (`java -jar vita-soft-test/vita-eureka/target/vita-eureka-0.0.1-SNAPSHOT.jar`)

[//]: # ()

[//]: # (`java -jar vita-soft-test/vita-gateway/target/vita-gateway-0.0.1-SNAPSHOT.jar`)

[//]: # ()

[//]: # (`java -jar vita-soft-test/vita-service/target/vita-service-0.0.1-SNAPSHOT.jar`)

[//]: # ()

[//]: # (`java -jar vita-soft-test/vita-auth/target/vita-auth-0.0.1-SNAPSHOT.jar`)

[//]: # ()

[//]: # ()

[//]: # (## Быстрый старт Docker)

[//]: # ()

[//]: # (### Требования)

[//]: # ()

[//]: # (- Java Platform &#40;JDK&#41; 11)

[//]: # ()

[//]: # (- Apache Maven 4.x)

[//]: # ()

[//]: # (- Docker client &#40;Docker-compose&#41;)

[//]: # ()

[//]: # ()

[//]: # (Находясь в каталоге в командной строке, введите:)

[//]: # ()

[//]: # ()

[//]: # (`./mvn package`)

[//]: # ()

[//]: # ()

[//]: # (`docker-compose up`)
