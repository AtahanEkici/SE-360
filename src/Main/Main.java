package Main;

import DataBase.Database_Connections;

public class Main 
{   
    public static void main(String[] args)
    {
        Database_Connections db = new Database_Connections();
        
        db.getConnection();
        
        System.out.println(db.getAllTableNames());
        
        System.out.println(db.getAllTableNumber());
    }  
}
