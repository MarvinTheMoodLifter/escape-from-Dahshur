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
        Main_Character hero = new Main_Character("Hero", 100, 50, 100, 0, 0);
        boolean inCombat = false;

        while (true)
        {
            // Descrizione della stanza corrente
            int[] currentPosition = hero.getCurrentPosition();
            pyramid.describeRoom(currentPosition[0], currentPosition[1]);

            // Menu per interazione utente
            System.out.println("Available actions:\n inspect [item_name]\n take [item_name] \n equip [item_name]\n talk to [npc_name]\n attack [npc_name]\n move [direction]\n view inventory\n type 'exit' to quit.");
            System.out.print("Enter action: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) { break; }
            else if (input.equalsIgnoreCase("view inventory")) { hero.printInventory(); }
            else if (input.toLowerCase().startsWith("inspect "))
            {
                if (inCombat) { System.out.println(ANSI_YELLOW + "You cannot inspect items during combat. You must 'attack' or 'flee'." + ANSI_RESET); }
                else
                {
                    String itemName = input.substring(8).trim();
                    hero.inspectItemByName(pyramid.getRoom(currentPosition[0], currentPosition[1]), itemName);
                }
            }
            else if (input.toLowerCase().startsWith("take "))
            {
                if (inCombat) { System.out.println(ANSI_YELLOW + "You cannot take items during combat. You must 'attack' or 'flee'." + ANSI_RESET); }
                else
                {
                    String itemName = input.substring(5).trim();
                    Item item = pyramid.getRoom(currentPosition[0], currentPosition[1]).findItemByName(itemName);
                    if (item != null) { hero.takeItem(item, pyramid.getRoom(currentPosition[0], currentPosition[1])); }
                    else { System.out.println("No item named '" + itemName + "' found in the room."); }
                }
            }
            else if (input.toLowerCase().startsWith("equip "))
            {
                String itemName = input.substring(6).trim();
                hero.equipItem(itemName);
            }
            else if (input.toLowerCase().startsWith("talk to "))
            {
                if (inCombat) { System.out.println(ANSI_YELLOW + "You cannot talk during combat. You must 'attack' or 'flee'." + ANSI_RESET); }
                else
                {
                    String npcName = input.substring(8).trim();
                    pyramid.getRoom(currentPosition[0], currentPosition[1]).talkToNPC(npcName);
                }
            }
            else if (input.toLowerCase().startsWith("attack "))
            {
                String npcName = input.substring(7).trim();
                NPC npc = pyramid.getRoom(currentPosition[0], currentPosition[1]).findNPCByName(npcName);
                if (npc != null && npc.isAlive())
                {
                    hero.attack(npc);
                    inCombat = true;
                    if (npc.isAlive())
                    {
                        npc.attack(hero, 10);
                        if (!hero.isAlive())
                        {
                            System.out.println(ANSI_RED + "You have died." + ANSI_RESET);
                            break;
                        }
                    }
                }
                else if (npc != null && !npc.isAlive())
                {
                    System.out.println(npc.getName() + " is already dead and cannot be attacked.");
                    inCombat = false;
                }
                else { System.out.println("No NPC named '" + npcName + "' found in the room."); }
            }
            else if (input.equalsIgnoreCase("flee"))
            {
                if (inCombat)
                {
                    System.out.println(ANSI_BLUE + "You flee from the combat!" + ANSI_RESET);
                    System.out.println("Available actions: 'inspect the [item_name]', 'equip [item_name]', 'talk to [npc_name]', 'attack [npc_name]', or type 'exit' to quit.");
                    inCombat = false;
                }
                else { System.out.println("You are not in combat."); }
            }
            else if (input.toLowerCase().startsWith("move "))
            {
                String direction = input.substring(5).trim();
                int[] previousPosition = hero.getCurrentPosition().clone();
                hero.move(pyramid, direction);
                if (hero.getHasMoved())
                {
                    int[] newPosition = hero.getCurrentPosition();
                    if (previousPosition[0] != newPosition[0] || previousPosition[1] != newPosition[1])
                    {
                        System.out.println("You moved " + direction + ".");
                        continue;
                    }
                    else { System.out.println("You hit a wall. You are still in the same room."); }
                }
                else { System.out.println("You cannot move " + direction + "."); }
            }
            else { System.out.println("Unknown command. Please try again."); }
        }

        scanner.close();
    }
}
