FROM openjdk:11
ARG version
RUN mkdir -p /opt/blog-web
COPY build/libs/blog-web-rdb-$version.jar /opt/blog-web/blog-web.jar
WORKDIR /opt/blog-web/
EXPOSE 8080
ENV info.app.name=blog-web info.app.version=$version
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "blog-web.jar"]