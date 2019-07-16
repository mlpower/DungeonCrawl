
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
}
