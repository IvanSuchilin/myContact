# myContact
___

*Тестовое задание для Digital chief (JAVA TRAINEE)*
___

Это приложение-блокнот myContact

 ## Стек
<img src="https://img.shields.io/badge/Java-C71A36?style=for-the-badge&logo=Java&logoColor=white"/> <img src="https://img.shields.io/badge/SPring boot-%236DB33F?style=for-the-badge&logo=Spring Boot&logoColor=white"/> <img src="https://img.shields.io/badge/PostgreSQL-blue?style=for-the-badge&logo=PostgreSQL&logoColor=white"/> <img src="https://img.shields.io/badge/Hibernate-006400?style=for-the-badge&logo=Hibernate&logoColor=white"/> <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white"/>
<img src="https://img.shields.io/badge/DOcker-blue?style=for-the-badge&logo=DOcker&logoColor=white"/>
<img src="https://img.shields.io/badge/H2-black?style=for-the-badge&logo=H2&logoColor=white"/>

### Функционал

Это приложение-блокнот, где можно создавать, удалять, редактировать тематические группы и заносить
в них информацию о своих контактах, чтобы всегда помнить "Что это за человек был тогда с тобой на йоге..".

В ветке *main* финальный merge из ветки develop согласно ТЗ.

В ветке *tests* добавлены тесты (бд H2 настроена для активного профиля test)

---
### Возможности по улучшению

Планирую добавить unit и конфигурационные тесты

### Запуск и тестирование приложения
- установить JDK 11+ (требуется для работы программы)
- скачать zip-архив с репозитория https://github.com/IvanSuchilin/myContact (нажав *зеленую кнопку code-download zip*)
- чтобы запустить программу из Main класса MyContactApplication - необходима запущенная бд Postgres
 
 С помощью pgAdmin4 создайте базу данных (user/password/database): postgres/091141/contacts
 - чтобы запустить программу в контейнере Docker (предварительно установить и запустить Docker)
 необходимо запустить процесс сборки docker-compose.yml
 - в папке postman подготовлены тесты для проверки работы программы (логика запуска тестов последовательная как в коллекции)

### Зависимости
 ``` java 
<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-ui</artifactId>
			<version>1.6.13</version>
		</dependency>
		<dependency>
			<groupId>org.postgresql</groupId>
			<artifactId>postgresql</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.mapstruct</groupId>
			<artifactId>mapstruct</artifactId>
			<version>1.5.3.Final</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>
   
```
