package edu.vt.cs5044;

import java.util.ArrayList;

import edu.vt.cs5044.adventure.FormatUtil;
import edu.vt.cs5044.adventure.Message;

public class Item{
	
	private String name;
	
	protected boolean isContainer;
	protected ArrayList<Item> items;
	
	protected boolean isUsable;
	protected MyRoom[] roomsToBeUsedIn;
	protected String description;
	
	protected boolean isFixed;
	
	public Item(String name)
	{
		this.name = name;
		this.description = MoreMessages.seemsNormal(this.getName());
	}
	
	public void addItem(Item item)
	{
		this.items.add(item);
	}
	
	public boolean isUsable()
	{
		return this.isUsable;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public boolean isContainer()
	{
		return this.isContainer;
	}
	
	public boolean isFixed()
	{
		return this.isFixed;
	}
	
	public String openContainer(MyPlayer player)
	{
		MyRoom itemRoom = (MyRoom) player.getCurrentRoom();
		ArrayList<String> names = new ArrayList<>();
		this.items.forEach(x->{itemRoom.addItems(x);names.add(x.getName());});
		this.items = new ArrayList<>();
		
		return Message.openSuccess(this.getName()) + " You see "+FormatUtil.formatCollection(names, "the ");
	}

}
