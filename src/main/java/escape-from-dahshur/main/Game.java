import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Game {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private static final int CONSOLE_WIDTH = 80; // Larghezza della console

    private static void clearScreen() {
        for (int i = 0; i < 8; ++i)
            System.out.println();
    }

    private static void printCentered(String text) {
        int padding = (CONSOLE_WIDTH - text.length()) / 2;
        for (int i = 0; i < padding; i++)
            System.out.print(" ");
        System.out.println(text);
    }

    private static void printCentered(String text, String color) {
        int padding = (CONSOLE_WIDTH - text.length()) / 2;
        for (int i = 0; i < padding; i++)
            System.out.print(" ");
        System.out.println(color + text + ANSI_RESET);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Pyramid pyramid = new Pyramid();
        Main_Character hero = new Main_Character("Hero", 100, 50, 100, 0, 2);
        boolean inCombat = false;

        pyramid.printRoomLayout();
        while (true) {
            int[] currentPosition = hero.getCurrentPosition();
            clearScreen();
            pyramid.describeRoom(currentPosition[1], currentPosition[0]);

            // Menu per interazione utente
            printCentered("Available actions:", ANSI_YELLOW);
            printCentered("inspect [item_name]", ANSI_GREEN);
            printCentered("talk to [npc_name]", ANSI_GREEN);
            printCentered("attack [npc_name]", ANSI_GREEN);
            printCentered("move [direction]", ANSI_GREEN);
            printCentered("view inventory", ANSI_GREEN);
            if(hero.getCurrentPosition()[1]==1&&hero.getCurrentPosition()[0]==1){
                printCentered("type 'enter hole' to enter the hole.", ANSI_GREEN);
            }
            if(hero.getCurrentPosition()[1]==0&&hero.getCurrentPosition()[0]==0){
                printCentered("type 'leave pyramid' to attmept to leave the pyramid.", ANSI_GREEN);
            }
            if(hero.getCurrentPosition()[1]==1&&hero.getCurrentPosition()[0]==2){
                printCentered("type 'open chest' to open the chest.", ANSI_GREEN);
            }
             printCentered("save game", ANSI_CYAN);
            printCentered("load game [hero_name]", ANSI_CYAN);
            printCentered("type 'exit' to quit.", ANSI_RED);
            System.out.print("Enter action: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                break;
            } else if (input.equalsIgnoreCase("view inventory")) {
                hero.printInventory();

                // Sottomenu per l'inventario
                System.out.println();
                printCentered("You are viewing your inventory.", ANSI_RED);
                printCentered("What would you like to do next?", ANSI_RED);
                printCentered("equip [item_name]", ANSI_GREEN);
                printCentered("back", ANSI_BLUE);
                System.out.print("Enter action: ");
                String subInput = scanner.nextLine();

                if (subInput.toLowerCase().startsWith("equip ")) {
                    String itemName = subInput.substring(6).trim();
                    hero.equipItem(itemName);
                } else if (subInput.equalsIgnoreCase("back")) {
                    System.out.println(
                            ANSI_CYAN +
                                    ("-------------------------------------------------------------" +
                                            "-------------------------------------------------------------" +
                                            "------------------------------------------------------\n") +
                                    ANSI_RESET);
                    continue; // Ritorna al menu principale
                } else {
                    printCentered("Unknown command. Returning to main menu.");
                }
            } else if (input.toLowerCase().startsWith("inspect ")) {
                if (inCombat) {
                    printCentered(ANSI_YELLOW +
                            ("You cannot inspect items during combat. You must " +
                                    "'attack' or 'flee'.") +
                            ANSI_RESET);
                } else {
                    String itemName = input.substring(8).trim();
                    hero.inspectItemByName(
                            pyramid.getRoom(currentPosition[1], currentPosition[0]),
                            itemName);

                    // Sottomenu per l'oggetto ispezionato
                    System.out.println();
                    System.out.println("-------------------------------------------------------------" +
                            "-------------------------------------------------------------" +
                            "------------------------------------------------------");
                    printCentered("You inspected the " + itemName + ".", ANSI_RED);
                    printCentered("What would you like to do next?", ANSI_RED);
                    printCentered("take " + itemName, ANSI_GREEN);
                    printCentered("back", ANSI_BLUE);
                    System.out.print("Enter action: ");
                    String subInput = scanner.nextLine();

                    if (subInput.toLowerCase().startsWith("take ")) {
                        String takeItemName = subInput.substring(5).trim();
                        if (takeItemName.equalsIgnoreCase(itemName)) {
                            Item item =
                                    pyramid.getRoom(currentPosition[1], currentPosition[0])
                                            .findItemByName(takeItemName);
                            if (item != null) {
                                hero.takeItem(item, pyramid.getRoom(currentPosition[1],
                                        currentPosition[0]));
                            } else {
                                printCentered("No item named '" + takeItemName +
                                        "' found in the room.");
                            }
                        } else {
                            printCentered("Item name does not match the inspected item.");
                        }
                    } else if (subInput.equalsIgnoreCase("back")) {
                        System.out.println(
                                ANSI_CYAN +
                                        ("-----------------------------------------------------------" +
                                                "-----------------------------------------------------------" +
                                                "----------------------------------------------------------" +
                                                "\n") +
                                        ANSI_RESET);
                        continue; // Ritorna al menu principale
                    } else {
                        printCentered("Unknown command. Returning to main menu.");
                    }
                }
            } else if (input.toLowerCase().startsWith("talk to ")) {
                if (inCombat) {
                    printCentered(
                            ANSI_YELLOW +
                                    "You cannot talk during combat. You must 'attack' or 'flee'." +
                                    ANSI_RESET);
                } else {
                    String npcName = input.substring(8).trim();
                    pyramid.getRoom(currentPosition[1], currentPosition[0])
                            .talkToNPC(npcName);

                    // Sottomenu per l'NPC
                    System.out.println();
                    printCentered("You talked to " + npcName + ".", ANSI_RED);
                    printCentered("What would you like to do next?", ANSI_RED);
                    printCentered("attack " + npcName, ANSI_GREEN);
                    if(hero.getCurrentPosition()[1]==2&&hero.getCurrentPosition()[0]==0){
                        printCentered("type 'free lost explorer' to attempt to free the lost explorer.", ANSI_GREEN);
                    }
                    printCentered("back", ANSI_BLUE);
                    System.out.print("Enter action: ");
                    String subInput = scanner.nextLine();

                    if (subInput.toLowerCase().startsWith("attack ")) {
                        String attackNPCName = subInput.substring(7).trim();
                        if (attackNPCName.equalsIgnoreCase(npcName)) {
                            NPC npc = pyramid.getRoom(currentPosition[1], currentPosition[0])
                                    .findNPCByName(attackNPCName);
                            if (npc != null && npc.isAlive()) {
                                hero.attack(npc);
                                inCombat = true;
                                if (npc.isAlive()) {
                                    npc.attack(hero, npc.getPower());
                                    if (!hero.isAlive()) {
                                        printCentered(ANSI_RED + "You have died." + ANSI_RESET);
                                        GameEnd(hero);
                                        break;
                                    }
                                }
                            } else if (npc != null && !npc.isAlive()) {
                                printCentered(npc.getName() +
                                        " is already dead and cannot be attacked.");
                                inCombat = false;
                            } else {
                                printCentered("No NPC named '" + attackNPCName +
                                        "' found in the room.");
                            }
                        } else {
                            printCentered("NPC name does not match the talked NPC.");
                        }
                    } else if (subInput.equalsIgnoreCase("back")) {
                        System.out.println(
                                ANSI_CYAN +
                                        ("-----------------------------------------------------------" +
                                                "-----------------------------------------------------------" +
                                                "----------------------------------------------------------" +
                                                "\n") +
                                        ANSI_RESET);
                        continue; // Ritorna al menu principale
                    } else if(subInput.toLowerCase().startsWith("free ")&&(hero.getCurrentPosition()[1]==1&&hero.getCurrentPosition()[0]==1)) {
                        String interactednpcname = subInput.substring(5).trim();
                        if (interactednpcname.equalsIgnoreCase(npcName)) {
                            NPC npc = pyramid.getRoom(currentPosition[1], currentPosition[0])
                                    .findNPCByName(interactednpcname);
                            if (npc != null && npc.isAlive()) {
                               npc.NpcInteraction(interactednpcname, "free", hero, pyramid.getRoom(currentPosition[1], currentPosition[0]));
                            } else if (npc != null && !npc.isAlive()) {
                                printCentered(npc.getName() +
                                        " is already dead and cannot be interacted with.");
                            } else {
                                printCentered("No NPC named '" + interactednpcname +
                                        "' found in the room.");
                            }
                        } else {
                            printCentered("NPC name does not match the talked NPC.");
                        }
                   }else {
                    printCentered("Unknown command. Returning to main menu.");
                   }
                }}
            
            else if (input.equalsIgnoreCase("flee")) {
                if (inCombat) {
                    printCentered(ANSI_BLUE + "You flee from the combat!" + ANSI_RESET);
                    printCentered("Available actions: 'inspect the [item_name]', " +
                            "'equip [item_name]', 'talk to [npc_name]', 'attack " +
                            "[npc_name]', or type 'exit' to quit.");
                    inCombat = false;
                } else {
                    printCentered("You are not in combat.");
                }
            } else if (input.toLowerCase().startsWith("move ")) {
                String direction = input.substring(5).trim();
                int[] previousPosition = hero.getCurrentPosition().clone();
                hero.move(pyramid, direction);
                if (hero.getHasMoved()) {
                    int[] newPosition = hero.getCurrentPosition();
                    if (previousPosition[0] != newPosition[0] ||
                            previousPosition[1] != newPosition[1]) {
                        printCentered("You moved " + direction + ".");
                    } else {
                        printCentered("You hit a wall. You are still in the same room.");
                    }
                } else {
                    printCentered("You cannot move " + direction + ".");
                }
            } else if (input.toLowerCase().startsWith("save game ")) {
                String saveName = input.substring(10).trim();
                // If the save name is empty, use the default name "savegame"
                if (saveName.isEmpty()) {
                    saveName = "savegame";
                }
                saveGame(hero, pyramid, saveName);
            } else if (input.toLowerCase().startsWith("load game ")) {
                String saveName = input.substring(10).trim();
                loadGame(hero, pyramid, saveName);
            } else if (input.toLowerCase().startsWith("exit pyramid")&&hero.getCurrentPosition()[1]==0&&hero.getCurrentPosition()[0]==0){
                if(hero.getInvItem("ankh").getName().toLowerCase().equals("ankh")||hero.getEqItem().getName().equalsIgnoreCase("ankh")){
                System.out.println("the ankh begins to glow alongside the incisions on the door\n"
                +"after a short while the door begins to open.\n"
                +"without wasting any time you immediatly exit the pyramid, you are finally free.\n"+
                "you made it out with all the artifacts you could carry with you.");
                GameEnd(hero);
                break;
                }else{
                    System.out.println("despite your best efforts the door won't budge.\n"
                +"after several minutes of careful examinations you notice\n several incisions depicting humanoid figured holding ankh shaped object in front of the door\n"+
                "peraphs if you find such an item you might be able to leave.");
                }
            }else if(input.toLowerCase().startsWith("enter hole")&&hero.getCurrentPosition()[1]==1&&hero.getCurrentPosition()[0]==1)
                { 
            pyramid.getRoom(1, 1).findLandmarkByName("hole").landmarkInteraction("hole", "enter", hero, pyramid.getRoom(1, 1));
            }else if(input.toLowerCase().startsWith("open chest")&&hero.getCurrentPosition()[1]==1&&hero.getCurrentPosition()[0]==2){
            pyramid.getRoom(1, 2).findLandmarkByName("chest").landmarkInteraction("chest", "open", hero, pyramid.getRoom(1, 2));
            }else{
                printCentered("Unknown command. Please try again.");
            }


            if (hero.getHealth() <= 0&&!input.toLowerCase().startsWith("exit"))
            {
                GameEnd(hero);
                break;
            }

            // Pausa dopo ogni azione
            printCentered("Press Enter to continue...", ANSI_RED);
            scanner.nextLine();
            System.out.println(
                    ANSI_CYAN +
                            ("-----------------------------------------------------------------" +
                                    "-----------------------------------------------------------------" +
                                    "----------------------------------------------\n") +
                            ANSI_RESET);

            // Descrizione della stanza corrente aggiornata
            pyramid.describeRoom(currentPosition[0], currentPosition[1]);
        }

        scanner.close();
        GameEnd(hero);
    }

    private static void saveGame(Main_Character hero, Pyramid pyramid,
                                 String saveName) {
        printCentered("Saving game...", ANSI_CYAN);
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter writer = new FileWriter(saveName + ".json");
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

    private static void loadGame(Main_Character hero, Pyramid pyramid,
                                 String saveName) {
        printCentered("Loading game...", ANSI_CYAN);
        try {
            Gson gson = new Gson();
            FileReader reader = new FileReader(saveName + ".json");
            JsonObject root = gson.fromJson(reader, JsonObject.class);
            reader.close();

            Main_Character loadedHero =
                    gson.fromJson(root.get("hero"), Main_Character.class);
            Pyramid loadedPyramid = gson.fromJson(root.get("pyramid"), Pyramid.class);

            // Update current hero and pyramid with loaded data
            hero.updateFrom(loadedHero);
            pyramid.updateFrom(loadedPyramid);

            printCentered("Game loaded successfully.", ANSI_GREEN);
        } catch (IOException e) {
            printCentered("Error loading game: " + e.getMessage(), ANSI_RED);
        }
    }

    private static void Victory(Main_Character hero) {
        if (hero.getInvScore() <= 0) {
            System.out.println("you did not bring back anything noteworthy.\n" +
                    "this entire expedtion was a waste of time....at least you didn't die");
        } else {
            if (hero.getInvScore() > 0 && hero.getInvScore() <= 50) {
                System.out.println("you brought back several artifacts.\n" +
                        "it is impossible for you to tell their value but you know you'll surely be remembered for your findings.");
            } else {
                if (hero.getInvScore() > 50 && hero.getInvScore() <= 100) {
                    System.out.println("you made several breaktroughs during your expediction.\n" +
                            "you brought back several inestimable artifacts, your name is sure to be remembered for years to come");
                } else {
                    System.out.println("you brought back everything you could get your hands on in the pyramid.\n" +
                            "not even a grave robber could've done such a thorough job.\n" +
                            "that said peraphs it was better for the world to forget about several of the artifacts you found.\n" +
                            "regardless of the price you've found your wealth and your fame.\n" +
                            "The world Will remember your name.");
                }
            }
        }
        System.out.println("██╗   ██╗ ██████╗ ██╗   ██╗    ██╗    ██╗██╗███╗   ██╗" + "\n" +
                "╚██╗ ██╔╝██╔═══██╗██║   ██║    ██║    ██║██║████╗  ██║" + "\n" +
                "╚████╔╝ ██║   ██║██║   ██║    ██║ █╗ ██║██║██╔██╗ ██║" + "\n" +
                "╚██╔╝  ██║   ██║██║   ██║    ██║███╗██║██║██║╚██╗██║" + "\n" +
                "██║   ╚██████╔╝╚██████╔╝    ███████╗╚███╔███╔╝██║██║ ╚████║" + "\n" +
                "╚═╝    ╚═════╝  ╚═════╝      ╚══╝╚══╝ ╚═╝╚═╝  ╚═══╝" + "\n");
        System.out.println("your final score is:" + hero.getInvScore());
    }

        private static void Loss(Main_Character hero) {
        System.out.println("unfortunately during your expedition you met your end.\n" +
                " You are now fated to remain forgotten unable to die trapped forever in the depths of the tomb");
        System.out.println("██╗   ██╗ ██████╗ ██╗   ██╗    ██╗      ██████╗ ███████╗███████╗" + "\n" +
                "╚██╗ ██╔╝██╔═══██╗██║   ██║    ██║     ██╔═══██╗██╔════╝██╔════╝" + "\n" +
                "╚████╔╝ ██║   ██║██║   ██║    ██║     ██║   ██║███████╗█████╗" + "\n" +
                "╚██╔╝  ██║   ██║██║   ██║    ██║     ██║   ██║╚════██║██╔══╝" + "\n" +
                "██║   ╚██████╔╝╚██████╔╝    ███████╗╚██████╔╝███████║███████╗" + "\n" +
                "╚═╝    ╚═════╝  ╚═════╝     ╚══════╝ ╚═════╝ ╚══════╝╚══════╝" + "\n");
        System.out.println("your final score is:" + hero.getInvScore());
    }

    private static void GameEnd(Main_Character hero) {
        if (hero.getHealth() <= 0) {
            Loss(hero);
        } else {
            Victory(hero);
        }
    }
}
