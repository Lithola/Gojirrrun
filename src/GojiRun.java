import javax.swing.*;
import javax.swing.text.Position;
import java.awt.*;
import java.awt.event.KeyListener;

public class GojiRun extends JFrame {
    public static void main(String[] args) {
        GojiRun gojiRun = new GojiRun();
        gojiRun.startGame();
    }


    private static final int Width = 1280;
    private static final int Height = 675;
    Screen screen;


    public GojiRun()  {
        super("GojiRun");
        setSize(Width,Height);
        setLocation(100, 150);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        screen = new Screen();
        add(screen);
        addKeyListener(screen);
        setFocusable(true);

    }

    public void startGame (){
        screen.startGame();

    }

}
