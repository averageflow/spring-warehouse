plugins {
    idea
    `java-library`
    jacoco
    eclipse
    // checkstyle
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.springframework.boot") version ("2.6.2")
    id("com.google.cloud.tools.jib") version "3.1.4"
}

buildscript {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://plugins.gradle.org/m2/")
    }
}

defaultTasks("clean", "build", "jacocoTestCoverageVerification")

val baseDockerImage: String = "eclipse-temurin:17.0.1_12-jre-alpine"
val projectVersion: String = "1.0.0"
val projectGroup: String = "nl.averageflow.springwarehouse"

object Versions {
    const val atpSchema = "0.0.2-RC10"
    const val checkstyle = "8.42"
    const val hamcrest = "2.2"
    const val mockito = "3.12.4"
    const val jacoco = "0.8.7"
    const val junit5 = "5.7.2"
    const val logback = "1.2.3"
    const val hdrHistogram = "2.1.12"
    const val protoc = "3.19.1"
    const val javaxAnnotation = "1.3.2"
    const val grpc = "1.33.0"
    const val springCloudGcp = "2.0.4"
    const val springBootVersion = "2.6.2"
    const val testcontainers = "1.16.0"
    const val jackson = "2.12.2"
    const val lombok = "1.18.12"
    const val springIntegrationCore = "5.5.3"
    const val jodaTime = "2.10.10"
}

tasks {
    named("jar") {
        enabled = true
    }
    named("bootJar") {
        enabled = false
    }
    named("jibDockerBuild") {
        enabled = false
    }
    named("jib") {
        enabled = false
    }
}

tasks.register<Copy>("copyTestLogs") {
    from(".")
    include("**/build/test-output/**")
    include("**/*.log")
    exclude("build")
    into("build/test_logs")
    includeEmptyDirs = false
}

allprojects {
    group = projectGroup
    version = projectVersion

    repositories {
        maven("https://plugins.gradle.org/m2/")
        mavenCentral()
        mavenLocal()
    }
}

subprojects {
    apply(plugin = "idea")
    apply(plugin = "java-library")
    apply(plugin = "eclipse")
    // apply(plugin = "checkstyle")
    apply(plugin = "jacoco")
    apply(plugin = "io.spring.dependency-management")

    configure<io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension> {
        imports {
            mavenBom("org.springframework.boot:spring-boot-starter-parent:${Versions.springBootVersion}")
        }
    }

    dependencies {
        testImplementation("org.hamcrest", "hamcrest", Versions.hamcrest)
        testImplementation("org.mockito", "mockito-junit-jupiter", Versions.mockito)
        testImplementation("org.junit.jupiter", "junit-jupiter-api", Versions.junit5)
        testImplementation("org.junit.jupiter", "junit-jupiter-params", Versions.junit5)
        testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", Versions.junit5)
    }

//    checkstyle {
//        sourceSets = singletonList(project.sourceSets.main.get())
//        toolVersion = Versions.checkstyle
//    }

    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    jacoco {
        toolVersion = Versions.jacoco
    }

    @Suppress("UnstableApiUsage")
    val jacocoAggregateReport by tasks.creating(JacocoReport::class) {
        group = LifecycleBasePlugin.VERIFICATION_GROUP
        executionData(project.buildDir.absolutePath + "/jacoco/test.exec")
        reports {
            xml.required.set(true)
        }
        additionalClassDirs(files(subprojects.flatMap { project ->
            listOf("java", "kotlin").map { project.buildDir.path + "/classes/$it/main" }
        }))
        additionalSourceDirs(files(subprojects.flatMap { project ->
            listOf("java", "kotlin").map { project.file("src/main/$it").absolutePath }
        }))

        dependsOn(tasks.test)
    }

    tasks {
        jar {
            enabled = true
        }

        test {
            useJUnitPlatform()
            testLogging {
                events("passed", "skipped", "failed")
            }
            testLogging {
                showStandardStreams = true
                exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
            }
            systemProperties(
                mapOf(
                    // ADD COMMON SYSTEM PROPERTIES FOR TESTS HERE
                    "exampleProperty" to "exampleValue"
                )
            )
            reports.html.required.set(false) // Disable individual test reports
            finalizedBy("jacocoTestReport")
        }

        jacocoTestReport {
            reports {
                xml.required.set(true)
            }
        }

        jacocoTestCoverageVerification {
            executionData.setFrom(jacocoAggregateReport.executionData)
            violationRules {
                rule {
                    limit {
                        counter = "INSTRUCTION"
                        minimum = "0.3".toBigDecimal()
                    }
                }
                rule {
                    limit {
                        counter = "BRANCH"
                        minimum = "0.1".toBigDecimal()
                    }
                }
            }
            dependsOn(jacocoAggregateReport, test)
        }

        javadoc {
            title = "<h1>${project.name}</h1>"
        }

        compileJava {
            options.encoding = "UTF-8"
            options.isDeprecation = true
        }

        compileTestJava {
            options.encoding = "UTF-8"
            options.isDeprecation = true
        }

        check {
            finalizedBy(jacocoTestCoverageVerification)
        }
    }
}

project(":app") {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "com.google.cloud.tools.jib")

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

    tasks {
        bootJar {
            enabled = true
        }
        test {
            useJUnitPlatform()
            testLogging {
                events("passed", "skipped", "failed")
            }
            testLogging {
                showStandardStreams = true
                exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
            }
            systemProperties(
                mapOf(
                    // ADD COMMON SYSTEM PROPERTIES FOR TESTS HERE
                    "exampleProperty" to "exampleValue"
                )
            )
            reports.html.required.set(false) // Disable individual test reports

            finalizedBy("jacocoTestReport", "jacocoTestCoverageVerification")
        }
    }

    jib {
        from {
            image = baseDockerImage
        }
        to {
            image = "gcr.io/spring-warehouse/app"
            credHelper = "gcr"
        }
        container{
            ports = mutableListOf("8080")
        }
    }
}
