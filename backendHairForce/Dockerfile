FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw .
COPY pom.xml .

RUN ./mvnw dependency:go-offline

COPY src ./src

RUN ./mvnw clean package -DskipTests

# Renomeia o .jar para um nome fixo
RUN cp target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
