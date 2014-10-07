import java.util.ArrayList;
import java.util.List;

public class Movie {

    String name;
    List<String> cast;
    int year;

    Movie(String name, int year) {
        this.name = name;
        this.year = year;
        cast = new ArrayList<>();   // we will be accessing it by the index so ArrayList is ideal
    }

    public void addCastMember(String name) {
        cast.add(name);
    }

    @Override
    public String toString() {
        return name + "," + year;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Movie))
            return false;
        else
            return this.name.equalsIgnoreCase(((Movie) other).name) &&
                    this.year == ((Movie) other).year;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + year;
    }
}
