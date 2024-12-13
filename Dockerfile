FROM maven:3.9.4-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn clean package -DskipTests

FROM tomee:10.0.0-M3-jre17-plume

WORKDIR /usr/local/tomee

RUN rm -rf /usr/local/tomee/webapps/ROOT

COPY --from=build /app/target/*.war ./webapps/ROOT.war
COPY --from=build /app/target/Clever ./webapps/ROOT

EXPOSE 80

CMD ["catalina.sh", "run"]
