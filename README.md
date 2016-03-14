<b>About:</b>
Product catalog is self contained application is running on Apache Tomcat/8.0.30 as data store is used mongodb.
Domain model is aggregated in Customer and Product.

<b>System requirements:</b>
 - Java 1.8
 - Maven 3.3.3
 - Free ports: 27017(mongodb), 8080(apache tomcat)

<b>Deployment and test:</b>
 - Step1: Run in command lien> mvn clean package spring-boot:run
 - Step2: Add cookie: Run in browser console> document.cookie="customerId=123;";
 - Step3: Application: http://localhost:8080/
 - Step4: Check Api documentation: http://localhost:8080/swagger-ui.html

<b>TODO:</b>
 - Finish UI and angular script.
 - Integrate with sales microservice for checkout function.

<b>Default data set:</b>
Created only for demo purpose. Later on the proper data store should be created and com.sky.CatalogueAppConfiguration#run should be removed.
<br/>
Products: <br/>
{ "name" : "Arsenal TV" , "category" : "Sport" , "locationId" : "London"} <br/>
{ "name" : "Chelsea TV" , "category" : "Sport" , "locationId" : "London"} <br/>
{ "name" : "Liverpool TV" , "category" : "Sport" , "locationId" : "Liverpool"} <br/>
{ "name" : "Sky News" , "category" : "News"} <br/>
{ "name" : "Sky Sport News" , "category" : "News"} <br/>

Users mocked:  <br/>
 {"customerId" : "123", "locationId" :  "London"} <br/>
 {"customerId" : "321", "locationId" :  "Liverpool"} <br/>

<b>Out of scope:</b>
 - Anny eventual consistency issue with CustomerLocationService in case it will be decided to implement as a separate microservice.
 - Anny infrastructural configuration.

<b>Convention:</b>
 - Test names does not contains "test" prefix and should provide as message the purpose of test(will be easy to read in case it is failing).
 - Test should have coverage > 70.
 - Unit test class will have "Test" postfix.
 - Integration test class will have "IT" postfix.

<b>DISCLAIMER:</b>
Purpose of project is only educational.
This project should not be used for any commercial purpose.
This work is licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License.
http://creativecommons.org/licenses/by-nc-sa/4.0/.

<b>Author:</b>
Eugeniu Cararus
cararuseugeniu@gmail.com