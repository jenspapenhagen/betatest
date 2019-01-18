FROM jboss/wildfly
ENV DEPLOY_DIR /opt/jboss/wildfly/standalone/deployments/

ENV message "Hello World JSF with Java EE 8 form docker"

COPY target/beta.war $DEPLOY_DIR
RUN /opt/jboss/wildfly/bin/add-user.sh admin Admin#70365 --silent
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]