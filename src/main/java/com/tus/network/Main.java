package com.tus.network;

import com.tus.network.graph.WeightedNetwork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Testing network implementation");

        WeightedNetwork<String> hyperLoopNetwork = new WeightedNetwork<>(
                List.of(
                "Dublin",
                "Cork",
                "Limerick",
                "Galway",
                "Belfast")
        );

        hyperLoopNetwork.addLink("Cork", "Limerick", 98.0);
        hyperLoopNetwork.addLink("Limerick", "Dublin", 200.0);
        hyperLoopNetwork.addLink("Limerick", "Belfast", 300.0);
        hyperLoopNetwork.addLink("Dublin", "Belfast", 170.0);
        hyperLoopNetwork.addLink("Galway", "Limerick", 105.0);

        logger.info("\n\n" + String.valueOf(hyperLoopNetwork));
        logger.info("Network optimizer");
    }
}
