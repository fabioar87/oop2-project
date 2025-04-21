package com.tus.network.graph;

import com.tus.network.graph.link.Link;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Network<N, L extends Link> {
    private ArrayList<N> nodes = new ArrayList<>();
    protected ArrayList<ArrayList<L>> links = new ArrayList<>();

    public Network(){
    }

    public Network(List<N> nodes) {
        this.nodes.addAll(nodes);
        for (N node : nodes)  {
            links.add(new ArrayList<>());
        }
    }

    public Integer getNodesCount(){
        return nodes.size();
    }

    public Integer getLinksCount(){
        return links.stream().mapToInt(ArrayList::size).sum();
    }

    public Integer addNode(N node){
        nodes.add(node);
        links.add(new ArrayList<>());
        return nodes.size() - 1;
    }

    public N getNodeAt(Integer index){
        return nodes.get(index);
    }

    public Integer getIndexOf(N node){
        return nodes.indexOf(node);
    }

    // Returns all the nodes that a node at some index points to
    // Returns a local neighbourhood of a node
    public List<N> getNeighborsOf(Integer index){
        return links.get(index).stream()
                .map(link -> getNodeAt(link.to))
                .collect(Collectors.toList());
    }

    // Returns all the nodes that a node points to
    // Look up the index of the node and call getNeighborsOf()
    public List<N> getNeighborsOf(N node){
        return getNeighborsOf(getIndexOf(node));
    }

    // Returns all the links associated with a node
    public List<L> getLinksOf(Integer index){
        return links.get(index);
    }

    public List<L> getLinksOf(N node){
        return getLinksOf(getIndexOf(node));
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getNodesCount(); i++) {
            sb.append(getNodeAt(i));
            sb.append(" --> ");
            sb.append(Arrays.toString(getNeighborsOf(i).toArray()));
            sb.append("\n");
        }
        return sb.toString();
    }
}
