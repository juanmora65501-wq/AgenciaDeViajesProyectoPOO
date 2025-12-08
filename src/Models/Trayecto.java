package Models;

public class Trayecto {

    private String id;
    private String idMunicipioOrigen;
    private String idMunicipioDestino;

    public Trayecto() {}

    public Trayecto(String id, String idMunicipioOrigen, String idMunicipioDestino) {
        this.id = id;
        this.idMunicipioOrigen = idMunicipioOrigen;
        this.idMunicipioDestino = idMunicipioDestino;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdMunicipioOrigen() {
        return idMunicipioOrigen;
    }

    public void setIdMunicipioOrigen(String idMunicipioOrigen) {
        this.idMunicipioOrigen = idMunicipioOrigen;
    }

    public String getIdMunicipioDestino() {
        return idMunicipioDestino;
    }

    public void setIdMunicipioDestino(String idMunicipioDestino) {
        this.idMunicipioDestino = idMunicipioDestino;
    }
}
