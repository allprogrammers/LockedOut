package edu.vt.cs5044;

import java.util.ArrayList;

import edu.vt.cs5044.adventure.Command;
import edu.vt.cs5044.adventure.FormatUtil;
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
		
		MyPlayer myPlayer = (MyPlayer) player;
		if(myPlayer.getInventory().size()==0)
		{
			return Message.inventoryEmpty();
		}
		ArrayList<String> names = new ArrayList<>();
		
		myPlayer.getInventory().forEach(x->names.add(x.getName()));
		
		return "You are carrying " + FormatUtil.formatCollection(names, "the ")+".";
		
	}

}
