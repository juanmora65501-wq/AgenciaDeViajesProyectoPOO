package Models;

public class Hotel {
    private String id;
    private String nombre;
    private String municipioId; 
    private String direccion;
    private String contacto;
    private int totalHabitaciones; // resumen (puede calcularse desde Habitacion)

    public Hotel() {}

    public Hotel(String id, String nombre, String municipioId, String direccion, String contacto, int totalHabitaciones) {
        this.id = id; this.nombre = nombre; this.municipioId = municipioId;
        this.direccion = direccion; this.contacto = contacto; this.totalHabitaciones = totalHabitaciones;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getMunicipioId() { return municipioId; }
    public void setMunicipioId(String municipioId) { this.municipioId = municipioId; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getContacto() { return contacto; }
    public void setContacto(String contacto) { this.contacto = contacto; }
    public int getTotalHabitaciones() { return totalHabitaciones; }
    public void setTotalHabitaciones(int totalHabitaciones) { this.totalHabitaciones = totalHabitaciones; }
}
