package Views;

import Controllers.ClienteController;
import Models.Cliente;
import java.util.List;
import java.util.Scanner;

public class ClienteConsole {
    private ClienteController clienteController;
    private Scanner scanner;

    public ClienteConsole() {
        this.clienteController = new ClienteController();
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== CLIENT MANAGEMENT ===");
            System.out.println("1. List all clients");
            System.out.println("2. Add new client");
            System.out.println("3. Update client");
            System.out.println("4. Delete client");
            System.out.println("5. Get activities in trips with car");
            System.out.println("6. Get client trips by airline");
            System.out.println("7. Get client activity participation frequency");
            System.out.println("8. Back to main menu");
            System.out.print("Select an option: ");

            int choice = readIntInput();

            switch (choice) {
                case 1:
                    listAllClients();
                    break;
                case 2:
                    addClient();
                    break;
                case 3:
                    updateClient();
                    break;
                case 4:
                    deleteClient();
                    break;
                case 5:
                    getActivitiesWithCar();
                    break;
                case 6:
                    getClientTripsByAirline();
                    break;
                case 7:
                    getActivityParticipationFrequency();
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void listAllClients() {
        System.out.println("\n--- ALL CLIENTS ---");
        List<Cliente> clients = clienteController.getAllClientes();
        if (clients.isEmpty()) {
            System.out.println("No clients found.");
        } else {
            for (Cliente client : clients) {
                System.out.println("ID: " + client.getId() + " | User ID: " + client.getUsuarioId() + 
                                 " | Preferences: " + client.getPreferencias());
            }
        }
    }

    private void addClient() {
        System.out.println("\n--- ADD NEW CLIENT ---");
        System.out.print("Enter user ID: ");
        String usuarioId = scanner.nextLine();

        System.out.print("Enter preferences: ");
        String preferencias = scanner.nextLine();

        boolean success = clienteController.addCliente(usuarioId, preferencias);
        if (success) {
            System.out.println("Client added successfully.");
        } else {
            System.out.println("Failed to add client. Please check the input data.");
        }
    }

    private void updateClient() {
        System.out.println("\n--- UPDATE CLIENT ---");
        System.out.print("Enter client ID: ");
        String id = scanner.nextLine();

        Cliente client = clienteController.getClienteById(id);
        if (client == null) {
            System.out.println("Client not found.");
            return;
        }

        System.out.println("Current data - User ID: " + client.getUsuarioId() + " | Preferences: " + client.getPreferencias());
        System.out.print("Enter new preferences (press Enter to keep current): ");
        String preferencias = scanner.nextLine();

        boolean success = clienteController.updateCliente(id,
                preferencias.isEmpty() ? null : preferencias);

        if (success) {
            System.out.println("Client updated successfully.");
        } else {
            System.out.println("Failed to update client.");
        }
    }

    private void deleteClient() {
        System.out.println("\n--- DELETE CLIENT ---");
        System.out.print("Enter client ID: ");
        String id = scanner.nextLine();

        boolean success = clienteController.deleteCliente(id);
        if (success) {
            System.out.println("Client deleted successfully.");
        } else {
            System.out.println("Failed to delete client. Client may not exist.");
        }
    }

    private void getActivitiesWithCar() {
        System.out.println("\n--- ACTIVITIES IN TRIPS WITH CAR ---");
        System.out.print("Enter client ID: ");
        String clienteId = scanner.nextLine();

        int cantidad = clienteController.getCantidadActividadesViajesClientesConCarro(clienteId);
        System.out.println("Number of activities in trips with car: " + cantidad);
    }

    private void getClientTripsByAirline() {
        System.out.println("\n--- CLIENT TRIPS BY AIRLINE ---");
        System.out.print("Enter client ID: ");
        String clienteId = scanner.nextLine();

        System.out.print("Enter airline ID: ");
        String aerolineaId = scanner.nextLine();

        int cantidad = clienteController.getCantidadViajesClientePorAerolinea(clienteId, aerolineaId);
        System.out.println("Number of trips by airline: " + cantidad);
    }

    private void getActivityParticipationFrequency() {
        System.out.println("\n--- ACTIVITY PARTICIPATION FREQUENCY ---");
        System.out.print("Enter client ID: ");
        String clienteId = scanner.nextLine();

        int frecuencia = clienteController.getFrecuenciaParticipacionActividadesCliente(clienteId);
        System.out.println("Activity participation frequency: " + frecuencia);
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
