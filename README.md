# About [Product-catalogue](https://product-catalogue-poc.herokuapp.com/): [![Build Status](https://travis-ci.org/ecararus/product-catalogue.svg?branch=master)](https://travis-ci.org/ecararus/product-catalogue) [![Coverage Status](https://coveralls.io/repos/github/ecararus/product-catalogue/badge.svg?branch=master)](https://coveralls.io/github/ecararus/product-catalogue?branch=master) [![Dependency Status](https://www.versioneye.com/user/projects/56f01b9b35630e003e0a7e4e/badge.svg?style=flat)](https://www.versioneye.com/user/projects/56f01b9b35630e003e0a7e4e) [![Code Climate](https://codeclimate.com/github/ecararus/product-catalogue/badges/gpa.svg)](https://codeclimate.com/github/ecararus/product-catalogue) [ ![Codeship Status for ecararus/product-catalogue](https://codeship.com/projects/9970d200-ce89-0133-ea65-56ac8db24476/status?branch=master)](https://codeship.com/projects/140958)  [![ReviewNinja](https://app.review.ninja/53860556/badge)](https://app.review.ninja/ecararus/product-catalogue) [![Slack Invite Button](https://slack-product-catalogue.herokuapp.com/badge.svg)](https://slack-invite-product-catalogue.herokuapp.com)

Product catalogue is self contained application is running on Apache Tomcat/8.0.30 as data store is used mongodb.
Ui implemented by using bootstrap and angular. Deployment is done throw Codeship on [heroku's cloud infrastructue](https://product-catalogue-poc.herokuapp.com).
[Entire specification is available in project root folder](https://github.com/ecararus/product-catalogue/blob/master/Product%20Selection%20UT.PDF).


## System requirements:
 - Java 1.8
 - Maven 3.3.3
 - Free ports: 27017(mongodb), 8080(apache tomcat)

## Local deployment and testing:
 - Step1: Run in command lien 
 ``` sh
    $ mvn clean package spring-boot:run
 ```
 - Step2: Add cookie run in browser console 
 ``` 
    > document.cookie="customerId=123;";
 ```
 - Step3: Application: http://localhost:8080/
 - Step4: Check Api documentation: http://localhost:8080/swagger-ui.html
 - Step5: Code coverage:<br/> 
          1.Run > mvn clean verify / or visit [Coveralls](https://coveralls.io/github/ecararus/product-catalogue?branch=master)<br/>
          2.Unit test coverage report product-catalogue/target/site/jacoco-ut/index.html<br/>
          3.Integration test coverage report product-catalogue/target/site/jacoco-it/index.html<br> 
          
## To implement:
 - Integrate with sales microservice for checkout function and with CustomerLocationService for location lookup.

## Default data set:
Domain model is aggregated in Customer and Product domain objects.

Initial data set is created only for demo purpose. Later when the proper data store will be identified than com.sky.CatalogueAppConfiguration#run should be removed.<br/>
Below is listed default persisted dataset, as well api allow to add more.

```
Products:
{ "name" : "Arsenal TV" , "category" : "Sport" , "locationId" : "London"} <br/>
{ "name" : "Chelsea TV" , "category" : "Sport" , "locationId" : "London"} <br/>
{ "name" : "Liverpool TV" , "category" : "Sport" , "locationId" : "Liverpool"} <br/>
{ "name" : "Sky News" , "category" : "News"} <br/>
{ "name" : "Sky Sport News" , "category" : "News"} <br/>
```

Users and location are mocked so service will be able to identify only:  <br/>
```
Customer locations:
 {"customerId" : "123", "locationId" :  "London"} <br/>
 {"customerId" : "321", "locationId" :  "Liverpool"} <br/>
```

## Out of scope:
 - Anny eventual consistency issue with CustomerLocationService in case it will be decided to implement as a separate microservice.
 - Anny additionl infrastructural configuration(except heroku's configuration).

## Convention:
 - Test names does not contains "test" prefix and should provide as message the purpose of test(will be easy to read in case it is failing).
 - Test should have coverage > 70%.
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
