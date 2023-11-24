import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {




//    static Connection conn = null;
//    static String url = " jdbc:h2:~/test";
//    static String user = "sa";
//    static String password = "1234";

    public static Connection connection;
    private static final String url = "jdbc:mysql://localhost:3306/fruitshop";
    private static final String user = "root";
    private static final String password = "";

    static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(url, user, password);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }


}
