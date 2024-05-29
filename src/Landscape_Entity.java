public class Landscape_Entity extends Entity {
    private String landmarkdescription;
    private int landmarkpower;
public Landscape_Entity(String name, int power,  int startX, int startY, String landmarkdescription){
    super(name,0, power,1000,  startX, startY);

setCanMove(false);
this.landmarkdescription = landmarkdescription;
this.landmarkpower = power;
}

public String getLandscapeDesc() { return landmarkdescription; }

public void damageCharacter(Main_Character victim)
{
    victim.takeDamage(landmarkpower);
}
public void describeLandscape() { System.out.println(getName() + " is here. " + getLandscapeDesc()); }
public void landmarkInteraction(Landscape_Entity target,String interaction){
switch(target.getName()){
    case"hole":if(interaction.toLowerCase().equals("jump")){
    //game over instantaneo
    break;
    }else{
    break;    
    }
    case "chest": if(interaction.toLowerCase().equals("open")){
        //bisogna fare il check se nell'inventario ho chiave oppure se la ho equipaggiata
    break;
    }else{
    break;    
    }
    }
    
}
}
