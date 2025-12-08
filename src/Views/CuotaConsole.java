package Views;
import Controllers.CuotaController;
import Models.Cuota;
import java.util.List;
import java.util.Scanner;
public class CuotaConsole {
    private CuotaController controller;
    private Scanner scanner;
    public CuotaConsole() {this.controller = new CuotaController();this.scanner = new Scanner(System.in);}
    public void showMenu() {
        while(true) {
            System.out.println("\n=== QUOTA ===");
            System.out.println("1.List\n2.Add\n3.Update\n4.Delete\n5.Back");
            System.out.print("Option:");
            switch(readIntInput()) {case 1:listAll();break;case 2:add();break;case 3:update();break;case 4:delete();break;case 5:return;default:System.out.println("Invalid");}
        }
    }
    private void listAll() {System.out.println("\n--- LIST ---");List<Cuota> l=controller.getAllCuotas();if(l.isEmpty())System.out.println("Empty");else for(Cuota c:l)System.out.println("ID:"+c.getId());}
    private void add() {System.out.println("\n--- ADD ---");System.out.print("viajeId:");String v=scanner.nextLine();System.out.print("monto:");double m=readDoubleInput();System.out.print("numeroCuota:");int n=readIntInput();System.out.print("fechaVencimiento:");String f=scanner.nextLine();boolean s=controller.addCuota(v,m,n,f);System.out.println(s?"OK":"Failed");}
    private void update() {System.out.println("\n--- UPDATE ---");System.out.print("ID:");String id=scanner.nextLine();if(controller.getCuotaById(id)==null){System.out.println("Not found");return;}System.out.print("monto:");String m_s=scanner.nextLine();Double m=m_s.isEmpty()?null:Double.parseDouble(m_s);System.out.print("numeroCuota:");String n_s=scanner.nextLine();Integer n=n_s.isEmpty()?null:Integer.parseInt(n_s);System.out.print("fechaVencimiento:");String f=scanner.nextLine();boolean s=controller.updateCuota(id,m,n,f.isEmpty()?null:f);System.out.println(s?"OK":"Failed");}
    private void delete() {System.out.println("\n--- DELETE ---");System.out.print("ID:");String id=scanner.nextLine();boolean s=controller.deleteCuota(id);System.out.println(s?"OK":"Failed");}
    private int readIntInput() {while(true){try{return Integer.parseInt(scanner.nextLine());}catch(Exception e){System.out.print("Invalid:");}}}
    private double readDoubleInput() {while(true){try{return Double.parseDouble(scanner.nextLine());}catch(Exception e){System.out.print("Invalid:");}}}
}
