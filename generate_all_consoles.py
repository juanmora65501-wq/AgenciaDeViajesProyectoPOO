#!/usr/bin/env python3
"""Generate all Console classes with correct method signatures"""

import json
import os

# Controller signatures extracted from analysis
controllers_config = {
    "ActividadTuristicaController": {
        "model": "ActividadTuristica",
        "getAll": "getAllActividades()",
        "getById": "getActividadById(String id)",
        "add": ("addActividad", ["nombre", "descripcion", "municipioId", "duracion", "costo"]),
        "addTypes": ["String", "String", "String", "String", "double"],
        "update": ("updateActividad", ["nombre", "descripcion", "duracion", "costo"]),
        "updateTypes": ["String", "String", "String", "Double"],
        "delete": "deleteActividad(String id)",
    },
    "AeronaveController": {
        "model": "Aeronave",
        "getAll": "getAllAeronaves()",
        "getById": "getAeronaveById(String id)",
        "add": ("addAeronave", ["aerolineaId", "matricula", "autonomia", "tipoAeronave", "tipo", "marca", "modelo", "capacidad", "placa"]),
        "addTypes": ["String", "String", "double", "String", "String", "String", "String", "int", "String"],
        "update": ("updateAeronave", ["aerolineaId", "matricula", "autonomia", "tipoAeronave", "tipo", "marca", "modelo", "capacidad", "placa"]),
        "updateTypes": ["String", "String", "Double", "String", "String", "String", "String", "Integer", "String"],
        "delete": "deleteAeronave(String id)",
    },
    "CarroController": {
        "model": "Carro",
        "getAll": "getAllCarros()",
        "getById": "getCarroById(String id)",
        "add": ("addCarro", ["hotelId", "marca", "modelo", "placa"]),
        "addTypes": ["String", "String", "String", "String"],
        "update": ("updateCarro", ["marca", "modelo", "placa"]),
        "updateTypes": ["String", "String", "String"],
        "delete": "deleteCarro(String id)",
    },
    "CuotaController": {
        "model": "Cuota",
        "getAll": "getAllCuotas()",
        "getById": "getCuotaById(String id)",
        "add": ("addCuota", ["viajeId", "monto", "numeroCuota", "fechaVencimiento"]),
        "addTypes": ["String", "double", "int", "String"],
        "update": ("updateCuota", ["monto", "numeroCuota", "fechaVencimiento"]),
        "updateTypes": ["Double", "Integer", "String"],
        "delete": "deleteCuota(String id)",
    },
    "HabitacionController": {
        "model": "Habitacion",
        "getAll": "getAllHabitaciones()",
        "getById": "getHabitacionById(String id)",
        "add": ("addHabitacion", ["hotelId", "tipo", "precio", "capacidad"]),
        "addTypes": ["String", "String", "double", "int"],
        "update": ("updateHabitacion", ["tipo", "precio", "capacidad"]),
        "updateTypes": ["String", "Double", "Integer"],
        "delete": "deleteHabitacion(String id)",
    },
    "ItinerarioTransporteController": {
        "model": "ItinerarioTransporte",
        "getAll": "getAllItinerarios()",
        "getById": "getItinerarioById(String id)",
        "add": ("addItinerario", ["viajeId", "trayectoId", "servicioId", "orden"]),
        "addTypes": ["String", "String", "String", "int"],
        "update": ("updateItinerario", ["viajeId", "trayectoId", "servicioId", "orden"]),
        "updateTypes": ["String", "String", "String", "Integer"],
        "delete": "deleteItinerario(String id)",
    },
    "MunicipioController": {
        "model": "Municipio",
        "getAll": "getAllMunicipios()",
        "getById": "getMunicipioById(String id)",
        "add": ("addMunicipio", ["nombre", "departamento"]),
        "addTypes": ["String", "String"],
        "update": ("updateMunicipio", ["nombre", "departamento"]),
        "updateTypes": ["String", "String"],
        "delete": "deleteMunicipio(String id)",
    },
    "ServicioTransporteController": {
        "model": "ServicioTransporte",
        "getAll": "getAllServicios()",
        "getById": "getServicioById(String id)",
        "add": ("addServicio", ["trayectoId", "vehiculoId", "fechaInicio", "fechaFin", "costo"]),
        "addTypes": ["String", "String", "String", "String", "double"],
        "update": ("updateServicio", ["trayectoId", "vehiculoId", "fechaInicio", "fechaFin", "costo"]),
        "updateTypes": ["String", "String", "String", "String", "Double"],
        "delete": "deleteServicio(String id)",
    },
    "TrayectoController": {
        "model": "Trayecto",
        "getAll": "getAllTrayectos()",
        "getById": "getTrayectoById(String id)",
        "add": ("addTrayecto", ["origenId", "destinoId"]),
        "addTypes": ["String", "String"],
        "update": ("updateTrayecto", ["origenId", "destinoId"]),
        "updateTypes": ["String", "String"],
        "delete": "deleteTrayecto(String id)",
    },
    "ClienteViajeController": {
        "model": "ClienteViaje",
        "getAll": "getAllClienteViaje()",
        "getById": "getClienteViajeById(String id)",
        "add": ("addClienteViaje", ["clienteId", "viajeId"]),
        "addTypes": ["String", "String"],
        "update": ("updateClienteViaje", ["clienteId", "viajeId"]),
        "updateTypes": ["String", "String"],
        "delete": "deleteClienteViaje(String id)",
    },
    "HabitacionItinerarioController": {
        "model": "HabitacionItinerario",
        "getAll": "getAllHabitacionItinerario()",
        "getById": "getHabitacionItinerarioById(String id)",
        "add": ("addHabitacionItinerario", ["habitacionId", "itinerarioId"]),
        "addTypes": ["String", "String"],
        "update": ("updateHabitacionItinerario", ["habitacionId", "itinerarioId"]),
        "updateTypes": ["String", "String"],
        "delete": "deleteHabitacionItinerario(String id)",
    },
    "PlanActividadController": {
        "model": "PlanActividad",
        "getAll": "getAllPlanActividad()",
        "getById": "getPlanActividadById(String id)",
        "add": ("addPlanActividad", ["planId", "actividadId"]),
        "addTypes": ["String", "String"],
        "update": ("updatePlanActividad", ["planId", "actividadId"]),
        "updateTypes": ["String", "String"],
        "delete": "deletePlanActividad(String id)",
    },
}

