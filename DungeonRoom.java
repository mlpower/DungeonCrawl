
public class DungeonRoom
{
   private String roomName;
   private String description;
   private Monster monster;
   boolean monsterPresent;
   private boolean[] exits;
   private DungeonRoom[] exitRooms;
   
   public DungeonRoom(String roomName, String description, boolean[] exits)
   {
      this.roomName = roomName;
      this.description = description;
      this.exits = exits;
      monsterPresent = false;
      monster = null;
   }
   
   public DungeonRoom(String roomName, String description, Monster monster,
      boolean[] exits)
   {
      this.roomName = roomName;
      this.description = description;
      this.monster = monster;
      monsterPresent = true;
      this.exits = exits;
   }
   
   public String getName()
   {
      return roomName;
   }
   
   public String getDescription()
   {
      return description;
   }
   
   public Monster getMonster()
   {
      return monster;
   }
   
   public boolean isMonsterPresent()
   {
      return monsterPresent;
   }
   
   public boolean[] getExits()
   {
      return exits;
   }
   
   public DungeonRoom getExitRooms(int index)
   {
      return exitRooms[index];
   }
   
   public void setMonster(Monster monster)
   {
      this.monster = monster;
   }
   
   public void setMonsterPresent(boolean monsterPresent)
   {
      this.monsterPresent = monsterPresent;
   }
   
   public void setExitRooms(DungeonRoom[] exitRooms)
   {
      this.exitRooms = exitRooms;
   }
}
