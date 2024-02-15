import java.util.Scanner;

public class CardTester {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        String cardNotation;

        System.out.print("Enter the card notation: ");
        cardNotation = keyboard.nextLine();

        CardWord card1 = new CardWord(cardNotation);

        System.out.println(card1.getDescription());
    }
}
