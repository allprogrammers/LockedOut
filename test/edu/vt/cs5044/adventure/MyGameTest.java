package edu.vt.cs5044.adventure;

import edu.vt.cs5044.FullGoCommand;
import edu.vt.cs5044.IUsable;
import edu.vt.cs5044.Inventory;
import edu.vt.cs5044.Item;
import edu.vt.cs5044.MoreMessages;
import edu.vt.cs5044.MyGame;
import edu.vt.cs5044.MyPlayer;
import edu.vt.cs5044.MyRoom;
import edu.vt.cs5044.Open;
import edu.vt.cs5044.Take;
import edu.vt.cs5044.Use;
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
		assertTrue(sd.useItem(player).equals(MoreMessages.useCant(((Item) sd).getName())));
		assertTrue(fd.useItem(player).equals(MoreMessages.useCant(((Item) fd).getName())));
		assertTrue(fr.useItem(player).equals(MoreMessages.useCant(((Item) fr).getName())));
		assertTrue(lb.useItem(player).equals(MoreMessages.NoPlaceForBulb()));

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
		
		
		assertEquals(
				Message.examineEmptyContainer(fr.getName()),
				fr.getDescription()
				);
		SpareKey sk = new SpareKey(rooms,new Item[] {fd,sd});
		fr.addItem(sk);
		
		assertEquals(
				Message.examineClosedContainer(fr.getName()),
				fr.getDescription()
				);
		
		Item lb = new LightBulb(rooms);
		WallLantern wl = new WallLantern(rooms);
		Item scd = new ScrewDriver(rooms);
		
		Item[] itemss = new Item[] { sb, sd, fr, lb, wl, sk, scd, fd };
		for (Item i : itemss) {
			room.addItems((Item) i);
		}
		
		assertTrue(game.processInput("x "+sb.getName()).equals(MoreMessages.examineEmptyContainer(sb.getName())));
		assertTrue(game.processInput("x "+sd.getName()).equals(MoreMessages.doorLocked(sd.getName())));
		assertTrue(game.processInput("x "+fd.getName()).equals(MoreMessages.doorLocked(fd.getName())));
		assertTrue(game.processInput("x "+scd.getName()).equals(MoreMessages.seemsNormal(scd.getName())));
		
	}
	
	@Test
	public void test_wl()
	{
		MyPlayer player = (MyPlayer) getPlayer();
		MyRoom room = (MyRoom) getRoom();
		MyRoom[] rooms = new MyRoom[] { room };
		
		WallLantern wl = new WallLantern(rooms);
		room.addItems(wl);
		ScrewDriver scd = new ScrewDriver(rooms);
		assertTrue(wl.getDescription().equals(MoreMessages.descriptionLanternBroken()+" "+Message.examineClosedContainer(wl.getName())));
		scd.useItem(player);
		assertEquals(
				MoreMessages.descriptionLanternBroken()+" "+MoreMessages.examineEmptyContainer(wl.getName()),
				wl.getDescription()
				);
		wl.addBulb(new LightBulb(rooms));

		assertEquals(
				MoreMessages.descriptionLanternFixed()+" "+MoreMessages.seeBulb(),
				wl.getDescription()
				);
	}
	
	@Test
	public void test_sk()
	{
		MyPlayer player = (MyPlayer) getPlayer();
		MyRoom room = (MyRoom) getRoom();
		MyRoom[] rooms = new MyRoom[] { room };
		
		FrontDoor fd = new FrontDoor(rooms);
		SideDoor sd = new SideDoor(rooms);
		
		SpareKey sk = new SpareKey(rooms, new Item[] {fd,sd});
		assertEquals(
				sk.useItem(player),
				MoreMessages.useCant(sk.getName()));
		
		room.addItems(fd);
		
		assertEquals(
				sk.useItem(player),
				MoreMessages.doorUnLocked(fd.getName()));
		room.removeFromRoom(fd);
		room.addItems(sd);
		assertEquals(
				sk.useItem(player),
				MoreMessages.doorUnLocked(sd.getName()));
		
		assertEquals(
				sk.useItem(player),
				MoreMessages.doorAlreadyUnLocked(sd.getName()));
		
		
	}
	
	
	
	@Test
	public void test_take()
	{
		MyPlayer player = (MyPlayer) getPlayer();
		MyRoom room = (MyRoom) getRoom();
		MyRoom[] rooms = new MyRoom[] { room };
		Take take = new Take();
		
		Inventory i = new Inventory();
		assertEquals(
				Message.inventoryEmpty(),
				i.execute(player,null));

		FrontDoor fd = new FrontDoor(rooms);
		SideDoor sd = new SideDoor(rooms);
		
		SpareKey sk = new SpareKey(rooms, new Item[] {fd,sd});
		
		assertEquals(
				Message.itemCantSee(fd.getName()),
				take.execute(player, fd.getName()));
		
		
		
		room.addItems(fd);
		
		assertEquals(
				Message.takeCant(fd.getName()),
				take.execute(player, fd.getName()));
		
		room.addItems(sk);
		assertEquals(
				Message.takeSuccess(sk.getName()),
				take.execute(player, sk.getName()));
		
		assertEquals(
				"You are carrying the spare-key.",
				i.execute(player,null));
		
		assertEquals(
				null,
				i.execute(player,""));
		
		assertEquals(
				Message.takeAlready(sk.getName()),
				take.execute(player, sk.getName()));
		
		assertEquals(
				MoreMessages.takeWhat(),
				take.execute(player, null));
		
		
		
		
		
	}
	
	@Test
	public void test_open()
	{
		MyPlayer player = (MyPlayer) getPlayer();
		MyRoom room = (MyRoom) getRoom();
		MyRoom[] rooms = new MyRoom[] { room };
		Open open = new Open();
		

		ShoeBox sb = new ShoeBox(rooms);
		LightBulb lb = new LightBulb(rooms);
		sb.addItem(lb);
		
		
		
		assertEquals(
				Message.itemCantSee(sb.getName()),
				open.execute(player, sb.getName()));
		
		room.addItems(sb);
		
		assertEquals(
				Message.openSuccess(sb.getName())+" You see the light-bulb",
				open.execute(player, sb.getName()));
		
		room.setDark(true);
		
		assertEquals(
				Message.openDark(),
				open.execute(player, sb.getName()));
		
		
	}
	
	@Test
	public void test_use()
	{
		MyPlayer player = (MyPlayer) getPlayer();
		MyRoom room1 = (MyRoom) getRoom();
		MyRoom room2 = new MyRoom("test");
		MyRoom[] rooms = new MyRoom[] { room1,room2 };
		MyRoom.addDoor(room1, room2, "north", true, "front-door");
		SideDoor sd = new SideDoor(rooms);
		FrontDoor fd = new FrontDoor(rooms);
		room2.addItems(fd);
		room1.addItems(sd);
		
		WallLantern wl = new WallLantern(rooms);
		room1.addItems(wl);
		
		LightBulb lb = new LightBulb(rooms);
		room1.addItems(lb);
		
		FakeRock fr = new FakeRock(rooms);
		ShoeBox sb = new ShoeBox(rooms);
		
		Use use = new Use();
		Open open = new Open();
		Take take = new Take();
		take.execute(player, lb.getName());
		assertEquals(
				MoreMessages.useWhat(),
				use.execute(player, null));
		assertEquals(
				MoreMessages.onlyInventory(),
				use.execute(player, wl.getName()));
		assertEquals(
				Message.examineClosedContainer(wl.getName()),
				use.execute(player, lb.getName()));
		
		assertEquals(
				MoreMessages.onlyInventory(),
				use.execute(player, sb.getName()));
		fd.unlock(player);
		FullGoCommand fgc = new FullGoCommand("");
		assertEquals("You move north.",
				fgc.execute(player, "north"));
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
