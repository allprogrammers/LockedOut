package edu.vt.cs5044;

import edu.vt.cs5044.adventure.Command;
import edu.vt.cs5044.adventure.Message;
import edu.vt.cs5044.adventure.Player;

public class Use implements Command {

	public Use() {
		super();
	}

	@Override
	public String execute(Player player, String secondWord) {
		if(secondWord.isEmpty())
		{
			return null;
		}
		
		MyPlayer myPlayer = (MyPlayer) player;
		
		IUsable itemToUse=null;
		
		for(Item i: myPlayer.getInventory())
		{
			if(i.getName().equals(secondWord))
			{
				if(i.isUsable())
				{
					itemToUse = (IUsable)i;
				}
				else {
					return MoreMessages.useCant(secondWord);
				}
			}
		}
		if(itemToUse==null)
		{
			return MoreMessages.onlyInventory();
		}
		
		return itemToUse.useItem(myPlayer);
	}

}
