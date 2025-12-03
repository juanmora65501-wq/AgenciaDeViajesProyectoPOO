package Models;

public class Usuario {

    private String id;         // PK
    private String nombre;     // Nombre del usuario
    private String apellido;   // Apellido del usuario
    private String correo;     // Correo electrónico
    private String telefono;   // Teléfono de contacto
    private String rol;       // Tipo de usuario (ej: "cliente", "admin", "guia")
    private String contrasena;

    public Usuario() {}

    public Usuario(String id, String nombre, String apellido, String correo,
                   String telefono, String rol, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.rol = rol;
        this.contrasena = contrasena;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol= rol; }

    public String getContrasena() {return contrasena;}
    public void setContrasena(String contrasena) {this.contrasena = contrasena;}
    
}
