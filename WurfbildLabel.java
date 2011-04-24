import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class WurfbildLabel extends JLabel
{
    private static final long serialVersionUID = 1L;
    
    private MainFrame hf;
    private WurfbildMouseListener wl;

    public WurfbildLabel(ImageIcon i, MainFrame hf)
    {
        super(i);
        this.hf = hf;
        wl = new WurfbildMouseListener(this, hf);
        this.addMouseListener(wl);
        this.addMouseMotionListener(wl);
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        hf.getPunkteListe().zeichneAlle(g, wl.getMousepos());
    }

}
