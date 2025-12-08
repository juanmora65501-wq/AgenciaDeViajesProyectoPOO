package Controllers;

import DataAccess.GuiaRepository;
import DataAccess.UsuarioRepository;
import Models.Guia;
import Models.Usuario;
import java.util.List;

public class GuiaController {
    private GuiaRepository guiaData;
    private UsuarioRepository usuarioData;

    public GuiaController() {
        this.guiaData = new GuiaRepository();
        this.usuarioData = new UsuarioRepository();
    }

    public GuiaController(GuiaRepository guiaData, UsuarioRepository usuarioData) {
        this.guiaData = guiaData;
        this.usuarioData = usuarioData;
    }

    public List<Guia> getAllGuias() { return guiaData.getAllGuias(); }
    public Guia getGuiaById(String id) { return guiaData.findGuiaById(id); }

    public boolean addGuia(String usuarioId, String certificacion, String idiomas) {
        if (usuarioId == null || usuarioId.trim().isEmpty()) return false;
        
        Usuario u = usuarioData.findUsuarioById(usuarioId);
        if (u == null) return false;

        Guia g = new Guia();
        g.setUsuarioId(usuarioId);
        g.setCertificacion(certificacion != null ? certificacion.trim() : "");
        g.setIdiomas(idiomas != null ? idiomas.trim() : "");

        guiaData.saveGuia(g);
        return true;
    }

    public boolean updateGuia(String id, String certificacion, String idiomas) {
        Guia g = guiaData.findGuiaById(id);
        if (g == null) return false;

        if (certificacion != null) g.setCertificacion(certificacion.trim());
        if (idiomas != null) g.setIdiomas(idiomas.trim());

        guiaData.saveGuia(g);
        return true;
    }

    public boolean deleteGuia(String id) {
        guiaData.deleteGuia(id);
        return true;
    }
}
