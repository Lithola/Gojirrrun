import java.awt.*;
import java.awt.image.BufferedImage;

public class Dinosaur {


    private static int x = 200;// dopasowane do obrazka
    private static int y = 435;// dopasowane do obrazka //wys skoku 400
    private int collisionBoxX=x+130;//hitbox w prawym dolnym rogu obrazka
    private int collisionBoxY =y+130;//hitbox w prawym dolnym rogu obrazka


    BufferedImage godziRun = Utilities.getPieck("SourcePics/godzimove.png");
    BufferedImage godziDead = Utilities.getPieck("SourcePics/godzidead.png");
    BufferedImage godziBase = Utilities.getPieck("SourcePics/godzi.png");

    private static boolean isAlive =true;
    private boolean isJumping = false;
    private boolean isFalling = false;
    private boolean isMoving = false;
    private boolean interval =false;
    private boolean ressurection = false;


    public void draw (Graphics g){
        if(isAlive){
            g.drawImage(isMoving?godziRun:godziBase,x,y,null);
            jump();
            if(interval){
            isMoving = !isMoving;}
            else interval=!interval;
        }
        else
            g.drawImage(godziDead,x,y,null);

    }

    public void jump (){
        if (isJumping&&y>135) { //wys skoku 400
            y-= 20;
            collisionBoxY =y+130;
        }
        else if(isJumping&&y<=135){ // skacze i osiągnęłą pułap -> zaczyna spadać
            isJumping=false;
            isFalling=true;

        }
        else if(isFalling&&y<435){ //spada
            y+=20;
            collisionBoxY =y+138;
        }
        else { //kiedy wraca do stanu pierwotnego
            isFalling=false;
        }

        }

    public int getGojiPositionX() {
        return collisionBoxX;

    }
    public int getGojiPositionY() {
        return collisionBoxY;

    }

    public void doResurrect(){
       isAlive = true;
       ressurection = false;

    }

    public void setRessurection() {
        this.ressurection = true;
    }

    public boolean isInTheAir (){
        return isJumping||isFalling;
    }
    public void setJumping (){ //do przycisku
        isJumping = true;
    }

    public void death (){
        isAlive =false;
    }

    public boolean isRessurection() {
        return ressurection;
    }

    public boolean isIsAlive() {
        return isAlive;

    }
}
