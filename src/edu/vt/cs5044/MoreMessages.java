package edu.vt.cs5044;

import edu.vt.cs5044.adventure.Message;

public class MoreMessages extends Message {
	
	public final static String useCant (String item)
	{
		return "The "+item+" cannot be used.";
	}
	
	public final static String bulbAdded()
	{
		return "You fixed the wall-lantern! It is brighter now.";
	}
	
	public final static String doorLocked(String door)
	{
		return "The "+door+" is locked.";
	}
	
	public final static String doorAlreadyUnLocked(String door)
	{
		return "The "+door+" is already unlocked.";
	}
	
	public final static String NeedScrewDriver()
	{
		return "Need a screw-driver to disassemble.";
	}
	
	public final static String usedScrewDriver()
	{
		return "You opened the wall-lantern.";
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
	
	public final static String NoPlaceForBulb()
	{
		return "No Place to put the light-bulb.";
	}
	
	public final static String seeBulb()
	{
		return "Inside you see light-bulb.";
	}

	public static String useWhat() {
		return "What do you want to use?";
	}

	public static String examineWhat() {
		return "What do you want to examine?";
	}
	
	public static String takeWhat() {
		return "What do you want to take?";
	}

	public static String doorUnLocked(String name) {
		// TODO Auto-generated method stub
		return "The "+name+ " is unlocked";
	}

	public static String examineOpenContainer(String name) {
		return "The "+name+" is open.";
	}

}
