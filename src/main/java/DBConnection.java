import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static Connection conect = null;

    private static  final String URL = "jdbc:postgresql://localhost:5432/dbtest";
    private static  final String LOGIN = "postgres";
    private static  final String PASSWORD = "postgres";

    private DBConnection(){
    }

    public static Connection getDBConnectin() throws Exception {
        Class.forName("org.postgresql.Driver");
      if(conect == null) {
         return conect = DriverManager.getConnection(URL, LOGIN, PASSWORD);
      }
      return  conect;
    }

}





