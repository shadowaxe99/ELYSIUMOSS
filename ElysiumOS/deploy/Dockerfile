FROM openjdk:11-jre-slim

WORKDIR /app

COPY ./build/ElysiumOS.jar /app/ElysiumOS.jar

EXPOSE 8080

CMD ["java", "-jar", "ElysiumOS.jar"]