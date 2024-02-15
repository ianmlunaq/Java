import java.util.Random;
import java.io.*;

public class Rancreate {
    public static void main(String[] args) throws IOException {
        Random rand = new Random();
        PrintWriter output = new PrintWriter("myrandom.txt");

        for (int i = 0; i < 100; i++) {
            int randomInt = rand.nextInt(100);
            output.println(randomInt);
        }

        output.close();
    }

}
