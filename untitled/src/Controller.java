import java.sql.SQLException;

public class Controller {
    private CitiesFrame citiesFrame;
    private CountriesFrame countriesFrame;
    private AttractionsFrame attractionsFrame;
    private static Controller instance;
    private AddCountryFrame addCountryFrame;
    private DeleteCountryFrame deleteCountryFrame;
    public static  Controller getInstance(){
        if(instance==null){
            instance=new Controller();
        }
        return instance;
    }
    public void createCitiesWindow(){
        if(citiesFrame==null){
            citiesFrame=new CitiesFrame();
        }
            citiesFrame.setVisible(true);
    }
    public void createCountriesWindow(){
        if(countriesFrame==null){
            countriesFrame=new CountriesFrame();
        }
        countriesFrame.setVisible(true);
    }
    public void createAttractionWindow(){
        if(attractionsFrame==null){
            attractionsFrame=new AttractionsFrame();
        }
        attractionsFrame.setVisible(true);
    }
    public void closeCountriesFrame(){
        countriesFrame.setVisible(false);
    }
    public void closeCitiesFrame(){
        citiesFrame.setVisible(false);
    }
    public void closeAttractionsFrame(){
        attractionsFrame.setVisible(false);
    }
    public void fillCountriesTable(){
        for (Object obj : DataAccesObject.countries) {
            if (obj instanceof Country) {
                Country country = (Country) obj;
                String countryName = country.getCountryName();
                System.out.println(countryName);
               countriesFrame.countryTable.addRow(new Object[]{countryName});
            } else {
                System.out.println("Элемент не является объектом Country");
            }
        }
    }
    public void clearCountriesTable(){
        countriesFrame.countryTable.setRowCount(0);
    }
    public void fillAttractionsTable(){
        for (Object obj : DataAccesObject.attractions) {
            if (obj instanceof Attraction) {
                Attraction attraction = (Attraction) obj;
                String attractionName = attraction.getAttractionName();
                System.out.println(attractionName);
                attractionsFrame.attractions.addRow(new Object[]{attractionName});
            } else {
                System.out.println("Элемент не является объектом Country");
            }
        }
    }
    public void clearAttractionsTable(){
        attractionsFrame.attractions.setRowCount(0);
    }
    public void fillCitiesTable(){
        for (Object obj : DataAccesObject.cities) {
            if (obj instanceof City) {
                City city = (City) obj;
                String cityName = city.getName();
                int cityPopulation=city.getPopulation();
                System.out.println(cityName);
                citiesFrame.cities.addRow(new Object[]{cityName, cityPopulation});
            } else {
                System.out.println("Элемент не является объектом City");
            }
        }
    }
    public void clearCitiesTable(){
        citiesFrame.cities.setRowCount(0);
    }
    public void createAddCountryFrame(){
        if(addCountryFrame==null){
            addCountryFrame=new AddCountryFrame();
        }
        else{
            addCountryFrame.setVisible(true);
        }
    }
    public void closeAddCountryFrame(){
        addCountryFrame.setVisible(false);
    }
    public String getCountryName(){
        String countryName=addCountryFrame.nameField.getText();
        return countryName;
    }
    public void addCountryToDb() throws SQLException {
        DataAccesObject.addCountry();
        clearCountriesTable();
        DataAccesObject.readCountries();
        fillCountriesTable();

    }
    public void createDeleteCountryFrame(){
        if(deleteCountryFrame==null){
            deleteCountryFrame=new DeleteCountryFrame();
        }
        else{
            deleteCountryFrame.setVisible(true);
        }
    }
    public void closeDeleteCountryFrame(){
        deleteCountryFrame.setVisible(false);
    }
    public String getCountryNameForDelete(){
        String result=deleteCountryFrame.nameField.getText();
        return result;
    }
    public void deleteCountry() throws SQLException {
        DataAccesObject.deleteCountryFromDb();
        clearCountriesTable();
        DataAccesObject.readCountries();
        fillCountriesTable();
    }
}
