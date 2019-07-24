package characters;

import dungeon.*;

public class Player extends Character
{
   private static final int STRONG = 75;
   private static final int WEAK = 25;
   private static final int MISS = 0;
   private String weapon;
   private int armour;
   private DungeonRoom dungeonRoom;

   public Player(String name, String type, String weapon, int hp,
                 int maxDmg, int armour, DungeonMap dungeonMap)
   {
      super(name, type, hp, maxDmg);
      this.weapon = weapon;
      this.armour = armour;
      this.dungeonRoom = dungeonMap.getRoom(0);

   }

   public String getWeapon()
   {
      return weapon;
   }

   public int getArmour()
   {
      return armour;
   }

   public void attackMonster(Monster monster)
   {
      System.out.println(this.name + " attacks " + monster.getName() +
                         " with a " + this.weapon + ".");
      int attackStrength = attackSuccess();
      if (attackStrength == STRONG)
      {
         int damage = this.maxDmg;
         System.out.println("Direct hit - " + monster.getName() + " has been" +
                            " wounded for " + damage + " points.");
         monster.setHp(-damage);
      }
      else if (attackStrength == WEAK)
      {
         System.out.println("You graze the " + monster.getName() +
                            " for 5 points.");
         monster.setHp(-5);
      }
      else
      {
         System.out.println("You swing and miss.");
      }

      if (monster.getHp() <= 0)
      {
         System.out.println("Victory!!! You have destroyed the " +
                            monster.getName() + "!");
         dungeonRoom.setMonsterPresent(false);
         dungeonRoom.setMonster(null);
      }
   }

   public int attackSuccess()
   {
      boolean success = false;
      int attackStrength;
      int attackRoll = attackCalc();
      if (attackRoll > defendCalc())
         success = true;
      if (success && attackRoll > STRONG)
         attackStrength = STRONG;
      else if (success && attackRoll > WEAK)
         attackStrength = WEAK;
      else
         attackStrength = MISS;
      return attackStrength;
   }

   public String displayPlayerStatus()
   {
      return this.name + " currently has " +
             this.hp + " hit points.\n" +
             this.name + " wields a " + this.weapon
             + " and can do " + this.maxDmg + " damage.";
   }


   public boolean goNorth()
   {
      System.out.println("Go North");
      boolean justEnteredRoom = false;
      if (dungeonRoom.isMonsterPresent() && (dungeonRoom.getExitBlocked() == 1))
      {
         System.out.println("The " + dungeonRoom.getMonster().getName() +
                            " is blocking your way!");
      }
      else
      {
         dungeonRoom = dungeonRoom.getExitRooms(0);
         justEnteredRoom = true;
         if (dungeonRoom.isMonsterPresent())
         {
            System.out.println(dungeonRoom.getMonster().monsterAlertMsg());
         }
      }
      return justEnteredRoom;
   }

   public boolean goEast()
   {
      System.out.println("Go East");
      boolean justEnteredRoom = false;
      if (dungeonRoom.isMonsterPresent() && dungeonRoom.getExitBlocked() == 2)
      {
         System.out.println("The " + dungeonRoom.getMonster().getName() +
                            " is blocking your way!");
      }
      else
      {
         dungeonRoom = dungeonRoom.getExitRooms(1);
         justEnteredRoom = true;
         if (dungeonRoom.isMonsterPresent())
         {
            System.out.println(dungeonRoom.getMonster().monsterAlertMsg());
         }
      }
      return justEnteredRoom;
   }

   public boolean goWest()
   {
      System.out.println("Go West");
      boolean justEnteredRoom = false;
      if (dungeonRoom.isMonsterPresent() && dungeonRoom.getExitBlocked() == 3)
      {
         System.out.println("The " + dungeonRoom.getMonster().getName() +
                            " is blocking your way!");
      }
      else
      {
         dungeonRoom = dungeonRoom.getExitRooms(2);
         justEnteredRoom = true;
         if (dungeonRoom.isMonsterPresent())
         {
            System.out.println(dungeonRoom.getMonster().monsterAlertMsg());
         }
      }
      return justEnteredRoom;
   }

   public boolean goSouth()
   {
      System.out.println("Go South");
      boolean justEnteredRoom = false;
      if (dungeonRoom.isMonsterPresent() && dungeonRoom.getExitBlocked() == 4)
      {
         System.out.println("The " + dungeonRoom.getMonster().getName() +
                            " is blocking your way!");
      }
      else
      {
         dungeonRoom = dungeonRoom.getExitRooms(3);
         justEnteredRoom = true;
         if (dungeonRoom.isMonsterPresent())
         {
            System.out.println(dungeonRoom.getMonster().monsterAlertMsg());
         }
      }
      return justEnteredRoom;
   }

   public DungeonRoom getDungeonRoom()
   {
      return dungeonRoom;
   }
}


