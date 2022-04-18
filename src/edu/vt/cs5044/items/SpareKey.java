package edu.vt.cs5044.items;

import edu.vt.cs5044.IDoor;
import edu.vt.cs5044.IUsable;
import edu.vt.cs5044.Item;
import edu.vt.cs5044.MoreMessages;
import edu.vt.cs5044.MyPlayer;
import edu.vt.cs5044.MyRoom;

public class SpareKey extends Item implements IUsable {
	
	private Item[] canBeUsedOn;

	public SpareKey(MyRoom[] roomsToBeUsedIn,Item[] canBeUsedOn) {
		super("spare-key");
		
		this.isContainer = false;
		
		this.isUsable = true;
		this.roomsToBeUsedIn = roomsToBeUsedIn;
		
		this.isFixed = false;
		this.canBeUsedOn = canBeUsedOn;
	}

	@Override
	public String useItem(MyPlayer player) {
		MyRoom room = null;
		for(MyRoom r: this.roomsToBeUsedIn)
		{
			if(r==(MyRoom)player.getCurrentRoom())
			{
				room = r;
			}
		}
		if (room== null)
		{
			return MoreMessages.useCant(getName());
		}
		IDoor door = null;
		
		for(Item i:this.canBeUsedOn)
		{
			Item toUseOn = player.lookInIventoryOrRoom(i.getName());
			if(toUseOn!=null)
			{
				door = (IDoor) toUseOn;
				break;
			}
		}
		if(door!=null)
		{
			return door.unlock();
		}
		return MoreMessages.useCant(getName());
	}

}
