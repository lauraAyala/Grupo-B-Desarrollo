import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
	war
	id("org.springframework.boot") version "2.7.15"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("plugin.jpa") version "1.6.21"
	id ("org.sonarqube") version "4.3.1.3277"


}

sonar {
	properties {
		property("sonar.projectKey", "lauraAyala_Grupo-B-Desarrollo")
		property ("sonar.organization", "lauraayala")
		property ("sonar.host.url", "https://sonarcloud.io")
	}
}


group = "ar.edu.unq.desapp.grupoB"
version = "0.0.1-SNAPSHOT"



java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("io.springfox:springfox-boot-starter:3.0.0")
	//implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.h2database:h2")
	implementation ("org.springframework.boot:spring-boot-starter-cache:2.4.3")
	implementation ("org.springframework.boot:spring-boot-starter-data-redis:2.4.3")
	implementation ("javax.cache:cache-api:1.1.1")
	implementation ("org.ehcache:ehcache:3.8.1")
	implementation("org.projectlombok:lombok:1.18.22")
	//providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
	//testImplementation("org.springframework.boot:spring-boot-starter-test")
	//testImplementation("org.springframework.security:spring-security-test")
	runtimeOnly("org.postgresql:postgresql")
	implementation("org.jscience:jscience:4.3.1")
	testImplementation ("com.tngtech.archunit:archunit-junit5:0.14.1")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
		testImplementation("junit:junit:4.12")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
