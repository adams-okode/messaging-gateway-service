# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="adamsokode@gmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

ADD  .  /

# Export environment variables
RUN export $(cat .env | xargs)

# Build jar
RUN ./gradlew build --info

# Make port 8080 available to the world outside this containr
EXPOSE 8080

# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/build/libs/messaging-0.0.1-SNAPSHOT.jar"]