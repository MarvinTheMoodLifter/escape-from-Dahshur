import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class Game
{
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private static final int CONSOLE_WIDTH = 80;  // Larghezza della console

    private static void clearScreen() { for (int i = 0; i < 20; ++i) System.out.println(); }

    private static void printCentered(String text)
    {
        int padding = (CONSOLE_WIDTH - text.length()) / 2;
        for (int i = 0; i < padding; i++) System.out.print(" ");
        System.out.println(text);
    }

    private static void printCentered(String text, String color)
    {
        int padding = (CONSOLE_WIDTH - text.length()) / 2;
        for (int i = 0; i < padding; i++) System.out.print(" ");
        System.out.println(color + text + ANSI_RESET);
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Pyramid pyramid = new Pyramid();
        Main_Character hero = new Main_Character("Hero", 100, 50, 100, 0, 0);
        boolean inCombat = false;

        while (true)
        {
            int[] currentPosition = hero.getCurrentPosition();
            pyramid.describeRoom(currentPosition[0], currentPosition[1]);

            // Menu per interazione utente
            printCentered("Available actions:", ANSI_YELLOW);
            printCentered("inspect [item_name]", ANSI_GREEN);
            printCentered("talk to [npc_name]", ANSI_GREEN);
            printCentered("attack [npc_name]", ANSI_GREEN);
            printCentered("move [direction]", ANSI_GREEN);
            printCentered("view inventory", ANSI_GREEN);
            printCentered("save game", ANSI_GREEN);
            printCentered("load game [hero_name]", ANSI_GREEN);
            printCentered("type 'exit' to quit.", ANSI_RED);
            System.out.print("Enter action: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) { break; }
            else if (input.equalsIgnoreCase("view inventory"))
            {
                hero.printInventory();

                // Sottomenu per l'inventario
                System.out.println();
                printCentered("You are viewing your inventory.", ANSI_RED);
                printCentered("What would you like to do next?", ANSI_RED);
                printCentered("equip [item_name]", ANSI_GREEN);
                printCentered("back", ANSI_BLUE);
                System.out.print("Enter action: ");
                String subInput = scanner.nextLine();

                if (subInput.toLowerCase().startsWith("equip "))
                {
                    String itemName = subInput.substring(6).trim();
                    hero.equipItem(itemName);
                }
                else if (subInput.equalsIgnoreCase("back"))
                {
                    System.out.println(ANSI_CYAN + "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" + ANSI_RESET);
                    continue; // Ritorna al menu principale
                }
                else { printCentered("Unknown command. Returning to main menu."); }
            }
            else if (input.toLowerCase().startsWith("inspect "))
            {
                if (inCombat) { printCentered(ANSI_YELLOW + "You cannot inspect items during combat. You must 'attack' or 'flee'." + ANSI_RESET); }
                else
                {
                    String itemName = input.substring(8).trim();
                    hero.inspectItemByName(pyramid.getRoom(currentPosition[0], currentPosition[1]), itemName);

                    // Sottomenu per l'oggetto ispezionato
                    System.out.println();
                    printCentered("You inspected the " + itemName + ".", ANSI_RED);
                    printCentered("What would you like to do next?", ANSI_RED);
                    printCentered("take " + itemName, ANSI_GREEN);
                    printCentered("back", ANSI_BLUE);
                    System.out.print("Enter action: ");
                    String subInput = scanner.nextLine();

                    if (subInput.toLowerCase().startsWith("take "))
                    {
                        String takeItemName = subInput.substring(5).trim();
                        if (takeItemName.equalsIgnoreCase(itemName))
                        {
                            Item item = pyramid.getRoom(currentPosition[0], currentPosition[1]).findItemByName(takeItemName);
                            if (item != null) { hero.takeItem(item, pyramid.getRoom(currentPosition[0], currentPosition[1])); }
                            else { printCentered("No item named '" + takeItemName + "' found in the room."); }
                        }
                        else { printCentered("Item name does not match the inspected item."); }
                    }
                    else if (subInput.equalsIgnoreCase("back"))
                    {
                        System.out.println(ANSI_CYAN + "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" + ANSI_RESET);
                        continue; // Ritorna al menu principale
                    }
                    else { printCentered("Unknown command. Returning to main menu."); }
                }
            }
            else if (input.toLowerCase().startsWith("talk to "))
            {
                if (inCombat) { printCentered(ANSI_YELLOW + "You cannot talk during combat. You must 'attack' or 'flee'." + ANSI_RESET); }
                else
                {
                    String npcName = input.substring(8).trim();
                    pyramid.getRoom(currentPosition[0], currentPosition[1]).talkToNPC(npcName);

                    // Sottomenu per l'NPC
                    System.out.println();
                    printCentered("You talked to " + npcName + ".", ANSI_RED);
                    printCentered("What would you like to do next?", ANSI_RED);
                    printCentered("attack " + npcName, ANSI_GREEN);
                    printCentered("back", ANSI_BLUE);
                    System.out.print("Enter action: ");
                    String subInput = scanner.nextLine();

                    if (subInput.toLowerCase().startsWith("attack "))
                    {
                        String attackNPCName = subInput.substring(7).trim();
                        if (attackNPCName.equalsIgnoreCase(npcName))
                        {
                            NPC npc = pyramid.getRoom(currentPosition[0], currentPosition[1]).findNPCByName(attackNPCName);
                            if (npc != null && npc.isAlive())
                            {
                                hero.attack(npc);
                                inCombat = true;
                                if (npc.isAlive())
                                {
                                    npc.attack(hero, 10);
                                    if (!hero.isAlive())
                                    {
                                        printCentered(ANSI_RED + "You have died." + ANSI_RESET);
                                        break;
                                    }
                                }
                            }
                            else if (npc != null && !npc.isAlive())
                            {
                                printCentered(npc.getName() + " is already dead and cannot be attacked.");
                                inCombat = false;
                            }
                            else { printCentered("No NPC named '" + attackNPCName + "' found in the room."); }
                        }
                        else { printCentered("NPC name does not match the talked NPC."); }
                    }
                    else if (subInput.equalsIgnoreCase("back"))
                    {
                        System.out.println(ANSI_CYAN + "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" + ANSI_RESET);
                        continue; // Ritorna al menu principale
                    }
                    else { printCentered("Unknown command. Returning to main menu."); }
                }
            }
            else if (input.equalsIgnoreCase("flee"))
            {
                if (inCombat)
                {
                    printCentered(ANSI_BLUE + "You flee from the combat!" + ANSI_RESET);
                    printCentered("Available actions: 'inspect the [item_name]', 'equip [item_name]', 'talk to [npc_name]', 'attack [npc_name]', or type 'exit' to quit.");
                    inCombat = false;
                }
                else { printCentered("You are not in combat."); }
            }
            else if (input.toLowerCase().startsWith("move "))
            {
                String direction = input.substring(5).trim();
                int[] previousPosition = hero.getCurrentPosition().clone();
                hero.move(pyramid, direction);
                if (hero.getHasMoved())
                {
                    int[] newPosition = hero.getCurrentPosition();
                    if (previousPosition[0] != newPosition[0] || previousPosition[1] != newPosition[1]) { printCentered("You moved " + direction + "."); }
                    else { printCentered("You hit a wall. You are still in the same room."); }
                }
                else { printCentered("You cannot move " + direction + "."); }
            }
            else if (input.equalsIgnoreCase("save game"))
            {
              saveGame(hero, pyramid);
            }
            else if (input.toLowerCase().startsWith("load game "))
            {
              loadGame(hero, pyramid);
            }
            else { printCentered("Unknown command. Please try again."); }

            // Pausa dopo ogni azione
            printCentered("Press Enter to continue...", ANSI_RED);
            scanner.nextLine();
            System.out.println(ANSI_CYAN + "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" + ANSI_RESET);

            // Descrizione della stanza corrente aggiornata
            pyramid.describeRoom(currentPosition[0], currentPosition[1]);
        }

        scanner.close();
    }

  private static void saveGame(Main_Character hero, Pyramid pyramid)
  {
    printCentered("Saving game...", ANSI_CYAN);
    try {
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      FileWriter writer = new FileWriter("savegame.json");
      writer.write("{\n");
      writer.write("\"hero\": " + gson.toJson(hero) + ",\n");
      writer.write("\"pyramid\": " + gson.toJson(pyramid) + "\n");
      writer.write("}");
      writer.close();
      printCentered("Game saved successfully.", ANSI_GREEN);
    } catch (IOException e) {
      printCentered("Error saving game: " + e.getMessage(), ANSI_RED);
    }
  }

  private static void loadGame(Main_Character hero, Pyramid pyramid)
  {
    try {
      Gson gson = new Gson();
      FileReader reader = new FileReader("savegame.json");
      JsonObject root = gson.fromJson(reader, JsonObject.class);
      reader.close();

      Main_Character loadedHero = gson.fromJson(root.get("hero"), Main_Character.class);
      Pyramid loadedPyramid = gson.fromJson(root.get("pyramid"), Pyramid.class);

      // Update current hero and pyramid with loaded data
      hero.updateFrom(loadedHero);
      pyramid.updateFrom(loadedPyramid);

      printCentered("Game loaded successfully.", ANSI_GREEN);
    } catch (IOException e) {
      printCentered("Error loading game: " + e.getMessage(), ANSI_RED);
    }
  }
}
