package Controllers;

import DataAccess.AerolineaRepository;
import DataAccess.AeronaveRepository;
import DataAccess.ServicioTransporteRepository;
import Models.Aerolinea;
import Models.Aeronave;
import Models.ServicioTransporte;
import java.util.List;

public class AerolineaController {
    private AerolineaRepository aerolineaData;
    private AeronaveRepository aeronaveData;
    private ServicioTransporteRepository servicioData;

    public AerolineaController() {
        this.aerolineaData = new AerolineaRepository();
        this.aeronaveData = new AeronaveRepository();
        this.servicioData = new ServicioTransporteRepository();
    }
    public AerolineaController(AerolineaRepository aerolineaData) { this.aerolineaData = aerolineaData; }

    public List<Aerolinea> getAllAerolineas() { return aerolineaData.getAllAerolineas(); }
    public Aerolinea getAerolineaById(String id) { return aerolineaData.findAeronlineaById(id); }

    public boolean addAerolinea(String nombre, String contacto) {
        if (nombre == null || nombre.trim().isEmpty()) return false;
        if (contacto == null || contacto.trim().isEmpty()) return false;

        Aerolinea a = new Aerolinea();
        a.setNombre(nombre.trim());
        a.setContacto(contacto.trim());

        aerolineaData.saveAerolinea(a);
        return true;
    }

    public boolean updateAerolinea(String id, String nombre, String contacto) {
        Aerolinea a = aerolineaData.findAeronlineaById(id);
        if (a == null) return false;

        if (nombre != null && !nombre.trim().isEmpty()) a.setNombre(nombre.trim());
        if (contacto != null && !contacto.trim().isEmpty()) a.setContacto(contacto.trim());

        aerolineaData.saveAerolinea(a);
        return true;
    }

    public boolean deleteAerolinea(String id) {
        aerolineaData.deleteAerolinea(id);
        return true;
    }

    // ==================== MÉTODOS ANALÍTICOS ====================

    // B. Mínimo costo de trayectos aéreos para una aerolínea
    public double getMinimoCostoTrayectoAereoParaAerolinea(String aerolineaId) {
        if (aerolineaId == null || aerolineaId.trim().isEmpty()) return -1;

        Aerolinea aerolinea = aerolineaData.findAeronlineaById(aerolineaId);
        if (aerolinea == null) return -1;

        double minimoCosto = Double.MAX_VALUE;
        boolean encontro = false;

        List<Aeronave> aeronaves = aeronaveData.getAllAeronaves();
        for (Aeronave aeronave : aeronaves) {
            if (aeronave.getAerolineaId().equals(aerolineaId)) {
                List<ServicioTransporte> servicios = servicioData.getAllServiciosTransporte();
                for (ServicioTransporte servicio : servicios) {
                    if (servicio.getIdVehiculo().equals(aeronave.getId())) {
                        if (servicio.getCosto() < minimoCosto) {
                            minimoCosto = servicio.getCosto();
                            encontro = true;
                        }
                    }
                }
            }
        }

        return encontro ? minimoCosto : -1;
    }
}
