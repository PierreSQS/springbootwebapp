FROM centos

RUN yum install java -y

VOLUME /tmp

ADD /target/spring-boot-web-0.0.1-SNAPSHOT.jar jt-app.jar

RUN sh -c 'touch /jt-app.jar'

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/jt-app.jar"]

