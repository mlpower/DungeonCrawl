package toons;

public class Vampire extends Monster
{
   public Vampire(String name){
      super(name, "Vampire", 150, "medium", 20);
   }

   public boolean monsterAttack(Player player)
   {
      return super.monsterAttack(player);
   }

}
