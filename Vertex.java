import java.awt.Graphics;

public class Vertex {

	private int[] koordinate = {0,0,0,0,0,0,0,0}; // x, y, +/-, punkte, team, player, quarter, time
	
	public Vertex(int x, int y)
	{
		koordinate[0] = x;
		koordinate[1] = y;
	}

	public int[] getkoordinate() {
		return koordinate;
	}

	public void setkoordinate(int x, int y) {
		this.koordinate[0] = x;
		this.koordinate[1] = y;
	}
	
	public int getX()
	{
		return koordinate[0];
	}
	
	public int getY()
	{
		return koordinate[1];
	}
	
	public void setX(int x)
	{
		koordinate[0] = x;
	}
	
	public void setY(int y)
	{
		koordinate[1] = y;
	}
	
	public int  getPunkt()
	{
	    return koordinate[3];
	}
	
	public void setPunkt(int c)
	{
	    koordinate[3] = c;
	}
	
	public void zeichne(Graphics g)
	{
	    int groesse = 10;
		g.fillOval(koordinate[0]-groesse/2, koordinate[1]-groesse/2, groesse, groesse);
	}
	
    public void zeichneSymbol(Graphics g)
	{
        int groesse = 10;
        if(getkoordinate()[2] == 1)
        {
            g.fillOval(koordinate[0]-groesse/2, koordinate[1]-groesse/2, groesse, groesse);
        }
        else if(getkoordinate()[2] == 2)
        {
            g.drawString("X", koordinate[0]-groesse/2, koordinate[1]+groesse/2);
        }
	}
}
