package programBunga;
import java.sql.*;
public class CreateTable {
public static void main (String[] args){
Connection conn = null;
 try {
    String userName = "root";
    String password = "";
    String url = "jdbc:mysql://localhost:3306/jdbc";
    Class.forName("com.mysql.jdbc.Driver").newInstance ();
    conn = DriverManager.getConnection(url, userName, password);
    Statement stmt =conn.createStatement();
    String tableName = "jdbc.db_bunga";
    stmt.executeUpdate ("CREATE TABLE " + tableName +
                       " (Id varchar(255) NOT NULL, " +
                       "nama varchar(255) default NULL, " +
                       "keterangan varchar(255) default NULL, " +
                       "jumlah int(5), PRIMARY KEY (Id));");
    stmt.close();
    conn.close();
 } catch (Exception e) {
   System.err.println (e.getMessage());
 }
}
}


