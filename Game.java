
import java.util.ArrayList;
import java.util.Scanner;

public class Game
{
   Scanner scanner;
   
   ArrayList<Monster> monsters;
   Player player;
   String weaponList;
   
   ArrayList<DungeonRoom> dungeonMap;
   DungeonRoom currentRoom;
   
   boolean justEnteredRoom;

   public Game()
   {
      boolean loopGame = true;
      scanner = new Scanner(System.in);
      
      player = new Player("Player 1", "Short Sword", 80, 15, 7);
      initialiseMonsters();
      initialiseDungeon();
      currentRoom = dungeonMap.get(0);
      
      System.out.println("Dungeon Crawl");
      System.out.println("-------------");
      System.out.println("Welcome");
      
      while(loopGame)
      {
         statusDisplay();
         
         loopGame = menuDisplay();
         
         if(loopGame && !justEnteredRoom && currentRoom.isMonsterPresent())
            loopGame = monsterAttack();
         
         justEnteredRoom = false;
      }
      
      System.out.println("Exiting Game.");
      scanner.close();
   }
   
   private void statusDisplay()
   {
      System.out.println();
      System.out.println(currentRoom.getName());
      System.out.println(currentRoom.getDescription());
      if(currentRoom.isMonsterPresent())
      {
         Monster monster = currentRoom.getMonster();
         System.out.println("The " + monster.getName() +
            " taunts you menacingly.");
         System.out.println("It has " + monster.getHp() + " hit points.");
      }
      System.out.println();
   }
   
   private boolean menuDisplay()
   {
      boolean loopGame = true;
      boolean[] exits = currentRoom.getExits();
      boolean monsterPresent = currentRoom.isMonsterPresent();
      
      System.out.println("Your Options:");
      System.out.println("-------------");
      
      if(currentRoom.hasSpecialMove())
      {
         System.out.println(currentRoom.getSpecialMoveDisplay());
      }
      
      if(monsterPresent)
      {
         System.out.println("A: Attack monster");
      }
      
      System.out.println("H: Health and Inventory");
      System.out.println("L: Look at your surroundings");
      
      if(exits[0])
      {
         System.out.println("N: Go North");
      }
      if(exits[1])
      {
         System.out.println("E: Go East");
      }
      if(exits[2])
      {
         System.out.println("W: Go West");
      }
      if(exits[3])
      {
         System.out.println("S: Go South");
      }
      
      System.out.println("R: Read monster compendium");
      System.out.println("X: Exit Game");
      
      boolean validInput = false;
      String selection = "";
      do
      {
         System.out.print("Enter selection: ");
         selection = scanner.nextLine();
         selection = selection.toUpperCase();
         if(selection.length() != 1)
         {
            System.out.println("Error - invalid input.");
         } else if(currentRoom.hasSpecialMove() &&
            selection.equals(currentRoom.getSpecialKey()))
         {
            validInput = true;
            specialMove(currentRoom.getSpecialKey());
         } else
         {
            switch(selection)
            {
               case "A":
                  if(monsterPresent)
                  {
                     validInput = true;
                     attackMonster();
                  } else
                  {
                     System.out.println("Error - invalid input.");
                  }
                  break;
               case "H":
                  validInput = true;
                  displayPlayerStatus();
                  break;
               case "L":
                  validInput = true;
                  System.out.println("Looking...");
                  break;
               case "N":
                  if(exits[0])
                  {
                     validInput = true;
                     goNorth();
                  } else
                  {
                     System.out.println("Error - invalid input.");
                  }
                  break;
               case "E":
                  if(exits[1])
                  {
                     validInput = true;
                     goEast();
                  } else
                  {
                     System.out.println("Error - invalid input.");
                  }
                  break;
               case "W":
                  if(exits[2])
                  {
                     validInput = true;
                     goWest();
                  } else
                  {
                     System.out.println("Error - invalid input.");
                  }
                  break;
               case "S":
                  if(exits[3])
                  {
                     validInput = true;
                     goSouth();
                  } else
                  {
                     System.out.println("Error - invalid input.");
                  }
                  break;
               case "R":
                  validInput = true;
                  System.out.println("You consult your monster compendium...");
                  displayMonsters();
                  break;
               case "X":
                  validInput = true;
                  loopGame = false;
                  break;
               default:
                  System.out.println("Error - invalid input.");
                  break;
            }
         }
      } while(!validInput);
      
      return loopGame;
   }
   
   private void attackMonster()
   {
      Monster monster = currentRoom.getMonster();
      System.out.println(player.getName() + " attacks " + monster.getName() +
         " with a " + player.getWeapon() + ".");
      int chance = (int)(Math.random() * 100) + 1;
      if(chance > 75)
      {
         int damage = player.getAttackStrength();
         System.out.println("Direct hit - " + monster.getName() + " has been" +
            " wounded for " + damage + " points.");
         monster.setHp(-damage);
      } else if(chance > 25)
      {
         System.out.println("You graze the " + monster.getName() +
            " for 5 points.");
         monster.setHp(-5);
      } else
      {
         System.out.println("You swing and miss.");
      }
      
      if(monster.getHp() <= 0)
      {
         System.out.println("Victory!!! You have destroyed the " +
            monster.getName() + "!");
         currentRoom.setMonsterPresent(false);
         currentRoom.setMonster(null);
      }
   }
   
   private void displayPlayerStatus()
   {
      System.out.println(player.getName() + " currently has " +
         player.getHitPoints() + " hit points.");
      System.out.println(player.getName() + " wields a " + player.getWeapon()
         + " and can do " + player.getAttackStrength() + " damage.");
   }
   
