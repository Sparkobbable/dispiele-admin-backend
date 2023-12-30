FROM maven:3.8.4-openjdk-17 AS MAVEN_BUILD
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn -Dmaven.test.skip=true package

FROM openjdk:17-jdk
ARG JAVA_OPTS
ARG STAGE
ARG BUSINESS_EMAIL
ARG OAUTH_CLIENT_SECRET
COPY --from=MAVEN_BUILD ./build/target/*.jar /app/dispiele-admin-backend.jar
ENV JAVA_OPTS=$JAVA_OPTS
ENV STAGE=$STAGE
ENV BUSINESS_EMAIL=$BUSINESS_EMAIL
ENV OAUTH_CLIENT_SECRET=$OAUTH_CLIENT_SECRET
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -Dspring.profiles.active=$STAGE -Dbusiness.email=$BUSINESS_EMAIL -Doauth.admin-site.clientsecret=$OAUTH_CLIENT_SECRET -jar /app/dispiele-admin-backend.jar