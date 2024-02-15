import java.util.Scanner;
import javax.swing.JOptionPane;

public class MyInput {
    public static void main(String[] args) {
        String myInput;
        int myNumber;
        Scanner scan = new Scanner(System.in);

        myInput = JOptionPane.showInputDialog("Please enter your number: ");
        myNumber = Integer.parseInt(myInput);
        //myInput = scan.nextLine();

        JOptionPane.showMessageDialog(null, "Your number + 5 is " + (myNumber + 5));
        //System.out.println("Your name is " + myInput.length() + " long");
        //System.out.println("The first letter is: " + myInput.charAt(0));

        scan.close();
        System.exit(0);
    }
}
