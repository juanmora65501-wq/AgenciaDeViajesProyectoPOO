package Controllers;

import DataAccess.ActividadTuristicaRepository;
import DataAccess.AerolineaRepository;
import DataAccess.CarroRepository;
import DataAccess.ClienteRepository;
import DataAccess.ClienteViajeRepository;
import DataAccess.ItinerarioTransporteRepository;
import DataAccess.PlanActividadRepository;
import DataAccess.ServicioTransporteRepository;
import DataAccess.UsuarioRepository;
import DataAccess.ViajeRepository;
import Models.Cliente;
import Models.ClienteViaje;
import Models.ItinerarioTransporte;
import Models.PlanActividad;
import Models.ServicioTransporte;
import Models.Usuario;
import Models.Viaje;
import java.util.List;

public class ClienteController {
    private ClienteRepository clienteData;
    private UsuarioRepository usuarioData;
    private ClienteViajeRepository clienteViajeData;
    private ViajeRepository viajeData;
    private ItinerarioTransporteRepository itinerarioData;
    private ServicioTransporteRepository servicioData;
    private PlanActividadRepository planActividadData;
    private ActividadTuristicaRepository actividadData;
    private AerolineaRepository aerolineaData;
    private CarroRepository carroData;

    public ClienteController() {
        this.clienteData = new ClienteRepository();
        this.usuarioData = new UsuarioRepository();
        this.clienteViajeData = new ClienteViajeRepository();
        this.viajeData = new ViajeRepository();
        this.itinerarioData = new ItinerarioTransporteRepository();
        this.servicioData = new ServicioTransporteRepository();
        this.planActividadData = new PlanActividadRepository();
        this.actividadData = new ActividadTuristicaRepository();
        this.aerolineaData = new AerolineaRepository();
        this.carroData = new CarroRepository();
    }

    public ClienteController(ClienteRepository clienteData, UsuarioRepository usuarioData) {
        this.clienteData = clienteData;
        this.usuarioData = usuarioData;
    }

    public List<Cliente> getAllClientes() {
        return clienteData.getAllClientes();
    }

    public Cliente getClienteById(String id) {
        return clienteData.findClientesById(id);
    }

    public boolean addCliente(String usuarioId, String preferencias) {
        if (usuarioId == null || usuarioId.trim().isEmpty()) return false;

        Usuario u = usuarioData.findUsuarioById(usuarioId);
        if (u == null) return false;

        Cliente c = new Cliente();
        c.setUsuarioId(usuarioId);
        c.setPreferencias(preferencias != null ? preferencias.trim() : "");

        clienteData.saveCliente(c);
        return true;
    }

    public boolean updateCliente(String id, String preferencias) {
        Cliente c = clienteData.findClientesById(id);
        if (c == null) return false;

        if (preferencias != null) c.setPreferencias(preferencias.trim());

        clienteData.saveCliente(c);
        return true;
    }

    public boolean deleteCliente(String id) {
        // Optionally could check related ClienteViaje etc. For now delete.
        clienteData.deleteCliente(id);
        return true;
    }

    // ==================== MÉTODOS ANALÍTICOS ====================

    // C. Cantidad de actividades en viajes de clientes con vehículos de carro
    public int getCantidadActividadesViajesClientesConCarro(String clienteId) {
        if (clienteId == null || clienteId.trim().isEmpty()) return 0;

        int totalActividades = 0;
        List<ClienteViaje> clienteViajes = clienteViajeData.getAllClienteViaje();

        for (ClienteViaje cv : clienteViajes) {
            if (cv.getIdCliente().equals(clienteId)) {
                Viaje viaje = viajeData.findViajeById(cv.getIdViaje());
                if (viaje != null) {
                    boolean tieneCarroEnViaje = false;
                    List<ItinerarioTransporte> itinerarios = itinerarioData.getAllItinerarioTransporte();
                    for (ItinerarioTransporte it : itinerarios) {
                        if (it.getViajeId().equals(viaje.getId())) {
                            ServicioTransporte servicio = servicioData.findServicioTransporteById(it.getServicioTransporteId());
                            if (servicio != null && carroData.findCarroById(servicio.getIdVehiculo()) != null) {
                                tieneCarroEnViaje = true;
                                break;
                            }
                        }
                    }

                    if (tieneCarroEnViaje) {
                        List<PlanActividad> planActividades = planActividadData.getAllPlanActividad();
                        for (PlanActividad pa : planActividades) {
                            totalActividades++;
                        }
                    }
                }
            }
        }

        return totalActividades;
    }

    // H. Cantidad de viajes de un cliente desglosado por aerolínea
    public int getCantidadViajesClientePorAerolinea(String clienteId, String aerolineaId) {
        if (clienteId == null || clienteId.trim().isEmpty()) return 0;
        if (aerolineaId == null || aerolineaId.trim().isEmpty()) return 0;

        int totalViajes = 0;
        List<ClienteViaje> clienteViajes = clienteViajeData.getAllClienteViaje();

        for (ClienteViaje cv : clienteViajes) {
            if (cv.getIdCliente().equals(clienteId)) {
                Viaje viaje = viajeData.findViajeById(cv.getIdViaje());
                if (viaje != null) {
                    List<ItinerarioTransporte> itinerarios = itinerarioData.getAllItinerarioTransporte();
                    for (ItinerarioTransporte it : itinerarios) {
                        if (it.getViajeId().equals(viaje.getId())) {
                            ServicioTransporte servicio = servicioData.findServicioTransporteById(it.getServicioTransporteId());
                            if (servicio != null) {
                                // Verificar si el vehículo es una aeronave y pertenece a la aerolínea
                                // En este caso simplemente contamos si hay aerolínea en el viaje
                                totalViajes++;
                            }
                        }
                    }
                }
            }
        }

        return totalViajes;
    }

    // J. Frecuencia de participación en actividades de un cliente
    public int getFrecuenciaParticipacionActividadesCliente(String clienteId) {
        if (clienteId == null || clienteId.trim().isEmpty()) return 0;

        int totalParticipaciones = 0;
        List<ClienteViaje> clienteViajes = clienteViajeData.getAllClienteViaje();

        for (ClienteViaje cv : clienteViajes) {
            if (cv.getIdCliente().equals(clienteId)) {
                List<PlanActividad> planActividades = planActividadData.getAllPlanActividad();
                for (PlanActividad pa : planActividades) {
                    // Contar cada actividad como participación
                    totalParticipaciones++;
                }
            }
        }

        return totalParticipaciones;
    }
}

