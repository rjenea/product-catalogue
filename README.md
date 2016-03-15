[![Build Status](https://travis-ci.org/ecararus/product-catalogue.svg?branch=master)](https://travis-ci.org/ecararus/product-catalogue)
# About:
Product catalogue is self contained application is running on Apache Tomcat/8.0.30 as data store is used mongodb.
[Entire specification is available in project root folder](https://github.com/ecararus/product-catalogue/blob/master/Product%20Selection%20UT.PDF). 

## System requirements:
 - Java 1.8
 - Maven 3.3.3
 - Free ports: 27017(mongodb), 8080(apache tomcat)

## Deployment and test:
 - Step1: Run in command lien> mvn clean package spring-boot:run
 - Step2: Add cookie: Run in browser console> document.cookie="customerId=123;";
 - Step3: Application: http://localhost:8080/
 - Step4: Check Api documentation: http://localhost:8080/swagger-ui.html
 - Step5: Code coverage:<br/> 
          1.Run > mvn clean verify<br/>
          2.Unit test coverage report product-catalogue/target/site/jacoco-ut/index.html<br/>
          3.Integration test coverage report product-catalogue/target/site/jacoco-it/index.html<br> 
          
## TODO:
 - Finish UI and angular script or ReactJs.
 - Integrate with sales microservice for checkout function and with CustomerLocationService for location lookup.

## Default data set:
Domain model is aggregated in Customer and Product domain objects.

Initial data set is created only for demo purpose. Later when the proper data store will be identified than com.sky.CatalogueAppConfiguration#run should be removed.<br/>
Below is listed default persisted dataset, as well api allow to add more.

Products: <br/>
{ "name" : "Arsenal TV" , "category" : "Sport" , "locationId" : "London"} <br/>
{ "name" : "Chelsea TV" , "category" : "Sport" , "locationId" : "London"} <br/>
{ "name" : "Liverpool TV" , "category" : "Sport" , "locationId" : "Liverpool"} <br/>
{ "name" : "Sky News" , "category" : "News"} <br/>
{ "name" : "Sky Sport News" , "category" : "News"} <br/>

Users and location are mocked so service will be able to identify only:  <br/>
 {"customerId" : "123", "locationId" :  "London"} <br/>
 {"customerId" : "321", "locationId" :  "Liverpool"} <br/>

## Out of scope:
 - Anny eventual consistency issue with CustomerLocationService in case it will be decided to implement as a separate microservice.
 - Anny infrastructural configuration.

## Convention:
 - Test names does not contains "test" prefix and should provide as message the purpose of test(will be easy to read in case it is failing).
 - Test should have coverage > 70.
 - Unit test class will have "Test" postfix.
 - Integration test class will have "IT" postfix.

## DISCLAIMER:
Purpose of project is only educational.
This project should not be used for any commercial purpose.
This work is licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License.
http://creativecommons.org/licenses/by-nc-sa/4.0/.

## Author:
Eugeniu Cararus
cararuseugeniu@gmail.com
