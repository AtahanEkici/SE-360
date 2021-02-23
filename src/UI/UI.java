package UI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public final class UI extends JFrame implements ActionListener
{
    private static UI single_instance = null; // singleton pattern so that only one Frame will be opened/rendered //
    private static final Color PALE_BLACK = new Color(33, 37, 41); // Frame Content Pane Color //
    
    public static UI getInstance()
    {
        if(single_instance == null)
        {
            single_instance = new UI();
        }
            return single_instance;    
    }
    
    // ------------------- Swing Components ------------------- //
    
    private JFrame main;
    private JButton btn1,btn2,btn3;
    private JTextArea jta;
    private JMenuBar mb;
    private JMenu fileMenu,aboutMenu;
    private JMenuItem jm_read,jm_new,jm_about,jm_github; // Main Frame Menu Components //
    private JScrollPane jsp;
    
    // ------------------- Swing Components ------------------- //
    
    private UI()
    {
        try 
        {
            Construct_Main_Frame();
        } catch(IOException e) 
        {
             JOptionPane.showMessageDialog(null,""+e.getMessage()+"","ERROR ("+e.getClass()+")",JOptionPane.ERROR_MESSAGE);
        }
    }
    
        private void Construct_Main_Frame() throws IOException     // Constructs the Main Frame //  
    {
        main = new JFrame("SE116 Project - Main Frame");
        //ImageIcon image = new ImageIcon(getClass().getResource("/Icons/icon.png"));
        //main.setIconImage(image.getImage());
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

        fileMenu = new JMenu("File");
        fileMenu.setFocusable(true);
        fileMenu.setForeground(Color.BLACK);
        
        jm_read = fileMenu.add("Open");
        jm_read.addActionListener(this);
        jm_read.setBackground(Color.WHITE);
        
        jm_new = fileMenu.add("Save");
        jm_new.addActionListener(this);
        jm_new.setBackground(Color.WHITE);
        
        aboutMenu = new JMenu("About");
        aboutMenu.setFocusable(true);
        aboutMenu.setForeground(Color.BLACK);
        
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


    @Override
    public void actionPerformed(ActionEvent Event) 
    {
       if(Event.getSource() == btn1)
       {
            JOptionPane.showMessageDialog(null,"Btn1 pressed"); 
       }
       
       else if(Event.getSource() == btn2)
       {
           JOptionPane.showMessageDialog(null,"Btn2 pressed"); 
       }
       
       else if(Event.getSource() == btn3)
       {
           JOptionPane.showMessageDialog(null,"Btn3 pressed"); 
       }
       
       else if(Event.getSource() == jm_github)
       {
           if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) // if the github button is pressed on the main frame //
            {
                try 
                {
                    Desktop.getDesktop().browse(new URI("https://github.com/AtahanEkici/SE-360/tree/Project"));
                } catch (IOException | URISyntaxException e) 
                {
                JOptionPane.showMessageDialog( null, ""+e.getMessage()+"", ""+e.getClass()+"", JOptionPane. ERROR_MESSAGE);
                }
            }
       }
       
       else
       {
           JOptionPane.showMessageDialog(null,"Unhandled Action "+Event.getSource().getClass().getSimpleName()+""); 
       }
    }
}
