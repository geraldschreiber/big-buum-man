package at.big_buum_man.server.gui.objects;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Powerdown extends Extra
{
	private Integer zeit;
	private long syszeit;
	
	public Powerdown(Image image) 
	{
		super(image);
		super.setObjectName("Powerdown");
		this.zeit=10000;
	}
	
	public Powerdown() 
	{
		setObjectName("Powerdown");
		this.zeit=10000;
	}
	
	@Override
	public void draw(Graphics g) 
	{
		image.draw(x, y);
		g.setColor(Color.green);
		g.drawRect(x,y, 50, 50);
	}
	
	@Override
	public void update(int delta) 
	{
		
	}
	
	public void setZeit(int zeit)
	{
		this.zeit=zeit;
	}
	
	public int getZeit()
	{
		return this.zeit;
	}
	
	public void setSystemZeit(long syszeit)
	{
		this.syszeit=syszeit;
	}
	
	public long getSystemZeit()
	{
		return this.syszeit;
	}
}
