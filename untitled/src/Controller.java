import java.sql.SQLException;

public class Controller {
    private CitiesFrame citiesFrame;
    private CountriesFrame countriesFrame;
    private AttractionsFrame attractionsFrame;
    private static Controller instance;
    private AddCountryFrame addCountryFrame;
    private DeleteCountryFrame deleteCountryFrame;
    private AddCityFrame addCityFrame;
    private DeleteCityFrame deleteCityFrame;
    private DeleteAttractionFrame deleteAttractionFrame;
    private AddAttractionFrame addAttractionFrame;
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
    public void createAddCityFrame(){
        if(addCityFrame==null){
            addCityFrame=new AddCityFrame();
        }
        else addCityFrame.setVisible(true);
    }
    public void closeAddCityFrame(){
        addCityFrame.setVisible(false);
    }
    public String getCityName(){
        String cityName=addCityFrame.nameField.getText();
        return cityName;
    }
    public String getCountryForCityName(){
        String countryName=addCityFrame.countryNameField.getText();
        return countryName;
    }
    public void addCityToDb() throws SQLException {
        clearCitiesTable();
        DataAccesObject.addCity();
        DataAccesObject.cities.clear();
        DataAccesObject.readCities();
        fillCitiesTable();
    }
    public void createDeleteCityFrame(){
        if(deleteCityFrame==null){
            deleteCityFrame=new DeleteCityFrame();
        }
        else{
            deleteCityFrame.setVisible(true);
        }
    }
    public void closeDeleteCityFrame(){
        deleteCityFrame.setVisible(false);
    }
    public String getCityForDelete(){
        return deleteCityFrame.nameField.getText();
    }
    public void deleteCityFromDb() throws SQLException {
        clearCitiesTable();
        DataAccesObject.deleteCity();
        DataAccesObject.cities.clear();
        DataAccesObject.readCities();
        fillCitiesTable();
    }
    public void createDeleteAttractionFrame(){
        if(deleteAttractionFrame==null){
            deleteAttractionFrame=new DeleteAttractionFrame();
        }
        else{
            deleteAttractionFrame.setVisible(true);
        }
    }
    public void closeDeleteAttractionFrame(){
        deleteAttractionFrame.setVisible(false);
    }
    public String getAttractionForDelete(){
        return deleteAttractionFrame.nameField.getText();
    }
    public void deleteAttractionFromDb() throws SQLException {
        clearAttractionsTable();
        DataAccesObject.deleteAttraction();
        DataAccesObject.attractions.clear();
        DataAccesObject.readAttractions();
        fillAttractionsTable();
    }
    public void createAddAttractionFrame(){
        if(addAttractionFrame==null){
            addAttractionFrame=new AddAttractionFrame();
        }
        else{
            addAttractionFrame.setVisible(true);
        }
    }
    public void closeAddAttractionFrame(){
        addAttractionFrame.setVisible(false);
    }
    public String getAttractionForAdd(){
        return addAttractionFrame.nameField.getText();
    }
    public String getCityForAddAttr(){
        return addAttractionFrame.cityNameField.getText();
    }
    public void addAttractionToDb() throws SQLException {
        clearAttractionsTable();
        DataAccesObject.addAttraction();
        DataAccesObject.attractions.clear();
        DataAccesObject.readAttractions();
        fillAttractionsTable();
    }
    public String getCountryForSearching(){
        return citiesFrame.searchingCountryField.getText();
    }
    public String getCityForSearching(){
        return attractionsFrame.searchingCityField.getText();
    }

    public void fillSearchingCities(){
        for (Object obj : DataAccesObject.searchingCountries) {
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
    public void fillSearchingAttractions(){
        for (Object obj : DataAccesObject.searchingCities) {
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
}
