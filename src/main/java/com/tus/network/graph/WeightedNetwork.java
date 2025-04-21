package com.tus.network.graph;

import java.util.Arrays;
import java.util.List;

public class WeightedNetwork<N> extends Network<N, WeightedLink> {
    public WeightedNetwork(List<N> nodes){
        super(nodes);
    }

    public void addLink(WeightedLink link){
        links.get(link.from).add(link);
        links.get(link.to).add(link.reverse());
    }

    public void addLink(Integer from, Integer to, Double weight){
        addLink(new WeightedLink(from, to, weight));
    }

    public void addLink(N from, N to, Double weight){
        addLink(getIndexOf(from), getIndexOf(to), weight);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getNodesCount(); i++) {
            sb.append(getNodeAt(i));
            sb.append(" --> ");
            sb.append(Arrays.toString(
                    getLinksOf(i).stream()
                            .map(link -> "(" + getNodeAt(link.to) + ", " + link.weight + ")").toArray()
            ));
            sb.append("\n");
        }
        return sb.toString();
    }
}
