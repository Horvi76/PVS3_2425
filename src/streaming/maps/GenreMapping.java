package streaming.maps;

import java.io.IOException;
import java.io.ObjectStreamField;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GenreMapping {

    static ArrayList<Movie> loadData(String path) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            ArrayList<Movie> movies = new ArrayList<>();

            String[] params;
            for (String line : lines) {
                params = line.split(";");
                movies.add(new Movie(
                        params[0],
                        Integer.parseInt(params[1]),
                        params[2],
                        Double.parseDouble(params[3])
                ));
            }
            return movies;
        } catch (IOException e) {
            System.out.println("Error in data load: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        ArrayList<Movie> movies = loadData("data\\movieList.txt");
//        System.out.println(movies);

        HashMap<String, List<Movie>> genreMap = new HashMap<>();

        for (Movie movie : movies) {
            if (genreMap.containsKey(movie.getGenre())) {
                genreMap.get(movie.getGenre()).add(movie);
            } else { //zanr jeste neni v keysetu
                ArrayList<Movie> newGenre = new ArrayList<>();
                newGenre.add(movie);
                genreMap.put(movie.getGenre(), newGenre);
            }
        }

        for (String genre : genreMap.keySet()) {
            System.out.println(genre + ":");
            for (Movie movie : genreMap.get(genre)) {
                System.out.println("|-" + movie);
            }
        }

        //mapa zanr - seznam NAZVU filmu
        Map<String, List<String>> alt = movies.stream()
                .collect(Collectors.groupingBy(Movie::getGenre,
                        Collectors.mapping(Movie::getName, Collectors.toList())));

        //mapa zanr - filmy jako celek
        Map<String, List<Movie>> alsoAnother = movies.stream()
                .collect(Collectors.groupingBy(Movie::getGenre));

        for (String genre : genreMap.keySet()) {
            System.out.println("Genre: " + genre);
            double average = genreMap.get(genre).stream()
                    .mapToDouble(Movie::getRating)
                    .average()
                    .orElse(0);
            System.out.println("Average: " + average);
        }

        Map<String, Double> avgRatingGenre = movies.stream()
                .collect(Collectors.groupingBy(
                        Movie::getGenre,
                        Collectors.averagingDouble(Movie::getRating)
                ));
        System.out.println(avgRatingGenre);

        //ekvivalentni postup
        movies.stream().collect(
                        Collectors.groupingBy(
                                Movie::getGenre,
                                Collectors.averagingDouble(Movie::getRating)
                        )
                )
                .forEach((genre, rating) ->
                        System.out.println("Genre: " + genre + ", rating: " + rating)
                );


        Map<String, List<Movie>> ratingCategories = movies.stream()
                .collect(Collectors.groupingBy(
                        movie -> {
                            if (movie.getRating() < 5){
                                return "Bad";
                            } else if (movie.getRating() < 7.5) {
                                return "Good";
                            } else {
                                return "Great";
                            }
                        }
                ));
    }
}

class Movie {
    String name;
    int year;
    String genre;
    double rating;

    public Movie(String name, int year, String genre, double rating) {
        this.name = name;
        this.year = year;
        this.genre = genre;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return name + " (" + year + ")";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}

