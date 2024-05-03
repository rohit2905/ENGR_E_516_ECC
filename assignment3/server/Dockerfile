FROM openjdk:latest

# Set the working directory
WORKDIR /home

# Copy the generated JAR file
COPY ./target/server-0.0.1-SNAPSHOT.jar server.jar

# Set the entrypoint to run the application
ENTRYPOINT ["java", "-jar", "server.jar", "--server.port=8085"]
