import java.util.*;

public class BFS {

    ActorsGraph graph;
    Map<ActorNode, ActorNode> parentMap;

    BFS(ActorsGraph graph) {
        this.graph = graph;
        this.parentMap = new HashMap<>();
    }

    public String doBFS(String name) {

        Set<ActorNode> discovered = new HashSet<>();

        Queue<ActorNode> queue = new LinkedList<>();
        ActorNode startNode = graph.getActor(name);

        discovered.add(startNode);
        queue.add(startNode);
        ActorNode front = null;

        long start = System.currentTimeMillis();
        while (!queue.isEmpty()) {
            front = queue.remove();
            if (front.name.equalsIgnoreCase("Kevin Bacon"))
                break;

            Set<ActorNode> coStars = graph.getCoStars(front);
            for (ActorNode coStar : coStars) {
                if (!discovered.contains(coStar)) {
                    parentMap.put(coStar, front);
                    queue.add(coStar);
                    discovered.add(coStar);
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("Time to run BFS : " + (end - start) + "ms");

        StringBuilder response = new StringBuilder();

        ActorNode parent = parentMap.get(front);
        int count = 0;

        while (parent != null) {
            response.append(front.name).append("::").append(parent.name).append("::")
                    .append(graph.getMovieCoStarredIn(front, parent).name).append("::")
                    .append(graph.getMovieCoStarredIn(front, parent).year);
            response.append(";;");

            front = parent;
            parent = parentMap.get(front);
            count++;
        }

        response.append(count);

        return response.toString();
    }


}
