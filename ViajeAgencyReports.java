// ============================================================================
// EJEMPLOS DE USO - AnalyticsController
// ============================================================================

// 1. INICIALIZACIÓN
// ============================================================================

// Opción A: Constructor por defecto
AnalyticsController analytics = new AnalyticsController();

// Opción B: Constructor parametrizado (con testing/mocking)
ViajeRepository viajeRepo = new ViajeRepository();
ClienteRepository clienteRepo = new ClienteRepository();
PlanRepository planRepo = new PlanRepository();
// ... (resto de repositorios)

AnalyticsController analyticsMocked = new AnalyticsController(
    viajeRepo, clienteRepo, planRepo, viajeplanRepo, planActividadRepo,
    clienteViajeRepo, itinerarioRepo, servicioRepo, hotelRepo,
    habitacionRepo, habitacionItinerarioRepo, carroRepo, aeronaveRepo
);


// ============================================================================
// MÉTODO A: Promedio de actividades en viajes aéreo-terrestres
// ============================================================================

// Caso: Analizar la complejidad promedio de planes en viajes combinados
double promedioActividades = analytics.getPromedioActividadesPorPlanConTrayectosAereosYTerrestres();

if (promedioActividades > 0) {
    System.out.println("Promedio de actividades: " + promedioActividades);
    // Output ej: "Promedio de actividades: 3.5"
} else {
    System.out.println("No hay viajes con trayectos aéreos y terrestres");
}


// ============================================================================
// MÉTODO B: Mínimo costo de trayectos aéreos por aerolínea
// ============================================================================

// Caso: Obtener la tarifa más barata de una aerolínea específica
String aerolineaId = "AEROLINEA_LATAM_001";
double minCostoAerolinea = analytics.getMinimosCostoTrayectoAereoParaAerolinea(aerolineaId);

if (minCostoAerolinea > 0) {
    System.out.println("Tarifa mínima de " + aerolineaId + ": $" + minCostoAerolinea);
    // Output ej: "Tarifa mínima de AEROLINEA_LATAM_001: $450000.0"
} else {
    System.out.println("Aerolínea sin servicios registrados");
}


// ============================================================================
// MÉTODO C: Viajes con actividad específica usando vehículos hotel pequeño
// ============================================================================

// Caso: Contar viajes que incluyeron "Senderismo" usando carros del hotel
//       con menos capacidad
String nombreActividad = "Senderismo";
int cantidadViajesSenderismo = analytics.getCantidadViajesConActividadYVehiculoHotelMenosHabitaciones(
    nombreActividad
);

System.out.println("Viajes con " + nombreActividad + " usando carros del hotel más pequeño: " + 
    cantidadViajesSenderismo);
// Output ej: "Viajes con Senderismo usando carros del hotel más pequeño: 3"


// ============================================================================
// MÉTODO D: Suma de costos de vuelos para un cliente
// ============================================================================

// Caso: Facturación de un cliente específico solo por vuelos
String clienteId = "CLIENTE_001";
double sumaVuelos = analytics.getSumaCostosTrayectosVueloClienteEspecifico(clienteId);

if (sumaVuelos >= 0) {
    System.out.println("Costo total de vuelos del cliente " + clienteId + ": $" + sumaVuelos);
    // Output ej: "Costo total de vuelos del cliente CLIENTE_001: $1500000.0"
} else {
    System.out.println("Cliente no encontrado");
}


// ============================================================================
// MÉTODO E: Hoteles con ocupación en viajes de planes complejos
// ============================================================================

// Caso: Identificar hoteles que tuvieron huéspedes en viajes con planes
//       de 3 o más actividades
List<Hotel> hotelesComplejos = analytics.getHotelesConHabitacionesReservadasEnViajesConPlanesConAlMenos3Actividades();

System.out.println("Hoteles con planes complejos (3+ actividades): " + hotelesComplejos.size());
for (Hotel h : hotelesComplejos) {
    System.out.println("  - " + h.getNombre() + " (" + h.getId() + ")");
}
// Output ej:
// "Hoteles con planes complejos (3+ actividades): 2"
//   "- Hotel Boutique Bogotá (HOTEL_001)"
//   "- Resort Cartagena (HOTEL_003)"


