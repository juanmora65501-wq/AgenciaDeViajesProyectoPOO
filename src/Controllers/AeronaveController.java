package Controllers;

import DataAccess.AeronaveRepository;
import DataAccess.AerolineaRepository;
import Models.Aeronave;
import java.util.List;

public class AeronaveController {
    private AeronaveRepository aeronaveData;
    private AerolineaRepository aerolineaData;

    public AeronaveController() {
        this.aeronaveData = new AeronaveRepository();
        this.aerolineaData = new AerolineaRepository();
    }
    public AeronaveController(AeronaveRepository aeronaveData, AerolineaRepository aerolineaData) {
        this.aeronaveData = aeronaveData;
        this.aerolineaData = aerolineaData;
    }

    public List<Aeronave> getAllAeronaves() { return aeronaveData.getAllAeronaves(); }
    public Aeronave getAeronaveById(String id) { return aeronaveData.findAeronaveById(id); }

    public boolean addAeronave(String aerolineaId, String matricula, double autonomia, String tipoAeronave,
                               String tipo, String marca, String modelo, int capacidad, String placa) {
        if (aerolineaId == null || aerolineaId.trim().isEmpty()) return false;
        if (aerolineaData.findAeronlineaById(aerolineaId) == null) return false;
        if (matricula == null || matricula.trim().isEmpty()) return false;
        if (autonomia <= 0 || capacidad <= 0) return false;
        if (tipo == null || tipo.trim().isEmpty()) return false;
        if (marca == null || marca.trim().isEmpty()) return false;
        if (modelo == null || modelo.trim().isEmpty()) return false;

        Aeronave ae = new Aeronave();
        ae.setAerolineaId(aerolineaId.trim());
        ae.setMatricula(matricula.trim());
        ae.setAutonomia(autonomia);
        ae.setTipoAeronave(tipoAeronave != null ? tipoAeronave.trim() : "");
        ae.setTipo(tipo.trim());
        ae.setMarca(marca.trim());
        ae.setModelo(modelo.trim());
        ae.setCapacidad(capacidad);
        ae.setPlaca(placa != null ? placa.trim() : "");

        aeronaveData.saveAeronave(ae);
        return true;
    }

    public boolean updateAeronave(String id, String aerolineaId, String matricula, Double autonomia, String tipoAeronave,
                                  String tipo, String marca, String modelo, Integer capacidad, String placa) {
        Aeronave ae = aeronaveData.findAeronaveById(id);
        if (ae == null) return false;

        if (aerolineaId != null && !aerolineaId.trim().isEmpty()) {
            if (aerolineaData.findAeronlineaById(aerolineaId) == null) return false;
            ae.setAerolineaId(aerolineaId.trim());
        }
        if (matricula != null && !matricula.trim().isEmpty()) ae.setMatricula(matricula.trim());
        if (autonomia != null && autonomia > 0) ae.setAutonomia(autonomia);
        if (tipoAeronave != null) ae.setTipoAeronave(tipoAeronave.trim());
        if (tipo != null && !tipo.trim().isEmpty()) ae.setTipo(tipo.trim());
        if (marca != null && !marca.trim().isEmpty()) ae.setMarca(marca.trim());
        if (modelo != null && !modelo.trim().isEmpty()) ae.setModelo(modelo.trim());
        if (capacidad != null && capacidad > 0) ae.setCapacidad(capacidad);
        if (placa != null) ae.setPlaca(placa.trim());

        aeronaveData.saveAeronave(ae);
        return true;
    }

    public boolean deleteAeronave(String id) {
        aeronaveData.deleteAeronave(id);
        return true;
    }

    public List<Aeronave> findByAerolineaId(String aerolineaId) { return aeronaveData.findAeronavesByAerolineaId(aerolineaId); }
}
