package toons;

public class Skeleton extends Monster
{
   public Skeleton(String name){
      super(name, "Skeleton", 80, "small", 12);
   }

   public String getName(){
      return super.getName();
   }

   public boolean monsterAttack(Player player)
   {
      return super.monsterAttack(player);
   }

   public void calcDamage(){

   }
}
