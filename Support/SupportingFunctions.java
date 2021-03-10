package Support;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SupportingFunctions 
{
    private static Timer timer1;
    private static Timer progressTimer;
    
    private static double getFileSizeInMegaBytes(File file) 
    {
	return (double) file.length() / (1048576);
    }
    
    public static void setImageIcon(URL url, JFrame frame) throws IOException
    {
        ImageIcon imageicon = new ImageIcon(url);
        Image image = imageicon.getImage();
        frame.setIconImage(image);
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
                    return "File too large\n";
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
                 JOptionPane.showMessageDialog( null, ""+e.getLocalizedMessage()+"", "ERROR!", JOptionPane. ERROR_MESSAGE);
                }
            }
        return content;
    }
    
    public static int RNG(int min, int max)
    {
        return ThreadLocalRandom.current().nextInt(min,(max + 1));
    }
    
    public static void LookAndFeelSetup() throws UnsupportedLookAndFeelException
    {
        UIManager.setLookAndFeel(new FlatLightLaf());
        UIManager.put("ScrollBar.track",(Color.WHITE));
        UIManager.put( "ScrollBar.thumbArc", 999 );
    }
    
    public static void UpdateFrame(JFrame main)
    {
        // Refresh Main Frame //
        //SwingUtilities.updateComponentTreeUI(main); // Unstable: have issues //
        // Refresh Main Frame //
    }
    
    public static void TimerForButton(int timer_value,int value,JButton button,JTextArea jta)
    {      
        //String def = button.getText();
        button.setEnabled(false);
        
        timer1 = new Timer(timer_value,new ActionListener()
            {
                public int timer_int = value;
                
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    timer_int--;
                    //button.setText(Integer.toString(timer_int)); // Causes horizontal scrollpane to fail //
                        
                    if(timer_int == 0)
                    {
                       timer1.stop();
                       jta.append(""+value+" seconds concluded\n");
                       //button.setText(def);
                       button.setEnabled(true);
                       timer1 = null;
                    }
                    else if(timer_int % 5 == 0 && timer_int != 0)
                    {
                       jta.append("Timer is running "+timer_int+" left\n");
                    }      
                }
            });
         timer1.start();
    }
    
    public static void ProgressBarController(JProgressBar jb, int timer_value, JButton button, JTextArea jta)
    {
        button.setEnabled(false);
        
        progressTimer = new Timer(timer_value,new ActionListener()
            {
                public int timer_int = 0;
                
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    timer_int++;
                    jb.setValue(timer_int);
                        
                    if(timer_int == 100)
                    {
                       progressTimer.stop();
                       progressTimer = null;
                       jta.append("ProgressBar finished\n");
                       button.setEnabled(true);
                    }   
                }
            });
         progressTimer.start();
    }
}
