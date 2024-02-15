// This program will determine the batting average of a player.
// The number of hits and at bats are set internally in the program.

// Ian Luna Quiroz

public class Batavg {
  public static void main(String[] args) {
    final int AT_BAT = 420;
    final int HITS = 123;

    float batAvg;

    batAvg = (float) HITS / AT_BAT; // an assignment statement
    System.out.println("The batting average is " + batAvg); // output the result
  }
}
