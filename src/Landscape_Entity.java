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
// in base al nome dell'entità questa funzione farà una cosa diversa, utilizzeremo uno switch eg:
// se target è buco e interaction è jump , tu muori
//implementeremo quando abbiamo tutte le entità pronte
 //evitiamo di esagerare con le interazioni senno  diventa pesante
}
}
