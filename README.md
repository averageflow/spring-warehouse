# Spring Warehouse

![Java](https://shields.io/badge/Java-17-red?logo=java&style=flat) ![Spring Boot](https://shields.io/badge/Spring%20Boot-2-green?logo=spring&style=flat) ![PostgreSQL](https://shields.io/badge/-PostgreSQL-gray?logo=postgresql&style=flat) ![Docker JIB](https://shields.io/badge/-Docker%20JIB-gray?logo=docker&style=flat) [![Codacy Badge](https://app.codacy.com/project/badge/Grade/0696080f9fa14ab3b7334cb1fc40b76e)](https://www.codacy.com/gh/averageflow/spring-warehouse/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=averageflow/spring-warehouse&amp;utm_campaign=Badge_Grade) [![Codacy Badge](https://app.codacy.com/project/badge/Coverage/0696080f9fa14ab3b7334cb1fc40b76e)](https://www.codacy.com/gh/averageflow/spring-warehouse/dashboard?utm_source=github.com&utm_medium=referral&utm_content=averageflow/spring-warehouse&utm_campaign=Badge_Coverage) ![GPL licensed](https://img.shields.io/badge/license-GPL-blue.svg)

Spring Warehouse is a Java Spring Boot REST API that has the purpose of managing products and articles in your
warehouse.

This project has been created in an effort to provide an up-to-date example of using the best practices of Java 17 and
Spring Boot with Data JPA and Liquibase and being a reference project, similar to Spring Pet Clinic.

You will also find in this repository a `docker-compose.yaml` which will
set up a stack in your machine with PostgreSQL. This project has been
developed with horizontal scalability in mind.

Take a moment to
read [the contributing guidelines](https://github.com/averageflow/spring-warehouse/blob/master/CONTRIBUTING.md) and
participate in this project in technical and non-technical ways.

Feel free to join [the Discord server for Spring Warehouse here](https://discord.gg/eTKDeTDC3j) for any ideas, support request, or for a friendly chat.

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
* [Deploying](#deploying)

<!--suppress HtmlDeprecatedAttribute -->
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

## Running the application's dependencies

You should be running on a machine with Docker installed. Clone the project, or download the zip file with the source code
from [the releases' page](https://github.com/averageflow/spring-warehouse/releases).

In the root folder of the project, run `docker-compose up -d` for starting a database with daemon behaviour (running in the background and even started at system boot) for your database. This should set you up with a PostgreSQL instance running on your machine, configured with the default credentials.

The default credentials are: database `warehouse_db`, user `warehouse_user`, password `warehouse_user_password`.

**IMPORTANT** make sure that port 5432 of the machine is available and there are no other DB instances using
it.


### Running for development

IntelliJ IDEA is recommended for this project. This IDE will automatically detect it's a Spring application and provide you with a runnable configuration. For more options see all the available Gradle tasks of this project with `./gradlew tasks`.

You can start it with `./gradlew bootRun`.

This project uses the [jib](https://github.com/GoogleContainerTools/jib) tool to build deployable images and docker images too. This makes it easy to deploy it to the Cloud and to build prepared images.

The application requires 3 environment variables to be present when running. There are several ways to go about this and you should choose what suits your workflow better. 

My default approach is for local development, create a file called `.env` at the root of the project with the content:
```sh
SPRING_DATASOURCE_URL="jdbc:postgresql://localhost:5432/warehouse_db"
SPRING_DATASOURCE_USERNAME="warehouse_user"
SPRING_DATASOURCE_PASSWORD="warehouse_user_password"
```

then load the variables with `source .env`. If you run the project via IntelliJ make sure to add the `.env` file in the run configuration by checking `Enable env file` and then adding a valid `.env` file.

This approach using environment variables makes it easier to deploy this to the cloud / other environments since we control the database connection / other variables from the running environment.

If you would like to create and run a Docker image of Spring Warehouse locally run:
```sh
./gradlew jibDockerBuild --image=<wanted name for the image>
docker run -it <wanted name for the image>
```

For publishing to the Google Cloud Registry it is as simple as `./gradlew jib`. This step could easily be automated and integrated into the CI / CD pipeline with GitHub Actions.

### Domain information

Products are composed of 0 or more articles. Products that are composed of articles can be sold only if they are in
stock. Products that are not composed of any article can always be sold. This is in order to take into account that the
product is of "infinite stock".

A list of transactions performed (sales) that have occurred can be obtained via the API.

Bear in mind if you want to add new products, the articles which compose the product and the category to which the
product belongs to should obviously already be present in the database.

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

Due to Spring Warehouse being a REST API this means we can build all kinds of front-ends around it, whether web, native,
mobile, etc. This also serves the purpose of allowing developing our skills further and exploring more technologies, due
to the de-coupled nature.

Find some honorable mentions below:

* [Spring Warehouse Frontend (React)](https://github.com/averageflow/spring-warehouse-frontend)

## Deploying

For learning purposes I created a possible [infrastructure running fully on Google Cloud, designed with Terraform](https://github.com/averageflow/spring-warehouse-cloud-infra).
This will setup a Google Kubernetes engine cluster and a PostgreSQL Cloud SQL instance too.
We can then learn further on how to deploy a Java application into the cluster.
