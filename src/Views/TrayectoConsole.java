package Views;

import Controllers.TrayectoController;
import Models.Trayecto;
import java.util.List;
import java.util.Scanner;

public class TrayectoConsole {
    private TrayectoController controller;
    private Scanner scanner;

    public TrayectoConsole() {
        this.controller = new TrayectoController();
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== ROUTE MANAGEMENT ===");
            System.out.println("1. List all");
            System.out.println("2. Add new");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("5. Back to main menu");
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
        List<Trayecto> items = controller.getAllTrayectos();
        if (items.isEmpty()) {
            System.out.println("No items found.");
        } else {
            for (Trayecto item : items) {
                System.out.println("ID: " + item.getId());
            }
        }
    }

    private void add() {
        System.out.println("\n--- ADD NEW ITEM ---");
        System.out.print("Enter origenId: ");
        String origenId = scanner.nextLine();
        System.out.print("Enter destinoId: ");
        String destinoId = scanner.nextLine();
        boolean success = controller.addTrayecto(origenId, destinoId);
        System.out.println(success ? "Item added successfully." : "Failed to add item.");
    }

    private void update() {
        System.out.println("\n--- UPDATE ITEM ---");
        System.out.print("Enter item ID: ");
        String id = scanner.nextLine();
        Trayecto item = controller.getTrayectoById(id);
        if (item == null) {
            System.out.println("Item not found.");
            return;
        }
        System.out.print("Enter new origenId: ");
        String origenId = scanner.nextLine();
        System.out.print("Enter new destinoId: ");
        String destinoId = scanner.nextLine();
        boolean success = controller.updateTrayecto(id, origenId, destinoId);
        System.out.println(success ? "Item updated successfully." : "Failed to update item.");
    }

    private void delete() {
        System.out.println("\n--- DELETE ITEM ---");
        System.out.print("Enter item ID: ");
        String id = scanner.nextLine();
        boolean success = controller.deleteTrayecto(id);
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
