import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame extends JFrame implements ActionListener
{
    private static final long serialVersionUID = 1L;
    
    private BufferedImage buf, buf_gray;
    
    private boolean playerFlag = false;
    private boolean wurfFlag = false;
    private int plusMinusFlag = 0;      // 0 = nicht gedrückt, 1 = +, 2 = -
    private boolean okay_flag = false;
    
    private final JButton plus_btn = new JButton("+");
    private final JButton minus_btn = new JButton("-");
    private final JButton okay_btn = new JButton("OK");
    private final JButton corr_btn = new JButton("Correction");
    
    //Liste für Koordianten
    private PunkteListe pl;
    
    // Festlegung der Farben
    private final int dreier = -6516;
    private final int zweier = -1010176;
    private final int zone = -5610496;
    
    // Wurf-Anklick-Feld
    private final BballLabel bl;
    
    // Punktestand
    private int[] punktestand = new int[2];
    
    public MainFrame(String title) throws InterruptedException
    {
        super(title);
        this.setLayout(new BorderLayout());
        //-------Bild laden-----------------------
        try 
        {
            buf = ImageIO.read(new File("bballfeld.png")); 
            buf_gray = ImageIO.read(new File("bballfeld_gray.png")); 
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }     
        //------------------------------------------
        pl = new PunkteListe();
        // erst Feld anklicken, dann erst PlusMinus aktivieren
        plus_btn.setEnabled(false);
        minus_btn.setEnabled(false);
        //----- Anklickfeld/ Wurfbilder ----------------------------
        JPanel wurf_panel = new JPanel();
        wurf_panel.setLayout(new BorderLayout());
        
        JPanel bf2 = new JPanel(); 
        wurf_panel.add(bf2, BorderLayout.WEST);
        final WurfbildLabel wl2 = new WurfbildLabel(new ImageIcon(buf_gray), this);
        bf2.add(wl2);
        
        JPanel bf3 = new JPanel(); 
        wurf_panel.add(bf3, BorderLayout.EAST);
        final WurfbildLabel wl3 = new WurfbildLabel(new ImageIcon(buf_gray), this);
        bf3.add(wl3);
        
        bl = new BballLabel(buf, this, bf2, bf3);
        //----- Buttons --------------------------------
        PlayerPanel player_btn = new PlayerPanel(this);
        //-----------------------------------------------------
        //--------- ACTIONLISTENER-----------------------------
        ActionListener okayListener= new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(wurfFlag && plusMinusFlag != 0) //Playerbedingung
                {
                    reset();
                }              
                bl.repaint();
                wl2.repaint();
                wl3.repaint();
            } 
        };
        okay_btn.addActionListener(okayListener);
        //--------CORRECTION-------------------------
        ActionListener corrListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                // Koordinatenanzahl senken
                if(wurfFlag)
                {
                    //Entferne letzten Vertex
                    getPunkteListe().remove(getPunkteListe().size()-1);
                }       
                // alles resetten
                reset();
                bl.repaint();
                wl2.repaint();
                wl3.repaint();
            } 
        };
        corr_btn.addActionListener(corrListener);
        //-----------------PLUS--------------------
        ActionListener plus = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                setPlusMinusFlag(1);
                pl.getLastVertex().getkoordinate()[2] = 1;
                plus_btn.setEnabled(false);
                minus_btn.setEnabled(true);
                bl.repaint();
                wl2.repaint();
                wl3.repaint();
            } 
        };
        //-----------------MINUS-------------------
        ActionListener minus = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                setPlusMinusFlag(2);
                pl.getLastVertex().getkoordinate()[2] = 2;
                plus_btn.setEnabled(true);
                minus_btn.setEnabled(false);
                bl.repaint();
                wl2.repaint();
                wl3.repaint();
            } 
        };
        plus_btn.addActionListener(plus); 
        minus_btn.addActionListener(minus);          
        //----------------------------------------------
        //----- Layout ---------------------------------
        int a = punktestand[0];
        int b = punktestand[1];
        JLabel punktestand = new JLabel("Punktestand: " + a + " : " + b);
        
        Container c = this.getContentPane();
        GridBagLayout gbl = new GridBagLayout();
        c.setLayout( gbl );
        //

        addComponent(c, gbl, player_btn, 0, 0, 1, 12, 0, 0);
        addComponent(c, gbl, bl, 1, 0, 2, 5, 0, 0);
        addComponent(c, gbl, plus_btn, 1, 5, 1, 1, 0, 0);
        addComponent(c, gbl, minus_btn, 2, 5, 1, 1, 0, 0);
        addComponent(c, gbl, corr_btn, 1, 6, 1, 1, 0, 0);
        addComponent(c, gbl, okay_btn, 2,6, 1, 1, 0, 0);
        addComponent(c, gbl, punktestand, 1,16, 2, 2, 0, 0);
        addComponent(c, gbl, wurf_panel, 1,8, 4, 4, 0, 0);
        //------------------------------------------
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(200, 400);
        //setSize(400, 300);
        pack();
        setVisible(true);
    }

    public static void addComponent(Container cont,
            GridBagLayout gbl,
            Component c,
            int x, int y,
            int width, int height,
            double weightx, double weighty )
{
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;
    gbc.gridx = x; 
    gbc.gridy = y;
    gbc.gridwidth = width; 
    gbc.gridheight = height;
    gbc.weightx = weightx; 
    gbc.weighty = weighty;
    gbl.setConstraints(c, gbc);
    cont.add(c);
}
    public void reset()
    {
        playerFlag = false;
        wurfFlag = false;
        plusMinusFlag = 0;      
        minus_btn.setEnabled(false);
        plus_btn.setEnabled(false);
    }
    public boolean getWurfFlag()
    {
        return wurfFlag;
    }
    public void setWurfFlag(boolean c)
    {
        wurfFlag = c;
    }
    
    public int getPlusMinusFlag()
    {
        return plusMinusFlag;
    }
    
    public void setPlusMinusFlag(int c)
    {
        plusMinusFlag = c;
    }
    
    public void setPlayerFlag(boolean c)
    {
        playerFlag = c;
    }
    
    public boolean getPlayerFlag()
    {
        return playerFlag;
    }
    
    public PunkteListe getPunkteListe()
    {
        return pl;
    }
    
    public int getDreier()
    {
        return dreier;
    }
    
    public int getZweier()
    {
        return zweier;
    }
    public int getZone()
    {
        return zone;
    }
    
    public void setPlus_btn(boolean c)
    {
        plus_btn.setEnabled(c);
    }
    
    public void setMinus_btn(boolean c)
    {
        minus_btn.setEnabled(c);
    }
    
    public boolean getOkayFlag()
    {
        return okay_flag;
    }
    
    public static void main(String[] args) throws InterruptedException
    {
        new MainFrame("Scouting");
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        // TODO Auto-generated method stub
        
    }

}
