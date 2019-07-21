package toons;

public abstract class Monster implements Combat
{
   private String name, type, size;
   private int hp, maxDmg;

   public Monster(String name, String type, int hp, String size, int maxDmg)
   {
      this.name = name;
      this.type = type;
      this.hp = hp;
      this.size = size;
      this.maxDmg = maxDmg;
   }

   public String getName()
   {
      return name;
   }

   public int getHp()
   {
      return hp;
   }

   public int getMaxDmg()
   {
      return maxDmg;
   }

   public boolean monsterAttack(Player player)
   {
      boolean loopGame = true;

      int chanceAttack = (int)(Math.random() * 100) + 1;
      if(chanceAttack < 40)
      {
         System.out.println("The " + this.name + " grimaces and " +
                            "mocks your defensive posture.");
      } else
      {
         int chance = (int)(Math.random() * 100) + 1;
         if(chance > 95)
         {
            System.out.println("Ouch! The " + this.name +
                               " strikes you directly for " + this.maxDmg +
                               " hit points.");
            player.setHitPoints(player.getHitPoints() - this.maxDmg);
         } else if(chance > 60)
         {
            System.out.println("The " + this.getName() +
                               " swings and glances you for " + (this.maxDmg / 4) +
                               " hit points.");
            player.setHitPoints(player.getHitPoints() -
                                (this.maxDmg) / 4);
         } else
         {
            System.out.println("The " + this.name + " lunges forward" +
                               " but missed you completely.");
         }
      }

      if(player.getHitPoints() <= 0)
      {
         System.out.println("Aaargh! Your life flashes before you and " +
                            "you pass out!");
         System.out.println("   *** G A M E  O V E R ***   ");
         loopGame = false;
      }

      return loopGame;
   }

   public void setHp(int hp)
   {
      this.hp += hp;
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





