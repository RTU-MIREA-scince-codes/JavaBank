FROM openjdk:20

RUN mkdir -p /info
RUN touch /info/javabank.log
RUN chmod 777 /info/javabank.log

COPY "./build/libs/JavaBank-0.0.1-SNAPSHOT.jar" "javabank.jar"
ENTRYPOINT ["java", "-jar", "javabank.jar"]