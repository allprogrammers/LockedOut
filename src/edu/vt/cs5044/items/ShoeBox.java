package edu.vt.cs5044.items;

import java.util.ArrayList;

import edu.vt.cs5044.IUsable;
import edu.vt.cs5044.Item;
import edu.vt.cs5044.MoreMessages;
import edu.vt.cs5044.MyPlayer;
import edu.vt.cs5044.MyRoom;
import edu.vt.cs5044.adventure.Message;

public class ShoeBox extends Item implements IUsable {

	public ShoeBox(MyRoom[] roomsToBeUsedIn) {
		super("shoe-box");
		
		this.isContainer = true;
		this.items = new ArrayList<Item>();
		
		this.isUsable = false;
		
		this.isFixed = true;
	}

	@Override
	public String useItem(MyPlayer player) {
		return MoreMessages.useCant(getName());
	}
	
	@Override
	public String getDescription()
	{
		if(this.items.size()==0)
			return MoreMessages.examineEmptyContainer(getName());
		return MoreMessages.examineClosedContainer(getName());
	}

}
