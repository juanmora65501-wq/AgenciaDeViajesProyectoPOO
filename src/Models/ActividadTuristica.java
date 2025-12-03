package Models;

public class ActividadTuristica {
    private String id;
    private String nombre;
    private String descripcion;
    private String municipioId;
    private String duracion; // e.g. "2h" or "180min"
    private double costo;

    public ActividadTuristica() {}

    public ActividadTuristica(String id, String nombre, String descripcion,
                              String municipioId, String duracion, double costo) {
        this.id = id; this.nombre = nombre; this.descripcion = descripcion;
        this.municipioId = municipioId; this.duracion = duracion; this.costo = costo;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getMunicipioId() { return municipioId; }
    public void setMunicipioId(String municipioId) { this.municipioId = municipioId; }
    public String getDuracion() { return duracion; }
    public void setDuracion(String duracion) { this.duracion = duracion; }
    public double getCosto() { return costo; }
    public void setCosto(double costo) { this.costo = costo; }
    
}

