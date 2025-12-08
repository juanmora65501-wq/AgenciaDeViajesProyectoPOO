package Views;
import Controllers.AeronaveController;
import Models.Aeronave;
import java.util.List;
import java.util.Scanner;
public class AeronaveConsole {
    private AeronaveController controller;
    private Scanner scanner;
    public AeronaveConsole() {this.controller = new AeronaveController();this.scanner = new Scanner(System.in);}
    public void showMenu() {
        while(true) {
            System.out.println("\n=== AIRCRAFT ===");
            System.out.println("1.List\n2.Add\n3.Update\n4.Delete\n5.Back");
            System.out.print("Option:");
            switch(readIntInput()) {
                case 1:listAll();break;case 2:add();break;case 3:update();break;case 4:delete();break;case 5:return;default:System.out.println("Invalid");
            }
        }
    }
    private void listAll() {System.out.println("\n--- LIST ---");List<Aeronave> l=controller.getAllAeronaves();if(l.isEmpty())System.out.println("Empty");else for(Aeronave a:l)System.out.println("ID:"+a.getId());}
    private void add() {System.out.println("\n--- ADD ---");System.out.print("aerolineaId:");String a=scanner.nextLine();System.out.print("matricula:");String m=scanner.nextLine();System.out.print("autonomia:");double au=readDoubleInput();System.out.print("tipoAeronave:");String t=scanner.nextLine();System.out.print("tipo:");String tp=scanner.nextLine();System.out.print("marca:");String ma=scanner.nextLine();System.out.print("modelo:");String mo=scanner.nextLine();System.out.print("capacidad:");int c=readIntInput();System.out.print("placa:");String p=scanner.nextLine();boolean s=controller.addAeronave(a,m,au,t,tp,ma,mo,c,p);System.out.println(s?"OK":"Failed");}
    private void update() {System.out.println("\n--- UPDATE ---");System.out.print("ID:");String id=scanner.nextLine();if(controller.getAeronaveById(id)==null){System.out.println("Not found");return;}System.out.print("aerolineaId:");String a=scanner.nextLine();System.out.print("matricula:");String m=scanner.nextLine();System.out.print("autonomia:");String au_s=scanner.nextLine();Double au=au_s.isEmpty()?null:Double.parseDouble(au_s);System.out.print("tipoAeronave:");String t=scanner.nextLine();System.out.print("tipo:");String tp=scanner.nextLine();System.out.print("marca:");String ma=scanner.nextLine();System.out.print("modelo:");String mo=scanner.nextLine();System.out.print("capacidad:");String c_s=scanner.nextLine();Integer c=c_s.isEmpty()?null:Integer.parseInt(c_s);System.out.print("placa:");String p=scanner.nextLine();boolean s=controller.updateAeronave(id,a,m,au,t,tp,ma,mo,c,p);System.out.println(s?"OK":"Failed");}
    private void delete() {System.out.println("\n--- DELETE ---");System.out.print("ID:");String id=scanner.nextLine();boolean s=controller.deleteAeronave(id);System.out.println(s?"OK":"Failed");}
    private int readIntInput() {while(true){try{return Integer.parseInt(scanner.nextLine());}catch(Exception e){System.out.print("Invalid:");}}}
    private double readDoubleInput() {while(true){try{return Double.parseDouble(scanner.nextLine());}catch(Exception e){System.out.print("Invalid:");}}}
}
