package es.uah.peliculasfront.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Usuario {

    //He tenido que poner @JsonProperty a
    //algunos campos aunque tuvieran el mismo
    //nombre que en el otro microservicio

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;
    private String correo;
    private boolean enable;
    private List<Rol> roles;
    private List<Critica> criticas;

    public Usuario(Integer id, String username, String password, String correo, boolean enable, List<Rol> roles, List<Critica> criticas) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.correo = correo;
        this.enable = enable;
        this.roles = roles;
        this.criticas = criticas;
    }

    public Usuario() {
    }

    public Integer getIdUsuario() {
        return id;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.id = idUsuario;
    }

    public String getNombre() {
        return username;
    }

    public void setNombre(String nombre) {
        this.username = nombre;
    }

    public String getClave() {
        return password;
    }

    public void setClave(String clave) {
        this.password = clave;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public List<Critica> getCriticas() {
        return criticas;
    }

    public void setCriticas(List<Critica> criticas) {
        this.criticas = criticas;
    }
}
