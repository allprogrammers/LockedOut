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

	public SideDoor(MyRoom[] roomsToBeUsedIn) {
		super("side-door");
		
		this.isContainer = true;
		
		this.isUsable = true;
		this.roomsToBeUsedIn = roomsToBeUsedIn;
		
		this.isFixed = true;
		this.isLocked = true;
	}

	@Override
	public String useItem(MyPlayer player) {
		if(this.isLocked)
		{
			return Message.openCant(this.getName());
		}
		FullGoCommand cmd = (new FullGoCommand("west"));
		cmd.execute(player, null);
		player.completeQuest();
		return MoreMessages.Success();
	}

	@Override
	public String unlock() {
		this.isLocked = false;
		return MoreMessages.doorNotLocked(getName());
	}
	
	@Override
	public String getDescription()
	{
		if(this.isLocked)
		{
			return Message.examineClosedContainer(this.getName());
		}
		return MoreMessages.doorNotLocked(this.getName());
	}
	
	@Override
	public String openContainer(MyPlayer player)
	{
		return this.useItem(player);
	}
}
