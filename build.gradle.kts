val baseDockerImage: String = "eclipse-temurin:17.0.1_12-jre-alpine"
val projectVersion: String = "1.0.3"
val projectGroup: String = "nl.averageflow.springwarehouse"

object Versions {
    const val checkstyle = "8.42"
    const val jacoco = "0.8.7"
    const val junit5 = "5.8.2"
    const val springBootVersion = "2.6.3"
    const val jibVersion = "3.2.0"
    const val jaxbVersion = "2.3.2"
    const val liquibaseVersion = "4.7.1"
    const val springDocOpenAPIVersion = "1.6.3"
    const val postgresqlVersion = "42.3.1"
    const val h2Version = "1.4.200"
    const val springSecurityTestVersion = "5.6.1"
    const val javaFakerVersion = "1.0.2"
}

plugins {
    idea
    `java-library`
    jacoco
    eclipse
    // checkstyle
    id("org.springframework.boot") version ("2.6.3")
    id("com.google.cloud.tools.jib") version ("3.2.0")
}

buildscript {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://plugins.gradle.org/m2/")
    }
}

defaultTasks("clean", "build", "jacocoTestCoverageVerification")

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
    named("bootRun") {
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
        mavenCentral()
        gradlePluginPortal()
        maven("https://plugins.gradle.org/m2/")
    }
}

subprojects {
    apply(plugin = "idea")
    apply(plugin = "java-library")
    apply(plugin = "eclipse")
    apply(plugin = "jacoco")
    // apply(plugin = "checkstyle")

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
}

project(":app") {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "com.google.cloud.tools.jib")

    dependencies {
        implementation("org.glassfish.jaxb","jaxb-runtime", Versions.jaxbVersion)
        implementation("org.liquibase","liquibase-core", Versions.liquibaseVersion)
        implementation("org.springdoc","springdoc-openapi-ui", Versions.springDocOpenAPIVersion)
        implementation("org.springframework.boot", "spring-boot-starter-data-jpa", Versions.springBootVersion)
        implementation("org.springframework.boot", "spring-boot-starter-json", Versions.springBootVersion)
        implementation("org.springframework.boot", "spring-boot-starter-web", Versions.springBootVersion)
        implementation("org.springframework.boot", "spring-boot-starter-validation", Versions.springBootVersion)
        implementation("org.springframework.boot", "spring-boot-starter-webflux", Versions.springBootVersion)
        implementation("org.springframework.boot", "spring-boot-starter-security", Versions.springBootVersion)
        implementation("org.springframework.boot", "spring-boot-starter-actuator", Versions.springBootVersion)
        implementation("org.springframework.boot","spring-boot-starter-mail", Versions.springBootVersion)

        runtimeOnly("org.springframework.boot", "spring-boot-devtools", Versions.springBootVersion)
        runtimeOnly("org.postgresql","postgresql", Versions.postgresqlVersion)

        testImplementation("org.springframework.boot", "spring-boot-starter-test", Versions.springBootVersion)
        testImplementation("com.h2database","h2", Versions.h2Version)
        testImplementation("org.springframework.security","spring-security-test", Versions.springSecurityTestVersion)
        testImplementation("com.github.javafaker","javafaker", Versions.javaFakerVersion)
    }

    tasks {
        jar {
            enabled = true
        }

        bootJar {
            enabled = true
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
