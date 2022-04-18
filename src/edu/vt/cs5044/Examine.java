package edu.vt.cs5044;

import java.util.ArrayList;

import edu.vt.cs5044.adventure.Command;
import edu.vt.cs5044.adventure.Message;
import edu.vt.cs5044.adventure.Player;

public class Examine implements Command {

	public Examine() {
		super();
	}
	
	private String returnDescription(ArrayList<Item> inventory, String secondWord)
	{
		for(Item i: inventory)
		{
			if(i.getName().equals(secondWord))
			{
				return i.getDescription();
			}
		}
		return Message.itemCantSee(secondWord);
	}

	@Override
	public String execute(Player player, String secondWord) {
		if (secondWord==null)
		{
			return MoreMessages.examineWhat();
		}
		
		MyPlayer myPlayer = (MyPlayer) player;
		return this.returnDescription(myPlayer.getInventoryAndRoomItems(), secondWord);
		
		
	}

}
