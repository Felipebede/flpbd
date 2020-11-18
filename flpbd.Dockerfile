FROM maven:3.6.3-jdk-11-slim AS packager

RUN mkdir -p /flpbd

WORKDIR /flpbd

ADD ./pom.xml .
ADD ./docker-entrypoint.sh /

RUN mvn install -B -f ./pom.xml

COPY ./ .

RUN mvn package -Dmaven.test.skip=true && \
    mv ./target/*.jar /run/flpbd.jar

FROM openjdk:8-jdk-slim

COPY --from=packager /run/flpbd.jar /var/com/test/ibyte/flpbd/flpbd.jar
COPY --from=packager /docker-entrypoint.sh /docker-entrypoint.sh

RUN chmod +x /docker-entrypoint.sh

EXPOSE 8080

ENTRYPOINT [ "bash", "/docker-entrypoint.sh" ]