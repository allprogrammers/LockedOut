package edu.vt.cs5044;

import java.util.HashMap;
import java.util.Map;

import edu.vt.cs5044.adventure.Command;
import edu.vt.cs5044.adventure.Game;
import edu.vt.cs5044.adventure.Message;
import edu.vt.cs5044.adventure.Room;
import edu.vt.cs5044.items.FakeRock;
import edu.vt.cs5044.items.FrontDoor;
import edu.vt.cs5044.items.LightBulb;
import edu.vt.cs5044.items.ScrewDriver;
import edu.vt.cs5044.items.ShoeBox;
import edu.vt.cs5044.items.SideDoor;
import edu.vt.cs5044.items.SpareKey;
import edu.vt.cs5044.items.WallLantern;


/**
 * This is just a very simple example game to demonstrate the use of the library
 *
 * @version 2022.Spring
 */
public class MyGame extends Game {

    @Override
    public Map<String, Command> setupCommands() {
        Map<String, Command> cmdMap = new HashMap<>();
        cmdMap.put("go", new FullGoCommand(""));
        
        cmdMap.put("n", new FullGoCommand("north"));
        cmdMap.put("north", new FullGoCommand("north"));
        
        cmdMap.put("s", new FullGoCommand("south"));
        cmdMap.put("south", new FullGoCommand("south"));
        
        cmdMap.put("e", new FullGoCommand("east"));
        cmdMap.put("east", new FullGoCommand("east"));
        
        cmdMap.put("w", new FullGoCommand("west"));
        cmdMap.put("west", new FullGoCommand("west"));
        
        cmdMap.put("take", new Take());
        
        cmdMap.put("examine", new Examine());
        cmdMap.put("x", new Examine());
        
        cmdMap.put("inventory", new Inventory());
        cmdMap.put("i", new Inventory());
        
        cmdMap.put("open", new Open());
        
        cmdMap.put("use", new Use());
        
        return cmdMap;
    }
    

    @Override
    public MyRoom setupRooms() {
        MyRoom driveway = new MyRoom(Message.roomDriveway());
        MyRoom garage = new MyRoom(Message.roomGarage());
        MyRoom frontyard = new MyRoom(Message.roomFrontYard());
        MyRoom house = new MyRoom(Message.roomHouse());
        
        Item[] doors = new Item[2];
        doors[0] = new SideDoor(new MyRoom[]{garage});
        doors[1] = new FrontDoor(new MyRoom[]{frontyard});
        
        
        garage.addItems( new ScrewDriver(new MyRoom[]{frontyard}));
        Item shoebox = new ShoeBox(new MyRoom[] {});
        shoebox.addItem(new LightBulb(new MyRoom[]{frontyard}));
        garage.addItems(shoebox);
        garage.addItems( doors[0] );
        
        
        
        frontyard.addItems( doors[1]);
        frontyard.addItems( new WallLantern(new MyRoom[]{frontyard}));
        Item fakerock = new FakeRock(new MyRoom[] {});
        fakerock.addItem(new SpareKey(new MyRoom[]{frontyard,garage},doors));
        frontyard.addItems(fakerock);
        frontyard.setDark(true);
        
        
        MyRoom.addDoor(driveway, garage, "north",false,"");
        MyRoom.addDoor(driveway,frontyard,"west",false,"");
        MyRoom.addDoor(garage,house,"west",true,"side-door");
        MyRoom.addDoor(frontyard,house,"north",true,"front-door");
        
        return driveway;
    }

    @Override
    public MyPlayer setupPlayer() {
        return new MyPlayer();
    }

    @Override
    public String getWelcomeMessage() {
        return "You're locked out of your house! Your goal is to get back inside, if you can...";
    }

    /**
     * Main method to launch the game.
     *
     * @param args options; include "test" to suppress any action beyond initialization. This is
     * only needed to support code coverage of the main() method.
     */
    public static void main(String[] args) {
        Game game = new MyGame();
        game.initialize();
        game.play(args);
    }

}
