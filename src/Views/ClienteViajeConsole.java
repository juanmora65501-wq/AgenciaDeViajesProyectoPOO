package Views;

import Controllers.ClienteViajeController;
import Models.ClienteViaje;
import java.util.List;
import java.util.Scanner;

public class ClienteViajeConsole {
    private ClienteViajeController controller;
    private Scanner scanner;

    public ClienteViajeConsole() {
        this.controller = new ClienteViajeController();
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== CLIENT-TRIP MANAGEMENT ===");
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
        List<ClienteViaje> items = controller.getAllClienteViaje();
        if (items.isEmpty()) {
            System.out.println("No items found.");
        } else {
            for (ClienteViaje item : items) {
                System.out.println("ID: " + item.getId());
            }
        }
    }

    private void add() {
        System.out.println("\n--- ADD NEW ITEM ---");
        System.out.print("Enter clienteId: ");
        String clienteId = scanner.nextLine();
        System.out.print("Enter viajeId: ");
        String viajeId = scanner.nextLine();
        boolean success = controller.addClienteViaje(clienteId, viajeId);
        System.out.println(success ? "Item added successfully." : "Failed to add item.");
    }

    private void update() {
        System.out.println("\n--- UPDATE ITEM ---");
        System.out.print("Enter item ID: ");
        String id = scanner.nextLine();
        ClienteViaje item = controller.getClienteViajeById(id);
        if (item == null) {
            System.out.println("Item not found.");
            return;
        }
        System.out.print("Enter new clienteId: ");
        String clienteId = scanner.nextLine();
        System.out.print("Enter new viajeId: ");
        String viajeId = scanner.nextLine();
        boolean success = controller.updateClienteViaje(id, clienteId, viajeId);
        System.out.println(success ? "Item updated successfully." : "Failed to update item.");
    }

    private void delete() {
        System.out.println("\n--- DELETE ITEM ---");
        System.out.print("Enter item ID: ");
        String id = scanner.nextLine();
        boolean success = controller.deleteClienteViaje(id);
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
