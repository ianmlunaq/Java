public class MouseTester {
    public static void main(String[] args) {
        MouseLine mousy = new MouseLine(0);
        System.out.println(mousy.getPosition());
        mousy.move();
        System.out.println(mousy.getPosition());
        mousy.move();
        System.out.println(mousy.getPosition());
        mousy.turn();
        mousy.move();
        System.out.println(mousy.getPosition());
        mousy.move();
        System.out.println(mousy.getPosition());
        mousy.move();
        System.out.println(mousy.getPosition());
    }
}