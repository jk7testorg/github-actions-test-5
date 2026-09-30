# ============================================================
# AI-Generated Dockerfile
# Language: java | Framework: spring-boot
# Builder: maven:3.9-eclipse-temurin-17 → Runtime: eclipse-temurin:17-jre-alpine
# ============================================================

FROM tomcat:10.1-jre17-temurin
ARG CONTEXT_PATH=ROOT
RUN rm -rf /usr/local/tomcat/webapps/ROOT
COPY target/*.war /usr/local/tomcat/webapps/${CONTEXT_PATH}.war
EXPOSE 8080
HEALTHCHECK --interval=30s --timeout=5s --retries=3 \
  CMD wget -qO- http://localhost:8080/${CONTEXT_PATH} || exit 1
CMD ["catalina.sh", "run"]