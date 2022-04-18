package edu.vt.cs5044.items;


import edu.vt.cs5044.IUsable;
import edu.vt.cs5044.Item;
import edu.vt.cs5044.MoreMessages;
import edu.vt.cs5044.MyPlayer;
import edu.vt.cs5044.MyRoom;
import edu.vt.cs5044.adventure.Message;

public class LightBulb extends Item implements IUsable {

	public LightBulb(MyRoom[] roomsToBeUsedIn) {
		// TODO Auto-generated constructor stub
		super("light-bulb");
		
		this.isContainer = false;
		
		this.isUsable = true;
		this.roomsToBeUsedIn = roomsToBeUsedIn;
		
		this.isFixed = false;
	}

	@Override
	public String useItem(MyPlayer player) {
		MyRoom room = (MyRoom) player.getCurrentRoom();
		WallLantern wl = null;
		for(Item i: room.getItems())
		{
			if(i instanceof WallLantern)
			{
				wl = (WallLantern) i;
				break;
			}
		}
		if(wl==null)
			return MoreMessages.NoPlaceForBulb();
		
		if(wl.isLocked())
		{
			return Message.examineClosedContainer(wl.getName());
		}
		wl.addBulb(this);
		player.removeFromInventory(this);
		
		return MoreMessages.bulbAdded();
	}

}
