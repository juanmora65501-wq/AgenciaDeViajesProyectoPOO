package Models;

public class Plan {
    private String id;
    private String nombre;
    private String descripcion;
    private boolean preestablecido;
    private double costoBase;

    public Plan() {}

    public Plan(String id, String nombre, String descripcion, boolean preestablecido, double costoBase) {
        this.id = id; this.nombre = nombre; this.descripcion = descripcion;
        this.preestablecido = preestablecido; this.costoBase = costoBase;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public boolean isPreestablecido() { return preestablecido; }
    public void setPreestablecido(boolean preestablecido) { this.preestablecido = preestablecido; }
    public double getCostoBase() { return costoBase; }
    public void setCostoBase(double costoBase) { this.costoBase = costoBase; }
}
