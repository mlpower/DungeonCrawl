package characters;

import dungeon.DungeonRoom;

public abstract class Character implements Combat
{
   protected String name, type;
   protected int hp, maxDmg;
   private DungeonRoom dungeonRoom;

   public Character(String name, String type, int hp, int maxDmg)
   {
      this.name = name;
      this.type = type;
      this.hp = hp;
      this.maxDmg = maxDmg;
      this.dungeonRoom = null;
   }

   public String getName()
   {
      return name;
   }

   public String getType()
   {
      return type;
   }

   public int getMaxDmg()
   {
      return maxDmg;
   }

   public int getHp()
   {
      return hp;
   }

   public void setHp(int hitP)
   {
      hp += hitP;
   }

   public int attackCalc()
   {
      int chanceAttack = (int) (Math.random() * 100) + 1;
      return chanceAttack;
   }

   public int defendCalc()
   {
      int chanceDefend = (int) (Math.random() * 100) + 1;
      return chanceDefend;
   }

   protected void setDungeonRoom(DungeonRoom dungeonRoom)
   {
      this.dungeonRoom = dungeonRoom;
   }

   protected DungeonRoom getDungeonRoom()
   {
      return dungeonRoom;
   }

}