   private void goNorth()
   {
      System.out.println("Go North");
      currentRoom = currentRoom.getExitRooms(0);
      justEnteredRoom = true;
      if(currentRoom.isMonsterPresent())
      {
         System.out.println("You face death itself in the form of a " +
            currentRoom.getMonster().getName());
      }
   }
   
   private void goEast()
   {
      System.out.println("Go East");
      currentRoom = currentRoom.getExitRooms(1);
      justEnteredRoom = true;
      if(currentRoom.isMonsterPresent())
      {
         System.out.println("You face death itself in the form of a " +
            currentRoom.getMonster().getName());
      }
   }
   
   private void goWest()
   {
      System.out.println("Go West");
      currentRoom = currentRoom.getExitRooms(2);
      justEnteredRoom = true;
      if(currentRoom.isMonsterPresent())
      {
         System.out.println("You face death itself in the form of a " +
            currentRoom.getMonster().getName());
      }
   }
   
   private void goSouth()
   {
      System.out.println("Go South");
      currentRoom = currentRoom.getExitRooms(3);
      justEnteredRoom = true;
      if(currentRoom.isMonsterPresent())
      {
         System.out.println("You face death itself in the form of a " +
            currentRoom.getMonster().getName());
      }
   }
   
   private void initialiseMonsters()
   {
      monsters = new ArrayList<Monster>();
      
      Monster monster = new Monster("Man Slayer", "Orc");
      monsters.add(monster);
      
      monster = new Monster("Gargantuan", "Orc");
      monsters.add(monster);
      
      monster = new Monster("Akaviri Warrior", "Skeleton");
      monsters.add(monster);
      
      monster = new Monster("Undead Soldier", "Skeleton");
      monsters.add(monster);
   }
   
   private void initialiseDungeon()
   {
      dungeonMap = new ArrayList<DungeonRoom>();
      
      DungeonRoom room = new DungeonRoom("Foyer",
         "A bland cave opening with faintly carved Mayan iconography.",
         new boolean[]{true, false, false, false});
      dungeonMap.add(room);
      
      room = new DungeonRoom("Hallway",
         "A narrow passage dimly lit by lanterns - ominous shadows\nplay " +
         "on the walls.",
         new boolean[]{true, false, false, true});
      dungeonMap.add(room);
      
      room = new DungeonRoom("Arena",
         "A large area with a motley crowd jeering from the grandstands.",
         monsters.get(0), new boolean[]{false, true, false, true});
      dungeonMap.add(room);
      
      room = new DungeonRoom("Secret Passage",
         "An corridor leading east from a secret passage in the\narena, " +
         "which is to the west.", new boolean[]{false, true, true, false});
      dungeonMap.add(room);
      
      room = new DungeonRoom("Underground Temple",
         "Hollowed out from the rock is a temple sanctuary with a\n" +
         "drinking fountain.", new boolean[]{true, false, true, false});
      dungeonMap.add(room);
      
      room = new DungeonRoom("Ancient Crypt",
         "An alcove with a series of old tombs. You are overwhelmed\n" +
         "with a sense of dark foreboding.", monsters.get(3),
         new boolean[]{false, false, false, true});
      dungeonMap.add(room);
      
      dungeonMap.get(0).setExitRooms(new DungeonRoom[]{dungeonMap.get(1), null,
         null, null});
      dungeonMap.get(1).setExitRooms(new DungeonRoom[]{dungeonMap.get(2), null,
         null, dungeonMap.get(0)});
      dungeonMap.get(2).setExitRooms(new DungeonRoom[]{null, dungeonMap.get(3),
         null, dungeonMap.get(1)});
      dungeonMap.get(3).setExitRooms(new DungeonRoom[]{null, dungeonMap.get(4),
         dungeonMap.get(2), null});
      dungeonMap.get(4).setExitRooms(new DungeonRoom[]{dungeonMap.get(5), null,
         dungeonMap.get(3), null});
      dungeonMap.get(5).setExitRooms(new DungeonRoom[]{null, null,
         null, dungeonMap.get(4)});
      
      dungeonMap.get(4).setSpecialOption("D", "D: Drink from fountain");
   }
   
   private boolean monsterAttack()
   {
      boolean loopGame = true;
      Monster monster = currentRoom.getMonster();
      int chanceAttack = (int)(Math.random() * 100) + 1;
      if(chanceAttack < 40)
      {
         System.out.println("The " + monster.getName() + " grimaces and " +
            "taunts you.");
      } else
      {
         int chance = (int)(Math.random() * 100) + 1;
         if(chance > 90)
         {
            System.out.println("Ouch! The " + monster.getName() +
               " strikes you directly for " + monster.getDamage() +
               " hit points.");
            player.setHitPoints(player.getHitPoints() - monster.getDamage());
         } else if(chance > 50)
         {
            System.out.println("The " + monster.getName() +
               " swings and glances you for " + (monster.getDamage() / 4) +
               " hit points.");
            player.setHitPoints(player.getHitPoints() -
               (monster.getDamage() / 4));
         } else
         {
            System.out.println("The " + monster.getName() + " lunges forward" +
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
   
   private void displayMonsters()
   {
      System.out.println("Monster Compendium");
      System.out.println("------------------");
      
      for (Monster monster: monsters)
      {
         System.out.println(monster.getName());
         System.out.println("Monster type: " + monster.getType());
         System.out.println("Size: " + monster.getSize());
         System.out.println("Maximum damage dealt: " + monster.getDamage());
         System.out.println();
      }
   }
   
   private void specialMove(String specialKey)
   {
      if(specialKey.equals("D"))
      {
         System.out.println("You drink from the fountain...");
         System.out.println("You feel fully refreshed! Your hit points have");
         System.out.println("been fully replenished.");
         player.setHitPoints(80);
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
