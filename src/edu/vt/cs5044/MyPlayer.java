package edu.vt.cs5044;

import java.util.ArrayList;

import edu.vt.cs5044.adventure.Player;


public class MyPlayer extends Player {
	
	private ArrayList<Item> inventory;

	public MyPlayer() {
		super();
		
		this.inventory = new ArrayList<>();
		
	}
	
	public ArrayList<Item> getInventory()
	{
		return new ArrayList<Item>(this.inventory);
	}
	
	public void addToInventory(Item item)
	{
		this.inventory.add(item);
	}
	
	public ArrayList<Item> getInventoryAndRoomItems()
	{
		ArrayList<Item> tor = new ArrayList<Item>(this.inventory);
		tor.addAll(((MyRoom)this.getCurrentRoom()).getItems());
		return tor;
	}
	public Item lookInIventoryOrRoom(String item)
	{
		for(Item i: this.getInventoryAndRoomItems())
		{
			if(i.getName().equals(item))
			{
				return i;
			}
		}
		return null;
	}

}
