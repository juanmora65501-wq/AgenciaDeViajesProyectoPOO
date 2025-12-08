package Controllers;

import DataAccess.*;
import Models.*;
import java.util.*;
import java.util.stream.Collectors;

public class AnalyticsController {
    private ViajeRepository viajeData;
    private ClienteRepository clienteData;
    private PlanRepository planData;
    private ViajePlanRepository viajePlanData;
    private PlanActividadRepository planActividadData;
    private ClienteViajeRepository clienteViajeData;
    private ItinerarioTransporteRepository itinerarioData;
    private ServicioTransporteRepository servicioData;
    private HotelRepository hotelData;
    private HabitacionRepository habitacionData;
    private HabitacionItinerarioRepository habitacionItinerarioData;
    private CarroRepository carroData;
    private AeronaveRepository aeronaveData;

    public AnalyticsController() {
        this.viajeData = new ViajeRepository();
        this.clienteData = new ClienteRepository();
        this.planData = new PlanRepository();
        this.viajePlanData = new ViajePlanRepository();
        this.planActividadData = new PlanActividadRepository();
        this.clienteViajeData = new ClienteViajeRepository();
        this.itinerarioData = new ItinerarioTransporteRepository();
        this.servicioData = new ServicioTransporteRepository();
        this.hotelData = new HotelRepository();
        this.habitacionData = new HabitacionRepository();
        this.habitacionItinerarioData = new HabitacionItinerarioRepository();
        this.carroData = new CarroRepository();
        this.aeronaveData = new AeronaveRepository();
    }

    public AnalyticsController(ViajeRepository viajeData, ClienteRepository clienteData, PlanRepository planData,
                               ViajePlanRepository viajePlanData, PlanActividadRepository planActividadData,
                               ClienteViajeRepository clienteViajeData, ItinerarioTransporteRepository itinerarioData,
                               ServicioTransporteRepository servicioData, HotelRepository hotelData,
                               HabitacionRepository habitacionData, HabitacionItinerarioRepository habitacionItinerarioData,
                               CarroRepository carroData, AeronaveRepository aeronaveData) {
        this.viajeData = viajeData;
        this.clienteData = clienteData;
        this.planData = planData;
        this.viajePlanData = viajePlanData;
        this.planActividadData = planActividadData;
        this.clienteViajeData = clienteViajeData;
        this.itinerarioData = itinerarioData;
        this.servicioData = servicioData;
        this.hotelData = hotelData;
        this.habitacionData = habitacionData;
        this.habitacionItinerarioData = habitacionItinerarioData;
        this.carroData = carroData;
        this.aeronaveData = aeronaveData;
    }

