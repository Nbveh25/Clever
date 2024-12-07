# Stage 1: Build the application
FROM amazoncorretto:22 as build

# Set the working directory
WORKDIR /home/app

# Copy the pom.xml and source files into the container
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package

# Stage 2: Create the final image with Tomcat
FROM tomcat:9.0

# Copy the WAR file from the build stage to Tomcat's webapps directory
COPY --from=build /home/app/target/Clever.war /usr/local/tomcat/webapps/ROOT.war

# Expose port 8080
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]