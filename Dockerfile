FROM centos

# Update the yum Repo first! (necessary on CentOS8 since it reached End Of Life!!)
# Also read the comment in the related GitHub Repo
RUN sed -i 's/mirrorlist/#mirrorlist/g' /etc/yum.repos.d/CentOS-*
RUN sed -i 's|#baseurl=http://mirror.centos.org|baseurl=http://vault.centos.org|g' /etc/yum.repos.d/CentOS-*

# Then install java17
RUN yum -y install java-17-openjdk

VOLUME /tmp

ADD /target/spring-boot-web-0.0.1-SNAPSHOT.jar jt-app.jar

RUN sh -c 'touch /jt-app.jar'

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/jt-app.jar"]

