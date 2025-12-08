package Views;

import Controllers.HotelController;
import Models.Hotel;
import java.util.List;
import java.util.Scanner;

public class HotelConsole {
    private HotelController hotelController;
    private Scanner scanner;

    public HotelConsole() {
        this.hotelController = new HotelController();
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== HOTEL MANAGEMENT ===");
            System.out.println("1. List all hotels");
            System.out.println("2. Add new hotel");
            System.out.println("3. Update hotel");
            System.out.println("4. Delete hotel");
            System.out.println("5. Hotels with mixed transport reservations");
            System.out.println("6. Back to main menu");
            System.out.print("Select an option: ");

            int choice = readIntInput();

            switch (choice) {
                case 1:
                    listAllHotels();
                    break;
                case 2:
                    addHotel();
                    break;
                case 3:
                    updateHotel();
                    break;
                case 4:
                    deleteHotel();
                    break;
                case 5:
                    getHotelsWithMixedTransport();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void listAllHotels() {
        System.out.println("\n--- ALL HOTELS ---");
        List<Hotel> hotels = hotelController.getAllHoteles();
        if (hotels.isEmpty()) {
            System.out.println("No hotels found.");
        } else {
            for (Hotel hotel : hotels) {
                System.out.println("ID: " + hotel.getId() + " | Name: " + hotel.getNombre() + 
                                 " | Rooms: " + hotel.getTotalHabitaciones() + " | Contact: " + hotel.getContacto());
            }
        }
    }

    private void addHotel() {
        System.out.println("\n--- ADD NEW HOTEL ---");
        System.out.print("Enter hotel name: ");
        String name = scanner.nextLine();

        System.out.print("Enter municipality ID: ");
        String municipioId = scanner.nextLine();

        System.out.print("Enter address: ");
        String direccion = scanner.nextLine();

        System.out.print("Enter contact: ");
        String contact = scanner.nextLine();

        System.out.print("Enter total rooms: ");
        int totalRooms = readIntInput();

        boolean success = hotelController.addHotel(name, municipioId, direccion, contact, totalRooms);
        if (success) {
            System.out.println("Hotel added successfully.");
        } else {
            System.out.println("Failed to add hotel. Please check the input data.");
        }
    }

    private void updateHotel() {
        System.out.println("\n--- UPDATE HOTEL ---");
        System.out.print("Enter hotel ID: ");
        String id = scanner.nextLine();

        Hotel hotel = hotelController.getHotelById(id);
        if (hotel == null) {
            System.out.println("Hotel not found.");
            return;
        }

        System.out.println("Current data - Name: " + hotel.getNombre() + " | Rooms: " + hotel.getTotalHabitaciones());
        System.out.print("Enter new name (press Enter to keep current): ");
        String name = scanner.nextLine();

        System.out.print("Enter new address (press Enter to keep current): ");
        String direccion = scanner.nextLine();

        System.out.print("Enter new contact (press Enter to keep current): ");
        String contact = scanner.nextLine();

        System.out.print("Enter new total rooms (press Enter to keep current): ");
        String roomsInput = scanner.nextLine();
        Integer totalRooms = roomsInput.isEmpty() ? null : Integer.parseInt(roomsInput);

        boolean success = hotelController.updateHotel(id,
                name.isEmpty() ? null : name,
                direccion.isEmpty() ? null : direccion,
                contact.isEmpty() ? null : contact,
                totalRooms);

        if (success) {
            System.out.println("Hotel updated successfully.");
        } else {
            System.out.println("Failed to update hotel.");
        }
    }

    private void deleteHotel() {
        System.out.println("\n--- DELETE HOTEL ---");
        System.out.print("Enter hotel ID: ");
        String id = scanner.nextLine();

        boolean success = hotelController.deleteHotel(id);
        if (success) {
            System.out.println("Hotel deleted successfully.");
        } else {
            System.out.println("Failed to delete hotel. Hotel may not exist.");
        }
    }

    private void getHotelsWithMixedTransport() {
        System.out.println("\n--- HOTELS WITH MIXED TRANSPORT RESERVATIONS ---");
        int count = hotelController.getCantidadHotelConHabitacionesReservadasViajesMixtos();
        System.out.println("Number of hotels with reservations in mixed transport trips: " + count);
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
