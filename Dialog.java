import javax.swing.JOptionPane;

public class Dialog {
    public static void main(String[] args) {
        String numOfFemalesStr = JOptionPane.showInputDialog("What is the amount of females registered in the class?");
        String numOfMalesStr = JOptionPane.showInputDialog("What is the amount of males registered in the class?");
        int numOfFemales = Integer.parseInt(numOfFemalesStr);
        int numOfMales = Integer.parseInt(numOfMalesStr);

        int total = numOfFemales + numOfMales;
        double percentFemales = (double)numOfFemales / total * 100;
        double percentMales = (double)numOfMales / total * 100;
        JOptionPane.showMessageDialog(null, "Percent females: " + percentFemales + "%\nPercent males: " + percentMales + '%');
        
    }
}