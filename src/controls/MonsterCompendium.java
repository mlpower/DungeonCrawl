package controls;

import characters.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;

public class MonsterCompendium
{
   // name generator strings
   private static String[] beginning = { "Kr", "Ca", "Ra", "Mrok", "Cru",
            "Ray", "Bre", "Zed", "Drak", "Mor", "Jag", "Mer", "Jar", "Mjol",
            "Zork", "Mad", "Cry", "Zur", "Creo", "Azak", "Azur", "Rei", "Cro",
            "Mar", "Luk" };

   private static String[] middle = { "air", "ir", "mi", "sor", "mee", "clo",
            "red", "cra", "ark", "arc", "miri", "lori", "cres", "mur", "zer",
            "marac", "zoir", "slamar", "salmar", "urak" };

   private static String[] end = { "d", "ed", "ark", "arc", "es", "er", "der",
            "tron", "med", "ure", "zur", "cred", "mur" };

   // monster type array
   private static String[] monsterType = { "Orc", "Skeleton", "Vampire",
            "Troll" };

   private static Random rand = new Random();
   // idea borrowed from http://www.java-gaming.org/index.php?topic=35802.0

   private ArrayList<Monster> monsters = new ArrayList<>();

   public MonsterCompendium()
   {
      // this could be changed with a difficulty setting or based on rooms
      // generated etc
      initialiseMonsters(4);
   }

   private void initialiseMonsters(int numberToSpawn)
   {
      for (int i = 0; numberToSpawn >= i; i++)
      {
         switch (assignMonsterType())
         {
            case "Orc":
               this.monsters.add(new Orc(generateName()));
               break;
            case "Skeleton":
               this.monsters.add(new Skeleton(generateName()));
               break;
            case "Vampire":
               this.monsters.add(new Vampire(generateName()));
               break;
            case "Troll":
               this.monsters.add(new Troll(generateName()));
               break;
            default:
               break;
         }
      }
   }

   @NotNull
   private String generateName()
   {
      return beginning[rand.nextInt(beginning.length)] +
             middle[rand.nextInt(middle.length)] +
             end[rand.nextInt(end.length)];
   }

   @NotNull
   private String assignMonsterType()
   {
      return monsterType[rand.nextInt(monsterType.length)];
   }

   public Monster getMonster(int index)
   {
      return monsters.get(index);
   }

   String displayMonsters()
   {
      StringBuilder sb = new StringBuilder();
      sb.append("Monster Compendium\n");
      sb.append("------------------\n");

      for (Monster monster : monsters)
      {
         sb.append(monster.toString());
      }
      return sb.toString();
   }

}
