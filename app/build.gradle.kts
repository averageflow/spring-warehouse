import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
    idea
    jacoco
//    checkstyle
    eclipse
    application
    java
    war
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.springframework.boot") version ("2.6.2")
    id("com.google.cloud.tools.jib") version "3.1.1"
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

val baseDockerImage: String = "openjdk:17"

version = "1.0.0"
group = "nl.averageflow.springwarehouse"
var applicationMainClass = "nl.averageflow.springwarehouse.SpringWarehouseApplication"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.glassfish.jaxb:jaxb-runtime:2.3.2")
    implementation("org.liquibase:liquibase-core:4.6.2")
    implementation("org.springdoc:springdoc-openapi-ui:1.6.3")
    implementation("org.springframework.boot", "spring-boot-starter-data-jpa")
    implementation("org.springframework.boot", "spring-boot-starter-json")
    implementation("org.springframework.boot", "spring-boot-starter-web")
    implementation("org.springframework.boot", "spring-boot-starter-validation")
    implementation("org.springframework.boot", "spring-boot-starter-webflux")
    implementation("org.springframework.boot", "spring-boot-starter-security")
    implementation("org.springframework.boot", "spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-mail")

    runtimeOnly("org.springframework.boot:spring-boot-devtools:2.6.2")
    runtimeOnly("org.postgresql:postgresql:42.3.1")

    testImplementation("org.springframework.boot:spring-boot-starter-test:2.6.2")
    testImplementation("junit:junit:4.13.2")
    testImplementation("com.h2database:h2:1.4.200")
    testImplementation("org.springframework.security:spring-security-test:5.6.1")
    testImplementation("com.github.javafaker:javafaker:1.0.2")

}

tasks.named<Test>("test") {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
    finalizedBy("jacocoTestReport")
}


tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
    }
}

tasks.getByName<BootRun>("bootRun") {
    main = applicationMainClass
    sourceResources(sourceSets["main"])
}

jib {
    from {
        image = baseDockerImage
    }
    to {
        image = "gcr.io/spring-warehouse/app"
        credHelper = "gcr"
    }
}

application {
    mainClass.set(applicationMainClass)
}
