FROM openjdk:18
WORKDIR /app
COPY ./target/FIR_Micro-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
CMD ["java", "-jar", "FIR_Micro-0.0.1-SNAPSHOT.jar"]
 
