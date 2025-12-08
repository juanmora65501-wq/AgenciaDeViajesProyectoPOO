package Views;

import Controllers.ActividadTuristicaController;
import Models.ActividadTuristica;
import java.util.List;
import java.util.Scanner;

public class ActividadTuristicaConsole {
    private ActividadTuristicaController controller;
    private Scanner scanner;

    public ActividadTuristicaConsole() {
        this.controller = new ActividadTuristicaController();
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== ACTIVITY MANAGEMENT ===");
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
        List<ActividadTuristica> items = controller.getAllActividades();
        if (items.isEmpty()) {
            System.out.println("No items found.");
        } else {
            for (ActividadTuristica item : items) {
                System.out.println("ID: " + item.getId() + " | Name: " + item.getNombre());
            }
        }
    }

    private void add() {
        System.out.println("\n--- ADD NEW ITEM ---");
        System.out.print("Enter nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Enter descripcion: ");
        String descripcion = scanner.nextLine();
        System.out.print("Enter municipioId: ");
        String municipioId = scanner.nextLine();
        System.out.print("Enter duracion: ");
        String duracion = scanner.nextLine();
        System.out.print("Enter costo: ");
        double costo = readDoubleInput();
        boolean success = controller.addActividad(nombre, descripcion, municipioId, duracion, costo);
        System.out.println(success ? "Item added successfully." : "Failed to add item.");
    }

    private void update() {
        System.out.println("\n--- UPDATE ITEM ---");
        System.out.print("Enter item ID: ");
        String id = scanner.nextLine();
        ActividadTuristica item = controller.getActividadById(id);
        if (item == null) {
            System.out.println("Item not found.");
            return;
        }
        System.out.print("Enter new nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Enter new descripcion: ");
        String descripcion = scanner.nextLine();
        System.out.print("Enter new duracion: ");
        String duracion = scanner.nextLine();
        System.out.print("Enter new costo: ");
        String costoInput = scanner.nextLine();
        Double costo = costoInput.isEmpty() ? null : Double.parseDouble(costoInput);
        boolean success = controller.updateActividad(id, nombre.isEmpty() ? null : nombre, descripcion.isEmpty() ? null : descripcion, duracion.isEmpty() ? null : duracion, costo);
        System.out.println(success ? "Item updated successfully." : "Failed to update item.");
    }

    private void delete() {
        System.out.println("\n--- DELETE ITEM ---");
        System.out.print("Enter item ID: ");
        String id = scanner.nextLine();
        boolean success = controller.deleteActividad(id);
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

    private double readDoubleInput() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input: ");
            }
        }
    }
}
