# Assessment - Sean Oxley

### Requirements
1. JavaJDK - 11.0.22
2. Maven 3.9.6 - 11.0.22
3. Chrome 123+ 

### Dev Setup
1. Download and install [Java JDK](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
    - Open a terminal then run `java -version` to verify the installation.
2. Create a `JAVA_HOME` environmental variable pointing to the Java version directory.
3. Download and install [Apache Maven](https://maven.apache.org/download.cgi) then add the tool to your `PATH`.
    - Open a terminal then run `mvn -v` to verify the installation.

### Usage
1. Clone this repo to your local machine.
2. Open a terminal then nabigate to the base project directory.
3. Run one of the following commands.
    1. For Chrome browser: ```mvn clean test```
    2. For Brave browser: ```mvn clean test -Dbrowser=brave```

### Notes
- As of 4/23/2024, test `storeSearchTest` will fail. There are multiply products which do not contain the word `Table` in the description.