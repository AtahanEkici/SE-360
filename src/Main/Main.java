package Main;

import DataBase.Database_Connections;import UI.UI;

public class Main 
{   
    public static void main(String[] args)
    {   
        UI.getInstance();
        
        Database_Connections.createIndex("index_1","sqlite_master"); // There is a mistake in indexing that needs fixing //
        
        //System.out.println(db.getAllTableNumber()); 
    }  
}
