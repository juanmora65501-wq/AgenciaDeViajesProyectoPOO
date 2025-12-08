package Views;

import Controllers.AerolineaController;
import Models.Aerolinea;
import java.util.List;
import java.util.Scanner;

public class AerolineaConsole {
    private AerolineaController aerolineaController;
    private Scanner scanner;

    public AerolineaConsole() {
        this.aerolineaController = new AerolineaController();
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== AIRLINE MANAGEMENT ===");
            System.out.println("1. List all airlines");
            System.out.println("2. Add new airline");
            System.out.println("3. Update airline");
            System.out.println("4. Delete airline");
            System.out.println("5. Get minimum flight cost");
            System.out.println("6. Back to main menu");
            System.out.print("Select an option: ");

            int choice = readIntInput();

            switch (choice) {
                case 1:
                    listAllAirlines();
                    break;
                case 2:
                    addAirline();
                    break;
                case 3:
                    updateAirline();
                    break;
                case 4:
                    deleteAirline();
                    break;
                case 5:
                    getMinimumFlightCost();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void listAllAirlines() {
        System.out.println("\n--- ALL AIRLINES ---");
        List<Aerolinea> airlines = aerolineaController.getAllAerolineas();
        if (airlines.isEmpty()) {
            System.out.println("No airlines found.");
        } else {
            for (Aerolinea airline : airlines) {
                System.out.println("ID: " + airline.getId() + " | Name: " + airline.getNombre() + " | Contact: " + airline.getContacto());
            }
        }
    }

    private void addAirline() {
        System.out.println("\n--- ADD NEW AIRLINE ---");
        System.out.print("Enter airline name: ");
        String name = scanner.nextLine();

        System.out.print("Enter contact: ");
        String contact = scanner.nextLine();

        boolean success = aerolineaController.addAerolinea(name, contact);
        if (success) {
            System.out.println("Airline added successfully.");
        } else {
            System.out.println("Failed to add airline. Please check the input data.");
        }
    }

    private void updateAirline() {
        System.out.println("\n--- UPDATE AIRLINE ---");
        System.out.print("Enter airline ID: ");
        String id = scanner.nextLine();

        Aerolinea airline = aerolineaController.getAerolineaById(id);
        if (airline == null) {
            System.out.println("Airline not found.");
            return;
        }

        System.out.println("Current data - Name: " + airline.getNombre() + " | Contact: " + airline.getContacto());
        System.out.print("Enter new name (press Enter to keep current): ");
        String name = scanner.nextLine();

        System.out.print("Enter new contact (press Enter to keep current): ");
        String contact = scanner.nextLine();

        boolean success = aerolineaController.updateAerolinea(id,
                name.isEmpty() ? null : name,
                contact.isEmpty() ? null : contact);

        if (success) {
            System.out.println("Airline updated successfully.");
        } else {
            System.out.println("Failed to update airline.");
        }
    }

    private void deleteAirline() {
        System.out.println("\n--- DELETE AIRLINE ---");
        System.out.print("Enter airline ID: ");
        String id = scanner.nextLine();

        boolean success = aerolineaController.deleteAerolinea(id);
        if (success) {
            System.out.println("Airline deleted successfully.");
        } else {
            System.out.println("Failed to delete airline. Airline may not exist.");
        }
    }

    private void getMinimumFlightCost() {
        System.out.println("\n--- MINIMUM FLIGHT COST ---");
        System.out.print("Enter airline ID: ");
        String airlineId = scanner.nextLine();

        double minimumCost = aerolineaController.getMinimoCostoTrayectoAereoParaAerolinea(airlineId);
        if (minimumCost == -1) {
            System.out.println("No flights found for this airline or invalid airline ID.");
        } else {
            System.out.println("Minimum flight cost for this airline: $" + minimumCost);
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
