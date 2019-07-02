
import java.util.Scanner;

public class Game
{
   Monster mon;
   String weaponList;

   public Game()
   {
      this.weaponList = "Sword\nHands\n";

      mon = new Monster("Man Slayer", "Orc");

      Scanner sc = new Scanner(System.in);

      System.out.println("\"" + this.mon.getName() +
                         "\" Enters the arena! Watch out for this badass " +
                         this.mon.getType() + "!");
      System.out.println(this.mon.getHp());
      System.out
               .println("You strike the monster with:\n\n" + this.weaponList + "?:");
      String choice = sc.nextLine();

      int attack = weaponStrike(choice);
      if (attack == -1)
         System.out.println("You fumble around and miss the chance");
      else
      {
         this.mon.setHp(-weaponStrike(choice));
         System.out.println("Hit! " + this.mon.getName() + " is down to " +
                            this.mon.getHp());
      }

   }

   public int weaponStrike(String weapon)
   {
      int force;
      if (weapon.equalsIgnoreCase("sword"))
         force = 10;
      else if (weapon.equalsIgnoreCase("hands"))
         force = 3;
      else
         force = -1;

      return force;
   }

   public String addWeapon(String newWeapon)
   {
      this.weaponList += newWeapon;
      return newWeapon + " added!";
   }

   public static void main(String[] args)
   {
      Game game = new Game();
   }

}
