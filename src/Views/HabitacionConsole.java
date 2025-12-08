package Views;
import Controllers.HabitacionController;
import Models.Habitacion;
import java.util.List;
import java.util.Scanner;
public class HabitacionConsole {
    private HabitacionController controller;
    private Scanner scanner;
    public HabitacionConsole() {this.controller = new HabitacionController();this.scanner = new Scanner(System.in);}
    public void showMenu() {
        while(true) {
            System.out.println("\n=== ROOM ===");
            System.out.println("1.List\n2.Add\n3.Update\n4.Delete\n5.Back");
            System.out.print("Option:");
            switch(readIntInput()) {case 1:listAll();break;case 2:add();break;case 3:update();break;case 4:delete();break;case 5:return;default:System.out.println("Invalid");}
        }
    }
    private void listAll() {System.out.println("\n--- LIST ---");List<Habitacion> l=controller.getAllHabitaciones();if(l.isEmpty())System.out.println("Empty");else for(Habitacion h:l)System.out.println("ID:"+h.getId());}
    private void add() {System.out.println("\n--- ADD ---");System.out.print("hotelId:");String h=scanner.nextLine();System.out.print("tipo:");String t=scanner.nextLine();System.out.print("precio:");double p=readDoubleInput();System.out.print("capacidad:");int c=readIntInput();boolean s=controller.addHabitacion(h,t,p,c);System.out.println(s?"OK":"Failed");}
    private void update() {System.out.println("\n--- UPDATE ---");System.out.print("ID:");String id=scanner.nextLine();if(controller.getHabitacionById(id)==null){System.out.println("Not found");return;}System.out.print("tipo:");String t=scanner.nextLine();System.out.print("precio:");String p_s=scanner.nextLine();Double p=p_s.isEmpty()?null:Double.parseDouble(p_s);System.out.print("capacidad:");String c_s=scanner.nextLine();Integer c=c_s.isEmpty()?null:Integer.parseInt(c_s);boolean s=controller.updateHabitacion(id,t.isEmpty()?null:t,p,c);System.out.println(s?"OK":"Failed");}
    private void delete() {System.out.println("\n--- DELETE ---");System.out.print("ID:");String id=scanner.nextLine();boolean s=controller.deleteHabitacion(id);System.out.println(s?"OK":"Failed");}
    private int readIntInput() {while(true){try{return Integer.parseInt(scanner.nextLine());}catch(Exception e){System.out.print("Invalid:");}}}
    private double readDoubleInput() {while(true){try{return Double.parseDouble(scanner.nextLine());}catch(Exception e){System.out.print("Invalid:");}}}
}
