package toons;

public class Troll extends Monster
{
   public Troll(String name)
   {
      super(name, "Troll", 60, "Large", 25);
   }

   public boolean monsterAttack(Player player)
   {
      return super.monsterAttack(player);
   }

}
