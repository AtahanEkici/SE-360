package Support;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class SupportingFunctions 
{
    private static double getFileSizeInMegaBytes(File file) 
    {
		return (double) file.length() / (1048576);
	}
    
    public static String ReadFile() throws IOException
    {
       String content = null;
        
        try
            {
               JFileChooser fileChooser;
               fileChooser = new JFileChooser(System.getProperty("user.home") +"/Desktop");
               fileChooser.showOpenDialog(null);
               File selectedFile = fileChooser.getSelectedFile();
               double size = getFileSizeInMegaBytes(selectedFile);
               
               if(size > 10)
               {
                    JOptionPane.showMessageDialog( null, "File is too large: "+(int)size+" MB","ERROR",JOptionPane.ERROR_MESSAGE);
                    return "";
               }
               
               StringBuilder stringBuilder;
               
                try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) 
                {
                    stringBuilder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null)
                    {
                        stringBuilder.append(line);
                    }
                    content = stringBuilder.toString().trim();
                }              
            }
        catch(Exception e)
            {
                if(e instanceof NullPointerException)
                {
                    JOptionPane.showMessageDialog( null, "No File was selected ", "ERROR!", JOptionPane. ERROR_MESSAGE);
                }
                else
                {
                 JOptionPane.showMessageDialog( null, ""+e+"", "ERROR!", JOptionPane. ERROR_MESSAGE);
                }
            }
        return content;
    }
}
