# JMS (Jave Messaging Service) Demo Project
Based on John Thompson's course - [https://github.com/springframeworkguru/sfg-jms](https://github.com/springframeworkguru/sfg-jms)

## Technology Used

1. **Spring JMS Artemis and Artemis Server (Embedded)**
2. **ActiveMQ** - managed by Apache, donated by RedHat
3. **Docker**

See [https://github.com/vromero/activemq-artemis-docker](https://github.com/vromero/activemq-artemis-docker) for ActiveMQ docker image implementation

To run:

run the Spring Boot application ensuring the embedded ActiveMQ server is **not** running  
in windows command line  
run  
**docker run -it --rm -p 8161:8161 -p 61616:61616 vromero/activemq-artemis**  
  
log in and view server/message details   
  
auto-refresh is on in the ActiveMQ management console, disable listener component in Spring app to keep it from
refreshing every 2 seconds (as a new message is sent in the app every 2 seconds)