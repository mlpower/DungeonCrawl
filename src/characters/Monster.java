package characters;

import dungeon.DungeonRoom;



public abstract class Monster extends Character
{

   private static final int STRONG = 95;
   private static final int WEAK = 60;
   private static final int BLOCKED = 40;
   private static final int MISS = 0;
   private String size;


   public Monster(String name, String type, int hp, String size, int maxDmg)
   {
      super(name, type, hp, maxDmg);
      this.size = size;
   }


   public String getSize()
   {
      return size;
   }

   public int attackSuccess()
   {
      boolean success = false;
      int attackStrength;
      int attackRoll = super.attackCalc();
      if(attackRoll > super.defendCalc())
         success = true;
      if(success && attackRoll > STRONG)
         attackStrength = STRONG;
      else if(success && attackRoll > WEAK)
         attackStrength = WEAK;
      else if(success && attackRoll > MISS)
         attackStrength = BLOCKED;
      else
         attackStrength = MISS;
      return attackStrength;
   }

   public boolean monsterAttack(Player player)
   {
      boolean loopGame = true;
      int attackStrength = attackSuccess();

      if (attackStrength == BLOCKED)
      {
         System.out.println(this.name + " grimaces and " +
                            "mocks your defensive posture.");
      }
      else
      {
         if (attackStrength == STRONG)
         {
            System.out.println("Ouch! The " + this.name +
                               " strikes you directly for " + this.maxDmg +
                               " hit points.");
            player.setHp(player.getHp() - this.maxDmg);
         }
         else if (attackStrength == WEAK)
         {
            System.out.println("The " + this.getName() +
                               " swings and glances you for " + (this.maxDmg / 4) +
                               " hit points.");
            player.setHp(player.getHp() -
                                (this.maxDmg) / 4);
         }
         else
         {
            System.out.println("The " + this.name + " lunges forward" +
                               " but missed you completely.");
         }
      }

      if (player.getHp() <= 0)
      {
         System.out.println("Aaargh! Your life flashes before you and " +
                            "you pass out!");
         System.out.println("   *** G A M E  O V E R ***   ");
         loopGame = false;
      }

      return loopGame;
   }

   public String monsterAlertMsg()
   {
      return "You face death itself in the form of " +
             this.name + ", a " +
             this.size + " " +
             this.type;

   }

   public DungeonRoom getDungeonRoom()
   {
      return super.getDungeonRoom();
   }

   public void setDungeonRoom(DungeonRoom dungeonRoom){
      super.setDungeonRoom(dungeonRoom);
   }

   @Override
   public String toString()
   {
      return "Monster name: " + name + "\n" +
             "Monster type: " + type + "\n" +
             "Size: " + size + "\n" +
             "HP: " + hp + "\n" +
             "Maximum damage dealt: " + maxDmg +
             "\n\n";
   }

}





