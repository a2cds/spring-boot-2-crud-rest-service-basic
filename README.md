# spring-boot-2-crud-rest-service-basic
Spring Boot 2 CRUD REST Service Basic

Projeto Maven/Spring Boot desenvolvido no Spring Tool Suite™ 3.9.5.RELEASE

Projeto desenvolvido em Java com características RESTful para manutenção de uma tabela com registros de endereços armazenado em um banco de dados relacional in-memory conhecido como H2.

O gerenciamento dependências está sob a responsabilidade do Maven que mantém as bibliotecas necessárias para a aplicação. Essas libs são adicionadas por meio de download delas do repositório oficial da ferramenta, de forma a padronizar a compilação de seu código e seu empacotamento. 

Desenvolvido sob o Spring Boot, que é uma das formas mais simples de se disponibilizar serviços REST numa aplicação RESTful em Java, permitindo interação com aplicativos externos inclusive, pois pode ser acessada através de chamadas no modelo de API interagindo sob requisições HTTP e os dados trafegando no formato JSON. O Spring Boot traz do Spring Framework o uso de um modelo de desenvolvimento que favorece a convenção sobre a configuração, utilizando annotations no código fonte ao invés de arquivos XML, comumente usados nas implementações de referência do Java. A criação do projeto é feita de forma simples bastando informar as dependências necessárias para a aplicação, sendo que nesta aplicação foram configuradas inicialmente: Web, JPA, H2 e DevTools. Para a geração do projeto com suporte a Maven foi usado o Spring Initializr na URL https://start.spring.io/

Para o acesso ao banco de dados in-memory H2, foi utilizado o suporte JPA do Spring Boot, cuja configuração de acesso foi feita por meio de um arquivo .properties e interação com o banco feita por uma interface do tipo Repository usando Spring Data JPA.

Por não ter uma interface web, a manutenção das requisições JSON precisa ser feita por meio de um ADE - API Development Evironment, como por exemplo, o Postman, que pode ser baixado através da URL https://www.getpostman.com

Executá-lo por meio do Run As > Spring Boot App

a) Para listar os endereços no Postman  
GET http://localhost:8080/enderecos

b) Para visualizar um endereço por meio do ID no Postman  
GET http://localhost:8080/enderecos/{id}

c) Para excluir um endereço por meio do ID no Postman  
DELETE http://localhost:8080/enderecos/{id}

d) Para incluir um endereço no Postman  
POST http://localhost:8080/enderecos

e) Para interface de manutençao do banco de dados H2 no browser  
http://localhost:8080/h2-console
