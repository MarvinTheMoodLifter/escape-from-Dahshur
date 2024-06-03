package escape_from_dahshur.main;

public class Item {
  private String name;
  private int score;
  private int weight;
  private int damage;
  private String itemDescription;

  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_PURPLE = "\u001B[35m";

  public Item(String name, int score, int weight, int damage, String itemdesc) {
    this.name = name;
    this.score = score;
    this.weight = weight;
    this.damage = damage;
    itemDescription = itemdesc;
  }

  public String getName() { return name; }
  public int getItemScore() { return score; }
  public int getWeight() { return weight; }
  public int getDamage() { return damage; }
  public String getItemDesc() { return itemDescription; }
  public void setItemDesc(String desc) { itemDescription = desc; }
  public void printItemDesc() { System.out.println(itemDescription); }

  public void inspectItem() {
    System.out.println(ANSI_PURPLE + "Name: " + getName());
    System.out.println("Score: " + getItemScore());
    System.out.println("Weight: " + getWeight());
    System.out.println("Damage: " + getDamage() + ANSI_RESET);
    System.out.print(
        "--------------------------------------------------------------------" +
        "--------------------------------------------------------------------" +
        "----------------------------------------\n");
  }
}
