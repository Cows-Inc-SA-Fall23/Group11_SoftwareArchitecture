FROM maven:3.8.3-openjdk-17-slim AS build
WORKDIR /app
COPY ./feedSystem .
RUN mvn package -DskipTests


# Start with a base image containing Java runtime
FROM openjdk:17-jdk

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Install wget and download Tomcat
ENV TOMCAT_VERSION 10.1.17
RUN microdnf install -y wget && \
    wget https://downloads.apache.org/tomcat/tomcat-10/v${TOMCAT_VERSION}/bin/apache-tomcat-${TOMCAT_VERSION}.tar.gz -O /tmp/tomcat.tar.gz && \
    tar xzf /tmp/tomcat.tar.gz -C /opt && \
    rm /tmp/tomcat.tar.gz && \
    mv /opt/apache-tomcat-${TOMCAT_VERSION} /opt/tomcat && \
    microdnf clean all

# Set environment variables for Tomcat
ENV CATALINA_HOME /opt/tomcat
ENV PATH $PATH:$CATALINA_HOME/bin

# Copy the war file to the webapps directory of Tomcat
COPY --from=build /app/target/*.war $CATALINA_HOME/webapps/


# Command to run Tomcat
CMD ["catalina.sh", "run"]