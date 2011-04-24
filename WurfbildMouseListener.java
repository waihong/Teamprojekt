import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class WurfbildMouseListener implements MouseListener,
MouseMotionListener
{
    private WurfbildLabel bl;
    private MainFrame hf;
    
    // aktuelle Mauskoordinaten
    private int[] mousepos = new int[2];
    
    public WurfbildMouseListener(WurfbildLabel bl, MainFrame hf)
    {
        this.bl = bl;
        this.hf = hf;
    }
    
    public int[] getMousepos()
    {
        return mousepos;
    }
    
    @Override
    public void mouseClicked(MouseEvent arg0)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent arg0)
    {
        mousepos[0] = arg0.getX();
        mousepos[1] = arg0.getY();

        bl.repaint();
    }

    @Override
    public void mouseExited(MouseEvent arg0)
    {
        mousepos[0] = arg0.getX();
        mousepos[1] = arg0.getY();

        bl.repaint();
    }

    @Override
    public void mousePressed(MouseEvent arg0)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent arg0)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseDragged(MouseEvent arg0)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(MouseEvent arg0)
    {
        mousepos[0] = arg0.getX();
        mousepos[1] = arg0.getY();

        bl.repaint();
    }

}
