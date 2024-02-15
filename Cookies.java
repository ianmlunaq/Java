import java.util.Scanner;

public class Cookies {
  public static void main(String[] args) {
    System.out.println("A bag of cookies holds 40 cookies.");
    System.out.println(
        "The calorie information on the bag claims that there are 10 servings in the bag, and that a serving equals 300 calories.");
    System.out.println("Tell me how many cookies you've eaten, and I'll tell you how many calories you've consumed.");
    System.out.print("\nCookies eaten: ");

    final int CALORIES_PER_COOKIE = 300;
    Scanner keyboard = new Scanner(System.in);
    int cookies = keyboard.nextInt();
    int calories = cookies * CALORIES_PER_COOKIE;

    System.out.println("Calories consumed: " + calories);
    keyboard.close();
  }
}
