import java.util.*;

public class Pyramid {
    private Room[][] gamemap;

    public Pyramid() {
        gamemap = new Room[3][3];
        for (int i = 0; i < gamemap.length; i++) {
            for (int j = 0; j < gamemap[i].length; j++) {
                gamemap[i][j] = new Room();
            }
        }
    }

    public Room getRoom(int posx, int posy) 
    {
        if (posx >= 0 && posx < gamemap.length && posy >= 0 && posy < gamemap[posx].length) { return gamemap[posx][posy]; } 
        else { return null; }
    }
}

