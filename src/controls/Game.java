package controls;

import dungeon.DungeonMap;
import dungeon.DungeonRoom;
import toons.Monster;
import toons.Player;

import java.util.Scanner;

public class Game
{
   Scanner scanner;
   Player player;
   String weaponList;

   private MonsterCompendium mc;
   private DungeonRoom currentRoom;
   
   private boolean justEnteredRoom;

   public Game()
   {
      boolean loopGame = true;
      scanner = new Scanner(System.in);

      System.out.println("Welcome explorer.  Please enter your name to begin: ");
      String playerName = scanner.nextLine();

      player = new Player(playerName, "Short Sword", 80, 15, 7);
      mc = new MonsterCompendium();

      DungeonMap dungeonMap = new DungeonMap(mc);
      currentRoom = dungeonMap.getRoom(0);
      
      System.out.println("Dungeon Crawl");
      System.out.println("-------------");
      System.out.println("Welcome");
      
      while(loopGame)
      {
         statusDisplay();
         
         loopGame = menuDisplay();
         
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
                     player.attackMonster(currentRoom.getMonster(), currentRoom);
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
      if(currentRoom.isMonsterPresent() && currentRoom.getExitBlocked() == 1)
      {
         System.out.println("The " + currentRoom.getMonster().getName() +
            " is blocking your way!");
      } else
      {
         currentRoom = currentRoom.getExitRooms(0);
         justEnteredRoom = true;
         if(currentRoom.isMonsterPresent())
         {
            System.out.println("You face death itself in the form of a " +
               currentRoom.getMonster().getName());
         }
      }
   }
   
   private void goEast()
   {
      System.out.println("Go East");
      if(currentRoom.isMonsterPresent() && currentRoom.getExitBlocked() == 2)
      {
         System.out.println("The " + currentRoom.getMonster().getName() +
            " is blocking your way!");
      } else
      {
         currentRoom = currentRoom.getExitRooms(1);
         justEnteredRoom = true;
         if(currentRoom.isMonsterPresent())
         {
            System.out.println("You face death itself in the form of a " +
               currentRoom.getMonster().getName());
         }
      }
   }
   
   private void goWest()
   {
      System.out.println("Go West");
      if(currentRoom.isMonsterPresent() && currentRoom.getExitBlocked() == 3)
      {
         System.out.println("The " + currentRoom.getMonster().getName() +
            " is blocking your way!");
      } else
      {
         currentRoom = currentRoom.getExitRooms(2);
         justEnteredRoom = true;
         if(currentRoom.isMonsterPresent())
         {
            System.out.println("You face death itself in the form of a " +
               currentRoom.getMonster().getName());
         }
      }
   }
   
   private void goSouth()
   {
      System.out.println("Go South");
      if(currentRoom.isMonsterPresent() && currentRoom.getExitBlocked() == 4)
      {
         System.out.println("The " + currentRoom.getMonster().getName() +
            " is blocking your way!");
      } else
      {
         currentRoom = currentRoom.getExitRooms(3);
         justEnteredRoom = true;
         if(currentRoom.isMonsterPresent())
         {
            System.out.println("You face death itself in the form of a " +
               currentRoom.getMonster().getName());
         }
      }
   }


   

   
   private void specialMove(String specialKey)
   {
      switch(specialKey)
      {
         case "D":
            System.out.println("You drink from the fountain...");
            System.out.println("You feel refreshed! Your hit points");
            System.out.println("have been fully replenished.");
            player.setHitPoints(80);
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

   public static void main(String[] args)
   {
      Game game = new Game();
   }

}
