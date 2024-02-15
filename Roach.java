public class Roach {
    private int population;

    public Roach(int initialPopulation) {
        population = initialPopulation;
    }

    public void breed() {
        population *= 2;
    }

    public void spray(double percent) {
        population -= population * (percent / 100);
    }

    public int getRoaches() {
        return population;
    }
}