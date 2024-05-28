import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

public class Utilities {

    public static BufferedImage getPieck(String path){
        BufferedImage image = null;

        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return image;

    }

    public static void saveScore(int score){ //do zapisywania wyników
        Writer output;
       File file = new File("Wyniki.txt");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            output = new BufferedWriter(new FileWriter("Wyniki.txt",true));
            output.append(Integer.toString(score));
            output.append("\n");
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
public static int readScore(String path){
        int wynik=0;
        int tmp;
    File file = new File(path);
    if(!file.exists()){
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    try {
        Scanner read = new Scanner(file);
        while(read.hasNext()){
            tmp=Integer.parseInt(read.next());
            wynik=tmp>wynik?tmp:wynik; //zwraca największy
        }
    } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
    }
   return wynik; // największy wynik
}
}
