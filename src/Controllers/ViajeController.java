package Controllers;

import DataAccess.ActividadTuristicaRepository;
import DataAccess.AeronaveRepository;
import DataAccess.CarroRepository;
import DataAccess.ClienteViajeRepository;
import DataAccess.HabitacionItinerarioRepository;
import DataAccess.HabitacionRepository;
import DataAccess.HotelRepository;
import DataAccess.ItinerarioTransporteRepository;
import DataAccess.PlanActividadRepository;
import DataAccess.PlanRepository;
import DataAccess.ServicioTransporteRepository;
import DataAccess.ViajePlanRepository;
import DataAccess.ViajeRepository;
import Models.ActividadTuristica;
import Models.Habitacion;
import Models.HabitacionItinerario;
import Models.ItinerarioTransporte;
import Models.Plan;
import Models.ServicioTransporte;
import Models.Viaje;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViajeController {
    private ViajeRepository viajeData;
    private ViajePlanRepository viajePlanData;
    private PlanRepository planData;
    private PlanActividadRepository planActividadData;
    private ActividadTuristicaRepository actividadData;
    private ItinerarioTransporteRepository itinerarioData;
    private ServicioTransporteRepository servicioData;
    private AeronaveRepository aeronaveData;
    private CarroRepository carroData;
    private ClienteViajeRepository clienteViajeData;
    private HabitacionRepository habitacionData;
    private HabitacionItinerarioRepository habitacionItinerarioData;
    private HotelRepository hotelData;

    public ViajeController() {
        this.viajeData = new ViajeRepository();
        this.viajePlanData = new ViajePlanRepository();
        this.planData = new PlanRepository();
        this.planActividadData = new PlanActividadRepository();
        this.actividadData = new ActividadTuristicaRepository();
        this.itinerarioData = new ItinerarioTransporteRepository();
        this.servicioData = new ServicioTransporteRepository();
        this.aeronaveData = new AeronaveRepository();
        this.carroData = new CarroRepository();
        this.clienteViajeData = new ClienteViajeRepository();
        this.habitacionData = new HabitacionRepository();
        this.habitacionItinerarioData = new HabitacionItinerarioRepository();
        this.hotelData = new HotelRepository();
    }

    public ViajeController(ViajeRepository viajeData) { this.viajeData = viajeData; }

    public List<Viaje> getAllViajes() { return viajeData.getAllViajes(); }
    public Viaje getViajeById(String id) { return viajeData.findViajeById(id); }

    public boolean addViaje(String fechaInicio, String fechaFin, double costoTotal) {
        if (fechaInicio == null || fechaInicio.trim().isEmpty()) return false;
        if (fechaFin == null || fechaFin.trim().isEmpty()) return false;
        if (costoTotal < 0) return false;

        Viaje v = new Viaje();
        v.setFechaInicio(fechaInicio.trim());
        v.setFechaFin(fechaFin.trim());
        v.setCostoTotal(costoTotal);

        viajeData.saveViaje(v);
        return true;
    }

    public boolean updateViaje(String id, String fechaInicio, String fechaFin, Double costoTotal) {
        Viaje v = viajeData.findViajeById(id);
        if (v == null) return false;

        if (fechaInicio != null && !fechaInicio.trim().isEmpty()) v.setFechaInicio(fechaInicio.trim());
        if (fechaFin != null && !fechaFin.trim().isEmpty()) v.setFechaFin(fechaFin.trim());
        if (costoTotal != null && costoTotal >= 0) v.setCostoTotal(costoTotal);

        viajeData.saveViaje(v);
        return true;
    }

    public boolean deleteViaje(String id) {
        viajeData.deleteViaje(id);
        return true;
    }

    // ==================== MÉTODOS ANALÍTICOS ====================
    
    // A. Promedio de actividades por plan para viajes con trayectos aéreos y terrestres
    public double getPromedioActividadesPorPlanAereoTerrestre() {
        List<Viaje> viajes = viajeData.getAllViajes();
        double totalActividades = 0;
        int totalPlanes = 0;

        for (Viaje viaje : viajes) {
            if (tieneViajesTrayectosAereosYTerrestres(viaje.getId())) {
                List<Plan> planes = viajePlanData.findPlanesByViajeId(viaje.getId());
                for (Plan plan : planes) {
                    List<ActividadTuristica> actividades = planActividadData.findActividadesByPlanId(plan.getId());
                    totalActividades += actividades.size();
                    totalPlanes++;
                }
            }
        }

        return totalPlanes == 0 ? 0.0 : totalActividades / (double) totalPlanes;
    }

    // D. Suma total de costos de vuelos para un cliente
    public double getSumaCostosTrayectosVueloClienteEspecifico(String clienteId) {
        if (clienteId == null || clienteId.trim().isEmpty()) return -1;

        double sumaTotal = 0;
        List<Viaje> clienteViajes = clienteViajeData.findViajesByClienteId(clienteId);

        for (Viaje viaje : clienteViajes) {
            List<ItinerarioTransporte> itinerarios = itinerarioData.getAllItinerarioTransporte();
            for (ItinerarioTransporte it : itinerarios) {
                if (it.getViajeId().equals(viaje.getId())) {
                    ServicioTransporte servicio = servicioData.findServicioTransporteById(it.getServicioTransporteId());
                    if (servicio != null) {
                        if (aeronaveData.findAeronaveById(servicio.getIdVehiculo()) != null) {
                            sumaTotal += servicio.getCosto();
                        }
                    }
                }
            }
        }

        return sumaTotal;
    }

    // F. Promedio de trayectos por viaje para clientes con más de un viaje
    public double getPromedioTrayectosPorViajeClientesMultiples() {
        List<Viaje> todosViajes = viajeData.getAllViajes();
        Map<String, Integer> clienteViajeCount = new HashMap<>();

        // Contar viajes por cliente
        for (Viaje viaje : todosViajes) {
            // Simulamos obtener clientes del viaje
            for (int i = 0; i < clienteViajeData.getAllClienteViaje().size(); i++) {
                if (clienteViajeData.getAllClienteViaje().get(i).getIdViaje().equals(viaje.getId())) {
                    String clienteId = clienteViajeData.getAllClienteViaje().get(i).getIdCliente();
                    clienteViajeCount.put(clienteId, clienteViajeCount.getOrDefault(clienteId, 0) + 1);
                }
            }
        }

        // Filtrar clientes con más de un viaje
        double totalTrayectos = 0;
        int totalViajes = 0;

        for (String clienteId : clienteViajeCount.keySet()) {
            if (clienteViajeCount.get(clienteId) > 1) {
                List<Viaje> viajesCliente = clienteViajeData.findViajesByClienteId(clienteId);
                for (Viaje viaje : viajesCliente) {
                    List<ItinerarioTransporte> itinerarios = itinerarioData.getAllItinerarioTransporte();
                    for (ItinerarioTransporte it : itinerarios) {
                        if (it.getViajeId().equals(viaje.getId())) {
                            totalTrayectos++;
                        }
                    }
                    totalViajes++;
                }
            }
        }

        return totalViajes == 0 ? 0.0 : totalTrayectos / (double) totalViajes;
    }

    // G. Máximo número de actividades en planes con trayectos terrestres
    public int getMaximoActividadesPlanViajesTerrestre() {
        List<Viaje> viajes = viajeData.getAllViajes();
        int maximoActividades = 0;

        for (Viaje viaje : viajes) {
            if (tieneViajesTrayectoTerrestre(viaje.getId())) {
                List<Plan> planes = viajePlanData.findPlanesByViajeId(viaje.getId());
                for (Plan plan : planes) {
                    List<ActividadTuristica> actividades = planActividadData.findActividadesByPlanId(plan.getId());
                    if (actividades.size() > maximoActividades) {
                        maximoActividades = actividades.size();
                    }
                }
            }
        }

        return maximoActividades;
    }

    // I. Suma total de costos de servicios de transporte para un viaje
    public double getSumaCostosServiciosTransporteViaje(String viajeId) {
        if (viajeId == null || viajeId.trim().isEmpty()) return -1;

        Viaje viaje = viajeData.findViajeById(viajeId);
        if (viaje == null) return -1;

        double sumaTotal = 0;
        List<ItinerarioTransporte> itinerarios = itinerarioData.getAllItinerarioTransporte();

        for (ItinerarioTransporte it : itinerarios) {
            if (it.getViajeId().equals(viajeId)) {
                ServicioTransporte servicio = servicioData.findServicioTransporteById(it.getServicioTransporteId());
                if (servicio != null) {
                    sumaTotal += servicio.getCosto();
                }
            }
        }

        return sumaTotal;
    }

    // K. Promedio de habitaciones por hotel en viajes aéreo-terrestres
    public double getPromedioHabitacionesReservadasPorHotel() {
        List<Viaje> viajes = viajeData.getAllViajes();
        Map<String, Integer> hotelHabitacionesCount = new HashMap<>();

        for (Viaje viaje : viajes) {
            if (tieneViajesTrayectosAereosYTerrestres(viaje.getId())) {
                List<ItinerarioTransporte> itinerarios = itinerarioData.getAllItinerarioTransporte();
                for (ItinerarioTransporte it : itinerarios) {
                    if (it.getViajeId().equals(viaje.getId())) {
                        List<HabitacionItinerario> habitacionesIt = habitacionItinerarioData.getAllHabitacionItinerario();
                        for (HabitacionItinerario hi : habitacionesIt) {
                            if (hi.getIdItinerario().equals(it.getId())) {
                                Habitacion hab = habitacionData.findHabitacionById(hi.getIdHabitacion());
                                if (hab != null) {
                                    hotelHabitacionesCount.put(hab.getHotelId(),
                                            hotelHabitacionesCount.getOrDefault(hab.getHotelId(), 0) + 1);
                                }
                            }
                        }
                    }
                }
            }
        }

        if (hotelHabitacionesCount.isEmpty()) return 0.0;

        double promedio = 0;
        for (Integer count : hotelHabitacionesCount.values()) {
            promedio += count;
        }

        return promedio / (double) hotelHabitacionesCount.size();
    }

    // ==================== MÉTODOS AUXILIARES ====================

    private boolean tieneViajesTrayectosAereosYTerrestres(String viajeId) {
        boolean tieneAereo = false;
        boolean tieneTerrestre = false;

        List<ItinerarioTransporte> itinerarios = itinerarioData.getAllItinerarioTransporte();
        for (ItinerarioTransporte it : itinerarios) {
            if (it.getViajeId().equals(viajeId)) {
                ServicioTransporte servicio = servicioData.findServicioTransporteById(it.getServicioTransporteId());
                if (servicio != null) {
                    if (aeronaveData.findAeronaveById(servicio.getIdVehiculo()) != null) {
                        tieneAereo = true;
                    } else {
                        if (carroData.findCarroById(servicio.getIdVehiculo()) != null) {
                            tieneTerrestre = true;
                        }
                    }
                }
            }
        }

        return tieneAereo && tieneTerrestre;
    }

    private boolean tieneViajesTrayectoTerrestre(String viajeId) {
        List<ItinerarioTransporte> itinerarios = itinerarioData.getAllItinerarioTransporte();
        for (ItinerarioTransporte it : itinerarios) {
            if (it.getViajeId().equals(viajeId)) {
                ServicioTransporte servicio = servicioData.findServicioTransporteById(it.getServicioTransporteId());
                if (servicio != null) {
                    if (aeronaveData.findAeronaveById(servicio.getIdVehiculo()) == null) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
