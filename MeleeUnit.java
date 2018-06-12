import java.util.ArrayList;
import java.awt.geom.*;
import javafx.event.*;
import javafx.scene.input.*;
public class MeleeUnit extends Unit
{
	private int counter = 0;
	public MeleeUnit(Map map, int health, int armor, Location location, int owner, double vision, double speed, int damage, double radius, double attackSpeed)
	{
		super(map, health, armor, location, owner, vision, speed, damage, radius, attackSpeed);
	}
	public MeleeUnit(Map map, int health, int armor, Location location, double vision, double speed, int damage, double radius, double attackSpeed)
	{
		super(map, health, armor, location, vision, speed, damage, radius, attackSpeed);
	}
	public MeleeUnit(int health, int armor, Location location, double vision, double speed, int damage, double radius, double attackSpeed)
	{
		super(health, armor, location, vision, speed, damage, radius, attackSpeed);
	}
	public double getVision() { return super.getVision(); }
	public int setOwner(int x) { return super.setOwner(x); }
	public Map setMap(Map map) { return super.setMap(map); }
	public int getMaxHealth() { return super.getMaxHealth(); }
	public void attackMove()
	{
		ArrayList <Thing> thingsInVision = super.getThingsInVision();

		Thing target = null;
		double minDistance = 100000.0;

		for(Thing thing : thingsInVision)
		{
			if(thing.getOwner() != getOwner())
			{
				if(getLocation().distanceFrom(thing.getCenter()) < minDistance)
				{
					target = thing;
					minDistance = getLocation().distanceFrom(thing.getCenter());
				}
			}
		}
		if(target != null)
		{
			if(inContact(target))
			{
				stop();
				attack();
				act();
				if(counter <= 0)
				{
					dealDamage(target);
					counter = (int)getAttackSpeed();
				}
				counter--;
			}
			else
			{
				moveTo(target.getLocation());
				super.move();
			}

		}
		else
		{
			moveTo(getTarget());
			super.move();
		}
	}

}

