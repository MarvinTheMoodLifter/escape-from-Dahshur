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
        room.addItem(new Item("Sword", 10, 5, 20,"pointy stick"));
        room.addItem(new Item("Shield", 15, 10, 5, "defense is the best offense"));
        room.addItem(new Item("Coconut", 5, 1, 0,"food"));
        gamemap[2][1].addItem(new Item("ankh", 10, 1, 0,"an ancient amulet of glittering gold, it used to be worshipped as the symbol of life"));
        gamemap[1][2].addItem(new Item("key", 10, 1, 0,"a large metal key well decorated with incisions all over the handle and shaft"));
        gamemap[0][1].addItem(new Item("djed", 20, 1, 0,"an ancient amulet, its symbol represents stability"));
        gamemap[0][1].addItem(new Item("khol", 5, 1, 0,"it kind of looks like a modern eyeliner, it's still used as eye makeup. The small wooden object is carvedin the shape of a diety an fully painted"));
        gamemap[2][2].addItem(new Item("mhyrr oil", 5, 5, 0,"a small bottle of perfume, it's still full and emanates a pleasant smell"));
        gamemap[2][2].addItem(new Item("linen tunic", 10, 5, 1,"just an average linen tunic, the only remarkable feature being the quality of its cloth"));
        gamemap[1][0].addItem(new Item("chisel and hammer", 1, 10, 15,"Hammer and chisel, probably left behind by the last workers that worked on the decorations"));
        gamemap[1][2].addItem(new Item("razor", 1, 2, 10,"a sharp razor with a copper blade and a bone handle"));
        gamemap[2][2].addItem(new Item("book of the dead", 20, 2, 5,"an ancient papyrus filled with magical formulas whose original purpose was to aid the deceased on their journey to the afterlife"));
        gamemap[1][1].addItem(new Item("wine", 10, 10, 5,"a big clay jar filled with wine"));
        gamemap[1][1].addItem(new Item("meat", 1, 10, 1,"rancid meat still sealed inside a clay jar, the smell is slowly leaking from the sealed container"));

        // Aggiunta di un NPC alla stanza
        NPC guardian = new NPC("Osiris", 100, 75, 150, 0, 0, "A creepy, ancient mummy that watches over the tomb.", false);
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
