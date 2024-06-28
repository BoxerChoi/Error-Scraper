# Error-Scraper specification

## 1. About Error-Scraper

The best of the best log tracking assistant that provides tailing and alert mailing for your log files.  
This program also supports the functionality of searching for specific keywords within a large number of log files.

## 2. Development environment

- IDE : Visual Studio Code
- Git Tools : Git bash
- JDK : Open JDK-17.0.2
- Spring Boot : 3.3.1
- Project : Gradle

## 3. IDE Extension Program(VS Code)

Installed Extesion program on VSCode  
Spring Boot Extension Pack  
Extension Pack for Java  
Debugger for Java  
Spring boot Dashboard  
Language Support for Java(TM) by Red Hat  
Project manager for java  
Test Runner for Java  
IntelliCode  
Lombok Annotations Support for VS Code  
Java Language Support  
Gradle for Java  
Java Snippets  
Getter and Setter Generator

## 4. How to use

- Download below executable files  
  https://github.com/BoxerChoi/Error-Scraper/blob/main/executableJar/Error-Scarper-0.0.1.jar  
  https://github.com/BoxerChoi/Error-Scraper/blob/main/executableJar/mail.application.properties
- Fill out your smtp mail server information from mail.application.properties  
  spring.mail.host= #smtp.gmail.com(gmail example)  
  spring.mail.port= #587  
  spring.mail.username= #{your_mail_address}@gmail.com  
  spring.mail.password= #{app_password}
- Run with below comman line  
  java -jar Error-Scarper-0.0.1.jar --spring.config.additional-location=file:./mail.application.properties
- Type address : "localhost:8080" on your web browser

## 5. Spring Boot initializer URL

https://start.spring.io/
