package Views;
import Controllers.ViajePlanController;
import Models.ViajePlan;
import java.util.List;
import java.util.Scanner;
public class ViajePlanConsole {
    private ViajePlanController controller;
    private Scanner scanner;
    public ViajePlanConsole() {
        this.controller = new ViajePlanController();
        this.scanner = new Scanner(System.in);
    }
    public void showMenu() {
        while (true) {
            System.out.println("\n=== TRIP-PLAN MANAGEMENT ===");
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
        List<ViajePlan> items = controller.getAllViajePlan();
        if (items.isEmpty()) {
            System.out.println("No items found.");
        } else {
            for (ViajePlan item : items) {
                System.out.println("ID: " + item.getId());
            }
        }
    }
    private void add() {
        System.out.println("\n--- ADD NEW ITEM ---");
        System.out.print("Enter viajeId: ");
        String viajeId = scanner.nextLine();
        System.out.print("Enter planId: ");
        String planId = scanner.nextLine();
        boolean success = controller.addViajePlan(viajeId, planId);
        System.out.println(success ? "Item added successfully." : "Failed to add item.");
    }
    private void update() {
        System.out.println("\n--- UPDATE ITEM ---");
        System.out.print("Enter item ID: ");
        String id = scanner.nextLine();
        ViajePlan item = controller.getViajePlanById(id);
        if (item == null) {
            System.out.println("Item not found.");
            return;
        }
        System.out.print("Enter new viajeId: ");
        String viajeId = scanner.nextLine();
        System.out.print("Enter new planId: ");
        String planId = scanner.nextLine();
        boolean success = controller.updateViajePlan(id, viajeId, planId);
        System.out.println(success ? "Item updated successfully." : "Failed to update item.");
    }
    private void delete() {
        System.out.println("\n--- DELETE ITEM ---");
        System.out.print("Enter item ID: ");
        String id = scanner.nextLine();
        boolean success = controller.deleteViajePlan(id);
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
