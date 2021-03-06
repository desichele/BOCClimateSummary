"# BOCClimateSummary Project Assignment
Address : https://github.com/desichele/BOCClimateSummary

Welcome, 

The climate summary project is build with MAVEN. After downloading please rename directory mvn -> .mvn and settings -> .settings first. Please follow the instruction given below to compile test and run the application. 

All the commands needs to run on application root /WeatherApp directory where pom.xml is present. 

1. Steps to compile:
					Please type and run : "mvn compile" from application root /WeatherApp on command line. 

2. Step to test:
					Please type and run : "mvn -Dtest=TestClimateSummaryModel test" to run unit tests in the model class
					"mvn -Dtest=TestClimateSummaryDAO test"  to run unit test in the DAO later class 
					
3. Step to package and run:
					Please and run : "mvn clean package" or "mvn clean -Dtests.skip=true package" in order to skip test while packaging 
					
This should create the required .jar file (in this case target\WeatherApp-0.0.1-SNAPSHOT.jar) inside target directory. 

4 . Run the command : "java -jar target\WeatherApp-0.0.1-SNAPSHOT.jar" to launch application
 
5. Open browser and type in http://localhost:8080/ to visit application 

Technology stack used:

- JAVA - 1.8
- Spring Boot - 2.2.6
- Spring Boot Started web - 2.2.6
- Spring Data JPA - 2.2.6
- Spring ORM - 5.2.5
- Spring MVC - 5.2.5
- Maven built to compile/test/run project

- JUNIT 5.5.2
- Thymeleaf 3.0.11
- CSS/Javascript
- Client side verification
- Logging framework 
	- logback classic 
	- logback core
	- slf4j framework 

