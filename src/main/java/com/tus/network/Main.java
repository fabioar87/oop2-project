package com.tus.network;

import com.tus.network.graph.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Link e = new Link(1, 2);
        logger.info("Network optimizer");
    }
}
