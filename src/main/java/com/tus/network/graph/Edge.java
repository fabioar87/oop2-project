package com.tus.network.graph;

public class Edge {
    public final Integer from;
    public final Integer to;


    public Edge(Integer from, Integer to){
        this.from = from;
        this.to = to;
    }

    public Integer getFrom(){
        return from;
    }

    public Integer getTo(){
        return to;
    }

    @Override
    public String toString() {
        return getFrom() + "->" + getTo();
    }
}
