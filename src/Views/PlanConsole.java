package Views;

import Controllers.PlanController;
import Models.Plan;
import java.util.List;
import java.util.Scanner;

public class PlanConsole {
    private PlanController planController;
    private Scanner scanner;

    public PlanConsole() {
        this.planController = new PlanController();
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== PLAN MANAGEMENT ===");
            System.out.println("1. List all plans");
            System.out.println("2. Add new plan");
            System.out.println("3. Update plan");
            System.out.println("4. Delete plan");
            System.out.println("5. Back to main menu");
            System.out.print("Select an option: ");

            int choice = readIntInput();

            switch (choice) {
                case 1:
                    listAllPlans();
                    break;
                case 2:
                    addPlan();
                    break;
                case 3:
                    updatePlan();
                    break;
                case 4:
                    deletePlan();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void listAllPlans() {
        System.out.println("\n--- ALL PLANS ---");
        List<Plan> plans = planController.getAllPlanes();
        if (plans.isEmpty()) {
            System.out.println("No plans found.");
        } else {
            for (Plan plan : plans) {
                System.out.println("ID: " + plan.getId() + " | Name: " + plan.getNombre() + 
                                 " | Preset: " + plan.isPreestablecido() + " | Base Cost: $" + plan.getCostoBase());
            }
        }
    }

    private void addPlan() {
        System.out.println("\n--- ADD NEW PLAN ---");
        System.out.print("Enter plan name: ");
        String nombre = scanner.nextLine();

        System.out.print("Enter description: ");
        String descripcion = scanner.nextLine();

        System.out.print("Is this a preset plan? (true/false): ");
        boolean preestablecido = readBooleanInput();

        System.out.print("Enter base cost: ");
        double costoBase = readDoubleInput();

        boolean success = planController.addPlan(nombre, descripcion, preestablecido, costoBase);
        if (success) {
            System.out.println("Plan added successfully.");
        } else {
            System.out.println("Failed to add plan. Please check the input data.");
        }
    }

    private void updatePlan() {
        System.out.println("\n--- UPDATE PLAN ---");
        System.out.print("Enter plan ID: ");
        String id = scanner.nextLine();

        Plan plan = planController.getPlanById(id);
        if (plan == null) {
            System.out.println("Plan not found.");
            return;
        }

        System.out.println("Current data - Name: " + plan.getNombre() + " | Base Cost: $" + plan.getCostoBase());
        System.out.print("Enter new name (press Enter to keep current): ");
        String nombre = scanner.nextLine();

        System.out.print("Enter new description (press Enter to keep current): ");
        String descripcion = scanner.nextLine();

        System.out.print("Is this a preset plan? (press Enter to keep current): ");
        String preestablecidoInput = scanner.nextLine();
        Boolean preestablecido = preestablecidoInput.isEmpty() ? null : Boolean.parseBoolean(preestablecidoInput);

        System.out.print("Enter new base cost (press Enter to keep current): ");
        String costoInput = scanner.nextLine();
        Double costoBase = costoInput.isEmpty() ? null : Double.parseDouble(costoInput);

        boolean success = planController.updatePlan(id,
                nombre.isEmpty() ? null : nombre,
                descripcion.isEmpty() ? null : descripcion,
                preestablecido,
                costoBase);

        if (success) {
            System.out.println("Plan updated successfully.");
        } else {
            System.out.println("Failed to update plan.");
        }
    }

    private void deletePlan() {
        System.out.println("\n--- DELETE PLAN ---");
        System.out.print("Enter plan ID: ");
        String id = scanner.nextLine();

        boolean success = planController.deletePlan(id);
        if (success) {
            System.out.println("Plan deleted successfully.");
        } else {
            System.out.println("Failed to delete plan. Plan may not exist.");
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

    private double readDoubleInput() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    private boolean readBooleanInput() {
        while (true) {
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("true") || input.equals("t")) return true;
            if (input.equals("false") || input.equals("f")) return false;
            System.out.print("Invalid input. Please enter true or false: ");
        }
    }
}
