import java.util.Collection;
import java.util.List;

public class BaconNumber {

    // test comment again	
    MovieProvider movieProvider;
    ActorsGraph graph;

    BaconNumber(MovieProvider movieProvider) {
        this.movieProvider = movieProvider;

        initGraph();
    }

    private void initGraph() {
        graph = new ActorsGraph();
        Collection<Movie> allMovies = movieProvider.getAllMovies();

        long start = System.currentTimeMillis();
        for (Movie movie : allMovies) {
            List<String> cast = movie.cast;
            for (int i = 0; i < cast.size(); i++) {
                for (int j = i + 1; j < cast.size(); j++) {
                    ActorNode actor1 = graph.addActor(cast.get(i));
                    ActorNode actor2 = graph.addActor(cast.get(j));

                    graph.addCoStar(actor1, actor2, movie);
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("Time to create the graph structure : " + (end-start) + "ms");
    }

    public String getBaconNumber(String name) {
        BFS bfs = new BFS(graph);
        return bfs.doBFS(name);
    }


}
