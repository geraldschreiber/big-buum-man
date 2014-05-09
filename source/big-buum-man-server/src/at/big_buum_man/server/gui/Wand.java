package at.big_buum_man.server.gui;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/***
 * @version 1.0
 * @author Michael Januschek
 *
 */
public class Wand extends SpielObjekt
{
	private String stein;
	private Integer haltbarkeit;
	
	public Wand(Image image, String stein) 
	{
		super(image);
		this.stein=stein;
	}
	
	public Wand(String stein)
	{
		this.stein=stein;
	}
	
	@Override
	public void draw(Graphics g) 
	{
		if(this.getStein().equals("0"))
		{
			try 
			{
				super.setImage(new Image("res/walls.png").getSubImage(0,0,50,50));
			} 
			catch (SlickException e) 
			{
				e.printStackTrace();
			}
		}
		else if(this.getStein().equals("1"))
		{
			try 
			{
				super.setImage(new Image("res/walls.png").getSubImage(375,0,50,50));
			} 
			catch (SlickException e) 
			{
				e.printStackTrace();
			}
		}
		else 
		{
			try 
			{
				super.setImage(new Image("res/saeule.png").getSubImage(0,0,50,50));
			} 
			catch (SlickException e) 
			{
				e.printStackTrace();
			}
		}
		image.drawCentered(x,y);
	}
	
	@Override
	public void update(int delta) 
	{
		
	}
	
	public String getStein()
	{
		return this.stein;
	}
	
	public void setStein(String stein)
	{
		this.stein=stein;
	}
	
	public Integer getHaltbarkeit() 
	{
		return haltbarkeit;
	}
	
	public void setHaltbarkeit(Integer haltbarkeit) 
	{
		this.haltbarkeit = haltbarkeit;
	}
}
