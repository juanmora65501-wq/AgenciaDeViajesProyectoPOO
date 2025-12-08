package Controllers;

import DataAccess.UsuarioRepository;
import Models.Usuario;
import java.util.List;

public class UsuarioController {
    private UsuarioRepository usuarioData;

    public UsuarioController() { this.usuarioData = new UsuarioRepository(); }
    public UsuarioController(UsuarioRepository usuarioData) { this.usuarioData = usuarioData; }

    public List<Usuario> getAllUsuarios() { return usuarioData.getAllUsuarios(); }
    public Usuario getUsuarioById(String id) { return usuarioData.findUsuarioById(id); }

    public boolean addUsuario(String nombre, String apellido, String correo, String telefono, String rol, String contrasena) {
        if (nombre == null || nombre.trim().isEmpty()) return false;
        if (apellido == null || apellido.trim().isEmpty()) return false;
        if (correo == null || correo.trim().isEmpty()) return false;
        if (rol == null || rol.trim().isEmpty()) return false;

        Usuario u = new Usuario();
        u.setNombre(nombre.trim());
        u.setApellido(apellido.trim());
        u.setCorreo(correo.trim());
        u.setTelefono(telefono != null ? telefono.trim() : "");
        u.setRol(rol.trim());
        u.setContrasena(contrasena != null ? contrasena.trim() : "");

        usuarioData.saveUsuario(u);
        return true;
    }

    public boolean updateUsuario(String id, String nombre, String apellido, String correo, String telefono, String rol, String contrasena) {
        Usuario u = usuarioData.findUsuarioById(id);
        if (u == null) return false;

        if (nombre != null && !nombre.trim().isEmpty()) u.setNombre(nombre.trim());
        if (apellido != null && !apellido.trim().isEmpty()) u.setApellido(apellido.trim());
        if (correo != null && !correo.trim().isEmpty()) u.setCorreo(correo.trim());
        if (telefono != null) u.setTelefono(telefono.trim());
        if (rol != null && !rol.trim().isEmpty()) u.setRol(rol.trim());
        if (contrasena != null) u.setContrasena(contrasena.trim());

        usuarioData.saveUsuario(u);
        return true;
    }

    public boolean deleteUsuario(String id) {
        usuarioData.deleteUsuario(id);
        return true;
    }
}
