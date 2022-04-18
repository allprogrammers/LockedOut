package edu.vt.cs5044.items;

import edu.vt.cs5044.FullGoCommand;
import edu.vt.cs5044.IDoor;
import edu.vt.cs5044.IUsable;
import edu.vt.cs5044.Item;
import edu.vt.cs5044.MoreMessages;
import edu.vt.cs5044.MyPlayer;
import edu.vt.cs5044.MyRoom;
import edu.vt.cs5044.adventure.Message;

public class SideDoor extends Item implements IUsable, IDoor{

	private boolean isLocked;
	
	public boolean isLocked()
	{
		return this.isLocked;
	}

	public SideDoor(MyRoom[] roomsToBeUsedIn) {
		super("side-door");
		
		this.isContainer = false;
		
		this.isUsable = true;
		this.roomsToBeUsedIn = roomsToBeUsedIn;
		
		this.isFixed = true;
		this.isLocked = true;
	}

	@Override
	public String useItem(MyPlayer player) {
		return MoreMessages.useCant(getName());
	}

	@Override
	public String unlock(MyPlayer player) {
		if(!this.isLocked)
		{
			return MoreMessages.doorAlreadyUnLocked(this.getName());
		}
		this.isLocked=false;
		MyRoom room = (MyRoom) player.getCurrentRoom();
		room.Unlock("west");
		return MoreMessages.doorUnLocked(getName());
	}
	
	@Override
	public String getDescription()
	{
		if(this.isLocked)
		{
			return MoreMessages.doorLocked(this.getName());
		}
		return MoreMessages.doorUnLocked(this.getName());
	}
	
	@Override
	public String openContainer(MyPlayer player)
	{
		return Message.openCant(getName());
	}
}
