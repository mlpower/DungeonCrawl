
public class Monster
{
   private String monName;
   private String monType;
   private int hp; // could also be double
   private String size; // small, large, huge...
   private int damageDealt; // could also be double

   public Monster(String monName, String monType)
   {
      this.monName = monName;
      this.monType = monType;
      if (monType == "Orc")
      {
         this.hp = 100;
         this.size = "large";
         this.damageDealt = 35;
      }
      else if (monType == "Skeleton")
      {
         this.hp = 80;
         this.size = "small";
         this.damageDealt = 12;
      }

   }

   public String getName()
   {
      return this.monName;
   }

   public String getType()
   {
      return this.monType;
   }

   public int getHp()
   {
      return this.hp;
   }

   public void setHp(int hp)
   {
      this.hp += hp;
   }

}
