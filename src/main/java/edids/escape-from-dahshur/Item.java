public class Item {
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
        System.out.println("Name: " + getName());
        System.out.println("Score: " + getItemScore());
        System.out.println("Weight: " + getWeight());
        System.out.println("Damage: " + getDamage());
        System.out.print("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
    }
}
