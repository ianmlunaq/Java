import java.util.Scanner;

public class Easter {
  public static void main(String[] args) {
    System.out.println("Enter a year: ");
    Scanner keyboard = new Scanner(System.in);

    int input = keyboard.nextInt(),
        valz = input % 19,
        valy = input / 100, valx = input % 100,
        valw = valy / 4, valv = valy % 4,
        valu = (8 * valy + 13) / 25,
        valt = (19 * valz + valy - valw - valu + 15) % 30,
        vals = valx / 4, valr = valx % 4,
        valq = (valz + 11 * valt) / 319,
        valp = (2 * valv + 2 * vals - valr - valt + valq + 32) % 7,
        month = (valt - valq + valp + 90) / 25,
        day = (valt - valq + valp + month + 19) % 32;

    System.out.println("In " + input + ", Easter fell on " + month + "/" + day + "/" + input);
    keyboard.close();
  }
}
