package DataBase;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Database_Connections 
{
    private static Connection con;
    
    public void getConnection() 
    {
        final String desktop = System.getProperty("user.home");
        try
        {
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:"+desktop+"/Desktop/SE360.db");
        //System.out.println("Connection Established");  
        }
        catch(HeadlessException | ClassNotFoundException | SQLException e)
        {
        //System.out.println("Connection Failure"); 
        JOptionPane.showMessageDialog(null,""+e.getMessage()+"","CONNECTION ERROR ("+e.getClass()+")",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void closeConnection()
    {
        if(con != null)
        {
            try 
            {
                con.close();
                //System.out.println("Connection Closed");
            } 
            catch (SQLException e) 
            {
                JOptionPane.showMessageDialog(null,""+e.getMessage()+"","ERROR Closing Connection ("+e.getClass()+")",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public ArrayList<String> getAllTableNames()
    {
        ArrayList<String> list = new ArrayList<>();
        try 
        {
            final String tables = "SELECT name FROM sqlite_master WHERE type='table';";
            
            if(con == null || con.isClosed())  // If the connnection is closed or does not exist //
            {
                //System.out.println("Connection is down");
                this.getConnection();
            }
            
            final Statement st = con.createStatement();
            final ResultSet rs = st.executeQuery(tables);
            
            while(rs.next() == true)
            {
                list.add(rs.getString(1));
            }
        } 
        catch (SQLException e) 
        {
           JOptionPane.showMessageDialog(null,""+e.getMessage()+"","ERROR ("+e.getClass()+")",JOptionPane.ERROR_MESSAGE);
        }
        this.closeConnection();
        return list;
    }
    
    public int getAllTableNumber()
    {
        int result = 0;
         try 
        {
            final String tables = "SELECT COUNT(DISTINCT name) from sqlite_master where type='table';";
            
            if(con == null || con.isClosed()) // If the connnection is closed or does not exist //
            {
                //System.out.println("Connection is down");
                this.getConnection();
            }
            
            final Statement st = con.createStatement();
            final ResultSet rs = st.executeQuery(tables);
            
            while(rs.next() == true)
            {
                result = rs.getInt(1);
            }
        } 
        catch (SQLException e) 
        {
           JOptionPane.showMessageDialog(null,""+e.getMessage()+"","ERROR ("+e.getClass()+")",JOptionPane.ERROR_MESSAGE);
        }
        this.closeConnection();
        return result;
    }
    
    public String getAllIndexes()
    {
        String indexes = "";
        try 
        {
            final String tables = "SELECT * FROM sqlite_master WHERE type = 'index';";
            
            if(con == null || con.isClosed())  // If the connnection is closed or does not exist //
            {
                //System.out.println("Connection is down");
                this.getConnection();
            }
            
            final Statement st = con.createStatement();
            final ResultSet rs = st.executeQuery(tables);
            
            while(rs.next() == true)
            {
                indexes += rs.getString(1);
            }
        } 
        catch (SQLException e) 
        {
           JOptionPane.showMessageDialog(null,""+e.getMessage()+"","ERROR ("+e.getClass()+")",JOptionPane.ERROR_MESSAGE);
        }
        this.closeConnection();
        return indexes;
    }
    
    public void createIndex(String Index_name, String table_name,String column_name)
    { 
       final String Indexing = "CREATE INDEX IF NOT EXISTS "+Index_name+" ON "+table_name+"("+column_name+")".trim();
        
        try 
        {
            
        if(con == null || con.isClosed())  // If the connnection is closed or does not exist //
        {
            //System.out.println("Connection is down");
            this.getConnection();
        }    
       
        System.out.println(Indexing);
        Statement index_statement = con.createStatement();       
        index_statement.executeQuery(Indexing); // execute the manipulated string //
   
        } catch (SQLException e) 
        {
           JOptionPane.showMessageDialog(null,""+e.getMessage()+"","ERROR ("+e.getClass().getSimpleName()+")",JOptionPane.ERROR_MESSAGE);         
    }   
    }
    
    public void createIndex(String Index_name, String table_name)
    { 
       final String Indexing = "CREATE INDEX IF NOT EXISTS "+Index_name+" ON "+table_name+"".trim();
        
        try 
        {
            
        if(con == null || con.isClosed())  // If the connnection is closed or does not exist //
        {
            //System.out.println("Connection is down");
            this.getConnection();
        }    
       
        System.out.println(Indexing);
        Statement index_statement = con.createStatement();       
        index_statement.executeQuery(Indexing); // execute the manipulated string //
   
        } catch (SQLException e) 
        {
           JOptionPane.showMessageDialog(null,""+e.getMessage()+"","ERROR ("+e.getClass().getSimpleName()+")",JOptionPane.ERROR_MESSAGE);         
    }   
    }
}
