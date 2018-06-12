import java.util.ArrayList;
public class Map
{
	private int x = 2000;
	private int y = 2000;
	private ArrayList <Building> buildings = new ArrayList <Building>();
	private ArrayList <Player> players = new ArrayList <Player>();
	private ArrayList <Unit> units = new ArrayList <Unit>();
	private ArrayList <MineralNode> mineralNodes = new ArrayList <MineralNode>();
	public Map(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	public Map()
	{
	}
	public void remove(Thing thing)
	{
		if(thing instanceof Goal)
			buildings.remove(buildings.indexOf((Goal)thing));

		else if(thing instanceof Building)
			buildings.remove(buildings.indexOf((Building)thing));

		else if(thing instanceof Unit)
			units.remove(units.indexOf((Unit)thing));

		getPlayerByNumber(thing.getOwner()).remove(thing);
	}
	public MineralNode add(MineralNode mineralNode)
	{
		mineralNodes.add(mineralNode);
		return mineralNode;
	}
	public ArrayList <MineralNode> getMineralNodes() { return mineralNodes; }
	public Player add(Player player) { players.add(player); return player; }
	public int getX() { return x; }
	public int getY() { return y; }
	public void setX(int x)
	{
		this.x = x;
	}
	public void setY(int y)
	{
		this.y = y;
	}
	public int numPlayers() { return players.size(); }
	public ArrayList <Unit> getUnits() { return units; }
	public ArrayList <Player> getPlayers() { return players; }
	public ArrayList <Building> getBuildings() { return buildings; }
	public Player getPlayerByNumber(int number)
	{
		for(Player player : players)
			if(player.getNumber() == number)
				return player;
		return null;
	}
	public String toString() { return "X: " + x + " Y: " + y; }
	public Building add(Building building)
	{
		if(!getPlayerByNumber(building.getOwner()).owns(building))
			getPlayerByNumber(building.getOwner()).give(building);
		buildings.add(building);
		return building;
	}
	public Unit add(Unit unit)
	{
		if(!getPlayerByNumber(unit.getOwner()).owns(unit))
			getPlayerByNumber(unit.getOwner()).give(unit);
		units.add(unit);
		return unit;
	}
	public boolean inMap(Thing thing)
	{
		if(thing instanceof Building)
		{
			thing = (Building)thing;
			for(Building building : buildings)
				if(thing == building)
					return true;
		}
		else if(thing instanceof Unit)
		{
			thing = (Unit)thing;
			for(Unit unit : units)
				if(thing == unit)
					return true;
		}
		return false;
	}
}
