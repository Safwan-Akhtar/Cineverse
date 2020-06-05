# Cineverse: QA Cinema Project

Our final project as part of the QA Academy, started as "Team Gates".

This application aims to build a website for "QA Cinemas" about movies, listings and upcoming releases. It is currently for demo purposes only, showcasing our academy training and knowledge of Spring & connecting a working back-end to a front-end via API calls.

## Table of Contents

1. [About the Project](#about-the-project)
1. [Project Status](#project-status)
1. [Getting Started](#getting-started)
    1. [Dependencies](#dependencies)
    1. [Building](#building)
    1. [Installation](#installation)
    1. [Usage](#usage)
1. [Release Process](#release-process)
    1. [Versioning](#versioning)
    <!--1. [Payload](#payload) [new line] 1.[How to Get Help](#how-to-get-help)-->
1. [Further Reading](#further-reading)
1. [Contributing](#contributing)
1. [License](#license)
1. [Authors](#authors)
1. [Acknowledgements](#acknowledgements)

## About the Project

MVP: A functional ‘front-end’ website (and integrated APIs) which connects to a back-end written in Java, and a relational database.
<details>
<summary>Technology used in the project...</summary>
    
- Back-end: Java source code using Spring libraries
- Database: SQL database hosted on Google Cloud Platform
- Front-end: html, css and javascript
- Source Control: Git
- IDE: IntelliJ Ultimate, Visual Studio Code, Eclipse
- Testing: using a combination of Junit, Mockito and Selenium
- Maven to build and integrate with...
- Jenkins for the CI Pipeline to send to...
- Sonarqube (hosted on a Google Cloud VM) and
- External API's: Stripe, Omdb.com

</details>


Kanban Board for QA Project: using Jira software.

Presentation about the project: [on google slides](https://docs.google.com/presentation/d/119-IZiVRVzC61cojHj9Mfl1r9C4oldZ9N1gdkUqk3Ec/edit?usp=sharing)

Please see the `docs` folder for the other documentation.


**[Back to top](#table-of-contents)**

## Project Status 
Current release: v0.1 - in development

**Test Coverage:** For src/main/java: 81% // Overall: 84% // 180 tests

For test reports please see the `docs` folder.

**Jenkins Status (CI Pipeline):**

N/A


**[Back to top](#table-of-contents)**

## Getting Started

### Dependencies
What things you need to install the software and where to find them.

**To Run**

```
Java SE 8 (or later) to run the jar file.
Maven to create the jar-file and run.

If the GCP instance is no longer live...
A relational database to configure the application to.
(a GCP instance will require the least application config, however you can also use local mySQL or h2)

You can use the command line to run the program but git & git bash are nice to have.

For the front end it's preferred that you have a Chrome browser.
```
**To Develop**

When you open the project in an IDE to develop, the pom.xml file should allow your IDE to automatically download the required dependencies (libraries).

```
The main IDE's that we used for this project was IntelliJ Ultimate & Visual Studio Code
Postman was used to test my API calls before writing them in JavaScript
As part of the CI pipeline for this project we used Jenkins
```

**Links for Dependencies**
Java latest version [here](https://www.oracle.com/java/technologies/javase-downloads.html#JDK14),
Maven [here](https://maven.apache.org/),
mySQL [here](https://dev.mysql.com/downloads/installer/),
Git & Git Bash [here](https://git-scm.com/downloads),
IntelliJ Ultimate [here](https://www.jetbrains.com/idea/download/#section=windows),
Visual Studio Code [here](https://code.visualstudio.com/Download),
Jenkins [here](https://jenkins.io/download/),
Postman [here](https://www.postman.com/downloads/),
Google Chrome [here](https://www.google.com/chrome/).

**External API's**
Stripe [here](https://stripe.com/docs/development/quickstart)
Open Movie Database [here](https://www.omdbapi.com/)

### Getting the Source

This project is [hosted on GitHub](https://github.com/CarolineS-QA/hwa-game-time-log). You can clone this project directly using this command:

```
git clone https://github.com/Safwan-Akhtar/Cineverse.git
```
**[Back to top](#table-of-contents)**
## Building

How to build this project: 

### Built With

[Maven](https://maven.apache.org/) - Dependency Management

* Clone the repo to your machine.
* Open the cmd line / git bash inside the repo file directory.
* Warning! The app does not have a 'stop' function, it is advised to only run in an IDE.
* Run the following commands:

``` mvn clean package ```

``` mvn spring-boot:run ```

As a Spring app, running the jar with `java -jar FileName.jar` won't work (at least not without some config).


Note: If the GCP instance is no longer active, you will need a database on your machine set up to connect to, and configured in `application.properties` before running the above commands. When you run the second command the program will run, launching the Spring boot application. You can then navigate to `localhost:8181` via a browser, to reach the home page of the web interface. The app will run until you trigger the `/shutdownAppContext` API call (click the red button on the home page).

### Running the tests

The easiest way to run all our existing tests is to right click on `test/java/com.qa.hwq` in your IDE and select `Run tests in 'com.qa.hwa'` or `Run tests in 'com.qa.hwa' with Coverage`


![Run All Tests](https://i.imgur.com/0YNyoqs.png)

**[Back to top](#table-of-contents)**
#### Unit Tests 
JUnit is used for unit tests. A unit test will test individual methods within a class for functionality. Below is a simple Unit Test for my UserDTO class:

```
// setUp for Mockito Unit tests
    @Before
    public void setUp(){
        this.customersList = new ArrayList<> ();
        this.testCustomers = new Customers ("Felix", "Tellytub");
        this.customersList.add(testCustomers);
        this.testCustomersWithID = new Customers (testCustomers.getName(), testCustomers.getUsername());
        this.testCustomersWithID.setCustomersId (id);
        this.customersDTO = this.mapToDTO(testCustomersWithID);
    }

    @Test
    public void getAllCustomersTest(){
        when(repository.findAll()).thenReturn(this.customersList);
        when(this.mapper.map(testCustomersWithID, CustomersDTO.class)).thenReturn(customersDTO);
        assertFalse("Service returned no Customers", this.service.readCustomers().isEmpty());
        verify(repository, times(1)).findAll();
    }

// simple unit test expecting an exception
    @Test(expected=NullPointerException.class)
    public void customersDTONameNullButOtherNameNotNull() {
        customersDTO.setName(null);
        assertFalse(customersDTO.equals(other));
    }
```

In IntelliJ, as you write tests annotated with @Test, it gives you the option to run tests in a class, or individual Tests. Just look for the green arrows in the margins.

![Run All Unit Tests in a class](https://i.imgur.com/B6wd2Pu.png)
![Run a specific Unit Test](https://i.imgur.com/dbfsorJ.png)

**[Back to top](#table-of-contents)**
#### Integration Tests 
Mockito is used for intergration testing, but can also be applied to certain unit tests. It tests how different classes interact with each other. By 'mocking' the functions that a method/class relies on we can see how the code we are testing works by assuming the parts it relies on work too.

```
//Integration Test
    @Before
    public void setUp(){
        this.testCustomers = new Customers ("Caroline", "CarebearFan");
        this.repository.deleteAll();
        this.testCustomersWithID = this.repository.save(this.testCustomers);
    }

    @Test
    public void readCustomersTest(){
        assertThat(this.service.readCustomers())
                .isEqualTo(
                        Stream.of(this.mapToDTO(testCustomersWithID)).collect(Collectors.toList())
                );
    }
```
In IntelliJ, as you write tests annotated with @Test, it gives you the option to run tests in a class, or individual Tests. Just look for the green arrows in the margins.

![Run all integration Tests](https://i.imgur.com/WrgkrWh.png)
![Run a single integration Test](https://i.imgur.com/pljXWW1.png)

**[Back to top](#table-of-contents)**

#### User acceptance Tests (with Selenium)
Selenium uses the `chromedriver.exe` included in this repository to run automated tests mocking use of the front-end. You should check that the driver you use matches your version of chrome. Get versions [here](http://chromedriver.chromium.org/).

We have included the `extent-report.xml` and dependencies required to get easy to read test reports in the form of html files.


There are examples of the tests in this project. You can run them like you would run unit tests.

Or take a look at this [selenium-testing](https://github.com/CarolineS-QA/selenium-testing) repo which has other examples.


#### Static analysis
Sonarqube is used for static analysis. We used it to see how well my code conformed to an industry standard, the amount of coverage for my tests, and also highlighting bugs and security warnings.

```
mvn clean package
sonar:sonar -Dsonar.host.url=http://YourVMForSonarQubeIP:PORT/ -Dsonar.login.admin=admin -Dsonar.password=admin
```
![SonarQube example](https://i.imgur.com/zFF3c7m.png)

**[Back to top](#table-of-contents)**
## Installation

Installing Demo

How to get a development environment running:

* Clone the repo to your machine. (fork it first if you want to make changes for yourself).
* Open git bash (git should already be initalised if you clone it otherwise use `git init`).
* It's recommended that you start making changes on a new branch `git checkout -b NAME-OF-YOUR-BRANCH`
* Open as an existing maven project in the IDE of your choice
* You'll probably want to check the **application.properties** file in `src/main/resources` first
* You can change the database connection details and port the web app is hosted on here
* Once configured, you can start developing!
* Find the `App` file in `src/main/java/com.qa.hwa`
* There should be an option to `run` the application
* When the application is running, you can open your browser to `localhost:PORT` or test the API calls in postman.

Example of getting some data out of the system with Postman:

![Postman createUser](https://i.imgur.com/xerOh0B.jpg)

Should response with:
```
[
    {
        "screeningsId": 7,
        "movieDateTime": "2020-06-10T18:15:00",
        "screenType": "deluxe",
        "screenNumber": 2,
        "movieName": "Spiderman: Into The Spiderverse",
        "customers": []
    },
    {
        "screeningsId": 16,
        "movieDateTime": "2020-06-10T18:15:00",
        "screenType": "standard",
        "screenNumber": 4,
        "movieName": "Spiderman: Into The Spiderverse",
        "customers": []
    },
    {
        "screeningsId": 18,
        "movieDateTime": "2020-06-10T23:35:00",
        "screenType": "standard",
        "screenNumber": 4,
        "movieName": "Spiderman: Into The Spiderverse",
        "customers": []
    },
    {
        "screeningsId": 19,
        "movieDateTime": "2020-06-10T13:15:00",
        "screenType": "standard",
        "screenNumber": 5,
        "movieName": "Spiderman: Into The Spiderverse",
        "customers": []
    },
    {
        "screeningsId": 20,
        "movieDateTime": "2020-06-10T15:35:00",
        "screenType": "standard",
        "screenNumber": 5,
        "movieName": "Spiderman: Into The Spiderverse",
        "customers": []
    },
    {
        "screeningsId": 21,
        "movieDateTime": "2020-06-10T17:55:00",
        "screenType": "standard",
        "screenNumber": 5,
        "movieName": "Spiderman: Into The Spiderverse",
        "customers": []
    },
    {
        "screeningsId": 22,
        "movieDateTime": "2020-06-10T20:15:00",
        "screenType": "standard",
        "screenNumber": 5,
        "movieName": "Spiderman: Into The Spiderverse",
        "customers": []
    },
    {
        "screeningsId": 23,
        "movieDateTime": "2020-06-10T22:45:00",
        "screenType": "standard",
        "screenNumber": 5,
        "movieName": "Spiderman: Into The Spiderverse",
        "customers": []
    }
]
```

JSON for sending addCustomerToScreening/{Screening_id}
```
    {
		"name" : "Chris",
		"username" : "Chrisctr"
    }
```
Responds with:
```
{
    "screeningsId": 1,
    "movieDateTime": "2020-06-10T13:15:00",
    "screenType": "deluxe",
    "screenNumber": 1,
    "movieName": "Mulan",
    "customers": [
        {
            "customersId": 1,
            "name": "Chris",
		    "username" : "Chrisctr"
            "tickets": []
        }
    ]
}
```

If you want to addTicketsToCustomer/{customer_id}

```
	{
		"seatNo" : "F8",
		"ticketType" : "child",
		"screenId" : 1
    }
 ```

On localhost:8181 page:
![Development web interface](https://i.imgur.com/kHuocmT.jpg)

Remember you can `Ctrl + Shift + I` to inspect and reach the developer's console.

**[Back to top](#table-of-contents)**

## Usage

This project is a demo for using the Spring library & API calls.

**[Back to top](#table-of-contents)**

## Release Process

This project is in development, for demo purposes only and not yet at 'release' stage.

### Versioning

We use [SemVer](http://semver.org/) for versioning. For a list of available versions, see the [repository tag list](https://github.com/CarolineS-QA/hwa-game-time-log/tags).

**[Back to top](#table-of-contents)**

<!-- ### Payload -->
<!-- ## How to get help -->

## Further Reading

Spring:
- [https://spring.io/guides/gs/accessing-data-mysql/](https://spring.io/guides/gs/accessing-data-mysql/)
- [https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-documentation)
- [https://vladmihalcea.com/jpa-hibernate-synchronize-bidirectional-entity-associations/](https://vladmihalcea.com/jpa-hibernate-synchronize-bidirectional-entity-associations/)

Front-end:
- [https://getbootstrap.com/docs/4.5/getting-started/introduction/](https://getbootstrap.com/docs/4.5/getting-started/introduction/)

API's:
- [https://stripe.com/docs/api](https://stripe.com/docs/api)

**[Back to top](#table-of-contents)**
## Contributing


Currently not accepting contributions due to the nature of this project being part of our Academy training. However, after June 8th 2020 we may be open to contributions.

Please review [CONTRIBUTING.md](docs/CONTRIBUTING.md) for details on our code of conduct and development process (this is currently in development).


## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

**[Back to top](#table-of-contents)**
## Authors

* **Caroline Strasenburgh** [[CarolineS-QA](https://github.com/CarolineS-QA)]
* **Felix Marley** [[Femarleycode](https://github.com/Femarleycode)]
* **Christian Redfern** [[Christian-QA](https://github.com/Christian-QA)]
* **Luke Smyth-osbourne** [[sosbourneQA](https://github.com/sosbourneQA)]
* **Safwan Akhtar** [[Safwan-QA](https://github.com/Safwan-Akhtar)]
* Jordan [[JHarry444](https://github.com/JHarry444)] *feature/jordan - the file purge*

## Acknowledgements

* Savannah [[savannahvaith](https://github.com/savannahvaith)]
for our JavaScript training.
* Jordan [[JHarry444](https://github.com/JHarry444)]
for his help with Spring during the project.
* Tadas [[tvaidotas](https://github.com/tvaidotas)]
for his initial introduction of Spring and Selenium testing.
* Nick [[nickstewarttds](https://github.com/nickrstewarttds)]
as our main trainer and moral support.
* Embedded Artistry [[embeddedartistry](https://github.com/embeddedartistry)] for their documentation templates [embedded-resources/docs](https://github.com/embeddedartistry/embedded-resources/tree/master/docs)


**[Back to top](#table-of-contents)**
