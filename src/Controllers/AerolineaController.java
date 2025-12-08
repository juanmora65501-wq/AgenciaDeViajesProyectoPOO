package Controllers;

import DataAccess.AerolineaRepository;
import Models.Aerolinea;
import java.util.List;

public class AerolineaController {
    private AerolineaRepository aerolineaData;

    public AerolineaController() { this.aerolineaData = new AerolineaRepository(); }
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
}
