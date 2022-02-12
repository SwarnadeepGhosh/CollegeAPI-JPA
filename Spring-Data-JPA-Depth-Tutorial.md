# Spring Data JPA - Depth Tutorial

[TOC]

## Setup and Prerequisite

```properties
Project - Maven project
Language - Java
Spring Boot- 2.6.3

Project Metadata
Group - com.swarna
Artifact - CollegeAPI-JPA
Name - CollegeAPI-JPA
Description - A complete API for college which demonstrates in depth practive of Spring Data JPA
Package name - com.swarna.collegeapi

Packaging - war
Java - 8

Dependencies
- MySQL Driver 
- Spring Web
- Lombok
- Spring Data JPA
```



I have initialised mySQL Database and connected Spring Project with it.

***application.properties***

```properties
spring.jpa.hibernate.ddl-auto:update
spring.datasource.url=jdbc:mysql://sql6.freesqldatabase.com:3306/sql6472211
spring.datasource.username=sql6472211
spring.datasource.password=xxxxxxxxx (Hidden for security purpose)
#spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
#spring.jpa.database-platform=org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.show-sql: true
```



Database ER Diagram

![JPA_inDepth-ER-DIagram](E:\My-Projects\CollegeAPI-JPA\JPA_inDepth-ER-DIagram.jpg)

