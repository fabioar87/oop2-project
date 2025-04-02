package com.tus.network;

import com.tus.network.graph.Edge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Edge e = new Edge(1, 2);
        logger.info("Network optimizer");
    }
}
