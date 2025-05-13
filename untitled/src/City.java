public class City {
    private String name;
    private int population;
    private String countryName;
    public City(String name, int population){
        this.name=name;
        this.population=population;
    }
    public int getPopulation(){
        return this.population;
    }
    public String getName(){
        return this.name;
    }
}
