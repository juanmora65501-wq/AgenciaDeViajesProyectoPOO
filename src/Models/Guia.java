package Models;

public class Guia extends Usuario {

    private String idGuia;        // PK
    private String usuarioId;     // FK -> Usuario
    private String certificacion; // Certificación profesional del guía
    private String idiomas;       // Lista de idiomas (ej: "español, inglés")

    public Guia() {}

    public Guia(String idGuia, String usuarioId, String nombre, String apellido,
                String correo, String telefono, String rol, String contrasena,
                String certificacion, String idiomas) {

        super(usuarioId, nombre, apellido, correo, telefono, rol, contrasena);
        this.idGuia = idGuia;
        this.usuarioId = usuarioId;
        this.certificacion = certificacion;
        this.idiomas = idiomas;
    }

    public String getIdGuia() { return idGuia; }
    public void setIdGuia(String idGuia) { this.idGuia = idGuia; }

    public String getUsuarioId() { return usuarioId; }
    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }

    public String getCertificacion() { return certificacion; }
    public void setCertificacion(String certificacion) { this.certificacion = certificacion; }

    public String getIdiomas() { return idiomas; }
    public void setIdiomas(String idiomas) { this.idiomas = idiomas; }

    @Override
    public String toString() {
        return "Guia{" +
                "idGuia='" + idGuia + '\'' +
                ", usuarioId='" + usuarioId + '\'' +
                ", certificacion='" + certificacion + '\'' +
                ", idiomas='" + idiomas + '\'' +
                ", usuario=" + super.toString() +
                '}';
    }
}
