package com.tus.network.graph;

import java.util.ArrayList;
import java.util.List;

public abstract class Graph <N, L extends Link> {
    private ArrayList<N> nodes = new ArrayList<>();
    protected ArrayList<ArrayList<L>> links = new ArrayList<>();

    public Graph(){
    }

    public Graph(List<N> nodes) {
        this.nodes.addAll(nodes);
        for (N node : nodes)  {
            links.add(new ArrayList<>());
        }
    }
}
