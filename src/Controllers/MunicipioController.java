package Controllers;

import DataAccess.MunicipioRepository;
import Models.Municipio;
import java.util.List;

public class MunicipioController {
    private MunicipioRepository municipioData;

    public MunicipioController() { this.municipioData = new MunicipioRepository(); }
    public MunicipioController(MunicipioRepository municipioData) { this.municipioData = municipioData; }

    public List<Municipio> getAllMunicipios() { return municipioData.getAllMunicipios(); }
    public Municipio getMunicipioById(String id) { return municipioData.findMunicipioById(id); }

    public boolean addMunicipio(String nombre, String departamento) {
        if (nombre == null || nombre.trim().isEmpty()) return false;
        if (departamento == null || departamento.trim().isEmpty()) return false;

        Municipio m = new Municipio();
        m.setNombre(nombre.trim());
        m.setDepartamento(departamento.trim());

        municipioData.saveMunicipio(m);
        return true;
    }

    public boolean updateMunicipio(String id, String nombre, String departamento) {
        Municipio m = municipioData.findMunicipioById(id);
        if (m == null) return false;

        if (nombre != null && !nombre.trim().isEmpty()) m.setNombre(nombre.trim());
        if (departamento != null && !departamento.trim().isEmpty()) m.setDepartamento(departamento.trim());

        municipioData.saveMunicipio(m);
        return true;
    }

    public boolean deleteMunicipio(String id) {
        municipioData.deleteMunicipio(id);
        return true;
    }
}
