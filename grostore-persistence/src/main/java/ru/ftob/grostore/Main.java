package ru.ftob.grostore;

import org.apache.log4j.Logger;

public class Main {
    private static final Logger LOG = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        LOG.info("Application started");
        System.out.println("I'm the main project");
        LOG.info("Application finished");
    }
}
