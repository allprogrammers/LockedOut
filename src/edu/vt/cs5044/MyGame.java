package edu.vt.cs5044;

import edu.vt.cs5044.adventure.Command;
import edu.vt.cs5044.adventure.Game;
import edu.vt.cs5044.adventure.GoCommand;
import edu.vt.cs5044.adventure.Player;
import edu.vt.cs5044.adventure.Room;
import java.util.HashMap;
import java.util.Map;

/**
 * This is just a very simple example game to demonstrate the use of the library
 *
 * @version 2022.Spring
 */
public class MyGame extends Game {

    @Override
    public Map<String, Command> setupCommands() {
        Map<String, Command> cmdMap = new HashMap<>();
        cmdMap.put("go", new GoCommand());
        return cmdMap;
    }

    @Override
    public Room setupRooms() {
        Room one = new Room("in room one");
        Room two = new Room("in room two");
        Room three = new Room("in room three");
        one.addExit("east", two);
        two.addExit("west", one);
        two.addExit("east", three);
        three.addExit("west", two);
        return one;
    }

    @Override
    public Player setupPlayer() {
        return new Player();
    }

    @Override
    public String getWelcomeMessage() {
        return "Welcome to this very simple adventure!";
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
