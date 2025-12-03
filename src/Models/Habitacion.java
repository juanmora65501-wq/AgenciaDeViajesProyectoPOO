package Models;

public class Habitacion {
    private String id;
    private String hotelId; 
    private String tipo; 
    private double precio;
    private int capacidad;

    public Habitacion() {}

    public Habitacion(String id, String hotelId, String tipo, double precio, int capacidad) {
        this.id = id; this.hotelId = hotelId; this.tipo = tipo; this.precio = precio; this.capacidad = capacidad;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getHotelId() { return hotelId; }
    public void setHotelId(String hotelId) { this.hotelId = hotelId; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }
}
