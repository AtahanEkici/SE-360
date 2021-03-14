package UI;

import DataBase.Database_Connections; // import Database Connections class //
import Support.SupportingFunctions; // import Supporting Functions class //

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
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public final class UI extends JFrame implements ActionListener, MouseListener, ChangeListener
{
    private static UI single_instance = null; // singleton pattern so that only one Frame will be rendered //
    private static final Color DARK_GREY = new Color(51,51,51);
    private static final Color LIGHT_GREY = new Color(204,204,204);
    
    public static UI getInstance() // Singleton Pattern //
    {
        if(single_instance == null)
        {
            single_instance = new UI();
        }
            return single_instance;    
    }

    // ------------------- Swing Components ------------------- //
    
    private static JFrame main,table;
    private static JButton btn1,btn2,btn3,btn4,btn5,btn6,btn7;
    private static JTextArea jta;
    private static JMenuBar mb;
    private static JMenu fileMenu,aboutMenu;
    private static JMenuItem jm_open,jm_save,jm_about,jm_github; // Main Frame Menu Components //
    private static JScrollPane jsp,jsp_Table;
    private static JComboBox tables;
    private static JCheckBox jcb;
    private static JSlider slider1;
    private static JProgressBar jb;
    private static JTable jt;
    private static ListSelectionModel select;
    
    // ------------------- Swing Components ------------------- //
    
    private UI()
    {
        try 
        {
            SupportingFunctions.LookAndFeelSetup(); // Change the Look And Feel of the java swing GUI 
            Construct_Main_Frame();
            JTable_Constructor();
        } 
        catch(Exception e) // Catch all exceptions //
        {
             JOptionPane.showMessageDialog(null,""+e.getMessage()+"","ERROR ("+e.getClass().getSimpleName()+")",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void JTable_Constructor()
    {
        table = new JFrame("JTable Demo");
        table.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        table.setBackground(DARK_GREY);
        
        String columns[]={"ID","NAME","SALARY"}; 
        String data[][]={ {"ID1","Name1","Salary1"},{"ID2","Name2","Salary2"},{"ID3","Name3","Salary3"}};    
        
        jt  = new JTable(data,columns);
        jt.setCellSelectionEnabled(true);
        jt.setBackground(DARK_GREY);
        jt.setForeground(LIGHT_GREY);
        jt.setDefaultEditor(Object.class, null);
        jt.addMouseListener(this);
        
        
        jsp_Table = new JScrollPane(jt);
        jsp_Table.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jsp_Table.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp_Table.getViewport().setBackground(Color.BLACK);
        jsp_Table.getViewport().setForeground(Color.WHITE);
        jsp_Table.setBorder(null);
    
        table.add(jsp_Table);  
        table.setSize(table.getPreferredSize());
        jt.setFillsViewportHeight(true);
        table.setLocationRelativeTo(null);
        table.setVisible(false);
        
    }
    
    private void Construct_Main_Frame() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException     // Constructs the Main Frame //  
    {
        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());  
        
        main = new JFrame("SE360 Project - Main Frame");
        main.setLayout(new BorderLayout());
        SupportingFunctions.setImageIcon(getClass().getResource("/Icons/demo.png"),main);
        //main.setResizable(false);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel tutucu = new JPanel();
        tutucu.setLayout(new FlowLayout());
        tutucu.setBackground(DARK_GREY);
        
        JPanel tutucu2 = new JPanel();
        tutucu2.setLayout(new FlowLayout());
        tutucu2.setBackground(Color.DARK_GRAY);

        JPanel textArea = new JPanel();
        textArea.setLayout(new FlowLayout(FlowLayout.CENTER));
        textArea.setBackground(Color.BLACK);

        mb = new JMenuBar();
        main.setJMenuBar(mb);      
        mb.setBorder(BorderFactory.createLineBorder(DARK_GREY));
        
        jcb = new JCheckBox();
        jcb.addActionListener(this);
        jcb.setFocusable(false);
        
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
        
        btn6 = new JButton("Btn6");
        btn6.addActionListener(this);
        btn6.setBackground(Color.WHITE);
        btn6.setFocusable(false);
        
        btn7 = new JButton("Btn7");
        btn7.addActionListener(this);
        btn7.setBackground(Color.WHITE);
        btn7.setFocusable(false);
        
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
        
        tutucu.add(jcb,BorderLayout.CENTER);
        tutucu.add(btn1,BorderLayout.CENTER);
        tutucu.add(btn2,BorderLayout.CENTER);
        tutucu.add(btn3,BorderLayout.CENTER);
        tutucu.add(btn4,BorderLayout.CENTER);
        tutucu.add(btn5,BorderLayout.CENTER);
        tutucu.add(btn6,BorderLayout.CENTER);
        tutucu.add(btn7,BorderLayout.CENTER);
        tutucu.add(tables,BorderLayout.SOUTH);
        tutucu.setBorder(null);
        
        slider1 = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        slider1.setBackground(Color.BLACK);
        slider1.setForeground(Color.WHITE);
        slider1.setFocusable(false);
        slider1.setMinorTickSpacing(5);  
        slider1.setMajorTickSpacing(20);  
        slider1.setPaintTicks(true);  
        slider1.setPaintLabels(true); 
        slider1.addChangeListener(this);
        
        jb = new JProgressBar(0,100);
        jb.setBackground(Color.WHITE);
        jb.setValue(0);    
        jb.setStringPainted(true); 
        jb.addChangeListener(this);
        
        tutucu2.add(slider1);
        tutucu2.add(jb);
        tutucu2.setBorder(null);
        
        jsp = new JScrollPane();
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.getViewport().setBackground(Color.BLACK);
        jsp.getViewport().setForeground(Color.WHITE);
        jsp.setBorder(null);

        jta = new JTextArea(35,55);
        jta.setForeground(LIGHT_GREY);
        jta.setBackground(Color.BLACK);
        jta.setEditable(false);
        
        jsp.getViewport().add(jta);
        textArea.add(jsp);
        textArea.setBorder(null);
  
        main.add(tutucu,BorderLayout.PAGE_START); // Butonları tutan panelin Ana Frame'e eklenmesi //
        main.add(tutucu2,BorderLayout.SOUTH);
        main.add(textArea,BorderLayout.CENTER); // Text'leri tutan panelin JFrame'e iliştirilmesi //
        main.pack(); // Function that packs the frame and cuts the unnecessary lines //
        main.setLocationRelativeTo(null); // initially start the frame at the center of the screen //
        main.setVisible(true);        
        main.setAutoRequestFocus(true);
        main.requestFocus();
    }
    
    @Override
    public void actionPerformed(ActionEvent Event) 
    {
       if(Event.getSource() == jcb)
       {
           if(jcb.isSelected() == true)
           {
               jta.append("JCheckBox checked\n");
           }
            
           else
           {
               jta.append("JCheckBox UNchecked\n");
           }   
       }
       
       else if(Event.getSource() == btn1) // Buton1'e tıklandığında //
       {
           jta.append("Btn1 pressed\n");
            //JOptionPane.showMessageDialog(null,"Btn1 pressed");  
       }
       
       else if(Event.getSource() == btn2) // Buton2'ye tıklandığında //
       {
           jta.append("Btn2 pressed\n");
           JOptionPane.showMessageDialog(null,"Btn2 pressed");
           //Update();
       }
       
       else if(Event.getSource() == btn3) // Buton3'e tıklandığında //
       {
           SupportingFunctions.UpdateFrame(main);
           jta.append("Frame refreshed\n");
       }
       
       else if(Event.getSource() == btn4)
       {
            int rng = SupportingFunctions.RNG(10,100);
            SupportingFunctions.TimerForButton(1000,rng,btn4,jta);      
       }
       
        else if(Event.getSource() == btn5)
       {
            jta.setText(""); // Clear jtext area //
       }
        
        else if(Event.getSource() == btn6)
       {
            SupportingFunctions.ProgressBarController(jb,250,btn6,jta);
       }
        
        else if(Event.getSource() == btn7)
       {
           if(table.isVisible() == true)
           {
               table.setVisible(false);
           }
           else
           {
               table.setVisible(true);
           }
           
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
                        + "<html><font color=#0066ff><u> Java</u>: </font> "+SupportingFunctions.getJavaVersion()+" </html> \n"
                        + "<html><font color=#0066ff> <u> IDE</u>: </font>  Apache Netbeans IDE 12.0 </html>\n"
                        + "<html><font color=#0066ff><u> Icons</u>: </font>  www.flaticon.com </html>\n"
                        + "<html><font color=#0066ff><u> Look&Feel</u>: </font>  Flatlaf 1.0 </html>\n","About This Project",JOptionPane.INFORMATION_MESSAGE);     
            }catch(HeadlessException e)
            {
                JOptionPane.showMessageDialog(null, ""+e.getLocalizedMessage()+"", "ERROR!", JOptionPane. ERROR_MESSAGE);
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
               JOptionPane.showMessageDialog(null, "File Reading Error", ""+e.getClass().getSimpleName()+"Error", JOptionPane. ERROR_MESSAGE);
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
// Not Needed //

private static String last_Selected_Table_Index = "";

@Override public void mouseClicked(MouseEvent Event)
{
    if(Event.getSource() == jt || Event.getSource() == select)
        {
            int row = jt.getSelectedRow();
            int column = jt.getSelectedColumn();
            String value = jt.getModel().getValueAt(row, column).toString();
            
            if(!last_Selected_Table_Index.matches(value))
            {
                jta.append(value + "\n");
                last_Selected_Table_Index = value;
            }
            else
            {
                last_Selected_Table_Index = value;
            } 
            last_Selected_Table_Index = "";
        }
}


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

    @Override
    public void stateChanged(ChangeEvent Event) 
    {
        if(Event.getSource() == slider1) // Slider //
        {
            jta.append("Slider Value: " +Integer.toString(slider1.getValue())+ "\n");
        }
        
        else if(Event.getSource() == jb) // Progress Bar //
        {
            jta.append("Progress bar state changed: "+jb.getValue()+"\n");
        }
    }
}
