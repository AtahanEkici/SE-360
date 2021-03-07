package Support;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class SupportingFunctions 
{
    private static Timer timer1;
    private static boolean Timer_Is_Active = false;
    
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
    
    public static int RNG(int min, int max)
    {
        return ThreadLocalRandom.current().nextInt(min,(max + 1));
    }
    
    public static void UpdateFrame(JFrame main)
    {
        // Refresh Main Frame //
        main.setVisible(false);
        main.revalidate();
        main.setVisible(true);
        // Refresh Main Frame //
    }
    
    public static void TimerForButton(int timer_value,int value,JButton button,JTextArea jta)
    {      
        String def = button.getText();
        timer1 = new Timer(timer_value,new ActionListener()
            {
                public int timer_int = value;
                
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    timer_int--;
                    button.setText(Integer.toString(timer_int));
                        
                    if(timer_int == 0)
                    {
                       timer1.stop();
                       button.setText(def);
                       button.setEnabled(true);
                       timer1 = null;
                       Timer_Is_Active = false;
                       System.gc(); // Call garbage collector for un-assigned references //
                    }
                    else if(timer_int % 5 == 0 && timer_int != 0)
                    {
                       jta.append("Timer is running\n");
                    }      
                }
            });
         timer1.start();
         button.setEnabled(false);
         Timer_Is_Active = true;
    }

}
