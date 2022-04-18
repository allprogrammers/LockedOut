package edu.vt.cs5044.adventure;

import edu.vt.cs5044.FullGoCommand;
import edu.vt.cs5044.IUsable;
import edu.vt.cs5044.Item;
import edu.vt.cs5044.MoreMessages;
import edu.vt.cs5044.MyGame;
import edu.vt.cs5044.MyPlayer;
import edu.vt.cs5044.MyRoom;
import edu.vt.cs5044.adventure.Game;
import edu.vt.cs5044.adventure.Player;
import edu.vt.cs5044.adventure.Room;
import edu.vt.cs5044.items.FakeRock;
import edu.vt.cs5044.items.FrontDoor;
import edu.vt.cs5044.items.LightBulb;
import edu.vt.cs5044.items.ScrewDriver;
import edu.vt.cs5044.items.ShoeBox;
import edu.vt.cs5044.items.SideDoor;
import edu.vt.cs5044.items.SpareKey;
import edu.vt.cs5044.items.WallLantern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

// Version: 2022.Spring
//
// NOTE: These tests are just meant to demonstrate how to do the kinds of testing you'll need to do.
// These aren't expected to continue to pass, once you begin to develop the Locked Out scenario.
// You'll definitely need to replace and/or change these sample tests in order to work as required.
// There will be no such rooms as "one" or "two" or "three" in the actual implementation.

@SuppressWarnings("javadoc")
public class MyGameTest {

	private Game game;

	@Before
	public void setUp() {
		game = new MyGame();
		game.initialize();
	}

	@Test
	public void test_Go_Navigation() {
		String[] dirscmd = new String[] { "north", "south", "east", "west" };
		for (String dir : dirscmd) {
			String reply = game.processInput("go " + dir);
			assertTrue(reply.equals("You move " + dir + ".") || reply.equals("There is no exit in that direction."));
		}

	}

	@Test
	public void test_dir_Navigation() {
		String[] dirscmd = new String[] { "north", "south", "east", "west" };
		for (String dir : dirscmd) {
			String reply = game.processInput(dir);
			assertTrue(reply.equals("You move " + dir + ".") || reply.equals("There is no exit in that direction."));
		}

	}

	@Test
	public void test_char_Navigation() {
		String[] dirscmd = new String[] { "n", "s", "e", "w" };
		Map<String, String> chartodir = new HashMap<>();
		chartodir.put("n", "north");
		chartodir.put("s", "south");
		chartodir.put("e", "east");
		chartodir.put("w", "west");
		for (String dir : dirscmd) {
			String reply = game.processInput(dir);
			assertTrue(reply.equals("You move " + chartodir.get(dir) + ".")
					|| reply.equals("There is no exit in that direction."));
		}

	}

	@Test
	public void test_game_use() {
		MyPlayer player = (MyPlayer) getPlayer();
		MyRoom room = (MyRoom) getRoom();
		MyRoom[] rooms = new MyRoom[] { room };

		IUsable sd = new SideDoor(rooms);
		IUsable fd = new FrontDoor(rooms);

		Item[] items = new Item[] { (Item) sd, (Item) fd };
		IUsable sb = new ShoeBox(rooms);
		IUsable fr = new FakeRock(rooms);
		IUsable lb = new LightBulb(rooms);
		IUsable sk = new SpareKey(rooms, items);
		IUsable scd = new ScrewDriver(rooms);

		IUsable[] usables = new IUsable[] { sb, sd, fr, lb, sk, scd, fd };
		for (IUsable i : usables) {
			room.addItems((Item) i);
		}
		assertTrue(sb.useItem(player).equals(MoreMessages.useCant(((Item) sb).getName())));
		assertTrue(sd.useItem(player).equals(MoreMessages.doorLocked(((Item) sd).getName())));
		assertTrue(fd.useItem(player).equals(MoreMessages.doorLocked(((Item) fd).getName())));
		assertTrue(fr.useItem(player).equals(MoreMessages.useCant(((Item) fr).getName())));
		assertTrue(lb.useItem(player).equals(MoreMessages.NeedScrewDriver()));
		assertTrue(scd.useItem(player).equals(MoreMessages.usedScrewDriver()));
		assertTrue(lb.useItem(player).equals(MoreMessages.bulbAdded()));
		assertTrue(sk.useItem(player).equals(MoreMessages.doorNotLocked(((Item) sd).getName())));
		assertTrue(sd.useItem(player).equals(MoreMessages.Success()));

	}
	
	@Test
	public void test_game_examine()
	{
		MyPlayer player = (MyPlayer) getPlayer();
		MyRoom room = (MyRoom) getRoom();
		MyRoom[] rooms = new MyRoom[] { room };

		Item sd = new SideDoor(rooms);
		Item fd = new FrontDoor(rooms);

		Item[] items = new Item[] { (Item) sd, (Item) fd };
		Item sb = new ShoeBox(rooms);
		Item fr = new FakeRock(rooms);
		Item lb = new LightBulb(rooms);
		WallLantern wl = new WallLantern(rooms);
		Item sk = new SpareKey(rooms, items);
		Item scd = new ScrewDriver(rooms);
		
		Item[] itemss = new Item[] { sb, sd, fr, lb, wl, sk, scd, fd };
		for (Item i : itemss) {
			room.addItems((Item) i);
		}
		
		assertTrue(game.processInput("x "+sb.getName()).equals(MoreMessages.examineEmptyContainer(sb.getName())));
		assertTrue(game.processInput("x "+sd.getName()).equals(MoreMessages.doorLocked(sd.getName())));
		assertTrue(game.processInput("x "+fd.getName()).equals(MoreMessages.doorLocked(fd.getName())));
		assertTrue(game.processInput("x "+scd.getName()).equals(MoreMessages.seemsNormal(scd.getName())));
		assertTrue(game.processInput("x "+wl.getName()).equals(MoreMessages.descriptionLanternBroken()));
		assertTrue(game.processInput("x "+lb.getName()).equals(MoreMessages.seemsNormal(lb.getName())));
		assertTrue(game.processInput("x "+sk.getName()).equals(MoreMessages.seemsNormal(sk.getName())));
		assertTrue(game.processInput("x "+fr.getName()).equals(MoreMessages.examineEmptyContainer(fr.getName())));
		sb.addItem(scd);
		fr.addItem(sk);
		wl.addBulb((LightBulb) lb);
		
		assertTrue(game.processInput("x "+sb.getName()).equals(MoreMessages.examineClosedContainer(sb.getName())));
		assertTrue(game.processInput("x "+fr.getName()).equals(MoreMessages.examineClosedContainer(fr.getName())));
		assertTrue(game.processInput("x "+wl.getName()).equals(MoreMessages.descriptionLanternFixed()));
		
	}

	/**
	 * Return the player.
	 *
	 * @return the player.
	 */
	private Player getPlayer() {
		return game.getPlayer();
	}

	/**
	 * Return the current room.
	 *
	 * @return the current room.
	 */
	private Room getRoom() {
		return getPlayer().getCurrentRoom();
	}

	/**
	 * Test to cover the main() method, using the "test" option to suppress
	 * input/output
	 */
	@Test
	public void testMyGameMain() {
		MyGame.main(new String[] { "test" });
	}

}
