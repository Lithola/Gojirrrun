import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Screen extends JPanel implements Runnable, KeyListener {

    int score = 0;
    String message = "Press ENTER to restart";
    Dinosaur dinosaur;
    String highscore;
    Obstacle [] tokio =  new Obstacle[5];
    Thread thread;
    BufferedImage backGround = Utilities.getPieck("SourcePics/background.png");
    BufferedImage death = Utilities.getPieck("SourcePics/you_died.png");
    public static int GROUND = 0;

    public Screen() {
        setVisible(true);
        dinosaur = new Dinosaur();
        for (int i = 0; i < tokio.length; i++) {
            tokio[i]= new Obstacle(1300+500*i); //zaczyna tworzenie poza granicą planszy w odstępach 500
        }
        thread = new Thread(this);

    }

    public void paint (Graphics g) {
        super.paint(g);
        g.setFont(new Font("Century Gothic",Font.BOLD, 45));
        g.setColor(Color.BLUE);
        g.drawImage((dinosaur.isIsAlive()?backGround:death),0,0,null);
        g.drawString((dinosaur.isIsAlive()?(score + ""):message), getWidth()/2, getHeight()/4);
        g.drawString("Best: " +highscore,10,50);
        dinosaur.draw(g);
        for (int i = 0; i < tokio.length; i++) {
        tokio[i].moveLeft(g);
        }


    }



public void startGame(){
        thread.start();
}






    @Override
    public void keyPressed(KeyEvent e) {
       if (!dinosaur.isInTheAir()&&e.getKeyCode()==KeyEvent.VK_SPACE){
       dinosaur.setJumping();}
else
       if(!dinosaur.isIsAlive()&&e.getKeyCode()==KeyEvent.VK_ENTER){
           System.out.println(1);
           dinosaur.setRessurection();
       }



    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void run() {
        highscore=Utilities.readScore("Wyniki.txt")+""; //na początku gry wczytuję najwyższy wynik
        while (dinosaur.isIsAlive()){
            score += 1;
            for (int i = 0; i < tokio.length; i++) {
                if (tokio[i].collision(dinosaur.getGojiPositionX(), dinosaur.getGojiPositionY())) {
                    dinosaur.death();
                }
                if (tokio[i].getX() <0 -tokio[i].obstacleImg.getWidth()){
                    tokio[i] = i==0?new Obstacle(tokio[tokio.length-1].getX()+500):new Obstacle(tokio[i-1].getX()+500);
                    // zapetla rysowanie sprawdzajac czy przeszkody są poza mapą
                }
            }
            repaint();
           revalidate();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        repaint();
        revalidate();
    Utilities.saveScore(score);



        while (!dinosaur.isRessurection()){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
     restart();
    }

    void restart (){
        score =0;
        dinosaur.doResurrect();
        for (int i = 0; i < tokio.length; i++) {
            tokio[i]= new Obstacle(1300+500*i);}
            run(); //wątek od nowa

    }



}
