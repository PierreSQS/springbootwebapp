FROM centos

# Update the yum Repo first! (Seems to be necessary on CentOS8 to allow java installation)
RUN sed -i 's/mirrorlist/#mirrorlist/g' /etc/yum.repos.d/CentOS-*
RUN sed -i 's|#baseurl=http://mirror.centos.org|baseurl=http://vault.centos.org|g' /etc/yum.repos.d/CentOS-*

# Then install java17
RUN yum -y install java-17-openjdk

VOLUME /tmp

ADD /target/spring-boot-web-0.0.1-SNAPSHOT.jar jt-app.jar

RUN sh -c 'touch /jt-app.jar'

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/jt-app.jar"]

