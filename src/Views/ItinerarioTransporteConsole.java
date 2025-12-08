package Views;
import Controllers.ItinerarioTransporteController;
import Models.ItinerarioTransporte;
import java.util.List;
import java.util.Scanner;
public class ItinerarioTransporteConsole {
    private ItinerarioTransporteController controller;
    private Scanner scanner;
    public ItinerarioTransporteConsole() {this.controller = new ItinerarioTransporteController();this.scanner = new Scanner(System.in);}
    public void showMenu() {
        while(true) {
            System.out.println("\n=== TRANSPORT ITINERARY ===");
            System.out.println("1.List\n2.Add\n3.Update\n4.Delete\n5.Back");
            System.out.print("Option:");
            switch(readIntInput()) {case 1:listAll();break;case 2:add();break;case 3:update();break;case 4:delete();break;case 5:return;default:System.out.println("Invalid");}
        }
    }
    private void listAll() {System.out.println("\n--- LIST ---");List<ItinerarioTransporte> l=controller.getAllItinerarios();if(l.isEmpty())System.out.println("Empty");else for(ItinerarioTransporte i:l)System.out.println("ID:"+i.getId());}
    private void add() {System.out.println("\n--- ADD ---");System.out.print("viajeId:");String v=scanner.nextLine();System.out.print("trayectoId:");String t=scanner.nextLine();System.out.print("servicioId:");String s=scanner.nextLine();System.out.print("orden:");int o=readIntInput();boolean su=controller.addItinerario(v,t,s,o);System.out.println(su?"OK":"Failed");}
    private void update() {System.out.println("\n--- UPDATE ---");System.out.print("ID:");String id=scanner.nextLine();if(controller.getItinerarioById(id)==null){System.out.println("Not found");return;}System.out.print("viajeId:");String v=scanner.nextLine();System.out.print("trayectoId:");String t=scanner.nextLine();System.out.print("servicioId:");String s=scanner.nextLine();System.out.print("orden:");String o_s=scanner.nextLine();Integer o=o_s.isEmpty()?null:Integer.parseInt(o_s);boolean su=controller.updateItinerario(id,v.isEmpty()?null:v,t.isEmpty()?null:t,s.isEmpty()?null:s,o);System.out.println(su?"OK":"Failed");}
    private void delete() {System.out.println("\n--- DELETE ---");System.out.print("ID:");String id=scanner.nextLine();boolean s=controller.deleteItinerario(id);System.out.println(s?"OK":"Failed");}
    private int readIntInput() {while(true){try{return Integer.parseInt(scanner.nextLine());}catch(Exception e){System.out.print("Invalid:");}}}
}
