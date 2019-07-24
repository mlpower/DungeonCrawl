package controls;

import dungeon.DungeonMap;
import dungeon.DungeonRoom;
import characters.Monster;
import characters.Player;

import java.util.Scanner;

public class Game
{
   Scanner scanner;
   Player player;
   String weaponList;

   private MonsterCompendium mc;
   private DungeonRoom currentRoom;
   private DungeonMap dungeonMap;
   
   private boolean justEnteredRoom;

   public Game()
   {
      boolean loopGame = true;
      scanner = new Scanner(System.in);

      System.out.println("Welcome explorer.  Please enter your name to begin: ");
      String playerName = scanner.nextLine();
      String type = selectRace();

      mc = new MonsterCompendium();
      dungeonMap = new DungeonMap(mc);

      player = new Player(playerName, type, "Short Sword", 80, 15, 10, dungeonMap);



      
      System.out.println("Dungeon Crawl");
      System.out.println("-------------");
      System.out.println("Welcome");
      
      while(loopGame)
      {
         currentRoom = player.getDungeonRoom();
         System.out.println(currentRoom.statusDisplay());
         
         loopGame = menuDisplay(dungeonMap, currentRoom);
         
         if(loopGame && !justEnteredRoom && currentRoom.isMonsterPresent())
         {
            Monster monster = currentRoom.getMonster();
            loopGame = monster.monsterAttack(player);
         }

         justEnteredRoom = false;
      }
      
      System.out.println("Exiting Game.");
      scanner.close();
   }
   
   private boolean menuDisplay(DungeonMap dungeonMap, DungeonRoom currentRoom)
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
      String selection;
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
                     player.attackMonster(currentRoom.getMonster());
                  } else
                  {
                     System.out.println("Error - invalid input.");
                  }
                  break;
               case "H":
                  validInput = true;
                  System.out.println(player.displayPlayerStatus());
                  break;
               case "L":
                  validInput = true;
                  System.out.println("Looking...");
                  break;
               case "N":
                  if(exits[0])
                  {
                     validInput = true;
                     justEnteredRoom = player.goNorth();
                  } else
                  {
                     System.out.println("Error - invalid input.");
                  }
                  break;
               case "E":
                  if(exits[1])
                  {
                     validInput = true;
                     justEnteredRoom = player.goEast();
                  } else
                  {
                     System.out.println("Error - invalid input.");
                  }
                  break;
               case "W":
                  if(exits[2])
                  {
                     validInput = true;
                     justEnteredRoom = player.goWest();
                  } else
                  {
                     System.out.println("Error - invalid input.");
                  }
                  break;
               case "S":
                  if(exits[3])
                  {
                     validInput = true;
                     justEnteredRoom = player.goSouth();
                  } else
                  {
                     System.out.println("Error - invalid input.");
                  }
                  break;
               case "R":
                  validInput = true;
                  System.out.println("You consult your monster compendium...");
                  System.out.println(mc.displayMonsters());
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

   private void specialMove(String specialKey)
   {
      switch(specialKey)
      {
         case "D":
            System.out.println("You drink from the fountain...");
            System.out.println("You feel refreshed! Your hit points");
            System.out.println("have been fully replenished.");
            player.setHp(80);
            break;
         case "T":
            System.out.println("You hear the faint sound of mechanical");
            System.out.println("gears moving...");
            System.out.println("A Gargantuan falls down from a valve in");
            System.out.println("the ceiling!");
            currentRoom.setMonster(mc.getMonster(1));
            currentRoom.setMonsterPresent(true);
            currentRoom.turnOffSpecial();
            currentRoom.setExitBlocked(1);
            break;
         default:
            System.out.println("Aren't you clever. You're not supposed");
            System.out.println("to reach this point.");
            break;
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

   public String selectRace()
   {
      String type = "";
      boolean condition = false;
      do
      {
         System.out.println("Please choose a race: ");
         System.out.println("A: Human");
         System.out.println("B: Elf");
         System.out.println("C: Dwarf");
         System.out.println("Enter Selection: ");
         String input = scanner.nextLine();
         switch(input.toLowerCase())
         {
            case "a":
               type = "Human";
               condition = true;
               break;
            case "b":
               type = "Elf";
               condition = true;
               break;
            case "c":
               type = "Dwarf";
               condition = true;
               break;
            default:
               System.out.println("Error: invalid selection, please try again");
               break;
         }
      } while(!condition);
      return type;
   }
   public static void main(String[] args)
   {
      Game game = new Game();
   }

}
