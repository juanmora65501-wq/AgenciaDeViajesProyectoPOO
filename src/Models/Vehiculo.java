package Models;

public class Vehiculo {

    private String id;            // PK
    private String tipo;          // Ej: "carro", "aeronave"
    private String marca;         // Marca del vehículo
    private String modelo;        // Modelo
    private int capacidad;        // Capacidad de pasajeros
    private String placa;         // Solo si aplica (carros). Puede ser null.

    public Vehiculo() {}

    public Vehiculo(String id, String tipo, String marca, String modelo,
                    int capacidad, String placa) {
        this.id = id;
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.capacidad = capacidad;
        this.placa = placa;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

}
