import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AWSMovieProvider implements MovieProvider {

    Map<String, Movie> movieMap;

    AWSMovieProvider() {
        movieMap = new HashMap<>();
    }

    @Override
    public Collection<Movie> getAllMovies() {
        try {
            URL url = new URL("https://s3.amazonaws.com/GraphLab-Datasets/americanMovies/freebase_performances.csv");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(url.openStream()));

            long start = System.currentTimeMillis();

            String line = in.readLine();    // skip first line
            line = in.readLine();

            while (line != null) {
                String[] tokens = line.split(",");
                String actorName = tokens[1];
                String movieName = tokens[3];
                int year = -1;

                // for whatever reason if cannot parse the year value
                // skip this line
                try {
                    year = Integer.parseInt(tokens[4]);
                } catch (NumberFormatException e) {
                    line = in.readLine();
                    continue;
                }

                if (movieMap.get(movieName) == null) {
                    movieMap.put(movieName, new Movie(movieName, year));
                } else {
                    movieMap.get(movieName).addCastMember(actorName);
                }

                line = in.readLine();
            }

            long end = System.currentTimeMillis();
            System.out.println("Time to read from AWS : " + (end-start) + "ms");

            return movieMap.values();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
