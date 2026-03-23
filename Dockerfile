
FROM --platform=$BUILDPLATFORM maven:3.9-eclipse-temurin-21 AS builder

WORKDIR /workspace

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src src
RUN mvn package -DskipTests

RUN java -Djarmode=tools -jar target/*.jar extract --layers --destination target/extracted

RUN find target/extracted -type f

FROM eclipse-temurin:21-jre-alpine

WORKDIR /application
EXPOSE 8082

ARG EXTRACTED=/workspace/target/extracted
COPY --from=builder ${EXTRACTED}/dependencies/          ./
COPY --from=builder ${EXTRACTED}/spring-boot-loader/    ./
COPY --from=builder ${EXTRACTED}/snapshot-dependencies/ ./
COPY --from=builder ${EXTRACTED}/application/           ./

ENTRYPOINT ["java", "-jar", "playgound-0.0.1-SNAPSHOT.jar"]