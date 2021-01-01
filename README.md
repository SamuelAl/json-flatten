# MongoDB Challenge: JSON Flatten

## Introduction
Project files for the assigned MongoDB Challenge: a program that takes a JSON object as input and outputs a flattened version of the JSON object, with keys as the path to every terminal value in the JSON structure. My solution has been implemented in Java (JDK 11) using Maven for dependency management.

## Build
The project is build using Maven to manage dependencies. In order to build the project on your machine please make sure you have Maven properly installed, together with JDK 11 or later version.

Before running any Maven commands, I recommend running __mvn clean__ to clean any previously compiled project code and avoid errors on some systems.

Open a terminal window on the root directory and run the following command:

```
mvn package
```
The resulting jar file will be store in the /target folder.

## Running Tests
Before running any Maven commands, I recommend running __mvn clean__ to clean any previously compiled project code and avoid errors on some systems.

This project has JUnit tests to test the main utility class JsonFlattener, which contains the functionality to flatten the JSON objects. Tests can be run automatically using the command

```
mvn test
```

The tests obtain input and expected output from the corresponding test .txt files in /src/test/resources.

## Usage
The exported jar package is included in the /target folder. The program offers various options to run it.
1. __Take .json file as input parameter:__ Accepts name of json file as first parameter of program. Please place .json file on same directory as jar file. Resulting flattened JSON object will be generated as a new JSON file called output.json that will be stored on the same directory.

```
$ java -jar json-flatten-0.0.1.jar example.json
```

2. __Use of stdin:__
The program allows for the standard usage of piping and redirection to take advantage of the System's stdin and stdout streams. To run the program in this mode call the program without parameters and use shell to setup desired stdin and stdout sources. Output will be generated through the stdout.

```
$ cat example.json | java -jar json-flatten-0.0.1.jar
$ java -jar json-flatten-0.0.1.jar < example.json > output.json
```

## Technologies 
This project makes use of the Jackson JSON library for Java to deserialize the JSON string obtained from the .json file to a Map object for further processing, as allowed by the project specifications.

The project was written entirely in Java using the Eclipse IDE and Maven to manage dependencies.

## Requirements
Please download and install the latest Java SE (11 or above) or JDK in order to use this program. In order to build or test the program, please download the Java JDK 11 or latest version.

## Credit
Author: Samuel Alarco Cantos

For any queries concerning the usage or workings of the program, please contact me at alarcocs@tcd.ie
