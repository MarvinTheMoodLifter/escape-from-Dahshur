import java.util.*;

public class Entity
{
    private String name;
    private int health;
    private int power;
    private List<Item> item; // bisogna prima creare Item per gestirla meglio
    private int maxWeight;
    private int score;
    private int[] currentPosition;

    public Entity(String name, int health, int power, int maxWeight, int startX, int startY)
    {
        this.name = name;
        this.health = health;
        this.power = power;
        this.maxWeight = maxWeight;
        this.item = new ArrayList<>(); // dopo lo sviluppiamo
        this.score = 0;
        this.currentPosition = new int[]{startX, startY}; // Usando la matrice bidimensionale
    }

    public String getName() { return name; }

    public int getHealth() { return health; }

    public int getPower() { return power; }

    public List<Item> getItem() { return item; }

    public double getMaxWeight() { return maxWeight; }

    public int getScore() { return score; }

    public int[] getCurrentPosition() { return currentPosition; }

    public void setName(String name) { this.name = name; }

    public void setHealth(int health) { this.health = health; }

    public void setPower(int power) { this.power = power; }

    public void addItem(Item item) { this.item.add(item); }

    public void setMaxWeight(int maxWeight) { this.maxWeight = maxWeight; }

    public void setScore(int score) { this.score = score; }

    public void setCurrentPosition(int x, int y)
    {
        this.currentPosition[0] = x;
        this.currentPosition[1] = y;
    }

    public void moveNorth(int maxY) { if (currentPosition[1] < maxY - 1) currentPosition[1]++; }

    public void moveSouth(int maxY) { if (currentPosition[1] > 0) currentPosition[1]--; }

    public void moveEast(int maxX) { if (currentPosition[0] < maxX - 1) currentPosition[0]++; }

    public void moveWest(int maxX) { if (currentPosition[0] > 0) currentPosition[0]--; }
}
