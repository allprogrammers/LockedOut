package edu.vt.cs5044;

import edu.vt.cs5044.adventure.Message;

public class MoreMessages extends Message {
	
	public final static String useCant (String item)
	{
		return "The "+item+" cannot be used.";
	}
	
	public final static String bulbAdded()
	{
		return "Added light bulb to the wall lantern.";
	}
	
	public final static String doorLocked(String door)
	{
		return "The "+door+" is locked.";
	}
	
	public final static String doorNotLocked(String door)
	{
		return "The "+door+" is not locked.";
	}
	
	public final static String NeedScrewDriver()
	{
		return "Need a screw driver to disassemble.";
	}
	
	public final static String usedScrewDriver()
	{
		return "You disassembled wall lantern successfully.";
	}
	
	public final static String seemsNormal(String item)
	{
		return "Seems to be a normal "+item+".";
	}
	
	public final static String Success()
	{
		return "Well done. You are finally inside the house.";
	}
	
	public final static String onlyInventory()
	{
		return "You can only use the items in your inventory.";
	}

}
