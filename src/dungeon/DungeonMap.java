package dungeon;

import characters.Player;
import controls.*;

import java.util.ArrayList;

public class DungeonMap
{
   private ArrayList<DungeonRoom> dungeonRooms = new ArrayList<>();

   public DungeonMap(MonsterCompendium mc)
   {
      initialiseDungeon(mc);
   }

   private void initialiseDungeon(MonsterCompendium mc)
   {

      DungeonRoom room = new DungeonRoom("Foyer",
                                         "A bland cave opening with faintly carved Mayan iconography.",
                                         new boolean[] { true, false, false, false });
      dungeonRooms.add(room);

      room = new DungeonRoom("Hallway",
                             "A narrow passage dimly lit by lanterns - ominous shadows\nplay " +
                             "on the walls.",
                             new boolean[] { true, false, false, true });
      dungeonRooms.add(room);

      room = new DungeonRoom("Arena",
                             "A large area with a motley crowd jeering from the grandstands.",
                             mc.getMonster(0),
                             new boolean[] { false, true, false, true });
      dungeonRooms.add(room);
      mc.getMonster(0).setDungeonRoom(room);

      room = new DungeonRoom("Secret Passage",
                             "A corridor leading east from a secret passage in the\n" +
                             "arena, which is to the west.",
                             new boolean[] { false, true, true, false });
      dungeonRooms.add(room);

      room = new DungeonRoom("Underground Temple",
                             "Hollowed out from the rock is a temple sanctuary with a\n" +
                             "drinking fountain.",
                             new boolean[] { true, false, true, true });
      dungeonRooms.add(room);

      room = new DungeonRoom("Ancient Crypt",
                             "An alcove with a series of old tombs. You are overwhelmed\n" +
                             "with a sense of dark foreboding.", mc.getMonster(3),
                             new boolean[] { false, false, false, true });
      dungeonRooms.add(room);
      mc.getMonster(3).setDungeonRoom(room);


      room = new DungeonRoom("Arched Corridor",
                             "A long north/south corridor lines with arches hewn " +
                             "from\n" +
                             "stone.", new boolean[] { true, false, false, true });
      dungeonRooms.add(room);

      room = new DungeonRoom("Hall of Statues",
                             "This room is lined with impressive marble statues of\n" +
                             "various creatures. A mischievous impish figure " +
                             "stands\n" +
                             "out in the centre. Grooves around the base indicate that\n" +
                             "it can be rotated.",
                             new boolean[] { true, false, false, false });
      dungeonRooms.add(room);

      dungeonRooms.get(0).setExitRooms(new DungeonRoom[] { dungeonRooms.get(1), null,
               null, null });
      dungeonRooms.get(1).setExitRooms(new DungeonRoom[] { dungeonRooms.get(2), null,
               null, dungeonRooms.get(0) });
      dungeonRooms.get(2).setExitRooms(new DungeonRoom[] { null, dungeonRooms.get(3),
               null, dungeonRooms.get(1) });
      dungeonRooms.get(3).setExitRooms(new DungeonRoom[] { null, dungeonRooms.get(4),
               dungeonRooms.get(2), null });
      dungeonRooms.get(4).setExitRooms(new DungeonRoom[] { dungeonRooms.get(5), null,
               dungeonRooms.get(3), dungeonRooms.get(6) });
      dungeonRooms.get(5).setExitRooms(new DungeonRoom[] { null, null,
               null, dungeonRooms.get(4) });
      dungeonRooms.get(6).setExitRooms(new DungeonRoom[] { dungeonRooms.get(4), null,
               null, dungeonRooms.get(7) });
      dungeonRooms.get(7).setExitRooms(new DungeonRoom[] { dungeonRooms.get(6), null,
               null, null });

      // East exit for the arena is blocked until the Man Slayer is killed.
      dungeonRooms.get(2).setExitBlocked(2);

      // South exit for the crypt is blocked until the Undead Solder is killed.
      dungeonRooms.get(5).setExitBlocked(4);

      dungeonRooms.get(4).setSpecialOption("D", "D: Drink from fountain");

      dungeonRooms.get(7).setSpecialOption("T", "T: Turn the Imp Statue");
   }

   public DungeonRoom getRoom(int i)
   {
      return dungeonRooms.get(i);
   }



}
