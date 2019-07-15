FROM maven

ENV APP_DIR /src/app/

RUN mkdir -p $APP_DIR

COPY ./pom.xml ${APP_DIR}

WORKDIR ${APP_DIR}

RUN ["mvn", "compile"]

COPY . .

CMD ["mvn", "-T", "1C", "clean", "package", "tomcat7:run"]
