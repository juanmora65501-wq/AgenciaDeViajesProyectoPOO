#!/usr/bin/env python3
import json
import os

# Definición de controladores con métodos correctos
controllers = {
    "ActividadGuiaController": {
        "getAll": "getAllActividadGuia()",
        "getById": "getActividadGuiaById(String)",
        "add": "addActividadGuia(String, String)",
        "update": "updateActividadGuia(String, String, String)",
        "delete": "deleteActividadGuia(String)",
        "model": "ActividadGuia",
        "addParams": ["guiaId", "actividadId"],
        "displayFields": ["guiaId", "actividadId"]
    },
    "ClienteViajeController": {
        "getAll": "getAllClienteViaje()",
        "getById": "getClienteViajeById(String)",
        "add": "addClienteViaje(String, String)",
        "update": "updateClienteViaje(String, String, String)",
        "delete": "deleteClienteViaje(String)",
        "model": "ClienteViaje",
        "addParams": ["clienteId", "viajeId"],
        "displayFields": ["clienteId", "viajeId"]
    },
    "HabitacionItinerarioController": {
        "getAll": "getAllHabitacionItinerario()",
        "getById": "getHabitacionItinerarioById(String)",
        "add": "addHabitacionItinerario(String, String)",
        "update": "updateHabitacionItinerario(String, String, String)",
        "delete": "deleteHabitacionItinerario(String)",
        "model": "HabitacionItinerario",
        "addParams": ["habitacionId", "itinerarioId"],
        "displayFields": ["habitacionId", "itinerarioId"]
    },
    "PlanActividadController": {
        "getAll": "getAllPlanActividad()",
        "getById": "getPlanActividadById(String)",
        "add": "addPlanActividad(String, String)",
        "update": "updatePlanActividad(String, String, String)",
        "delete": "deletePlanActividad(String)",
        "model": "PlanActividad",
        "addParams": ["planId", "actividadId"],
        "displayFields": ["planId", "actividadId"]
    },
    "ViajePlanController": {
        "getAll": "getAllViajePlan()",
        "getById": "getViajePlanById(String)",
        "add": "addViajePlan(String, String)",
        "update": "updateViajePlan(String, String, String)",
        "delete": "deleteViajePlan(String)",
        "model": "ViajePlan",
        "addParams": ["viajeId", "planId"],
        "displayFields": ["viajeId", "planId"]
    }
}

# Crear template para consola simple (2 string params)
template_simple = '''package Views;

import Controllers.{Controller};
import Models.{Model};
import java.util.List;
import java.util.Scanner;

public class {ConsoleClass} {{
    private {Controller} controller;
    private Scanner scanner;

    public {ConsoleClass}() {{
        this.controller = new {Controller}();
        this.scanner = new Scanner(System.in);
    }}

    public void showMenu() {{
        while (true) {{
            System.out.println("\\n=== {Title} ===");
            System.out.println("1. List all");
            System.out.println("2. Add new");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("5. Back to main menu");
            System.out.print("Select an option: ");

            int choice = readIntInput();
            switch (choice) {{
                case 1: listAll(); break;
                case 2: add(); break;
                case 3: update(); break;
                case 4: delete(); break;
                case 5: return;
                default: System.out.println("Invalid option.");
            }}
        }}
    }}

    private void listAll() {{
        System.out.println("\\n--- ALL ITEMS ---");
        List<{Model}> items = controller.{getAllMethod};
        if (items.isEmpty()) {{
            System.out.println("No items found.");
        }} else {{
            for ({Model} item : items) {{
                System.out.println("ID: " + item.getId());
            }}
        }}
    }}

    private void add() {{
        System.out.println("\\n--- ADD NEW ITEM ---");
        System.out.print("Enter {param1}: ");
        String {paramVar1} = scanner.nextLine();
        System.out.print("Enter {param2}: ");
        String {paramVar2} = scanner.nextLine();
        boolean success = controller.{addMethod}({paramVar1}, {paramVar2});
        System.out.println(success ? "Item added successfully." : "Failed to add item.");
    }}

    private void update() {{
        System.out.println("\\n--- UPDATE ITEM ---");
        System.out.print("Enter item ID: ");
        String id = scanner.nextLine();
        {Model} item = controller.{getByIdMethod};
        if (item == null) {{
            System.out.println("Item not found.");
            return;
        }}
        System.out.print("Enter new {param1}: ");
        String {paramVar1} = scanner.nextLine();
        System.out.print("Enter new {param2}: ");
        String {paramVar2} = scanner.nextLine();
        boolean success = controller.{updateMethod}(id, {paramVar1}, {paramVar2});
        System.out.println(success ? "Item updated successfully." : "Failed to update item.");
    }}

    private void delete() {{
        System.out.println("\\n--- DELETE ITEM ---");
        System.out.print("Enter item ID: ");
        String id = scanner.nextLine();
        boolean success = controller.{deleteMethod};
        System.out.println(success ? "Item deleted successfully." : "Failed to delete item.");
    }}

    private int readIntInput() {{
        while (true) {{
            try {{
                return Integer.parseInt(scanner.nextLine());
            }} catch (NumberFormatException e) {{
                System.out.print("Invalid input: ");
            }}
        }}
    }}
}}
'''

# Generar archivos
base_path = "/c/Users/juank/OneDrive/Desktop/Proyectos\\ U/TravelAgencyProject/src/Views"

for controller_name, config in controllers.items():
    console_name = controller_name.replace("Controller", "Console")
    model_name = config["model"]
    
    # Extraer nombres de métodos
    get_all_method = config["getAll"]
    get_by_id_method = config["getById"]
    add_method = config["add"].split("(")[0]
    update_method = config["update"].split("(")[0]
    delete_method = config["delete"]
    
    # Parámetros
    params = config["addParams"]
    param1 = params[0]
    param2 = params[1] if len(params) > 1 else params[0]
    
    # Generar nombre de archivo
    filename = f"{console_name}.java"
    filepath = f"{base_path}/{filename}"
    
    # Reemplazar placeholders en template
    content = template_simple.format(
        Controller=controller_name,
        ConsoleClass=console_name,
        Model=model_name,
        Title=model_name.upper() + " MANAGEMENT",
        getAllMethod=get_all_method,
        getByIdMethod=f"{get_by_id_method.replace('(String)', f'(id)')}",
        addMethod=add_method,
        updateMethod=update_method,
        deleteMethod=delete_method,
        param1=param1,
        param2=param2,
        paramVar1=param1.lower(),
        paramVar2=param2.lower()
    )
    
    # Escribir archivo
    print(f"Generated {console_name}")

print("Done!")
