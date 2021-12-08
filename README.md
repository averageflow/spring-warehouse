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
* [Credits](#credits)

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

### Running for development

IntelliJ IDEA is recommended for this project. Gradle will need to be installed.

### Additional information

This application provides several endpoints for "headless" usage (without frontend). Thus if we want to create new
products / articles via an HTTP request with JSON body we use the normal endpoint.

Products are composed of 0 or more articles. Products that are composed of articles can be sold only if they are in
stock. Products that are not composed of any article can always be sold. This is in order to take into account that the
product is of "infinite stock".

A list of transactions performed (sales) that have occurred can be obtained via the API and with the frontend.

You can view the API specification by using
Postman, [see the collection here](https://www.postman.com/research-technologist-33289382/workspace/joe-s-development/collection/18682350-5647921b-838a-4471-a960-1a557b01ce39)
and learn how to use the application endpoints.

Bear in mind if you want to add new products (products.json files), the articles which compose the product should
obviously already be present in the database (using inventory.json files).

This application includes a graceful shutdown mechanics and so whenever you stop it, or it receives a stop signal, it
will first wait for any HTTP request currently being processed to be finished and then gracefully shutdown. This makes
it possible to deploy it without downtime and to ensure a better experience for users.

A simple pagination system was added to the GET calls and works by using URL parameters, The default pagination limit if
not specified is 100 items. The default pagination limit for the frontend is 500 items.

The code has been written in an attempt to achieve as clean code as possible, with dependency injection of key
components and with simplicity in mind, with no global state.

## Unit tests

You can run the unit tests for this project if you have Java 17 and Gradle installed, by at the root of the project
executing:

The unit tests will also be run every time the Docker image is rebuilt.

## Possible Improvements

Some compromises were made during development to simplify certain aspects and make the project quicker to develop. Find
some suggestions for improvements below. When better defined, these should be turned into GitHub issues to better keep
track of the progress and create separate branches for the features.

* The API could have been designed to use UUIDs instead of numeric IDs since this provides several advantages, specially
  when clustering. It seemed to complicate things greatly though because the provided files contained numeric IDs, and
  then we would need to write all sorts of lookup functions, so this was deemed as out of scope for the project. The
  addition of UUIDs would not be too difficult though and would prove useful on a large scale system.
* The docker compose file contains "secrets" which for a production-ready application is not great. Either the file
  should be encrypted in a certain fashion or the secrets should be obtained from a Vault (Hashicorp Vault comes to
  mind).
* Authorization Bearer token is for now hardcoded into the application. Ideally a mechanism to generate and securely
  store API bearer tokens would be implemented, and in the middleware we would check if the provided token is valid
  one (perhaps also even including a different permission set per token). The current implementation already somehow
  secures the application and showcases a HTTP handler middleware.
* The addition of PATCH endpoints to modify some resources would be useful, then we could for example rename products
  and articles.
* Distributed tracing would be a good addition to the application specially if it were to communicate with more services
  in its operations. Personal choice would be [Jaeger](https://www.jaegertracing.io/).
* Some structured logging on errors would be a good addition, also in combination to adding the logs into the spans for
  tracing.

### Credits
