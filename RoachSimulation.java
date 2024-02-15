public class RoachSimulation {
    public static void main(String[] args) {
        Roach milRoach = new Roach(1000);

        milRoach.breed();
        milRoach.spray(15);
        System.out.println(milRoach.getRoaches());

        milRoach.breed();
        milRoach.spray(15);
        System.out.println(milRoach.getRoaches());

        milRoach.breed();
        milRoach.spray(15);
        System.out.println(milRoach.getRoaches());
        
        milRoach.breed();
        milRoach.spray(15);
        System.out.println(milRoach.getRoaches());
    }
}
