package edu.vt.cs5044;


import edu.vt.cs5044.adventure.Command;
import edu.vt.cs5044.adventure.GoCommand;
import edu.vt.cs5044.adventure.Player;

public class FullGoCommand extends GoCommand {
	
	private String dir;
	private boolean type;
	public FullGoCommand(String dir) {
		super();
		
		if(dir.length()==0)
		{
			this.type=true;
		}else
		{
			this.type=false;
			this.dir = dir;
		}
	}
	
	@Override
	public String execute(Player player, String secondWord)
	{
		String dir;
		if(this.type)
		{
			dir = secondWord;
		}else
		{
			dir = this.dir;
		}
		
		MyRoom room = (MyRoom) player.getCurrentRoom();
		if(room.lockedDir.containsKey(dir) && room.lockedDir.get(dir))
		{
			return MoreMessages.doorLocked(room.doorName.get(dir));
		}
		return super.execute(player, dir);
	}

}
