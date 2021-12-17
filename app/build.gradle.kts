import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
    application
    java
    war
    id("org.springframework.boot") version("2.6.1")
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
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.6.1")
    implementation("org.springframework.boot:spring-boot-starter-web:2.6.1")
    implementation( "org.liquibase:liquibase-core:4.6.2")
    runtimeOnly("org.springframework.boot:spring-boot-devtools:2.6.1")
    runtimeOnly("org.postgresql:postgresql:42.3.1")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.6.1")
    testImplementation("junit:junit:4.13.2")
    testImplementation("com.h2database:h2:1.3.148")
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