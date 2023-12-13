package es.uah.peliculasusuarios.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Usuario {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idUsuario")
    private Integer idUsuario;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "correo")
    private String correo;
    @Basic
    @Column(name = "enable")
    private Byte enable;

    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(name = "usuarios_tiene_authorities",
            joinColumns = @JoinColumn(name = "idUsuario"),
            inverseJoinColumns = @JoinColumn(name = "idRol"))
    private List<Rol> roles = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Critica> criticas = new ArrayList<>();

    public List<Critica> getCriticas() {
        return criticas;
    }

    public void setCriticas(List<Critica> criticas) {
        this.criticas = criticas;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Byte getEnable() {
        return enable;
    }

    public void setEnable(Byte enable) {
        this.enable = enable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuarios = (Usuario) o;
        return Objects.equals(idUsuario, usuarios.idUsuario) && Objects.equals(username, usuarios.username) && Objects.equals(password, usuarios.password) && Objects.equals(correo, usuarios.correo) && Objects.equals(enable, usuarios.enable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, username, password, correo, enable);
    }

    public void addCritica(Critica critica) {
        getCriticas().add(critica);
        critica.setUsuario(this);
    }
    public void removeCritica(Critica critica) {
        if (critica != null) {
            getCriticas().remove(critica);
        }
    }

}
