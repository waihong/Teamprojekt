import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class FeldMouseListener implements MouseListener,
MouseMotionListener
{
    private BballLabel bl;
    
    // Wurfbilder
    private JLabel bf2_label, bf3_label;
    
    private BufferedImage buf;
    //private final int[] dreier = {255, 233, 142};
    //private final int[] zweier = {240, 150, 0};
    //private final int[] zone = {170, 100, 0};

    // aktuelle Mauskoordinaten
    private int[] mousepos = new int[2];
    
    //aktuelles Pixel
    private int pixel;
    
    //Punkt als gesetzt markieren
    private MainFrame hf;

    public FeldMouseListener(BballLabel l, BufferedImage buf, MainFrame hf, JPanel bf2, JPanel bf3)
    {
        bl = l;
        this.buf = buf;
        pixel = 0;
        this.hf = hf;
        this.bf2_label = (JLabel)bf2.getComponent(0);
        this.bf3_label = (JLabel)bf3.getComponent(0);
    }
    
    public int[] getMousepos()
    {
        return mousepos;
    }
    
    public int getPixel()
    {
        return pixel;
    }
    @Override
    public void mouseClicked(MouseEvent arg0)
    {
        //System.out.println(pixel);
        //printPixelARGB(pixel);
    }
    

    /* Quelle:
       http://www.devdaily.com/blog/post/java/getting-rgb-values-for-each-pixel-in-image-using-java-bufferedi 
    
    public void printPixelARGB(int pixel) {
        int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        System.out.println("argb: " + alpha + ", " + red + ", " + green + ", " + blue);
      }
    */
    
    @Override
    public void mouseEntered(MouseEvent e)
    {
        mousepos[0] = e.getX();
        mousepos[1] = e.getY();
        
        // Cursor ändern
        Cursor kreuz = new Cursor(Cursor.CROSSHAIR_CURSOR);
        bl.setCursor(kreuz);
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        pixel = 0;
        bl.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e)
    {

    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        //if ermöglicht, dass beim Klicken nicht definierter Bereiche nichts passiert
        if (pixel == hf.getDreier() || pixel == hf.getZweier() || pixel == hf.getZone())
        {
            mousepos[0] = e.getX();
            mousepos[1] = e.getY();
            
            // damit das feld gecleared wird wenn ok oder corr gedrückt wird
            hf.setClearFlag(false);
            
            // wenn zuerst + oder - gedrückt wurde und im nachhinein das feld geändert wird, 
            // sollen die buttons unverändert bleiben (aktiviert bzw. deaktiviert)
            if (hf.getPlusMinusFlag() == 0)
            {           
                // plus-minus-buttons erst aktivieren, nachdem auf das feld geklickt wurde
                hf.setPlus_btn(true);
                hf.setMinus_btn(true);     
            }

            // Überprüfen, ob bereits Punkt gesetzt wurde
            if (!hf.getWurfFlag())
            {
                // Punkt als gesetzt markieren
                hf.setWurfFlag(true);
                // in Koordinatenliste einfügen
                hf.getPunkteListe().add(new Vertex(e.getX(), e.getY()));
            }
            else
            {
                // Lösche den alten Vertex
                hf.getPunkteListe().remove(hf.getPunkteListe().size()-1);
                // Füge den neuen hinzu
                hf.getPunkteListe().add(new Vertex(e.getX(), e.getY()));
            }

            // Art des Wurfes anzeigen
            if (pixel == hf.getDreier())
            {
                System.out.println("3PTS");
                // 3 in Vertex eintragen, um zu kennzeichnen, dass es sich um einen 3er handelt
                hf.getPunkteListe().getLastVertex().setPunkt(3);
            }
            else if (pixel == hf.getZweier())
            {
                System.out.println("2PTS ");
                hf.getPunkteListe().getLastVertex().setPunkt(2);
            }
            else if (pixel == hf.getZone())
            {
                System.out.println("2PTS PAINT");
                hf.getPunkteListe().getLastVertex().setPunkt(2);
            }

            System.out.println("Anz Koordinaten: " + hf.getPunkteListe().size());
            System.out.println("WurfFlag: " + hf.getWurfFlag());
            System.out.println("PlayerFlag: " + hf.getPlayerFlag());
            System.out.println();
            
            bl.repaint();
            bf2_label.repaint();
            bf3_label.repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        mousepos[0] = e.getX();
        mousepos[1] = e.getY();
        pixel = buf.getRGB(e.getX(), e.getY());  

        bl.repaint();
        bf2_label.repaint();
        bf3_label.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        mousepos[0] = e.getX();
        mousepos[1] = e.getY();
        pixel = buf.getRGB(e.getX(), e.getY()); 

        bl.repaint();
        bf2_label.repaint();
        bf3_label.repaint();
    }
}
