
# GridFSApp

GridFSApp is a Java application that demonstrates how to use MongoDB's GridFS for storing and retrieving large files.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

Before running this project, you need to have the following installed:
- Java JDK 8 or higher
- Maven
- MongoDB

### Installing

A step-by-step series of examples that tell you how to get a development environment running.

1. **Generate the Project Structure**:
   Use Maven to generate the project structure.

   ```bash
   mvn archetype:generate -DgroupId=com.msresearch.app -DartifactId=GridFSApp -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
   ```

2. **Build the Project**:
   Navigate to the project directory and run:

   ```bash
   mvn clean install
   ```
   This command compiles the project and installs the necessary dependencies.


# General Execution:
```bash
java -Dmongodb.uri="mongodb+srv://" -Dmongodb.database="" -Dlocal.filepath="" -jar target/gridfsapp-1.0-SNAPSHOT-jar-with-dependencies.jar
```

Download a File by Filename:

```bash
java -Dmongodb.uri="mongodb+srv://" -Dmongodb.database="" -Dfilename="" -Dlocal.filepath="" -jar target/gridfsapp-1.0-SNAPSHOT-jar-with-dependencies.jar
```


Download a File by ObjectId:
```bash
java -Dmongodb.uri="mongodb+srv://" -Dmongodb.database="" -Dobjectid="" -Dlocal.filepath="" -jar target/gridfsapp-1.0-SNAPSHOT-jar-with-dependencies.jar
```


# Configuration

    mongodb.uri: MongoDB connection URI.
    mongodb.database: The name of the database to connect to.
    local.filepath: Local path for uploading or downloading files.
    filename: The name of the file to download (used in the second command).
    objectid: The ObjectId of the file to download (used in the third command).
