package travellingPoliceman;

public class Report {
    private String name;
    private int pokemons;
    private int carDamage;
    private int points;
    private int fuel;

    public Report(String name, int carDamage, int pokemons, int fuel) {
        this.name = name;
        this.pokemons = pokemons;
        this.carDamage = carDamage;
        this.fuel = fuel;
        this.points = pokemons * 10 - carDamage;
    }

    public String getName() {
        return name;
    }

    public int getPokemons() {
        return pokemons;
    }

    public int getCarDamage() {
        return carDamage;
    }

    public int getPoints() {
        return points;
    }

    public int getFuel() {
        return fuel;
    }
}