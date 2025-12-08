package Views;

import Controllers.GuiaController;
import Models.Guia;
import java.util.List;
import java.util.Scanner;

public class GuiaConsole {
    private GuiaController guiaController;
    private Scanner scanner;

    public GuiaConsole() {
        this.guiaController = new GuiaController();
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== GUIDE MANAGEMENT ===");
            System.out.println("1. List all guides");
            System.out.println("2. Add new guide");
            System.out.println("3. Update guide");
            System.out.println("4. Delete guide");
            System.out.println("5. Back to main menu");
            System.out.print("Select an option: ");

            int choice = readIntInput();

            switch (choice) {
                case 1:
                    listAllGuides();
                    break;
                case 2:
                    addGuide();
                    break;
                case 3:
                    updateGuide();
                    break;
                case 4:
                    deleteGuide();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void listAllGuides() {
        System.out.println("\n--- ALL GUIDES ---");
        List<Guia> guides = guiaController.getAllGuias();
        if (guides.isEmpty()) {
            System.out.println("No guides found.");
        } else {
            for (Guia guide : guides) {
                System.out.println("ID: " + guide.getId() + " | User ID: " + guide.getUsuarioId() + 
                                 " | Certification: " + guide.getCertificacion() + " | Languages: " + guide.getIdiomas());
            }
        }
    }

    private void addGuide() {
        System.out.println("\n--- ADD NEW GUIDE ---");
        System.out.print("Enter user ID: ");
        String usuarioId = scanner.nextLine();

        System.out.print("Enter certification: ");
        String certificacion = scanner.nextLine();

        System.out.print("Enter languages (comma-separated): ");
        String idiomas = scanner.nextLine();

        boolean success = guiaController.addGuia(usuarioId, certificacion, idiomas);
        if (success) {
            System.out.println("Guide added successfully.");
        } else {
            System.out.println("Failed to add guide. Please check the input data.");
        }
    }

    private void updateGuide() {
        System.out.println("\n--- UPDATE GUIDE ---");
        System.out.print("Enter guide ID: ");
        String id = scanner.nextLine();

        Guia guide = guiaController.getGuiaById(id);
        if (guide == null) {
            System.out.println("Guide not found.");
            return;
        }

        System.out.println("Current data - User ID: " + guide.getUsuarioId() + " | Certification: " + guide.getCertificacion());
        System.out.print("Enter new certification (press Enter to keep current): ");
        String certificacion = scanner.nextLine();

        System.out.print("Enter new languages (press Enter to keep current): ");
        String idiomas = scanner.nextLine();

        boolean success = guiaController.updateGuia(id,
                certificacion.isEmpty() ? null : certificacion,
                idiomas.isEmpty() ? null : idiomas);

        if (success) {
            System.out.println("Guide updated successfully.");
        } else {
            System.out.println("Failed to update guide.");
        }
    }

    private void deleteGuide() {
        System.out.println("\n--- DELETE GUIDE ---");
        System.out.print("Enter guide ID: ");
        String id = scanner.nextLine();

        boolean success = guiaController.deleteGuia(id);
        if (success) {
            System.out.println("Guide deleted successfully.");
        } else {
            System.out.println("Failed to delete guide. Guide may not exist.");
        }
    }

    private int readIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
}
