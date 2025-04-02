package com.tus.network.graph;

public class Edge {
    public final Integer from;
    public final Integer to;

    public Edge(Integer from, Integer to){
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return from + "->" + to;
    }
}
