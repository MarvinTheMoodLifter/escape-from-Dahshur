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

public void setLandscapeDesc(String desc) { landmarkdescription=desc; }

public void damageCharacter(Main_Character victim)
{
    victim.takeDamage(landmarkpower);
}
public void describeLandscape() { System.out.println(getName() + " is here. " + getLandscapeDesc()); }
public void landmarkInteraction(Landscape_Entity target,String interaction, Main_Character hero){
switch(target.getName()){
    case"hole":if(interaction.toLowerCase().equals("jump")){
    //game over instantaneo
    break;
    }else{
    break;    
    }
    case "chest": if(interaction.toLowerCase().equals("open")&&(hero.getEqItem().getName().equals("key"))){
    target.setLandscapeDesc("the chest has been opened it is of no use now");
    System.out.println("you pick up a golden bracelet");
    hero.setEqItem(new Item("golden bracelet", 10, 5, 0, "a golden bracelet with intricate patters and precious gems"));
    break;
    }else{
        System.out.println("you can't open the chest");
    break;    
    }
    }
    
}
}
