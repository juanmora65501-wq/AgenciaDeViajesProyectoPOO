package Views;
import Controllers.VehiculoController;
import Models.Vehiculo;
import java.util.List;
import java.util.Scanner;
public class VehiculoConsole {
    private VehiculoController controller;
    private Scanner scanner;
    public VehiculoConsole() {
        this.controller = new VehiculoController();
        this.scanner = new Scanner(System.in);
    }
    public void showMenu() {
        while (true) {
            System.out.println("\n=== VEHICLE MANAGEMENT ===");
            System.out.println("1. List all\n2. Add new\n3. Update\n4. Delete\n5. Back to main menu");
            System.out.print("Select an option: ");
            int choice = readIntInput();
            switch (choice) {
                case 1: listAll(); break;
                case 2: add(); break;
                case 3: update(); break;
                case 4: delete(); break;
                case 5: return;
                default: System.out.println("Invalid option.");
            }
        }
    }
    private void listAll() {
        System.out.println("\n--- ALL ITEMS ---");
        List<Vehiculo> items = controller.getAllVehiculos();
        if (items.isEmpty()) {
            System.out.println("No items found.");
        } else {
            for (Vehiculo item : items) {
                System.out.println("ID: " + item.getId());
            }
        }
    }
    private void add() {
        System.out.println("\n--- ADD NEW ITEM ---");
        System.out.print("Enter marca: ");
        String marca = scanner.nextLine();
        System.out.print("Enter modelo: ");
        String modelo = scanner.nextLine();
        System.out.print("Enter placa: ");
        String placa = scanner.nextLine();
        System.out.print("Enter year: ");
        int year = readIntInput();
        System.out.print("Enter tipo: ");
        String tipo = scanner.nextLine();
        boolean success = controller.addVehiculo(marca, modelo, placa, year, tipo);
        System.out.println(success ? "Item added successfully." : "Failed to add item.");
    }
    private void update() {
        System.out.println("\n--- UPDATE ITEM ---");
        System.out.print("Enter item ID: ");
        String id = scanner.nextLine();
        Vehiculo item = controller.getVehiculoById(id);
        if (item == null) {
            System.out.println("Item not found.");
            return;
        }
        System.out.print("Enter new marca: ");
        String marca = scanner.nextLine();
        System.out.print("Enter new modelo: ");
        String modelo = scanner.nextLine();
        System.out.print("Enter new placa: ");
        String placa = scanner.nextLine();
        System.out.print("Enter new year: ");
        String yearInput = scanner.nextLine();
        Integer year = yearInput.isEmpty() ? null : Integer.parseInt(yearInput);
        System.out.print("Enter new tipo: ");
        String tipo = scanner.nextLine();
        boolean success = controller.updateVehiculo(id, marca.isEmpty() ? null : marca, modelo.isEmpty() ? null : modelo, placa.isEmpty() ? null : placa, year, tipo.isEmpty() ? null : tipo);
        System.out.println(success ? "Item updated successfully." : "Failed to update item.");
    }
    private void delete() {
        System.out.println("\n--- DELETE ITEM ---");
        System.out.print("Enter item ID: ");
        String id = scanner.nextLine();
        boolean success = controller.deleteVehiculo(id);
        System.out.println(success ? "Item deleted successfully." : "Failed to delete item.");
    }
    private int readIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input: ");
            }
        }
    }
}
