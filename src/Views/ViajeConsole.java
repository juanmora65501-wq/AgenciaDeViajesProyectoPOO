package Views;

import Controllers.ViajeController;
import Models.Viaje;
import java.util.List;
import java.util.Scanner;

public class ViajeConsole {
    private ViajeController viajeController;
    private Scanner scanner;

    public ViajeConsole() {
        this.viajeController = new ViajeController();
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== TRIP MANAGEMENT ===");
            System.out.println("1. List all trips");
            System.out.println("2. Add new trip");
            System.out.println("3. Update trip");
            System.out.println("4. Delete trip");
            System.out.println("5. Get average activities per plan (mixed transport)");
            System.out.println("6. Get sum flight costs by client");
            System.out.println("7. Get average segments for frequent clients");
            System.out.println("8. Get maximum activities (ground transport)");
            System.out.println("9. Get total transport costs for trip");
            System.out.println("10. Get average rooms per hotel (mixed transport)");
            System.out.println("11. Back to main menu");
            System.out.print("Select an option: ");

            int choice = readIntInput();

            switch (choice) {
                case 1:
                    listAllTrips();
                    break;
                case 2:
                    addTrip();
                    break;
                case 3:
                    updateTrip();
                    break;
                case 4:
                    deleteTrip();
                    break;
                case 5:
                    getAverageActivitiesMixedTransport();
                    break;
                case 6:
                    getSumFlightCostsByClient();
                    break;
                case 7:
                    getAverageSegmentsFrequentClients();
                    break;
                case 8:
                    getMaxActivitiesGroundTransport();
                    break;
                case 9:
                    getTotalTransportCosts();
                    break;
                case 10:
                    getAverageRoomsPerHotel();
                    break;
                case 11:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void listAllTrips() {
        System.out.println("\n--- ALL TRIPS ---");
        List<Viaje> trips = viajeController.getAllViajes();
        if (trips.isEmpty()) {
            System.out.println("No trips found.");
        } else {
            for (Viaje trip : trips) {
                System.out.println("ID: " + trip.getId() + " | Start: " + trip.getFechaInicio() + 
                                 " | End: " + trip.getFechaFin() + " | Cost: $" + trip.getCostoTotal());
            }
        }
    }

    private void addTrip() {
        System.out.println("\n--- ADD NEW TRIP ---");
        System.out.print("Enter start date (YYYY-MM-DD): ");
        String fechaInicio = scanner.nextLine();

        System.out.print("Enter end date (YYYY-MM-DD): ");
        String fechaFin = scanner.nextLine();

        System.out.print("Enter total cost: ");
        double costoTotal = readDoubleInput();

        boolean success = viajeController.addViaje(fechaInicio, fechaFin, costoTotal);
        if (success) {
            System.out.println("Trip added successfully.");
        } else {
            System.out.println("Failed to add trip. Please check the input data.");
        }
    }

    private void updateTrip() {
        System.out.println("\n--- UPDATE TRIP ---");
        System.out.print("Enter trip ID: ");
        String id = scanner.nextLine();

        Viaje trip = viajeController.getViajeById(id);
        if (trip == null) {
            System.out.println("Trip not found.");
            return;
        }

        System.out.println("Current data - Start: " + trip.getFechaInicio() + " | End: " + trip.getFechaFin() + " | Cost: $" + trip.getCostoTotal());
        System.out.print("Enter new start date (press Enter to keep current): ");
        String fechaInicio = scanner.nextLine();

        System.out.print("Enter new end date (press Enter to keep current): ");
        String fechaFin = scanner.nextLine();

        System.out.print("Enter new total cost (press Enter to keep current): ");
        String costoInput = scanner.nextLine();
        Double costoTotal = costoInput.isEmpty() ? null : Double.parseDouble(costoInput);

        boolean success = viajeController.updateViaje(id,
                fechaInicio.isEmpty() ? null : fechaInicio,
                fechaFin.isEmpty() ? null : fechaFin,
                costoTotal);

        if (success) {
            System.out.println("Trip updated successfully.");
        } else {
            System.out.println("Failed to update trip.");
        }
    }

    private void deleteTrip() {
        System.out.println("\n--- DELETE TRIP ---");
        System.out.print("Enter trip ID: ");
        String id = scanner.nextLine();

        boolean success = viajeController.deleteViaje(id);
        if (success) {
            System.out.println("Trip deleted successfully.");
        } else {
            System.out.println("Failed to delete trip. Trip may not exist.");
        }
    }

    private void getAverageActivitiesMixedTransport() {
        System.out.println("\n--- AVERAGE ACTIVITIES PER PLAN (MIXED TRANSPORT) ---");
        double promedio = viajeController.getPromedioActividadesPorPlanAereoTerrestre();
        System.out.println("Average activities per plan in mixed transport trips: " + String.format("%.2f", promedio));
    }

    private void getSumFlightCostsByClient() {
        System.out.println("\n--- SUM FLIGHT COSTS BY CLIENT ---");
        System.out.print("Enter client ID: ");
        String clienteId = scanner.nextLine();

        double suma = viajeController.getSumaCostosTrayectosVueloClienteEspecifico(clienteId);
        if (suma == -1) {
            System.out.println("Invalid client ID or no flights found.");
        } else {
            System.out.println("Total flight costs for client: $" + String.format("%.2f", suma));
        }
    }

    private void getAverageSegmentsFrequentClients() {
        System.out.println("\n--- AVERAGE SEGMENTS FOR FREQUENT CLIENTS ---");
        double promedio = viajeController.getPromedioTrayectosPorViajeClientesMultiples();
        System.out.println("Average segments per trip for frequent clients: " + String.format("%.2f", promedio));
    }

    private void getMaxActivitiesGroundTransport() {
        System.out.println("\n--- MAXIMUM ACTIVITIES (GROUND TRANSPORT) ---");
        int maximo = viajeController.getMaximoActividadesPlanViajesTerrestre();
        System.out.println("Maximum activities in ground transport trips: " + maximo);
    }

    private void getTotalTransportCosts() {
        System.out.println("\n--- TOTAL TRANSPORT COSTS FOR TRIP ---");
        System.out.print("Enter trip ID: ");
        String viajeId = scanner.nextLine();

        double suma = viajeController.getSumaCostosServiciosTransporteViaje(viajeId);
        if (suma == -1) {
            System.out.println("Invalid trip ID.");
        } else {
            System.out.println("Total transport costs for trip: $" + String.format("%.2f", suma));
        }
    }

    private void getAverageRoomsPerHotel() {
        System.out.println("\n--- AVERAGE ROOMS PER HOTEL (MIXED TRANSPORT) ---");
        double promedio = viajeController.getPromedioHabitacionesReservadasPorHotel();
        System.out.println("Average rooms per hotel in mixed transport trips: " + String.format("%.2f", promedio));
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

    private double readDoubleInput() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
}
