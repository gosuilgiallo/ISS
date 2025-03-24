# Java Microservice Application

## Overview
This project is a Java microservice application that implements a WebSocket server for managing a game using the Game of Life concept. The application allows clients to connect via WebSocket, send commands to control the game, and receive updates about the game state.

## Project Structure
```
java-microservice-app
├── src
│   ├── main
│   │   ├── java
│   │   │   └── conway
│   │   │       ├── devices
│   │   │       │   └── WSIoDev.java
│   │   │       ├── controller
│   │   │       │   └── CommandSender.java
│   │   │       └── Application.java
│   │   └── resources
│   │       └── application.properties
├── pom.xml
└── README.md
```

## Setup Instructions
1. **Clone the Repository**
   Clone the repository to your local machine using:
   ```
   git clone <repository-url>
   ```

2. **Navigate to the Project Directory**
   Change to the project directory:
   ```
   cd java-microservice-app
   ```

3. **Build the Project**
   Use Maven to build the project:
   ```
   mvn clean install
   ```

4. **Run the Application**
   Start the Spring Boot application:
   ```
   mvn spring-boot:run
   ```

## Usage
- Connect to the WebSocket server at the endpoint `/wsupdates`.
- Send commands such as `start`, `stop`, `exit`, `clear`, and `cell-x-y` to control the game.
- The first client to connect will be designated as the owner and will have control over the game commands.

## Dependencies
This project uses the following dependencies:
- Spring Boot for building the application.
- WebSocket for real-time communication.
- Other necessary libraries as specified in the `pom.xml`.

## Contributing
Contributions are welcome! Please submit a pull request or open an issue for any enhancements or bug fixes.

## License
This project is licensed under the MIT License. See the LICENSE file for more details.