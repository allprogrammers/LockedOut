package edu.vt.cs5044;

import edu.vt.cs5044.adventure.Command;
import edu.vt.cs5044.adventure.Message;
import edu.vt.cs5044.adventure.Player;

public class Open implements Command {

	public Open() {
		super();
	}

	@Override
	public String execute(Player player, String secondWord) {
		if(secondWord==null)
		{
			return "Open what?";
		}
		
		
		
		MyPlayer myPlayer = (MyPlayer) player;
		MyRoom myRoom = (MyRoom) myPlayer.getCurrentRoom();
		
		if(myRoom.isDark())
		{
			return Message.openDark();
		}
		
		Item i = myPlayer.lookInIventoryOrRoom(secondWord);
		if(i==null)
		{
			return Message.itemCantSee(secondWord);
		}
		if(i.isContainer())
		{
			if(i.items.size()==0)
			{
				return Message.openAlready(secondWord);
			}
			return i.openContainer(myPlayer);
		}
		return Message.openCant(secondWord);
	}

}
