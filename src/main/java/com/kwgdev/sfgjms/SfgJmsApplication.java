package com.kwgdev.sfgjms;

import org.apache.activemq.artemis.core.config.impl.ConfigurationImpl;
import org.apache.activemq.artemis.core.server.ActiveMQServer;
import org.apache.activemq.artemis.core.server.ActiveMQServers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SfgJmsApplication {





    public static void main(String[] args) throws Exception {

        // this is not a normal configuration - you normally don't run an embedded server like this

        // normally you would run an ActiveMQ Server outside of a Spring Boot Application
        // but we are doing it here to start for simplicity

        ActiveMQServer server = ActiveMQServers.newActiveMQServer(new ConfigurationImpl()
                .setPersistenceEnabled(false)
                .setJournalDirectory("target/data/journal")
                .setSecurityEnabled(false)
                .addAcceptorConfiguration("invm", "vm://0"));

        server.start();


        SpringApplication.run(SfgJmsApplication.class, args);
    }

}
