package Views;

import java.util.Scanner;

public class MainConsole {
    private Scanner scanner;

    public MainConsole() {
        this.scanner = new Scanner(System.in);
    }

    public void showMainMenu() {
        while (true) {
            System.out.println("\n========================================");
            System.out.println("   TRAVEL AGENCY MANAGEMENT SYSTEM");
            System.out.println("========================================");
            System.out.println("1. Trip Management");
            System.out.println("2. Client Management");
            System.out.println("3. Plan Management");
            System.out.println("4. Hotel Management");
            System.out.println("5. Airline Management");
            System.out.println("6. Guide Management");
            System.out.println("7. Tourist Activity Management");
            System.out.println("8. Aircraft Management");
            System.out.println("9. Car Management");
            System.out.println("10. Room Management");
            System.out.println("11. Municipality Management");
            System.out.println("12. Route Management");
            System.out.println("13. Vehicle Management");
            System.out.println("14. User Management");
            System.out.println("15. Payment Quota Management");
            System.out.println("16. Transport Itinerary Management");
            System.out.println("17. Transport Service Management");
            System.out.println("18. Guide Activity Management");
            System.out.println("19. Client-Trip Management");
            System.out.println("20. Room-Itinerary Management");
            System.out.println("21. Plan-Activity Management");
            System.out.println("22. Trip-Plan Management");
            System.out.println("23. Exit");
            System.out.print("Select an option: ");

            int choice = readIntInput();

            switch (choice) {
                case 1:
                    ViajeConsole viajeConsole = new ViajeConsole();
                    viajeConsole.showMenu();
                    break;
                case 2:
                    ClienteConsole clienteConsole = new ClienteConsole();
                    clienteConsole.showMenu();
                    break;
                case 3:
                    PlanConsole planConsole = new PlanConsole();
                    planConsole.showMenu();
                    break;
                case 4:
                    HotelConsole hotelConsole = new HotelConsole();
                    hotelConsole.showMenu();
                    break;
                case 5:
                    AerolineaConsole aerolineaConsole = new AerolineaConsole();
                    aerolineaConsole.showMenu();
                    break;
                case 6:
                    GuiaConsole guiaConsole = new GuiaConsole();
                    guiaConsole.showMenu();
                    break;
                case 7:
                    ActividadTuristicaConsole actividadConsole = new ActividadTuristicaConsole();
                    actividadConsole.showMenu();
                    break;
                case 8:
                    AeronaveConsole aeronaveConsole = new AeronaveConsole();
                    aeronaveConsole.showMenu();
                    break;
                case 9:
                    CarroConsole carroConsole = new CarroConsole();
                    carroConsole.showMenu();
                    break;
                case 10:
                    HabitacionConsole habitacionConsole = new HabitacionConsole();
                    habitacionConsole.showMenu();
                    break;
                case 11:
                    MunicipioConsole municipioConsole = new MunicipioConsole();
                    municipioConsole.showMenu();
                    break;
                case 12:
                    TrayectoConsole trayectoConsole = new TrayectoConsole();
                    trayectoConsole.showMenu();
                    break;
                case 13:
                    VehiculoConsole vehiculoConsole = new VehiculoConsole();
                    vehiculoConsole.showMenu();
                    break;
                case 14:
                    UsuarioConsole usuarioConsole = new UsuarioConsole();
                    usuarioConsole.showMenu();
                    break;
                case 15:
                    CuotaConsole cuotaConsole = new CuotaConsole();
                    cuotaConsole.showMenu();
                    break;
                case 16:
                    ItinerarioTransporteConsole itinerarioConsole = new ItinerarioTransporteConsole();
                    itinerarioConsole.showMenu();
                    break;
                case 17:
                    ServicioTransporteConsole servicioConsole = new ServicioTransporteConsole();
                    servicioConsole.showMenu();
                    break;
                case 18:
                    ActividadGuiaConsole actividadGuiaConsole = new ActividadGuiaConsole();
                    actividadGuiaConsole.showMenu();
                    break;
                case 19:
                    ClienteViajeConsole clienteViajeConsole = new ClienteViajeConsole();
                    clienteViajeConsole.showMenu();
                    break;
                case 20:
                    HabitacionItinerarioConsole habitacionItinerarioConsole = new HabitacionItinerarioConsole();
                    habitacionItinerarioConsole.showMenu();
                    break;
                case 21:
                    PlanActividadConsole planActividadConsole = new PlanActividadConsole();
                    planActividadConsole.showMenu();
                    break;
                case 22:
                    ViajePlanConsole viajePlanConsole = new ViajePlanConsole();
                    viajePlanConsole.showMenu();
                    break;
                case 23:
                    System.out.println("\nThank you for using Travel Agency Management System. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
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

    public static void main(String[] args) {
        MainConsole mainConsole = new MainConsole();
        mainConsole.showMainMenu();
    }
}
