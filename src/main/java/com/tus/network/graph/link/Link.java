package com.tus.network.graph.link;

public class Link {
    public final Integer from;
    public final Integer to;

    public Link(Integer from, Integer to){
        this.from = from;
        this.to = to;
    }

    public Link reverse(){
        return new Link(to, from);
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