    // A. Promedio de actividades por plan para viajes que incluyen al menos un trayecto aéreo y uno terrestre
    public double getPromedioActividadesPorPlanConTrayectosAereosYTerrestres() {
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

    // B. Mínimo costo de un trayecto aéreo para una aerolínea específica
    public double getMinimosCostoTrayectoAereoParaAerolinea(String aerolineaId) {
        if (aerolineaId == null || aerolineaId.trim().isEmpty()) return -1;

        List<Aeronave> aeronaves = aeronaveData.getAllAeronaves();
        double minimosCostos = Double.MAX_VALUE;
        boolean encontrado = false;

        for (Aeronave aeronave : aeronaves) {
            if (aeronave.getAerolineaId() != null && aeronave.getAerolineaId().equals(aerolineaId)) {
                List<ServicioTransporte> servicios = servicioData.getAllServiciosTransporte();
                for (ServicioTransporte servicio : servicios) {
                    if (servicio.getIdVehiculo() != null && servicio.getIdVehiculo().equals(aeronave.getId())) {
                        if (servicio.getCosto() < minimosCostos) {
                            minimosCostos = servicio.getCosto();
                            encontrado = true;
                        }
                    }
                }
            }
        }

        return encontrado ? minimosCostos : -1;
    }

    // C. Cantidad de viajes que incluyen al menos un plan con una actividad específica y que hayan utilizado un vehículo del hotel con menos habitaciones
    public int getCantidadViajesConActividadYVehiculoHotelMenosHabitaciones(String nombreActividad) {
        if (nombreActividad == null || nombreActividad.trim().isEmpty()) return 0;

        List<Hotel> hoteles = hotelData.getAllHoteles();
        Hotel hotelMenosHabitaciones = hoteles.stream()
                .min(Comparator.comparingInt(Hotel::getTotalHabitaciones))
                .orElse(null);

        if (hotelMenosHabitaciones == null) return 0;

        List<Carro> carrosHotel = carroData.findCarrosByHotelId(hotelMenosHabitaciones.getId());
        int contador = 0;
        List<Viaje> viajes = viajeData.getAllViajes();

        for (Viaje viaje : viajes) {
            List<Plan> planes = viajePlanData.findPlanesByViajeId(viaje.getId());
            boolean tieneActividad = false;

            for (Plan plan : planes) {
                List<ActividadTuristica> actividades = planActividadData.findActividadesByPlanId(plan.getId());
                for (ActividadTuristica actividad : actividades) {
                    if (actividad.getNombre().equals(nombreActividad)) {
                        tieneActividad = true;
                        break;
                    }
                }
                if (tieneActividad) break;
            }

            if (tieneActividad) {
                List<ItinerarioTransporte> itinerarios = itinerarioData.getAllItinerarioTransporte();
                for (ItinerarioTransporte it : itinerarios) {
                    if (it.getViajeId().equals(viaje.getId())) {
                        ServicioTransporte servicio = servicioData.findServicioTransporteById(it.getServicioTransporteId());
                        if (servicio != null) {
                            for (Carro carro : carrosHotel) {
                                if (carro.getId().equals(servicio.getIdVehiculo())) {
                                    contador++;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        return contador;
    }

    // D. Suma total de costos de todos los trayectos de tipo vuelo para un cliente específico
    public double getSumaCostosTrayectosVueloClienteEspecifico(String clienteId) {
        if (clienteId == null || clienteId.trim().isEmpty()) return -1;

        Cliente cliente = clienteData.findClientesById(clienteId);
        if (cliente == null) return -1;

        double sumaTotal = 0;
        List<Viaje> clienteViajes = clienteViajeData.findViajesByClienteId(clienteId);

        for (Viaje viaje : clienteViajes) {
            List<ItinerarioTransporte> itinerarios = itinerarioData.getAllItinerarioTransporte();
            for (ItinerarioTransporte it : itinerarios) {
                if (it.getViajeId().equals(viaje.getId())) {
                    ServicioTransporte servicio = servicioData.findServicioTransporteById(it.getServicioTransporteId());
                    if (servicio != null) {
                        Aeronave aeronave = aeronaveData.findAeronaveById(servicio.getIdVehiculo());
                        if (aeronave != null) {
                            sumaTotal += servicio.getCosto();
                        }
                    }
                }
            }
        }

        return sumaTotal;
    }

    // E. Retornar en una lista todos los hoteles que tienen habitaciones reservadas en viajes que incluyen un plan con al menos 3 actividades
    public List<Hotel> getHotelesConHabitacionesReservadasEnViajesConPlanesConAlMenos3Actividades() {
        Set<String> hotelIds = new HashSet<>();
        List<Viaje> viajes = viajeData.getAllViajes();

        for (Viaje viaje : viajes) {
            List<Plan> planes = viajePlanData.findPlanesByViajeId(viaje.getId());

            for (Plan plan : planes) {
                List<ActividadTuristica> actividades = planActividadData.findActividadesByPlanId(plan.getId());
                if (actividades.size() >= 3) {
                    List<ItinerarioTransporte> itinerarios = itinerarioData.getAllItinerarioTransporte();
                    for (ItinerarioTransporte it : itinerarios) {
                        if (it.getViajeId().equals(viaje.getId())) {
                            List<HabitacionItinerario> habitacionesIt = habitacionItinerarioData.getAllHabitacionItinerario();
                            for (HabitacionItinerario hi : habitacionesIt) {
                                if (hi.getIdItinerario().equals(it.getId())) {
                                    Habitacion hab = habitacionData.findHabitacionById(hi.getIdHabitacion());
                                    if (hab != null) {
                                        hotelIds.add(hab.getHotelId());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return hotelIds.stream()
                .map(hotelData::findHotelById)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    // F. Promedio de trayectos por viaje para clientes que han realizado más de un viaje
    public double getPromedioTrayectosPorViajeClientesConMasDeUnViaje() {
        List<ClienteViaje> clienteViajes = clienteViajeData.getAllClienteViaje();
        Map<String, List<String>> clienteViajesMap = clienteViajes.stream()
                .collect(Collectors.groupingBy(
                        ClienteViaje::getIdCliente,
                        Collectors.mapping(ClienteViaje::getIdViaje, Collectors.toList())
                ));

        List<String> clientesConMultiplesViajes = clienteViajesMap.entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        if (clientesConMultiplesViajes.isEmpty()) return 0.0;

        double totalTrayectos = 0;
        int totalViajes = 0;

        for (String clienteId : clientesConMultiplesViajes) {
            List<String> viajesCliente = clienteViajesMap.get(clienteId);
            for (String viajeId : viajesCliente) {
                List<ItinerarioTransporte> itinerarios = itinerarioData.getAllItinerarioTransporte().stream()
                        .filter(it -> it.getViajeId().equals(viajeId))
                        .collect(Collectors.toList());
                totalTrayectos += itinerarios.size();
                totalViajes++;
            }
        }

        return totalViajes == 0 ? 0.0 : totalTrayectos / (double) totalViajes;
    }

    // G. Máximo número de actividades en un plan para viajes que tienen al menos un trayecto terrestre
    public int getMaximoActividadesPlanViajesConTrayectoTerrestre() {
        List<Viaje> viajes = viajeData.getAllViajes();
        int maximoActividades = 0;

        for (Viaje viaje : viajes) {
            if (tieneViajesTrayectosTerrestre(viaje.getId())) {
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

    // H. Conteo de clientes que han utilizado una aerolínea específica y han realizado al menos una actividad en un municipio específico
    public int getConteoClientesAerolineaMunicipio(String aerolineaId, String municipioId) {
        if (aerolineaId == null || municipioId == null) return 0;
        if (aerolineaId.trim().isEmpty() || municipioId.trim().isEmpty()) return 0;

        Set<String> clientesValidos = new HashSet<>();
        List<ClienteViaje> clienteViajes = clienteViajeData.getAllClienteViaje();

        for (ClienteViaje cv : clienteViajes) {
            String clienteId = cv.getIdCliente();
            String viajeId = cv.getIdViaje();

            boolean utilizoAerolinea = false;
            List<ItinerarioTransporte> itinerarios = itinerarioData.getAllItinerarioTransporte().stream()
                    .filter(it -> it.getViajeId().equals(viajeId))
                    .collect(Collectors.toList());

            for (ItinerarioTransporte it : itinerarios) {
                ServicioTransporte servicio = servicioData.findServicioTransporteById(it.getServicioTransporteId());
                if (servicio != null) {
                    Aeronave aeronave = aeronaveData.findAeronaveById(servicio.getIdVehiculo());
                    if (aeronave != null && aeronave.getAerolineaId().equals(aerolineaId)) {
                        utilizoAerolinea = true;
                        break;
                    }
                }
            }

            boolean realizoActividadMunicipio = false;
            List<Plan> planes = viajePlanData.findPlanesByViajeId(viajeId);

            for (Plan plan : planes) {
                List<ActividadTuristica> actividades = planActividadData.findActividadesByPlanId(plan.getId());
                for (ActividadTuristica actividad : actividades) {
                    if (actividad.getMunicipioId().equals(municipioId)) {
                        realizoActividadMunicipio = true;
                        break;
                    }
                }
                if (realizoActividadMunicipio) break;
            }

            if (utilizoAerolinea && realizoActividadMunicipio) {
                clientesValidos.add(clienteId);
            }
        }

        return clientesValidos.size();
    }

    // I. Suma total de costos de todos los servicios de transporte (trayectos) para un viaje específico
    public double getSumaCostosServiciosTransporteViaje(String viajeId) {
        if (viajeId == null || viajeId.trim().isEmpty()) return -1;

        Viaje viaje = viajeData.findViajeById(viajeId);
        if (viaje == null) return -1;

        double sumaTotal = 0;
        List<ItinerarioTransporte> itinerarios = itinerarioData.getAllItinerarioTransporte().stream()
                .filter(it -> it.getViajeId().equals(viajeId))
                .collect(Collectors.toList());

        for (ItinerarioTransporte it : itinerarios) {
            ServicioTransporte servicio = servicioData.findServicioTransporteById(it.getServicioTransporteId());
            if (servicio != null) {
                sumaTotal += servicio.getCosto();
            }
        }

        return sumaTotal;
    }

    // J. Retornar en una nueva lista todos los planes que incluyen una actividad con nombre específico y que han sido contratados por clientes con más de un viaje
    public List<Plan> getPlanesConActividadEspecificaClientesMultiplesViajes(String nombreActividad) {
        if (nombreActividad == null || nombreActividad.trim().isEmpty()) return new ArrayList<>();

        List<ClienteViaje> clienteViajes = clienteViajeData.getAllClienteViaje();
        Map<String, List<String>> clienteViajesMap = clienteViajes.stream()
                .collect(Collectors.groupingBy(
                        ClienteViaje::getIdCliente,
                        Collectors.mapping(ClienteViaje::getIdViaje, Collectors.toList())
                ));

        List<String> clientesMultiplesViajes = clienteViajesMap.entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        Set<String> planIds = new HashSet<>();

        for (String clienteId : clientesMultiplesViajes) {
            List<String> viajesCliente = clienteViajesMap.get(clienteId);

            for (String viajeId : viajesCliente) {
                List<Plan> planes = viajePlanData.findPlanesByViajeId(viajeId);

                for (Plan plan : planes) {
                    List<ActividadTuristica> actividades = planActividadData.findActividadesByPlanId(plan.getId());

                    for (ActividadTuristica actividad : actividades) {
                        if (actividad.getNombre().equals(nombreActividad)) {
                            planIds.add(plan.getId());
                        }
                    }
                }
            }
        }

        return planIds.stream()
                .map(planData::findPlanById)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    // K. Promedio de habitaciones reservadas por hotel en viajes que incluyen al menos un trayecto aéreo y un trayecto terrestre
    public double getPromedioHabitacionesReservadasPorHotelTrayectosAereosYTerrestres() {
        List<Viaje> viajes = viajeData.getAllViajes();
        Map<String, Integer> hotelHabitacionesCount = new HashMap<>();

        for (Viaje viaje : viajes) {
            if (tieneViajesTrayectosAereosYTerrestres(viaje.getId())) {
                List<ItinerarioTransporte> itinerarios = itinerarioData.getAllItinerarioTransporte().stream()
                        .filter(it -> it.getViajeId().equals(viaje.getId()))
                        .collect(Collectors.toList());

                for (ItinerarioTransporte it : itinerarios) {
                    List<HabitacionItinerario> habitacionesIt = habitacionItinerarioData.getAllHabitacionItinerario().stream()
                            .filter(hi -> hi.getIdItinerario().equals(it.getId()))
                            .collect(Collectors.toList());

                    for (HabitacionItinerario hi : habitacionesIt) {
                        Habitacion hab = habitacionData.findHabitacionById(hi.getIdHabitacion());
                        if (hab != null) {
                            hotelHabitacionesCount.put(hab.getHotelId(),
                                    hotelHabitacionesCount.getOrDefault(hab.getHotelId(), 0) + 1);
                        }
                    }
                }
            }
        }

        if (hotelHabitacionesCount.isEmpty()) return 0.0;

        double promedio = hotelHabitacionesCount.values().stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);

        return promedio;
    }

    // Método auxiliar: Verifica si un viaje tiene al menos un trayecto aéreo y uno terrestre
    private boolean tieneViajesTrayectosAereosYTerrestres(String viajeId) {
        boolean tieneAereo = false;
        boolean tieneTerrestre = false;

        List<ItinerarioTransporte> itinerarios = itinerarioData.getAllItinerarioTransporte().stream()
                .filter(it -> it.getViajeId().equals(viajeId))
                .collect(Collectors.toList());

        for (ItinerarioTransporte it : itinerarios) {
            ServicioTransporte servicio = servicioData.findServicioTransporteById(it.getServicioTransporteId());
            if (servicio != null) {
                Aeronave aeronave = aeronaveData.findAeronaveById(servicio.getIdVehiculo());
                if (aeronave != null) {
                    tieneAereo = true;
                } else {
                    Carro carro = carroData.findCarroById(servicio.getIdVehiculo());
                    if (carro != null) {
                        tieneTerrestre = true;
                    }
                }
            }
        }

        return tieneAereo && tieneTerrestre;
    }

    // Método auxiliar: Verifica si un viaje tiene al menos un trayecto terrestre
    private boolean tieneViajesTrayectosTerrestre(String viajeId) {
        List<ItinerarioTransporte> itinerarios = itinerarioData.getAllItinerarioTransporte().stream()
                .filter(it -> it.getViajeId().equals(viajeId))
                .collect(Collectors.toList());

        for (ItinerarioTransporte it : itinerarios) {
            ServicioTransporte servicio = servicioData.findServicioTransporteById(it.getServicioTransporteId());
            if (servicio != null) {
                Aeronave aeronave = aeronaveData.findAeronaveById(servicio.getIdVehiculo());
                if (aeronave == null) {
                    return true;
                }
            }
        }

        return false;
    }
}
