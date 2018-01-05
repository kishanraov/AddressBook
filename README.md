# Spring Boot MySQL AddressBook

This project is to create a RESTful API services and a sample webpage to view address book, edit, delete addresses. Addresses are loaded from a CSV file (name, address) and stored in MySQL database.

## Getting Started

These instructions will get you a copy of the project, compile and run on your local machine for development and testing purposes.

### Prerequisites

What things you need to install the software, and how to install them

```
 Java 1.8+
 MySQL 5.7 and above
 Apache Maven 3.3.3 and above
 Rest API Client (example: Google Chrome Browser extension Postman) or curl utility installed
```

```
Download JDK from Oracle website http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html
Download MySQL from https://dev.mysql.com/downloads/windows/installer/
Download maven from http://maven.apache.org/download.cgi
To install mvnw  use the command  mvn -N io.takari:maven:wrapper
```

### Installing

Follow the instructions to download the source code, compile and run

After compilation, the packaged file is a self executable jar file, with all dependencies

```
git clone https://github.com/kishanraov/AddressBook.git
cd AddressBook
mvn clean package (or mvnw clean package)

java  -jar target/spring-boot-addressbook-0.0.1.jar (keep the data.csv file in /tmp/ directory) OR
java  -jar target/spring-boot-addressbook-0.0.1.jar --csvfile=provide full path of data.csv

The CSV file gets uploaded to MySQL database. the default databse url is jdbc:mysql://localhost:3306/test and default username and password are scott/tiger. If you would like to change the db url, userid and passsword then you need to modify resource.properties file under the src/main/resources directory.

The Tomcat web server runs on port 8080.
```


## Running the tests

  * To test the web server is up and running, run the following commands.

```
  http://localhost:8080/address/list
  http://localhost:8080/address/new
  http://localhost:8080/address/show/1
  http://localhost:8080/address/edit/1

```  

## Built With

* [Eclipse](http://www.eclipse.org/downloads/) - The IDE used
* [Maven](https://maven.apache.org/) - Dependency Management
* [Google](https://github.com/googlemaps/google-maps-services-java/) - Google Geo Coding Api


## Authors

* ** Kishan Rao Verukonda** - *Initial work*


## License

This project require license to run

