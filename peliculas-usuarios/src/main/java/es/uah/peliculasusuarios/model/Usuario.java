package es.uah.peliculasusuarios.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false, length = 45)
    private String username;

    @Column(name = "password", nullable = false, length = 45)
    private String password;

    @Column(name = "correo", nullable = false, length = 45)
    private String correo;

    @Column(name = "enable", nullable = false)
    private Boolean enable = false;

    @OneToMany(mappedBy = "usuario", cascade = {CascadeType.ALL}, orphanRemoval =
            true)
    private List<Critica> criticas;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Users_has_Authorities", joinColumns = {
            @JoinColumn(name = "Users_idUsuario", referencedColumnName =
                    "idUsuario")}, inverseJoinColumns = {
            @JoinColumn(name = "Authorities_idRol", referencedColumnName = "idRol")})
    private List<Rol> roles;


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