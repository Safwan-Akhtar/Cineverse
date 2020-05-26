# Cineverse: QA Cinema Project

Our final project as part of the QA Academy, started as "Team Gates".

This application aims to build a website for "QA Cinemas" about movies, listings and upcoming releases. It is currently for demo purposes only, showcasing our academy training and knowledge of Spring & connecting a working back-end to a front-end via API calls.

Warning, some parts of this readme might be out of date.

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

MVP: A functional ‘front-end’ web app (and integrated APIs) which connects to a back-end written in Java, and a relational database.
<details>
<summary>Technology used in the project...</summary>
    
- Back-end: Java source code using Spring libraries
- Database: SQL database hosted on Google Cloud Platform
- Front-end: html, css and javascript
- Source Control: Git
- IDE: IntelliJ Ultimate
- Testing: using a combination of Junit, Mockito and Selenium
- Maven to build and integrate with...
- Jenkins as part of my CI Pipeline to send to...
- Sonarqube (hosted on a Google Cloud VM) and...
- Nexus (artifact repository).

</details>

Kanban Board for QA Project: [github boards](https://github.com/CarolineS-QA/hwa-game-time-log/projects/1)

Presentation about the project: [on google slides](https://docs.google.com/presentation/d/1wznZu-mg9XHuvzp51Q3ZPYwYu3qjcSRW8Uu_N3bzJ_g/edit?usp=sharing)

Please see the `docs` folder for the other documentation that is not linked in the 'design' column of the Kanban Board.

**[Back to top](#table-of-contents)**

## Project Status 
Current release: v0.1 - in development

**Test Coverage:** For src/main/java: 0% // Sonarqube: 0% // Overall: 0%

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

If my GCP instance is no longer live...
A relational database to configure the application to.
(a GCP instance will require the least application config, however you can also use mySQL or h2)

You can use the command line to run the program but git & git bash are nice to have.

For the front end it's preferred that you have a Chrome browser (if you want to make use of my app shortcut).
```
**To Develop**

When you open the project in an IDE to develop, the pom.xml file should allow your IDE to automatically download the required dependencies (libraries).

```
The main IDE that I used for this project was IntelliJ Ultimate
Postman was used to test my API calls before writing them in JavaScript
I also used Visual Studio Code for the front-end
As part of the CI pipeline for this project I used Jenkins
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

### Getting the Source

This project is [hosted on GitHub](https://github.com/CarolineS-QA/hwa-game-time-log). You can clone this project directly using this command:

```
git clone git@github.com:CarolineS-QA/hwa-game-time-log.git
```
**[Back to top](#table-of-contents)**
## Building

How to build my project: 

### Built With

[Maven](https://maven.apache.org/) - Dependency Management

* Clone the repo to your machine.
* Open the cmd line / git bash inside the repo file directory.
* Run the following commands:

``` mvn clean package ```

``` mvn spring-boot:run ```

As a Spring app, running the jar with `java -jar FileName.jar` won't work (at least not without some config).

Note: If my GCP instance is no longer active, you will need a database on your machine set up to connect to, and configured in `application.properties` before running the above commands. When you run the second command the program will run, launching the Spring boot application. You can then navigate to `localhost:8181` via a browser or my shortcut provided in this repo, to reach the home page of the web interface. The app will run until you trigger the `/shutdownAppContext` API call (click the red button on the home page).

### Running the tests

The easiest way to run all my existing tests is to right click on `test/java/com.qa.hwq` in your IDE and select `Run tests in 'com.qa.hwa'` or `Run tests in 'com.qa.hwa' with Coverage`

![Run All Tests](https://i.imgur.com/0YNyoqs.png)

**[Back to top](#table-of-contents)**
#### Unit Tests 
JUnit is used for unit tests. A unit test will test individual methods within a class for functionality. Below is a simple Unit Test for my UserDTO class:

```
    @Before
    public void SetUp()
    {
        sessionDTOs = new ArrayList<>();
        zeroDuration = Duration.ofDays(0);
        userWithId = new UserDTO(1L, "testUser", zeroDuration, zeroDuration, zeroDuration, sessionDTOs);
    }

    @Test
    public void notEqualsWithNull() {
        assertNotEquals(null, userWithId);
    }
```

In IntelliJ, as you write tests annotated with @Test, it gives you the option to run tests in a class, or individual Tests. Just look for the green arrows in the margins.

![Run All Unit Tests in a class](https://i.imgur.com/B6wd2Pu.png)
![Run a specific Unit Test](https://i.imgur.com/dbfsorJ.png)

**[Back to top](#table-of-contents)**
#### Integration Tests 
Mockito is used for intergration testing, but can also be applied to certain unit tests. It tests how different classes interact with each other. By 'mocking' the functions that a method/class relies on we can see how the code we are testing works by assuming the parts it relies on work too.

```
//Mockito Unit Test
    @Test
    public void readAllSessionsTest() {
        when(repo.findAll()).thenReturn(gameSessionList);
        when(this.mapper.map(testSessionWithId, GameSessionDTO.class)).thenReturn(sessionDTO);
        assertEquals(this.service.readAllSessions(), gameSessionDTOList);
        verify(repo, times(1)).findAll();
    }

//Integration Test
@Test
    public void deleteGameSessionTest(){
        assertThat(this.service.deleteGameSession(this.testSessionWithId.getSessionId())).isFalse();
    }
```
In IntelliJ, as you write tests annotated with @Test, it gives you the option to run tests in a class, or individual Tests. Just look for the green arrows in the margins.

![Run all integration Tests](https://i.imgur.com/WrgkrWh.png)
![Run a single integration Test](https://i.imgur.com/pljXWW1.png)

**[Back to top](#table-of-contents)**

#### User acceptance Tests (with Selenium)
Selenium uses the `chromedriver.exe` included in this repository to run automated tests mocking use of the front-end. I have included the `extent-report.xml` and dependencies required to get easy to read test reports in the form of html files.

Checkout my `selenium-tests` branch for examples of the tests in this project - it's not included in the master because it doesn't agree with Jenkins just yet...

Or take a look at my [selenium-testing](https://github.com/CarolineS-QA/selenium-testing) repo which has other examples.

#### Static analysis
Sonarqube is used for static analysis. I used it to see how well my code conformed to an industry standard, the amount of coverage for my tests, and also highlighting bugs and security warnings.

```
mvn clean package
sonar:sonar -Dsonar.host.url=http://YourVMForSonarQubeIP:PORT/ -Dsonar.login.admin=admin -Dsonar.password=admin
```
![SonarQube example](https://i.imgur.com/f7agBSC.png)

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

![Postman createUser](https://i.imgur.com/FKdR2Rf.png)

Should response with:
```
{
    "userId": 1,
    "username": "testGamer",
    "totalTimePlayed": 0.0,
    "freeTime": 24.000000000,
    "timeRemaining": 24.000000000,
    "gameSessions": []
}
```

JSON for sending /createGameSession
```
{
	"gameName": "Hello World",
	"user":
	{
		"userId": 1,
		"username": "testGamer"
	},
	"timePlayed": 7,
	"timeOfSession": "2007-12-03T10:15:30"
}
```
Responds with:
```
{
    "sessionId": 1,
    "user": "testGamer",
    "gameName": "Hello World",
    "timeOfSession": "2007-12-03T10:15:30",
    "timePlayed": 7.000000000
}
```

If you /getUserByUsername/testGamer

```
{
     "userId": 1,
     "username": "testGamer",
     "totalTimePlayed": 0.0,
     "freeTime": 24.000000000,
     "timeRemaining": 24.000000000,
     "gameSessions": [
         {
             "sessionId": 1,
             "user": "testGamer",
             "gameName": "Hello World",
             "timeOfSession": "2007-12-03T10:15:30",
             "timePlayed": 7.000000000
         }
     ]
 }
 ```

On development localhost:8181 page:
![Development web interface](https://i.imgur.com/vZhjC1V.png)

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

**[Back to top](#table-of-contents)**
## Contributing

Currently not accepting contributions due to the nature of this project being part of my Academy training. However, after June 8th 2020 I will be open to contributions.

Please review [CONTRIBUTING.md](docs/CONTRIBUTING.md) for details on our code of conduct and development process (this is currently in development).

**[Back to top](#table-of-contents)**
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

## Acknowledgements

* Savannah [[savannahvaith](https://github.com/savannahvaith)]
for my JavaScript training.
* Jordan [[JHarry444](https://github.com/JHarry444)]
for his help with Spring during the project.
* Tadas [[tvaidotas](https://github.com/tvaidotas)]
for his initial introduction of Spring and Selenium testing.
* Nick [[nickstewarttds](https://github.com/nickrstewarttds)]
as my main trainer and moral support.
* Embedded Artistry [[embeddedartistry](https://github.com/embeddedartistry)] for their documentation templates [embedded-resources/docs](https://github.com/embeddedartistry/embedded-resources/tree/master/docs)


**[Back to top](#table-of-contents)**

