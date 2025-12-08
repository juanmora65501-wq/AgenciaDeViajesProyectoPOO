package Controllers;

import DataAccess.ClienteRepository;
import DataAccess.UsuarioRepository;
import Models.Cliente;
import Models.Usuario;
import java.util.List;

public class ClienteController {
    private ClienteRepository clienteData;
    private UsuarioRepository usuarioData;

    public ClienteController() {
        this.clienteData = new ClienteRepository();
        this.usuarioData = new UsuarioRepository();
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
}

