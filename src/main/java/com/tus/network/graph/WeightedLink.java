package com.tus.network.graph;

public class WeightedLink extends Link implements Comparable<WeightedLink> {
    public final Double weight;

    public WeightedLink(Integer from, Integer to, Double weight){
        super(from, to);
        this.weight = weight;
    }

    @Override
    public WeightedLink reverse() {
        return new WeightedLink(to, from, weight);
    }

    @Override
    public int compareTo(WeightedLink o) {
        Double thisWeight = weight;
        Double otherWeight = o.weight;
        return thisWeight.compareTo(otherWeight);
    }

    @Override
    public String toString() {
        return getFrom() + "->" + getTo() + " [" + weight + "]";
    }
}
