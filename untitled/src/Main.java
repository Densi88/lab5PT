import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MainMenu mainMenu=new MainMenu();
        DataAccesObject.connectDb();
        DataAccesObject.readCountries();
        DataAccesObject.readAttractions();
        DataAccesObject.readCities();
    }
}