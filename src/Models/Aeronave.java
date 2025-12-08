package Models;

public class Aeronave extends Vehiculo {

    private String id;   
    private String aerolineaId;  // FK -> Aerolínea
    private String matricula;    // Identificación de aeronave
    private double autonomia;    // Autonomía de vuelo (km)
    private String tipoAeronave; // Ej: "Jet", "Helicóptero", "Avión comercial"

    public Aeronave() {}

    public Aeronave(String idAeronave, String aerolineaId, String matricula,
                    double autonomia, String tipoAeronave,
                    String vehiculoId, String tipo, String marca,
                    String modelo, int capacidad, String placa) {

        super(vehiculoId, tipo, marca, modelo, capacidad, placa);

        this.id = idAeronave;
        this.aerolineaId = aerolineaId;
        this.matricula = matricula;
        this.autonomia = autonomia;
        this.tipoAeronave = tipoAeronave;
    }

    public String getIdAeronave() { return id; }
    public void setIdAeronave(String idAeronave) { this.id = idAeronave; }

    public String getAerolineaId() { return aerolineaId; }
    public void setAerolineaId(String aerolineaId) { this.aerolineaId = aerolineaId; }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public double getAutonomia() { return autonomia; }
    public void setAutonomia(double autonomia) { this.autonomia = autonomia; }

    public String getTipoAeronave() { return tipoAeronave; }
    public void setTipoAeronave(String tipoAeronave) { this.tipoAeronave = tipoAeronave; }

}

