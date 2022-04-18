package edu.vt.cs5044;


import edu.vt.cs5044.adventure.Command;
import edu.vt.cs5044.adventure.GoCommand;
import edu.vt.cs5044.adventure.Player;

public class FullGoCommand extends GoCommand {
	
	private String dir;
	public FullGoCommand(String dir) {
		super();
		
		if(dir.length()==0)
		{
			this.dir = "go";
		}else
		{
			this.dir = dir;
		}
	}
	
	@Override
	public String execute(Player player, String secondWord)
	{
		
		String result;
		MyRoom room = (MyRoom)player.getCurrentRoom();
		if(room.lockedDir.get(this.dir))
		{
			String name;
			if(((MyRoom)room).doorName.containsKey(this.dir))
			{
				name = ((MyRoom)room).doorName.get(this.dir);
				return MoreMessages.doorLocked(name);
			}
			
		}
		if(this.dir=="go")
		{
			result  = super.execute(player, secondWord);
		}else
		{
			result = super.execute(player, this.dir);
		}
		//System.out.println(player.getCurrentRoom().getName());
		
		return result;
	}

}
