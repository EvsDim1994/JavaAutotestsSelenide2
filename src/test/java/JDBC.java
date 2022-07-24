import java.sql.*;

public class JDBC {
    private static Connection connection = null;
    //ввести адрес БД + добавить драйвера через Open Module Setting
    private static String URL = "jdbc:postgresql://localhost/postgres";
    // Ввести логин и пароль от БД
    private static String Username = "postgres";
    private static String password = "111";


        Statement sql = null;
        ResultSet result = null;
        String value = "";
        String SQL = "";



        public void insert(String Lastname, String postcode) throws SQLException {
        try {
            System.out.println("Соединение с БД");
            connection = DriverManager.getConnection(URL,Username,password);

            //запрос на создание таблицы
            /*SQL = "CREATE TABLE Persons (PersonID int, FirstName varchar(255), City varchar(255));";*/

            //запрос на добавление в таблицу
            SQL = String.format("INSERT INTO Data (LastName, Post_Code)"
                    + "VALUES ('%s', '%s')", Lastname, postcode) ;

            sql = connection.createStatement();

            sql.execute(SQL);
        }  catch (Exception e){
            e.printStackTrace();
        }
        finally {
            connection.close();
            System.out.println("Соединение закрыто");
        }
    }
}
