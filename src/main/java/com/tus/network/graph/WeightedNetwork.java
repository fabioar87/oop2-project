package com.tus.network.graph;

import com.tus.network.graph.link.WeightedLink;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.StringTemplate.STR;

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

// TODO: In this particular case, the WeightedLink class needs to be converted to a record
//    public void addLink(Object link){
//        if(link instanceof WeightedLink(var from, var to, var weight)) {
//            links.get(from).add(new WeightedLink(from, to, weight));
//            links.get(to).add(new WeightedLink(to, from, weight));
//        } else {
//            throw new IllegalArgumentException("Invalid link type");
//        }
//    }

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
        return switch (getIndexOf(root)){
            case Integer rootIndex when rootIndex >= 0 -> {
                double[] distances = new double[getNodesCount()];
                Arrays.fill(distances, Double.POSITIVE_INFINITY);
                distances[rootIndex] = 0.0;

                boolean[] visited = new boolean[getNodesCount()];
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
                yield new DjikstraResult(distances, path);
            }
            case null -> throw new IllegalArgumentException(STR."Node \{root} not found in the network");
            default -> throw new IllegalArgumentException(STR."Invalid node index for \{root}");
        };
    }

    public static record DjikstraNode(Integer node, Double distance)
        implements Comparable<DjikstraNode> {

        @Override
        public int compareTo(DjikstraNode o) {
            return distance.compareTo(o.distance);
        }
    }

    public static record DjikstraResult(double[] distances, HashMap<Integer, WeightedLink> path) {
        public Optional<Double> getDistanceTo(Integer node){
            return switch(this) {
                case DjikstraResult(var dist, var p)
                    when node >= 0 && node < dist.length -> Optional.of(dist[node]);
                default -> Optional.empty();
            };
        }
    }

    // Method to compile the results of the Dijkstra algorithm
    public Map<N, Double> compileResults(double[] distances){
//  Note: original implementation
//        HashMap<N, Double> result = new HashMap<>();
//        for (int i = 0; i < distances.length; i++) {
//            result.put(getNodeAt(i), distances[i]);
//        }
//        return result;
//  Using modern Java Instream feature:
        return IntStream.range(0, distances.length)
                .boxed()
                .collect(Collectors.toMap(
                        this::getNodeAt,
                        i -> distances[i],
                        (a,b) -> a,
                        HashMap::new
                ));
    }

    public static List<WeightedLink> optimizedPath(Integer start, Integer end, Map<Integer, WeightedLink> path){
        if(path.size() == 0) return List.of();

        LinkedList<WeightedLink> result = new LinkedList<>();
        WeightedLink link = path.get(end);
        result.add(link);
        while (link.from != start){
            link = path.get(link.from);
            result.add(link);
        }
        Collections.reverse(result);
        return result;
    }
}
