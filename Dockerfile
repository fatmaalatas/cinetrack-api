# 1. Aşama: Projeyi Derle (Maven ve Java 17 kullanarak)
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Kodu derle ve .jar dosyasına çevir (Testleri şimdilik atla)
RUN mvn clean package -DskipTests

# 2. Aşama: Çalıştır (Sadece çalıştırmak için hafif bir Java kopyası al)
FROM eclipse-temurin:17-jre
WORKDIR /app
# İlk aşamada üretilen o .jar dosyasını bu kutunun içine kopyala
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
# Motoru ateşleme komutu
ENTRYPOINT ["java", "-jar", "app.jar"]