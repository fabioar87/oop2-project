package com.tus.network.graph;

import com.tus.network.graph.link.Link;

import java.util.List;

public class ConnectivityNetwork<N> extends Network<N, Link> {
    public ConnectivityNetwork(List<N> nodes){
        super(nodes);
    }

    // The connectivity network is an undirected graph
    // Links are added in both directions
    public void addLink(Link link){
        links.get(link.from).add(link);
        links.get(link.to).add(link.reverse());
    }

    public void addLink(Integer from, Integer to){
        addLink(new Link(from, to));
    }
}
