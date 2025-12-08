package Views;
import Controllers.ActividadGuiaController;
import Models.ActividadGuia;
import java.util.List;
import java.util.Scanner;
public class ActividadGuiaConsole {
    private ActividadGuiaController controller;
    private Scanner scanner;
    public ActividadGuiaConsole() {this.controller = new ActividadGuiaController();this.scanner = new Scanner(System.in);}
    public void showMenu() {
        while(true) {
            System.out.println("\n=== GUIDE ACTIVITY ===");
            System.out.println("1.List\n2.Add\n3.Update\n4.Delete\n5.Back");
            System.out.print("Option:");
            switch(readIntInput()) {case 1:listAll();break;case 2:add();break;case 3:update();break;case 4:delete();break;case 5:return;default:System.out.println("Invalid");}
        }
    }
    private void listAll() {System.out.println("\n--- LIST ---");List<ActividadGuia> l=controller.getAllActividadGuia();if(l.isEmpty())System.out.println("Empty");else for(ActividadGuia a:l)System.out.println("ID:"+a.getId());}
    private void add() {System.out.println("\n--- ADD ---");System.out.print("guiaId:");String g=scanner.nextLine();System.out.print("actividadId:");String a=scanner.nextLine();boolean s=controller.addActividadGuia(g,a);System.out.println(s?"OK":"Failed");}
    private void update() {System.out.println("\n--- UPDATE ---");System.out.print("ID:");String id=scanner.nextLine();if(controller.getActividadGuiaById(id)==null){System.out.println("Not found");return;}System.out.print("guiaId:");String g=scanner.nextLine();System.out.print("actividadId:");String a=scanner.nextLine();boolean s=controller.updateActividadGuia(id,g.isEmpty()?null:g,a.isEmpty()?null:a);System.out.println(s?"OK":"Failed");}
    private void delete() {System.out.println("\n--- DELETE ---");System.out.print("ID:");String id=scanner.nextLine();boolean s=controller.deleteActividadGuia(id);System.out.println(s?"OK":"Failed");}
    private int readIntInput() {while(true){try{return Integer.parseInt(scanner.nextLine());}catch(Exception e){System.out.print("Invalid:");}}}
}