// ============================================================================
// MÉTODO F: Promedio de trayectos por viaje (clientes frecuentes)
// ============================================================================

// Caso: Análisis de patrones de viaje de clientes que han viajado más de una vez
double promedioTrayectos = analytics.getPromedioTrayectosPorViajeClientesConMasDeUnViaje();

System.out.println("Promedio de trayectos en clientes frecuentes: " + promedioTrayectos);
// Output ej: "Promedio de trayectos en clientes frecuentes: 2.5"


// ============================================================================
// MÉTODO G: Máximo de actividades en planes terrestres
// ============================================================================

// Caso: Identificar el plan más completo (en actividades) para viajes 
//       que solo usan transporte terrestre
int maxActividades = analytics.getMaximoActividadesPlanViajesConTrayectoTerrestre();

System.out.println("Máximo de actividades en planes terrestres: " + maxActividades);
// Output ej: "Máximo de actividades en planes terrestres: 5"


// ============================================================================
// MÉTODO H: Clientes que usaron aerolínea AND visitaron municipio
// ============================================================================

// Caso: Marketing: ¿Cuántos clientes utilizaron Latam Y visitaron Cartagena?
String aerolinea = "AEROLINEA_LATAM_001";
String municipio = "MUNICIPIO_CARTAGENA_001";
int clientesAerolineaMunicipio = analytics.getConteoClientesAerolineaMunicipio(aerolinea, municipio);

System.out.println("Clientes que usaron " + aerolinea + " y visitaron " + municipio + ": " + 
    clientesAerolineaMunicipio);
// Output ej: "Clientes que usaron AEROLINEA_LATAM_001 y visitaron MUNICIPIO_CARTAGENA_001: 12"


// ============================================================================
// MÉTODO I: Suma de costos de transporte en un viaje
// ============================================================================

// Caso: Desglose de costos: ¿cuánto invirtió el cliente en transporte en este viaje?
String viajeId = "VIAJE_001";
double costoTransporte = analytics.getSumaCostosServiciosTransporteViaje(viajeId);

if (costoTransporte > 0) {
    System.out.println("Costo total de transporte del viaje " + viajeId + ": $" + costoTransporte);
    // Output ej: "Costo total de transporte del viaje VIAJE_001: $2500000.0"
} else {
    System.out.println("Viaje sin servicios de transporte");
}


// ============================================================================
// MÉTODO J: Planes populares entre clientes frecuentes
// ============================================================================

// Caso: Marketing: Qué planes incluyen "Buceo" y son populares entre 
//       clientes que viajan frecuentemente?
String actividad = "Buceo";
List<Plan> planesPopulares = analytics.getPlanesConActividadEspecificaClientesMultiplesViajes(actividad);

System.out.println("Planes con '" + actividad + "' para clientes frecuentes: " + planesPopulares.size());
for (Plan p : planesPopulares) {
    System.out.println("  - " + p.getNombre() + " (ID: " + p.getId() + ", $" + p.getCostoBase() + ")");
}
// Output ej:
// "Planes con 'Buceo' para clientes frecuentes: 2"
//   "- Plan Caribe Total (ID: PLAN_001, $5000000.0)"
//   "- Plan Islas Rosario (ID: PLAN_003, $4500000.0)"


// ============================================================================
// MÉTODO K: Promedio de ocupación hotelera en viajes complejos
// ============================================================================

// Caso: Análisis operacional: En viajes que combinen aire y tierra,
//       ¿cuántas habitaciones se ocupan en promedio por hotel?
double promedioHabitaciones = analytics.getPromedioHabitacionesReservadasPorHotelTrayectosAereosYTerrestres();

System.out.println("Promedio de habitaciones ocupadas por hotel (viajes aéreo-terrestres): " + 
    promedioHabitaciones);
// Output ej: "Promedio de habitaciones ocupadas por hotel (viajes aéreo-terrestres): 4.5"


// ============================================================================
// EJEMPLO COMPLETO: REPORTERÍA
// ============================================================================

public class ViajeAgencyReports {
    private AnalyticsController analytics;
    
    public ViajeAgencyReports() {
        this.analytics = new AnalyticsController();
    }
    
