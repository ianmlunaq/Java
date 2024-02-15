import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Ranselect {
    public static void main(String[] args) throws IOException {
        File input = new File("myrandom.txt");
        Scanner inputScan = new Scanner(input);

        final int FAV_NUM_1 = 42;
        final int FAV_NUM_2 = 21;
        int favNum1Counter = 0;
        int favNum2Counter = 0;

        // Prints the numbers from myrandom.txt in rows of 10 & counts occurrences of FAV_NUM_1 and FAV_NUM_2
        while (inputScan.hasNext()) {
            for (int i = 0; i < 10; i++) {
                String str = inputScan.nextLine();
                int strInt = Integer.parseInt(str);

                if (strInt == FAV_NUM_1) {
                    favNum1Counter++;
                } else if (strInt == FAV_NUM_2) {
                    favNum2Counter++;
                }

                System.out.print(str + " ");
            }
            System.out.println();
        }

        inputScan.close();

        // Prints a row of 30 asterisks
        for (int i = 0; i < 30; i++) { System.out.print("*"); }

        System.out.println();
        System.out.println("My favorite number is " + FAV_NUM_1 + " and it occurs " + favNum1Counter + " times.");
        System.out.println("My second favorite number is " + FAV_NUM_2 + " and it occurs " + favNum2Counter + " times.");


    }
}
