# Spring Warehouse

Spring Warehouse is a Java Spring Boot application that has the purpose of managing products and articles in
your warehouse.

## Table of contents

* [Functionalities](#functionalities)
* [Running the application](#running-the-application)
* [Running for development](#running-for-development)
* [Additional information](#additional-information)
* [Technologies used](#technologies-used)
* [Unit tests](#unit-tests)
* [Possible Improvements](#possible-improvements)

## Functionalities

In summary the application can:

* Create new products (from JSON request or from file upload)
* Create new articles (from JSON request or from file upload)
* Retrieve list of products present in warehouse (with related articles)
* Retrieve list of articles present in warehouse
* Sell products if stock is enough, or product is unlimited stock like software (from JSON request or from form)
* Log every sale of products (transactions)
* Retrieve list of transactions
* Delete products from the warehouse
* Delete articles from the warehouse
* Edit products from the warehouse
* Edit articles from the warehouse

You can view the API specification by using Postman, [see the collection here](https://www.postman.com/research-technologist-33289382/workspace/joe-s-development/collection/18682350-5647921b-838a-4471-a960-1a557b01ce39)
and learn how to use the application endpoints.


### Technologies used

This project was built using:

* Java 17
* Spring Boot Framework
* Gradle
* [PostgreSQL database](https://www.postgresql.org/)
* [Docker](https://www.docker.com/)

## Running the application

To kickstart the application and all dependencies required for its operation, you should be running on a machine with
Docker installed. Clone the project, or download the zip file with the source code
from [the releases page](https://github.com/averageflow/spring-warehouse/releases) page.
Then all you need is to run `docker-compose up`, optionally `docker-compose up -d` for daemon behaviour.

### Running for development

IntelliJ IDEA is recommended for this project. Launch configurations for this project are available in the `.run` folder. Gradle will need to be installed. You can start it with `./gradlew run`.

### Domain information

Products are composed of 0 or more articles. Products that are composed of articles can be sold only if they are in
stock. Products that are not composed of any article can always be sold. This is in order to take into account that the
product is of "infinite stock".

A list of transactions performed (sales) that have occurred can be obtained via the API.

Bear in mind if you want to add new products, the articles which compose the product should obviously already be present in the database.

This application includes a graceful shutdown mechanics and so whenever you stop it, or it receives a stop signal, it
will first wait for any HTTP request currently being processed to be finished and then gracefully shutdown. This makes
it possible to deploy it without downtime and to ensure a better experience for users.

The code has been written in an attempt to achieve as clean code as possible, with dependency injection of key
components and with simplicity in mind, with no global state and trying to use functional programming approach where beneficial.

## Unit tests

You can run the unit tests for this project if you have Java 17 and Gradle installed, by at the root of the project
executing:
```sh
./gradlew test
```

The unit tests will also be run every time the Docker image is rebuilt.

## Possible Improvements

Some compromises were made during development to simplify certain aspects and make the project quicker to develop. Find
some suggestions for improvements below. When better defined, these should be turned into GitHub issues to better keep
track of the progress and create separate branches for the features.

* The docker compose file contains "secrets" which for a production-ready application is not great. Either the file
  should be encrypted in a certain fashion or the secrets should be obtained from a Vault (Hashicorp Vault comes to
  mind).
* The API should be secured. Refer to [#6](https://github.com/averageflow/spring-warehouse/issues/6).
* Distributed tracing would be a good addition to the application specially if it were to communicate with more services
  in its operations. Personal choice would be [Jaeger](https://www.jaegertracing.io/).

