package edu.vt.cs5044.items;

import edu.vt.cs5044.IUsable;
import edu.vt.cs5044.Item;
import edu.vt.cs5044.MyPlayer;
import edu.vt.cs5044.MyRoom;

public class ScrewDriver extends Item implements IUsable {

	public ScrewDriver(MyRoom[] roomsToBeUsedIn) {
		super("screw-driver");
		
		this.isContainer = false;
		
		this.isUsable = true;
		this.roomsToBeUsedIn = roomsToBeUsedIn;
		
		this.isFixed = false;
	}

	@Override
	public String useItem(MyPlayer player) {
		WallLantern wl = null;
		for(Item i: player.getInventoryAndRoomItems())
		{
			if (i instanceof WallLantern)
			{
				wl = (WallLantern) i;
			}
		}
		if(wl==null)
		{
			return "Don't know where to use the screw driver";
		}
		
		return wl.unscrew();
	}
	
	

}
