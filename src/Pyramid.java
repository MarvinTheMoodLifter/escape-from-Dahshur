import java.util.*;

public class Pyramid {
    private Room[][] gamemap;
    private String pyramidDescription;

    public Pyramid() 
    {
        pyramidDescription="";
        gamemap = new Room[3][3];
        for (int i = 0; i < gamemap.length; i++) 
        {
            for (int j = 0; j < gamemap[i].length; j++) { gamemap[i][j] = new Room(); }
        }
        wallInitializer();
    }

    public Room getRoom(int posx, int posy) 
    {
        if (posx >= 0 && posx < gamemap.length && posy >= 0 && posy < gamemap[posx].length) { return gamemap[posx][posy]; } 
        else { return null; }
    }
    public String getPyramidDesc(){return pyramidDescription;}
    public void setPyramidDesc(String desc){ pyramidDescription=desc;}
    public void printPyramidDesc(){System.out.println(pyramidDescription) ;}
    public void wallInitializer(){
        gamemap[0][0].setWall("right",true);
        gamemap[0][1].setWall("left",true);gamemap[0][1].setWall("right",true);
        gamemap[0][2].setWall("left",true);
        gamemap[1][0].setWall("up",true);
        gamemap[2][0].setWall("down",true);
        gamemap[2][1].setWall("right",true);
    }
    private void roomInitializer()
    {
        // Aggiunta di oggetti alla stanza per dimostrazione
        Room room = gamemap[0][0];
        room.addItem(new Item("Sword", 10, 5, 20));
        room.addItem(new Item("Shield", 15, 10, 5));
        room.addItem(new Item("Coconut", 5, 1, 0));

        // Aggiunta di un NPC alla stanza
        NPC guardian = new NPC("Osiris", 100, 75, 150, 0, 0, "A creepy, ancient mummy that watches over the tomb.");
        room.addEntity(guardian);
    }

    public void describeRoom(int x, int y)
    {
        Room room = getRoom(x, y);
        if (room != null)
        {
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
        }
        else { System.out.println("No room found at the specified coordinates."); }
    }
}

