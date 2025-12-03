package Models;

public class Municipio {
    private String id;
    private String nombre;
    private String departamento;

    public Municipio() {}

    public Municipio(String id, String nombre, String departamento) {
        this.id = id; this.nombre = nombre; this.departamento = departamento;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }
}
