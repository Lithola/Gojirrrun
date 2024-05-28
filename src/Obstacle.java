import java.awt.*;
import java.awt.image.BufferedImage;

public class Obstacle {

    private int pozX;
    private int pozY;
    private int moveX;
    private int collisionX ;

    BufferedImage obstacleImg = Utilities.getPieck("SourcePics/cthulhu.png"); //szansa 1:10 000


    public Obstacle(int pozX) { //tworzy losową przeszkodę
        this.pozX = pozX;
        int ran = (int) (Math.random() * 10000); //przypisuje losowy obrazek
        if (ran > 0 && ran <= 3300)
            obstacleImg = Utilities.getPieck("SourcePics/bud1.png");
        else if (ran > 3300 && ran <= 6600)
            obstacleImg = Utilities.getPieck("SourcePics/bud2.png");
        else if (ran > 6600 && ran <= 9900)
            obstacleImg = Utilities.getPieck("SourcePics/bud3.png");
        else if (ran > 9900) {
            obstacleImg = Utilities.getPieck("SourcePics/palac.png");
        }
        this.pozX= pozX;
        this.pozY = 565 - obstacleImg.getHeight(); // wsp. podlogi  - wsp. obrazka
        moveX = pozX;
    }

    void moveLeft (Graphics o){ //przemieszcza na lewo
        moveX -=15;//szybkość rysowania

        o.drawImage(obstacleImg,moveX,pozY,null);
        collisionX = moveX + obstacleImg.getWidth(); //aktualizuje pkt. zderzenia


    }
    public boolean collision (int gojiX, int gojiY){ //
        if (gojiX>moveX&&gojiX-80<collisionX&&gojiY>pozY)
            return true;
        return false;
    }

    public int getX(){
        return moveX;
    }
}