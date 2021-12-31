# Spring Warehouse

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white) ![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white) ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)

![GPL licensed](https://img.shields.io/badge/license-GPL-blue.svg) [![Codacy Badge](https://app.codacy.com/project/badge/Grade/0696080f9fa14ab3b7334cb1fc40b76e)](https://www.codacy.com/gh/averageflow/spring-warehouse/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=averageflow/spring-warehouse&amp;utm_campaign=Badge_Grade)

Spring Warehouse is a Java Spring Boot REST API that has the purpose of managing products and articles in your
warehouse.

This project has been created in an effort to provide an up-to-date example of using the best practices of Java 17 and
Spring Boot with Data JPA and Liquibase and being a reference project, similar to Spring Pet Clinic.

Take a moment to
read [the contributing guidelines](https://github.com/averageflow/spring-warehouse/blob/master/CONTRIBUTING.md) and participate in this project in technical and non-technical ways.

Feel free to join [the Discord server for Spring Warehouse here](https://discord.gg/eTKDeTDC3j).

## Table of contents

* [Technologies used](#technologies-used)
* [Documentation](#documentation)
* [Functionalities](#functionalities)
* [Running the application](#running-the-application)
* [Running for development](#running-for-development)
* [Domain information](#domain-information)
* [Unit tests](#unit-tests)
* [Authentication and Authorization](#authentication-and-authorization)
* [Possible Improvements](#possible-improvements)
* [Front-ends](#front-ends)

<p align="center">
  <img src="https://res.cloudinary.com/dehs6irlh/image/upload/c_scale,w_256/v1640818163/Github/Warehouse-PNG-Photos_jcaebq.png" alt="Spring Warehouse logo"/>
</p>

### Technologies used

This project was built using:

* [Java 17](https://docs.oracle.com/en/java/javase/17/index.html)
* [Spring Boot Framework](https://spring.io/projects/spring-boot)
* [Gradle](https://gradle.org/)
* [PostgreSQL database](https://www.postgresql.org/)
* [Docker](https://www.docker.com/)
* [Liquibase](https://www.liquibase.org/)

## Documentation

To view the API Swagger documentation, start the application and see:

* [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
* [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

You can view the API specification by using
Postman, [see the collection here](https://www.postman.com/research-technologist-33289382/workspace/joe-s-development/collection/18682350-5647921b-838a-4471-a960-1a557b01ce39)
and learn how to use the application endpoints.

## Functionalities

In summary the application can:

**GET**

* Retrieve a paginated list of products
* Retrieve a product by UUID
* Retrieve a paginated list of articles
* Retrieve an article by UUID
* Retrieve a paginated list of transactions
* Retrieve a transaction by UUID
* Retrieve a paginated list of categories
* Retrieve a category by UUID
* Retrieve a paginated list of users
* Retrieve a user by UUID

**POST**

* Add articles
* Add categories
* Add products
* Login and obtain session cookie
* Register as READ_ONLY user (admins should be created by editing the DB manually)

**PATCH**

* Edit product
* Edit article
* Edit category
* Sell products (reserve stock if enough stock is present) or product is unlimited stock like software, this will log
  every sale of products (transactions)

**DELETE**

* Delete product by UUID
* Delete article by UUID
* Delete category by UUID

## Running the application

To kickstart the application and all dependencies required for its operation, you should be running on a machine with
Docker installed. Clone the project, or download the zip file with the source code
from [the releases' page](https://github.com/averageflow/spring-warehouse/releases). Then all you need is to
run `docker-compose up`, optionally `docker-compose up -d` for daemon behaviour.

### Running for development

IntelliJ IDEA is recommended for this project. Launch configurations for this project are available in the `.run`
folder. Gradle will need to be installed. You can start it with `./gradlew bootRun`.

### Domain information

Products are composed of 0 or more articles. Products that are composed of articles can be sold only if they are in
stock. Products that are not composed of any article can always be sold. This is in order to take into account that the
product is of "infinite stock".

A list of transactions performed (sales) that have occurred can be obtained via the API.

Bear in mind if you want to add new products, the articles which compose the product should obviously already be present
in the database.

This application includes a graceful shutdown mechanics and so whenever you stop it, or it receives a stop signal, it
will first wait for any HTTP request currently being processed to be finished and then gracefully shutdown. This makes
it possible to deploy it without downtime and to ensure a better experience for users.

The code has been written in an attempt to achieve as clean code as possible, with dependency injection of key
components and with simplicity in mind, with no global state and trying to use functional programming approach where
beneficial.

## Unit tests

You can run the unit tests for this project if you have Java 17 and Gradle installed, by at the root of the project
executing:

```sh
./gradlew test
```

The unit tests will also be run every time the Docker image is rebuilt.

## Authentication and Authorization

This REST API is only accessible for authenticated users. Authentication is done by means of an HTTP cookie. You can
register users via the API, and posteriorly login also via the API. This will create `READ_ONLY` users, which are only
allowed to perform GET calls to the API. If you want to create an `ADMIN` account you will need to modify a database
record for a user and turn him into admin. That user will have then permission to perform all CRUD operations on all
resources, and even create / edit users of all kinds.

## Possible Improvements

Some compromises were made during development to simplify certain aspects and make the project quicker to develop. Find
some suggestions for improvements below. When better defined, these should be turned into GitHub issues to better keep
track of the progress and create separate branches for the features.

* The docker compose file contains "secrets" which for a production-ready application is not great. Either the file
  should be encrypted in a certain fashion or the secrets should be obtained from a Vault (Hashicorp Vault comes to
  mind).
* Distributed tracing would be a good addition to the application specially if it were to communicate with more services
  in its operations. Personal choice would be [Jaeger](https://www.jaegertracing.io/).


## Front-ends

Due to Spring Warehouse being a REST API this means we can build all kinds of front-ends around it, whether web, native, mobile, etc. This also serves the purpose of allowing developing our skills further and exploring more technologies, due to the de-coupled nature. 

Find some honorable mentions below: 

* [Spring Warehouse Frontend (React)](https://github.com/averageflow/spring-warehouse-frontend)
