package exams;

import fileworks.DataImport;

import java.lang.reflect.Array;
import java.util.*;

public class CityListing {

    static HashSet<City> loadData(String filePath){
        String line;
        String[] params;
        HashSet<City> cities = new HashSet<>();
        DataImport di = new DataImport(filePath);
        while ((line = di.readLine())!=null){
            params = line.split(",");
            cities.add(
                    new City(params[0], params[1], params[2])
            );
        }
        di.finishImport();
        return cities;
    }

    static int getUniqueCountriesCount(ArrayList<City> cities){
        HashSet<String> countries = new HashSet<>();
        for (City city : cities){
            countries.add(city.country);
        }
        return countries.size();
    }

    static void printCities(String country, ArrayList<City> cities){
        System.out.println(country);
        for (City city : cities){
            if (city.country.equals(country))
                System.out.println("|- " + city.name);
        }
    }

//    static void printCountries(String continent, ArrayList<City> cities){
//        System.out.println(continent);
//        for (City city : cities){
//            if (city.continent.equals(continent))
//                System.out.println("|- " + city.country);
//                printCities(city.country, cities);
//        }
//    }

    static Queue<City> getClose(City city,ArrayList<City> cities){
        Queue<City> closeCities = new LinkedList<>();
        ArrayList<City> sameCountry = new ArrayList<>();
        for (City c : cities){
            if (c.country.equals(city.country))
                sameCountry.add(city);
        }
        System.out.println(closeCities);

        ArrayList<City> sameContinent = new ArrayList<>();
        for (City c : cities){
            if (c.continent.equals(city.continent))
                sameContinent.add(city);
        }

        System.out.println(closeCities);
        sameContinent.removeAll(sameCountry);
        System.out.println(closeCities);


        ArrayList<City> others = new ArrayList<>(cities);
        others.removeAll(sameContinent);
        others.removeAll(sameCountry);

        closeCities.addAll(sameCountry);
        closeCities.addAll(sameContinent);
        closeCities.addAll(others);
        return closeCities;
    }

    public static void main(String[] args) {
        //Part 1
        HashSet<City> uniqueCities = loadData("Cities.txt");
        System.out.println(uniqueCities.size());

        //Part 2
        ArrayList<City> cities = new ArrayList<>(uniqueCities);
        cities.sort(City.BY_CONTINENT);
        System.out.println(cities);

        //Part 3
        System.out.println("Country count: " + getUniqueCountriesCount(cities));

        //Part 4
        printCities("Finland", cities);

        System.out.println(getClose(cities.get(0), cities));

    }
} class City{
    String name, country, continent;
    static final Comparator<City> BY_CONTINENT = new Comparator<City>() {
        @Override
        public int compare(City o1, City o2) {
            return o1.continent.compareTo(o2.continent);
        }
    };


    public City(String name, String country, String continent) {
        this.name = name;
        this.country = country;
        this.continent = continent;
    }

    @Override
    public String toString() {
        return name + " - " + country + ", " + continent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(name, city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
class File{
    String path;

    public File(String path) {
        this.path = path;
    }
}
class SpecificFile extends File{
    public SpecificFile(String path) {
       super(path);
    }
}