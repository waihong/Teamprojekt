import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class BballLabel extends JLabel
{
    private static final long serialVersionUID = 1L;


    private FeldMouseListener fl;
    private MainFrame hf;
    
    //Abstandsparameter des Wurf-Labels
    private final int zy = 22;  
    private final int zx = 15;
    


    public BballLabel(BufferedImage buf, MainFrame hf, JPanel bf2, JPanel bf3)
    {
        super(new ImageIcon(buf));                              // Bild in ImageIcon laden);
        this.hf = hf;
        fl = new FeldMouseListener(this, buf, hf, bf2, bf3);  // this wird für cursor-änderung übergeben
        this.addMouseListener(fl);
        this.addMouseMotionListener(fl);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        // Farbe wechseln, wenn + oder - gedrückt wird
        if (hf.getPlusMinusFlag() == 0)
        {
            g.setColor(Color.BLACK);
        }
        else if (hf.getPlusMinusFlag() == 1)
        {
            g.setColor(Color.GREEN);
        }
        else
        {
            g.setColor(Color.RED);
        }

        // zeichne aktuellen Punkt
        hf.getPunkteListe().zeichne(g,hf.getClearFlag());
        
        // Farbe für Textanzeige
        g.setColor(Color.BLACK);
        // Art des Wurfes anzeigen
        if (fl.getPixel() == hf.getDreier())
        {
            g.drawString("3PTS", fl.getMousepos()[0]-zx, fl.getMousepos()[1]+zy);
        }
        else if (fl.getPixel() == hf.getZweier())
        {
            g.drawString("2PTS", fl.getMousepos()[0]-zx, fl.getMousepos()[1]+zy);
        }
        else if(fl.getPixel() == hf.getZone())
        {
            g.drawString("2PTS", fl.getMousepos()[0]-zx, fl.getMousepos()[1]+zy);
        }
    }

}
