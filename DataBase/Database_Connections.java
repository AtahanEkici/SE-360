package DataBase;
import java.awt.HeadlessException;import java.sql.Connection;import java.sql.DriverManager;import java.sql.SQLException;import javax.swing.JOptionPane;

public class Database_Connections 
{
    private static Connection con;
    
    public void getConnection() 
    {
        String desktop = System.getProperty("user.home") + "/Desktop";
        try
        {
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:"+desktop+"/SE360.db");
        JOptionPane.showMessageDialog(null, "Connection Established");   
        }catch(HeadlessException | ClassNotFoundException | SQLException e)
        {
        JOptionPane.showMessageDialog(null,""+e.getMessage()+"","ERROR ("+e.getClass()+")",JOptionPane.ERROR_MESSAGE);
        }
    }
}
