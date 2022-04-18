package edu.vt.cs5044.items;

import java.util.ArrayList;

import edu.vt.cs5044.IUsable;
import edu.vt.cs5044.Item;
import edu.vt.cs5044.MoreMessages;
import edu.vt.cs5044.MyPlayer;
import edu.vt.cs5044.MyRoom;
import edu.vt.cs5044.adventure.Message;

public class WallLantern extends Item {
	

	private boolean isLocked;
	
	public boolean isLocked()
	{
		return this.isLocked;
	}

	public WallLantern(MyRoom[] roomsToBeUsedIn) {
		super("wall-lantern");
		
		this.isContainer = true;
		this.items = new ArrayList<Item>();
		
		this.isUsable = true;
		this.roomsToBeUsedIn = roomsToBeUsedIn;
		
		this.isFixed = true;
		this.isLocked = true;
		
	}
	
	public String useItem(MyPlayer player) {
		if(this.items.size()==0)
		{
			return Message.descriptionLanternBroken();
		}
		MyRoom room = (MyRoom) player.getCurrentRoom();
		room.setDark(false);
		
		return Message.descriptionLanternFixed();
	}
	public String unscrew()
	{
		if (!this.isLocked)
		{
			return Message.openAlready(getName());
		}
		this.isLocked=false;
		this.setDescription(MoreMessages.examineOpenContainer(this.getName()));
		return Message.openSuccess(this.getName());
	}/*
	@Override
	public String openContainer(MyPlayer player)
	{
		MyRoom itemRoom = (MyRoom) player.getCurrentRoom();
		if(this.isLocked)
		{
			return MoreMessages.examineClosedContainer(this.getName());
		}
		
		return super.openContainer(player);
		
	}*/

	public String addBulb(LightBulb lightBulb) {
		if(this.isLocked)
		{
			return Message.openCant(this.getName());
		}
		this.addItem(lightBulb);
		this.roomsToBeUsedIn[0].setDark(false);
		return Message.descriptionLanternFixed();
	}
	
	@Override
	public String getDescription()
	{
		String opened;
		if(this.isLocked)
		{
			opened = Message.examineClosedContainer(this.getName());
		}else
		{
			opened = MoreMessages.examineEmptyContainer(this.getName());
		}
		
		if(this.items.size()==0)
			return MoreMessages.descriptionLanternBroken()+" "+opened;
		return MoreMessages.descriptionLanternFixed()+" "+MoreMessages.seeBulb();
	}

}
