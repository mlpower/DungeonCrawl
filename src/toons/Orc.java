package toons;
public class Orc extends Monster
{
   public Orc(String name)
   {
      super(name, "Orc", 100, "large", 35);
   }

   public boolean monsterAttack(Player player)
   {
      return super.monsterAttack(player);
   }

}