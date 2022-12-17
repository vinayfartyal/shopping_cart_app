FROM openjdk:11

#We want our application would start when we run our container.So, we are taking reference of
#jar file in an argument and after that we will copy that argument.
ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} serviceregistry.jar

#ENTRYPOINT command is used when you want to execute files when your container starts.
ENTRYPOINT ["java","-jar","/serviceregistry.jar"]

EXPOSE 8761