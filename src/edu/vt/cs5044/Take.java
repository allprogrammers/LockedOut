package edu.vt.cs5044;

import edu.vt.cs5044.adventure.Command;
import edu.vt.cs5044.adventure.Message;
import edu.vt.cs5044.adventure.Player;

public class Take implements Command {

	public Take() {
		super();
	}

	@Override
	public String execute(Player player, String secondWord) {
		if (secondWord==null)
		{
			return MoreMessages.takeWhat();
		}
		
		MyPlayer myPlayer = (MyPlayer) player;
		
		MyRoom myRoom = (MyRoom) myPlayer.getCurrentRoom();
		
		for(Item i: myPlayer.getInventory())
		{
			if (i.getName().equals(secondWord))
			{
				return Message.takeAlready(secondWord);
			}
		}
		
		for(Item i: myRoom.getItems())
		{
			if (i.getName().equals(secondWord))
			{
				if(i.isFixed())
				{
					return Message.takeCant(secondWord);
				}
				
				myPlayer.addToInventory(i);
				myRoom.removeFromRoom(i);
				
				return Message.takeSuccess(secondWord);
			}
		}
		return Message.itemCantSee(secondWord);
	}

}
