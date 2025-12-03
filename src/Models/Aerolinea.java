package Models;

public class Aerolinea {
    private String id;
    private String nombre;
    private String contacto;

    public Aerolinea() {}

    public Aerolinea(String id, String nombre, String contacto) {
        this.id = id; this.nombre = nombre; this.contacto = contacto;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getContacto() { return contacto; }
    public void setContacto(String contacto) { this.contacto = contacto; }

}
