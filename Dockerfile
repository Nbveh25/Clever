FROM maven:3.9.9-amazoncorretto-21 as build

COPY src /home/app/src
COPY pom.xml home/app
RUN mvn -f /home/app/pom.xml clean package

FROM tomcat:9.0.65-jdk17-corretto

COPY --from=build /home/app/target/Clever.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080

CMD ["catalina.sh", "run"]