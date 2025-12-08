package Views;
import Controllers.CarroController;
import Models.Carro;
import java.util.List;
import java.util.Scanner;
public class CarroConsole {
    private CarroController controller;
    private Scanner scanner;
    public CarroConsole() {
        this.controller = new CarroController();
        this.scanner = new Scanner(System.in);
    }
    public void showMenu() {
        while (true) {
            System.out.println("\n=== CAR MANAGEMENT ===");
            System.out.println("1. List all\n2. Add new\n3. Update\n4. Delete\n5. Back");
            System.out.print("Select: ");
            int c = readIntInput();
            switch(c) {case 1:listAll();break;case 2:add();break;case 3:update();break;case 4:delete();break;case 5:return;default:System.out.println("Invalid");}
        }
    }
    private void listAll() {System.out.println("\n--- ALL ---");List<Carro> i=controller.getAllCarros();if(i.isEmpty())System.out.println("None");else for(Carro x:i)System.out.println("ID:"+x.getId());}
    private void add() {System.out.println("\n--- ADD ---");System.out.print("hotelId:");String h=scanner.nextLine();System.out.print("marca:");String m=scanner.nextLine();System.out.print("modelo:");String mo=scanner.nextLine();System.out.print("placa:");String p=scanner.nextLine();boolean s=controller.addCarro(h,m,mo,p);System.out.println(s?"OK":"Failed");}
    private void update() {System.out.println("\n--- UPDATE ---");System.out.print("ID:");String id=scanner.nextLine();Carro c=controller.getCarroById(id);if(c==null){System.out.println("Not found");return;}System.out.print("marca:");String m=scanner.nextLine();System.out.print("modelo:");String mo=scanner.nextLine();System.out.print("placa:");String p=scanner.nextLine();boolean s=controller.updateCarro(id,m,mo,p);System.out.println(s?"OK":"Failed");}
    private void delete() {System.out.println("\n--- DELETE ---");System.out.print("ID:");String id=scanner.nextLine();boolean s=controller.deleteCarro(id);System.out.println(s?"OK":"Failed");}
    private int readIntInput() {while(true){try{return Integer.parseInt(scanner.nextLine());}catch(Exception e){System.out.print("Invalid:");}}}
}
