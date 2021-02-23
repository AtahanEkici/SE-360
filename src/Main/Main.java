package Main;

import DataBase.Database_Connections;
import UI.UI;

public class Main 
{   
    public static void main(String[] args)
    {
        Database_Connections db = new Database_Connections();
        
        UI.getInstance();
        
        System.out.println(db.getAllTableNames());
        
        System.out.println(db.getAllTableNumber());
    }  
}
