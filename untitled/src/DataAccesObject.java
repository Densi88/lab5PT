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
    public static ArrayList<Object>searchingCountries=new ArrayList<>();
    public static ArrayList<Object>searchingCities=new ArrayList<>();
    private static String escapeSql(String str) {
        if (str == null) {
            return null;
        }
        return str.replace("'", "''");
    }
    public static void connectDb() throws ClassNotFoundException, SQLException {
       data=null;
       Class.forName("org.sqlite.JDBC");
       data=DriverManager.getConnection("jdbc:sqlite:gazetee2.db");
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
    public static void addCity() throws SQLException {
        String countryName=Controller.getInstance().getCountryForCityName();
        String cityName=Controller.getInstance().getCityName();
        String findCountrySql = "SELECT id_ FROM countries WHERE country_name = ?";
        PreparedStatement findCountryStmt = data.prepareStatement(findCountrySql);
        findCountryStmt.setString(1, countryName);
        ResultSet resultSet=findCountryStmt.executeQuery();
        int countryId;
        if (resultSet.next()) {
            // Страна найдена - получаем её ID
            countryId = resultSet.getInt("id_");
        } else {
            // Страны нет - добавляем и получаем её ID
            String insertCountrySql = "INSERT INTO countries (country_name) VALUES (?)";
            PreparedStatement insertCountryStmt = data.prepareStatement(insertCountrySql,
                    Statement.RETURN_GENERATED_KEYS);
            insertCountryStmt.setString(1, countryName);
            insertCountryStmt.executeUpdate();

            ResultSet generatedKeys = insertCountryStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                countryId = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Не удалось получить ID добавленной страны");
            }
        }

        // 2. Добавляем город
        String insertCitySql = "INSERT INTO cities (city_name, country_id) VALUES (?, ?)";
        PreparedStatement insertCityStmt = data.prepareStatement(insertCitySql);
        insertCityStmt.setString(1, cityName);
        insertCityStmt.setInt(2, countryId);
        int rowsAffected = insertCityStmt.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Город успешно добавлен");
        } else {
            System.out.println("Не удалось добавить город");
        }
    }
    public static void deleteCity() throws SQLException {
        String cityName=Controller.getInstance().getCityForDelete();
        int result=statement.executeUpdate("DELETE FROM cities WHERE city_name = '" + escapeSql(cityName) + "' ");
        System.out.println("Город удален");
    }
    public static void deleteAttraction() throws SQLException {
        String attractionName=Controller.getInstance().getAttractionForDelete();
        int result=statement.executeUpdate("DELETE FROM attractions WHERE attraction_name = '" + escapeSql(attractionName) + "' ");
        System.out.println("Достопримечательность удалена");
    }
    public static  void addAttraction() throws SQLException {
        String cityName=Controller.getInstance().getCityForAddAttr();
        String attractionName=Controller.getInstance().getAttractionForAdd();
        String findCountrySql = "SELECT id_ FROM cities WHERE city_name = ?";
        PreparedStatement findCountryStmt = data.prepareStatement(findCountrySql);
        findCountryStmt.setString(1, cityName);
        ResultSet resultSet=findCountryStmt.executeQuery();
        int cityId;
        if (resultSet.next()) {
            // Страна найдена - получаем её ID
            cityId = resultSet.getInt("id_");
        } else {
            // Страны нет - добавляем и получаем её ID
            String insertCountrySql = "INSERT INTO cities (city_name) VALUES (?)";
            PreparedStatement insertCountryStmt = data.prepareStatement(insertCountrySql,
                    Statement.RETURN_GENERATED_KEYS);
            insertCountryStmt.setString(1, cityName);
            insertCountryStmt.executeUpdate();

            ResultSet generatedKeys = insertCountryStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                cityId = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Не удалось получить ID добавленной страны");
            }
        }

        // 2. Добавляем город
        String insertCitySql = "INSERT INTO attractions (attraction_name, city_id) VALUES (?, ?)";
        PreparedStatement insertCityStmt = data.prepareStatement(insertCitySql);
        insertCityStmt.setString(1, attractionName);
        insertCityStmt.setInt(2, cityId);
        int rowsAffected = insertCityStmt.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("достопримечательность добавлена");
        } else {
            System.out.println("Не удалось добавить достопримечательность ");
        }
    }
    public static void countrySearch() throws SQLException {
        searchingCountries.clear();
        String countryName=Controller.getInstance().getCountryForSearching();
        String sql = "SELECT c.city_name, c.population " +
                "FROM cities c " +
                "JOIN countries co ON c.country_id = co.id_ " +
                "WHERE co.country_name = ?";

        // Используем PreparedStatement для защиты от SQL-инъекций
        PreparedStatement pstmt = data.prepareStatement(sql);
        pstmt.setString(1, countryName);

        result = pstmt.executeQuery();
        while(result.next()) {
            City city=new City(result.getString("city_name"), result.getInt("population"));
            searchingCountries.add(city);
        }
    }
    public static void citySearch() throws SQLException {
        searchingCities.clear();
        String cityName=Controller.getInstance().getCityForSearching();
        String sql = "SELECT a.attraction_name " +
                "FROM attractions a " +
                "JOIN cities c ON a.city_id = c.id_ " +
                "WHERE c.city_name = ?";

        // Используем PreparedStatement для защиты от SQL-инъекций
        PreparedStatement pstmt = data.prepareStatement(sql);
        pstmt.setString(1, cityName);

        ResultSet result = pstmt.executeQuery();

        // Обработка результатов
        while(result.next()) {
            Attraction attraction = new Attraction(result.getString("attraction_name"));
            searchingCities.add(attraction);
        }
    }
}
