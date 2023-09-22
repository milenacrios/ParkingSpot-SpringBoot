# Parking Spot-Control

Esse projeto trata-se de uma API construída em Java com Spring Boot, abordando a arquitetura MVC com as definições das entidades, controllers com os end-points para cada verbo HTTP; services e custom validators para o método POST. O contexto da aplicação é o controle de um estacionamento de automóveis de um dado condomínio e por isso, a entidade do banco de dados em Postgress é composta dos seguintes atributos:

 - Id
 - ParkingSpotNumber - Número da vaga no estacionamento
 - LicensePlateCar - Número da placa do automóvel que ocupa a vaga
 - BrandCar - Marca do automóvel
 - ModelCar - Modelo do automóvel
 - ColorCar - Cor do automóvel
 - ResponsibleName - Nome do Dono do automóvel
 - Apartment - Número do apartamento (cada apartamento possui 1 única vaga)
 - Block - Número do bloco do condominio caso tiver
 
 Criei 3 anotações personalizadas para validar a entrada da LicensePlateCar, uma vez que ela tem que ser única; para ParkingSpotNumber, uma vez que números da vagas também são únicos e Apartment, que só pode possuir uma vaga. 
 
 ## Dependências Necessárias
 As seguintes dependências precisam ser adicionadas no arquivo pom.xml. 
 
 
 1: Spring Data JPA

    <dependency>
	    <groupId>org.springframework.boot</groupId>
	    <artifactId>spring-boot-starter-data-jpa</artifactId>
	</dependency>
 2: Validation
 

    <dependency>  
	     <groupId>org.springframework.boot</groupId>  
	     <artifactId>spring-boot-starter-validation</artifactId>  
    </dependency>

 
3: Spring Web

    <dependency>  
	     <groupId>org.springframework.boot</groupId>  
	     <artifactId>spring-boot-starter-web</artifactId>  
    </dependency>

  4: PostgreSQL Driver
  

    <dependency>  
	     <groupId>org.postgresql</groupId>  
	     <artifactId>postgresql</artifactId>  
	     <scope>runtime</scope>  
    </dependency>
5: Lombock

    <dependency>  
	     <groupId>org.projectlombok</groupId>  
	     <artifactId>lombok</artifactId>  
	     <optional>true</optional>  
    </dependency>
	
	
   ## Criação do Banco
   

 - Criar um banco de dados com o nome **parking-control-db**

  ## Execução e Testes
 - Utilização do Postman ou Insomnia para realizar as requisições HTTP.

