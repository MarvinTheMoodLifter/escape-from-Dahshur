public class Item {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    private String name;
    private int score;
    private int weight;
    private int damage;

    public Item(String name, int score, int weight, int damage) {
        this.name = name;
        this.score = score;
        this.weight = weight;
        this.damage = damage;
    }

    public String getName() { return name; }
    public int getItemScore() { return score; }
    public int getWeight() { return weight; }
    public int getDamage() { return damage; }

    public void inspectItem()
    {
        System.out.println(ANSI_PURPLE + "Name: " + getName());
        System.out.println("Score: " + getItemScore());
        System.out.println("Weight: " + getWeight());
        System.out.println("Damage: " + getDamage() + ANSI_RESET);
        System.out.print("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
    }
}