# Template for generating consoles
def generate_console(controller_name, config):
    """Generate a complete console class for the given controller"""
    
    model = config["model"]
    getall_method = config["getAll"]
    getbyid_method = config["getById"]
    add_method_name, add_params = config["add"]
    add_types = config["addTypes"]
    update_method_name, update_params = config["update"]
    update_types = config["updateTypes"]
    delete_method = config["delete"].split("(")[0]
    
    # Generate add method call
    add_call_params = ", ".join(add_params)
    
    # Generate update method call
    update_call_params = "id, " + ", ".join(update_params)
    
    # Title
    title = model.upper().replace("I", " I").strip()
    
    # Build input prompts and variables
    add_inputs = []
    for i, param in enumerate(add_params):
        param_type = add_types[i]
        if param_type in ["double", "int"]:
            input_line = f'        System.out.print("Enter {param}: ");\n        {param_type} {param} = read{"Double" if param_type == "double" else "Int"}Input();'
        else:
            input_line = f'        System.out.print("Enter {param}: ");\n        String {param} = scanner.nextLine();'
        add_inputs.append(input_line)
    
    add_input_section = "\n".join(add_inputs)
    
    # Build update inputs
    update_inputs = []
    for i, param in enumerate(update_params):
        param_type = update_types[i]
        if "Double" in param_type or "Integer" in param_type:
            type_str = "Double" if "Double" in param_type else "Integer"
            input_line = f'        System.out.print("Enter new {param} (Enter to skip): ");\n        String {param}Input = scanner.nextLine();\n        {type_str} {param} = {param}Input.isEmpty() ? null : new {type_str}({param}Input);'
        else:
            input_line = f'        System.out.print("Enter new {param} (Enter to skip): ");\n        String {param} = scanner.nextLine();'
        update_inputs.append(input_line)
    
    update_input_section = "\n".join(update_inputs)
    
    # Template
    template = f'''package Views;

import Controllers.{controller_name};
import Models.{model};
import java.util.List;
import java.util.Scanner;

public class {model}Console {{
    private {controller_name} controller;
    private Scanner scanner;

    public {model}Console() {{
        this.controller = new {controller_name}();
        this.scanner = new Scanner(System.in);
    }}

    public void showMenu() {{
        while (true) {{
            System.out.println("\\n=== {title} MANAGEMENT ===");
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
        List<{model}> items = controller.{getall_method};
        if (items.isEmpty()) {{
            System.out.println("No items found.");
        }} else {{
            for ({model} item : items) {{
                System.out.println("ID: " + item.getId());
            }}
        }}
    }}

    private void add() {{
        System.out.println("\\n--- ADD NEW ITEM ---");
{add_input_section}
        boolean success = controller.{add_method_name}({add_call_params});
        System.out.println(success ? "Item added successfully." : "Failed to add item.");
    }}

    private void update() {{
        System.out.println("\\n--- UPDATE ITEM ---");
        System.out.print("Enter item ID: ");
        String id = scanner.nextLine();
        {model} item = controller.{getbyid_method.replace("(String id)", "(id)")};
        if (item == null) {{
            System.out.println("Item not found.");
            return;
        }}
{update_input_section}
        boolean success = controller.{update_method_name}({update_call_params});
        System.out.println(success ? "Item updated successfully." : "Failed to update item.");
    }}

    private void delete() {{
        System.out.println("\\n--- DELETE ITEM ---");
        System.out.print("Enter item ID: ");
        String id = scanner.nextLine();
        boolean success = controller.{delete_method}(id);
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

    private double readDoubleInput() {{
        while (true) {{
            try {{
                return Double.parseDouble(scanner.nextLine());
            }} catch (NumberFormatException e) {{
                System.out.print("Invalid input: ");
            }}
        }}
    }}
}}
'''
    
    return template

# Generate all consoles
base_path = "c:\\Users\\juank\\OneDrive\\Desktop\\Proyectos U\\TravelAgencyProject\\src\\Views"

for controller_name, config in controllers_config.items():
    model = config["model"]
    console_name = f"{model}Console"
    console_code = generate_console(controller_name, config)
    
    filepath = os.path.join(base_path, f"{console_name}.java")
    
    with open(filepath, 'w') as f:
        f.write(console_code)
    
    print(f"Generated {console_name}.java")

print("\nAll consoles generated successfully!")
