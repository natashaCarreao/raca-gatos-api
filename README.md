# raca-gatos-api
Aplicação Java 11, Spring 2.6.4 e Gradle criada para armazenar os dados consultados das API's The Cats Api.
Para os visualizar a documentação das API que esta aplicação consulta, acessar https://documenter.getpostman.com/view/5578104/RWgqUxxh#19c0515e-27da-415a-b844-bc7fe37d038c
Para visualizar os detalhes das API's desta alicação, acessar: http://localhost:8080/raca-gatos-api/swagger-ui/index.html


# Getting Started
Antes de iniciar a aplicação será nescessário fazer o start do banco de dados que esta configurado na raiz do projeto
    Rodar o comando "docker-compose up"
    Para instalar as dependencias rodar o comando "gradle clean build"
    Configurar nos parametros da JVM "-Dspring.profiles.active=local"
    Apos isso, executar a classe RacaGatosApplication
    No start, sera executada a carga inicial dos dados utilizados nas consultas de Raças

Aplicação estará dispovivel na porta 8080
Para verificar se aplição iniciou corretamente check o localhost:9000/actuator/health

### Links e documentações:
Documentações oficiais dos frameworks que foram ultilizados na cnsutrução desta aplicação

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.0-SNAPSHOT/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.0-SNAPSHOT/gradle-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.6.4/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.6.4/reference/htmlsingle/#production-ready)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
