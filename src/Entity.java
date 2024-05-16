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
    private Boolean hasmoved;

    public Entity(String name, int health, int power, int maxWeight, int startX, int startY)
    {
        this.name = name;
        this.health = health;
        this.power = power;
        this.maxWeight = maxWeight;
        this.item = new ArrayList<>(); // dopo lo sviluppiamo
        this.score = 0;
        this.currentPosition = new int[]{startX, startY}; // Usando la matrice bidimensionale
        hasmoved= false; /*lo usiamo per i print perche se si piò muovere dopo un movimento allora si printa 
        che si è mosso sennò si printa che ha colpito un muro o qualcosa del genere
        */ 
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

        public void moveNorth() { if (currentPosition[1] < 2){ currentPosition[1]++; 
        hasmoved=true;
    }else{hasmoved=false;}}

    public void moveSouth() { if (currentPosition[1] > 0){ currentPosition[1]--; 
        hasmoved=true;
    }else{hasmoved=false;}}

    public void moveEast() { if (currentPosition[0] < 2) {currentPosition[0]++; 
        hasmoved=true;
    }else{hasmoved=false;}}

    public void moveWest() { if (currentPosition[0] > 0){ currentPosition[0]--; 
        hasmoved=true;
    }else{hasmoved=false;}}

    public Boolean setHasMoved(Boolean setting){
    return hasmoved=setting;
    }

    public Boolean getHasMoved(){
        return hasmoved;
        }

    public void move(Pyramid target, String direction){
             direction.toLowerCase();
        if(direction=="up"||direction=="down"||direction=="right"||direction=="left"){
            switch(direction){
            case "up": if(!target.getRoom(currentPosition[1],currentPosition[0]).getWall(direction)){moveNorth();}else{hasmoved=false;}    break;
            case "down":if(!target.getRoom(currentPosition[1],currentPosition[0]).getWall(direction)){moveSouth();}else{hasmoved=false;}   break;
            case "left":if(!target.getRoom(currentPosition[1],currentPosition[0]).getWall(direction)){moveWest();}else{hasmoved=false;}    break;
            case "right":if(!target.getRoom(currentPosition[1],currentPosition[0]).getWall(direction)){moveEast();}else{hasmoved=false;}   break;
            }
        }else{hasmoved=false;}
    }
}
