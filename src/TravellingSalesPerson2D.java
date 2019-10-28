import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TravellingSalesPerson2D {

    /*
    Try to use a genetic algorithm implementation to solve the TSP problem.
     */

    private static Deadline deadline;

    public static void main(String[] args) {

        List<City> cities = readInputValues();
        deadline = new Deadline();
        // CityService.printCities(cities);
        getBestCityPath(cities);

    }

    private static void getBestCityPath(List<City> cities) {

        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
        geneticAlgorithm.generateInitialPopulation(cities);
        double fitnessValue = Integer.MAX_VALUE;
        double fitness = Integer.MAX_VALUE;
        int maxItrs =0;
        do {
            fitnessValue = fitness;
            geneticAlgorithm.selection();
            geneticAlgorithm.evaluatePopulation();
            geneticAlgorithm.sortPopulation();
            geneticAlgorithm.crossOver();
            geneticAlgorithm.mutation();
            geneticAlgorithm.evaluateOffSpring();
            geneticAlgorithm.sortOffspring();
            fitness = geneticAlgorithm.getBestFitnessValue();
            System.out.println("Fitness..."+fitness);

            if(fitness==fitnessValue) {
                maxItrs++;
            } else {
                maxItrs = 0;
            }
        }while(fitness<=fitnessValue && maxItrs<Constants.MAX_ITRS && Math.abs(deadline.remainingMs())<Constants.MAX_MS);

        Chromosome best = geneticAlgorithm.getBest();
        for(City city:best.getGenes()) {

            System.out.println(cities.indexOf(city));
        }
    }

    private static List<City> readInputValues() {

        List<City> cities;
        Scanner readInput = new Scanner(System.in);
        int size = Integer.parseInt(readInput.nextLine());
        cities = new ArrayList<>(size);
        String[] cityCoordinates;
        City city;
        while (size > 0) {
            cityCoordinates = readInput.nextLine().split(" ");
            city = new City(Double.parseDouble(cityCoordinates[0]),Double.parseDouble(cityCoordinates[1]));
            cities.add(city);
            size--;
        }
        return cities;
    }
}
