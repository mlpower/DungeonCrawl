package toons;

import dungeon.DungeonRoom;

public class Player
{
   private String name;
   private String weapon;
   private int hitPoints;
   private int attackStrength;
   private int armour;
   
   public Player(String name, String weapon, int hitPoints,
      int attackStrength, int armour)
   {
      this.name = name;
      this.weapon = weapon;
      this.hitPoints = hitPoints;
      this.attackStrength = attackStrength;
      this.armour = armour;
   }
   
   public String getName()
   {
      return name;
   }
   
   public String getWeapon()
   {
      return weapon;
   }
   
   public int getHitPoints()
   {
      return hitPoints;
   }
   
   public int getAttackStrength()
   {
      return attackStrength;
   }
   
   public int getArmour()
   {
      return armour;
   }
   
   public void setHitPoints(int hitPoints)
   {
      this.hitPoints = hitPoints;
   }

   public void attackMonster(Monster monster, DungeonRoom currentRoom)
   {

      System.out.println(this.name + " attacks " + monster.getName() +
                         " with a " + this.weapon + ".");
      int chance = (int)(Math.random() * 100) + 1;
      if(chance > 75)
      {
         int damage = this.attackStrength;
         System.out.println("Direct hit - " + monster.getName() + " has been" +
                            " wounded for " + damage + " points.");
         monster.setHp(-damage);
      } else if(chance > 25)
      {
         System.out.println("You graze the " + monster.getName() +
                            " for 5 points.");
         monster.setHp(-5);
      } else
      {
         System.out.println("You swing and miss.");
      }

      if(monster.getHp() <= 0)
      {
         System.out.println("Victory!!! You have destroyed the " +
                            monster.getName() + "!");
         currentRoom.setMonsterPresent(false);
         currentRoom.setMonster(null);
      }
   }
}
