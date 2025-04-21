package com.tus.network.graph;

import com.tus.network.graph.link.WeightedLink;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

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

    public DjikstraResult shortestPath(N root){
        Integer rootIndex = getIndexOf(root);
        Double[] distances = new Double[getNodesCount()];
        distances[rootIndex] = 0.0;

        Boolean[] visited = new Boolean[getNodesCount()];
        visited[rootIndex] = true;

        HashMap<Integer, WeightedLink> path = new HashMap<>();
        PriorityQueue<DjikstraNode> queue = new PriorityQueue<>();
        queue.offer(new DjikstraNode(rootIndex, 0.0));

        while (!queue.isEmpty()){
            Integer node = queue.poll().node;
            Double distanceNode = distances[node];
            for (WeightedLink link : getLinksOf(node)){
                Double distTo = distances[link.to];
                Double newDist = distanceNode + link.weight;
                if (!visited[link.to] || (distTo > newDist)){
                    visited[link.to] = true;
                    distances[link.to] = newDist;
                    path.put(link.to, link);
                    queue.offer(new DjikstraNode(link.to, newDist));
                }
            }
        }
        return new DjikstraResult(distances, path);
    }

    public static final class DjikstraNode implements Comparable<DjikstraNode> {
        public final Integer node;
        public final Double distance;

        public DjikstraNode(Integer node, Double distance){
            this.node = node;
            this.distance = distance;
        }

        @Override
        public int compareTo(DjikstraNode o) {
            Double thisDistance = distance;
            Double otherDistance = o.distance;
            return thisDistance.compareTo(otherDistance);
        }
    }

    public static final class DjikstraResult {
        public final Double[] distances;
        public final HashMap<Integer, WeightedLink> path;

        public DjikstraResult(Double[] distances, HashMap<Integer, WeightedLink> path){
            this.distances = distances;
            this.path = path;
        }
    }
}
