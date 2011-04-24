import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class PunkteListe extends ArrayList<Vertex>
{
	private static final long serialVersionUID = 1L;
	
	public PunkteListe()
	{
		super();
	}
	
	public void zeichne(Graphics g, boolean clearFlag) 
	{
		if(this.size() != 0)
		{
		    if(!clearFlag)
		    {
		        this.getLastVertex().zeichne(g);
		    }
		}
	}
	public void zeichneAlle(Graphics g, int[] mousepos) 
    {
        for(Vertex v: this)
        {
            v.zeichneSymbol(g);
            showInfo(g,v, mousepos);
        }
    }
	
	public Vertex getLastVertex()
	{
	    return this.get(this.size()-1);
	}
	
	public void showInfo(Graphics g, Vertex v, int[] mousepos)
	{
	    int box = 3;
        if(((v.getX()- box) <= mousepos[0] && mousepos[0] <= (v.getX()+ box)) && ((v.getY()- box <= mousepos[1])&& mousepos[1] <= (v.getY()+box)))
        {
            g.setColor(Color.RED);
            g.drawString("" + v.getkoordinate()[4], mousepos[0], mousepos[1]);
            g.setColor(Color.BLACK);
        }
	}
}
