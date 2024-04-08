package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {

    public static Connection objConnection = null;

    public static Connection openConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/outlet";
            String user = "root";
            String password = "Rlwl2023.";

            //Establecer la conexión
            objConnection = DriverManager.getConnection(url, user, password);
            System.out.println("¡Me conecté perfectamente!");

        }catch (ClassNotFoundException e){
            System.out.println("ERROR > Driver no instalado " + e.getMessage());
        }catch (SQLException e){
            System.out.println("ERROR > al conectar la base de datos " + e.getMessage());
        }

        return objConnection;
    }

    public static void closeConnection(){
        try {
            if (objConnection != null){
                objConnection.close();
                System.out.println("Se finalizó la conexión con éxito");
            }
        }catch (SQLException e){
            System.out.println("ERROR" + e.getMessage());
        }
    }
}
