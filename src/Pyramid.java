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
        gamemap[0][1].setWall("left",true);gamemap[1][0].setWall("right",true);
        gamemap[0][2].setWall("left",true);
        gamemap[1][0].setWall("up",true);
        gamemap[2][0].setWall("down",true);
        gamemap[2][1].setWall("right",true);
    }
}
