package edids.escape_from_dahshur;

import java.util.Scanner;

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

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Pyramid pyramid = new Pyramid();
        Room room = pyramid.getRoom(0, 0);
        Main_Character hero = new Main_Character("Hero", 100, 50, 100, 0, 0);
        boolean inCombat = false;

        if (room != null)
        {
            System.out.print("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            // Aggiunta di oggetti alla stanza per dimostrazione
            room.addItem(new Item("Sword", 10, 5, 20));
            room.addItem(new Item("Shield", 15, 10, 5));
            room.addItem(new Item("Potion", 5, 1, 0));

            // Aggiunta di un NPC alla stanza
            NPC guardian = new NPC("Osiris", 100, 75, 150, 0, 0, "A creepy, ancient mummy that watches over the tomb.");
            room.addEntity(guardian);

            // Iniziale descrizione degli NPC e oggetti nella stanza
            room.describeEntities();
            if (room.hasItem())
            {
                System.out.print("You can see ");
                int count = room.getRoomItems().size();
                for (int i = 0; i < count; i++)
                {
                    Item item = room.getRoomItems().get(i);
                    if (i > 0 && i == count - 1) { System.out.print(" and a " + item.getName()); }
                    else if (i > 0) { System.out.print(", a " + item.getName()); }
                    else { System.out.print("a " + item.getName()); }
                }
                System.out.println(".");
            }

            // Menu per interazione utente
            System.out.println("Available actions: 'inspect [item_name]', 'equip [item_name]', 'talk to [npc_name]', 'attack [npc_name]', or type 'exit' to quit.");
            while (true)
            {
                if (inCombat) { System.out.println("You are in combat! You can '" + ANSI_RED + "attack [npc_name]" + ANSI_RESET + "' or '" + ANSI_BLUE + "flee" + ANSI_RESET + "'."); }
                else { System.out.print("Enter action: "); }

                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("exit")) { break; }
                else if (input.toLowerCase().startsWith("inspect "))
                {
                    if (inCombat) { System.out.println(ANSI_YELLOW + "You cannot inspect items during combat. You must 'attack' or 'flee'." + ANSI_RESET); }
                    else
                    {
                        String itemName = input.substring(8).trim();  // Rimuove 'inspect the ' dalla stringa di input
                        hero.inspectItemByName(room, itemName);
                    }
                }
                else if (input.toLowerCase().startsWith("equip ")) {
                    String itemName = input.substring(6).trim(); // Rimuove 'equip ' dalla stringa di input
                    Item item = room.findItemByName(itemName);
                    if (item != null) { hero.equipItem(item); }
                    else { System.out.println("No item named '" + itemName + "' found in the room."); }
                }
                else if (input.toLowerCase().startsWith("talk to "))
                {
                    if (inCombat) { System.out.println(ANSI_YELLOW + "You cannot talk during combat. You must 'attack' or 'flee'." + ANSI_RESET); }
                    else
                    {
                        String npcName = input.substring(8).trim(); // Rimuove 'talk to ' dalla stringa di input
                        room.talkToNPC(npcName);
                    }
                }
                else if (input.toLowerCase().startsWith("attack "))
                {
                    String npcName = input.substring(7).trim(); // Estrae il nome dell'NPC dall'azione
                    NPC npc = room.findNPCByName(npcName);
                    if (npc != null && npc.isAlive())
                    {
                        hero.attack(npc); // Assume fixed damage for simplicity

                        inCombat = true; // Setta lo stato di combattimento a true
                        if (npc.isAlive())
                        {
                            npc.attack(hero, 10); // NPC contrattacca con danno fisso
                            if (!hero.isAlive())
                            {
                                System.out.println(ANSI_RED + "You have died." + ANSI_RESET);
                                break; // End the game if the hero dies
                            }
                        }
                    }
                    else if (npc != null && !npc.isAlive())
                    {
                        System.out.println(npc.getName() + " is already dead and cannot be attacked.");
                        inCombat = false; // Fine del combattimento se l'NPC è morto
                    }
                    else { System.out.println("No NPC named '" + npcName + "' found in the room."); }
                }
                else if (input.equalsIgnoreCase("flee"))
                {
                    if (inCombat)
                    {
                        System.out.println(ANSI_BLUE + "You flee from the combat!" + ANSI_RESET);
                        System.out.println("Available actions: 'inspect the [item_name]', 'equip [item_name]', 'talk to [npc_name]', 'attack [npc_name]', or type 'exit' to quit.");
                        inCombat = false; // Resetta lo stato di combattimento a false
                    }
                    else { System.out.println("You are not in combat."); }
                }
                else { System.out.println("Unknown command. Please try again."); }
            }
        }
        else { System.out.println("No room found at the specified coordinates."); }

        scanner.close();
    }

}
