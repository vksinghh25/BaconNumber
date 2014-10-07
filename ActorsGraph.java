import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ActorsGraph {

    private Map<String, ActorNode> allActors;
    private Map<ActorNode, Set<ActorNode>> coStars;
    private Map<ActorNode, Map<ActorNode, Movie>> actedTogetherIn;

    ActorsGraph() {
        allActors = new HashMap<>();
        coStars = new HashMap<>();
        actedTogetherIn = new HashMap<>();
    }

    public ActorNode getActor(String name) {
        return allActors.get(name);
    }

    public Set<ActorNode> getAllActors() {
        return new HashSet<>(allActors.values());
    }

    public ActorNode addActor(String name) {
        if (!(allActors.containsKey(name))) {
            ActorNode node = new ActorNode(name);
            allActors.put(name, node);
            coStars.put(node, new HashSet<>());
            actedTogetherIn.put(node, new HashMap<>());
            return node;
        } else {
            return allActors.get(name);
        }
    }

    public void addCoStar(ActorNode actorNode1, ActorNode actorNode2, Movie movie) {
        coStars.get(actorNode1).add(actorNode2);
        coStars.get(actorNode2).add(actorNode1);

        actedTogetherIn.get(actorNode1).put(actorNode2, movie);
        actedTogetherIn.get(actorNode2).put(actorNode1, movie);
    }

    public Movie getMovieCoStarredIn(ActorNode actorNode1, ActorNode actorNode2) {
        return actedTogetherIn.get(actorNode1).get(actorNode2);
    }

    public Set<ActorNode> getCoStars(ActorNode actorNode) {
        return coStars.get(actorNode);
    }

}
