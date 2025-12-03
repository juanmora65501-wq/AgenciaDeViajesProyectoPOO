package Models;

public class Cliente extends Usuario {

    private String idCliente;     // PK del cliente
    private String usuarioId;     // FK -> Usuario
    private String preferencias;  // Preferencias turísticas (opcional)

    public Cliente() {}

    public Cliente(String idCliente, String usuarioId, String nombre, String apellido,
                   String correo, String telefono, String rol, String contrasena,String preferencias) {

        super(usuarioId, nombre, apellido, correo, telefono, rol, contrasena);
        this.idCliente = idCliente;
        this.usuarioId = usuarioId;
        this.preferencias = preferencias;
    }

    public String getIdCliente() { return idCliente; }
    public void setIdCliente(String idCliente) { this.idCliente = idCliente; }

    public String getUsuarioId() { return usuarioId; }
    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }

    public String getPreferencias() { return preferencias; }
    public void setPreferencias(String preferencias) { this.preferencias = preferencias; }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente='" + idCliente + '\'' +
                ", usuarioId='" + usuarioId + '\'' +
                ", preferencias='" + preferencias + '\'' +
                ", usuario=" + super.toString() +
                '}';
    }
}
