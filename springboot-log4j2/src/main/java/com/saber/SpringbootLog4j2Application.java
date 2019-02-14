package com.saber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootLog4j2Application {

    private static Logger logger = LoggerFactory.getLogger(SpringbootLog4j2Application.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringbootLog4j2Application.class, args);
        logger.error("log4j2 error!");
        logger.debug("log4j2 debug!");
    }

}

