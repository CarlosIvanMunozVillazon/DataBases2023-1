package MainPackage;

import java.sql.*;

public class DataBaseManager {

    private static final String DATA_BASE_NAME = "CRUD.db";
    private static final String CONNECTION_STRING =
            "jdbc:sqlite:/home/carlosivan/Escritorio/JavaProgramming2023/UdemyCourse/CRUDTestSQLite3/" + DATA_BASE_NAME;

    //GENERAL SQLITE3 connection String beginning = "jdbc:sqlite: + DataBase path"
    private static Connection connection;
    public static void initializeDataBase(){

        try {
            connection = DriverManager.getConnection(CONNECTION_STRING);

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void registerPerson(int cc, String name, String lastName, int edad){

        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Persona (cc, Nombre, Apellido, " +
                    "edad) VALUES (?,?,?,?)");
            statement.setInt(1,cc);
            statement.setString(2,name);
            statement.setString(3,lastName);
            statement.setInt(4,edad);

            statement.execute();
            statement.close();

        }catch (SQLException e){
            System.out.println("Insert Exception: " + e.getMessage());
        }

    }

    public static void deletePerson(int cc){
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Persona WHERE cc = (?)");
            statement.setInt(1, cc);
            statement.execute();
            statement.close();
            System.out.println("Persona " + cc + " ha sido borrada.");
        } catch (SQLException e){
            System.out.println("Delete Exception " + e.getMessage());
        }
    }

    public static void peekPerson(int cc){
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Persona WHERE cc = (?)");
            statement.setInt(1,cc);
            ResultSet resultSet = statement.executeQuery();
            System.out.println(resultSet.getInt("cc")+ " " + resultSet.getString("Nombre") + " " +
                    resultSet.getString("Apellido") + " " + resultSet.getInt("Edad"));
            statement.close();
        }catch (SQLException e){
            System.out.println("Peek Exception: " + e.getMessage());
        }
    }

    public static void updatePerson(int cc, String newName, String attribute){
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE Persona SET " + attribute + "=" +
                    "'"+ newName + "'" + //The new attribute value has to be between "' '" * single quotes.
                      " WHERE cc = (?)");
            statement.setInt(1,cc);
            statement.execute();
            statement.close();
        } catch (SQLException e){
            System.out.println("Update Exception: " + e.getMessage());
        }
    }

    public static void updatePerson(int cc, int newAge){
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE Persona SET Edad="+ newAge
                    + " WHERE cc = (?)");
            statement.setInt(1,cc);
            statement.execute();
            statement.close();
        } catch (SQLException e){
            System.out.println("Update Exception: " + e.getMessage());
        }
    }

}
