FROM openjdk:11-jre-slim
COPY playerone/target/*.jar playerone.jar
COPY playertwo/target/*.jar playertwo.jar
