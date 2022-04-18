package edu.vt.cs5044;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.vt.cs5044.adventure.FormatUtil;
import edu.vt.cs5044.adventure.Room;


public class MyRoom extends Room {
	
	private ArrayList<Item> items;
	
	private boolean isDark;
	
	public HashMap<String,Boolean> lockedDir;
	public HashMap<String,String> doorName;

	public MyRoom(String name) {
		super(name);
		this.items = new ArrayList<>();
		this.lockedDir = new HashMap<>();
		this.doorName = new HashMap<>();
	}
	
	public void addItems(Item item)
	{
		items.add(item);
	}
	
	public ArrayList<Item> getItems()
	{
		return new ArrayList<Item>(this.items);
	}
	
	public void removeFromRoom(Item item)
	{
		items.remove(item);
	}
	
	public void setDark(boolean isDark)
	{
		this.isDark = isDark;
	}
	
	public boolean isDark()
	{
		return this.isDark;
	}
	
	@Override
	public String getAdditionalDescription()
	{
		
		if(items.size()==0)
		{
			return "";
		}
		ArrayList<String> names = new ArrayList<>();
		this.items.forEach(x->names.add(x.getName()));
		
		String description = FormatUtil.formatCollection(names, "the ");
		
		return "You see " +description+".";
	}
	
	public void addExitv2(String dir,MyRoom room,boolean locked,String name)
	{
		this.addExit(dir, room);
		this.lockedDir.put(dir, locked);
		this.doorName.put(dir,name);
		
	}
	
	public static void addDoor(MyRoom from, MyRoom to,String dir,boolean locked,String name)
    {
    	Map<String,String> opposite = new HashMap<>();
    	opposite.put("east","west");
    	opposite.put("west", "east");
    	opposite.put("north", "south");
    	opposite.put("south","north");
    	
    	from.addExitv2(dir,to,locked,name);
    	to.addExitv2(opposite.get(dir), from,false,"");
    	
    }

}
