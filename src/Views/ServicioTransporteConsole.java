package Views;
import Controllers.ServicioTransporteController;
import Models.ServicioTransporte;
import java.util.List;
import java.util.Scanner;
public class ServicioTransporteConsole {
    private ServicioTransporteController controller;
    private Scanner scanner;
    public ServicioTransporteConsole() {this.controller = new ServicioTransporteController();this.scanner = new Scanner(System.in);}
    public void showMenu() {
        while(true) {
            System.out.println("\n=== TRANSPORT SERVICE ===");
            System.out.println("1.List\n2.Add\n3.Update\n4.Delete\n5.Back");
            System.out.print("Option:");
            switch(readIntInput()) {case 1:listAll();break;case 2:add();break;case 3:update();break;case 4:delete();break;case 5:return;default:System.out.println("Invalid");}
        }
    }
    private void listAll() {System.out.println("\n--- LIST ---");List<ServicioTransporte> l=controller.getAllServicios();if(l.isEmpty())System.out.println("Empty");else for(ServicioTransporte s:l)System.out.println("ID:"+s.getId());}
    private void add() {System.out.println("\n--- ADD ---");System.out.print("trayectoId:");String t=scanner.nextLine();System.out.print("vehiculoId:");String v=scanner.nextLine();System.out.print("fechaInicio:");String fi=scanner.nextLine();System.out.print("fechaFin:");String ff=scanner.nextLine();System.out.print("costo:");double c=readDoubleInput();boolean s=controller.addServicio(t,v,fi,ff,c);System.out.println(s?"OK":"Failed");}
    private void update() {System.out.println("\n--- UPDATE ---");System.out.print("ID:");String id=scanner.nextLine();if(controller.getServicioById(id)==null){System.out.println("Not found");return;}System.out.print("trayectoId:");String t=scanner.nextLine();System.out.print("vehiculoId:");String v=scanner.nextLine();System.out.print("fechaInicio:");String fi=scanner.nextLine();System.out.print("fechaFin:");String ff=scanner.nextLine();System.out.print("costo:");String c_s=scanner.nextLine();Double c=c_s.isEmpty()?null:Double.parseDouble(c_s);boolean s=controller.updateServicio(id,t.isEmpty()?null:t,v.isEmpty()?null:v,fi.isEmpty()?null:fi,ff.isEmpty()?null:ff,c);System.out.println(s?"OK":"Failed");}
    private void delete() {System.out.println("\n--- DELETE ---");System.out.print("ID:");String id=scanner.nextLine();boolean s=controller.deleteServicio(id);System.out.println(s?"OK":"Failed");}
    private int readIntInput() {while(true){try{return Integer.parseInt(scanner.nextLine());}catch(Exception e){System.out.print("Invalid:");}}}
    private double readDoubleInput() {while(true){try{return Double.parseDouble(scanner.nextLine());}catch(Exception e){System.out.print("Invalid:");}}}
}
