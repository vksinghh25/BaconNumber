import org.junit.Test;

import java.util.Collection;

public class AWSMovieProviderTest {
    @Test
    public void testGetAllMovies() throws Exception {

        MovieProvider movieProvider = new AWSMovieProvider();

        Collection<Movie> allMovies = movieProvider.getAllMovies();

        int count = 1;
        for (Movie movie : allMovies) {
            System.out.println(count++ + "." + "  " + movie);
        }
    }
}
