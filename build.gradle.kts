plugins {
    java
    id("org.springframework.boot") version "4.0.5"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "ca.tetervak"
version = "0.0.1-SNAPSHOT"
description = "DiceGame"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

// Add mockito-core to the mockitoAgent configuration
val mockitoAgent by configurations.creating {
    isTransitive = false
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-webmvc")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-thymeleaf-test")
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    // Add the mockito-core to the mockitoAgent configuration
    mockitoAgent("org.mockito:mockito-core")
}

tasks.withType<Test> {
    useJUnitPlatform()
    // Add the javaagent JVM argument
    jvmArgs("-javaagent:${mockitoAgent.asPath}")
}
