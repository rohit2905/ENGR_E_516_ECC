FROM openjdk:latest

# Set the working directory
WORKDIR /home

# Copy the generated JAR file
COPY ./target/client-0.0.1-SNAPSHOT.jar client.jar

# Set the entrypoint to run the application
ENTRYPOINT ["java", "-jar", "client.jar"]
