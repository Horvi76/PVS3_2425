package streaming.maps;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MovieReviews {
    static class Movie {
        private int movieID;
        private String title;
        private String genre;
        private double rating;

        public int getMovieID() {
            return movieID;
        }

        public String getTitle() {
            return title;
        }

        public String getGenre() {
            return genre;
        }

        public double getRating() {
            return rating;
        }

        public Movie(int movieID, String title, String genre) {
            this.movieID = movieID;
            this.title = title;
            this.genre = genre;
            this.rating = Double.NaN;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }
    }

    static class Rating {
        private int movieID;
        private double rating;

        public Rating(int movieID, double rating) {
            this.movieID = movieID;
            this.rating = rating;
        }

        public double getRating() {
            return rating;
        }

        public int getMovieID() {
            return movieID;
        }
    }

    public static void main(String[] args) throws IOException {
        List<Movie> movies = Files.lines(Paths.get("data\\reviews\\input.csv"))
                .skip(1)
                .map(line -> line.split(","))
                .map(parts -> new Movie(
                        Integer.parseInt(parts[0]),
                        parts[1],
                        parts[2]
                ))
                .toList();

        System.out.println("Nacteno filmu: " + movies.size());

        List<Rating> ratings = Files.lines(Paths.get("data\\reviews\\ratings.csv"))
                .skip(1)
                .map(line -> line.split(","))
                .map(parts -> new Rating(
                        Integer.parseInt(parts[1].trim()),
                        Double.parseDouble(parts[2].trim())
                ))
                .toList();
        System.out.println("Recenzi nacteno: " + ratings.size());

        //Terribly BAD IDEA:
//        int count;
//        double sum;
//        long start = System.currentTimeMillis();
//        for (Movie m : movies) {
//            count = 0;
//            sum = 0;
//            for (Rating r : ratings) {
//                if (r.movieID == m.movieID) {
//                    count++;
//                    sum += r.rating;
//                }
//            }
//            m.setRating((sum / count));
//        }
//        long stop = System.currentTimeMillis();
//        System.out.println("Duration: " + (stop - start) + " ms");
//        System.out.println(movies.get(0).getRating());
//        System.out.println(movies.get(movies.size() - 1).getRating());
//
//        movies.forEach(movie -> movie.setRating(Double.NaN));


        long start = System.currentTimeMillis();
        Map<Integer, List<Double>> ratingsMap = ratings.stream()
                .collect(Collectors.groupingBy(
                        Rating::getMovieID,
                        Collectors.mapping(Rating::getRating, Collectors.toList())
                ));

        movies.forEach(movie -> {
            List<Double> movieRatings = ratingsMap.get(movie.getMovieID());
            if (movieRatings != null && !movieRatings.isEmpty()) {
                double avg = movieRatings.stream()
                        .mapToDouble(Double::doubleValue)
                        .average()
                        .orElse(Double.NaN);
                movie.setRating(avg);
            }
        });
       long stop = System.currentTimeMillis();
        System.out.println("Map duration: " + (stop - start) + " ms");
//        movies.forEach(m -> System.out.println(m.getTitle() + ": " + m.getRating()));
    }
}
