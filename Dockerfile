# Use an official OpenJDK runtime as a parent image
FROM amazoncorretto:21.0.6

# Set the working directory in the container
WORKDIR /app

# Copy the jar file into the container
COPY target/bank-account-0.0.1-SNAPSHOT.jar bank-account-0.0.1-SNAPSHOT.jar

# Expose port 8080
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "bank-account-0.0.1-SNAPSHOT.jar"]