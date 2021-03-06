package UI;

import DataBase.Database_Connections; // import Database Connections class //
import Support.SupportingFunctions;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.UnsupportedLookAndFeelException;

public final class UI extends JFrame implements ActionListener, MouseListener
{
    private static UI single_instance = null; // singleton pattern so that only one Frame will be rendered //
    private static final Color PALE_BLACK = new Color(33, 37, 41); // Frame Content Pane Color //
    private static boolean Timer_Is_Active = false;
    
    public static UI getInstance()
    {
        if(single_instance == null)
        {
            single_instance = new UI();
        }
            return single_instance;    
    }

    // ------------------- Swing Components ------------------- //
    
    private static JFrame main;
    private static JButton btn1,btn2,btn3,btn4,btn5;
    private static JTextArea jta;
    private static JMenuBar mb;
    private static JMenu fileMenu,aboutMenu;
    private static JMenuItem jm_open,jm_save,jm_about,jm_github; // Main Frame Menu Components //
    private static JScrollPane jsp;
    private static JComboBox tables;
    
    // ------------------- Swing Components ------------------- //
    
    private static Timer timer1;
    private static int timer_int;
    
    private UI()
    {
        try 
        {
            Construct_Main_Frame();
        } 
        catch(Exception e) // Catch all exceptions //
        {
             JOptionPane.showMessageDialog(null,""+e.getMessage()+"","ERROR ("+e.getClass().getSimpleName()+")",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void Construct_Main_Frame() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException     // Constructs the Main Frame //  
    {
        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());  
        
        main = new JFrame("SE360 Project - Main Frame");
        ImageIcon image = new ImageIcon(getClass().getResource("/Icons/demo.png"));
        main.setIconImage(image.getImage());
        main.setLayout(new BorderLayout());
        main.setResizable(false);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel tutucu = new JPanel();
        tutucu.setLayout(new FlowLayout());
        tutucu.setBackground(PALE_BLACK);

        JPanel textArea = new JPanel();
        textArea.setLayout(new FlowLayout(FlowLayout.CENTER));
        textArea.setBackground(Color.BLACK);

        mb = new JMenuBar();
        main.setJMenuBar(mb);      
        mb.setBorder(BorderFactory.createLineBorder(PALE_BLACK));
        
        btn1 = new JButton("Btn1");
        btn1.addActionListener(this);
        btn1.setBackground(Color.WHITE);
        btn1.setFocusable(false);
        
        btn2 = new JButton("Btn2");
        btn2.addActionListener(this);
        btn2.setBackground(Color.WHITE);
        btn2.setFocusable(false);

        btn3 = new JButton("Btn3");
        btn3.addActionListener(this);
        btn3.setBackground(Color.WHITE);
        btn3.setFocusable(false);
        
        btn4 = new JButton("Btn4");
        btn4.addActionListener(this);
        btn4.setBackground(Color.WHITE);
        btn4.setFocusable(false);
        
        btn5 = new JButton("Btn5");
        btn5.addActionListener(this);
        btn5.setBackground(Color.WHITE);
        btn5.setFocusable(false);
        
        tables = new JComboBox(Database_Connections.getAllTableNames().toArray());
        tables.addActionListener(this);
        tables.setBackground(Color.WHITE);
        tables.setFocusable(false);
        tables.setSelectedIndex(-1);
        
        fileMenu = new JMenu("File");
        fileMenu.setFocusable(true);
        fileMenu.setForeground(Color.BLACK);
        fileMenu.addMouseListener(this);
        
        jm_open = fileMenu.add("Open");
        jm_open.addActionListener(this);
        jm_open.setBackground(Color.WHITE);
        
        jm_save = fileMenu.add("Save");
        jm_save.addActionListener(this);
        jm_save.setBackground(Color.WHITE);
        
        aboutMenu = new JMenu("About");
        aboutMenu.setFocusable(true);
        aboutMenu.setForeground(Color.BLACK);
        aboutMenu.addMouseListener(this);
        
        jm_about = aboutMenu.add("About This Project");
        jm_about.addActionListener(this);
        jm_about.setBackground(Color.WHITE);
        
        jm_github = aboutMenu.add("GitHub Page");
        jm_github.addActionListener(this);
        jm_github.setBackground(Color.WHITE);
        
        mb.add(fileMenu);
        mb.add(aboutMenu);
        
        tutucu.add(btn1,BorderLayout.CENTER);
        tutucu.add(btn2,BorderLayout.CENTER);
        tutucu.add(btn3,BorderLayout.CENTER);
        tutucu.add(btn4,BorderLayout.CENTER);
        tutucu.add(btn5,BorderLayout.CENTER);
        tutucu.add(tables,BorderLayout.SOUTH);
        tutucu.setBorder(null);
        
        jsp = new JScrollPane();
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setBorder(null);
        
        jsp.getVerticalScrollBar().setBackground(PALE_BLACK);
        jsp.getHorizontalScrollBar().setBackground(PALE_BLACK);
        
        jta = new JTextArea(35,55);
        jta.setForeground(Color.WHITE);
        jta.setBackground(Color.BLACK);
        jta.setEditable(false);
        
        jsp.getViewport().add(jta);
        add(jsp);
                     
        textArea.add(jsp);
        textArea.setBorder(null);
  
        main.add(textArea,BorderLayout.PAGE_END); // Text'leri tutan panelin JFrame'e iliştirilmesi //
        main.add(tutucu,BorderLayout.NORTH); // Butonları tutan panelin Ana Frame'e eklenmesi //
        main.pack(); // Function that packs the frame and cuts the unnecessary lines //
        main.setLocationRelativeTo(null); // initially start the frame at the center of the screen //
        main.setVisible(true);        
        main.setAutoRequestFocus(true);
        main.requestFocus();
    }

    private void UpdateFrame()
    {
        // Refresh Main Frame //
        main.setVisible(false);
        main.revalidate();
        main.setVisible(true);
        jta.append("Frame refreshed\n");
        // Refresh Main Frame //
    }
    
    private int RNG(int min, int max)
    {
        return ThreadLocalRandom.current().nextInt(min,(max + 1));
    }
    
    private static void TimerForButton(int timer_value,int value,JButton button)
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
         btn4.setEnabled(false);
         Timer_Is_Active = true;
    }
    
    


    @Override
    public void actionPerformed(ActionEvent Event) 
    {
       if(Event.getSource() == btn1) // Buton1'e tıklandığında //
       {
           jta.append("Btn1 pressed\n");
            //JOptionPane.showMessageDialog(null,"Btn1 pressed");  
            //Update();
       }
       
       else if(Event.getSource() == btn2) // Buton2'ye tıklandığında //
       {
           jta.append("Btn2 pressed\n");
           JOptionPane.showMessageDialog(null,"Btn2 pressed");
           //Update();
       }
       
       else if(Event.getSource() == btn3) // Buton3'e tıklandığında //
       {
           UpdateFrame();
       }
       
       else if(Event.getSource() == btn4)
       {
           if(Timer_Is_Active == false)
           {
               TimerForButton(1000, RNG(10,100), btn4);
           }        
       }
       
        else if(Event.getSource() == btn5)
       {
            jta.setText(""); // Clear jtext area //
       }
       
       else if(Event.getSource() == jm_github) // Github Menüsü seçildiğinde //
       {
           if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) // if the github button is pressed on the main frame //
            {
                try 
                {
                  Desktop.getDesktop().browse(new URI("https://github.com/AtahanEkici/SE-360/tree/Project"));
                } 
                catch (IOException | URISyntaxException e) 
                {
                JOptionPane.showMessageDialog(null,""+e.getMessage()+"",""+e.getClass().getSimpleName()+"",JOptionPane. ERROR_MESSAGE);
                }
            }
       }
       
       else if(Event.getSource() == jm_about) // About menü butonuna basılınca //
       {
            try
            {   
                JOptionPane.showMessageDialog(null,"<html><font color=#0066ff> <u> Java Swing Application</u></font></html>\n\n"
                        + "<html><font color=#0066ff><u> Java</u>: </font>  1.8.0_125 </html> \n"
                        + "<html><font color=#0066ff> <u> IDE</u>: </font>  Apache Netbeans IDE 12.0 </html>\n"
                        + "<html><font color=#0066ff><u> Icons</u>: </font>  www.flaticon.com </html>\n","About This Project",JOptionPane.INFORMATION_MESSAGE);     
            }catch(HeadlessException e)
            {
                JOptionPane.showMessageDialog( null, ""+e+"", "ERROR!", JOptionPane. ERROR_MESSAGE);
            }
       }
       
       else if(Event.getSource() == jm_open)
       {
           try 
           {
               jta.append(SupportingFunctions.ReadFile() + "\n");
           } 
           catch (Exception e) 
           {
               JOptionPane.showMessageDialog( null, "File Reading Error", ""+e.getClass().getSimpleName()+"Error", JOptionPane. ERROR_MESSAGE);
           }
       }
       
       else if(Event.getSource() == tables) // Combobox üzerinde seçim yapıldığında //
       {
           if(tables.getSelectedItem() != null)
           {
               JOptionPane.showMessageDialog(null,"<html><center>"+tables.getSelectedItem()+" selected </center> </html>");
           } 
      }
       
       else
       {
          jta.append("Unhandled Action\n");
          JOptionPane.showMessageDialog( null, "Unhandled Action", ""+Event.getSource().getClass().getSimpleName()+" Error", JOptionPane. ERROR_MESSAGE);
       }
    }

// ---------------------------- Other Action Elements ---------------------------- //
// Not Needed //
@Override public void mousePressed(MouseEvent Event){}
@Override public void mouseReleased(MouseEvent Event){}
@Override public void mouseClicked(MouseEvent Event){}
// Not Needed //

    @Override
    public void mouseEntered(MouseEvent Event) 
    {
       if(Event.getSource() == fileMenu)
        {
            fileMenu.setSelected(true); // begin hover effect //
        }
        else if(Event.getSource() == aboutMenu)
        {
            aboutMenu.setSelected(true); // begin hover effect //
        }
    }

    @Override
    public void mouseExited(MouseEvent Event) 
    {
       if(Event.getSource() == fileMenu)
        {
            fileMenu.setSelected(false); // dispose hover effect //
        }
        else if(Event.getSource() == aboutMenu)
        {
            aboutMenu.setSelected(false); // dispose hover effect //
        }
    }
}
