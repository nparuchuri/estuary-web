FROM tomcat:latest

COPY target/estuary-web.war $CATALINA_HOME/webapps/estuary-web.war