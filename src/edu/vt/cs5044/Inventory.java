package edu.vt.cs5044;

import edu.vt.cs5044.adventure.Command;
import edu.vt.cs5044.adventure.Message;
import edu.vt.cs5044.adventure.Player;

public class Inventory implements Command {

	public Inventory() {
		super();
	}

	@Override
	public String execute(Player player, String secondWord) {
		if(secondWord!=null)
		{
			return null;
		}
		StringBuilder inventory = new StringBuilder("Your inventory is:\n");
		
		MyPlayer myPlayer = (MyPlayer) player;

		
		if(myPlayer.getInventory().size()==0)
		{
			return Message.inventoryEmpty();
		}
		
		for(Item i:myPlayer.getInventory())
		{
			inventory.append(i.getName()+"\n");
		}
		return inventory.toString();
		
	}

}
