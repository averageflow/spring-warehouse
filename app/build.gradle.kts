import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
    application
    java
    war
    id("org.springframework.boot") version ("2.6.2")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
}


version = "1.0.0"
group = "nl.averageflow.springwarehouse"
var applicationMainClass = "nl.averageflow.springwarehouse.SpringWarehouseApplication"

repositories {
    mavenCentral()
}

dependencies {
    implementation("jakarta.xml.bind:jakarta.xml.bind-api:2.3.2")
    implementation("org.glassfish.jaxb:jaxb-runtime:2.3.2")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.6.2")
    implementation("org.springframework.boot:spring-boot-starter-web:2.6.2")
    implementation("org.springframework.boot:spring-boot-starter-validation:2.6.2")
    implementation("org.springframework.boot:spring-boot-starter-security:2.6.2")
    implementation("org.liquibase:liquibase-core:4.6.2")
    implementation("org.springdoc:springdoc-openapi-ui:1.6.3")
    runtimeOnly("org.springframework.boot:spring-boot-devtools:2.6.2")
    runtimeOnly("org.postgresql:postgresql:42.3.1")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.6.2")
    testImplementation("junit:junit:4.13.2")
    testImplementation("com.h2database:h2:1.4.200")
    testImplementation("org.springframework.security:spring-security-test:5.6.1")

}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

tasks.getByName<BootRun>("bootRun") {
    main = applicationMainClass
    sourceResources(sourceSets["main"])
}

application {
    mainClass.set(applicationMainClass)
}