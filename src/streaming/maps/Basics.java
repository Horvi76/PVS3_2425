package streaming.maps;

import java.util.HashMap;
import java.util.Map;

public class Basics {

    public static void main(String[] args) {
        HashMap<String, String> countries = new HashMap<>();

        countries.put("USA", "Washington DC");
        countries.put("Germany", "Berlin");
        countries.put("Finland", "Helsinki");
        countries.put("Czech Republic", "Prague");
        System.out.println(countries.size());
        System.out.println(countries);

        System.out.println(countries.get("Finland"));
        System.out.println(countries.getOrDefault("Norway", "Country not found"));
        countries.put("France", "Paris");
        System.out.println(countries);
        countries.remove("France");
        System.out.println(countries);

        countries.replace("USA", "Detroit");
        System.out.println(countries);

        System.out.println(countries.containsKey("France"));
        System.out.println(countries.containsValue("Prague"));

        for (String country : countries.keySet()){
            System.out.println(country);
            System.out.println("Capital: " + countries.get(country));
        }

        for (Map.Entry<String, String> entry : countries.entrySet()){
            System.out.println("Coutry: " + entry.getKey() +
                    ", Capital: " + entry.getValue());
        }

        double averageNameLength = countries.values().stream()
                .mapToInt(String::length)
                .average()
                .orElse(0);
        System.out.println("Average capital name length: " + averageNameLength);
    }
}
