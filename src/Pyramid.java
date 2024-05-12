import java.util.*;

public class Pyramid {
    private Room[][] gamemap;
public Pyramid(){
    gamemap = new Room[2][2];
    for(int i=0;i < 3;i++){
    for(int j=0;j < 3;j++){
        gamemap[i][j]= new Room();
    }
    }
    
}
public Room getRoom(int posx,int posy){
    return gamemap[posx][posy];
 }
}
