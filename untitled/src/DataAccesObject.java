import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class DataAccesObject {
    public static Connection data;
    public static Statement statement;
    public static ResultSet result;
    public static ArrayList<Object>countries=new ArrayList<>();
    public static ArrayList<Object>cities=new ArrayList<>();
    public static ArrayList<Object>attractions=new ArrayList<>();
    private static String escapeSql(String str) {
        if (str == null) {
            return null;
        }
        return str.replace("'", "''");
    }
    public static void connectDb() throws ClassNotFoundException, SQLException {
       data=null;
       Class.forName("org.sqlite.JDBC");
       data=DriverManager.getConnection("jdbc:sqlite:gazetee.db");
       if(data!=null){
           System.out.println("БД подключена");
           statement= data.createStatement();
       }
        else{
           System.out.println("Ошибка");
       }
    }
    public static void readCountries() throws SQLException {
        countries.clear();
        result=statement.executeQuery("SELECT country_name FROM countries");
        while(result.next()){
            Country country=new Country(result.getString("country_name"));
            countries.add(country);
        }
        System.out.println("Таблица со странами выгружена");
        for (int i=0; i<countries.size(); i++){
            System.out.println(i);
        }
    }
    public static void readCities() throws SQLException {
        result=statement.executeQuery("SELECT city_name, population FROM cities");
        while (result.next()){
            City city=new City(result.getString("city_name"), result.getInt("population"));
            cities.add(city);
        }
        System.out.println("Таблица с городами выгружена");
        for(int i=0; i< cities.size(); i++){
            System.out.println(i);
        }
    }
    public static void readAttractions() throws SQLException {
        result=statement.executeQuery("SELECT attraction_name FROM attractions");
        while (result.next()){
            Attraction attraction=new Attraction(result.getString("attraction_name"));
            attractions.add(attraction);
        }
        System.out.println("Таблица с достопримечательностями загружена");
        for(int i=0; i<attractions.size(); i++){
            System.out.println(i);
        }
    }
    public static  void addCountry() throws SQLException {
        String countryName=Controller.getInstance().getCountryName();
        int result=statement.executeUpdate("INSERT INTO countries (country_name) VALUES ('" + escapeSql(countryName) + "')");
        //readCountries();
    }
    public static void deleteCountryFromDb() throws SQLException {
        String countryName=Controller.getInstance().getCountryNameForDelete();
        int result=statement.executeUpdate("DELETE FROM countries WHERE country_name = '" + escapeSql(countryName) + "' ");
        System.out.println("Страна удалена");
    }
}