    public void generarReporteGerencial() {
        System.out.println("=".repeat(60));
        System.out.println("REPORTE GERENCIAL - AGENCIA DE VIAJES");
        System.out.println("=".repeat(60));
        
        // Estadísticas de viajes
        System.out.println("\n1. ANÁLISIS DE VIAJES");
        System.out.println("-".repeat(60));
        
        double promedioActividades = analytics.getPromedioActividadesPorPlanConTrayectosAereosYTerrestres();
        System.out.println("Promedio actividades (viajes combinados): " + 
            String.format("%.2f", promedioActividades));
        
        int maxActividades = analytics.getMaximoActividadesPlanViajesConTrayectoTerrestre();
        System.out.println("Máximo actividades (planes terrestres): " + maxActividades);
        
        double promedioTrayectos = analytics.getPromedioTrayectosPorViajeClientesConMasDeUnViaje();
        System.out.println("Promedio trayectos (clientes frecuentes): " + 
            String.format("%.2f", promedioTrayectos));
        
        // Análisis de precios
        System.out.println("\n2. ANÁLISIS DE PRECIOS");
        System.out.println("-".repeat(60));
        
        String[] aerolineas = {"AEROLINEA_001", "AEROLINEA_002", "AEROLINEA_003"};
        for (String aerolineaId : aerolineas) {
            double minCosto = analytics.getMinimosCostoTrayectoAereoParaAerolinea(aerolineaId);
            if (minCosto > 0) {
                System.out.println("Tarifa mínima " + aerolineaId + ": $" + 
                    String.format("%.0f", minCosto));
            }
        }
        
        // Análisis de ocupación
        System.out.println("\n3. ANÁLISIS DE OCUPACIÓN");
        System.out.println("-".repeat(60));
        
        double promedioHabitaciones = analytics.getPromedioHabitacionesReservadasPorHotelTrayectosAereosYTerrestres();
        System.out.println("Habitaciones promedio por hotel (viajes combinados): " + 
            String.format("%.2f", promedioHabitaciones));
        
        List<Hotel> hotelesActivos = analytics.getHotelesConHabitacionesReservadasEnViajesConPlanesConAlMenos3Actividades();
        System.out.println("Hoteles con planes complejos: " + hotelesActivos.size());
        
        System.out.println("\n" + "=".repeat(60));
    }
}

// Uso:
ViajeAgencyReports reports = new ViajeAgencyReports();
reports.generarReporteGerencial();

// Output esperado:
// ============================================================
// REPORTE GERENCIAL - AGENCIA DE VIAJES
// ============================================================
// 
// 1. ANÁLISIS DE VIAJES
// ------------------------------------------------------------
// Promedio actividades (viajes combinados): 3.50
// Máximo actividades (planes terrestres): 5
// Promedio trayectos (clientes frecuentes): 2.50
// 
// 2. ANÁLISIS DE PRECIOS
// ------------------------------------------------------------
// Tarifa mínima AEROLINEA_001: $450000
// Tarifa mínima AEROLINEA_002: $520000
// Tarifa mínima AEROLINEA_003: $380000
// 
// 3. ANÁLISIS DE OCUPACIÓN
// ------------------------------------------------------------
// Habitaciones promedio por hotel (viajes combinados): 4.50
// Hoteles con planes complejos: 4
// 
// ============================================================


// ============================================================================
// NOTAS DE USO
// ============================================================================

/*
 * 1. MANEJO DE ERRORES
 *    - Los métodos retornan -1 para doble si hay error
 *    - Retornan 0 para int si no hay resultados
 *    - Retornan List vacía si no hay datos
 *    
 * 2. VALIDACIÓN DE PARÁMETROS
 *    - Todos los métodos validan parámetros nulos/vacíos
 *    - Regresar valores por defecto en caso de error
 *    
 * 3. PERFORMANCE
 *    - Para bases de datos grandes, los métodos pueden tomar tiempo
 *    - Se recomienda cachear resultados cuando sea posible
 *    - Para reportes, considerar ejecutar en threads separados
 *    
 * 4. BASES DE DATOS
 *    - Los métodos leen directamente de repositorios
 *    - No realizan modificaciones de datos
 *    - Seguros para lectura concurrente
 *
 * 5. TESTING
 *    - Usar constructor parametrizado con mocks
 *    - Inyectar datos de prueba a través de repositorios
 *    - Validar retornos esperados
 */
