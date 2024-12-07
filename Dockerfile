FROM maven:3.8.3-openjdk-17 as build

WORKDIR /home/app

COPY pom.xml .
COPY src ./src

RUN mvn clean package

FROM tomcat:9.0

COPY --from=build /home/app/target/Clever.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]