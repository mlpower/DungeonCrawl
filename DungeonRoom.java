
public class DungeonRoom
{
   private String roomName;
   private String description;
   private Monster monster;
   boolean monsterPresent;
   private boolean[] exits;
   private DungeonRoom[] exitRooms;
   
   private boolean specialMove;
   private String specialKey;
   private String specialMoveDisplay;
   
   public DungeonRoom(String roomName, String description, boolean[] exits)
   {
      this.roomName = roomName;
      this.description = description;
      this.exits = exits;
      monsterPresent = false;
      monster = null;
      specialMove = false;
   }
   
   public DungeonRoom(String roomName, String description, Monster monster,
      boolean[] exits)
   {
      this.roomName = roomName;
      this.description = description;
      this.monster = monster;
      monsterPresent = true;
      this.exits = exits;
      specialMove = false;
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
   
   public boolean hasSpecialMove()
   {
      return specialMove;
   }
   
   public String getSpecialKey()
   {
      return specialKey;
   }
   
   public String getSpecialMoveDisplay()
   {
      return specialMoveDisplay;
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
   
   public void setSpecialOption(String specialKey, String specialMoveDisplay)
   {
      specialMove = true;
      this.specialKey = specialKey;
      this.specialMoveDisplay = specialMoveDisplay;
   }
}
