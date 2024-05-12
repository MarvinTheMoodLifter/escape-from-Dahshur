/**
 * Item.java
 * This class represents an item in the game.
 * The item has a name, a score value, a weight, a location and a description.
 **/

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
}
